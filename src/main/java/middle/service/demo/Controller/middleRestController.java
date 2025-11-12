package middle.service.demo.Controller;

import jakarta.annotation.Resource;
import middle.service.demo.Result;
import middle.service.demo.Services.middleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class middleRestController {

    @Resource
    private middleService middleService;

    @GetMapping("/PolicyData")
    public Result policyData(){
        return middleService.getRestData("policy");
    }

    @GetMapping("/DriverData")
    public Result driverData(){
        return middleService.getRestData("driver");
    }

    @GetMapping("/VehicleData")
    public Result vehicleData(){
        return middleService.getRestData("vehicle");
    }

    @GetMapping("/BillData")
    public Result billData(){
        return middleService.getRestData("bill");
    }
}
