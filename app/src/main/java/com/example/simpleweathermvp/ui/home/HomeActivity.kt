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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleweathermvp.App

import com.example.simpleweathermvp.R
import com.example.simpleweathermvp.data.geteway.CityGeteway
import com.example.simpleweathermvp.entity.City
import com.example.simpleweathermvp.extension.inject

import kotlinx.android.synthetic.main.home_activity.*
import org.koin.core.parameter.parametersOf

class HomeActivity : AppCompatActivity(), HomeView {

    val presenter: HomePresenter by inject {
        parametersOf(this as HomeView)
    }

    private val PLACE_ID_COLUM: String = "place_id"
    private val CITY_COLUM: String = "city"
    private val COUNTRY_COLUM: String = "country"
    private val SUGGESTION_CULUM: Array<String> = arrayOf(BaseColumns._ID, PLACE_ID_COLUM, CITY_COLUM, COUNTRY_COLUM)


    val colums: Array<String> = arrayOf(CITY_COLUM, COUNTRY_COLUM)
    val itemIds: IntArray = intArrayOf(android.R.id.text1, android.R.id.text2) // массив id view из разметки

    private val suggestionAdapter by lazy {
        SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_2, null, colums, itemIds, 0)
    }

    private val cityAdapter = CityAdapter(onRemoveClicks = { city ->
        presenter.removeCity(city)
    })


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.home_activity)

        setSupportActionBar(toolbar)

        rv_cities.layoutManager = LinearLayoutManager(this)
        rv_cities.adapter = cityAdapter

        presenter.getAllCities()
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
        searchView.setOnSuggestionListener(object : SearchView.OnSuggestionListener{
            override fun onSuggestionSelect(position: Int): Boolean {
                val cursor = suggestionAdapter.cursor
                cursor.moveToPosition(position)

                val idSelected = cursor.getString(cursor.getColumnIndex(PLACE_ID_COLUM))
                val citySelected = cursor.getString(cursor.getColumnIndex(CITY_COLUM))
                val countrySelected = cursor.getString(cursor.getColumnIndex(COUNTRY_COLUM))

                presenter.savedCityDb(City(idSelected, citySelected, countrySelected))

                searchView.setQuery("", false) // Очищаю строку поиска
                searchView.clearFocus() // Убрал фокус чтобы скрыть клавиатуру
                menuItem.collapseActionView()
                return true
            }

            override fun onSuggestionClick(position: Int): Boolean {
                return onSuggestionSelect(position)
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

    override fun showListCicies(cities: List<City>) {
        cityAdapter.setItems(cities)
    }
}
