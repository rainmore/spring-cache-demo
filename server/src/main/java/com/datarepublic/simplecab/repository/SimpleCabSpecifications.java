package com.datarepublic.simplecab.repository;

import com.datarepublic.simplecab.domains.QCabTripData;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SimpleCabSpecifications {

    public static BooleanExpression idCondition(String id) {
        return QCabTripData.cabTripData.id.eq(id);
    }

    public static BooleanExpression pickupDateRangeCondition(LocalDate localDate) {
        LocalDateTime from = LocalDateTime.of(localDate, LocalTime.MIDNIGHT);
        LocalDateTime to = from.plusDays(1);
        return QCabTripData.cabTripData.pickup.datetime.between(from, to);
    }

}
