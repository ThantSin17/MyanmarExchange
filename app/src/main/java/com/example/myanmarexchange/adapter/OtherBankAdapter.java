package com.example.myanmarexchange.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myanmarexchange.R;
import com.example.myanmarexchange.dto.Currency;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OtherBankAdapter extends RecyclerView.Adapter<OtherBankAdapter.OtherBankViewHolder>{
    ArrayList<Currency> currencies=new ArrayList<>();
    int [] imgList=new int[]{};

    public OtherBankAdapter(ArrayList<Currency> currencies, int[] imgList) {
        this.currencies = currencies;
        this.imgList = imgList;
    }

    public OtherBankAdapter(ArrayList<Currency> currencies) {
        this.currencies = currencies;
    }

    @NonNull
    @Override
    public OtherBankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.other_bank_items_layout, parent, false);
        return new OtherBankViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OtherBankViewHolder holder, int position) {
        Currency currency=currencies.get(position);
        holder.img.setImageResource(imgList[position]);
        holder.txtCountry.setText(currency.getCountryName());
        holder.txtCurrency.setText(currency.getCurrency());
        holder.txtBuyRate.setText(currency.getBuyRate());
        holder.txtSellRate.setText(currency.getSellRate());

    }

    @Override
    public int getItemCount() {
        return currencies.size();
    }

    public class OtherBankViewHolder extends RecyclerView.ViewHolder{


        private TextView txtCountry, txtCurrency, txtBuyRate,txtSellRate;
        private ImageView img;
        public OtherBankViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.id_img);
            txtCountry = itemView.findViewById(R.id.id_txt_countryName);
            txtCurrency = itemView.findViewById(R.id.id_txt_currency);
            txtBuyRate = itemView.findViewById(R.id.txt_buy_rate);
            txtSellRate=itemView.findViewById(R.id.txt_sell_rate);
        }
    }
}
