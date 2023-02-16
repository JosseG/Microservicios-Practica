package com.josegq.carservice.controller;

import com.josegq.carservice.document.Car;
import com.josegq.carservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    CarService carService;

    public CarController(@Autowired CarService carService){
        this.carService = carService;
    }

    @GetMapping()
    public ResponseEntity<List<Car>> getAll(){
        List<Car> carList = carService.getAll();
        if(carList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(carList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getById(@PathVariable("id") String id ){
        Car car = carService.getCarById(id);
        if(car == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(car);
    }

    @PostMapping()
    public ResponseEntity<Car> save(@RequestBody Car car ){
        Car carSaved = carService.save(car);

        return ResponseEntity.ok(carSaved);
    }

    @PostMapping("/saveall")
    public ResponseEntity<List<Car>> saveAll(@RequestBody List<Car> cars ){
        List<Car> carsSaved = carService.saveAll(cars);

        return ResponseEntity.ok(carsSaved);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Car>> getAllByUserId(@PathVariable("userId") int userId){
        List<Car> carList = carService.getCarsByUserId(userId);

        return ResponseEntity.ok(carList);
    }


}
