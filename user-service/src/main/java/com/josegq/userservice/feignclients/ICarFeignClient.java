package com.josegq.userservice.feignclients;

import com.josegq.userservice.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "car-service",url = "http://localhost:8002/car")
public interface ICarFeignClient {
    @PostMapping()
    Car save(@RequestBody Car car );

    @GetMapping("/byuser/{userId}")
    List<Car> getCars(@PathVariable("userId") int userId);

}
