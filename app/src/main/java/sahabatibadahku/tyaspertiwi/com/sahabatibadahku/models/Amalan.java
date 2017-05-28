package sahabatibadahku.tyaspertiwi.com.sahabatibadahku.models;

import android.database.Cursor;

import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.Database;

/**
 * Created by Agung on 5/27/2017.
 */

public class Amalan {

    private int id;
    private String nama;
    private JenisAmalan jenisAmalan;
    private String hari;
    private String jam;
    private String onOff;
    private String achievementType;
    private String unit;

    public Amalan(int id, String nama, JenisAmalan jenisAmalan, String hari, String jam, String onOff, String achievementType, String unit) {
        this.id = id;
        this.nama = nama;
        this.jenisAmalan = jenisAmalan;
        this.hari = hari;
        this.jam = jam;
        this.onOff = onOff;
//        this.achievementType = achievementType;
//        this.unit = unit;
    }

    public Amalan(String nama, JenisAmalan jenisAmalan, String hari, String jam, String onOff, String achievementType, String unit) {

        this.nama = nama;
        this.jenisAmalan = jenisAmalan;
        this.hari = hari;
        this.jam = jam;
        this.onOff = onOff;
//        this.achievementType = achievementType;
//        this.unit = unit;
    }

    public Amalan(Cursor cursor) {
        this.id = cursor.getInt(cursor.getColumnIndex(Database.amalan_id));
        this.nama = cursor.getString(cursor.getColumnIndex(Database.amalan_nama));
        this.jenisAmalan = new JenisAmalan(cursor.getInt(cursor.getColumnIndex(Database.amalan_jenis_id)), cursor.getString(cursor.getColumnIndex(Database.jenis_nama)));
        this.hari = cursor.getString(cursor.getColumnIndex(Database.amalan_hari));
        this.jam = cursor.getString(cursor.getColumnIndex(Database.amalan_jam));
        this.onOff = cursor.getString(cursor.getColumnIndex(Database.amalan_on_off));
//        this.achievementType = new AchievementType(cursor.getColumnIndex(Database.achievement_type_id), "");
//        this.units = cursor.getInt(cursor.getColumnIndex(Database.amalan_units));
    }

    public Amalan() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setJenisAmalan(JenisAmalan jenisAmalan) {
        this.jenisAmalan = jenisAmalan;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public void setOnOff(String onOff) {
        this.onOff = onOff;
    }

    public void setAchievementType(String achievementType) {
        this.achievementType = achievementType;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getId() {

        return id;
    }

    public String getNama() {
        return nama;
    }

    public JenisAmalan getJenisAmalan() {
        return jenisAmalan;
    }

    public String getHari() {
        return hari;
    }

    public String getJam() {
        return jam;
    }

    public String getOnOff() {
        return onOff;
    }

    public String getAchievementType() {
        return achievementType;
    }

    public String getUnit() {
        return unit;
    }
}
