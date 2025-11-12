package middle.service.demo.Entity;

import lombok.Data;

@Data
public class Driver {
    private int driverId;
    private int policyId;
    private String title;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String emailAddress;
    private String phoneNumber;
    private String ssn;
    private String licenseIssuedDate;

    private String licenseIssuedState;
    private String licenseNumber;
    private boolean asPrimaryPolicyHolder;
    private String primaryPolicyHolderRelation;
    private String gender;
    private String maritalStatus;
    private String createdDate;
    private boolean active;
}
