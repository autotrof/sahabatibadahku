package sahabatibadahku.tyaspertiwi.com.sahabatibadahku;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.dao.DaoAmalan;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.dao.DaoJenisAmalan;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.models.Amalan;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.models.JenisAmalan;

/**
 * Created by Agung on 5/28/2017.
 */

public class DialogInputAmalan extends Dialog implements View.OnClickListener, View.OnFocusChangeListener{

    String TAG = "DIALOGINPUTAMALAN";
    public Context context;
    public Button btnSimpan,btnBatal;
    public EditText namaAmalan, jamAmalan;
    public CheckBox senin,selasa,rabu,kamis,jumat,sabtu,ahad;
    public Spinner jenisAmalan;
    public SwitchCompat on_off_amalan;
    Amalan amalan;
    DaoJenisAmalan daoJenisAmalan;
    DaoAmalan daoAmalan;
    List<JenisAmalan> listJenisAmalan;
    List<String> stringListJenisAmalan;
    View view;

    public DialogInputAmalan(Context context, View view) {
        super(context);
        this.view = view;
        this.context = context;
        daoJenisAmalan = new DaoJenisAmalan(context);
        daoAmalan = new DaoAmalan(context);
        listJenisAmalan = daoJenisAmalan.getAll();
        stringListJenisAmalan = new ArrayList<>();
        for(JenisAmalan jenisAmalan:listJenisAmalan){
            stringListJenisAmalan.add(jenisAmalan.getNama());
        }
    }

    public DialogInputAmalan(Context context, View view, Amalan amalan) {
        super(context);
        this.view = view;
        this.context = context;
        this.amalan = amalan;
        daoJenisAmalan = new DaoJenisAmalan(context);
        daoAmalan = new DaoAmalan(context);
        listJenisAmalan = daoJenisAmalan.getAll();
        stringListJenisAmalan = new ArrayList<>();
        for(JenisAmalan jenisAmalan:listJenisAmalan){
            stringListJenisAmalan.add(jenisAmalan.getNama());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_input_amalan);
        jenisAmalan = (Spinner)findViewById(R.id.jenisAlaman);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, stringListJenisAmalan);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jenisAmalan.setAdapter(adapter);
        btnSimpan = (Button)findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(this);
        btnBatal = (Button)findViewById(R.id.btnBatal);
        btnBatal.setOnClickListener(this);
        namaAmalan = (EditText)findViewById(R.id.namaAmalan);
        namaAmalan.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        jamAmalan = (EditText)findViewById(R.id.jamAmalan);
        jamAmalan.setOnFocusChangeListener(this);
        jamAmalan.setOnClickListener(this);
        senin = (CheckBox)findViewById(R.id.senin);
        selasa = (CheckBox)findViewById(R.id.selasa);
        rabu = (CheckBox)findViewById(R.id.rabu);
        kamis = (CheckBox)findViewById(R.id.kamis);
        jumat = (CheckBox)findViewById(R.id.jumat);
        sabtu = (CheckBox)findViewById(R.id.sabtu);
        ahad = (CheckBox)findViewById(R.id.ahad);
        on_off_amalan = (SwitchCompat)findViewById(R.id.on_off_amalan);

        if(amalan!=null){
            jenisAmalan.setSelection(amalan.getJenisAmalan().getId(),false);
            namaAmalan.setText(amalan.getNama());
            jamAmalan.setText(amalan.getJam());
            if(amalan.getOnOff().equals("1")) on_off_amalan.setChecked(true);
            else on_off_amalan.setChecked(false);
            String[] hari = amalan.getHari().split("");
            for (String h:hari){
                switch (h){
                    case "1":
                        senin.setChecked(true);
                        break;
                    case "2":
                        selasa.setChecked(true);
                        break;
                    case "3":
                        rabu.setChecked(true);
                        break;
                    case "4":
                        kamis.setChecked(true);
                        break;
                    case "5":
                        jumat.setChecked(true);
                        break;
                    case "6":
                        sabtu.setChecked(true);
                        break;
                    case "7":
                        ahad.setChecked(true);
                        break;
                }
            }
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSimpan :
                String nama = namaAmalan.getText().toString();
                String jam = jamAmalan.getText().toString();
                String onOff = "";
                if(nama.length()==0) {
                    Toast.makeText(context, "Nama Alaman Harus Diisi", Toast.LENGTH_SHORT).show();
                }
                else{
                    String hari = "";
                    if(senin.isChecked()){
                        hari+=senin.getTag();
                    }
                    if(selasa.isChecked()){
                        hari+=selasa.getTag();
                    }
                    if(rabu.isChecked()){
                        hari+=rabu.getTag();
                    }
                    if(kamis.isChecked()){
                        hari+=kamis.getTag();
                    }
                    if(jumat.isChecked()){
                        hari+=jumat.getTag();
                    }
                    if(sabtu.isChecked()){
                        hari+=sabtu.getTag();
                    }
                    if(ahad.isChecked()){
                        hari+=ahad.getTag();
                    }
                    if(on_off_amalan.isChecked()){
                        onOff = "1";
                    }else{
                        onOff = "0";
                    }
                    if(amalan!=null){
                        amalan.setNama(nama);
                        amalan.setJam(jam);
                        amalan.setHari(hari);
                        amalan.setJenisAmalan(listJenisAmalan.get(jenisAmalan.getSelectedItemPosition()));
                        amalan.setOnOff(onOff);
                        daoAmalan.update(amalan);
                    }else{
                        Amalan amalan = new Amalan(nama,listJenisAmalan.get(jenisAmalan.getSelectedItemPosition()),hari,jam,onOff,"ac","un");
                        daoAmalan.insert(amalan);
                    }
                    dismiss();
                }
                break;
            case R.id.jamAmalan:
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String stringSelectedHour,stringSelectedMinute;
                        if(selectedHour<10) stringSelectedHour = "0"+selectedHour;
                        else stringSelectedHour = selectedHour+"";
                        if(selectedMinute<10) stringSelectedMinute = "0"+selectedMinute;
                        else stringSelectedMinute = selectedMinute+"";
                        jamAmalan.setText( stringSelectedHour + ":" + stringSelectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
                break;
            case R.id.btnBatal:
                dismiss();
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean b) {
        switch (v.getId()){
            case R.id.jamAmalan:
                if(b){
                    Calendar mcurrentTime = Calendar.getInstance();
                    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                    int minute = mcurrentTime.get(Calendar.MINUTE);

                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            String stringSelectedHour,stringSelectedMinute;
                            if(selectedHour<10) stringSelectedHour = "0"+selectedHour;
                            else stringSelectedHour = selectedHour+"";
                            if(selectedMinute<10) stringSelectedMinute = "0"+selectedMinute;
                            else stringSelectedMinute = selectedMinute+"";
                            jamAmalan.setText( stringSelectedHour + ":" + stringSelectedMinute);
                        }
                    }, hour, minute, true);
                    mTimePicker.setTitle("Select Time");
                    mTimePicker.show();
                }
                break;
        }
    }


}
