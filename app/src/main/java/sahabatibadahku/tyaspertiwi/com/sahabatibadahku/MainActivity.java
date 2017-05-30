package sahabatibadahku.tyaspertiwi.com.sahabatibadahku;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import net.danlew.android.joda.JodaTimeAndroid;

import java.util.List;

import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.fragments.MainFragment;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.models.Amalan;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MAINACTIVITY LOG";
    int viewState = 1;
    Toolbar toolbar;
    FragmentManager fragmentManager;
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JodaTimeAndroid.init(this);

        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinatorLayout);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
//        getSupportActionBar().setIcon(R.drawable.logo);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment,new MainFragment());
        fragmentTransaction.commit();

        viewState = 1;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
