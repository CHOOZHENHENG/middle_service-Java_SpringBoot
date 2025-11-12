package middle.service.demo.Entity;

import lombok.Data;

@Data
public class Vehicle {
    private int vehicleId;
    private int policyId;
    private int year;
    private String make;
    private String model;
    private String color;
    private int mileage;
    private String vinNumber;
    private String vehicleNumberPlate;
    private String vehicleRegisteredState;

    private String createdDate;
    private boolean active;
}
