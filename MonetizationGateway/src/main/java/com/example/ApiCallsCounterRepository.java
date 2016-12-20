package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by atrposki on 20-Dec-16.
 */
@Repository
public interface ApiCallsCounterRepository extends JpaRepository<ApiCallsCounter, Long> {
    public Collection<ApiCallsCounter> serviceName(String serviceName);
}
