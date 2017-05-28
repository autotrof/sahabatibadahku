package sahabatibadahku.tyaspertiwi.com.sahabatibadahku.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.AmalanAdapter;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.R;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.dao.DaoAmalan;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.dao.DaoKota;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.models.Amalan;

/**
 * Created by Agung on 5/29/2017.
 */

public class JadwalAdzanFragment extends Fragment {

    private View view;
    DaoKota daoKota;

    public JadwalAdzanFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
    }
}
