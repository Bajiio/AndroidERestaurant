package fr.isen.cousseau.androiderestaurant

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler

/*data class Plats(val name: String, val price: String,val description: String, val categorie: String, val id: Int ) {
}*/

class CategoryAdapter(private val platsList: Array<String>): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

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
        holder.textView.text = ItemsViewModel
        Log.v("CategoryAdapter", "onBindViewHolder"+ ItemsViewModel)

    }

    override fun getItemCount(): Int {
        return platsList.size
    }

    class CategoryViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
    }
}
