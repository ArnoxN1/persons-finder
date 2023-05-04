package com.persons.finder.domain.services

import com.persons.finder.data.Location
import com.persons.finder.mapper.LocationMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*
import java.util.stream.Collectors

@Service
class LocationsServiceImpl : LocationsService {

    @Autowired
    private val locationMapper: LocationMapper? = null;

    /**
     * create location
     */
    override fun addLocation(location: Location) {
        locationMapper!!.insert(location);
    }

    /**
     * delete a location from db
     */
    override fun removeLocation(referenceId: Int) {
        locationMapper!!.removeLocation(referenceId)
    }

    /**
     * retrieve people around query location with a radius in KM, Use query param for radius
     * ref: http://t.csdn.cn/Jp9i4
     */
    override fun findAround(latitude: Double, longitude: Double, radiusInKm: Double): MutableList<Int> {
        val r = 6371.0; //earth radius (km)
        var dlng = 2 * Math.asin(Math.sin(radiusInKm / (2 * r)) / Math.cos(latitude * Math.PI / 180));
        dlng = dlng * 180 / Math.PI; //angle to radians
        var dlat = radiusInKm / r;
        dlat = dlat * 180 / Math.PI;
        // min latitude
        val minlat = latitude - dlat;
        // max latitude
        val maxlat = latitude + dlat;
        // max longitude
        val maxlng = longitude - dlng;
        // min longitude
        val minlng = longitude + dlng;
        val vicinity = locationMapper?.getVicinity(BigDecimal.valueOf(minlng), BigDecimal.valueOf(maxlng), BigDecimal.valueOf(minlat), BigDecimal.valueOf(maxlat))
        return vicinity?.stream()?.map { it.referenceId }?.collect(Collectors.toList()) ?: Collections.emptyList();
    }

    /**
     * update or create location
     */
    override fun updateLocation(location: Location) {
        if (location.referenceId?.let { getLocationByReferenceId(it) } != null) {
            locationMapper!!.update(location);
        } else {
            addLocation(location);
        }
    }

    /**
     * check if certain location exist in the db
     */
    override fun getLocationByReferenceId(id: Int): Location {
        return locationMapper!!.selectByReferenceId(id);
    }

}