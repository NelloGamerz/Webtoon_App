package com.example.webtooninfoapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesActivity : AppCompatActivity() {
    private lateinit var webtoonDatabase: WebtoonDatabase
    private lateinit var webtoonDao: WebtoonDao
    private lateinit var favoritesRecyclerView: RecyclerView
    private lateinit var favoritesAdapter: WebtoonAdapter

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        favoritesRecyclerView = findViewById(R.id.favoritesRecyclerView)
        favoritesRecyclerView.layoutManager = LinearLayoutManager(this)

        webtoonDatabase = WebtoonDatabase.getDatabase(this)
        webtoonDao = webtoonDatabase.webtoonDao()

        // Observe the favorites data
        observeFavorites()
    }

    private fun FavoriteWebtoonEntity.toWebtoonEntity(): WebtoonEntity {
        return WebtoonEntity(
            id = this.webtoonId,
            title = this.title,
            description = this.description,
            imageUrl = this.imageUrl
        )
    }


    private fun observeFavorites() {
        CoroutineScope(Dispatchers.IO).launch {
            webtoonDao.getFavoriteWebtoons().collect { favoriteWebtoons ->
                val webtoonEntities = favoriteWebtoons.map { it.toWebtoonEntity() } // Convert the list
                withContext(Dispatchers.Main) {
                    favoritesAdapter = WebtoonAdapter(webtoonEntities) { webtoon ->
                        // Handle the click action here
                        val intent = Intent(this@FavoritesActivity, WebtoonDetailActivity::class.java).apply {
                            putExtra("title", webtoon.title)
                            putExtra("description", webtoon.description)
                            putExtra("imageUrl", webtoon.imageUrl)
                        }
                        startActivity(intent)
                    }
                    favoritesRecyclerView.adapter = favoritesAdapter
                }
            }
        }
    }


}