package com.persons.finder.domain.services

import com.persons.finder.data.Person

interface PersonsService {
    fun getById(id: Int): Person
    fun save(person: Person)
    fun getByIds(ids: Array<Int>): List<Person>
}