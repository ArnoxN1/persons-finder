package com.persons.finder.presentation

import com.persons.finder.data.Person
import com.persons.finder.domain.services.PersonsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.lang.NullPointerException

@RestController
@RequestMapping("api/v1/persons")
class PersonController @Autowired constructor() {

    @Autowired
    private val personsService: PersonsService? = null;

    /*
        TODO PUT API to update/create someone's location using latitude and longitude
        (JSON) Body
     */

    /*
        TODO POST API to create a 'person'
        (JSON) Body and return the id of the created entity
    */
    @PostMapping("/addPerson")
    fun insertPerson(@RequestBody person: Person): R<Int> {
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

    /*
        TODO GET API to retrieve a person or persons name using their ids
        // Example
        // John has the list of people around them, now they need to retrieve everybody's names to display in the app
        // API would be called using person or persons ids
     */
    @GetMapping("/persons")
    fun persons(ids: Array<Int>): R<List<Person>> {
        return R.ok(personsService!!.getByIds(ids))
    }

    @GetMapping("")
    fun getExample(): String {
        return "Hello Example"
    }

}