package fr.isen.cousseau.androiderestaurant

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.cousseau.androiderestaurant.data.model.Category
import fr.isen.cousseau.androiderestaurant.data.model.Plat



class CategoryAdapter(private var platsList: Array<Plat>, private val intentFun: (Plat) -> Unit): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        val ItemsViewModel = platsList[position]

        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.name_fr
        holder.price.text = ItemsViewModel.prices[0].price
        if(ItemsViewModel.images[0]!= "")
        {
            Picasso.get().load(ItemsViewModel.images[0]).into(holder.imageView)
        }

        Log.v("URI_IMAGE", holder.imageView.toString())

        holder.cardLinear.setOnClickListener{
            intentFun(ItemsViewModel)
        }

    }

    override fun getItemCount(): Int {
        return platsList.size
    }

    fun updateData(filter: Category?) {
        Log.i("DATA CATEGORY", filter.toString())
        if (filter != null) {
            platsList = filter.items
        }
        Log.i("UPDATED PLATS", platsList[0].toString())
        notifyDataSetChanged()
    }

    class CategoryViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        val cardLinear: ConstraintLayout= itemView.findViewById(R.id.CardLinear)
        val price: TextView = itemView.findViewById(R.id.price)
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
    }
}

