package middle.service.demo.Services;

import middle.service.demo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class middleService {

    @Autowired
    private WebClient webClient;

    public Result getRestData(String pathName){
        Result rsData = webClient.get()
                .uri("/" + pathName)
                .retrieve()
                .bodyToMono(Result.class)
                .block();

        return Result.success(rsData.getData());
    }

    public String getSummaryData(){
        return webClient.get()
                .uri("/summaryAPI")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String getActiveVehicleData(){
        return webClient.get()
                .uri("/activeVehicle")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String getUnpaidVehicleData(){
        return webClient.get()
                .uri("/unpaidDriver")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
