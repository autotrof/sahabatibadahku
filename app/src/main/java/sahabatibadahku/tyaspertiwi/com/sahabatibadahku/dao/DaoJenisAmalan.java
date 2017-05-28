package sahabatibadahku.tyaspertiwi.com.sahabatibadahku.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.Database;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.models.JenisAmalan;

/**
 * Created by Agung on 5/27/2017.
 */

public class DaoJenisAmalan {
    SQLiteDatabase DB;

    public DaoJenisAmalan(Context context){
        DB = new Database(context).getWritableDatabase();
    }

    public ArrayList<JenisAmalan> getAll(){
        String query = "SELECT * FROM " +Database.table_jenis;

        ArrayList<JenisAmalan> listAmalan = new ArrayList<>();
        Cursor cursor = DB.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String nama = cursor.getString(1);
                listAmalan.add(new JenisAmalan(id,nama));
            }while (cursor.moveToNext());
        }

        return listAmalan;
    }

    public void insertData(JenisAmalan jenisAmalan) {
        ContentValues cv = new ContentValues();
        cv.put( Database.jenis_id, jenisAmalan.getId());
        cv.put( Database.jenis_nama, jenisAmalan.getNama());

        DB.insert(Database.table_jenis, null, cv);
    }
}
