package com.josegq.carservice.repository;

import com.josegq.carservice.document.Car;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICarRepositoryMongo extends MongoRepository<Car,Integer> {

    List<Car> findByUserId(int userId);
}
