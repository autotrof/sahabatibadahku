package sahabatibadahku.tyaspertiwi.com.sahabatibadahku.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.Database;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.models.Kota;

import static android.R.attr.filter;

/**
 * Created by Agung on 5/29/2017.
 */

public class DaoKota {

    SQLiteDatabase DB;

    public DaoKota(Context context) {
        DB = new Database(context).getWritableDatabase();
    }

    public ArrayList<Kota> getAll(){
        String query = "SELECT * FROM " +Database.table_kota+" ORDER BY "+Database.kota_nama+" ASC";

        ArrayList<Kota> listKota = new ArrayList<>();
        Cursor cursor = DB.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                listKota.add(new Kota(cursor));
            }while (cursor.moveToNext());
        }
        return listKota;
    }

    public Kota getOne(int id) {
        String query = "SELECT * FROM "+Database.table_kota+" WHERE "+Database.table_kota+"."+Database.kota_id+"=?";
        Cursor cursor = DB.rawQuery(query, new String[]{String.valueOf(id)});
        if (cursor != null)
            cursor.moveToFirst();

        Kota kota = new Kota(cursor);
        return kota;
    }

    public List<Kota> searchKotaByName(String namaKota){
        ArrayList<Kota> listKota = new ArrayList<>();
        Cursor cursor = DB.query(true,Database.table_kota, new String[]{Database.kota_id,Database.kota_nama,Database.kota_negara}, Database.kota_nama + " LIKE ?",new String[] {"%"+namaKota+"%"},null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                listKota.add(new Kota(cursor));
            }while (cursor.moveToNext());
        }
        return listKota;
    }

}
