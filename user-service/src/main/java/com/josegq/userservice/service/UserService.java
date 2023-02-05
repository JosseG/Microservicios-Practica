package com.josegq.userservice.service;

import com.josegq.userservice.config.RestTemplateConfig;
import com.josegq.userservice.entity.User;
import com.josegq.userservice.feignclients.IBikeFeignClient;
import com.josegq.userservice.feignclients.ICarFeignClient;
import com.josegq.userservice.model.Bike;
import com.josegq.userservice.model.Car;
import com.josegq.userservice.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {

    public IUserRepository userRepository;

    public RestTemplate restTemplate ;

    public ICarFeignClient carFeignClient;

    public IBikeFeignClient bikeFeignClient;

    public UserService(@Autowired IUserRepository userRepository, @Autowired RestTemplate restTemplate, @Autowired ICarFeignClient carFeignClient, @Autowired IBikeFeignClient bikeFeignClient){
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
        this.carFeignClient = carFeignClient;
        this.bikeFeignClient = bikeFeignClient;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getUserById(int id){
        if(userRepository.findById(id).isEmpty()){
            return null;
        }
        return userRepository.findById(id).get();
    }

    public User save(User user){
        User usernow = userRepository.save(user);
        return usernow;
    }

    public List<Car> getCars(int userId){
        List<Car> carList = restTemplate.getForObject("http://car-service/car/byuser/" + userId,List.class);
        return carList;
    }


    public List<Bike> getBikes(int userId){
        List<Bike> bikeList = restTemplate.getForObject("http://bike-service/bike/byuser/" + userId,List.class);
        return bikeList;
    }

    public Car saveCar(int userId, Car car){
        car.setUserId(userId);
        Car carOwned = carFeignClient.save(car);
        return carOwned;
    }

    public Bike saveBike(int userId, Bike bike){
        bike.setUserId(userId);
        Bike bikeOwned = bikeFeignClient.save(bike);
        return bikeOwned;
    }

    public Map<String, Object> getUserAndVehicles(int userId){
        Map<String, Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            result.put("mensaje","No existe el usuario");
            return result;
        }
        result.put("User",user);

        List<Car> carList = carFeignClient.getCars(user.getId());

        if(carList.isEmpty())
            result.put("Cars","No hay lista de carros");
        else
            result.put("Cars",carList);

        List<Bike> bikeList = bikeFeignClient.getBikes(user.getId());

        if(bikeList.isEmpty())
            result.put("Bikes","No hay lista de motos");
        else
            result.put("Bikes",bikeList);


        return result;
    }


}
