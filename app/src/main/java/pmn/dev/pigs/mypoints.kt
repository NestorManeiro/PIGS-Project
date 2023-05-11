package pmn.dev.pigs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar

class mypoints : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypoints)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "RideMate"
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item0 -> {
                val nextpage = Intent( this, MainActivity::class.java);
                startActivity(nextpage);
                finish();
                return true
            }
            R.id.menu_item1 -> {
                val nextpage = Intent( this, mytrips::class.java);
                startActivity(nextpage);
                finish();
                return true
            }
            R.id.menu_item2 -> {
                val nextpage = Intent( this, mypoints::class.java);
                startActivity(nextpage);
                finish();
                return true
            }
            R.id.menu_item3 -> {
                val nextpage = Intent( this, LoginActivity::class.java);
                startActivity(nextpage);
                finish();
                return true
            }
            // Agrega más casos aquí si tienes más elementos de menú
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
