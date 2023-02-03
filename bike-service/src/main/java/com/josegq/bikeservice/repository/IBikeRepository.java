package com.josegq.bikeservice.repository;

import com.josegq.bikeservice.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBikeRepository extends JpaRepository<Bike,Integer> {

    List<Bike> findByUserId(int userId);

}
