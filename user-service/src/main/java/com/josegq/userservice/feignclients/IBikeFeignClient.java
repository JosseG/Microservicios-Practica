package com.josegq.userservice.feignclients;

import com.josegq.userservice.model.Bike;
import com.josegq.userservice.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "bike-service",url = "http://localhost:8003/bike")

public interface IBikeFeignClient {

    @PostMapping()
    Bike save(@RequestBody Bike bike );

    @GetMapping("/byuser/{userId}")
    List<Bike> getBikes(@PathVariable("userId") int userId);

}
