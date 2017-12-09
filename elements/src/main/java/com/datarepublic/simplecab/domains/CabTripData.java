package com.datarepublic.simplecab.domains;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cab_trip_data")
public class CabTripData implements Model {

    @Id
    private String  id;
    private String  hackLicense;
    private String  vendorId;
    private String  rateCode;
    private String  storeAndFwdFlag;
    private Integer passengerCount;

    private Integer tripTimeInSecs;
    private Double  tripDistance;

    private GeoPoint pickup;
    private GeoPoint dropoff;

    @Id
    @Column(name = "medallion", nullable = false)
    public String getId() {
        return id;
    }

    public CabTripData setId(String id) {
        this.id = id;
        return this;
    }

    @Column(name = "hack_license")
    public String getHackLicense() {
        return hackLicense;
    }

    public CabTripData setHackLicense(String hackLicense) {
        this.hackLicense = hackLicense;
        return this;
    }

    @Column(name = "vendor_id")
    public String getVendorId() {
        return vendorId;
    }

    public CabTripData setVendorId(String vendorId) {
        this.vendorId = vendorId;
        return this;
    }

    @Column(name = "rate_code")
    public String getRateCode() {
        return rateCode;
    }

    public CabTripData setRateCode(String rateCode) {
        this.rateCode = rateCode;
        return this;
    }

    @Column(name = "store_and_fwd_flag")
    public String getStoreAndFwdFlag() {
        return storeAndFwdFlag;
    }

    public CabTripData setStoreAndFwdFlag(String storeAndFwdFlag) {
        this.storeAndFwdFlag = storeAndFwdFlag;
        return this;
    }

    @Column(name = "passenger_count")
    public Integer getPassengerCount() {
        return passengerCount;
    }

    public CabTripData setPassengerCount(Integer passengerCount) {
        this.passengerCount = passengerCount;
        return this;
    }

    @Column(name = "trip_time_in_secs")
    public Integer getTripTimeInSecs() {
        return tripTimeInSecs;
    }

    public CabTripData setTripTimeInSecs(Integer tripTimeInSecs) {
        this.tripTimeInSecs = tripTimeInSecs;
        return this;
    }

    @Column(name = "trip_distance")
    public Double getTripDistance() {
        return tripDistance;
    }

    public CabTripData setTripDistance(Double tripDistance) {
        this.tripDistance = tripDistance;
        return this;
    }

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "datetime", column = @Column(name = "pickup_datetime")),
            @AttributeOverride(name = "longitude", column = @Column(name = "pickup_longitude")),
            @AttributeOverride(name = "latitude", column = @Column(name = "pickup_latitude"))
    })
    public GeoPoint getPickup() {
        return pickup;
    }

    public CabTripData setPickup(GeoPoint pickup) {
        this.pickup = pickup;
        return this;
    }

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "datetime", column = @Column(name = "dropoff_datetime")),
            @AttributeOverride(name = "longitude", column = @Column(name = "dropoff_longitude")),
            @AttributeOverride(name = "latitude", column = @Column(name = "dropoff_latitude"))
    })
    public GeoPoint getDropoff() {
        return dropoff;
    }

    public CabTripData setDropoff(GeoPoint dropoff) {
        this.dropoff = dropoff;
        return this;
    }
}
