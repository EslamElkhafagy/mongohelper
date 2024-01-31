package com.example.mongodb.model;


import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.util.List;

@Data
public class HelperDTO {

    private Long billId;
    private List<AccountId> accountIds;
    private String firstName;
    private String billPeriodFrom;
    private String billPeriodTo;

}
