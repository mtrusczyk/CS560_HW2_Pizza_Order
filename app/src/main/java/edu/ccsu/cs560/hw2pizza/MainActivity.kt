package edu.ccsu.cs560.hw2pizza

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.CompoundButton
import kotlinx.android.synthetic.main.activity_main.*
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    val pizza = Pizza(9.99, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pizza_type.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long){
                var selectedPizza = parent.getItemAtPosition(position)

                val pizzaImage: Int = when (selectedPizza) {
                    "Pepperoni" -> R.drawable.pepperoni
                    "Margheritta" -> R.drawable.margheritta
                    "Hawaiian" -> R.drawable.hawaiian
                    "BBQ Chicken" -> R.drawable.bbq_chicken
                    else -> R.drawable.pepperoni
                }
                pizzaView.setImageResource(pizzaImage)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        pizza_size.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long){
                var selectedSize = parent.getItemAtPosition(position)

                val pizzaSize: Double = when (selectedSize) {
                    "Medium (\$9.99)" -> 9.99
                    "Large (\$13.99)" -> 13.99
                    "XLarge (\$15.99)" -> 15.99
                    else -> 9.99
                }
                pizza.size = pizzaSize
                calculatePayment(pizza)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        delivery_switch.setOnCheckedChangeListener{_, isChecked ->
            pizza.delivery = isChecked
            calculatePayment(pizza)
        }

        cheese.setOnCheckedChangeListener { buttonView, isChecked ->
            toppingChecked(buttonView.text.toString(), isChecked)
        }

        mushrooms.setOnCheckedChangeListener { buttonView, isChecked ->
            toppingChecked(buttonView.text.toString(), isChecked)
        }

        onions.setOnCheckedChangeListener { buttonView, isChecked ->
            toppingChecked(buttonView.text.toString(), isChecked)
        }

        spinach.setOnCheckedChangeListener { buttonView, isChecked ->
            toppingChecked(buttonView.text.toString(), isChecked)
        }

        broccoli.setOnCheckedChangeListener { buttonView, isChecked ->
            toppingChecked(buttonView.text.toString(), isChecked)
        }

        black_olives.setOnCheckedChangeListener { buttonView, isChecked ->
            toppingChecked(buttonView.text.toString(), isChecked)
        }

        tomatoes.setOnCheckedChangeListener { buttonView, isChecked ->
            toppingChecked(buttonView.text.toString(), isChecked)
        }

        calculatePayment(pizza)

    }


    fun toppingChecked(topping: String, isChecked: Boolean) {
        if (isChecked)
            pizza.addTopping(topping)
        else
            pizza.removeTopping(topping)

        calculatePayment(pizza)
    }


    fun calculatePayment(pizza: Pizza) {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.FLOOR

        var totalCost = 0.0;
        totalCost += pizza.size
        totalCost += if (pizza.delivery) 2 else 0
        totalCost += 1.69 * pizza.toppings.size

        total_cost.text = "Total Cost: \$${df.format(totalCost)}"
    }


}
