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
public class data_transaksi {
    Integer id_transaksi,id_kasir,total_pembelian,meja;
    String nama_kasir,waktu;

    public Integer getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(Integer id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public Integer getId_kasir() {
        return id_kasir;
    }

    public void setId_kasir(Integer id_kasir) {
        this.id_kasir = id_kasir;
    }

    public Integer getTotal_pembelian() {
        return total_pembelian;
    }

    public void setTotal_pembelian(Integer total_pembelian) {
        this.total_pembelian = total_pembelian;
    }

    public Integer getMeja() {
        return meja;
    }

    public void setMeja(Integer meja) {
        this.meja = meja;
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

    public data_transaksi(Integer id_transaksi, Integer id_kasir, Integer total_pembelian, Integer meja, String nama_kasir, String waktu) {
        this.id_transaksi = id_transaksi;
        this.id_kasir = id_kasir;
        this.total_pembelian = total_pembelian;
        this.meja = meja;
        this.nama_kasir = nama_kasir;
        this.waktu = waktu;
    }
}
