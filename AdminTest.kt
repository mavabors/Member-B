fun main() {
    val destinations = mutableListOf(
        Destination("Central Station", 10.0, 18.0, 5),
        Destination("London", 15.0, 25.0, 3),
        Destination("Manchester", 12.0, 20.0, 2)
    )

    val adminService = AdminService(destinations)
    val adminMenu = AdminMenu(adminService)

    adminMenu.display()
}
