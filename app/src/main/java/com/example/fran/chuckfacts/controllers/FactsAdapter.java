package com.example.fran.chuckfacts.controllers;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.fran.chuckfacts.R;
import com.example.fran.chuckfacts.models.Fact;

import java.util.ArrayList;

/**
 * Classe FactsAdapter
 * Created by Fran on 27/02/2015.
 */
public class FactsAdapter extends ArrayAdapter<Fact> {

    private Context context;
    private ArrayList<Fact> dades;
    private Typeface font;

    /**
     * Constructor amb paràmetres
     *
     * @param context
     * @param dades
     * @param font
     */
    public FactsAdapter(Context context, ArrayList<Fact> dades, Typeface font) {
        super(context, R.layout.activity_fact_title, dades);
        this.context = context;
        this.dades = dades;
        this.font = font;
    }

    /**
     * Mètode que s'executa automàticament segons les dades que conté l'array 'dades'
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        View element = convertView;

        Fact f = getItem(position);

        if (element == null) {
            // Genera un layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            element = inflater.inflate(R.layout.activity_fact_title, null);
        }

        TextView lblTitol = (TextView) element.findViewById(R.id.txtTitolListFacts);
        lblTitol.setText(f.getTitle());
        lblTitol.setTypeface(font);

        return element;
    }

    /**
     * Sobreescriptura del mètode getCount que indica quantes dades gestiona
     * l'adaptador.
     */
    @Override
    public int getCount() {
        return dades.size();
    }

    /**
     * Sobreescriptura del mètode getItem que retorna l'objecte que ocupa la
     * posició indicada amb el paràmetre.
     */
    @Override
    public Fact getItem(int position) {
        return dades.get(position);
    }

    /**
     * Sobreescriptura del mètode getItemId que retorna l'id de l'objecte
     * que ocupa la posició indicad amb el paràmetre.
     */
    @Override
    public long getItemId(int position) {
        return getItem(position).getCodi();
    }
}
