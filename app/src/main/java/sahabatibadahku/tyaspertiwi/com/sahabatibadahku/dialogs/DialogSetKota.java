package sahabatibadahku.tyaspertiwi.com.sahabatibadahku.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.AutoCompleteKotaTextViewTextChangeListener;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.R;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.dao.DaoJadwalAdzan;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.dao.DaoKota;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.models.JadwalAdzan;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.models.Kota;

/**
 * Created by Agung on 5/30/2017.
 */

public class DialogSetKota extends Dialog implements View.OnClickListener{

    SharedPreferences sharedPreferences;
    DaoKota daoKota;
    DaoJadwalAdzan daoJadwalAdzan;
    String TAG = "DIALOGSETKOTA";
    Context context;
    AutoCompleteTextView input_kota;
    List<Kota> listKota;
    Button btnSimpan,btnBatal;

    public DialogSetKota(Context context) {
        super(context);
        this.context = context;
        daoKota = new DaoKota(context);
        daoJadwalAdzan = new DaoJadwalAdzan(context);
        sharedPreferences = context.getSharedPreferences("TYASPERTIWIPREFERENCE", Context.MODE_PRIVATE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_set_kota);

        btnSimpan = (Button)findViewById(R.id.btnSimpan);
        btnBatal = (Button)findViewById(R.id.btnBatal);
        btnSimpan.setOnClickListener(this);
        btnBatal.setOnClickListener(this);
        input_kota = (AutoCompleteTextView)findViewById(R.id.input_kota);

        List<Kota> listKota = daoKota.getAll();
        String[] listNamaKota = new String[listKota.size()];
        for(int i=0;i<listKota.size();i++){
            listNamaKota[i] = listKota.get(i).getNama();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,listNamaKota);
        input_kota.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSimpan:
                String kota = input_kota.getText().toString();
                if(kota.length()==0) {

                }else{
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("kota",kota);
                    editor.commit();
                }
                dismiss();
                break;
            case R.id.btnBatal:
                dismiss();
                break;
        }
    }
}
