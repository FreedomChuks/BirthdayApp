package com.funworld.util;

import android.view.View;

import com.funworld.Adapter.BirthdayAdapter;
import com.funworld.Model.Birthday;
import com.funworld.R;
import com.google.android.material.snackbar.Snackbar;

public class snackbarhelper {

    public static void birthdayDeleted(View view, BirthdayAdapter birthdayAdapter,int pos) {
        Snackbar.make(view, birthdayAdapter.getPosAt(pos).getFirstName() + " " + view.getContext().getString(R.string.delete)
                , Snackbar.LENGTH_LONG).show();
    }
}
