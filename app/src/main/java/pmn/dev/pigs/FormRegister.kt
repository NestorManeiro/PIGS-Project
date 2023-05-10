package pmn.dev.pigs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class FormRegister : AppCompatActivity() {
    private lateinit var register: TextView
    private lateinit var usernameform: EditText
    private lateinit var passwordform: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formregister)



        register = findViewById(R.id.register)
        usernameform = findViewById(R.id.usernameform)
        passwordform = findViewById(R.id.passwordform)

        setup();
    }


    private fun setup(){

        register.setOnClickListener(){

            if(usernameform.text.isNotEmpty() && passwordform.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(usernameform.text.toString(),passwordform.text.toString()).addOnCompleteListener {
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
