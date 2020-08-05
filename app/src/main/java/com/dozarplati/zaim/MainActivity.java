package com.dozarplati.zaim;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.dozarplati.zaim.Net.Loader;
import com.dozarplati.zaim.models.DB;
import com.dozarplati.zaim.utils.AFURLS;
import com.dozarplati.zaim.utils.App;
import com.dozarplati.zaim.utils.SnackBarMessage;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);


        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_zaymi, R.id.navigation_cards, R.id.navigation_credit, R.id.navigation_zaymi_detail)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        setup();
        checkBar(navView);


        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            getDesc(bundle.getString("cat", "err"), bundle.getInt("pos", 0));
        }

    }

    void checkBar(BottomNavigationView nav){
        DB db = Loader.getDb();
        if(db != null){
            if(!db.getApp_config().getLoans_item().equals("1")){
                nav.getMenu().removeItem(R.id.navigation_zaymi);

                nav.setSelectedItemId(R.id.navigation_cards);
            }
            if(!db.getApp_config().getCards_item().equals("1")){
                nav.getMenu().removeItem(R.id.navigation_cards);
                nav.setSelectedItemId(R.id.navigation_credit);
            }
            if(!db.getApp_config().getCredits_item().equals("1")){
                nav.getMenu().removeItem(R.id.navigation_credit);
                nav.setSelectedItemId(R.id.navigation_zaymi);
            }
        }
    }

    void getDesc(String mode, int position){
        DB db = Loader.getDb();
        Bundle bundle = new Bundle();
        bundle.putInt("id", position);
        bundle.putString("mode", mode);
        switch (mode){
            case "zaym":
                if(db.getLoans().size() < position) {
                    SnackBarMessage.getMessage(findViewById(R.id.nav_host_fragment), "Нет соответствующей записи");
                    return;
                }
                bundle.putString("title", db.getLoans().get(position).getName());
                break;
            case "cardCredit":
                if(db.getCards_credit().size() < position){
                    SnackBarMessage.getMessage(findViewById(R.id.nav_host_fragment), "Нет соответствующей записи");
                    return;
                }
                bundle.putString("title", db.getCards_credit().get(position).getName());
                break;
            case "cardDebet":
                if(db.getCards_debit().size() < position){
                    SnackBarMessage.getMessage(findViewById(R.id.nav_host_fragment), "Нет соответствующей записи");
                    return;
                }
                bundle.putString("title", db.getCards_debit().get(position).getName());
                break;
            case "cardInstallment":
                if(db.getCards_installment().size() < position){
                    SnackBarMessage.getMessage(findViewById(R.id.nav_host_fragment), "Нет соответствующей записи");
                    return;
                }
                bundle.putString("title", db.getCards_installment().get(position).getName());
                break;
            case "Credit":
                if(db.getCredits().size() < position){
                    SnackBarMessage.getMessage(findViewById(R.id.nav_host_fragment), "Нет соответствующей записи");
                    return;
                }
                bundle.putString("title", db.getCredits().get(position).getName());
                break;
        }
        Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.navigation_zaymi_detail, bundle);
    }

    boolean isfirst(){
        DB app = Loader.getDb();
        if(app != null)
            return Loader.getDb().getApp_config().isFirst_run();
        else
            return true;
    }

    void setup(){
//        if(isfirst()){
//            DB app = Loader.getDb();
//            app.getApp_config().setFirst_run(false);
//            ServerRepo.setAppConfig(app);
//        }
        if(App.first){
            AFURLS.getInstance().saveFirst();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu1:
                startActivity(new Intent(getApplicationContext(), Info.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}