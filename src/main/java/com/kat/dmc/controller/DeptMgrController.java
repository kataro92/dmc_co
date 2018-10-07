package com.kat.dmc.controller;


import com.kat.dmc.common.constant.ControllerAction;
import com.kat.dmc.common.dto.DepartmentDto;
import com.kat.dmc.common.dto.DocumentDto;
import com.kat.dmc.common.dto.EmployeeDto;
import com.kat.dmc.common.dto.JobPositionDto;
import com.kat.dmc.common.util.CommonUtil;
import com.kat.dmc.common.util.FileUtil;
import com.kat.dmc.common.util.SQLErrorUtil;
import com.kat.dmc.repository.interfaces.UtilRepo;
import com.kat.dmc.service.interfaces.*;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Named("deptMgr")
@ViewScoped
@RestController
@RequestMapping("/api")
public class DeptMgrController implements Serializable {

    @Autowired
    DeptService deptService;

    @Autowired
    ObjectService objectService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    UtilRepo utilRepo;

    @Autowired
    JobPositionService jobPositionService;

    @Autowired
    FileUtil fileUtil;

    @Autowired
    DocumentService documentService;

    private ControllerAction.State currentAct;
    private ControllerAction.State currentEmployeeAct;
    private boolean disableAdd;
    private boolean disableCopy;
    private boolean disableEdit;
    private boolean disableDelete;
    private DepartmentDto selectedDept;
    private DepartmentDto tempDept;
    private List<DepartmentDto> lstAllDept;
    private List<DepartmentDto> lstFilteredDept;
    private TreeNode root;
    private EmployeeDto selectedEmployee;
    private List<JobPositionDto> lstAllPosition;


    @PostConstruct
    public void init(){
        setCurrentAct(ControllerAction.State.VIEW);
        setCurrentEmployeeAct(ControllerAction.State.VIEW);
        lstAllDept = deptService.findAll();
        makeDepttree();
        lstAllPosition = jobPositionService.findAllActive();
        selectedEmployee = new EmployeeDto();
    }

    @GetMapping("/downloadDocument/{code}")
    public void downloadDocument(@PathVariable(name = "code") String code, HttpServletRequest request, HttpServletResponse response) throws IOException {
        DocumentDto documentDto = null;
        try {
            documentDto = documentService.findOneByCode(code);
        }catch (NoResultException ex){
            List<DocumentDto> lstDocuments = getSelectedEmployee().getLstDocuments();
            for(DocumentDto dto : lstDocuments){
                if(dto.getCode().equals(code)){
                    documentDto = dto;
                }
            }
        }
        response.setContentType("application/octet-stream");
        String userAgent = request.getHeader("User-Agent");
        String newFilename = CommonUtil.makeJapanEncodeOnly(documentDto.getName()).replaceAll("\\+", "%20");
        if(userAgent != null && (userAgent.contains("Firefox") || userAgent.contains("Safari"))){
            response.setHeader("Content-Disposition","attachment; filename*=utf-8''" + CommonUtil.rfc5987_encode(documentDto.getName()));
        }else {
            response.setHeader("Content-Disposition", "attachment; filename=" + newFilename);
        }
        ServletOutputStream out = response.getOutputStream();
        if(documentDto != null) {
            out.write(fileUtil.readFile(documentDto.getPath()));
        }
    }

    public void actDeleteDocument(Integer index){
        selectedEmployee.getLstDocuments().remove(index.intValue());
        PrimeFaces.current().ajax().update("main:pnlDocumentList");
    }

