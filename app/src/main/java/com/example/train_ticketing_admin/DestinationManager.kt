package com.example.train_ticketing_admin

class DestinationManager {
    private val destinations = mutableListOf<Destination>()


    fun addDestination(destination: Destination) {
        destinations.add(destination)
    }


    fun findDestinationByName(name: String): Destination? {
        val query = name.trim().lowercase()
        return destinations.firstOrNull { it.name.lowercase() == query }
    }


    fun listDestinations(): String {
        if (destinations.isEmpty()) return "No destinations available."


        val header = String.format("%-30s | %10s | %11s | %6s", "Destination", "Single", "Return", "Sales")
        val divider = "" + "-".repeat(30) + "-+" + "-".repeat(12) + "+" + "-".repeat(13) + "+" + "-".repeat(8)
        val builder = StringBuilder()
        builder.appendLine(header)
        builder.appendLine(divider)
        destinations.forEach { builder.appendLine(it.toString()) }
        return builder.toString()
    }


    /**
     * Update all prices by a factor. 0 < factor < 1 for discounts, factor > 1 for increases.
     * Factor must be positive.
     */
    fun updateAllPrices(factor: Double) {
        require(factor > 0) { "Factor must be positive" }
        destinations.forEach { dest ->
            dest.singlePrice = (dest.singlePrice * factor).let { (Math.round(it * 100) / 100.0) }
            dest.returnPrice = (dest.returnPrice * factor).let { (Math.round(it * 100) / 100.0) }
        }
    }


    fun getAll(): List<Destination> = destinations.toList()
}