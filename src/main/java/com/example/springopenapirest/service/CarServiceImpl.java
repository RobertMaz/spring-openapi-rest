package com.example.springopenapirest.service;

import com.example.springopenapirest.entity.Car;
import com.example.springopenapirest.repository.CarRepository;
import com.example.web.dto.CarFindRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Override
    public List<Car> findCars(CarFindRequest request) {
        String model = request.getModel();
        return carRepository.findByModel(model);
    }
}
