package com.kat.dmc.service.interfaces;

import com.kat.dmc.common.dto.JobPositionDto;

import java.util.List;

public interface JobPositionService {
    List<JobPositionDto> findAll();
    List<JobPositionDto> findAllActive();
    void save(JobPositionDto userDto);
    void delete(Integer id);
}
