package com.kat.dmc.common.util;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileUtil {
    @Value("${system.file_upload_dir}")
    private String fileUploadDir;

    public String uploadFile(byte[] fileData, String fileCode) throws IOException {
        String filePath = fileUploadDir + File.separator + fileCode + ".dmc";
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(fileData);
        }
        return filePath;
    }

    public byte[] readFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readAllBytes(path);
    }

    public void removeFile(String filepath){
        File file = new File(filepath);
        if(file.exists() && file.canWrite()){
            file.delete();
        }
    }
    public static String writeReport2Temp(String filePath) throws IOException{
        String tempFile = filePath+".temp";
        String username = ResourceUtil.getProperty("application.properties", "spring.datasource.username");
        String password = ResourceUtil.getProperty("application.properties", "spring.datasource.password");
        String url = ResourceUtil.getProperty("application.properties", "spring.datasource.url");
        byte[] encodedBytes = Base64.encodeBase64(password.getBytes());
        BufferedReader br;
        BufferedWriter pw;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF8"));
            pw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempFile), "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                if(line.contains("name=\"odaURL\"")){
                    line = " <property name=\"odaURL\">"+url+"</property> ";
                }else if(line.contains("name=\"odaUser\"")){
                    line = " <property name=\"odaUser\">"+username+"</property> ";
                }else if(line.contains("name=\"odaPassword\"")){
                    line = " <encrypted-property name=\"odaPassword\" encryptionID=\"base64\">"+new String(encodedBytes)+"</encrypted-property> ";
                }
                pw.append(line+"\r\n");
            }
            br.close();
            pw.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return tempFile;
    }

}
