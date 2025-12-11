package com.example.train_ticketing_admin

import java.util.Scanner

class AdminInterface(private val manager: DestinationManager) {

    private val scanner = Scanner(System.`in`)

    fun start() {
        while (true) {
            println("\n=== TRAIN TICKETING ADMIN PANEL ===")
            println("1. List all destinations")
            println("2. Search destination by name")
            println("3. Update all prices (discount/increase)")
            println("4. Add a new destination")
            println("5. Edit an existing destination")   // NEW OPTION
            println("6. Exit")

            when (readValidInt("Choose an option: ", 1, 6)) {
                1 -> println(manager.listDestinations())
                2 -> searchDestination()
                3 -> updatePrices()
                4 -> addDestination()
                5 -> editDestination()      // NEW FUNCTION
                6 -> {
                    println("Exiting system...")
                    return
                }
            }
        }
    }

    private fun searchDestination() {
        val name = readNonEmptyString("Enter destination name: ")
        val result = manager.findDestinationByName(name)
        if (result == null) {
            println("No destination found with that name.")
        } else {
            println("Found: $result")
        }
    }

    private fun updatePrices() {
        println("Enter factor as decimal (0.9 = 10% discount, 1.1 = 10% increase)")
        val factor = readValidDouble("Factor: ") { it > 0 }
        manager.updateAllPrices(factor)
        println("Prices updated successfully!")
    }

    private fun addDestination() {
        val name = readNonEmptyString("Enter destination name: ")
        val single = readValidDouble("Enter single fare: ") { it > 0 }
        val returnFare = readValidDouble("Enter return fare: ") { it > 0 }

        manager.addDestination(Destination(name, single, returnFare))
        println("Destination added successfully!")
    }

    // ============================================================
    // NEW: EDIT DESTINATION FUNCTION
    // ============================================================

    private fun editDestination() {
        val name = readNonEmptyString("Enter the name of the destination to edit: ")
        val dest = manager.findDestinationByName(name)

        if (dest == null) {
            println("Destination not found.")
            return
        }

        println("\nEditing: ${dest.name}")
        println("Leave a field blank to keep the current value.\n")

        // Update name
        print("New name (current: ${dest.name}): ")
        val newName = scanner.nextLine().trim()
        if (newName.isNotBlank()) dest.name = newName

        // Update single fare
        print("New single fare (current: ${dest.singlePrice}): ")
        val singleInput = scanner.nextLine().trim()
        if (singleInput.isNotBlank()) {
            dest.singlePrice = singleInput.toDoubleOrNull() ?: dest.singlePrice
        }

        // Update return fare
        print("New return fare (current: ${dest.returnPrice}): ")
        val returnInput = scanner.nextLine().trim()
        if (returnInput.isNotBlank()) {
            dest.returnPrice = returnInput.toDoubleOrNull() ?: dest.returnPrice
        }

        // Update sales count (optional)
        print("New sales count (current: ${dest.sales}): ")
        val salesInput = scanner.nextLine().trim()
        if (salesInput.isNotBlank()) {
            dest.sales = salesInput.toIntOrNull() ?: dest.sales
        }

        println("\nDestination updated successfully!")
    }

    // ============================================================
    // INPUT UTILITIES
    // ============================================================

    private fun readNonEmptyString(prompt: String): String {
        while (true) {
            print(prompt)
            val input = scanner.nextLine()
            if (input.isBlank()) println("Input cannot be empty.")
            else return input.trim()
        }
    }

    private fun readValidDouble(prompt: String, validator: (Double) -> Boolean): Double {
        while (true) {
            print(prompt)
            try {
                val value = scanner.nextLine().toDouble()
                if (validator(value)) return value
                else println("Value is invalid.")
            } catch (e: Exception) {
                println("Invalid number, try again.")
            }
        }
    }

    private fun readValidInt(prompt: String, min: Int, max: Int): Int {
        while (true) {
            print(prompt)
            try {
                val value = scanner.nextLine().toInt()
                if (value in min..max) return value
                else println("Enter a number between $min and $max.")
            } catch (e: Exception) {
                println("Invalid input. Try again.")
            }
        }
    }
}
