package com.example.springopenapirest.service;

import com.example.springopenapirest.entity.Car;
import com.example.web.dto.CarFindRequest;

import java.util.List;

public interface CarService {
    List<Car> findCars(CarFindRequest request);
}
