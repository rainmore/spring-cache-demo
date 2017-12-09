package com.datarepublic.simplecab.domains;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Embeddable
public class GeoCode implements Serializable {

    private Double latitude;
    private Double longitude;

    public GeoCode() {
    }

    public GeoCode(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Column(nullable = false)
    public Double getLatitude() {
        return latitude;
    }

    public GeoCode setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }


    @Column(nullable = false)
    public Double getLongitude() {
        return longitude;
    }

    public GeoCode setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

}
