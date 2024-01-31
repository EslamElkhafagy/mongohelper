package com.example.mongodb.repository;

import com.example.mongodb.model.AggregatIOnChartDTO;
import com.example.mongodb.model.BillBasicInfo;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BillBasicInfoRepository extends MongoRepository<BillBasicInfo, UUID> {

    BillBasicInfo findByBillId(Long billId);
    BillBasicInfo deleteByBillId(Long billId);
    Optional<BillBasicInfo> deleteById(String id);
    Optional<BillBasicInfo> findById(String id);

    @Query("{'ccbs':{$in : [?0] }}")
    List<BillBasicInfo> findByCcb(String ccb);

    @Query("{'ccbs':{$in :?0 }}")
    List<BillBasicInfo> findByCcb(List<String> ccb);

    @Query("{'accountId':{$in :?0 }}")
    List<BillBasicInfo> findByAccountIdInWithQuery(List<String> accountsId);
    List<BillBasicInfo> findByAccountIdIn(List<String> accountsId);
    Page<BillBasicInfo> findByAccountIdIn(List<String> accountsId,Pageable pageable);

//    @Query("{'accountId':?0}")
    @Query(value="{'accountId':?0}",fields="{account_id:1,bill_id:1,company_name:1,issue_date:1,net_amount:1,gross_amount:1,_id : 0}")
    Page<BillBasicInfo> findByAccountId(String accountId, Pageable pageable);

    @Query(value="{'issue_date':{$gt: ?0, $lt: ?1}}",fields="{account_id:1,bill_id:1,company_name:1,issue_date:1,net_amount:1,gross_amount:1,_id : 0}")

    Page<BillBasicInfo> findAllInvoicesByMonths(String startMonth,String endMonth,Pageable pageable);
    @Query(value="{'issue_date':{$gte: ?0, $lt: ?1},'account_id': ?2}",fields="{account_id:1,bill_id:1,company_name:1,issue_date:1,net_amount:1,gross_amount:1,_id : 0}")

    Page<BillBasicInfo> findAllInvoicesByMonthsWithAccountId(String startMonth,String endMonth,String accountId, Pageable pageable);

    List<BillBasicInfo> findByIssueDate(Long issueDate, Sort sort);
    @Query(value="{}", fields="{_id : 0}")
    Page<BillBasicInfo> findAll(Pageable pageable);
    @Query(fields="{account_id:1,bill_id:1,company_name:1,issue_date:1,net_amount:1,gross_amount:1,_id : 0}")
    Page<BillBasicInfo> findAllByAccountIdLike(String accountId,Pageable pageable);
    List<BillBasicInfo> findByAccountIdContainingIgnoreCase(String accountId);
    List<BillBasicInfo> findByAccountIdNotLike(String accountId);
    List<BillBasicInfo> findByAccountIdStartsWith(String accountId);
    List<BillBasicInfo> findByAccountIdEndsWith(String accountId);

    List<BillBasicInfo> findByNetAmountLessThan(Double netAmount);
    List<BillBasicInfo> findByNetAmountLessThanEqual(Double netAmount);
    List<BillBasicInfo> findByNetAmountNotLike(Double netAmount);

    List<BillBasicInfo> findByIssueDateAfter(Long issueDate);
    List<BillBasicInfo> findByIssueDateBefore(Long issueDate);
    List<BillBasicInfo> findByIssueDateEquals(Long issueDate);
    @Query(value="{}", fields="{bill_id: 1,account_id: 1, _id : 0}")
    List<BillBasicInfo> findAllExcludeId();


    @Query(value="{}", fields = "{'userNames': { '$slice': [?0,?1] } }")
    List<BillBasicInfo> findAlluserNames(Integer start,Integer end);


    @Aggregation(pipeline = {"{" +
            "    $match: {" +
            " account_id:{$in:?0},"+
            "      issue_date: {" +
            "        $gte: ?1," +
            "        $lte: ?2" +
            "      }" +
            "    }" +
            "  }","{" +
            "   $group: {" +
            "      _id: { $dateToString: { format: \"%Y-%m\", date: \"$issue_date\" } }," +
            "      total_net_amount: { $sum: \"$net_amount\" }," +
            "      bills: { $push: \"$issue_date\" }" +
            "   }" +
            "}","{" +
            "   $project: {" +
            "      _id: 0," +
            "     monthlyTotalAmounts:[\"$_id\",{ $round: [\"$total_net_amount\", 2] }]," +
            "     monthlyDetails:[{\"month\":\"$_id\", \"billDates\": \"$bills\"}]," +
            "   }" +
            "}"})
    List<AggregatIOnChartDTO> findByAccountIdAndIssueDateBetween(List<String> accountIds, Date start, Date end);

}
