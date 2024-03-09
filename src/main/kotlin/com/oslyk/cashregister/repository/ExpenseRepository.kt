package com.oslyk.cashregister.repository

import com.oslyk.cashregister.model.Expense
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ExpenseRepository : MongoRepository<Expense, String> {
    @Query("{'name':?0}")
    fun findByName(name:String): Optional<Expense>
}