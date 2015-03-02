package com.example.fran.chuckfacts.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fran.chuckfacts.R;
import com.example.fran.chuckfacts.models.Fact;

/**
 * Classe NewEditFactActivity
 * Created by Fran on 27/02/2015.
 */
public class NewEditFactActivity extends ActionBarActivity {

    private EditText editTextTitle;
    private EditText editTextContent;
    private long idFact;
    private Fact editFact;

    /**
     * Mètode que s'executa en crear l'activitat
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_fact);

        // Activar el retorn de layout
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Obtenir objectes de la GUI
        editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        editTextContent = (EditText) findViewById(R.id.editTextContent);

        // Per si es un fet nou
        editFact = null;
        idFact = -1;

        // Si s'envia un fet per editar
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            editFact = (Fact) getIntent().getExtras().getSerializable("edit_fact");
            idFact = editFact.getCodi();
            editTextTitle.setText(editFact.getTitle());
            editTextContent.setText(editFact.getContent());
        }
    }

    /**
     * Mètode que crea el menú d'opcions
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_fact, menu);
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
        if (id == R.id.home) {
            return true;
        }

        // Event clic botó Desar fet
        if (id == R.id.action_accept){
            saveFact();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Mètode desa el nou fact i torna a la llista de facts
     */
    public void saveFact() {

        if (comprovaDades()) {

            Fact nouFet = new Fact(idFact, editTextTitle.getText().toString(),
                    editTextContent.getText().toString(), "cn_chuck.gif");

            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("nouFact", nouFet);
            intent.putExtras(bundle);
            setResult(Activity.RESULT_OK, intent);

            editTextTitle.setText("");
            editTextContent.setText("");

            finish();
        }
    }

    /**
     * Mètode que comprova que els editText del nou fact continguin dades
     *
     * @return true si les comprovacions són vàlides, false en cas contrari
     */
    public boolean comprovaDades() {

        if (editTextTitle.length() > 0 && editTextContent.length() > 0) {
            return true;
        } else {
            Toast toastAvis = Toast.makeText(getApplicationContext(),
                    "S'han d'omplir tots els camps", Toast.LENGTH_SHORT);
            toastAvis.show();
            return false;
        }
    }
}
