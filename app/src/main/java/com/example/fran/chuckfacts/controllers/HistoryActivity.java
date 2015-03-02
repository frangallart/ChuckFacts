package com.example.fran.chuckfacts.controllers;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.fran.chuckfacts.R;

/**
 * Classe HistoryActivity
 * Created by Fran on 27/02/2015.
 */
public class HistoryActivity extends ActionBarActivity {

    private TextView titolHistoria;

    /**
     * Mètode que s'executa en crear l'activitat
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Activar el retorn de layout
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        titolHistoria = (TextView) findViewById(R.id.txtTitolHistoria);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Saddlebag Black.ttf");
        titolHistoria.setTypeface(font);
    }

    /**
     * Mètode que crea el menú d'opcions
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_history, menu);
        return true;
    }

    /**
     * Mètode que controla la selecció dels items del menú d'opcions
     */
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

        if (id == R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
