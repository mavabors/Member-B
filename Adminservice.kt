data class Destination(
    var stationName: String,
    var singlePrice: Double,
    var returnPrice: Double,
    var salesCount: Int
)

class AdminService(private val destinations: MutableList<Destination>) {

    fun viewDestinations() {
        println("\n--- DESTINATIONS ---")
        destinations.forEachIndexed { index, d ->
            println("$index: ${d.stationName} | Single: £%.2f | Return: £%.2f | Sales: ${d.salesCount}"
                .format(d.singlePrice, d.returnPrice))
        }
    }

    fun addDestination(name: String, single: Double, ret: Double) {
        destinations.add(Destination(name, single, ret, 0))
        println("Destination '$name' added successfully.")
    }

    fun updateDestination(index: Int, single: Double, ret: Double) {
        val d = destinations.getOrNull(index)
        if (d != null) {
            d.singlePrice = single
            d.returnPrice = ret
            println("Destination '${d.stationName}' updated successfully.")
        } else {
            println("Invalid index.")
        }
    }

    fun applyPriceFactor(factor: Double) {
        destinations.forEach {
            it.singlePrice *= factor
            it.returnPrice *= factor
        }
        println("All ticket prices updated by factor $factor")
    }
}
