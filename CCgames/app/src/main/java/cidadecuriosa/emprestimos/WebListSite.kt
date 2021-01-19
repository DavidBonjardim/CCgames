package cidadecuriosa.emprestimos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class WebListSite : AppCompatActivity() {
    var webView : WebView? = null

    var gameUrl : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        setContentView(R.layout.activity_web_list_site)

        webView = findViewById(R.id.webViewSite)

        gameUrl = "https://www.cidadecuriosa.org"

        gameUrl?.let{
            webView?.loadUrl(it)
        }

        val webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                url?.let { view?.loadUrl(it) }
                return true
            }
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            webView?.webViewClient = webViewClient
        }

    }
}