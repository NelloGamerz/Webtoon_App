//package com.example.webtooninfoapp
//
//import android.content.res.ColorStateList
//import android.graphics.Color
//import android.os.Bundle
//import android.widget.*
//import androidx.appcompat.app.AppCompatActivity
//import com.bumptech.glide.Glide
//import com.bumptech.glide.load.resource.bitmap.CenterCrop
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import com.bumptech.glide.request.RequestOptions
//import com.bumptech.glide.load.resource.bitmap.RoundedCorners
//import kotlinx.coroutines.flow.firstOrNull
//
//class WebtoonDetailActivity : AppCompatActivity() {
//
//    private lateinit var webtoonDatabase: WebtoonDatabase
//    private lateinit var webtoonDao: WebtoonDao
//    private lateinit var webtoonImageView: ImageView
//    private lateinit var webtoonTitleTextView: TextView
//    private lateinit var webtoonDescriptionTextView: TextView
//    private lateinit var addToFavoritesButton: Button
//    private lateinit var ratingBar: RatingBar
//    private lateinit var averageRatingTextView: TextView
//
//    private var webtoonId: Int = 0
//    private var isFavorite: Boolean = false
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_webtoon_detail)
//
//        // Initialize database and DAO
//        webtoonDatabase = WebtoonDatabase.getDatabase(this)
//        webtoonDao = webtoonDatabase.webtoonDao()
//
//        // Initialize views
//        webtoonImageView = findViewById(R.id.webtoonDetailImage)
//        webtoonTitleTextView = findViewById(R.id.webtoonDetailTitle)
//        webtoonDescriptionTextView = findViewById(R.id.webtoonDetailDescription)
//        addToFavoritesButton = findViewById(R.id.addToFavoritesButton)
//        ratingBar = findViewById(R.id.ratingBar)
//        averageRatingTextView = findViewById(R.id.averageRating)
//
//        val title = intent.getStringExtra("title")!!
//        val description = intent.getStringExtra("description")!!
//        val imageUrl = intent.getStringExtra("imageUrl")!!
//
//        // Set data in the views
//        webtoonTitleTextView.text = title
//        webtoonDescriptionTextView.text = description
//        Glide.with(this)
//            .load(imageUrl)
//            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(25))) // Combine CenterCrop with RoundedCorners
//            .placeholder(R.drawable.image)
//            .into(webtoonImageView)
//
//        // Fetch webtoon data from database
//        fetchWebtoonData(title)
//
//        // Handle favorites logic
//        addToFavoritesButton.setOnClickListener {
//            if (isFavorite) {
//                removeWebtoonFromFavorites(webtoonId, title)
//            } else {
//                val favoriteWebtoon = FavoriteWebtoonEntity(webtoonId = webtoonId, title = title, description = description, imageUrl = imageUrl)
//                saveWebtoonToFavorites(favoriteWebtoon)
//            }
//        }
//
//        ratingBar.progressTintList = ColorStateList.valueOf(resources.getColor(R.color.gold, null))
//        ratingBar.progressBackgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.darkGray, null))
//
//        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
//            CoroutineScope(Dispatchers.IO).launch {
//                webtoonDao.updateUserRating(webtoonId, rating)
//                val avgRating = webtoonDao.getAverageRating(webtoonId).firstOrNull()
//                withContext(Dispatchers.Main) {
//                    averageRatingTextView.text = "Average Rating: ${avgRating?.toString() ?: "N/A"}"
//                }
//            }
//        }
//
//        // Display the average rating
//        displayAverageRating()
//    }
//
//    private fun fetchWebtoonData(title: String) {
//        CoroutineScope(Dispatchers.IO).launch {
//            val webtoon = webtoonDao.getWebtoonById(webtoonId)
//            webtoon?.let {
//                withContext(Dispatchers.Main) {
//                    ratingBar.rating = it.userRating
//                }
//            }
//
//            // Check if the webtoon is already in favorites
//            val favoriteWebtoon = webtoonDao.getFavoriteWebtoonByTitle(title)
//            isFavorite = favoriteWebtoon != null
//            withContext(Dispatchers.Main) {
//                addToFavoritesButton.text = if (isFavorite) "Remove from Favorites" else "Add to Favorites"
//            }
//        }
//    }
//
//    private fun saveWebtoonToFavorites(favoriteWebtoon: FavoriteWebtoonEntity) {
//        CoroutineScope(Dispatchers.IO).launch {
//            webtoonDao.addFavoriteWebtoon(favoriteWebtoon)
//            withContext(Dispatchers.Main) {
//                isFavorite = true
//                addToFavoritesButton.text = "Remove from Favorites"
//            }
//        }
//    }
//
//    private fun removeWebtoonFromFavorites(webtoonId: Int, title: String) {
//        CoroutineScope(Dispatchers.IO).launch {
//            webtoonDao.deleteFavoriteWebtoon(webtoonId)
//            withContext(Dispatchers.Main) {
//                isFavorite = false
//                addToFavoritesButton.text = "Add to Favorites"
//            }
//        }
//    }
//
//    private fun displayAverageRating() {
//        CoroutineScope(Dispatchers.IO).launch {
//            webtoonDao.getAverageRating(webtoonId).collect { avgRating ->
//                withContext(Dispatchers.Main) {
//                    averageRatingTextView.text = "Average Rating: ${avgRating?.toString() ?: "N/A"}"
//                }
//            }
//        }
//    }
//}

