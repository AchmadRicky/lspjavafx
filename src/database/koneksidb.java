/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import grafikacafe.data_log;
import grafikacafe.data_menu;
import grafikacafe.data_transaksi;
import grafikacafe.data_user;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author SAMSUNG
 */
public class koneksidb {
    private static Connection koneksi;
    
    public static Connection KoneksiDB() throws SQLException{
        if(koneksi==null){
            new com.mysql.jdbc.Driver();
            koneksi = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/grafika_cafe","root","");
        }
        return koneksi;
    }
    public static ObservableList<data_user> getDataUser() throws SQLException {
        Connection conn = KoneksiDB();
        ObservableList<data_user> list = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM akun";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new data_user(
                        rs.getInt("id_akun"),
                        rs.getString("username"),
                        rs.getString("level"),
                        rs.getString("password")
                ));
            }

        } catch (Exception e) {
        }
        return list;
    }
    
    public static ObservableList<data_menu> getDataMenu() throws SQLException {
        Connection conn = KoneksiDB();
        ObservableList<data_menu> list = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM menu";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new data_menu(
                        rs.getInt("id_menu"),
                        rs.getInt("harga"),
                        rs.getInt("stok"),
                        rs.getString("nama_menu")
                ));
            }

        } catch (Exception e) {
        }
        return list;
    }
    
    public static ObservableList<data_log> getDataLog() throws SQLException {
        Connection conn = KoneksiDB();
        ObservableList<data_log> list = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM log";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new data_log(
                        rs.getInt("id_log"),
                        rs.getInt("id_kasir"),
                        rs.getString("nama_kasir"),
                        rs.getString("waktu"),
                        rs.getString("keterangan")
                ));
            }

        } catch (Exception e) {
        }
        return list;
    }
    
    public static ObservableList<data_transaksi> getDataTransaksi() throws SQLException {
        Connection conn = KoneksiDB();
        ObservableList<data_transaksi> list = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM transaksi";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new data_transaksi(
                        rs.getInt("id_transaksi"),
                        rs.getInt("id_kasir"),
                        rs.getInt("total_pembelian"),
                        rs.getInt("meja"),
                        rs.getString("nama_kasir"),
                        rs.getString("waktu")
                ));
            }

        } catch (Exception e) {
        }
        return list;
    }
   
    }