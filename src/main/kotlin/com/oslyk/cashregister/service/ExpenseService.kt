package com.oslyk.cashregister.service

import com.oslyk.cashregister.model.Expense
import com.oslyk.cashregister.repository.ExpenseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import mu.KotlinLogging

@Service
class ExpenseService(@Autowired val expenseRepository: ExpenseRepository) {

    private val logger = KotlinLogging.logger {}

    fun addExpense(expense: Expense): Expense
        = expenseRepository.insert(expense)

    fun updateExpense(expense:Expense) {
        val savedExpense:Expense = expenseRepository
                .findById(expense.id)
                .orElseThrow { throw RuntimeException("Cannot find Expense by ID") }
        savedExpense.name=expense.name
        savedExpense.expenseCategory=expense.expenseCategory
        savedExpense.amount=expense.amount
        expenseRepository.save(savedExpense)
    }

    fun getAllExpense(): List<Expense> = expenseRepository.findAll()

    fun getExpenseByName(name:String): Expense
        = expenseRepository
                .findByName(name)
                .orElseThrow{ throw RuntimeException("Cannot find Expense by Name") }

    fun deleteExpense(id:String)
        = expenseRepository.deleteById(id)
}