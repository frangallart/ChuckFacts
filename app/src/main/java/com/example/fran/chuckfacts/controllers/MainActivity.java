package com.example.fran.chuckfacts.controllers;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.fran.chuckfacts.R;
import com.example.fran.chuckfacts.dao.FactsSQLiteHelper;

/**
 * Classe MainActivity
 * Created by Fran on 27/02/2015.
 */
public class MainActivity extends Activity {

    private FactsSQLiteHelper factsHelper;
    private SQLiteDatabase db;

    /**
     * Mètode que s'executa en crear l'activitat
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // crear l'objecte que crea a la connexió amb la BD
        factsHelper = new FactsSQLiteHelper(this, "ChuckFacts.db", null, 1);
        // obtenir l'objecte BD
        db = factsHelper.getWritableDatabase();

        // recuperar els controls
        TextView menuPrincipalFont = (TextView) findViewById(R.id.txtViewMainTitol);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Saddlebag Black.ttf");
        menuPrincipalFont.setTypeface(font);

        // TODO : Fer més atractius els botons d'inici, the facts i history
    }

    /**
     * Mètode que dirigeix als fets
     */
    public void goFacts(View view) {
        Intent intent = new Intent(this, FactsListActivity.class);
        startActivity(intent);
    }

    /**
     * Mètode que dirigeix a la història
     */
    public void goHistory(View view) {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }
}
