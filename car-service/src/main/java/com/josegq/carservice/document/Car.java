package com.josegq.carservice.document;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "car")
public class Car {

    @Id
    private String carId;

    private String brand;

    private String model;

    private int userId;


}
