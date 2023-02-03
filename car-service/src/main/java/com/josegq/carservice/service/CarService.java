package com.josegq.carservice.service;

import com.josegq.carservice.entity.Car;
import com.josegq.carservice.repository.ICarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    public ICarRepository carRepository;

    public CarService(@Autowired ICarRepository carRepository){
        this.carRepository = carRepository;
    }

    public List<Car> getAll(){
        return carRepository.findAll();
    }

    public Car getCarById(int id){
        if(carRepository.findById(id).isEmpty()){
            return null;
        }
        return carRepository.findById(id).get();
    }

    public Car save(Car car){
        Car carnow = carRepository.save(car);
        return carnow;
    }

    public List<Car> getCarsByUserId(int userId){
        return carRepository.findByUserId(userId);
    }


}
