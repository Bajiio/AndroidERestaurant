package fr.isen.cousseau.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val action= findViewById<Button>(R.id.Homebutton)
        action.setOnClickListener{
            Toast.makeText(this,"Hello World", Toast.LENGTH_SHORT).show()
            Snackbar.make(it,"Hello ISEN M1", Snackbar.LENGTH_SHORT).show()

        }
    }
}