    public void handleFileUpload(FileUploadEvent event){
        if(selectedEmployee.getLstDocuments() == null){
            selectedEmployee.setLstDocuments(new ArrayList<>());
        }
        UploadedFile file = event.getFile();
        byte[] contents = file.getContents();
        String fileName = file.getFileName();
        DocumentDto documentDto = new DocumentDto();
        documentDto.setName(fileName);
        documentDto.setId(utilRepo.findSequenceNextval("dmc_document_id_seq"));
        documentDto.setCode("DO" + String.format("%06d", documentDto.getId()));
        try {
            String uploadFile = fileUtil.uploadFile(contents, documentDto.getCode());
            documentDto.setPath(uploadFile);
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR
                    , SQLErrorUtil.getSQLError(e), "Tải tài liệu lên bị lỗi"));
        }
        selectedEmployee.getLstDocuments().add(documentDto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO
                , fileName, "Tải lên thành công !"));
        PrimeFaces.current().ajax().update("main:pnlDocumentList");
    }

    private void makeDepttree(){
        lstAllDept.sort((p1, p2) -> p1.getDefCode().compareTo(p2.getDefCode()));
        root = new DefaultTreeNode(null, null);
        lstAllDept.stream().filter(departmentDto -> CommonUtil.isEmpty(departmentDto.getParentCode())).forEach(departmentDto -> {
            TreeNode deptRoot = new DefaultTreeNode(departmentDto, root);
            rescusiveDeptTree(deptRoot, departmentDto);
        });
    }

    private void rescusiveDeptTree(TreeNode deptRoot, DepartmentDto departmentDto){
        for(DepartmentDto child : departmentDto.getLstChildDept()){
            TreeNode deptChild = new DefaultTreeNode(child, deptRoot);
            rescusiveDeptTree(deptChild, child);
        }
    }

    public void selectDept(NodeSelectEvent event){
        tempDept = (DepartmentDto) event.getTreeNode().getData();
        selectedDept = tempDept.clone();
        selectedDept.setLstEmployees(employeeService.findByDeptId(selectedDept.getId()));

    }

    public void actAddEmployee(){
        selectedEmployee = new EmployeeDto();
        selectedEmployee.setId(utilRepo.findSequenceNextval("employee__id_seq"));
        selectedEmployee.setCode("NV" + String.format("%06d", selectedEmployee.getId()));
        setCurrentEmployeeAct(ControllerAction.State.ADD);
        PrimeFaces.current().executeScript("PF('pnlAddEmployee').show()");
    }

    public void actAdd(){
        setCurrentAct(ControllerAction.State.ADD);
        selectedDept = new DepartmentDto();
        selectedDept.setId(utilRepo.findSequenceNextval("department__id_seq"));
        selectedDept.setDefCode("PB" + String.format("%06d", selectedDept.getId()));
        selectedDept.setLstChildDept(new ArrayList<>());
    }
    public void actCopy(){
        selectedDept.setId(utilRepo.findSequenceNextval("department__id_seq"));
        selectedDept.setDefCode("PB" + String.format("%06d", selectedDept.getId()));
        selectedDept.setLstChildDept(new ArrayList<>());
        setCurrentAct(ControllerAction.State.COPY);
    }
    public void actEdit(){
        setCurrentAct(ControllerAction.State.EDIT);
    }
    public void actDelete(){
        try{
            deptService.delete(selectedDept.getId());
            lstAllDept.removeIf(s -> s.getId() == selectedDept.getId());
            selectedDept = null;
            root = new DefaultTreeNode(null, null);
            setCurrentAct(ControllerAction.State.VIEW);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO
                    , "Info", "Xoá thành công"));
        }catch (Exception ex){
            Logger.getLogger(this.getClass().getName()).warning(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR
                    , SQLErrorUtil.getSQLError(ex), "Có lỗi xảy ra"));
        }
    }
    public void actBack(){
        selectedDept = tempDept;
        setCurrentAct(ControllerAction.State.VIEW);
    }

    public void actViewEmployee(Integer index){
        selectedEmployee = selectedDept.getLstEmployees().get(index).clone();
        if(currentAct == ControllerAction.State.VIEW){
            setCurrentEmployeeAct(ControllerAction.State.VIEW);
        }else{
            setCurrentEmployeeAct(ControllerAction.State.EDIT);
        }
        PrimeFaces.current().ajax().update("main:pnlAddEmployee");
        PrimeFaces.current().executeScript("PF('pnlAddEmployee').show()");
    }

    public void actAcceptEmployee(){
        boolean meet = false;
        int idx = -1;
        if(selectedDept.getLstEmployees() != null) {
            for (int i = 0; i < selectedDept.getLstEmployees().size(); i++) {
                if (selectedDept.getLstEmployees().get(i).getId() == selectedEmployee.getId()) {
                    meet = true;
                    idx = i;
                }
            }
        }else{
            selectedDept.setLstEmployees(new ArrayList<>());
        }
        if(!meet){
            selectedDept.getLstEmployees().add(selectedEmployee.clone());
        }else if(idx != -1){
            selectedDept.getLstEmployees().set(idx, selectedEmployee.clone());
        }
        setCurrentEmployeeAct(ControllerAction.State.VIEW);
        PrimeFaces.current().ajax().update("main:pnlDetail");
        PrimeFaces.current().executeScript("PF('pnlAddEmployee').hide()");
    }

    public void actDeleteEmployee(){
        int idx = -1;
        for (int i = 0 ; i < selectedDept.getLstEmployees().size(); i++) {
            if(selectedDept.getLstEmployees().get(i).getId() == selectedEmployee.getId()){
                idx = i;
            }
        }
        if(idx != -1){
            selectedDept.getLstEmployees().remove(idx);
        }
        setCurrentEmployeeAct(ControllerAction.State.VIEW);
        PrimeFaces.current().ajax().update("main:pnlDetail");
        PrimeFaces.current().executeScript("PF('pnlAddEmployee').hide()");
    }

    public void actAccept(){
        try {
            deptService.save(selectedDept);
            tempDept = selectedDept;
            lstAllDept = deptService.findAll();
            makeDepttree();
            setCurrentAct(ControllerAction.State.VIEW);
            PrimeFaces.current().executeScript("PF('blkList').hide()");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO
                    , "", "Lưu thông tin thành công"));
        }catch (Exception ex){
            Logger.getLogger(this.getClass().getName()).warning(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR
                    , SQLErrorUtil.getSQLError(ex), "Có lỗi xảy ra"));
        }
    }

    public ControllerAction.State getCurrentAct() {
        return currentAct;
    }

    public void setCurrentAct(ControllerAction.State currentAct) {
        this.currentAct = currentAct;
    }

    public boolean isDisableAdd() {
        return disableAdd;
    }

    public void setDisableAdd(boolean disableAdd) {
        this.disableAdd = disableAdd;
    }

    public boolean isDisableCopy() {
        return disableCopy;
    }

    public void setDisableCopy(boolean disableCopy) {
        this.disableCopy = disableCopy;
    }

    public boolean isDisableEdit() {
        return disableEdit;
    }

    public void setDisableEdit(boolean disableEdit) {
        this.disableEdit = disableEdit;
    }

    public boolean isDisableDelete() {
        return disableDelete;
    }

    public void setDisableDelete(boolean disableDelete) {
        this.disableDelete = disableDelete;
    }

    public DepartmentDto getSelectedDept() {
        return selectedDept;
    }

    public void setSelectedDept(DepartmentDto selectedDept) {
        this.selectedDept = selectedDept;
    }

    public List<DepartmentDto> getLstAllDept() {
        return lstAllDept;
    }

    public void setLstAllDept(List<DepartmentDto> lstAllDept) {
        this.lstAllDept = lstAllDept;
    }

    public List<DepartmentDto> getLstFilteredDept() {
        return lstFilteredDept;
    }

    public void setLstFilteredDept(List<DepartmentDto> lstFilteredDept) {
        this.lstFilteredDept = lstFilteredDept;
    }

    public TreeNode getRoot() {
        return root;
    }

    public EmployeeDto getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(EmployeeDto selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    public List<JobPositionDto> getLstAllPosition() {
        return lstAllPosition;
    }

    public void setLstAllPosition(List<JobPositionDto> lstAllPosition) {
        this.lstAllPosition = lstAllPosition;
    }

    public ControllerAction.State getCurrentEmployeeAct() {
        return currentEmployeeAct;
    }

    public void setCurrentEmployeeAct(ControllerAction.State currentEmployeeAct) {
        this.currentEmployeeAct = currentEmployeeAct;
    }
}
