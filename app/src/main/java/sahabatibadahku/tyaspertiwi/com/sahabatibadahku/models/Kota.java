package sahabatibadahku.tyaspertiwi.com.sahabatibadahku.models;

import android.database.Cursor;

import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.Database;

/**
 * Created by Agung on 5/29/2017.
 */

public class Kota {

    private int id;
    private String kode,nama,range;
    private int negara_id;

    public void setId(int id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNegara(int negara_id) {
        this.negara_id = negara_id;
    }

    public int getId() {

        return id;
    }

    public String getNama() {
        return nama;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public void setNegara_id(int negara_id) {
        this.negara_id = negara_id;
    }

    public String getRange() {

        return range;
    }

    public int getNegara_id() {
        return negara_id;
    }

    public int getNegara() {
        return negara_id;
    }

    public Kota(String  nama, int negara) {

        this.nama = nama;
        this.negara_id = negara_id;
    }

    public Kota(int id, String kode, String  nama, String range, int negara_id) {

        this.id = id;
        this.nama = nama;
        this.kode = kode;
        this.range = range;
        this.negara_id = negara_id;
    }

    public Kota(String kode, String  nama, String range, int negara_id) {
        this.nama = nama;
        this.kode = kode;
        this.range = range;
        this.negara_id = negara_id;
    }

    public Kota() {

    }

    public Kota(Cursor cursor) {
        this.id = cursor.getInt(cursor.getColumnIndex(Database.kota_id));
        this.kode = cursor.getString(cursor.getColumnIndex(Database.kota_kode));
        this.nama = cursor.getString(cursor.getColumnIndex(Database.kota_nama));
        this.negara_id = cursor.getInt(cursor.getColumnIndex(Database.kota_negara));
    }
}
