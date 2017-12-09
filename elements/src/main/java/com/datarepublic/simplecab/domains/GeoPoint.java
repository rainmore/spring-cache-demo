package com.datarepublic.simplecab.domains;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
public class GeoPoint extends GeoCode {

    private LocalDateTime datetime;

    public GeoPoint() {
    }

    public GeoPoint(LocalDateTime datetime, GeoCode geoCode) {
        this(datetime, geoCode.getLatitude(), geoCode.getLongitude());
    }

    public GeoPoint(LocalDateTime datetime, Double latitude, Double longitude) {
        this.datetime = datetime;
        this.setLatitude(latitude);
        this.setLongitude(longitude);
    }

    @Column(nullable = false)
    public LocalDateTime getDatetime() {
        return datetime;
    }

    public GeoPoint setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
        return this;
    }

}
