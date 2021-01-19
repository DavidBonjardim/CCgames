package cidadecuriosa.emprestimos

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import java.util.*
import java.util.concurrent.TimeUnit

class RegisterEmprestimoActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    //class que da notify ao utilizador de eventos que acontecem
    lateinit var notificationManager: NotificationManager

    //channel
    lateinit var notificationChannel: NotificationChannel

    //Ajuda a acessar features da notificaçao
        lateinit var builder: NotificationCompat.Builder

    //ID do channel
    private var channelId = "cidadecuriosa.emprestimos"

    //nome do channel
    private var channelName = "Sucesso"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        //Data base e reference
        database = FirebaseDatabase.getInstance()
        reference = database.getReference("Users")

        setContentView(R.layout.activity_register_emprestimo)

        var emprestimo : Emprestimo

        val emprestimoName = findViewById<EditText>(R.id.editTextTextGame)
        val emprestimoRequesitante = findViewById<EditText>(R.id.editTextTextPersonName)
        val emprestimoEmail = findViewById<EditText>(R.id.editTextTextEmailAddress)
        //Põe o email igual ao email que foi introduzido
        emprestimoEmail.setText(emailUser.toString())
        val emprestimoMobilePhone = findViewById<EditText>(R.id.editTextPhone)
        val buttonSave = findViewById<Button>(R.id.buttonReqGuardar)



        buttonSave.setOnClickListener(){

            val nameGame = emprestimoName.text.toString()
            val requesitante = emprestimoRequesitante.text.toString()
            val email = emprestimoEmail.text.toString()
            val mobilePhone = emprestimoMobilePhone.text.toString()

            if(TextUtils.isEmpty(nameGame)){
                Toast.makeText(this, "Insira o nome do jogo", Toast.LENGTH_LONG).show()
            }
            else if(TextUtils.isEmpty(requesitante)){
                Toast.makeText(this, "Insira o seu nome ", Toast.LENGTH_LONG).show()
            }
            else if(TextUtils.isEmpty(email)){
                Toast.makeText(this, "Insira o seu email", Toast.LENGTH_LONG).show()
            }
            else if(TextUtils.isEmpty(mobilePhone)){
                Toast.makeText(this, "Insira o seu número do telemóvel", Toast.LENGTH_LONG).show()
            }
            else {

                //Criação do id
                val auth = Firebase.auth
                val itemId = auth.currentUser?.uid

                //Data de hoje
                val today = Calendar.getInstance()
                val todaySave = Date(today.timeInMillis)

                //Data de 7 dias a partir de hoje
                val dateMax = Calendar.getInstance()
                dateMax.add(Calendar.HOUR, 168)
                val dateMaxSave = Date(dateMax.timeInMillis)

                //Caso ainda nao tenha feito o 1º request
                if(emprestimoList[0].name == "Volte ao menu"){

                    emprestimo = Emprestimo(nameGame, requesitante, email, mobilePhone, todaySave, dateMaxSave)
                    reference.child(itemId.toString()).child("userEmprestimo1").setValue(emprestimo)

                    emprestimoList[0] = emprestimo

                    val intent = Intent(this, MainMenuActivity::class.java)
                    startActivity(intent)

                    notificationSucessRequest()

                }
                //Caso ja tenha feito o 2º request mas ja tenha feito o 1º request
                else if(emprestimoList[1].name == "Volte ao menu"){

                    //dá valor ao emprestimo com o que foi escrito pelo utilizador
                    emprestimo = Emprestimo(nameGame, requesitante, email, mobilePhone, today.time, dateMax.time)
                    reference.child(itemId.toString()).child("userEmprestimo2").setValue(emprestimo)

                    emprestimoList[1] = emprestimo

                    val intent = Intent(this, MainMenuActivity::class.java)
                    startActivity(intent)

                    notificationSucessRequest()

                }
                //Caso ja tenha feito o 3º request mas ja tenha feito o 1º e 2º request
                else if(emprestimoList[2].name == "Volte ao menu"){

                    emprestimo = Emprestimo(nameGame, requesitante, email, mobilePhone, today.time, dateMax.time)
                    reference.child(itemId.toString()).child("userEmprestimo3").setValue(emprestimo)

                    emprestimoList[2] = emprestimo

                    val intent = Intent(this, MainMenuActivity::class.java)
                    startActivity(intent)

                    notificationSucessRequest()

                }
                //Caso ja tenha feito o 3º request mas ja tenha feito o 1º, 2º e 3º request
                else if(emprestimoList[3].name == "Volte ao menu"){

                    emprestimo = Emprestimo(nameGame, requesitante, email, mobilePhone, today.time, dateMax.time)
                    reference.child(itemId.toString()).child("userEmprestimo4").setValue(emprestimo)

                    emprestimoList[3] = emprestimo

                    val intent = Intent(this, MainMenuActivity::class.java)
                    startActivity(intent)

                    notificationSucessRequest()

                } else {
                    //Caso ja n possa fazer mais requests = lista cheia
                    Toast.makeText(this,"Não pode ter mais do que 4 jogos requesitados ao mesmo tempo", Toast.LENGTH_LONG).show()
                }

            }

        }
    }

    //Funçao para dar a notificação ao utilizador
    private fun notificationSucessRequest(){

        //Inicializar notification Manager para o registar no sistema
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(this, LoginActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_UPDATE_CURRENT)

        //Verifica a versão corre este codigo
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            //Como so ha uma notificação, não é preciso muito mais do que isto
            notificationChannel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            //Regista o canal no sistema
            notificationManager.createNotificationChannel(notificationChannel)

            //Builder
            builder = NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.drawable.cc)
                    //Titulo
                    .setContentTitle("Ludoteca da Estufa")
                    //Texto
                    .setContentText("Jogo adicionado com sucesso")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    //Se carregar vai para o login
                    .setContentIntent(pendingIntent)
                    //apaga a notificação
                    .setAutoCancel(true)

            with(NotificationManagerCompat.from(this)) {
                //id pode ser um número qualquer para já pois não será problema
                notify(12, builder.build())
            }

        }

    }

}