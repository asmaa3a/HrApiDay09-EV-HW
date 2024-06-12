package org.example.mappers;


import org.example.dto.jobsdto1;
import org.example.models.jobs;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface jobMapper {

    jobMapper INSTANCE = Mappers.getMapper(jobMapper.class);

    jobsdto1 tojobDto(jobs d);

    jobs toModel(jobsdto1 dto);


}
