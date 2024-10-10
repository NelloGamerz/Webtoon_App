package com.example.webtooninfoapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WebtoonDetailActivity : AppCompatActivity() {

    private lateinit var webtoonDatabase: WebtoonDatabase
    private lateinit var webtoonDao: WebtoonDao
    private lateinit var webtoonImageView: ImageView
    private lateinit var webtoonTitleTextView: TextView
    private lateinit var webtoonDescriptionTextView: TextView
    private lateinit var addToFavoritesButton: Button
    private lateinit var ratingBar: RatingBar
    private lateinit var averageRatingTextView: TextView

    private var webtoonId: Int = 0
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webtoon_detail)

        // Initialize database and DAO
        webtoonDatabase = WebtoonDatabase.getDatabase(this)
        webtoonDao = webtoonDatabase.webtoonDao()

        // Initialize views
        webtoonImageView = findViewById(R.id.webtoonDetailImage)
        webtoonTitleTextView = findViewById(R.id.webtoonDetailTitle)
        webtoonDescriptionTextView = findViewById(R.id.webtoonDetailDescription)
        addToFavoritesButton = findViewById(R.id.addToFavoritesButton)
        ratingBar = findViewById(R.id.ratingBar)
        averageRatingTextView = findViewById(R.id.averageRating)

        val title = intent.getStringExtra("title")!!
        val description = intent.getStringExtra("description")!!
        val imageUrl = intent.getStringExtra("imageUrl")!!

        // Set data in the views
        webtoonTitleTextView.text = title
        webtoonDescriptionTextView.text = description
        Glide.with(this).load(imageUrl).into(webtoonImageView)

        // Fetch webtoon data from database
        fetchWebtoonData(title)

        // Handle favorites logic
        addToFavoritesButton.setOnClickListener {
            if (isFavorite) {
                removeWebtoonFromFavorites(webtoonId)
            } else {
                val favoriteWebtoon = FavoriteWebtoonEntity(webtoonId = webtoonId, title = title, description = description, imageUrl = imageUrl)
                saveWebtoonToFavorites(favoriteWebtoon)
            }
        }

        // Handle rating logic
        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            saveRatingForWebtoon(rating)
        }

        // Display the average rating
        displayAverageRating()
    }

    private fun fetchWebtoonData(title: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val webtoon = webtoonDao.getWebtoonById(webtoonId)
            webtoon?.let {
                withContext(Dispatchers.Main) {
                    ratingBar.rating = it.userRating
                }
            }

            // Check if the webtoon is already in favorites
            val favoriteWebtoon = webtoonDao.getFavoriteWebtoonByTitle(title)
            isFavorite = favoriteWebtoon != null
            withContext(Dispatchers.Main) {
                addToFavoritesButton.text = if (isFavorite) "Remove from Favorites" else "Add to Favorites"
            }
        }
    }

    private fun saveWebtoonToFavorites(favoriteWebtoon: FavoriteWebtoonEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            webtoonDao.addFavoriteWebtoon(favoriteWebtoon)
            withContext(Dispatchers.Main) {
                Toast.makeText(this@WebtoonDetailActivity, "${favoriteWebtoon.title} added to favorites", Toast.LENGTH_SHORT).show()
                isFavorite = true
                addToFavoritesButton.text = "Remove from Favorites"
            }
        }
    }

    private fun removeWebtoonFromFavorites(webtoonId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            webtoonDao.deleteFavoriteWebtoon(webtoonId)
            withContext(Dispatchers.Main) {
                Toast.makeText(this@WebtoonDetailActivity, "Webtoon removed from favorites", Toast.LENGTH_SHORT).show()
                isFavorite = false
                addToFavoritesButton.text = "Add to Favorites"
            }
        }
    }

    private fun saveRatingForWebtoon(userRating: Float) {
        CoroutineScope(Dispatchers.IO).launch {
            webtoonDao.rateWebtoon(webtoonId, userRating)
        }
    }

    private fun displayAverageRating() {
        CoroutineScope(Dispatchers.IO).launch {
            webtoonDao.getAverageRating(webtoonId).collect { avgRating ->
                withContext(Dispatchers.Main) {
                    averageRatingTextView.text = "Average Rating: ${avgRating ?: "N/A"}"
                }
            }
        }
    }
}
