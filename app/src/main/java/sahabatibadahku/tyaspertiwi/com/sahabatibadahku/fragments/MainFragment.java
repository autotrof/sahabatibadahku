package sahabatibadahku.tyaspertiwi.com.sahabatibadahku.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.AmalanAdapter;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.DialogInputAmalan;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.R;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.dao.DaoAmalan;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.models.Amalan;

/**
 * Created by Agung on 5/27/2017.
 */

public class MainFragment extends Fragment implements Dialog.OnCancelListener, Dialog.OnDismissListener, View.OnClickListener {

    private View view;
    private RecyclerView recycler_view_amalan;
    private TextView placeholder_recycler_view_amalan;
    private DaoAmalan daoAmalan;
    FragmentManager fragmentManager;

    public MainFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        if(view==null){
            view = inflater.inflate(R.layout.fragment_main,container,false);
        }else{
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        }
        placeholder_recycler_view_amalan = (TextView)view.findViewById(R.id.placeholder_recycler_view_amalan);
        recycler_view_amalan = (RecyclerView)view.findViewById(R.id.recycler_view_amalan);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycler_view_amalan.setLayoutManager(layoutManager);
        recycler_view_amalan.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        daoAmalan = new DaoAmalan(getActivity());
        loadAllAmalan();
        fragmentManager = getActivity().getSupportFragmentManager();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.toolbar_main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_tambah:
                showDialogInputAmalan();
                break;
            case R.id.menu_waktu_adzan:
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment,new JadwalAdzanFragment());
                fragmentTransaction.commit();
                break;
        }
        return true;
    }

    public void showDialogInputAmalan(){
        DialogInputAmalan dialogInputAmalan = new DialogInputAmalan(getActivity(),view);
        dialogInputAmalan.setTitle("Tambah Amalan");
        dialogInputAmalan.show();
        dialogInputAmalan.getWindow().setLayout(CoordinatorLayout.LayoutParams.MATCH_PARENT, CoordinatorLayout.LayoutParams.WRAP_CONTENT);
        dialogInputAmalan.setOnCancelListener(this);
        dialogInputAmalan.setOnDismissListener(this);
    }

    public void loadAllAmalan(){
        List <Amalan> list = daoAmalan.getAll();
        if (list.size()<1) {
            recycler_view_amalan.setVisibility(View.GONE);
            placeholder_recycler_view_amalan.setVisibility(View.VISIBLE);
            placeholder_recycler_view_amalan.setOnClickListener(this);
        }
        else {
            placeholder_recycler_view_amalan.setVisibility(View.GONE);
            recycler_view_amalan.setVisibility(View.VISIBLE);
        }
        AmalanAdapter adapter = new AmalanAdapter(getActivity(),view,list);
        recycler_view_amalan.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {
        loadAllAmalan();
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        loadAllAmalan();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.placeholder_recycler_view_amalan:
                showDialogInputAmalan();
                break;
        }
    }
}
