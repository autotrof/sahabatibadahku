package sahabatibadahku.tyaspertiwi.com.sahabatibadahku.models;

import android.database.Cursor;

import sahabatibadahku.tyaspertiwi.com.sahabatibadahku.Database;

/**
 * Created by Agung on 5/27/2017.
 */

public class JenisAmalan {
    private int id;
    private String nama;

    public JenisAmalan(Cursor cursor) {
        this.setId(cursor.getInt(cursor.getColumnIndex(Database.jenis_id)));
        this.setNama(cursor.getString(cursor.getColumnIndex(Database.jenis_nama)));
    }

    public JenisAmalan(int id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public JenisAmalan(String nama) {
        this.nama = nama;
    }

    public JenisAmalan() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }
}
