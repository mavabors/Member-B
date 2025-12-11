package com.example.train_ticketing_admin

fun main() {
    val manager = DestinationManager()
    initializeSampleData(manager)


    val admin = AdminInterface(manager)
    admin.start()
}


fun initializeSampleData(manager: DestinationManager) {
// Sample data requested in the brief
    manager.addDestination(Destination("London to Paris", 50.0, 90.0))
    manager.addDestination(Destination("London to Berlin", 70.0, 130.0))
    manager.addDestination(Destination("London to Amsterdam", 40.0, 75.0))
}