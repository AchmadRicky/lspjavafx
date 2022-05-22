/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafikacafe;

/**
 *
 * @author SAMSUNG
 */
public class data_log {
    Integer id_log,id_kasir;
    String nama_kasir,waktu,keterangan;

    public Integer getId_log() {
        return id_log;
    }

    public void setId_log(Integer id_log) {
        this.id_log = id_log;
    }

    public Integer getId_kasir() {
        return id_kasir;
    }

    public void setId_kasir(Integer id_kasir) {
        this.id_kasir = id_kasir;
    }

    public String getNama_kasir() {
        return nama_kasir;
    }

    public void setNama_kasir(String nama_kasir) {
        this.nama_kasir = nama_kasir;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public data_log(Integer id_log, Integer id_kasir, String nama_kasir, String waktu, String keterangan) {
        this.id_log = id_log;
        this.id_kasir = id_kasir;
        this.nama_kasir = nama_kasir;
        this.waktu = waktu;
        this.keterangan = keterangan;
    }
}
