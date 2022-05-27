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
public class data_keranjang {
    Integer id_keranjang,id_menu,harga,jumlah,total_pembelian;
    String nama_menu;

    public Integer getId_keranjang() {
        return id_keranjang;
    }

    public void setId_keranjang(Integer id_keranjang) {
        this.id_keranjang = id_keranjang;
    }

    public Integer getId_menu() {
        return id_menu;
    }

    public void setId_menu(Integer id_menu) {
        this.id_menu = id_menu;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Integer getTotal_pembelian() {
        return total_pembelian;
    }

    public void setTotal_pembelian(Integer total_pembelian) {
        this.total_pembelian = total_pembelian;
    }

    public String getNama_menu() {
        return nama_menu;
    }

    public void setNama_menu(String nama_menu) {
        this.nama_menu = nama_menu;
    }

    public data_keranjang(Integer id_keranjang, Integer id_menu, Integer harga, Integer jumlah, Integer total_pembelian, String nama_menu) {
        this.id_keranjang = id_keranjang;
        this.id_menu = id_menu;
        this.harga = harga;
        this.jumlah = jumlah;
        this.total_pembelian = total_pembelian;
        this.nama_menu = nama_menu;
    }
}
