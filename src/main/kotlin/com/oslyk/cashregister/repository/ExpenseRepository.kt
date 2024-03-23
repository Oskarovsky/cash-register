package com.oslyk.cashregister.repository

import com.oslyk.cashregister.model.Expense
import com.oslyk.cashregister.model.ExpenseCategory
import org.bson.types.ObjectId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*

@Repository
interface ExpenseRepository : MongoRepository<Expense, ObjectId> {

    @Query("{'name':?0}")
    fun findByName(name:String): Optional<Expense>

    fun findByDate(date: LocalDate, pageable: Pageable): Page<Expense>

    fun findByDateAndExpenseCategory(
            date: LocalDate?,
            category: ExpenseCategory?,
            pageable: Pageable)
    : Page<Expense>


}