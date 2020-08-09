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
import com.example.myanmarexchange.adapter.RecyclerAdapter;
import com.example.myanmarexchange.dto.Country;
import com.example.myanmarexchange.dto.Currency;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * A simple {@link Fragment} subclass.
 */
public class CentralBank extends Fragment {


RecyclerView recyclerView;
RecyclerAdapter adapter;
LinearLayout layoutDate,relatedView,noInternet;
ProgressBar progressBar;
TextView txtDate;
Button btnConnection;
    private ArrayList<Country> countries=new ArrayList<Country>(){{
        add(new Country("USD","United State Dollar"));
        add(new Country("EUR","Euro"));
        add(new Country("SGD","Singapore Dollar"));
        add(new Country("GBP","Pound Sterling"));
        add(new Country("CHF","Swiss Franc"));
        add(new Country("JPY","Japanese Yen"));
        add(new Country("AUD","Australian Dollar"));
        add(new Country("BDT","Bangladesh Taka"));
        add(new Country("BND","Brunei Dollar"));
        add(new Country("KHR","Cambodian Riel"));
        add(new Country("CAD","Canadian Dollar"));
        add(new Country("CNY","Chinese Yuan"));
        add(new Country("HKD","Hong Kong Dollar"));
        add(new Country("INR","Indian Rupee"));
        add(new Country("IDR","Indonesian Rupiah"));
        add(new Country("KRW","Korean Won"));
        add(new Country("LAK","Lao Kip"));
        add(new Country("MYR","Malaysian Ringgit"));
        add(new Country("NZD","New Zealand Dollar"));
        add(new Country("PKR","Pakistani Rupee"));
        add(new Country("PHP","Philippines Peso"));
        add(new Country("LKR","Sri Lankan Rupee"));
        add(new Country("THB","Thai Baht"));
        add(new Country("VND","Vietnamese Dong"));
        add(new Country("BRL","Brazilian Real"));
        add(new Country("CZK","Czech Koruna"));
        add(new Country("DKK","Danish Krone"));
        add(new Country("EGP","Egyptian Pound"));
        add(new Country("ILS","Israeli Shekel"));
        add(new Country("KES","Kenya Shilling"));
        add(new Country("KWD","Kuwaiti Dinar"));
        add(new Country("NPR","Nepalese Rupee"));
        add(new Country("NOK","Norwegian Kroner"));
        add(new Country("RUB","Russian Rouble"));
        add(new Country("SAR","Saudi Arabian Riyal"));
        add(new Country("RSD","Serbian Dinar"));
        add(new Country("ZAR","South Africa Rand"));
        add(new Country("SEK","Swedish Krona"));}};

    public CentralBank() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_central_bank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpViews(view);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter=new RecyclerAdapter(getCurrency());
        recyclerView.setAdapter(adapter);
        hideRelatedViews();
        doEverything(view);
        btnConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doEverything(view);

            }
        });

    }
     ArrayList<Currency> getCurrency(){
        ArrayList<Currency> currencies=new ArrayList<>();
        currencies.add(new Currency("United State","USD","500"));
        currencies.add(new Currency("United State","USD","500"));
        currencies.add(new Currency("United State","USD","500"));
        currencies.add(new Currency("United State","USD","500"));
        currencies.add(new Currency("United State","USD","500"));
        currencies.add(new Currency("United State","USD","500"));
        return currencies;
    }
    private void hideRelatedViews(){
        progressBar.setVisibility(View.VISIBLE);
        relatedView.setVisibility(View.GONE);
    }
    private void showRelatedViews(){
        progressBar.setVisibility(View.GONE);
        relatedView.setVisibility(View.VISIBLE);
    }
    private void setUpViews(View view)
    {
        recyclerView=view.findViewById(R.id.recycler_view);
        layoutDate=view.findViewById(R.id.layout_date);
        txtDate=view.findViewById(R.id.txt_date);
        progressBar=view.findViewById(R.id.progress_bar);
        relatedView=view.findViewById(R.id.related_view);
        noInternet=view.findViewById(R.id.no_internet);
        btnConnection=view.findViewById(R.id.btn_connection);
    }
    public class JsonFetch extends AsyncTask<String,Void,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... str) {
//            String result=JSONDownloader.download(str[0]);
//            return result;
            StringBuffer result=new StringBuffer();
           // Toast.makeText(getContext(),"enter",Toast.LENGTH_LONG);
            try
            {

                HttpURLConnection httpConn = ((HttpURLConnection)new URL(str[0]).openConnection());
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(httpConn.getInputStream()));
                String line = null;
                while ((line = reader.readLine()) != null)
                    result.append(line).append("\n");

                reader.close();
                httpConn.disconnect();
                return result.toString();

            }
            catch (IOException e)
            {
              //  Toast.makeText(getContext(),e.toString(),Toast.LENGTH_LONG).show();

                e.printStackTrace();
                return "false";
            }

          //  return result.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

                processJson(s);

        }
    }

    private void processJson(String result){

            ArrayList<Currency> currencies = new ArrayList<>();
            try {
                JSONObject outObj = new JSONObject(result);
                txtDate.setText(parseTimestamp(outObj.getLong("timestamp")));
                JSONObject innObj = outObj.getJSONObject("rates");
                //Iterator<String> iterator=innObj.keys();
                for (Country country : countries) {

                    currencies.add(new Currency(country.getCountry(), country.getCur(), innObj.getString(country.getCur())));
                }
//            while (iterator.hasNext()){
//                String currency = iterator.next();
//                String rate = innObj.getString(currency);
//                currencies.add(new Currency(currency,currency,rate));
//            }
                adapter = new RecyclerAdapter(currencies);
                showRelatedViews();
                adapter.notifyDataSetChanged();

                recyclerView.setAdapter(adapter);
            } catch (JSONException e) {
                Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();

            }

    }
    private String parseTimestamp(long timestamp) {
        Date date = new Date(timestamp * 1000l);
        //  SimpleDateFormat format = new SimpleDateFormat("h:mm a, MMMM d, yyyy, EEEE");
        SimpleDateFormat format = new SimpleDateFormat("h:mm a, MMM d yyyy");
        return format.format(date);
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
    private void doEverything(View view){
        if (isOnline()){
            noInternet.setVisibility(View.GONE);
            hideRelatedViews();
            new JsonFetch().execute("https://forex.cbm.gov.mm/api/latest");
        }
        else {
            progressBar.setVisibility(View.GONE);
            noInternet.setVisibility(View.VISIBLE);
        }

    }
}
