package com.josegq.carservice.service;

import com.josegq.carservice.document.Car;
import com.josegq.carservice.repository.ICarRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    public ICarRepositoryMongo carRepository;

    public CarService(@Autowired ICarRepositoryMongo carRepository){
        this.carRepository = carRepository;
    }

    public List<Car> getAll(){
        return carRepository.findAll();
    }

    public Car getCarById(String id){
        if(carRepository.findById(id).isEmpty()){
            return null;
        }
        return carRepository.findById(id).get();
    }

    public Car save(Car car){
        Car carnow = carRepository.save(car);
        return carnow;
    }


    public List<Car> saveAll(List<Car> cars){
        List<Car> carsnow = carRepository.saveAll(cars);
        return carsnow;
    }

    public List<Car> getCarsByUserId(int userId){
        return carRepository.findByUserId(userId);
    }


}
