package com.oslyk.cashregister.service

import com.oslyk.cashregister.model.Expense
import com.oslyk.cashregister.model.ExpenseCategory
import com.oslyk.cashregister.repository.ExpenseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import mu.KotlinLogging
import org.bson.types.ObjectId
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedReader
import java.io.InputStreamReader
import java.math.BigDecimal
import java.time.LocalDate

@Service
class ExpenseService(
        @Autowired private val expenseRepository: ExpenseRepository
) {

    private val logger = KotlinLogging.logger {}

    fun addExpense(expense: Expense): Expense
        = expenseRepository.insert(expense)

    fun updateExpense(expense:Expense) {
        val savedExpense:Expense = expenseRepository
                .findById(expense.id!!)
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
        = expenseRepository.deleteById(ObjectId(id))

    fun deleteAllExpenses() = expenseRepository.deleteAll()

    fun processCSV(file: MultipartFile) {
        val reader = BufferedReader(InputStreamReader(file.inputStream))
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            val parts = line!!.split(",")
            if (parts.size == 5) {
                val name = parts[0].trim()
                val category = parts[1].trim()
                val amount = parts[2].toDoubleOrNull()
                val customer = parts[3].trim()
                val expenseDate = LocalDate.parse(parts[4])
                if (amount != null) {
                    val expense = Expense(
                            name = name,
                            expenseCategory = ExpenseCategory.valueOf(category),
                            amount = BigDecimal.valueOf(amount),
                            customer = customer,
                            date = expenseDate
                    )
                    expenseRepository.save(expense)
                }
            }
        }
    }
}