package com.example.myanmarexchange.adapter;

import android.util.Log;

import com.google.android.material.snackbar.Snackbar;

import androidx.fragment.app.Fragment;

public class SnackBarAdapter{
    private static final String TAG = SnackBarAdapter.class.getName();
    private Snackbar snackbar;
    private Create instance;
    private boolean isMultiSnackbar;
    public interface Create {
        Snackbar create();
    }
    public SnackBarAdapter(Create instance) {
// why not pass in snackbar? coz snackbar.show will fail after 1st show (it multiple snackbar), thus need to recreate it
        snackbar = instance.create();
        this.instance = instance;
    }
    public void show(Fragment fragment) {
        if (fragment.getUserVisibleHint()) {
            snackbar.show();
        }
    }

    public void onSetUserVisibleHint(boolean isVisible) {
        if (isVisible) {
            if (snackbar == null) {
                snackbar = instance.create();
            }
            snackbar.show();
            Log.d(TAG, "showSnackbar="+snackbar.isShown());
// if snackbar.isShown()=false, if means multiple snackbar exist (might or might not be in same fragment)
/*
boolean isMultiSnackbar = !snackbar.isShown();
// the following is inaccurate when I manually dismiss one of the snackbar
// even when isShown()=true, the snackbar is not shown
if (isMultiSnackbar) {
snackbar = null;
snackbar = instance.create();
snackbar.show();
}
*/
        }
//        else {
//            Log.d(TAG, "dismissSnackbar");
//            snackbar.dismiss();
//// subsequent show will fail, make sure to recreate next
//            snackbar = null;
//        }
    }
}
