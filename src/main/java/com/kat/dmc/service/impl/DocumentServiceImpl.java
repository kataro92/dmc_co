package com.kat.dmc.service.impl;

import com.kat.dmc.common.model.DmcDocumentEntity;
import com.kat.dmc.common.dto.DocumentDto;
import com.kat.dmc.repository.interfaces.DocumentRepo;
import com.kat.dmc.service.interfaces.DocumentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    DocumentRepo documentRepo;

    @Override
    public DocumentDto findOneByCode(String code)  throws NoResultException{
        return modelMapper.map(documentRepo.findOneByCode(code), DocumentDto.class);
    }

    @Override
    public List<DocumentDto> findByEmployeeId(int id) {
        List<DmcDocumentEntity> documentEntities = documentRepo.findAllByTypeAndParentId("document", id);
        List<DocumentDto> lstDocument = new ArrayList<>();
        for(DmcDocumentEntity documentEntity : documentEntities){
            lstDocument.add(modelMapper.map(documentEntity, DocumentDto.class));
        }
        return lstDocument;
    }
}
