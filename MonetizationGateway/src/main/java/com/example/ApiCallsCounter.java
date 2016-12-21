package com.example;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by atrposki on 20-Dec-16.
 */
@Entity
@Data
public class ApiCallsCounter {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    Long id;
    String serviceName;
    Integer count;
    public void Increment(){
        count = count+1;
    }
}
