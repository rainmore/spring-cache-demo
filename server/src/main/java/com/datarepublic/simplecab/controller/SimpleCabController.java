package com.datarepublic.simplecab.controller;

import com.datarepublic.simplecab.domains.CabTripData;
import com.datarepublic.simplecab.service.SimpleCabService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/simple-cab")
public class SimpleCabController {

    private final SimpleCabService simpleCabService;

    @Inject
    public SimpleCabController(SimpleCabService simpleCabService) {
        this.simpleCabService = simpleCabService;
    }

    @GetMapping("/medallionId/{medallionId}/pickupDate/{pickupDate}/ignoreCache/{ignoreCache}")
    public Iterable<CabTripData> getCabTripDataBy(
            @PathVariable(name = "medallionId") String medallionId,
            @PathVariable(name = "pickupDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate pickupDate,
            @PathVariable(name = "ignoreCache") Boolean ignoreCache
            ) {

        if (ignoreCache) {
            return simpleCabService.loadCabTripDataBy(medallionId, pickupDate);
        }
        else {
            return simpleCabService.getCabTripDataBy(medallionId, pickupDate);
        }
    }

}
