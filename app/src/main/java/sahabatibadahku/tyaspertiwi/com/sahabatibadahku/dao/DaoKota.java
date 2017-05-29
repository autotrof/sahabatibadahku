package sahabatibadahku.tyaspertiwi.com.sahabatibadahku.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.Database;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.models.Kota;

/**
 * Created by Agung on 5/29/2017.
 */

public class DaoKota {

    SQLiteDatabase DB;

    public DaoKota(Context context) {
        DB = new Database(context).getWritableDatabase();
    }

    public ArrayList<Kota> getAll(){
        String query = "SELECT * FROM " +Database.table_kota;

        ArrayList<Kota> listKota = new ArrayList<>();
        Cursor cursor = DB.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String nama = cursor.getString(1);
                int negara = cursor.getInt(2);
                listKota.add(new Kota(id,nama,negara));
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
}
