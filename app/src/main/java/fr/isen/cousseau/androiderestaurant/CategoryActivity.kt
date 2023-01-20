package fr.isen.cousseau.androiderestaurant

import com.android.volley.Request
import android.content.Intent
import android.icu.text.CaseMap.Title
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.cousseau.androiderestaurant.data.model.Data
import fr.isen.cousseau.androiderestaurant.data.model.Plat
import fr.isen.cousseau.androiderestaurant.databinding.ActivityCategoryBinding
import org.json.JSONException
import org.json.JSONObject
import java.util.Objects


class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding
    private var url="http://test.api.catering.bluecodegames.com/menu"
    private lateinit var adapter: CategoryAdapter

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
        binding.rvList.layoutManager = LinearLayoutManager(this)
        binding.rvList.adapter = CategoryAdapter(arrayOf()){
            onSetCategory(it)
        }
        addData()

//        val dishes = when (binding.Title.text) {
//            "Starter" -> resources.getStringArray(R.array.starter)
//            "Plat" -> resources.getStringArray(R.array.middle)
//            "Dessert" -> resources.getStringArray(R.array.dessert)
//            else -> arrayOf()
//        }
//        recyclerview.adapter =

    }

    private fun onSetCategory(plat: Plat) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("plat", plat)
        startActivity(intent)
    }

    private fun addData(){
        val queue=Volley.newRequestQueue(this)
        val jsonObjectID = JSONObject()
        val gson=Gson()


        try {
            jsonObjectID.put("id_shop",1)

        } catch (e: JSONException)
        {
            Log.e("ERROR", e.toString())
        }

        val req = JsonObjectRequest(Request.Method.POST, url, jsonObjectID, {
            response -> Log.i("API_data", response.toString())
            val dataFromJSON: Data= gson.fromJson(response.toString(),Data::class.java)
            Log.i("DATA_API",dataFromJSON.toString())
            binding.progressBar.visibility = View.GONE

            val catlist= dataFromJSON.data.firstOrNull() { it.name_fr == binding.Title.text }
            val adapter = binding.rvList.adapter as CategoryAdapter
            adapter.updateData(catlist)
        }, {
           errorData -> Log.e("ERROR", errorData.toString())
        })

        queue.add(req)

    }


}

