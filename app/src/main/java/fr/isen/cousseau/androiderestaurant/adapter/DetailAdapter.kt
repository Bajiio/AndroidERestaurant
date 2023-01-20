package fr.isen.cousseau.androiderestaurant.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.cousseau.androiderestaurant.R

class DetailAdapter(private val images: Array<String>):RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.details_imageview, parent, false)

        return DetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val itemsViewModel = images[position]

        if (itemsViewModel!= "")
        {
            Picasso.get().load(itemsViewModel).into(holder.imageViewVP)
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class DetailViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageViewVP: ImageView = itemView.findViewById(R.id.imageViewVP)
    }
}