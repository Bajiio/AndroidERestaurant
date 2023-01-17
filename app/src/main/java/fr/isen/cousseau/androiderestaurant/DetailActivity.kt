package fr.isen.cousseau.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.cousseau.androiderestaurant.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        binding=ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = intent.getStringExtra("title")

        binding.DetailsTitle.text=intent.getStringExtra("title")
        binding.DetailDescription.text=intent.getStringExtra("desc")

    }
}