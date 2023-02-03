package com.josegq.userservice.controller;

import com.josegq.userservice.entity.User;
import com.josegq.userservice.model.Bike;
import com.josegq.userservice.model.Car;
import com.josegq.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    public UserController(@Autowired UserService userService){
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAll(){
        List<User> userList = userService.getAll();
        if(userList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id ){
        User user = userService.getUserById(id);
        if(user == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(user);
    }

    @PostMapping()
    public ResponseEntity<User> save(@RequestBody User user ){
        User userSaved = userService.save(user);

        return ResponseEntity.ok(userSaved);
    }

    @GetMapping("/cars/{userid}")
    public ResponseEntity<List<Car>> getCars(@PathVariable("userid") int userId){
        User userOwned = userService.getUserById(userId);
        if(userOwned == null)
            return ResponseEntity.notFound().build();

        List<Car> carList = userService.getCars(userOwned.getId());

        return ResponseEntity.ok(carList);
    }


    @GetMapping("/bikes/{userid}")
    public ResponseEntity<List<Bike>> getBikes(@PathVariable("userid") int userId){
        User userOwned = userService.getUserById(userId);
        if(userOwned == null)
            return ResponseEntity.notFound().build();

        List<Bike> bikeList = userService.getBikes(userOwned.getId());

        return ResponseEntity.ok(bikeList);
    }

    @PostMapping("/savecar/{userId}")
    public ResponseEntity<Car> saveCar(@PathVariable("userId") int userId, @RequestBody Car car){
        Car carNew = userService.saveCar(userId,car);
        return ResponseEntity.ok(carNew);
    }


    @PostMapping("/savebike/{userId}")
    public ResponseEntity<Bike> saveBike(@PathVariable("userId") int userId, @RequestBody Bike bike){
        Bike bikeNew = userService.saveBike(userId,bike);
        return ResponseEntity.ok(bikeNew);
    }

    @GetMapping("/getall/{userId}")
    public ResponseEntity<Map<String, Object>> getAllVehicles(@PathVariable("userId") int userId){
        Map<String, Object> result = userService.getUserAndVehicles(userId);
        return  ResponseEntity.ok(result);
    }


}
