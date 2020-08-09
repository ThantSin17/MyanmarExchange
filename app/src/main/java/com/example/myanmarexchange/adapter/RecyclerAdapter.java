package com.example.myanmarexchange.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myanmarexchange.R;
import com.example.myanmarexchange.dto.Country;
import com.example.myanmarexchange.dto.Currency;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>{
    ArrayList<Currency> currencies=new ArrayList<>();
    private int[] imgList = new int[]{ R.drawable.ic1, R.drawable.ic2, R.drawable.ic3, R.drawable.ic4, R.drawable.ic5, R.drawable.ic6, R.drawable.ic7, R.drawable.ic8, R.drawable.ic9, R.drawable.ic10, R.drawable.ic11, R.drawable.ic12, R.drawable.ic13, R.drawable.ic14, R.drawable.ic15, R.drawable.ic16, R.drawable.ic17, R.drawable.ic18, R.drawable.ic19, R.drawable.ic20, R.drawable.ic21, R.drawable.ic22, R.drawable.ic23, R.drawable.ic24, R.drawable.ic25, R.drawable.ic26, R.drawable.ic27, R.drawable.ic28, R.drawable.ic29, R.drawable.ic30, R.drawable.ic31, R.drawable.ic32, R.drawable.ic33, R.drawable.ic34, R.drawable.ic35, R.drawable.ic36, R.drawable.ic37, R.drawable.ic38};
   // private String[] countryList=new String[]{"United State Dollar","Euro","Singapore Dollar","Pound Sterling","Swiss Franc","Japanese Yen","Australian Dollar","Bangladesh Taka","Brunei Dollar","Cambodian Riel","Canadian Dollar","Chinese Yuan","Hong Kong Dollar","Indian Rupee","Indonesian Rupiah","Korean Won","Lao Kip","Malaysian Ringgit","New Zealand Dollar","Pakistani Rupee", "Philippines Peso","Sri Lankan Rupee","Thai Baht","Vietnamese Dong","Brazilian Real","Czech Koruna","Danish Krone","Egyptian Pound","Israeli Shekel","Kenya Shilling","Kuwaiti Dinar","Nepalese Rupee","Norwegian Kroner","Russian Rouble","Saudi Arabian Riyal","Serbian Dinar","South Africa Rand","Swedish"};
//    private ArrayList<Country> countries=new ArrayList<Country>(){{
//        add(new Country("USD","United State Dollar"));
//        add(new Country("EUR","Euro"));
//        add(new Country("SGD","Singapore Dollar"));
//        add(new Country("GBP","Pound Sterling"));
//        add(new Country("CHF","Swiss Franc"));
//        add(new Country("JPY","Japanese Yen"));
//        add(new Country("AUD","Australian Dollar"));
//        add(new Country("BDT","Bangladesh Taka"));
//        add(new Country("BND","Brunei Dollar"));
//        add(new Country("KHR","Cambodian Riel"));
//        add(new Country("CAD","Canadian Dollar"));
//        add(new Country("CNY","Chinese Yuan"));
//        add(new Country("HKD","Hong Kong Dollar"));
//        add(new Country("INR","Indian Rupee"));
//        add(new Country("IDR","Indonesian Rupiah"));
//        add(new Country("KRW","Korean Won"));
//        add(new Country("LAK","Lao Kip"));
//        add(new Country("MYR","Malaysian Ringgit"));
//        add(new Country("NZD","New Zealand Dollar"));
//        add(new Country("PKR","Pakistani Rupee"));
//        add(new Country("PHP","Philippines Peso"));
//        add(new Country("LKR","Sri Lankan Rupee"));
//        add(new Country("THB","Thai Baht"));
//        add(new Country("VND","Vietnamese Dong"));
//        add(new Country("BRL","Brazilian Real"));
//        add(new Country("CZK","Czech Koruna"));
//        add(new Country("DKK","Danish Krone"));
//        add(new Country("EGP","Egyptian Pound"));
//        add(new Country("ILS","Israeli Shekel"));
//        add(new Country("KES","Kenya Shilling"));
//        add(new Country("KWD","Kuwaiti Dinar"));
//        add(new Country("NPR","Nepalese Rupee"));
//        add(new Country("NOK","Norwegian Kroner"));
//        add(new Country("RUB","Russian Rouble"));
//        add(new Country("SAR","Saudi Arabian Riyal"));
//        add(new Country("RSD","Serbian Dinar"));
//        add(new Country("ZAR","South Africa Rand"));
//        add(new Country("SEK","Swedish Krona"));}};

    public RecyclerAdapter(ArrayList<Currency> currencies) {
        this.currencies = currencies;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_layout, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Currency exchange = currencies.get(position);


            holder.img.setImageResource(imgList[position]);
            holder.txt_country.setText(exchange.getCountryName());
            holder.txt_currency.setText(exchange.getCurrency());
            holder.txt_rate.setText(exchange.getRate());



    }

    @Override
    public int getItemCount() {
        return currencies.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_country, txt_currency, txt_rate;
        private ImageView img;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.id_img);
            txt_country = itemView.findViewById(R.id.id_txt_countryName);
            txt_currency = itemView.findViewById(R.id.id_txt_currency);
            txt_rate = itemView.findViewById(R.id.id_txt_rates);
        }
    }
}
