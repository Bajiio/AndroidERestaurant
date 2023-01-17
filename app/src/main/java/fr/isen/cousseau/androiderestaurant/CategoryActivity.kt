package fr.isen.cousseau.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.cousseau.androiderestaurant.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle(intent.getStringExtra("title"))
        binding.Title.text = intent.getStringExtra("title")
        val back = binding.back
        back.setOnClickListener {
            var intent=Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }


        val recyclerview = binding.rvList
        recyclerview.layoutManager = LinearLayoutManager(this)
        val dishes = when (binding.Title.text) {
            "Starter" -> resources.getStringArray(R.array.starter)
            "Plat" -> resources.getStringArray(R.array.middle)
            "Dessert" -> resources.getStringArray(R.array.dessert)
            else -> arrayOf()
        }
        recyclerview.adapter = CategoryAdapter(dishes) {
            onSetCategory(it)
        }

    }

    private fun onSetCategory(name: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("title", name)
        intent.putExtra("desc", "test")
        startActivity(intent)
    }
}

