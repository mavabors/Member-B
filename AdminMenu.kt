class AdminMenu(private val adminService: AdminService) {

    fun display() {
        while (true) {
            println("\n--- ADMIN MENU ---")
            println("1. View all destinations")
            println("2. Add a destination")
            println("3. Update a destination")
            println("4. Change all prices by a factor")
            println("5. Exit")
            print("Choose an option: ")

            when (readLine()?.toIntOrNull()) {
                1 -> adminService.viewDestinations()
                2 -> {
                    print("Enter station name: ")
                    val name = readLine() ?: ""
                    print("Enter single price: ")
                    val s = readLine()?.toDoubleOrNull() ?: 0.0
                    print("Enter return price: ")
                    val r = readLine()?.toDoubleOrNull() ?: 0.0
                    adminService.addDestination(name, s, r)
                }
                3 -> {
                    adminService.viewDestinations()
                    print("Enter index of destination to update: ")
                    val i = readLine()?.toIntOrNull() ?: -1
                    print("Enter new single price: ")
                    val s = readLine()?.toDoubleOrNull() ?: 0.0
                    print("Enter new return price: ")
                    val r = readLine()?.toDoubleOrNull() ?: 0.0
                    adminService.updateDestination(i, s, r)
                }
                4 -> {
                    print("Enter factor (e.g. 1.1 or 0.9): ")
                    val f = readLine()?.toDoubleOrNull() ?: 1.0
                    adminService.applyPriceFactor(f)
                }
                5 -> return
                else -> println("Invalid option.")
            }
        }
    }
}
