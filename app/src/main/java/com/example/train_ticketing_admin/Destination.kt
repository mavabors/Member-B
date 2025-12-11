package com.example.train_ticketing_admin

data class Destination(
    var name: String,
    var singlePrice: Double,
    var returnPrice: Double,
    var sales: Int = 0
) {
    override fun toString(): String {
// A neat, fixed-width formatted representation for table output
        return String.format(
            "%-30s | %10.2f | %11.2f | %6d",
            name,
            singlePrice,
            returnPrice,
            sales
        )
    }
}