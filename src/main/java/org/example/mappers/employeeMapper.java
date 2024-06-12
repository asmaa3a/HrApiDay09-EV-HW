package org.example.mappers;


import org.example.dto.employeeDto;
import org.example.models.employee;
import org.example.models.jobs;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {jobMapper.class}, imports = {java.util.UUID.class})
public interface employeeMapper {

    employeeMapper INSTANCE = Mappers.getMapper(employeeMapper.class);


    employeeDto toDto(employee e);

    employee toModel(employeeDto dto);
}

