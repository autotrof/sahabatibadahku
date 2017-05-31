package sahabatibadahku.tyaspertiwi.com.sahabatibadahku.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.chrono.IslamicChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.AmalanAdapter;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.JadwalAdzanAdapter;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.R;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.dao.DaoAmalan;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.dao.DaoJadwalAdzan;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.dao.DaoKota;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.dialogs.DialogSetKota;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.models.Amalan;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.models.JadwalAdzan;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.models.Kota;

/**
 * Created by Agung on 5/29/2017.
 */

public class JadwalAdzanFragment extends Fragment implements DialogInterface.OnDismissListener, DialogInterface.OnCancelListener{

    private View view;
    private TextView text_tanggal_georgian,text_tanggal,text_kota,placeholder_list_view_jadwal_adzan;
    DaoKota daoKota;
    DaoJadwalAdzan daoJadwalAdzan;
    FragmentManager fragmentManager;
    SharedPreferences sharedPreferences;
    ListView listView;

    public JadwalAdzanFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        sharedPreferences = getActivity().getSharedPreferences("TYASPERTIWIPREFERENCE", Context.MODE_PRIVATE);
        if(view==null){
            view = inflater.inflate(R.layout.fragment_jadwal_adzan,container,false);
        }else{
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        }
        placeholder_list_view_jadwal_adzan = (TextView) view.findViewById(R.id.placeholder_list_view_jadwal_adzan);
        placeholder_list_view_jadwal_adzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogSetKota();
            }
        });
        text_tanggal_georgian = (TextView)view.findViewById(R.id.text_tanggal_georgian);
        text_tanggal = (TextView)view.findViewById(R.id.text_tanggal);
        text_kota = (TextView)view.findViewById(R.id.text_kota);

        DateTime dateTime = new DateTime().withChronology(IslamicChronology.getInstance());
        dateTime = dateTime.minusDays(1);
        DateFormat dateInstance = SimpleDateFormat.getDateInstance();
        String bulan = "";
        switch (dateTime.getMonthOfYear()){
            case 1:
                bulan = "Muharram";
                break;
            case 2:
                bulan = "Safar";
                break;
            case 3:
                bulan = "Rabiul awal";
                break;
            case 4:
                bulan = "Rabiul akhir";
                break;
            case 5:
                bulan = "Jumadil awal";
                break;
            case 6:
                bulan = "Jumadil akhir";
                break;
            case 7:
                bulan = "Rajab";
                break;
            case 8:
                bulan = "Sya'ban";
                break;
            case 9:
                bulan = "Ramadhan";
                break;
            case 10:
                bulan = "Syawal";
                break;
            case 11:
                bulan = "Dzulkaidah";
                break;
            default:
                bulan = "Dzulhijjah";
                break;
        }
        text_tanggal.setText(dateTime.getDayOfMonth()+" "+bulan+" "+dateTime.getYear()+"H");
        text_tanggal_georgian.setText(dateInstance.format(Calendar.getInstance().getTime()));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        daoKota = new DaoKota(getActivity());
        daoJadwalAdzan = new DaoJadwalAdzan(getActivity());
        if(sharedPreferences.contains("kota")){
            text_kota.setText(sharedPreferences.getString("kota","Kota Belum Di set"));
            placeholder_list_view_jadwal_adzan.setVisibility(View.GONE);
            loadAllJadwalAdzan(sharedPreferences.getString("kota","Kota Belum Di set"));
        }else{
            String text = "<font color='#cb9b8c'>Kota Belum Di set</font>";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                text_kota.setText(Html.fromHtml(text,1));
            }else{
                text_kota.setText(Html.fromHtml(text));
            }
            listView = (ListView)view.findViewById(R.id.list_view_jadwal_adzan);
            listView.setVisibility(View.GONE);
        }
        fragmentManager = getActivity().getSupportFragmentManager();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.toolbar_jadwal_adzan_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_home:
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment,new MainFragment());
                fragmentTransaction.commit();
                break;
            case R.id.menu_set_kota:
                showDialogSetKota();
                break;
        }
        return true;
    }

        private void loadAllJadwalAdzan(String kota){
        JadwalAdzan jadwalAdzan = daoJadwalAdzan.getJadwalAdzanByKota(kota);
        String[] stringListJadwalAdzan = new String[5];
        stringListJadwalAdzan[0] = (jadwalAdzan.getSubuh());
        stringListJadwalAdzan[1] = (jadwalAdzan.getDuhur());
        stringListJadwalAdzan[2] = (jadwalAdzan.getAshar());
        stringListJadwalAdzan[3] = (jadwalAdzan.getMagrib());
        stringListJadwalAdzan[4] = (jadwalAdzan.getIsya());

        JadwalAdzanAdapter jadwalAdzanAdapter = new JadwalAdzanAdapter(getActivity(),stringListJadwalAdzan);

        listView = (ListView)view.findViewById(R.id.list_view_jadwal_adzan);
//        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,stringListJadwalAdzan);
        listView.setAdapter(jadwalAdzanAdapter);

    }

    /*
    public void loadAllKota(){
        List<Kota> listKota = daoKota.getAll();
        List<String> kotakota = new ArrayList<>();
        for(Kota kota:listKota){
            kotakota.add(kota.getNama());
        }
        ListView listViewKota = (ListView)view.findViewById(R.id.list_kota);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,kotakota);
        listViewKota.setAdapter(adapter);
    }
    */

    @Override
    public void onCancel(DialogInterface dialogInterface) {

    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {

    }

    private void showDialogSetKota(){
        DialogSetKota dialogSetKota = new DialogSetKota(getActivity(),view);
        dialogSetKota.setTitle("Setting Kota");
        dialogSetKota.show();
        dialogSetKota.getWindow().setLayout(CoordinatorLayout.LayoutParams.MATCH_PARENT, CoordinatorLayout.LayoutParams.WRAP_CONTENT);
        dialogSetKota.setOnDismissListener(this);
        dialogSetKota.setOnCancelListener(this);
    }
}
