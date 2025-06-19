package za.ac.iie.rosebankpraticumimad

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
//ST10466977 - DARIO GOURGEL
class DetailedView : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detailed_view)

        //Get data from MainActivity
        val arrSong = intent.getStringArrayListExtra("song") ?: ArrayList()
        val arrArtist = intent.getStringArrayListExtra("artist") ?: ArrayList()
        val arrRating = intent.getIntegerArrayListExtra("rating") ?: ArrayList()
        val arrComment = intent.getStringArrayListExtra("comment") ?: ArrayList()

        //Ui elements
        val btnPlaylist = findViewById<Button>(R.id.btnPlaylist)
        val btnReturn = findViewById<Button>(R.id.btnReturn)
        val btnAverage = findViewById<Button>(R.id.btnAverage)
        val tvDetails = findViewById<TextView>(R.id.tvDetails)
        val tvRate = findViewById<TextView>(R.id.tvRate)
        val tvAverage = findViewById<TextView>(R.id.tvAverage)
        val frameLayout = findViewById<View>(R.id.frameLayout)
        val tvCategory = findViewById<TextView>(R.id.tvCategory)
        val btnReturn2 = findViewById<Button>(R.id.btnReturn2)

        //Variables for display
        val builderPlaylist = StringBuilder()
        val builderAverage = StringBuilder()

        //btn Playlist clicked
        btnPlaylist.setOnClickListener {
            btnPlaylist.visibility = View.GONE
            btnReturn.visibility = View.GONE
            btnAverage.visibility = View.GONE
            frameLayout.visibility = View.VISIBLE
            tvDetails.visibility = View.VISIBLE
            tvRate.visibility = View.GONE
            tvAverage.visibility = View.GONE
            btnReturn2.visibility = View.VISIBLE
            tvCategory.text = "Playlist"

            //Displaying the playlist
            for (i in 0 until maxOf(arrSong.size, arrArtist.size, arrRating.size, arrComment.size)){
                val song = arrSong.getOrNull(i) ?: ""
                val artist = arrArtist.getOrNull(i) ?: ""
                val rating = arrRating.getOrNull(i)?.toString() ?: ""
                val comment = arrComment.getOrNull(i) ?: ""
                builderPlaylist.append("Song: $song\n",
                                       "Artist: $artist\n",
                                       "Rating: $rating\n",
                                       "Comment: $comment\n",
                                        "\n")

            }
            tvDetails.text = builderPlaylist.toString()
        }
        //btn Average clicked
        btnAverage.setOnClickListener {
            btnPlaylist.visibility = View.GONE
            btnReturn.visibility = View.GONE
            btnAverage.visibility = View.GONE
            frameLayout.visibility = View.VISIBLE
            tvDetails.visibility = View.VISIBLE
            tvRate.visibility = View.VISIBLE
            tvAverage.visibility = View.VISIBLE
            btnReturn2.visibility = View.VISIBLE
            tvCategory.text = "Average Rating"

            //Displaying Details (Only Name and Rating)
            for (i in 0 until maxOf(arrSong.size, arrRating.size)){
                val song = arrSong.getOrNull(i) ?: ""
                val rating = arrRating.getOrNull(i)?.toString() ?: ""
                builderAverage.append("Song: $song\n",
                                      "Rating: $rating",
                                      "\n")
            }
            tvDetails.text = builderAverage.toString()

            //Calculating the average with the rating array using for loop
            val average = arrRating.sum() / arrRating.size
            tvRate.text = average.toString()
        }
        //btn Return2 clicked
        btnReturn2.setOnClickListener {
            btnPlaylist.visibility = View.VISIBLE
            btnReturn.visibility = View.VISIBLE
            btnAverage.visibility = View.VISIBLE
            frameLayout.visibility = View.GONE
            tvDetails.visibility = View.GONE
            tvRate.visibility = View.GONE
            tvAverage.visibility = View.GONE
            btnReturn2.visibility = View.GONE
        }
        //btn Return clicked
        btnReturn.setOnClickListener {
            finish()
        }
    }
}