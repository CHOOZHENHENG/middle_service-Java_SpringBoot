package middle.service.demo.Entity;

import lombok.Data;

@Data
public class Policy {
    private int policyID;
    private String policyNumber;
    private String policyEffectiveDate;
    private String policyExpireDate;
    private String paymentOption;
    private double totalAmount;
    private boolean active;
    private String additionalInfo;
    private String createdDate;
}
