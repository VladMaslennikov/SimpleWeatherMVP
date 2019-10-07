package com.example.simpleweathermvp.ui.home

import android.app.SearchManager
import android.content.Context
import android.database.MatrixCursor
import android.os.Bundle
import android.provider.BaseColumns
import android.view.Menu
import android.view.MenuItem

import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.cursoradapter.widget.SimpleCursorAdapter

import com.example.simpleweathermvp.R
import com.example.simpleweathermvp.entity.City
import com.example.simpleweathermvp.model.CityModel
import com.example.simpleweathermvp.model.network.GooglePlacesApi

import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : AppCompatActivity(), HomeView {

    private val PLACE_ID_COLUM: String = "place_id"
    private val CITY_COLUM: String = "city"
    private val COUNTRY_COLUM: String = "country"
    private val SUGGESTION_CULUM: Array<String> = arrayOf(BaseColumns._ID, PLACE_ID_COLUM, CITY_COLUM, COUNTRY_COLUM)

    private val presenter by lazy {
        HomePresenter(CityModel(GooglePlacesApi.create()))
    }
    val colums: Array<String> = arrayOf(CITY_COLUM, COUNTRY_COLUM)
    val itemIds: IntArray = intArrayOf(android.R.id.text1, android.R.id.text2) // массив id view из разметки

    private val suggestionAdapter by lazy {
        SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_2, null, colums, itemIds, 0)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        presenter.onViewCreated(this)

        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.user_info_menu, menu)

        val menuItem: MenuItem = menu!!.findItem(R.id.action_search)
        val searchView: SearchView = menuItem.actionView as SearchView

        val searchManager: SearchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        searchView.suggestionsAdapter = suggestionAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {return false}

            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) {
                    presenter.onSuggvestionQuery(query)
                }
                return true
            }

        })
        return true
    }

    override fun showSuggvestionCities(cities: List<City>) {
        val cursor = MatrixCursor(SUGGESTION_CULUM)

        cities.forEachIndexed { index, city ->
            val array: Array<String> = arrayOf(index.toString(), city.id, city.name, city.country)
            cursor.addRow(array)
        }

        suggestionAdapter.swapCursor(cursor)

    }
}
