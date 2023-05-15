package pmn.dev.pigs

import TripAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pmn.dev.pigs.model.Trip

class mytrips : AppCompatActivity() {

    private val tripArray = ArrayList<Trip>()
    private lateinit var adapter: TripAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mytrips)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "RideMate"
        // Configurar el RecyclerView con el adaptador y los datos de tripArray
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TripAdapter(this, tripArray)
        tripArray.addAll(adapter.getTripArray())

        recyclerView.adapter = adapter

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
                val nextpage = Intent(this, mytrips::class.java)
                startActivity(nextpage)
                finish()
                return true
            }
            R.id.menu_item2 -> {
                val nextpage = Intent(this, mypoints::class.java)
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