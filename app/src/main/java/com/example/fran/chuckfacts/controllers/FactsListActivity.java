package com.example.fran.chuckfacts.controllers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fran.chuckfacts.R;
import com.example.fran.chuckfacts.dao.FactsConversor;
import com.example.fran.chuckfacts.dao.FactsSQLiteHelper;
import com.example.fran.chuckfacts.models.Fact;

import java.util.ArrayList;

/**
 * Classe FactsListActivity
 * Created by Fran on 27/02/2015.
 */
public class FactsListActivity extends ActionBarActivity {

    private final static int ADD_FACT = 1;
    private final static int EDIT_FACT = 2;

    private ArrayList<Fact> dadesFacts;
    private FactsAdapter adapter;
    private FactsSQLiteHelper factsHelper;
    private FactsConversor factsConversor;
    private SQLiteDatabase db;
    private ListView listFactsView;
    private TextView lblNoData;
    private Typeface font;

    /**
     * Mètode que s'executa en crear l'activitat
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fact_list);

        // recuperar els controls
        listFactsView = (ListView) findViewById(R.id.listViewFacts);
        lblNoData = (TextView) findViewById(R.id.lblNoData);

        // vincular el menú contextual a la llista
        registerForContextMenu(listFactsView);

        // Activar el retorn de layout
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // seleccionar la font del títol
        font = Typeface.createFromAsset(getAssets(), "fonts/Saddlebag Black.ttf");

        // crear l'objecte que crea a la connexió amb la BD
        factsHelper = new FactsSQLiteHelper(this, "ChuckFacts.db", null, 1);
        // obtenir l'objecte BD
        db = factsHelper.getWritableDatabase();
        factsConversor = new FactsConversor(factsHelper);

        // Si s'ha obert correctament la BD
        if (db != null) {
            // actualitzar la llista
            refreshData();
            // Tancar la base de dades
            db.close();
        }
    }

    /*
    * Torna a executar la consulta i a enllaçar les dades
    */
    void refreshData() {
        dadesFacts = factsConversor.getAllAsList();

        // Control per si no hi han dades
        if (dadesFacts == null) {
            lblNoData.setHeight(100);
            lblNoData.setVisibility(lblNoData.VISIBLE);
            listFactsView.setVisibility(listFactsView.INVISIBLE);
        } else {
            lblNoData.setHeight(0);
            lblNoData.setVisibility(lblNoData.INVISIBLE);
            listFactsView.setVisibility(listFactsView.VISIBLE);

            adapter = new FactsAdapter(this, dadesFacts, font);
            listFactsView.setAdapter(adapter);

            listFactsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getBaseContext(), FactDetail.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("fact", adapter.getItem(position));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
    }

    /**
     * Mètode que crea el menú d'opcions
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_fact, menu);
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

        // Tornar a inici
        if (id == R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }

        // Afegir nou fet
        if (id == R.id.action_new) {
            Intent intent = new Intent(FactsListActivity.this, NewEditFactActivity.class);
            startActivityForResult(intent, ADD_FACT);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Mètode que espera el resultat
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Fact fet = (Fact) data.getExtras().getSerializable("nouFact");

        // Carrega el nou fet al listView
        if (requestCode == ADD_FACT && resultCode == RESULT_OK) {

            if (factsConversor.save(fet) != -1) {
                refreshData();
                Toast toastAvis = Toast.makeText(getApplicationContext(),
                        "El nou fet, " + fet.getTitle() + " , s'ha creat satisfactòriament",
                        Toast.LENGTH_SHORT);
                toastAvis.show();
            }
        }

        // Carrega el fet editat al listView
        if (requestCode == EDIT_FACT && resultCode == RESULT_OK) {

            if (factsConversor.edit(fet) != -1) {
                refreshData();
                Toast toastAvis = Toast.makeText(getApplicationContext(),
                        "El fet, " + fet.getTitle() + " , s'ha modificat satisfactòriament",
                        Toast.LENGTH_SHORT);
                toastAvis.show();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Mètode que crea el menú contextual d'un item de la llista
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Opcions");
        menu.add(0, 1, 0, "Eliminar");
        menu.add(0, 2, 1, "Editar");
    }

    /**
     * Mètode que controla la selecció d'un item seleccionat en el menú contextual d'un botó
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        // Item Eliminar seleccionat
        if (item.getTitle() == "Eliminar") {

            AlertDialog.Builder alerta = new AlertDialog.Builder(FactsListActivity.this);
            alerta.setTitle("Eliminar fet");
            alerta.setMessage("Estàs segur que vols eliminar aquest fet?");
            // Eliminar el fet si es fà clic al botó SI
            alerta.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int button) {

                    if (factsConversor.remove(dadesFacts.get(info.position))) {
                        Toast.makeText(getApplicationContext(), "S'ha eliminat el fet " +
                                dadesFacts.get(info.position).getTitle(), Toast.LENGTH_LONG).show();
                        refreshData();
                    }
                }
            });
            // No fer res si es es fà clic al botó NO
            alerta.setNegativeButton("No", null).show();

            // Item Editar seleccionat
        } else if (item.getTitle() == "Editar") {

            Fact editFact = new Fact(dadesFacts.get(info.position).getCodi(),
                    dadesFacts.get(info.position).getTitle(),
                    dadesFacts.get(info.position).getContent(),
                    dadesFacts.get(info.position).getGif());

            Intent intent = new Intent(FactsListActivity.this, NewEditFactActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("edit_fact", editFact);

            intent.putExtras(bundle);

            startActivityForResult(intent, EDIT_FACT);

        } else {
            return false;
        }
        return true;
    }
}
