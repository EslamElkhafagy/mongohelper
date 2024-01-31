package com.example.mongodb.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortField {

    BILL_ID("billId"),
    ISSUE_DATE("issueDate"),
    ACCOUNT_ID("accountId");

    private final String databaseFieldName;

}
