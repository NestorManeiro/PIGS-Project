package pmn.dev.pigs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    private lateinit var logintxt: TextView
    private lateinit var registertxt: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        logintxt = findViewById(R.id.logintxt)
        registertxt = findViewById(R.id.registertxt)

        setup();
    }

    private fun setup(){
        logintxt.setOnClickListener{
            val nextpage = Intent( this, formlogin::class.java);
            startActivity(nextpage);
            finish();
        }

        registertxt.setOnClickListener{
            val nextpage = Intent( this, formregister::class.java);
            startActivity(nextpage);
            finish();
        }

    }
}