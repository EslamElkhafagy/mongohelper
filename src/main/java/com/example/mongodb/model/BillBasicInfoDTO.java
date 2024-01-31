package com.example.mongodb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Data
public class BillBasicInfoDTO {


        private Long billId;


        private String accountId;

        private String companyName;

        private String salutation;

        private String title;

        private String firstName;

        private String lastName;

        private String issueDate;

        private String dueDate;

        private String billPeriodFrom;

        private String billPeriodTo;

        private String currency;

        private String country;

        private String timeZone;

        private String paymentText;

        private String paymentMethod;

        private Double balanceForwardDueAmount;

        private Double totalDueAmount;

        private Double netAmount;

        private Double taxAmount;

        private Double grossAmount;

        private String chargeFlag;

        private String pdfFlag;

        private String notifyEmail;

        private String notifySmsMsisdn;

        private String notifyCategory;

        private String banMediaCategory;


        private List<ChargeGroup> chargeGroups;

        private Date createdAt;

        private Long ttl;


}
