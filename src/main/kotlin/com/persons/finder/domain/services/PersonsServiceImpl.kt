package com.persons.finder.domain.services

import com.persons.finder.data.Person
import com.persons.finder.mapper.PersonMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PersonsServiceImpl : PersonsService {

    @Autowired
    private val personMapper: PersonMapper? = null;

    override fun getById(id: Int): Person {
        return personMapper!!.selectById(id);
    }

    override fun save(person: Person) {
        TODO("Not yet implemented")
    }

    override fun getByIds(ids: Array<Int>): List<Person> {
        return personMapper!!.selectByIds(ids);
    }
}