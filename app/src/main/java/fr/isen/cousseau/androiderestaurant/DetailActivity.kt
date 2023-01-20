package fr.isen.cousseau.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fr.isen.cousseau.androiderestaurant.data.model.Plat
import fr.isen.cousseau.androiderestaurant.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    lateinit var plat: Plat
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
        val viewPager = binding.DetailViewPager
        val adapter = DetailAdapter(plat.images)
        viewPager.adapter=adapter


    }
}