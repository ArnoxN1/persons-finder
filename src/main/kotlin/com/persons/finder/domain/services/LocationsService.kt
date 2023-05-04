package com.persons.finder.domain.services

import com.persons.finder.data.Location

interface LocationsService {
    fun addLocation(location: Location)
    fun removeLocation(referenceId: Int)
    fun findAround(latitude: Double, longitude: Double, radiusInKm: Double) : List<Int>
    fun updateLocation(location: Location)
    fun getLocationByReferenceId(id: Int): Location
}