package com.example;

import com.example.WordFrequency;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by atrposki on 20-Dec-16.
 */
@FeignClient(value = "word-count-service", name="word-count-service")
public interface WordCountClient {
    @RequestMapping( path = "/count", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<WordFrequency> count(@RequestBody String text);
}
