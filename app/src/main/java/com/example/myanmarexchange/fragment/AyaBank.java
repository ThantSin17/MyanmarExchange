package com.example.myanmarexchange.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myanmarexchange.JSONDownloader;
import com.example.myanmarexchange.R;
import com.example.myanmarexchange.adapter.OtherBankAdapter;
import com.example.myanmarexchange.adapter.SnackBarAdapter;
import com.example.myanmarexchange.dto.Currency;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * A simple {@link Fragment} subclass.
 */
public class AyaBank extends Fragment {
   // private SnackBarAdapter snackBarAdapter;
    RecyclerView recyclerView;
    OtherBankAdapter adapter;
    ProgressBar progressBar;
    LinearLayout relatedView,noInternet;
    TextView txtDate;
    Button btnConnection;
    ArrayList<Currency> currencies = new ArrayList<>();
    Snackbar snackbar;
    int[] imgList = new int[]{R.drawable.ic1, R.drawable.ic2,  R.drawable.ic3};
private Bundle state=null;
    public AyaBank() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("create view","create view");
        return inflater.inflate(R.layout.fragment_aya_bank, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpViews(view);
        Log.d("on v create","on view create");
           // snackbar=Snackbar.make(getView(), "Can't load data aya....", Snackbar.LENGTH_INDEFINITE);
            hideRelatedViews();
            doEverything(view);
            currencies = getCurrency();
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            adapter = new OtherBankAdapter(getCurrency(), imgList);
            recyclerView.setAdapter(adapter);
            btnConnection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    doEverything(view);

                }
            });


    }

    private void doEverything(View view){
        if (isOnline()){
            noInternet.setVisibility(View.GONE);
            hideRelatedViews();
            new JsonFetch().execute("https://networkingstone.000webhostapp.com/bank/aya.php");
        }
        else {
            progressBar.setVisibility(View.GONE);
            noInternet.setVisibility(View.VISIBLE);
        }

    }

    private ArrayList<Currency> getCurrency() {

        ArrayList<Currency> currencies = new ArrayList<>();
        currencies.add(new Currency("United State Dollar", "USD", "1,500", "1,300"));
        currencies.add(new Currency("Euro", "EUR", "50,00", "21,00"));
        currencies.add(new Currency("Singapore Dollar", "SGD", "50,00", "1,200"));

       // currencies.add(new Currency("Thai Baht", "THB", "500", "200"));
        return currencies;
    }

    private void setUpViews(View view) {
        recyclerView = view.findViewById(R.id.kbz_recycler);
        progressBar = view.findViewById(R.id.progress_bar);
        relatedView = view.findViewById(R.id.related_layout);
        txtDate = view.findViewById(R.id.txt_date);
        noInternet=view.findViewById(R.id.no_internet);
        btnConnection=view.findViewById(R.id.btn_connection);
    }

    private void hideRelatedViews() {
        progressBar.setVisibility(View.VISIBLE);
        relatedView.setVisibility(View.GONE);
    }

    private void showRelatedViews() {
        progressBar.setVisibility(View.GONE);
        relatedView.setVisibility(View.VISIBLE);
    }

    public class JsonFetch extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... str) {
            return JSONDownloader.download(str[0]);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            processJson(s);

        }
    }

    private void processJson(String str) {

            try {
                JSONObject obj = new JSONObject(str);
                txtDate.setText(obj.getString("date"));
                for (int i = 0; i < currencies.size(); i++) {
                    Currency currency = currencies.get(i);
                    String rates = obj.getString(currency.getCurrency());
                    StringTokenizer tokenizer = new StringTokenizer(rates, ",");
                    String buyRate = tokenizer.nextToken();
                    String sellRate = tokenizer.nextToken();
                    currencies.get(i).setBuyRate(buyRate);
                    currencies.get(i).setSellRate(sellRate);

                }
                adapter = new OtherBankAdapter(currencies, imgList);
                showRelatedViews();
                adapter.notifyDataSetChanged();

                recyclerView.setAdapter(adapter);

            } catch (JSONException e) {
                //showSnackBar();
                doEverything(getView());
                e.printStackTrace();
            }


    }
    private boolean isOnline() {
        ConnectivityManager manager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            // Network is present and connected
            isAvailable = true;
        }
        return isAvailable;
    }
}


