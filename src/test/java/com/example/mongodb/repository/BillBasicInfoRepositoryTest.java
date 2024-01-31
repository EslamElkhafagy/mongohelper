package com.example.mongodb.repository;

import com.example.mongodb.model.BillBasicInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ActiveProfiles;

//@DataMongoTest
@SpringBootTest
@EnableMongoTestServer
@ActiveProfiles("junit")
//@EnableMongoRepositories(basePackages = "com.example.mongodb.repository")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class BillBasicInfoRepositoryTest {


    @Autowired
    BillBasicInfoRepository billBasicInfoRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    void testSave(){

        BillBasicInfo basicInfo= new BillBasicInfo();
//        basicInfo.setBillId(1234l);
        basicInfo.setAccountId("12345");

        mongoTemplate.save(basicInfo);
        System.out.println(billBasicInfoRepository.findAll());
        Assertions.assertEquals(1,billBasicInfoRepository.findAll().size());
    }
}
