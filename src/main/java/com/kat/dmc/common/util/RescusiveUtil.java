package com.kat.dmc.common.util;

import com.kat.dmc.common.dto.ObjectDto;

import java.util.List;

public class RescusiveUtil {

    public static void rescusiveSetPermission(List<ObjectDto> lstPermission, List<Integer> objectIDHasPermission){
        for(ObjectDto permissionDto : lstPermission) {
            if (objectIDHasPermission.contains(permissionDto.getObjectId())) {
                permissionDto.setHasPermission(true);
            }
            if(permissionDto.getLstChildObject() != null) {
                rescusiveSetPermission(permissionDto.getLstChildObject(), objectIDHasPermission);
            }
        }
    }
}
