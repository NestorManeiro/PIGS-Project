package pmn.dev.pigs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import pmn.dev.pigs.R.*

class formlogin : AppCompatActivity() {
    private lateinit var login: TextView
    private lateinit var usernameform: EditText
    private lateinit var passwordform: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_formlogin)

        login = findViewById(id.login)
        usernameform = findViewById(id.usernameform)
        passwordform = findViewById(id.passwordform)

        setup();
    }
    private fun setup(){

        login.setOnClickListener(){
            if(usernameform.text.isNotEmpty() && passwordform.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(usernameform.text.toString(),passwordform.text.toString()).addOnCompleteListener {

                    if(it.isSuccessful){
                        val nextpage = Intent( this, MainActivity::class.java);
                        startActivity(nextpage);
                        finish();
                    } else {
                        val errorMessage = "${it.exception?.message}"
                        showAlert(errorMessage);
                    }
                }
            }
        }
    }

    private fun showAlert(message: String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(message)
        builder.setPositiveButton ("Accept",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}