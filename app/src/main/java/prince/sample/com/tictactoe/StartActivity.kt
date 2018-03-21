package prince.sample.com.tictactoe

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
    }

    /**
     * method to select the game type
     */
    fun gameSelect(view: View) {
        val btnSelect = view as Button
        val intent = Intent(this@StartActivity, MainActivity::class.java)
        when (btnSelect.id) {
            R.id.btnplayer -> intent.putExtra("autoplay", false)
            R.id.btn_autoplay -> intent.putExtra("autoplay", true)
        }
        startActivity(intent)
    }
}
