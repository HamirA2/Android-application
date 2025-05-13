package com.example.cs360projecttwo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.widget.EditText;

// Fragment for creating a dialog input for database values
public class EditDialogFragment extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Build the dialog using the login_dialog layout
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Set positive done button and on click listener
        builder.setView(inflater.inflate(R.layout.edit_dialog, null))
                .setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                // Set negative cancel button, canceling the dialog
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditDialogFragment.this.getDialog().cancel();
                    }
                });

        // Return the builder
        return builder.create();
    }
}