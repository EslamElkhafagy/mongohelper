package com.example.mongodb.model;

import java.io.Serializable;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class Charge implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Field("seq_no")
    private Integer seqNo;

    @Field("charge_description")
    private String chargeDescription;

    @Field( "quantity")
    private Long quantity;

    @Field( "net_amount")
    private Double netAmount;

    @Field( "total_net_amount")
    private Double totalNetAmount;

    @Field("tax_rate")
    private Double taxRate;

    @Field( "tax_amount")
    private Double taxAmount;

    @Field( "gross_amount")
    private Double grossAmount;
}
