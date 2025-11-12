package middle.service.demo.Entity;

import lombok.Data;

@Data
public class Bill {
    private int billId;
    private int policyId;
    private String dueDate;
    private double minimumPayment;
    private String createdDate;
    private double balance;
    private String status;
}
