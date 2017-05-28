package sahabatibadahku.tyaspertiwi.com.sahabatibadahku.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.Database;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.models.Amalan;
import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.models.JenisAmalan;

/**
 * Created by Agung on 5/27/2017.
 */

public class DaoAmalan {

    SQLiteDatabase DB;

    public DaoAmalan(Context context) {
        DB = new Database(context).getWritableDatabase();
    }

    public DaoAmalan(SQLiteDatabase DB) {
        this.DB = DB;
    }

    public void insert(Amalan amalan) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(Database.amalan_nama, amalan.getNama());
        contentValues.put(Database.amalan_jenis_id, amalan.getJenisAmalan().getId());
        contentValues.put(Database.amalan_hari, amalan.getHari());
        contentValues.put(Database.amalan_jam, amalan.getJam());
        contentValues.put(Database.amalan_on_off, amalan.getOnOff());
//        contentValues.put(Database.achievement_type_id, amalan.getAchievementType().getId());
//        contentValues.put(Database.amalan_units, amalan.getUnits());

        long status = DB.insert(Database.table_amalan, null, contentValues);

    }

    public void update(Amalan amalan) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Database.amalan_id, amalan.getId());
        contentValues.put(Database.amalan_nama, amalan.getNama());
        contentValues.put(Database.amalan_jenis_id, amalan.getJenisAmalan().getId());
        contentValues.put(Database.amalan_hari, amalan.getHari());
        contentValues.put(Database.amalan_jam, amalan.getJam());
        contentValues.put(Database.amalan_on_off, amalan.getOnOff());
//        contentValues.put(Database.achievement_type_id, amalan.getAchievementType().getId());
//        contentValues.put(Database.amalan_units, amalan.getUnits());
        long status = DB.update(Database.table_amalan, contentValues, Database.amalan_id + "=" + amalan.getId(), null);
    }

    public void updateOnOff(Amalan amalan) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Database.amalan_on_off, amalan.getOnOff());
//        contentValues.put(Database.achievement_type_id, amalan.getAchievementType().getId());
//        contentValues.put(Database.amalan_units, amalan.getUnits());
        long status = DB.update(Database.table_amalan, contentValues, Database.amalan_id + "=" + amalan.getId(), null);
        Log.d("DAOAMALAN","UPDATE ON OFF");
    }

    public List<Amalan> getAll() {
        List<Amalan> listAmalan = new ArrayList<>();

        Cursor cursor = DB.rawQuery("SELECT * FROM "
                +Database.table_amalan+ " INNER JOIN "
                +Database.table_jenis+" ON "
                +Database.table_amalan+"."
                +Database.amalan_jenis_id+" = "
                +Database.table_jenis+"."
                +Database.jenis_id, null);
        if(cursor.moveToFirst()){
            do{
                Amalan amalan = new Amalan(cursor);
                listAmalan.add(amalan);
            }while (cursor.moveToNext());
        }
        return listAmalan;
    }

    public void delete(int id){
        DB.delete(Database.table_amalan, Database.amalan_id+ "=" + id, null);
    }

    public Amalan getOne(int id) {
        String query = "SELECT * FROM "+Database.table_amalan+" INNER JOIN "+Database.table_jenis+" ON "+Database.table_amalan+"."+Database.amalan_jenis_id+" = "+Database.table_jenis+"."+Database.jenis_id+" WHERE "+Database.table_amalan+"."+Database.amalan_id+"=?";
        Cursor cursor = DB.rawQuery(query, new String[]{String.valueOf(id)});
        if (cursor != null)
            cursor.moveToFirst();

        Amalan amalan = new Amalan(cursor);
        return amalan;
    }

}
