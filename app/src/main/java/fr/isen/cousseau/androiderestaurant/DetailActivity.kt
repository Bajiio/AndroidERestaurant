package fr.isen.cousseau.androiderestaurant

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import fr.isen.cousseau.androiderestaurant.data.model.Plat
import fr.isen.cousseau.androiderestaurant.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var plat: Plat
    private var cart: Int = 0
    var price: Float = 0.0F
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        binding=ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        plat = intent.getSerializableExtra("plat") as Plat
        title = plat.name_fr

        Log.i("DATA DETAILS TEST", plat.name_fr)
        var ingredients = ""
        for (ingr in plat.ingredients)
        {
            ingredients += ingr.name_fr+ ", "
        }

        binding.DetailsTitle.text = plat.name_fr
        binding.DetailsDesc.text = ingredients
        price = plat.prices[0].price.toFloat()
        binding.DetailsPrice.text = price.toString()


        val viewPager = binding.DetailViewPager
        val adapter = DetailAdapter(plat.images)
        viewPager.adapter=adapter
        binding.DetailsNumber.text=cart.toString()

        binding.DetailsPlus.setOnClickListener {
            addToCart()
        }

        binding.DetailsMinus.setOnClickListener {
            removeFromCart()
        }
    }

    private fun removeFromCart() {
        cart--
        binding.DetailsNumber.text=cart.toString()
        Snackbar.make(binding.root, plat.name_fr + " retiré(e)", Snackbar.LENGTH_SHORT).show()
        calculateCart()
    }

    fun addToCart(){
        cart++
        binding.DetailsNumber.text=cart.toString()
        Snackbar.make(binding.root, plat.name_fr + " ajouté(e)", Snackbar.LENGTH_SHORT).show()
        calculateCart()
    }

    @SuppressLint("SetTextI18n")
    fun calculateCart(){
        val total= price * cart
        binding.DetailsCart.text = "Total Price " + total.toString()
    }
}