package com.example.webtooninfoapp

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

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
        Glide.with(this)
            .load(imageUrl)
            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(25)))
            .placeholder(R.drawable.image)
            .into(webtoonImageView)

        // Fetch webtoon data from database
        fetchWebtoonData(title)

        // Handle favorites logic
        addToFavoritesButton.setOnClickListener {
            if (isFavorite) {
                removeWebtoonFromFavorites(webtoonId, title)
            } else {
                val favoriteWebtoon = FavoriteWebtoonEntity(webtoonId = webtoonId, title = title, description = description, imageUrl = imageUrl)
                saveWebtoonToFavorites(favoriteWebtoon)
            }
        }

        ratingBar.progressTintList = ColorStateList.valueOf(resources.getColor(R.color.gold, null))
        ratingBar.progressBackgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.darkGray, null))

        // Set rating listener
        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            updateRatingAndDisplayAverage(rating)
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
                isFavorite = true
                addToFavoritesButton.text = "Remove from Favorites"
            }
        }
    }

    private fun removeWebtoonFromFavorites(webtoonId: Int, title: String) {
        CoroutineScope(Dispatchers.IO).launch {
            webtoonDao.deleteFavoriteWebtoon(webtoonId)
            withContext(Dispatchers.Main) {
                isFavorite = false
                addToFavoritesButton.text = "Add to Favorites"
            }
        }
    }

    private fun updateRatingAndDisplayAverage(newRating: Float) {
        // Update rating in the database and refresh average rating
        CoroutineScope(Dispatchers.IO).launch {
            // First, update the webtoon rating in the database
            webtoonDao.updateWebtoonRating(webtoonId, newRating)

            // Then, fetch the updated average rating
            val averageRating = webtoonDao.getAverageRating(webtoonId).firstOrNull()

            withContext(Dispatchers.Main) {
                // Update the UI with the new average rating
                val ratingText = if (averageRating != null) "%.1f".format(averageRating) else "%.1f".format(newRating)
                averageRatingTextView.text = "Average Rating: $ratingText"
            }
        }
    }

    private fun displayAverageRating() {
        CoroutineScope(Dispatchers.IO).launch {
            val averageRating = webtoonDao.getAverageRating(webtoonId).firstOrNull()

            withContext(Dispatchers.Main) {
                val ratingText = if (averageRating != null) "%.1f".format(averageRating) else "N/A"
                averageRatingTextView.text = "Average Rating: $ratingText"
            }
        }
    }


}

