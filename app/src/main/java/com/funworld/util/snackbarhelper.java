package com.funworld.util;

import android.view.View;

import com.funworld.Adapter.BirthdayAdapter;
import com.funworld.Model.Birthday;
import com.funworld.R;
import com.google.android.material.snackbar.Snackbar;

public class snackbarhelper {

    public static void birthdayDeleted(View view, BirthdayAdapter birthdayAdapter) {
        Snackbar.make(view, birthdayAdapter.getPosAt(birthdayAdapter.getItemCount()-1).getFirstName() + " " + view.getContext().getString(R.string.delete)
                , Snackbar.LENGTH_LONG).show();
    }
}
