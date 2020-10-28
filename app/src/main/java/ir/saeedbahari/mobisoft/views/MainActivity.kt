package ir.saeedbahari.mobisoft.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import ir.saeedbahari.mobisoft.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    override fun onSupportNavigateUp() =
        findNavController(this, R.id.navHostFragment).navigateUp()
}