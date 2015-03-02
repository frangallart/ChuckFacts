package com.example.fran.chuckfacts.controllers;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.fran.chuckfacts.R;

/**
 * Classe Dialeg
 * Created by Fran on 27/02/2015.
 */
public class Dialeg extends DialogFragment {

    private String titol, missatge;

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public void setMissatge(String missatge) {
        this.missatge = missatge;
    }


    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(missatge).setTitle(titol)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO : No implementat
                    }
                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO : No implementat
            }
        });

        return builder.create();
    }
}

