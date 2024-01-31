package com.example.mongodb.controller;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.mongodb.model.enums.SortField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mongodb.model.BillBasicInfo;
import com.example.mongodb.repository.BillBasicInfoRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/overview")
@Validated
@RequiredArgsConstructor
public class OverviewController {

    private final BillBasicInfoRepository billBasicInfoRepository;
    private final MongoTemplate mongoTemplate;


    @GetMapping(value = "/manyField/sortAllBillsWithProjectionAndFilterByAccountId", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sortBillsWithProjectionAndFilterByAccount_Id(
            @RequestParam(required = true) String startMonth,
            @RequestParam(required = false) String endMonth,
            @RequestParam(required = false) String accountId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "1") int size,
            @RequestParam(defaultValue = "billId") String sortFields,
            @RequestParam(defaultValue = "DESC") String sortDirections) {

        List<BillBasicInfo> bills= new ArrayList<>();
        List<Sort.Order> orders = new ArrayList<Sort.Order>();

        String[] fields=sortFields.split(",");
        String[] directions=sortDirections.split(",");

        if(fields.length != directions.length){
            return new ResponseEntity<>(
                    "btal le3b y baba",
                    HttpStatus.NOT_FOUND
            );
        }
        for(int i=0;i<fields.length;i++){
            orders.add(new Sort.Order(Sort.Direction.valueOf(directions[i]), fields[i]));
        }
        Sort sorts= Sort.by(orders);
        Pageable paging = PageRequest.of(page, size,sorts);

        Page<BillBasicInfo> pageBills;

        if (accountId == null)
            pageBills = billBasicInfoRepository.findAllInvoicesByMonths(startMonth,endMonth,paging);
        else
            pageBills = billBasicInfoRepository.findAllInvoicesByMonthsWithAccountId(startMonth,endMonth,accountId,paging);

        bills = pageBills.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("bills", bills);
        response.put("currentPage", pageBills.getNumber());
        response.put("totalItems", pageBills.getTotalElements());
        response.put("totalPages", pageBills.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping(value = "/oneField/sortAllBillsWithProjectionAndFilterByAccountId", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sortAllBillsWithProjectionAndFilterByAccount_Id(
            @RequestParam(required = true) String startMonth,
            @RequestParam(required = false) String endMonth,
            @RequestParam(required = false) String accountId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "1") int size,
            @RequestParam(defaultValue = "billId") SortField sortField,
            @RequestParam(defaultValue = "DESC") Sort.Direction sortDirection) {

        List<BillBasicInfo> bills= new ArrayList<>();
        Sort orders= Sort.by(sortDirection, sortField.getDatabaseFieldName());
        Pageable paging = PageRequest.of(page, size,orders);

        Page<BillBasicInfo> pageBills;

        if (accountId == null)
            pageBills = billBasicInfoRepository.findAllInvoicesByMonths(startMonth,endMonth,paging);
        else
            pageBills = billBasicInfoRepository.findAllInvoicesByMonthsWithAccountId(startMonth,endMonth,accountId,paging);

        bills = pageBills.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("bills", bills);
        response.put("currentPage", pageBills.getNumber());
        response.put("totalItems", pageBills.getTotalElements());
        response.put("totalPages", pageBills.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping(value = "/monthly-statistics", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getStatistics(
            @RequestParam(name = "start-month", required = true) YearMonth startMonth,
            @RequestParam(name = "end-month", required = false) YearMonth endMonth,
            @RequestParam List<String> accountIds) throws ParseException {

        LocalDate startLocal=startMonth.atDay(1);
        Instant instant = startLocal.atStartOfDay().atZone(ZoneId.of("UTC")).toInstant();
        Date startDate = Date.from(instant);

        LocalDate endLocal=endMonth.atEndOfMonth();
        Instant endInstant = endLocal.atStartOfDay().atZone(ZoneId.of("UTC")).toInstant();
        Date endDate = Date.from(endInstant);

        return ResponseEntity.ok().body(billBasicInfoRepository.findByAccountIdAndIssueDateBetween(accountIds,startDate,endDate));
    }
//    public List<MonthStatistics> getMonthlyStatistics() {
//       return mongoTemplate.aggregate(Aggregation.newAggregation(Aggregation.match(Criteria.where("accountId").in(Collections.singletonList("12345")).and("issueDate")
//                                .gte( Date.from(LocalDate.of(2020,1,1).atStartOfDay().atZone(ZoneId.of("UTC")).toInstant()))
//                               .lte(Date.from(LocalDate.of(2025,1,1).atStartOfDay().atZone(ZoneId.of("UTC")).toInstant()))),
//                        Aggregation.project().andExpression("dayOfMonth(issueDate)").as("day")
//                                .andExpression("month(issueDate)").as("month")
//                                .andExpression("year(issueDate)").as("year").and("netAmount").as("netAmount"),
//
//                        Aggregation.group("month", "year").addToSet("day").as("days").push("netAmount").as("netAmounts").sum("netAmount")
//                                .as("totalNetAmount"),
//
//                        Aggregation.sort(Sort.by(Sort.Order.desc("year"), Sort.Order.desc("month")))), "bill_basic_info",
//                MonthStatistics.class).getMappedResults();
//    }



}
