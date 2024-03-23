package com.oslyk.cashregister.gateway

import com.oslyk.cashregister.model.Expense
import com.oslyk.cashregister.model.ExpenseCategory
import com.oslyk.cashregister.service.ExpenseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate


@RestController
@RequestMapping("/api/expense")
class ExpenseController(@Autowired
                        val expenseService: ExpenseService) {

    @GetMapping("/list")
    fun getAllExpense(): ResponseEntity<List<Expense>>
            = ResponseEntity.ok(expenseService.getAllExpense())

    @GetMapping("/{name}")
    fun getExpenseByName(@PathVariable name:String) : ResponseEntity<Expense>
            = ResponseEntity.ok(expenseService.getExpenseByName(name))

    @GetMapping
    fun getExpensePageByFilter(
            @RequestParam(required = false) date: LocalDate?,
            @RequestParam(required = false) category: ExpenseCategory?,
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "10") size: Int,
    ): ResponseEntity<Any> {
        val paging: Pageable = PageRequest.of(page, size)
        val expenses = expenseService.getPage(date, category, paging)
        return ResponseEntity<Any>(expenses, HttpStatus.OK)
    }

    @PostMapping
    fun addExpense(@RequestBody expense: Expense) : ResponseEntity<String> {
        expenseService.addExpense(expense)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PatchMapping
    fun updateExpense(@RequestBody expense:Expense): ResponseEntity<String>{
        expenseService.updateExpense(expense)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{id}")
    fun deleteExpense(@PathVariable id:String): ResponseEntity<String> {
        expenseService.deleteExpense(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @DeleteMapping
    fun deleteAllExpenses(): ResponseEntity<String> {
        expenseService.deleteAllExpenses()
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @PostMapping("/import")
    fun importCSV(@RequestParam("file") file: MultipartFile) {
        expenseService.processCSV(file)
    }
}