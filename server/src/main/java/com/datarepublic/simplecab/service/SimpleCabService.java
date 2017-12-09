package com.datarepublic.simplecab.service;

import com.datarepublic.simplecab.Server;
import com.datarepublic.simplecab.domains.CabTripData;
import com.datarepublic.simplecab.repository.SimpleCabRepository;
import com.datarepublic.simplecab.repository.SimpleCabSpecifications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@CacheConfig(cacheNames = Server.CACHE_NAME)
public class SimpleCabService {

    private static final Logger logger = LoggerFactory.getLogger(SimpleCabService.class);

    private final SimpleCabRepository simpleCabRepository;

    @Inject
    public SimpleCabService(SimpleCabRepository simpleCabRepository) {
        this.simpleCabRepository = simpleCabRepository;
    }

    public Long getCountByMedallionAndPickupDatetime(String medallionId, LocalDate pickupDate) {
        return simpleCabRepository.count(SimpleCabSpecifications.idCondition(medallionId)
            .and(SimpleCabSpecifications.pickupDateRangeCondition(pickupDate)));
    }

    private Iterable<CabTripData> findAllBy(String medallionId, LocalDate pickupDate) {
        logger.info(String.format("Called to the database at %s", LocalDateTime.now()));
        return simpleCabRepository.findAll(SimpleCabSpecifications.idCondition(medallionId)
                .and(SimpleCabSpecifications.pickupDateRangeCondition(pickupDate)));
    }

    @Cacheable
    public Iterable<CabTripData> getCabTripDataBy(String medallionId, LocalDate pickupDate) {
        logger.info("Caches %s has been cached");
        return findAllBy(medallionId, pickupDate);
    }

    @CachePut
    public Iterable<CabTripData> loadCabTripDataBy(String medallionId, LocalDate pickupDate) {
        logger.info("Caches %s has been cleaned and regenerated");
        return findAllBy(medallionId, pickupDate);
    }

}
