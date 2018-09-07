package com.kat.dmc.service.impl;

import com.kat.dmc.common.dto.JobPositionDto;
import com.kat.dmc.common.model.JobPositionEntity;
import com.kat.dmc.repository.interfaces.JobPositionRepo;
import com.kat.dmc.service.interfaces.JobPositionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class JobPositionServiceImpl implements JobPositionService {

    @Autowired
    JobPositionRepo jobPositionRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<JobPositionDto> findAll() {
        return jobPositionRepo.findAll().stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public List<JobPositionDto> findAllActive() {
        return jobPositionRepo.findAllActive().stream().map(this::entity2Dto).collect(Collectors.toList());
    }

    @Override
    public void save(JobPositionDto userDto) {
        JobPositionEntity savingObj = modelMapper.map(userDto, JobPositionEntity.class);
        jobPositionRepo.save(savingObj);
    }

    @Override
    public void delete(Integer id) {
        jobPositionRepo.delete(jobPositionRepo.findById(id));
    }

    private JobPositionDto entity2Dto(JobPositionEntity entity){
        return modelMapper.map(entity, JobPositionDto.class);
    }
}
