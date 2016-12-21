package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MonetizationGatewayApplication {
	public static final int MAX_COUNT=3;

	@Autowired
	WordCountClient wordCountClient;

	@Autowired
	ApiCallsCounterRepository apiCallsRepository;

	@RequestMapping(path = "/count",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
	public List<WordFrequency> count(@RequestBody String text){

		ApiCallsCounter apiCallsCounter = apiCallsRepository.findAll()
				.stream()
				.filter(x -> x.getServiceName().equals("asd"))
				.findAny()
				.orElseGet(() -> {
					ApiCallsCounter counter = new ApiCallsCounter();
					counter.setServiceName("asd");
					counter.setCount(0);
					return apiCallsRepository.save(counter);
				});

		if(apiCallsCounter.count>MAX_COUNT){
			throw  new RuntimeException("Too many api calls");
		}



		List<WordFrequency> result = wordCountClient.count(text);

		apiCallsCounter.Increment();
		apiCallsRepository.save(apiCallsCounter);
		return result;
	}

	@RequestMapping(path = "/apicalls", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public List<ApiCallsCounter> apicalls(){
		return apiCallsRepository.findAll().stream().collect(Collectors.toList());
	}

	@RequestMapping(path = "/reset", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public void reset(){
		apiCallsRepository.deleteAll();
	}
}
