package com.persons.finder.domain.services

import com.persons.finder.data.Person
import com.persons.finder.exceptions.CustomizeException
import com.persons.finder.mapper.PersonMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PersonsServiceImpl : PersonsService {

    @Autowired
    private val personMapper: PersonMapper? = null;

    /**
     * find one person
     */
    override fun getById(id: Int): Person {
        return personMapper!!.selectById(id);
    }

    /**
     * create a 'person'
     */
    override fun save(person: Person): Int {
        return personMapper!!.insert(person);
    }

    /**
     * get person(s) name by id(s)
     */
    override fun getByIds(ids: Array<Int>): List<Person> {
        return personMapper!!.selectByIds(ids);
    }
}