package com.oslyk.cashregister.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.math.BigDecimal
import java.time.LocalDate

@Document("expenses")
data class Expense(
        @Id
        val id: ObjectId? = ObjectId.get(),

        @Field(name="name")
        @Indexed(unique = true)
        var name: String,

        @Field(name="category")
        var expenseCategory: ExpenseCategory,

        @Field(name="amount")
        var amount: BigDecimal,

        @Field(name = "customer")
        val customer: String,

        @Field(name = "date")
        var date: LocalDate
)