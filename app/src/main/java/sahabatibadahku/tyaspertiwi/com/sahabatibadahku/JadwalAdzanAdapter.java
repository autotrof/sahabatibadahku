package sahabatibadahku.tyaspertiwi.com.sahabatibadahku;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.models.JadwalAdzan;

/**
 * Created by Agung on 5/31/2017.
 */

public class JadwalAdzanAdapter extends BaseAdapter {

    private Context context;
    private static LayoutInflater inflater = null;
    private String[] jadwal = new String[5];

    public JadwalAdzanAdapter(Context context, String[] jadwal) {
        this.context = context;
        this.jadwal = jadwal;
        Log.d("HALOO",jadwal[0]);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int i) {
        return jadwal[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null) view = inflater.inflate(R.layout.row_waktu_adzan,null);
        TextView waktu_salat = (TextView)view.findViewById(R.id.waktu_salat);
        switch (i){
            case 0:
                waktu_salat.setText("Subuh");
                break;
            case 1:
                waktu_salat.setText("Duhur");
                break;
            case 2:
                waktu_salat.setText("Ashar");
                break;
            case 3:
                waktu_salat.setText("Magrib");
                break;
            case 4:
                waktu_salat.setText("Isya'");
                break;
        }
        TextView text_waktu = (TextView)view.findViewById(R.id.text_waktu);
        text_waktu.setText(jadwal[i]);
        return view;
    }



    /*
    private final Context context;
    private final String[] values;

    public JadwalAdzanAdapter(Context context, String[] values) {
        super(context, R.layout.row_waktu_adzan);
        this.values = values;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row_waktu_adzan,parent,false);
        TextView waktu_salat = (TextView)view.findViewById(R.id.waktu_salat);
        switch (position){
            case 0:
                waktu_salat.setText("Subuh");
                break;
            case 1:
                waktu_salat.setText("Duhur");
                break;
            case 2:
                waktu_salat.setText("Ashar");
                break;
            case 3:
                waktu_salat.setText("Magrib");
                break;
            case 4:
                waktu_salat.setText("Isya'");
                break;
        }
        TextView text_waktu = (TextView)view.findViewById(R.id.text_waktu);
        text_waktu.setText(values[position]);
        return view;
    }
    */
}
