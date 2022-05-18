package com.example.springopenapirest.web;

import com.example.springopenapirest.entity.Car;
import com.example.springopenapirest.mapper.CarMapper;
import com.example.springopenapirest.service.CarService;
import com.example.web.controller.CarApi;
import com.example.web.dto.CarFindRequest;
import com.example.web.dto.CarResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CarController implements CarApi {
    private final CarService carService;

    @Override
    public ResponseEntity<List<CarResponse>> findCars(CarFindRequest request) {
        List<Car> cars = carService.findCars(request);
        List<CarResponse> carDtos = CarMapper.INSTANCE.toDto(cars);
        return ResponseEntity.ok(carDtos);
    }
}
