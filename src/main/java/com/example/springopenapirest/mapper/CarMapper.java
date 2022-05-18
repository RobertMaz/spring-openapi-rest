package com.example.springopenapirest.mapper;

import com.example.springopenapirest.entity.Car;
import com.example.web.dto.CarResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = CarMapper.class)
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    List<CarResponse> toDto(List<Car> cars);
    CarResponse toDto(Car cars);
}
