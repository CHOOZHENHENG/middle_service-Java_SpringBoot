package middle.service.demo.Controller;

import middle.service.demo.Services.middleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class middleController {

    @Resource
    private middleService middleService;

    @GetMapping(value = "/SummaryAPI")
    @ResponseBody
    public String summaryData(){
        return middleService.getSummaryData();
    }

    @GetMapping("/ActiveVehicle")
    @ResponseBody
    public String activeVehicleData(){
        return middleService.getActiveVehicleData();
    }

    @GetMapping("/UnpaidVehicle")
    @ResponseBody
    public String unpaidVehicleData(){
        return middleService.getUnpaidVehicleData();
    }
}
