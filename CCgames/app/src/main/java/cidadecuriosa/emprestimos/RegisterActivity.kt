package cidadecuriosa.emprestimos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.util.*


class RegisterActivity : AppCompatActivity() {

    private val TAG = "RegisterActivity"
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        setContentView(R.layout.activity_register)

        val username = findViewById<EditText>(R.id.username)
        val passwordText = findViewById<EditText>(R.id.password)
        val register = findViewById<Button>(R.id.register)
        val textViewLogin = findViewById<TextView>(R.id.textViewLogin)

        auth = Firebase.auth

        register.setOnClickListener {
            val email = username.text.toString().trim()
            val password = passwordText.text.toString().trim()

            when {
                email.isEmpty() -> {
                    Toast.makeText(this, "Introduza um email", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                password.isEmpty() -> {
                    Toast.makeText(this, "Introduza a password", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                password.length < 6 -> {
                    Toast.makeText(this, "Password deve ter pelo menos 6 carateres", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                else -> registUser(email, password)
            }
        }

        textViewLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registUser(email: String, password: String){

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        //Registado com sucesso
                        Log.d(TAG, "createUserWithEmail:success")

                        val uid = FirebaseAuth.getInstance().uid
                        val emprestimo1 = Emprestimo("Volte ao menu", "", "", "", null, null)
                        val emprestimo2 = Emprestimo("Volte ao menu", "", "", "", null, null)
                        val emprestimo3 = Emprestimo("Volte ao menu", "", "", "", null, null)
                        val emprestimo4 = Emprestimo("Volte ao menu", "", "", "", null, null)

                        val user = User(uid, email, password, emprestimo1, emprestimo2, emprestimo3, emprestimo4)
                        reference.child(uid!!).setValue(user)

                        emprestimoList.add(emprestimo1)
                        emprestimoList.add(emprestimo2)
                        emprestimoList.add(emprestimo3)
                        emprestimoList.add(emprestimo4)

                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)

                    } else {

                        //Se nao der para registar, mostra uma frase ao Utilizador
                        Toast.makeText(baseContext, "Registration failed.",
                                Toast.LENGTH_SHORT).show()

                    }
                }

    }

}
