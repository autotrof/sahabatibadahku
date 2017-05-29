package sahabatibadahku.tyaspertiwi.com.sahabatibadahku.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.AmalanAdapter;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.R;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.dao.DaoAmalan;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.dao.DaoKota;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.models.Amalan;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.models.Kota;

/**
 * Created by Agung on 5/29/2017.
 */

public class JadwalAdzanFragment extends Fragment {

    private View view;
    DaoKota daoKota;
    FragmentManager fragmentManager;

    public JadwalAdzanFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        if(view==null){
            view = inflater.inflate(R.layout.fragment_jadwal_adzan,container,false);
        }else{
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        daoKota = new DaoKota(getActivity());
        fragmentManager = getActivity().getSupportFragmentManager();
        loadAllKota();
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
        }
        return true;
    }

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
}
