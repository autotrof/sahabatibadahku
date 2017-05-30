package sahabatibadahku.tyaspertiwi.com.sahabatibadahku.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.Database;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.models.JadwalAdzan;

/**
 * Created by Agung on 5/31/2017.
 */

public class DaoJadwalAdzan {

    SQLiteDatabase DB;

    public DaoJadwalAdzan(Context context) {
        DB = new Database(context).getWritableDatabase();
    }

    public JadwalAdzan getJadwalAdzanByKota(String kota){
//        List<JadwalAdzan> listJadwalAdzan = new ArrayList<>();
        JadwalAdzan jadwalAdzan = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String tanggal = dateFormat.format(date);
        Cursor cursor = DB.query(true,Database.table_waktu_salat, new String[]{Database.waktu_salat_id,Database.waktu_salat_kota,Database.waktu_salat_tanggal,Database.waktu_salat_subuh,Database.waktu_salat_duhur,Database.waktu_salat_ashar,Database.waktu_salat_magrib,Database.waktu_salat_isya}, Database.waktu_salat_kota + " LIKE ? AND "+Database.waktu_salat_tanggal+" = ?",new String[] {"%"+kota+"%",tanggal},null,null,null,null);
//        if(cursor.moveToFirst()){
//
//            do{
//                listJadwalAdzan.add(new JadwalAdzan(cursor));
//                Log.d("HALO",cursor.getString(1));
//            }while (cursor.moveToNext());
//        }
        if (cursor!=null) cursor.moveToFirst();
        jadwalAdzan = new JadwalAdzan(cursor);
        return jadwalAdzan;
//        return null;
    }
}
