package edu.ccsu.cs560.hw2pizza

public class Pizza(size: Double, delivery: Boolean) {
    var size: Double = size
    val toppings: HashSet<String> = HashSet<String>()
    var delivery = delivery

    fun addTopping(topping: String) {
        toppings.add(topping)
    }

    fun removeTopping(topping: String) {
        toppings.remove(topping)
    }
}