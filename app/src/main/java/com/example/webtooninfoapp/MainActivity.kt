package com.example.webtooninfoapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var webtoonRecyclerView: RecyclerView
    private lateinit var webtoonAdapter: WebtoonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webtoonRecyclerView = findViewById(R.id.webtoonRecyclerView)

        // Sample webtoon data
        val webtoonList = listOf(
            Webtoon(
                "Webtoon 1",
                "This is a brief description of Webtoon 1.",
                "https://example.com/image1.jpg"
            ),
            Webtoon(
                "Webtoon 2",
                "This is a brief description of Webtoon 2.",
                "https://example.com/image2.jpg"
            ),
            Webtoon(
                "Webtoon 3",
                "This is a brief description of Webtoon 3.",
                "https://example.com/image3.jpg"
            ),
            Webtoon(
                "Webtoon 4",
                "This is a brief description of Webtoon 3.",
                "https://example.com/image3.jpg"
            )

        )

        // Convert Webtoon list to WebtoonEntity list
        val webtoonEntityList = webtoonList.map { webtoon ->
            WebtoonEntity(
                title = webtoon.title,
                description = webtoon.description,
                imageUrl = webtoon.imageUrl
            )

        }
        webtoonAdapter = WebtoonAdapter(webtoonEntityList) { webtoonEntity ->
            val intent = Intent(this, WebtoonDetailActivity::class.java).apply {
                putExtra("webtoonId", webtoonEntity.id)
                putExtra("title", webtoonEntity.title)
                putExtra("description", webtoonEntity.description)
                putExtra("imageUrl", webtoonEntity.imageUrl)
            }
            startActivity(intent)
        }
        webtoonRecyclerView.adapter = webtoonAdapter

        val favoritesButton: FloatingActionButton = findViewById(R.id.favoritesButton)
        favoritesButton.setOnClickListener {
            val intent = Intent(this, FavoritesActivity::class.java)
            startActivity(intent)
        }
    }
}