package com.example.mongodb.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;

@Data
public class ChargeGroup implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Field( "charge_type")
    private String chargeType;

    @Field("net_amount")
    private Double netAmount;

    @Field( "gross_amount")
    private Double grossAmount;

    private List<Charge> charges;
}
