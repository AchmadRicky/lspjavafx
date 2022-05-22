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
public class data_menu {
    Integer id_menu,harga,stok;
    String nama_menu;

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

    public Integer getStok() {
        return stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }

    public String getNama_menu() {
        return nama_menu;
    }

    public void setNama_menu(String nama_menu) {
        this.nama_menu = nama_menu;
    }

    public data_menu(Integer id_menu, Integer harga, Integer stok, String nama_menu) {
        this.id_menu = id_menu;
        this.harga = harga;
        this.stok = stok;
        this.nama_menu = nama_menu;
    }
}
