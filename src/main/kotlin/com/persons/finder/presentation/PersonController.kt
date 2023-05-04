package com.persons.finder.presentation

import com.persons.finder.data.Location
import com.persons.finder.data.Person
import com.persons.finder.domain.services.LocationsService
import com.persons.finder.domain.services.PersonsService
import com.persons.finder.exceptions.CustomizeException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty

@RestController
@RequestMapping("api/v1/persons")
@Validated
class PersonController @Autowired constructor() {

    @Autowired
    private val personsService: PersonsService? = null;

    @Autowired
    private val locationsService: LocationsService? = null;

    /*
        TODO PUT API to update/create someone's location using latitude and longitude
        (JSON) Body
     */
    @PutMapping("/location")
    fun updateLocation(@RequestBody @Valid location: Location): R<Void?> {
        if (Objects.isNull(personsService!!.getById(location.referenceId!!))){
            throw CustomizeException("Please create the person first!")
        }
        locationsService!!.updateLocation(location!!);
        return R.ok();
    }

    /*
        TODO POST API to create a 'person'
        (JSON) Body and return the id of the created entity
    */
    @PostMapping("/addPerson")
    fun insertPerson(@RequestBody person: Person): R<Int?> {
        personsService!!.save(person);
        return R.ok(person.id);
    }

    /*
        TODO GET API to retrieve people around query location with a radius in KM, Use query param for radius.
        TODO API just return a list of persons ids (JSON)
        // Example
        // John wants to know who is around his location within a radius of 10km
        // API would be called using John's id and a radius 10km
     */
    @GetMapping("/around")
    fun findAround(
        @Min(value = 1, message = "radiusInKm can't be less than 1")
        radiusInKm: Double,
        @Min(value = 1, message = "person's id can't be less than 1")
        id: Int): R<List<Int>> {
        if (radiusInKm < 1 || id < 1) {
            throw CustomizeException("parameters can't be less than one")
        }
        // if person's location doesn't exist then can't find person's around
        val location = locationsService!!.getLocationByReferenceId(id!!);
        if (Objects.isNull(location)) {
            throw CustomizeException("Can't find the person's location in database!");
        }
        return R.ok(locationsService.findAround(location.latitude!!, location.longitude!!, radiusInKm!!))
    }

    /*
        TODO GET API to retrieve a person or persons name using their ids
        // Example
        // John has the list of people around them, now they need to retrieve everybody's names to display in the app
        // API would be called using person or persons ids
     */
    @GetMapping("/persons")
    fun persons(@NotEmpty(message = "ids can't be null") ids: Array<Int>): R<List<Person>> {
        return R.ok(personsService!!.getByIds(ids))
    }

    @GetMapping("")
    fun getExample(): String {
        return "Hello Example"
    }

}