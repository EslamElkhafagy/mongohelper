package com.example.mongodb.model;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.mongodb.annotation.DoubleContextualSerializer;
import com.example.mongodb.annotation.Precision;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Document(collection  = "bill_basic_info")
@JsonInclude(JsonInclude.Include.NON_NULL)
@CompoundIndexes({
        @CompoundIndex(name = "account_id_1_bill_id_1", def = "{'account_id' : 1, 'bill_id': 1}"),
        @CompoundIndex(name = "account_id_1_bill_id_1_issue_date_1", def = "{'account_id' : 1, 'bill_id': 1, 'issue_date': 1}"),
        @CompoundIndex(name = "account_id_1_name_1_issue_date_1", def = "{'account_id' : 1, 'name': 1, 'issue_date': 1}"),
        @CompoundIndex(name = "account_id_1_net_amount_1_bill_id_1_issue_date_1", def = "{'account_id' : 1, 'total_due_amount': 1, 'issue_date': 1}"),
        @CompoundIndex(name = "account_id_1_tax_amount_1_bill_id_1_issue_date_1", def = "{'account_id' : 1, 'tax_amount': 1, 'issue_date': 1}"),
        @CompoundIndex(name = "account_id_1_gross_amount_1_bill_id_1_issue_date_1", def = "{'account_id' : 1, 'gross_amount': 1,'bill_id': 1, 'issue_date': 1}")
})
public class BillBasicInfo implements Serializable {

    private static final long serialVersionUID = 1L;


    @MongoId
    @Field( "_id")
    private ObjectId id;

    @Indexed(unique = true)
    @Field( "bill_id")
    private String billId;

    @Indexed
    @Field( "account_id")
    private String accountId;

    @Field( "name")
    private String name;

    @Field("issue_date")
    @Indexed
    private Date issueDate;


    @Field("bill_period_from")
    @JsonFormat(pattern="dd-mm-yyyy")
    private LocalDate billPeriodFrom;

    @Field( "bill_period_to")
    @DateTimeFormat(iso = DateTimeFormat.ISO.NONE, pattern = "dd-mm-yyyy")
    private LocalDate billPeriodTo;

    @Field("total_due_amount")
    @JsonSerialize(using = DoubleContextualSerializer.class)
    @Precision(precision = 2)
    private Double totalDueAmount;

    @Field( "net_amount")
    @JsonSerialize(using = DoubleContextualSerializer.class)
    @Precision(precision = 2)
    private Double netAmount;

    @Field( "tax_amount")
    @JsonSerialize(using = DoubleContextualSerializer.class)
    @Precision(precision = 2)
    private Double taxAmount;

    @Field( "gross_amount")
    @JsonSerialize(using = DoubleContextualSerializer.class)
    @Precision(precision = 2)
    private Double grossAmount;

    @Field( "created_at")
    private Date createdAt;

    public void setIssueDate(LocalDate issueDate) {
        Instant instant = issueDate.atStartOfDay().atZone(ZoneId.of("UTC")).toInstant();
        Date date = Date.from(instant);
        this.issueDate = date;
    }
}
