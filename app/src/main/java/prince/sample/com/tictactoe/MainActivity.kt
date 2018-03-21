package prince.sample.com.tictactoe

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var activePlayer: Int = 1

    private val row1 = ArrayList<Int>()
    private val row2 = ArrayList<Int>()
    private val row3 = ArrayList<Int>()
    private val col1 = ArrayList<Int>()
    private val col2 = ArrayList<Int>()
    private val col3 = ArrayList<Int>()
    private val diag1 = ArrayList<Int>()
    private val diag2 = ArrayList<Int>()

    private var autoPlay = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        autoPlay = intent.getBooleanExtra("autoplay", false)

        Collections.addAll(row1, 1, 2, 3)
        Collections.addAll(row2, 4, 5, 6)
        Collections.addAll(row3, 7, 8, 9)
        Collections.addAll(col1, 1, 4, 7)
        Collections.addAll(col2, 2, 5, 8)
        Collections.addAll(col3, 3, 6, 9)
        Collections.addAll(diag1, 1, 5, 9)
        Collections.addAll(diag2, 3, 5, 7)
    }


    /**
     * Method handling button click
     */
    fun btnClick(view: View) {
        val btnSelected = view as Button
        var cellId = 0

        when (btnSelected.id) {
            R.id.btn1 -> cellId = 1
            R.id.btn2 -> cellId = 2
            R.id.btn3 -> cellId = 3
            R.id.btn4 -> cellId = 4
            R.id.btn5 -> cellId = 5
            R.id.btn6 -> cellId = 6
            R.id.btn7 -> cellId = 7
            R.id.btn8 -> cellId = 8
            R.id.btn9 -> cellId = 9
        }

        playGame(cellId, btnSelected)

    }

    /**
     * Method to select the clicked cell
     */
    private fun playGame(cellId: Int, btnSelected: Button) {

        if (activePlayer == 1) {
            btnSelected.text = "X"
            btnSelected.setBackgroundResource(R.color.blue)
            player1.add(cellId)
            activePlayer = 2
            if (autoPlay)
                autoPlay()
        } else {
            btnSelected.text = "O"
            btnSelected.setBackgroundResource(R.color.darkgreen)
            player2.add(cellId)
            activePlayer = 1
        }

        btnSelected.isEnabled = false

        checkWinner()
    }

    /**
     * Method checking the winner of the game
     */
    private fun checkWinner() {
        var winner = -1

        if (player1.containsAll(row1) || player1.containsAll(row2) || player1.containsAll(row3) ||
                player1.containsAll(col1) || player1.containsAll(col2) || player1.containsAll(col3) ||
                player1.containsAll(diag1) || player1.containsAll(diag2))
            winner = 1

        if (player2.containsAll(row1) || player2.containsAll(row2) || player2.containsAll(row3) ||
                player2.containsAll(col1) || player2.containsAll(col2) || player2.containsAll(col3) ||
                player2.containsAll(diag1) || player2.containsAll(diag2))
            winner = 2


        if (winner != -1)
            Toast.makeText(this, "Winner is Player $winner", Toast.LENGTH_SHORT).show()
    }


    /**
     * Method handling the auto play part of the game
     */
    private fun autoPlay() {
        val emptyCells = ArrayList<Int>()

        for (cell in 1..9) {
            if (!(player1.contains(cell) || player2.contains(cell)))
                emptyCells.add(cell)
        }

        val size = emptyCells.size

        if (size > 0) {
            val r = Random()
            val ranIndex = r.nextInt(size - 0) + 0

            val cellId = emptyCells[ranIndex]
            val btnSelected: Button?

            btnSelected = when (cellId) {
                1 -> btn1
                2 -> btn2
                3 -> btn3
                4 -> btn4
                5 -> btn5
                6 -> btn6
                7 -> btn7
                8 -> btn8
                9 -> btn9
                else -> btn1

            }

            playGame(cellId, btnSelected)
        }

    }

    /**
     * Method to reset the game
     */
    fun resetClick(view: View) {
        player1.clear()
        player2.clear()

        val r = resources
        val name = packageName

        for (i in 1..9) {
            val id = r.getIdentifier("btn" + i, "id", name)
            val btn = findViewById<Button>(id)
            btn.setBackgroundResource(R.color.white)
            btn.text = ""
            btn.isEnabled = true
        }

        activePlayer = 1
    }
}