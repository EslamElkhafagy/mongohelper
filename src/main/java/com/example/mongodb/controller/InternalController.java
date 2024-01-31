//package com.example.mongodb.controller;
//
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import com.example.mongodb.model.BillBasicInfoDTO;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.repository.query.Param;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.mongodb.model.BillBasicInfo;
//import com.example.mongodb.model.RequestDTO;
//import com.example.mongodb.model.enums.SortField;
//import com.example.mongodb.repository.BillBasicInfoRepository;
//
//import lombok.RequiredArgsConstructor;
//
//@RestController
//@RequestMapping("/internal")
//@Validated
//@RequiredArgsConstructor
//public class InternalController {
//
//    private final BillBasicInfoRepository billBasicInfoRepository;
//
//
//    @PostMapping(value = "/generateBills", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> saveBill(@RequestParam List<String> accountList,@RequestParam List<Long> billIdList,@RequestParam Integer documentNumbers,@RequestBody BillBasicInfoDTO billBasicInfo) {
//
//        Map<String,Integer> accountMap= new HashMap<>();
//        for(int i=1;i<=documentNumbers.intValue();i++){
//            BillBasicInfo temp= new BillBasicInfo();
//            int randomIndexForBillId=(int)(Math.random()*10)%billIdList.size();
//            int randomIndexForAccountId=(int)(Math.random()*10)%accountList.size();
//
//            temp.setBillId(billIdList.get(randomIndexForBillId)+i);
//            temp.setAccountId(accountList.get(randomIndexForAccountId));
////            temp.setLastName(billBasicInfo.getLastName());
//            temp.setIssueDate(LocalDate.parse(billBasicInfo.getIssueDate(),DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//            temp.setDueDate(LocalDate.parse(billBasicInfo.getDueDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//            temp.setBillPeriodFrom(LocalDate.parse(billBasicInfo.getBillPeriodFrom(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//            temp.setBillPeriodTo(LocalDate.parse(billBasicInfo.getBillPeriodTo(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//            temp.setCurrency(billBasicInfo.getCurrency());
//            temp.setCountry(billBasicInfo.getCountry());
//            temp.setTimeZone(billBasicInfo.getTimeZone());
//            temp.setPaymentText(billBasicInfo.getPaymentText());
//            temp.setPaymentMethod(billBasicInfo.getPaymentMethod());
//            temp.setBalanceForwardDueAmount(billBasicInfo.getBalanceForwardDueAmount());
//            temp.setTotalDueAmount(billBasicInfo.getTotalDueAmount());
//            temp.setNetAmount(billBasicInfo.getNetAmount());
//            temp.setTaxAmount(billBasicInfo.getTaxAmount());
//            temp.setGrossAmount(billBasicInfo.getGrossAmount());
//            temp.setChargeFlag(billBasicInfo.getChargeFlag());
//            temp.setPdfFlag(billBasicInfo.getPdfFlag());
//            temp.setNotifyEmail(billBasicInfo.getNotifyEmail());
//            temp.setNotifyCategory(billBasicInfo.getNotifyCategory());
//            temp.setNotifySmsMsisdn(billBasicInfo.getNotifySmsMsisdn());
//            temp.setChargeGroups(billBasicInfo.getChargeGroups());
//            temp.setTtl(billBasicInfo.getTtl());
////            temp.setCreatedAt(new Date());
//
//            accountMap.put(accountList.get(randomIndexForAccountId),accountMap.getOrDefault(accountList.get(randomIndexForAccountId),0)+1);
//
//            billBasicInfoRepository.save(temp);
//        }
//
//        return ResponseEntity.ok().body(accountMap);
//    }
//
//    @PostMapping(value = "/bills", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<BillBasicInfo> saveBill(@Validated @RequestBody BillBasicInfo billBasicInfo) {
//        return ResponseEntity.ok().body(billBasicInfoRepository.save(billBasicInfo));
//    }
//
//    @GetMapping(value = "/bills", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<BillBasicInfo>> getALlBills() {
//        return ResponseEntity.ok().body(billBasicInfoRepository.findAll());
//    }
//
//    @GetMapping(value = "/bills/{billId}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<BillBasicInfo> getBill(@PathVariable Long billId) {
//        return ResponseEntity.ok().body(billBasicInfoRepository.findByBillId(billId));
//    }
//
//    @DeleteMapping(value = "/bills/{billId}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<BillBasicInfo> deleteBill(@PathVariable Long billId) {
//        return ResponseEntity.ok().body(billBasicInfoRepository.deleteByBillId(billId));
//    }
//
//    @DeleteMapping(value = "/bills", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<BillBasicInfo> deleteBillById(@Param("id") String id) {
//        return ResponseEntity.ok().body(billBasicInfoRepository.deleteById(id).get());
//    }
//
//    @PutMapping(value = "/bills", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> updateBillById(@Param("id") String id,@RequestBody BillBasicInfo billBasicInfo) {
//        Optional existBill=billBasicInfoRepository.findById(id);
//
//        if(existBill.isPresent()){
//            BillBasicInfo  basicInfo= (BillBasicInfo) existBill.get();
//            basicInfo.setCountry(billBasicInfo.getCountry());
//            return ResponseEntity.ok().body(billBasicInfoRepository.save(basicInfo));
//        }
//        return new ResponseEntity<>(
//                "bill not found",
//                HttpStatus.NOT_FOUND
//        );    }
///////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
//
//    @GetMapping(value = "/billsByCCB/{ccb}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<BillBasicInfo>> getBillByCcbs(@PathVariable String ccb) {
//        return ResponseEntity.ok().body(billBasicInfoRepository.findByCcb(ccb));
//    }
//
//    @GetMapping(value = "/billsByCCBList", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<BillBasicInfo>> getBillByCcbsList(@RequestBody RequestDTO accountIds) {
//        return ResponseEntity.ok().body(billBasicInfoRepository.findByCcb(accountIds.getAccountIds()));
//    }
//
//    @GetMapping(value = "/billsByAccountIdWithPagination", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> getBillBybillId(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "3") int size,@RequestBody RequestDTO accountIds) {
//
//        try {
//
//            List<BillBasicInfo> bills= new ArrayList<>();
//            Pageable paging = PageRequest.of(page, size);
//            Page<BillBasicInfo> pageBills;
//
//            if (accountIds == null)
//                pageBills = billBasicInfoRepository.findAll(paging);
//            else
//                pageBills = billBasicInfoRepository.findByAccountIdIn(accountIds.getAccountIds(), paging);
//
//            bills = pageBills.getContent();
//
//            Map<String, Object> response = new HashMap<>();
//            response.put("bills", bills);
//            response.put("currentPage", pageBills.getNumber());
//            response.put("totalItems", pageBills.getTotalElements());
//            response.put("totalPages", pageBills.getTotalPages());
//
//            return new ResponseEntity<>(response, HttpStatus.OK);
//
//        }catch (Exception ex){
//            return new ResponseEntity<>(
//                    "bill not found",
//                    HttpStatus.NOT_FOUND
//            );
//        }
//        }
//
//    @GetMapping(value = "/sortAllBillsWithProjectionAndFilterByAccountIdLikeOverViewPage", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> sortAllBillsWithProjectionAndFilterByAccountId(
//            @RequestParam(required = false) String accountId,
//            @RequestParam(defaultValue = "1") int page,
//            @RequestParam(defaultValue = "1") int size,
//            @RequestParam(defaultValue = "billId") SortField sortField,
//            @RequestParam(defaultValue = "DESC") Sort.Direction sortDirection) {
//
//        List<BillBasicInfo> bills= new ArrayList<>();
//        Sort orders= Sort.by(sortDirection, sortField.getDatabaseFieldName());
//        Pageable paging = PageRequest.of(page, size,orders);
//
//        Page<BillBasicInfo> pageBills;
//
//        if (accountId == null)
//            pageBills = billBasicInfoRepository.findAll(paging);
//        else
//            pageBills = billBasicInfoRepository.findAllByAccountIdLike(accountId,paging);
//
//        bills = pageBills.getContent();
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("bills", bills);
//        response.put("currentPage", pageBills.getNumber());
//        response.put("totalItems", pageBills.getTotalElements());
//        response.put("totalPages", pageBills.getTotalPages());
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/sortAllBillsWithProjectionAndFilterByAccountId", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> sortAllBillsWithProjectionAndFilterByAccount_Id(
//            @RequestParam(required = false) String accountId,
//            @RequestParam(defaultValue = "1") int page,
//            @RequestParam(defaultValue = "1") int size,
//            @RequestParam(defaultValue = "billId") SortField sortField,
//            @RequestParam(defaultValue = "DESC") Sort.Direction sortDirection) {
//
//        List<BillBasicInfo> bills= new ArrayList<>();
//        Sort orders= Sort.by(sortDirection, sortField.getDatabaseFieldName());
//        Pageable paging = PageRequest.of(page, size,orders);
//
//        Page<BillBasicInfo> pageBills;
//
//        if (accountId == null)
//            pageBills = billBasicInfoRepository.findAll(paging);
//        else
//            pageBills = billBasicInfoRepository.findByAccountId(accountId,paging);
//
//        bills = pageBills.getContent();
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("bills", bills);
//        response.put("currentPage", pageBills.getNumber());
//        response.put("totalItems", pageBills.getTotalElements());
//        response.put("totalPages", pageBills.getTotalPages());
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/sortBillsByIssueDate", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<BillBasicInfo>> sortBillsByIssueDate(@RequestParam Long issueDate) {
//
//        Sort orders= Sort.by(Sort.Direction.ASC, "issueDate");
//        return ResponseEntity.ok().body(billBasicInfoRepository.findByIssueDate(issueDate,orders));
//    }
//
//    @GetMapping(value = "/sortAllBills", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<BillBasicInfo>> sortAllBillsWithOneField(@RequestParam(defaultValue = "billId") SortField sortField,
//                                                                       @RequestParam(defaultValue = "DESC") Sort.Direction sortDirection) {
//
//        Sort orders= Sort.by(sortDirection, sortField.getDatabaseFieldName());
//        return ResponseEntity.ok().body(billBasicInfoRepository.findAll(orders));
//    }
//
//
//    @GetMapping(value = "/sortAllBillsWithManyObject", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> sortAllBillsWithManyFields(@RequestParam(defaultValue = "billId") String sortFields,
//                                                                       @RequestParam(defaultValue = "DESC") String sortDirections) {
//
//        List<Sort.Order> orders = new ArrayList<Sort.Order>();
//
//        String[] fields=sortFields.split(",");
//        String[] directions=sortDirections.split(",");
//
//        if(fields.length != directions.length){
//            return new ResponseEntity<>(
//                    "btal le3b y baba",
//                    HttpStatus.NOT_FOUND
//            );
//        }
//        for(int i=0;i<fields.length;i++){
//            orders.add(new Sort.Order(Sort.Direction.valueOf(directions[i]), fields[i]));
//        }
//
//        return ResponseEntity.ok().body(billBasicInfoRepository.findAll(Sort.by(orders)));
//    }
//
//
//    ///////////////////////////////////////////////////////////////////////////
//    //////////////////////////////////////////////////////////////////////////
//
//    @GetMapping(value = "/accountIdLike/{accountId}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> searchBill(@PathVariable String accountId) {
//        return ResponseEntity.ok().body(billBasicInfoRepository.findByAccountIdContainingIgnoreCase(accountId));
//    }
//
//    @GetMapping(value = "/accountIdNotLike/{accountId}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> searchBillNotLike(@PathVariable String accountId) {
//        return ResponseEntity.ok().body(billBasicInfoRepository.findByAccountIdNotLike(accountId));
//    }
//
//    @GetMapping(value = "/findByNetAmountLessThan/{netAmount}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> findByNetAmountLessThan(@PathVariable Double netAmount) {
//        return ResponseEntity.ok().body(billBasicInfoRepository.findByNetAmountLessThan(netAmount));
//    }
//
//    @GetMapping(value = "/findByNetAmountLessThanEqual/{netAmount}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> findByNetAmountLessThanEqual(@PathVariable Double netAmount) {
//        return ResponseEntity.ok().body(billBasicInfoRepository.findByNetAmountLessThanEqual(netAmount));
//    }
//
//    @GetMapping(value = "/findByNetAmountNotLike/{netAmount}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> findByNetAmountNotLike(@PathVariable Double netAmount) {
//        return ResponseEntity.ok().body(billBasicInfoRepository.findByNetAmountNotLike(netAmount));
//    }
//
//    @GetMapping(value = "/findByIssueDateAfter/{issueDate}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> findByNetAmountNotLike(@PathVariable Long issueDate) {
//        return ResponseEntity.ok().body(billBasicInfoRepository.findByIssueDateAfter(issueDate));
//    }
//    @GetMapping(value = "/findByIssueDateBefore/{issueDate}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> findByIssueDateBefore(@PathVariable Long issueDate) {
//        return ResponseEntity.ok().body(billBasicInfoRepository.findByIssueDateBefore(issueDate));
//    }
//    @GetMapping(value = "/findByIssueDateEquals/{issueDate}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> findByIssueDateEquals(@PathVariable Long issueDate) {
//        return ResponseEntity.ok().body(billBasicInfoRepository.findByIssueDateEquals(issueDate));
//    }
//
//    @GetMapping(value = "/findAllExcludeId", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> findAllExcludeId() {
//        return ResponseEntity.ok().body(billBasicInfoRepository.findAllExcludeId());
//    }
//
//
//
//
//
//
//    @GetMapping(value = "/billsByAccountIdList", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<BillBasicInfo>> getBillByAccountIdsList(@RequestBody RequestDTO accountIds) {
//        return ResponseEntity.ok().body(billBasicInfoRepository.findByAccountIdIn(accountIds.getAccountIds()));
//    }
//
//    @GetMapping(value = "/billsByAccountIdListWithQuery", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<BillBasicInfo>> getBillByAccountIdsListWithQuery(@RequestBody RequestDTO accountIds) {
//        return ResponseEntity.ok().body(billBasicInfoRepository.findByAccountIdInWithQuery(accountIds.getAccountIds()));
//    }
//
//}
