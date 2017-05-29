package sahabatibadahku.tyaspertiwi.com.sahabatibadahku;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.dao.DaoAmalan;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.models.Amalan;

/**
 * Created by Agung on 5/27/2017.
 */

public class AmalanAdapter extends RecyclerView.Adapter<AmalanAdapter.TheHolder> implements View.OnClickListener, PopupMenu.OnMenuItemClickListener, Dialog.OnCancelListener, Dialog.OnDismissListener{

    private List<Amalan> listAmalan;
    private Context context;
    private DaoAmalan daoAmalan;
    private int tempId;
    private View view;
    private static final int UPDATE_CODE = 1, INSERT_CODE = 2;

    public AmalanAdapter(Context context, View view, List<Amalan> listAmalan) {
        this.context = context;
        this.view = view;
        daoAmalan = new DaoAmalan(context);
        this.listAmalan = listAmalan;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_setting:
                PopupMenu popupMenu = new PopupMenu(context,view);
                popupMenu.getMenuInflater().inflate(R.menu.amalan_row_menu, popupMenu.getMenu());
                Amalan amalan = (Amalan)view.getTag();
                tempId = amalan.getId();
                Menu menu = popupMenu.getMenu();
                if(amalan.getOnOff().equals("1")){
                    menu.findItem(R.id.menu_set_alarm).setTitle("Matikan Alarm");
                }else{
                    menu.findItem(R.id.menu_set_alarm).setTitle("Hidupkan Alarm");
                }
                popupMenu.setOnMenuItemClickListener(this);
                popupMenu.show();
                break;
            case R.id.placeholder_recycler_view_amalan:
                showDialogInputAmalan(INSERT_CODE);
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_set_alarm:
                Amalan amalan = daoAmalan.getOne(tempId);
                if(amalan.getOnOff().equals("1")) amalan.setOnOff("0");
                else amalan.setOnOff("1");
                daoAmalan.updateOnOff(amalan);
                listAmalan = daoAmalan.getAll();
                notifyDataSetChanged();
                break;
            case R.id.menu_atur:
                showDialogInputAmalan(UPDATE_CODE);
                break;
            case R.id.menu_hapus:
                AlertDialog dialog = AskOption(tempId);
                dialog.show();
                break;
        }
        return true;
    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {
        listAmalan = daoAmalan.getAll();
        notifyDataSetChanged();
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        listAmalan = daoAmalan.getAll();
        notifyDataSetChanged();
    }

    public class TheHolder extends RecyclerView.ViewHolder{

        TextView nomor_amalan,nama_amalan,jam_amalan;
        ImageButton button_setting;
        public TheHolder(View view) {
            super(view);
            nomor_amalan = (TextView)view.findViewById(R.id.nomor_amalan);
            nama_amalan = (TextView)view.findViewById(R.id.nama_amalan);
            jam_amalan = (TextView)view.findViewById(R.id.jam_amalan);
            button_setting = (ImageButton)view.findViewById(R.id.button_setting);
        }
    }

    @Override
    public TheHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_amalan,parent,false);
        return new TheHolder(view);
    }

    @Override
    public void onBindViewHolder(TheHolder holder, int position) {
        Amalan amalan = listAmalan.get(position);
        holder.nomor_amalan.setText((position+1)+"");
        holder.nama_amalan.setText(amalan.getJenisAmalan().getNama()+" "+amalan.getNama());
        if (amalan.getOnOff().equals("1")){
            String text = "<font color='#39796b'>Alarm On</font> <font color='#757575'>"+amalan.getJam()+"</font>";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.jam_amalan.setText(Html.fromHtml(text,1));
            }else{
                holder.jam_amalan.setText(Html.fromHtml(text));
            }
        }else{
            String text = "<font color='#cb9b8c'>Alarm Off</font> <font color='#757575'>"+amalan.getJam()+"</font>";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.jam_amalan.setText(Html.fromHtml(text,1));
            }else{
                holder.jam_amalan.setText(Html.fromHtml(text));
            }
        }
        holder.button_setting.setTag(amalan);
        holder.button_setting.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return listAmalan.size();
    }

    private AlertDialog AskOption(final int id)
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(context)
                .setTitle("Hapus Amalan")
                .setMessage("Anda yakin akan menghapus amalan ini ?")
                .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        daoAmalan.delete(id);
                        dialog.dismiss();
                        listAmalan = daoAmalan.getAll();

                        TextView placeholder_recycler_view_amalan = (TextView)view.findViewById(R.id.placeholder_recycler_view_amalan);
                        RecyclerView recycler_view_amalan = (RecyclerView)view.findViewById(R.id.recycler_view_amalan);

                        if (listAmalan.size()<1) {
                            recycler_view_amalan.setVisibility(View.GONE);
                            placeholder_recycler_view_amalan.setVisibility(View.VISIBLE);
                            placeholder_recycler_view_amalan.setOnClickListener(AmalanAdapter.this);
                        }
                        else {
                            placeholder_recycler_view_amalan.setVisibility(View.GONE);
                            recycler_view_amalan.setVisibility(View.VISIBLE);
                        }
                        recycler_view_amalan.setAdapter(AmalanAdapter.this);
                        notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        return myQuittingDialogBox;
    }

    private void showDialogInputAmalan(int processCode){
        DialogInputAmalan dialogInputAmalan;
        if(processCode==UPDATE_CODE) dialogInputAmalan = new DialogInputAmalan(context, view, daoAmalan.getOne(tempId));
        else dialogInputAmalan = new DialogInputAmalan(context, view);
        dialogInputAmalan.setTitle("Tambah Amalan");
        dialogInputAmalan.show();
        dialogInputAmalan.getWindow().setLayout(CoordinatorLayout.LayoutParams.MATCH_PARENT, CoordinatorLayout.LayoutParams.WRAP_CONTENT);
        dialogInputAmalan.setOnCancelListener(this);
        dialogInputAmalan.setOnDismissListener(this);
    }
}
