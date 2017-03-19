package com.intelligentz.skoolvan.view;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.intelligentz.skoolvan.R;
import com.intelligentz.skoolvan.adaptor.SearchSpinnerRecyclerAdaptor;
import com.intelligentz.skoolvan.constants.Data;
import com.intelligentz.skoolvan.model.SearchSet;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    AppCompatSpinner age_spinner;
    ImageView advanced_icon;
    View.OnClickListener advanced_listener;
    RecyclerView search_spinner_recyclerview;
    boolean advanced_visibility = false;
    private LinearLayoutManager search_spinner_layoutManager;
    private SearchSpinnerRecyclerAdaptor searchSpinnerRecyclerAdaptor;
    private ArrayList<SearchSet> searchList;
    private TextView add_more_btn;
    AppCompatCheckBox male_check;
    AppCompatCheckBox female_check;
    AppCompatCheckBox pick_check;
    AppCompatCheckBox drop_check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        configureRecyclerView();
        configureAppBar();
        configureSpinners();
        configureAdvancedButton();
        configureAddMoreBtn();
        configureGenderCheckBoxes();
        configureTurnCheckBoxes();
    }

    private void configureTurnCheckBoxes() {
        pick_check = (AppCompatCheckBox) findViewById(R.id.pick_check);
        drop_check = (AppCompatCheckBox) findViewById(R.id.drop_check);
    }

    private void configureGenderCheckBoxes() {
        male_check = (AppCompatCheckBox) findViewById(R.id.male_check);
        female_check = (AppCompatCheckBox) findViewById(R.id.female_check);
        male_check.setChecked(true);
        View.OnClickListener genderCheckListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.male_check){
                    male_check.setChecked(true);
                    female_check.setChecked(false);
                } else if (view.getId() == R.id.female_check) {
                    male_check.setChecked(false);
                    female_check.setChecked(true);
                }
            }
        };
        male_check.setOnClickListener(genderCheckListener);
        female_check.setOnClickListener(genderCheckListener);
    }

    private void configureAddMoreBtn() {
        add_more_btn = (TextView)findViewById(R.id.add_more_btn);
        add_more_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchList.add(new SearchSet());
                searchSpinnerRecyclerAdaptor.notifyItemInserted(searchList.size()-1);
            }
        });
    }

    private void configureSpinners() {
        age_spinner = (AppCompatSpinner) findViewById(R.id.input_age);
//        age_spinner.setTitle("Grade");
        ArrayAdapter ageAdaptor = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, Data.grades);
        age_spinner.setAdapter(ageAdaptor);
    }
    private void configureAdvancedButton(){
        final LinearLayout advance_layout = (LinearLayout) findViewById(R.id.advance_layout);
        final LinearLayout advanced_layout = (LinearLayout) findViewById(R.id.advanced_layout);
        advanced_listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (advanced_visibility){
                    advance_layout.setVisibility(View.GONE);
                    advanced_icon.setImageResource(android.R.drawable.ic_input_add);
                    advanced_visibility = false;
                }else {
                    advance_layout.setVisibility(View.VISIBLE);
                    advanced_visibility = true;
                    advanced_icon.setImageResource(android.R.drawable.ic_delete);
                }
            }
        };
        advanced_icon = (ImageView) findViewById(R.id.advanced_icon);
        advanced_layout.setOnClickListener(advanced_listener);
    }
    private void configureAppBar(){
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setBackgroundResource(0);
                    toolbar.setLogo(R.drawable.logo_color);
                    isShow = true;
                } else if(isShow) {
                    toolbar.setLogo(null);
                    collapsingToolbarLayout.setBackgroundResource(R.drawable.main);
                    collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });
    }
    private void configureRecyclerView(){
        search_spinner_recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        search_spinner_layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        search_spinner_recyclerview.setLayoutManager(search_spinner_layoutManager);
        searchList = new ArrayList();
        searchList.add(new SearchSet());
        searchSpinnerRecyclerAdaptor = new SearchSpinnerRecyclerAdaptor(searchList, this);
        search_spinner_recyclerview.setAdapter(searchSpinnerRecyclerAdaptor);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
