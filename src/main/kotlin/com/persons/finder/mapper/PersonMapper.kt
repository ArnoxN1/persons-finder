package com.persons.finder.mapper

import com.persons.finder.data.Person
import org.apache.ibatis.annotations.Mapper

@Mapper
interface PersonMapper {
    fun selectById(id: Int): Person
    fun selectByIds(ids: Array<Int>): List<Person>
}