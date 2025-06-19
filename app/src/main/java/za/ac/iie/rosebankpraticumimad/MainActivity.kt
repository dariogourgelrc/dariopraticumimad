package za.ac.iie.rosebankpraticumimad

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
//ST10466977 - DARIO GOURGEL
class MainActivity : AppCompatActivity() {

    //Initialize Arrays
    val arrSong = mutableListOf<String>()
    val arrArtist = mutableListOf<String>()
    val arrRating = mutableListOf<Int>()
    val arrComment = mutableListOf<String>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //Ui elements
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnList = findViewById<Button>(R.id.btnList)
        val btnExit = findViewById<Button>(R.id.btnExit)
        val etSong = findViewById<EditText>(R.id.etSong)
        val etArtist = findViewById<EditText>(R.id.etArtist)
        val etRating = findViewById<EditText>(R.id.etRating)
        val etComment = findViewById<EditText>(R.id.etComment)

        //Add data to playlist
        btnAdd.setOnClickListener {
            //Setting variables to the input fields
            val song = etSong.text.toString()
            val artist = etArtist.text.toString()
            val rating = etRating.text.toString().toInt()
            val comment = etComment.text.toString()

            //Add data to arrays
            arrSong.add(song)
            arrArtist.add(artist)
            arrRating.add(rating)
            arrComment.add(comment)

            //Error message if no input
            if (song.isEmpty() || artist.isEmpty() || rating.toString().isEmpty() || comment.isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
                } else {
                Toast.makeText(this, "Added to playlist", Toast.LENGTH_SHORT).show()

                //Clear input fields
                etSong.text.clear()
                etArtist.text.clear()
                etRating.text.clear()
                etComment.text.clear()
            }
        }
        //Go to Detailed View
        btnList.setOnClickListener {
            goToDetailedView()
        }
        //Exit App
        btnExit.setOnClickListener {
            finish()
        }
    }
    private fun goToDetailedView() {
        val detailedView = Intent(this, DetailedView::class.java)
        detailedView.putStringArrayListExtra("song", ArrayList(arrSong))
        detailedView.putStringArrayListExtra("artist", ArrayList(arrArtist))
        detailedView.putIntegerArrayListExtra("rating", ArrayList(arrRating))
        detailedView.putStringArrayListExtra("comment", ArrayList(arrComment))
        startActivity(detailedView)

    }
}