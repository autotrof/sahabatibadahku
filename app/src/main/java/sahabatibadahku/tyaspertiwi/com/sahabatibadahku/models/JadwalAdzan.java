package sahabatibadahku.tyaspertiwi.com.sahabatibadahku.models;

import android.database.Cursor;

import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.Database;

/**
 * Created by Agung on 5/31/2017.
 */

public class JadwalAdzan {

    private int id;
    private String kota,tanggal,subuh,duhur,ashar,magrib,isya;


    public void setId(int id) {
        this.id = id;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public void setSubuh(String subuh) {
        this.subuh = subuh;
    }

    public void setDuhur(String duhur) {
        this.duhur = duhur;
    }

    public void setAshar(String ashar) {
        this.ashar = ashar;
    }

    public void setMagrib(String magrib) {
        this.magrib = magrib;
    }

    public void setIsya(String isya) {
        this.isya = isya;
    }

    public int getId() {

        return id;
    }

    public String getKota() {
        return kota;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getSubuh() {
        return subuh;
    }

    public String getDuhur() {
        return duhur;
    }

    public String getAshar() {
        return ashar;
    }

    public String getMagrib() {
        return magrib;
    }

    public String getIsya() {
        return isya;
    }

    public JadwalAdzan(int id, String kota, String tanggal, String subuh, String duhur, String ashar, String magrib, String isya) {

        this.id = id;
        this.kota = kota;
        this.tanggal = tanggal;
        this.subuh = subuh;
        this.duhur = duhur;
        this.ashar = ashar;
        this.magrib = magrib;
        this.isya = isya;
    }

    public JadwalAdzan(String kota, String tanggal, String subuh, String duhur, String ashar, String magrib, String isya) {

        this.kota = kota;
        this.tanggal = tanggal;
        this.subuh = subuh;
        this.duhur = duhur;
        this.ashar = ashar;
        this.magrib = magrib;
        this.isya = isya;
    }

    public JadwalAdzan(Cursor cursor) {
        this.id = cursor.getInt(cursor.getColumnIndex(Database.waktu_salat_id));
        this.kota = cursor.getString(cursor.getColumnIndex(Database.waktu_salat_kota));
        this.tanggal = cursor.getString(cursor.getColumnIndex(Database.waktu_salat_tanggal));
        this.subuh = cursor.getString(cursor.getColumnIndex(Database.waktu_salat_subuh));
        this.duhur = cursor.getString(cursor.getColumnIndex(Database.waktu_salat_duhur));
        this.ashar = cursor.getString(cursor.getColumnIndex(Database.waktu_salat_ashar));
        this.magrib = cursor.getString(cursor.getColumnIndex(Database.waktu_salat_magrib));
        this.isya = cursor.getString(cursor.getColumnIndex(Database.waktu_salat_isya));
    }
}
