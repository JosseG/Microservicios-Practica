package com.josegq.bikeservice.service;

import com.josegq.bikeservice.entity.Bike;
import com.josegq.bikeservice.repository.IBikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {

    public IBikeRepository bikeRepository;

    public BikeService(@Autowired IBikeRepository bikeRepository){
        this.bikeRepository = bikeRepository;
    }

    public List<Bike> getAll(){
        return bikeRepository.findAll();
    }

    public Bike getUserById(int id){
        if(bikeRepository.findById(id).isEmpty()){
            return null;
        }
        return bikeRepository.findById(id).get();
    }

    public Bike save(Bike bike){
        Bike bikenow = bikeRepository.save(bike);
        return bikenow;
    }

    public List<Bike> saveAll(List<Bike> bikes){
        List<Bike> bikesnow = bikeRepository.saveAll(bikes);
        return bikesnow;
    }

    public List<Bike> getBikesByUserId(int userId){
        return bikeRepository.findByUserId(userId);
    }


}
