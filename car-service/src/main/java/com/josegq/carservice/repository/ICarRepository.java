package com.josegq.carservice.repository;

import com.josegq.carservice.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICarRepository extends JpaRepository<Car,Integer> {

    List<Car> findByUserId(int userId);

}
