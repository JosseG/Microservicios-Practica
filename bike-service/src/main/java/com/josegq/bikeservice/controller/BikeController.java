package com.josegq.bikeservice.controller;

import com.josegq.bikeservice.entity.Bike;
import com.josegq.bikeservice.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeController {

    BikeService bikeService;

    public BikeController(@Autowired BikeService bikeService){
        this.bikeService = bikeService;
    }

    @GetMapping()
    public ResponseEntity<List<Bike>> getAll(){
        List<Bike> bikeList = bikeService.getAll();
        if(bikeList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(bikeList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getById(@PathVariable("id") int id ){
        Bike bike = bikeService.getUserById(id);
        if(bike == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(bike);
    }

    @PostMapping()
    public ResponseEntity<Bike> save(@RequestBody Bike bike ){
        Bike bikeSaved = bikeService.save(bike);

        return ResponseEntity.ok(bikeSaved);
    }

    @PostMapping("/saveall")
    public ResponseEntity<List<Bike>> saveAll(@RequestBody List<Bike> bikes ){
        List<Bike> bikesSaved = bikeService.saveAll(bikes);

        return ResponseEntity.ok(bikesSaved);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Bike>> getAllByUserId(@PathVariable("userId") int userId){
        List<Bike> bikeList = bikeService.getBikesByUserId(userId);


        return ResponseEntity.ok(bikeList);
    }

}
