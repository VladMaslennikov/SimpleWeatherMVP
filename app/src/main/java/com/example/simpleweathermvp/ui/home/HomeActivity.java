package com.example.simpleweathermvp.ui.home;

import android.app.SearchManager;
import android.content.Context;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpleweathermvp.R;
import com.example.simpleweathermvp.entity.City;
import com.example.simpleweathermvp.model.CityModel;

import java.util.List;


public class HomeActivity extends AppCompatActivity implements HomeView {

    private final String PLACE_ID_COLUM = "place_id";
    private final String CITY_COLUM = "city";
    private final String COUNTRY_COLUM = "country";
    private final String[] SUGGESTION_CULUM = {BaseColumns._ID, PLACE_ID_COLUM, CITY_COLUM, COUNTRY_COLUM};

    HomePresenter presenter = new HomePresenter(new CityModel());

    private RecyclerView citiesList;
    private Toolbar toolbar;
    private CursorAdapter suggestionAdapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        presenter.onViewCreated(this);

        citiesList = findViewById(R.id.rv_cities);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        String[] colums = {CITY_COLUM, COUNTRY_COLUM};
        int[] itemIds = {android.R.id.text1, android.R.id.text2}; // массив id view из разметки
        suggestionAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_2, null, colums,itemIds, 0 );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_info_menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) menuItem.getActionView();

        final SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);// view передаю xml
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));  // view передаю xml

        searchView.setSuggestionsAdapter(suggestionAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String qwery) {
                presenter.onSuggvestionQwery(qwery);

                return true;
            }
        });



        return true;
    }

    @Override
    public void showSuggvestionCities(List<City> cities) {
        final MatrixCursor cursor = new MatrixCursor(SUGGESTION_CULUM);
        for (int index = 0; index < cities.size(); index++){
            City city = cities.get(index);
            cursor.addRow(new String[]{String.valueOf(index),city.getId(), city.getCity(), city.getCountry()});
        }
        suggestionAdapter.swapCursor(cursor);
    }

    @Override
    public void showCityList(List<City> cities) {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showEmptyView() {

    }
}
