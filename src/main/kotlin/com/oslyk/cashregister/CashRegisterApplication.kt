package com.oslyk.cashregister

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CashRegisterApplication

fun main(args: Array<String>) {
    runApplication<CashRegisterApplication>(*args)
}
