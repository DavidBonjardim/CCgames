package cidadecuriosa.emprestimos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class EmprestimoDetailActivity : AppCompatActivity() {
    companion object {
        const val NAME = "name"
        const val REQUESITANTE = "requesitante"
        const val EMAIL = "email"
        const val MOBILEPHONE = "mobilephone"
        const val DATE = "date"
        const val DATEMAX = "datemax"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        setContentView(R.layout.activity_emprestimo_detail)

        val name = intent.getStringExtra(NAME)
        val requesitante = intent.getStringExtra(REQUESITANTE)
        val email = intent.getStringExtra(EMAIL)
        val mobilePhone = intent.getStringExtra(MOBILEPHONE)
        val date = intent.getLongExtra(DATE, 0)
        val dateMax = intent.getLongExtra(DATEMAX, 0)

        val textViewDetailName = findViewById<TextView>(R.id.textViewDetailName)
        val textViewDetailRequesitante = findViewById<TextView>(R.id.textViewDetailRequesitante)
        val textViewDetailEmail= findViewById<TextView>(R.id.textViewDetailEmail)
        val textViewDetailMobilePhone = findViewById<TextView>(R.id.textViewDetailMobilePhone)
        val textViewDetailDate = findViewById<TextView>(R.id.textViewDetailDate)
        val textViewDetailDateMax = findViewById<TextView>(R.id.textViewDetailDateLimit)

        textViewDetailName.text = name
        textViewDetailRequesitante.text = requesitante
        textViewDetailEmail.text = email
        textViewDetailMobilePhone.text = mobilePhone
        textViewDetailDate.text = Date(date).toString()
        textViewDetailDateMax.text = Date(dateMax).toString()

    }
}