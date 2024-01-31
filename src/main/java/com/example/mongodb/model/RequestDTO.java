package com.example.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@Data
public class RequestDTO {

    List<String> accountIds;
}
