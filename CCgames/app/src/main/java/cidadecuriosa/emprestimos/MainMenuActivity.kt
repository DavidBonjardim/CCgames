package cidadecuriosa.emprestimos

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        setContentView(R.layout.activity_main_menu)

        val buttonJogos = findViewById<Button>(R.id.buttonViewJogos)
        val buttonViewEmprestimos = findViewById<Button>(R.id.buttonViewEmprestimos)
        val buttonAddEmprestimos = findViewById<Button>(R.id.buttonAddEmprestimo)
        val buttonVisitSite = findViewById<Button>(R.id.buttonVisitSite)

        buttonJogos.setOnClickListener(){

            /* val intent = Intent(this, WebListGames::class.java)
            startActivity(intent) */

            //Abre na net envez de abrir na app
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://boardgamegeek.com/collection/user/LudotecadaEstufa")
            startActivity(openURL)
        }

        buttonViewEmprestimos.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        buttonAddEmprestimos.setOnClickListener(){
            val intent = Intent(this, RegisterEmprestimoActivity::class.java)
            startActivity(intent)
        }

        buttonVisitSite.setOnClickListener(){
            val intent = Intent(this, WebListSite::class.java)
            startActivity(intent)
        }

    }

}