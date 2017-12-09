package com.datarepublic.simplecab.repository;

import com.datarepublic.simplecab.domains.CabTripData;
import org.springframework.stereotype.Repository;

@Repository
public interface SimpleCabRepository extends BaseRepository <CabTripData, String> {

}
