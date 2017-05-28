package sahabatibadahku.tyaspertiwi.com.sahabatibadahku;

import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.dao.DaoAmalan;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.dao.DaoJenisAmalan;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.fragments.MainFragment;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.models.Amalan;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.models.JenisAmalan;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    DaoAmalan daoAmalan;
    DaoJenisAmalan daoJenisAmalan;
    private static final String TAG = "MAINACTIVITY LOG";
    int viewState = 1;
    Toolbar toolbar;
    FragmentManager fragmentManager;
    CoordinatorLayout coordinatorLayout;
    Menu menu;
    List<Amalan> listAmalan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        daoAmalan = new DaoAmalan(this);
        listAmalan = daoAmalan.getAll();

        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinatorLayout);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
//        getSupportActionBar().setIcon(R.drawable.logo);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        for(int i=0;i<12;i++){
//            Amalan amalan = new Amalan();
//            amalan.setNama("Amalan "+i);
//            amalan.setJam("10:0"+i);
//            listAmalan.add(amalan);
//        }
        fragmentTransaction.add(R.id.fragment,new MainFragment(listAmalan));
        fragmentTransaction.commit();

        viewState = 1;

        db = new Database(this).getReadableDatabase();
        daoAmalan = new DaoAmalan(this);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
//        return true;
//    }
}
