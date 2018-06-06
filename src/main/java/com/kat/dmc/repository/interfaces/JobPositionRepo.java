package com.kat.dmc.repository.interfaces;

import com.kat.dmc.common.model.JobPositionEntity;

import java.util.List;

public interface JobPositionRepo {
    List<JobPositionEntity> findAll();
    List<JobPositionEntity> findAllActive();
    void save(JobPositionEntity userEntity);
    void delete(JobPositionEntity userEntity);
    JobPositionEntity findById(Integer id);
}
