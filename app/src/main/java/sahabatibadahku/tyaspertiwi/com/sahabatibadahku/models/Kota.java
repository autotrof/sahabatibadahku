package sahabatibadahku.tyaspertiwi.com.sahabatibadahku.models;

/**
 * Created by Agung on 5/29/2017.
 */

public class Kota {

    private int id;
    private String nama;
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

    public int getNegara() {
        return negara_id;
    }

    public Kota(String  nama, int negara) {

        this.nama = nama;
        this.negara_id = negara_id;
    }

    public Kota(int id, String  nama, int negara_id) {

        this.id = id;
        this.nama = nama;
        this.negara_id = negara_id;
    }

    public Kota() {

    }
}
