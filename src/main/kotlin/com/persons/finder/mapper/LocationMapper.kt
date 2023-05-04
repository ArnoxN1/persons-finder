package com.persons.finder.mapper

import com.persons.finder.data.Location
import org.apache.ibatis.annotations.Mapper
import java.math.BigDecimal

@Mapper
interface LocationMapper {
    fun update(location: Location?): Boolean
    fun insert(location: Location): Int
    fun selectByReferenceId(id: Int): Location
    fun getVicinity(minlng: BigDecimal, maxlng: BigDecimal, minlat: BigDecimal, maxlat: BigDecimal): List<Location>
    fun removeLocation(referenceId: Int)
}