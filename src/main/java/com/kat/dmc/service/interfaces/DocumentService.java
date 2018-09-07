package com.kat.dmc.service.interfaces;

import com.kat.dmc.common.dto.DocumentDto;

import javax.persistence.NoResultException;
import java.util.List;

public interface DocumentService {
    DocumentDto findOneByCode(String code) throws NoResultException;
    List<DocumentDto> findByEmployeeId(int id);
}
