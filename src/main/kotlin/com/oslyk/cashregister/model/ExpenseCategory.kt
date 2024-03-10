package com.oslyk.cashregister.model

enum class ExpenseCategory(
    val description: String
) {
    ENTERTAINMENT(description = "cinemas, concerts, parties"),
    TRIP(description = "holidays, trips"),
    GROCERIES(description = "bigger foods shopping (eclerc, markets)"),
    RESTAURANT(description = "restaurants, bars"),
    OTHERS(description = "others"),
    MEDIA(description = "tv, internet, youtube, spotify"),
    HOME(description = "rent, fees"),
    VEHICLE(description = "fuel, car inspections"),
    BUSINESS(description = "b2b, servers, IT"),
    STOCK_MARKET(description = "etf, markets, xtb")
}