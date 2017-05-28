package sahabatibadahku.tyaspertiwi.com.sahabatibadahku;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Agung on 5/27/2017.
 */

public class Database extends SQLiteOpenHelper {

    AssetManager assetManager;
    private static final String TAG = "DATABASE INIT";
    public static final String db_nama = "Database";
    public static final Integer db_versi = 2;

    public static final String table_amalan = "amalan";
    public static final String amalan_id = "amalan_id";
    public static final String amalan_nama = "amalan_nama";
    public static final String amalan_jenis_id = "amalan_jenis_id";//Jenis Amalan ref=>jenis_amalan
    public static final String amalan_hari = "amalan_hari";//hari alarm amalan
    public static final String amalan_jam = "amalan_jam";//jam alarm amalan
    public static final String amalan_on_off = "amalan_on_off";//status alarm on atau off
    public static final String amalan_achievement_type = "amalan_achievement_type";//Nanti Dulu
    public static final String amalan_unit = "amalan_unit";//Nanti Dulu

    public static final String table_jenis = "jenis_amalan";
    public static final String jenis_id = "jenis_id";
    public static final String jenis_nama = "nama";

    public static final String table_monitoring = "monitoring";
    public static final String monitoring_id = "monitoring_id";
    public static final String monitoring_amalan_id = "monitoring_amalan_id";
    public static final String monitoring_tanggal = "monitoring_tanggal";
    public static final String monitoring_achievement = "monitoring_achievement";

    public static final String tabel_achievement_type = "achievement_type";
    public static final String achievement_type_id = "achievement_type_id";
    public static final String achievement_type_achievement = "achievement_type_achievement";

    public static final String tabel_kota = "kota";
    public static final String kota_id = "kota_id";
    public static final String kota_nama = "kota_nama";
    public static final String kota_negara = "kota_negara";

    public Database(Context context){
        super(context, db_nama, null, db_versi);
        assetManager = context.getAssets();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREATE TABLES
        db.execSQL("CREATE TABLE " + table_amalan + "(" +
                "" + amalan_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "" + amalan_nama + " TEXT, " +
                "" + amalan_jenis_id + " INTEGER, " +
                "" + amalan_hari + " TEXT, " +
                "" + amalan_jam + " TEXT, " +
                "" + amalan_on_off + " INTEGER);"
                //"" + achievement_type_id + " TEXT, " +
                //"" + amalan_units + " INTEGER);"
        );

        db.execSQL("CREATE TABLE " + table_jenis + "(" +
                "" + jenis_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "" + jenis_nama + " TEXT, " +
                "" + amalan_jenis_id + " INTEGER);"
        );


        //DEFINISI JENIS AMALAN
        ContentValues[] contentValueses = new ContentValues[7];

        contentValueses[0] = new ContentValues();
        contentValueses[0].put(jenis_id, 0);
        contentValueses[0].put(jenis_nama, "Sholat Wajib");

        contentValueses[1] = new ContentValues();
        contentValueses[1].put(jenis_id, 1);
        contentValueses[1].put(jenis_nama, "Sholat Sunnah");

        contentValueses[2] = new ContentValues();
        contentValueses[2].put(jenis_id, 2);
        contentValueses[2].put(jenis_nama, "Puasa");

        contentValueses[3] = new ContentValues();
        contentValueses[3].put(jenis_id, 3);
        contentValueses[3].put(jenis_nama, "Hafalan");

        contentValueses[4] = new ContentValues();
        contentValueses[4].put(jenis_id, 4);
        contentValueses[4].put(jenis_nama, "Tilawah");

        contentValueses[5] = new ContentValues();
        contentValueses[5].put(jenis_id, 5);
        contentValueses[5].put(jenis_nama, "Kajian");

        contentValueses[6] = new ContentValues();
        contentValueses[6].put(jenis_id, 6);
        contentValueses[6].put(jenis_nama, "Lainnya");

        for(ContentValues contentValues:contentValueses){
            db.insert(table_jenis, null, contentValues);
        }
        Log.d(TAG,"INSERT ALL JENIS AMALAN SUCCESS");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXIST "+table_amalan);
        db.execSQL("DROP TABLE IF EXIST "+table_jenis);
        db.execSQL("DROP TABLE IF EXIST "+table_monitoring);
        this.onCreate(db);
    }
}
