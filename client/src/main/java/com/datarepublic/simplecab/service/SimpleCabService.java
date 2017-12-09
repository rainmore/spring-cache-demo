package com.datarepublic.simplecab.service;

import com.datarepublic.simplecab.domains.CabTripData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class SimpleCabService {

    private static final Logger logger = LoggerFactory.getLogger(SimpleCabService.class);

    private static final String CAB_TRIP_DATA_REQUEST_TEMPLATE =
            "http://localhost:8080/api/v1/simple-cab/medallionId/%s/pickupDate/%s/ignoreCache/%s";

    public List<CabTripData> getMedallionsSummary(String[] medallions, LocalDate pickupDate) {
        return getMedallionsSummary(medallions, pickupDate, false);
    }

    public List<CabTripData> getMedallionsSummary(String[] medallions, LocalDate pickupDate, boolean ignoreCache) {
        List<CabTripData> data = new ArrayList<>();

        Arrays.stream(medallions).forEach(medallionId -> {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<List<CabTripData>> responseEntity = restTemplate.exchange(
                buildCabTripDataRequestUrl(medallionId, pickupDate, ignoreCache),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CabTripData>>() {}
            );
            Optional<List<CabTripData>> responseList = Optional.ofNullable(responseEntity.getBody());
            if (responseList.isPresent() && !responseList.get().isEmpty()) {
                data.addAll(responseList.get());
            }
        });

        return data;
    }

    private static String buildCabTripDataRequestUrl(String medallionId, LocalDate pickupDate, boolean ignoreCache) {
        return String.format(CAB_TRIP_DATA_REQUEST_TEMPLATE,
                medallionId,
                pickupDate.format(DateTimeFormatter.ISO_DATE),
                Boolean.toString(ignoreCache)
        );
    }

}
