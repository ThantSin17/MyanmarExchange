package com.example.myanmarexchange;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myanmarexchange.adapter.PageAdapter;
import com.example.myanmarexchange.fragment.AyaBank;
import com.example.myanmarexchange.fragment.CentralBank;
import com.example.myanmarexchange.fragment.KbzBank;
import com.example.myanmarexchange.fragment.MobBank;
import com.example.myanmarexchange.fragment.MwdBank;
import com.example.myanmarexchange.fragment.YomaBank;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
PageAdapter pageAdapter;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Toolbar toolbar = findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);

//
        // Get the ViewPager and set it's PageAdapter so that it can display items
        pageAdapter=new PageAdapter(getSupportFragmentManager());
        pageAdapter.addFragment(new CentralBank(),"Central Bank");
       pageAdapter.addFragment(new KbzBank(),"KBZ Bank");
        pageAdapter.addFragment(new AyaBank(),"AYA Bank");
        pageAdapter.addFragment(new MobBank(),"MOB Bank");
        pageAdapter.addFragment(new YomaBank(),"YOMA Bank");
        pageAdapter.addFragment(new MwdBank(),"MWD Bank");
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(pageAdapter);
        viewPager.setOffscreenPageLimit(6);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.home_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.about:
                //Toast.makeText(getApplicationContext(), "about", Toast.LENGTH_SHORT).show();
                final AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setMessage("App version\n" +
                        " version 1\n" +
                        "\n" +
                        "App Details\n" +
                        " If you are looking for user friendly and no ads myanmar banks exchange rate, this app is for you.\n" +
                        " You can easily see and compare exchange rates of Central bank, KBZ bank, AYA bank, YOMA bank, MOB bank, MWD bank.\n" +
                        "\n" +
                        "Developer\n" +
                        " STONE")
                        .setCancelable(false)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                alertDialog.hide();
                            }
                        });

                builder.setTitle("About App");
                alertDialog=builder.create();
                alertDialog.show();
                alertDialog.setTitle("About App");

                break;
            case R.id.update:
               // Toast.makeText(getApplicationContext(), "update", Toast.LENGTH_SHORT).show();
//                if (isOnline()) {
//                   // new CheckUpdate().execute();
//                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://networkingstone.000webhostapp.com/update.php?id=1"));
//                    startActivity(browserIntent);
//                }
//                else {
//                    Toast.makeText(getApplicationContext(), "No internet connection!!!!", Toast.LENGTH_SHORT).show();
//                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public class CheckUpdate extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {

            return JSONDownloader.download("https://networkingstone.000webhostapp.com/bank/update.json");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            int versionCode=BuildConfig.VERSION_CODE;
            try {
                JSONObject obj=new JSONObject(s);
                final String link=obj.getString("link");
                int updateCode=obj.getInt("versionCode");
                if (updateCode<=versionCode){
                    Toast.makeText(getApplicationContext(), "No Update is available", Toast.LENGTH_SHORT).show();
                }
                else {
                     AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage(obj.getString("message"))
                            .setCancelable(false)
                            .setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                   // alertDialog.hide();
                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                                    startActivity(browserIntent);
                                }
                            });
                    builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            alertDialog.hide();
                        }
                    });

                    builder.setTitle("About App");
                    alertDialog=builder.create();
                    alertDialog.show();
                    alertDialog.setTitle(obj.getString("title"));

                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Check your connection and Try Again", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }


        }
    }
    private boolean isOnline() {
        ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            // Network is present and connected
            isAvailable = true;
        }
        return isAvailable;
    }
}
