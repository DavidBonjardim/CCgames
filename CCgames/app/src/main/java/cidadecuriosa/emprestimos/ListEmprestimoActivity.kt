package cidadecuriosa.emprestimos

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import java.time.*
import java.util.*
import kotlin.collections.ArrayList

var emprestimoList : MutableList<Emprestimo> = ArrayList()

class MainActivity : AppCompatActivity() {

    lateinit var listViewEmprestimo : ListView
    lateinit var adapter : TempAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        listViewEmprestimo = findViewById(R.id.listView)
        adapter = TempAdapter()
        listViewEmprestimo.adapter = adapter

    }

    inner class TempAdapter : BaseAdapter() {

        @SuppressLint("ViewHolder")
        override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {

            val rowView = layoutInflater.inflate(R.layout.row_main, viewGroup, false)

            val textViewName = rowView.findViewById<TextView>(R.id.writeJogo)
            val textViewDateMax = rowView.findViewById<TextView>(R.id.writeDateMax)

            textViewName.text = emprestimoList[position].name
            textViewDateMax.text = emprestimoList[position].dateMax.toString()

            if(textViewDateMax.text == "null"){
                val textViewName1 = rowView.findViewById<TextView>(R.id.askJogo)
                val textViewName2 = rowView.findViewById<TextView>(R.id.askDataMax)
                textViewName1.text = "Quer requesitar?"
                textViewDateMax.text = ""
                textViewName2.text = ""

            }

            else {
                rowView.isClickable = true
                rowView.setOnClickListener(){
                    val intent = Intent(this@MainActivity, EmprestimoDetailActivity::class.java)
                    intent.putExtra(EmprestimoDetailActivity.NAME, emprestimoList[position].name)
                    intent.putExtra(
                            EmprestimoDetailActivity.REQUESITANTE,
                            emprestimoList[position].requesitante
                    )
                    intent.putExtra(EmprestimoDetailActivity.EMAIL, emprestimoList[position].email)
                    intent.putExtra(
                            EmprestimoDetailActivity.MOBILEPHONE,
                            emprestimoList[position].mobilePhone
                    )
                    intent.putExtra(EmprestimoDetailActivity.DATE, emprestimoList[position].date?.time)
                    intent.putExtra(EmprestimoDetailActivity.DATEMAX, emprestimoList[position].dateMax?.time)

                    startActivity(intent)
                }

            }

            return rowView
        }


        override fun getItem(position: Int): Any {
            return emprestimoList[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getCount(): Int {
            return emprestimoList.size
        }
    }

}