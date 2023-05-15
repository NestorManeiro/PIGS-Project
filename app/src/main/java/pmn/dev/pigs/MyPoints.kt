package pmn.dev.pigs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar


class MyPoints : AppCompatActivity() {

    private var points = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypoints)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "RideMate"

        val pointsTextView: TextView = findViewById(R.id.pointsTextView)
        pointsTextView.text = "Puntos: $points"

        val rewardButton1: Button = findViewById(R.id.rewardButton1)
        val rewardButton2: Button = findViewById(R.id.rewardButton2)
        val rewardButton3: Button = findViewById(R.id.rewardButton3)

        rewardButton1.setOnClickListener {
            if (points >= 150) {
                points -= 150
                pointsTextView.text = "Puntos: $points"
            } else {
                Toast.makeText(this, "No tienes suficientes puntos para canjear esta recompensa", Toast.LENGTH_SHORT).show()
            }
        }
        rewardButton2.setOnClickListener {
            if (points >= 500) {
                points -= 500
                pointsTextView.text = "Puntos: $points"
            } else {
                Toast.makeText(this, "No tienes suficientes puntos para canjear esta recompensa", Toast.LENGTH_SHORT).show()
            }
        }
        rewardButton3.setOnClickListener {
            if (points >= 1000) {
                points -= 1000
                pointsTextView.text = "Puntos: $points"
            } else {
                Toast.makeText(this, "No tienes suficientes puntos para canjear esta recompensa", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item0 -> {
                val nextpage = Intent(this, MainActivity::class.java)
                startActivity(nextpage)
                finish()
                return true
            }
            R.id.menu_item1 -> {
                val nextpage = Intent(this, MyTrips::class.java)
                startActivity(nextpage)
                finish()
                return true
            }
            R.id.menu_item2 -> {
                val nextpage = Intent(this, MyPoints::class.java)
                startActivity(nextpage)
                finish()
                return true
            }
            R.id.menu_item3 -> {
                val nextpage = Intent(this, LoginActivity::class.java)
                startActivity(nextpage)
                finish()
                return true
            }
            // Agrega más casos aquí si tienes más elementos de menú
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
