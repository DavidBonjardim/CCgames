package cidadecuriosa.emprestimos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import java.net.HttpCookie.parse
import java.text.DateFormat
import java.time.Duration.parse
import java.time.LocalDate.parse
import java.time.ZonedDateTime.parse
import java.util.*
import java.util.Date.parse
import java.util.Locale.LanguageRange.parse
import java.util.logging.Level.parse

var emailUser : String? = null

class LoginActivity : AppCompatActivity() {

    private val TAG = "LoginActivity"
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

        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.username)
        val passwordText = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        val textViewRegister = findViewById<TextView>(R.id.textViewRegister)

        auth = Firebase.auth

        login.setOnClickListener {

            val email = username.text.toString()
            val password = passwordText.text.toString()

            when {
                email.isEmpty() -> {
                    Toast.makeText(this, "Introduza um email", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                password.isEmpty() -> {
                    Toast.makeText(this, "Introduza a password", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                else -> loginUser(email, password)
            }

        }

        textViewRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }

    private fun loginUser(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        //Log com sucesso

                        val userUID = auth.currentUser?.uid

                        //variavel geral para usar quando registo um emprestimo
                        emailUser = email

                        //Só se tiver vazia é que adiciona, se não teriamos uma lista com 2*4 = 8 que não é o suposto
                        if (emprestimoList.isEmpty()) {
                            getDateEmprestimo1(userUID!!)
                            getDateEmprestimo2(userUID)
                            getDateEmprestimo3(userUID)
                            getDateEmprestimo4(userUID)
                        }

                        val intent = Intent(this, MainMenuActivity::class.java)
                        startActivity(intent)
                    } else {
                        //Se nao der para logar, mostra uma frase ao Utilizador
                        Toast.makeText(
                                baseContext, "Email ou password está incorreta",
                                Toast.LENGTH_SHORT
                        ).show()
                    }
                }
    }


    private fun getDateEmprestimo4(userUID:String){
        //Aceder ao sitio certo
        database.reference
                .child("Users")
                .child(userUID)
                .child("userEmprestimo4")
                .addListenerForSingleValueEvent(object: ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        var map = snapshot.value as Map<*, *>
                        //var mapDate = snapshot.getValue()
                        var name = map["name"].toString()
                        var requesitante = map["requesitante"].toString()
                        var email = map["email"].toString()
                        var mobilePhone = map["mobilePhone"].toString()
                        /*var date : Long = map["date"] as Long
                        var date2 = Date(date)
                        var dateMax : Long = map["dateMax"] as Long
                        var dateMax2 = Date(dateMax) */

                        Log.d("LoginActivity", "$name")
                        Log.d("LoginActivity", "$requesitante")
                        Log.d("LoginActivity", "$email")
                        Log.d("LoginActivity", "$mobilePhone")

                        val dateMax = Calendar.getInstance()
                        dateMax.add(Calendar.HOUR, 168)
                        val dateMaxSave = Date(dateMax.timeInMillis)

                        if(name == "Volte ao menu"){
                            val emprestimo = Emprestimo("Volte ao menu", "", "", "", null, null)
                            emprestimoList.add(emprestimo)
                        }
                        else{
                            val emprestimo = Emprestimo(name, requesitante, email, mobilePhone, Date(), dateMaxSave)
                            emprestimoList.add(emprestimo)
                        }

                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })


    }

    private fun getDateEmprestimo3(userUID:String){
        database.reference
            .child("Users")
            .child(userUID)
            .child("userEmprestimo3")
            .addListenerForSingleValueEvent(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    var map = snapshot.value as Map<*, *>
                    //var mapDate = snapshot.getValue()
                    var name = map["name"].toString()
                    var requesitante = map["requesitante"].toString()
                    var email = map["email"].toString()
                    var mobilePhone = map["mobilePhone"].toString()
                    /*var date : Long = map["date"] as Long
                    var date2 = Date(date)
                    var dateMax : Long = map["dateMax"] as Long
                    var dateMax2 = Date(dateMax) */

                    Log.d("LoginActivity", "$name")
                    Log.d("LoginActivity", "$requesitante")
                    Log.d("LoginActivity", "$email")
                    Log.d("LoginActivity", "$mobilePhone")

                    val dateMax = Calendar.getInstance()
                    dateMax.add(Calendar.HOUR, 168)
                    val dateMaxSave = Date(dateMax.timeInMillis)

                    if(name == "Volte ao menu"){
                        val emprestimo = Emprestimo("Volte ao menu", "", "", "", null, null)
                        emprestimoList.add(emprestimo)
                    }
                    else{
                        val emprestimo = Emprestimo(name, requesitante, email, mobilePhone, Date(), dateMaxSave)
                        emprestimoList.add(emprestimo)
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                }
            })


    }

    private fun getDateEmprestimo2(userUID:String){
        database.reference
            .child("Users")
            .child(userUID)
            .child("userEmprestimo2")
            .addListenerForSingleValueEvent(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    var map = snapshot.value as Map<*, *>
                    //var mapDate = snapshot.getValue()
                    var name = map["name"].toString()
                    var requesitante = map["requesitante"].toString()
                    var email = map["email"].toString()
                    var mobilePhone = map["mobilePhone"].toString()
                    /*var date : Long = map["date"] as Long
                    var date2 = Date(date)
                    var dateMax : Long = map["dateMax"] as Long
                    var dateMax2 = Date(dateMax) */

                    Log.d("LoginActivity", "$name")
                    Log.d("LoginActivity", "$requesitante")
                    Log.d("LoginActivity", "$email")
                    Log.d("LoginActivity", "$mobilePhone")

                    val dateMax = Calendar.getInstance()
                    dateMax.add(Calendar.HOUR, 168)
                    val dateMaxSave = Date(dateMax.timeInMillis)

                    if(name == "Volte ao menu"){
                        val emprestimo = Emprestimo("Volte ao menu", "", "", "", null, null)
                        emprestimoList.add(emprestimo)
                    }
                    else{
                        val emprestimo = Emprestimo(name, requesitante, email, mobilePhone, Date(), dateMaxSave)
                        emprestimoList.add(emprestimo)
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                }
            })


    }

    private fun getDateEmprestimo1(userUID:String){
        database.reference
            .child("Users")
            .child(userUID)
            .child("userEmprestimo1")
            .addListenerForSingleValueEvent(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot){
                    var map = snapshot.value as Map<*, *>
                    //var mapDate = snapshot.getValue()
                    var name = map["name"].toString()
                    var requesitante = map["requesitante"].toString()
                    var email = map["email"].toString()
                    var mobilePhone = map["mobilePhone"].toString()
                    //val date = snapshot.child("date").getValue()

                    /*var date = map["date"].toString()
                    var date2 = Date(date)
                    var dateMax : Long = map["dateMax"] as Long
                    var dateMax2 = Date(dateMax) */

                    Log.d("LoginActivity", "$name")
                    Log.d("LoginActivity", "$requesitante")
                    Log.d("LoginActivity", "$email")
                    Log.d("LoginActivity", "$mobilePhone")

                    val dateMax = Calendar.getInstance()
                    dateMax.add(Calendar.HOUR, 168)
                    val dateMaxSave = Date(dateMax.timeInMillis)

                    if(name == "Volte ao menu"){
                        val emprestimo = Emprestimo("Volte ao menu", "", "", "", null, null)
                        emprestimoList.add(emprestimo)
                    }
                    else{
                        val emprestimo = Emprestimo(name, requesitante, email, mobilePhone, Date(), dateMaxSave)
                        emprestimoList.add(emprestimo)
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                }

            })


    }


}
