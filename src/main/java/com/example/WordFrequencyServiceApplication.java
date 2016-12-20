package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class WordFrequencyServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(WordFrequencyServiceApplication.class, args);
	}

	@RequestMapping( path = "/count", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<WordFrequency> count(@RequestBody String textBody){
		Map<String,Integer> words= new HashMap<>();
		Pattern pattern = Pattern.compile("\\w+");
		Matcher matcher = pattern.matcher(textBody);

		while (matcher.find()) {
			String word = matcher.group().toLowerCase();
			if(words.containsKey(word)){
				Integer previousCount = words.get(word);
				words.put(word,previousCount+1);
			}else {
				words.put(word,1);
			}
		}

		return  words.entrySet()
				.stream()
				.map(x->new WordFrequency(x.getKey(),x.getValue()))
				.collect(Collectors.toList());
	}
}