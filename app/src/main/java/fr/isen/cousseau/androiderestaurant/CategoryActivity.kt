package fr.isen.cousseau.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.isen.cousseau.androiderestaurant.databinding.ActivitySecondBinding

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle(intent.getStringExtra("title"))
        binding.Title.text = intent.getStringExtra("title")
        val back = findViewById<Button>(R.id.back)
        back.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        val recyclerview = binding.rvList
        recyclerview.layoutManager = LinearLayoutManager(this)
        when(binding.Title.text) {
            "Starter" -> recyclerview.adapter = CategoryAdapter(resources.getStringArray(R.array.starter))
            "Plat" -> recyclerview.adapter = CategoryAdapter(resources.getStringArray(R.array.middle))
            "Dessert" -> recyclerview.adapter = CategoryAdapter(resources.getStringArray(R.array.dessert))
        }
    }
}