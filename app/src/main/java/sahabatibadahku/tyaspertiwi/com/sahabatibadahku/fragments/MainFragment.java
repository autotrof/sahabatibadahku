package sahabatibadahku.tyaspertiwi.com.sahabatibadahku.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.AmalanAdapter;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.DialogInputAmalan;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.R;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.dao.DaoAmalan;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.models.Amalan;

/**
 * Created by Agung on 5/27/2017.
 */

public class MainFragment extends Fragment implements Dialog.OnCancelListener, Dialog.OnDismissListener{

    private View view;
    private RecyclerView recycler_view_amalan;
    private AmalanAdapter amalanAdapter;
    private List<Amalan> listAmalan;
    private TextView placeholder_recycler_view_amalan;
    private DaoAmalan daoAmalan;

    public MainFragment(List<Amalan> listAmalan) {
        this.listAmalan = listAmalan;
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
        if (listAmalan.size()<1) {
            recycler_view_amalan.setVisibility(View.GONE);
            placeholder_recycler_view_amalan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDialogInputAmalan();
                }
            });
        }
        else {
            placeholder_recycler_view_amalan.setVisibility(View.GONE);
        }
        recycler_view_amalan.setAdapter(amalanAdapter);
        amalanAdapter = new AmalanAdapter(getActivity(),view,listAmalan);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        amalanAdapter.notifyDataSetChanged();
        daoAmalan = new DaoAmalan(getActivity());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.toolbar_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_tambah:
                showDialogInputAmalan();
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
}
