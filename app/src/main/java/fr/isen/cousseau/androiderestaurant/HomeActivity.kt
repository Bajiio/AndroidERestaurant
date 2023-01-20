package fr.isen.cousseau.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import fr.isen.cousseau.androiderestaurant.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setTitle("Home")
        Log.v("HomeActivity", "onCreate")

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Starter.setOnClickListener{
            ChangePage(binding.Starter)
        }
        binding.middle.setOnClickListener{
            ChangePage(binding.middle)
        }
        binding.dessert.setOnClickListener{
            ChangePage(binding.dessert)
        }

        
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v("HomeActivity", "onDestroy")
    }

    fun ChangePage(Button: Button){
        val intent= Intent(this, CategoryActivity::class.java)
        intent.putExtra("title", Button.text)

        startActivity(intent)
    }
}