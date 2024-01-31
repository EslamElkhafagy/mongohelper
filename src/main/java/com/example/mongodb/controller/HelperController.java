package com.example.mongodb.controller;


import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mongodb.model.BillBasicInfo;
import com.example.mongodb.repository.BillBasicInfoRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/helper")
@Validated
@RequiredArgsConstructor
public class HelperController {

    private final BillBasicInfoRepository billBasicInfoRepository;


    @PostMapping(value = "/generateBills", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveBill( @RequestParam String billId,@RequestParam String accountIdList,@RequestParam Integer numberOfDocuments) {

        String[] accountIds=accountIdList.split(",");
        DateFormat isoFormat = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss'Z'");
        isoFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Map<String,Integer> accountMap= new HashMap<>();
        int counter=1;
        for(String account:accountIds){
            for(int i=1;i<=numberOfDocuments;i++) {
                BillBasicInfo temp= new BillBasicInfo();
                double nextAmount=Double.parseDouble(new DecimalFormat("###.##").format(Math.random()*1000));
                double taxAmount=Double.parseDouble(new DecimalFormat("###.##").format(nextAmount*0.19));
                double grossAmount=Double.parseDouble(new DecimalFormat("###.##").format(nextAmount+taxAmount));

                temp.setBillId(billId + counter);
                temp.setAccountId(account);
                temp.setName(getDummyName().get((int)(Math.random()*10)%getDummyName().size())+" "+getDummyName().get((int)(Math.random()*10)%getDummyName().size()));
                temp.setIssueDate(LocalDate.now().minusMonths(i).withDayOfMonth((int)(Math.random() * 25 + 1)));
                temp.setNetAmount(nextAmount);
                temp.setTaxAmount(taxAmount);
                temp.setGrossAmount(grossAmount);
                temp.setCreatedAt(new Date());

                billBasicInfoRepository.save(temp);
                counter++;

            }
        }

        return ResponseEntity.ok().body(accountMap);
    }


    @GetMapping(value = "/bills", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BillBasicInfo>> getALlBills() {
        return ResponseEntity.ok().body(billBasicInfoRepository.findAll());
    }

    @GetMapping(value = "/sliceNames", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sliceBills( @RequestParam Integer start,@RequestParam Integer end) {

        return  ResponseEntity.ok().body(billBasicInfoRepository.findAlluserNames(start,end));
        
    }

    private List<String> getDummyName(){
        List<String> dummyNames=new ArrayList<>();
        dummyNames.add("Matthias");
        dummyNames.add("Peter");
        dummyNames.add("Markus");
        dummyNames.add("Sebastian");
        dummyNames.add("Frederik");
        dummyNames.add("David");
        dummyNames.add("Patrick");
        dummyNames.add("Jürgen");
        dummyNames.add("Alexander");
        dummyNames.add("Leon");
        dummyNames.add("Köhler");
        dummyNames.add("Schubert");
        dummyNames.add("Meyer");
        dummyNames.add("Böhm");
        dummyNames.add("Schmitt");
        dummyNames.add("Lang");
        dummyNames.add("Vog");
        dummyNames.add("Zimmermann");
        dummyNames.add("Engel");
        dummyNames.add("Maier");

        return dummyNames;
    }
}
