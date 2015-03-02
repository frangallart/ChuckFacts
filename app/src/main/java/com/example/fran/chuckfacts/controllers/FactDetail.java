package com.example.fran.chuckfacts.controllers;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.fran.chuckfacts.R;
import com.example.fran.chuckfacts.models.Fact;

/**
 * Classe FactDetail
 * Classe que model la vista dels detalls d'un fact
 * Created by Fran on 27/02/2015.
 */
public class FactDetail extends ActionBarActivity {

    private Fact dades;
    private ShareActionProvider mShareActionProvider;

    /**
     * Mètode que s'executa en crear l'activitat
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fact_detail);

        // Activar el retorn de layout
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Obtenir dades
        dades = (Fact) getIntent().getExtras().getSerializable("fact");

        // Obtenir objectes de la GUI
        TextView lblTitol = (TextView) findViewById(R.id.txtTitol);
        TextView lblContingut = (TextView) findViewById(R.id.txtContingut);
        WebView gif = (WebView) findViewById(R.id.webViewGif);

        // Assignar dades a la GUI
        lblTitol.setText(dades.getTitle());
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Saddlebag Black.ttf");
        lblTitol.setTypeface(font);
        lblContingut.setText(dades.getContent());
        WebSettings settings = gif.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        gif.loadUrl("file:///android_asset/" + dades.getGif());
    }

    /**
     * Mètode que crea el menú d'opcions
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_fact, menu);

        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.item_menu_share);

        // Fetch and store ShareActionProvider
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        mShareActionProvider.setShareIntent(getDefaultIntent());

        // Return true to display menu
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

        if (id == R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Missatge que s'enviarà al compartir
     */
    private Intent getDefaultIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, dades.getContent());
        return intent;
    }
}
