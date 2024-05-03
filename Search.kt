package com.example.i21_0846

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.SearchView

class Search : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if(query != null){
                    performSearch(query)
                    return true
                }
                return false

            }

            override fun onQueryTextChange(newText: String): Boolean {
                // Called when the user changes the search query text
                return false
            }
        })


    }

    private fun performSearch(query: String) {
        // Here, you can handle the search query as per your application's requirements.
        // You may perform a network/API call or search operation based on the query.

        // Assuming you navigate to the SearchResultActivity when the search is performed:
        val intent = Intent(this, SearchResult::class.java)
        intent.putExtra("query", query)
        startActivity(intent)
    }



}