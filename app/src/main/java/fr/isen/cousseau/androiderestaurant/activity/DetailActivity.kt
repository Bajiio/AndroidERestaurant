package fr.isen.cousseau.androiderestaurant.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import fr.isen.cousseau.androiderestaurant.adapter.DetailAdapter
import fr.isen.cousseau.androiderestaurant.R
import fr.isen.cousseau.androiderestaurant.data.model.CartContainer
import fr.isen.cousseau.androiderestaurant.data.model.CartObject
import fr.isen.cousseau.androiderestaurant.data.model.Plat
import fr.isen.cousseau.androiderestaurant.databinding.ActivityDetailBinding
import java.io.File
import java.util.*

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private lateinit var plat: Plat
    private var cart: Int = 0
    private lateinit var cartContainer: CartContainer
    var price: Float = 0.0F
    val filename: String = "current_cart.json"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        binding=ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        plat = intent.getSerializableExtra("plat") as Plat
        title = plat.name_fr
        cartContainer = getCartContainer()

        var ingredients = ""
        for (ingr in plat.ingredients)
        {
            ingredients += ingr.name_fr+ ", "
        }

        binding.DetailsTitle.text = plat.name_fr
        binding.DetailsDesc.text = ingredients
        price = plat.prices[0].price.toFloat()
        binding.DetailsPrice.text = price.toString() + "€"
        binding.DetailsNumber.text = cart.toString()
        calculateCart()

        val viewPager = binding.DetailViewPager
        val adapter = DetailAdapter(plat.images)
        viewPager.adapter=adapter

        binding.DetailsPlus.setOnClickListener {
            addNumber()
        }

        binding.DetailsMinus.setOnClickListener {
            removeNumber()
        }

        binding.DetailsCart.setOnClickListener {
            addToCart()
        }
    }

    private fun removeNumber() {
        if (cart > 0)
            cart--
        binding.DetailsNumber.text=cart.toString()
        Snackbar.make(binding.root, plat.name_fr + " retiré(e)", Snackbar.LENGTH_SHORT).show()
        calculateCart()
    }

    fun addNumber(){
        cart++
        binding.DetailsNumber.text=cart.toString()
        Log.i("CART_CONTENT", cart.toString())
        Snackbar.make(binding.root, plat.name_fr + " ajouté(e)", Snackbar.LENGTH_SHORT).show()
        calculateCart()
    }

    @SuppressLint("SetTextI18n")
    fun calculateCart(){
        var total= price * cart
        binding.DetailsCart.text = "Total Price " + total.toString()
    }

    fun addToCart() {
        var index = -1
        index = cartContainer.CartsObjectList.map { it.plat.name_fr }.indexOf(plat.name_fr)
        if (index <0)
        {
            cartContainer.CartsObjectList.add(CartObject(plat,cart))
        }
        else
        {
            cartContainer.CartsObjectList[index].nb_cart=cart
        }

        Log.i("CART_CONTAINER", cartContainer.toString())
        saveCartContainer()
    }

    fun saveCartContainer() {
        val jsonBuilder= GsonBuilder().setPrettyPrinting().create()
        val jsonText = jsonBuilder.toJson(cartContainer)
        File(cacheDir.absolutePath,filename).writeText(jsonText)
    }

    fun getCartContainer(): CartContainer {
        var file = File(cacheDir.absolutePath,filename)
        if( file.exists())
        {
            var outputText = file.readText()
            val jsonBuilder = Gson()
            Log.i("FILE EXIST", "TRUE")

            var lastCartContainer =  jsonBuilder.fromJson(outputText,CartContainer::class.java)
            var index = lastCartContainer.CartsObjectList.map { it.plat.name_fr }.indexOf(plat.name_fr)
            Log.v("INDEX", index.toString())
            if (index >= 0)
            {
                Log.i("LASTCARTCONTAINER", lastCartContainer.CartsObjectList[index].nb_cart.toString())
                cart= lastCartContainer.CartsObjectList[index].nb_cart
            }
            else
            {
                cart = 0
            }

            return lastCartContainer
        }
        else
        {
            Log.i("FILE DOES NOT EXIST", "FALSE")
            return CartContainer(mutableListOf<CartObject>())
        }
    }
}