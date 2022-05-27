/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafikacafe;

import database.koneksidb;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author SAMSUNG
 */
public class KasirController implements Initializable {
    
    @FXML
    private ComboBox cbmeja;

    @FXML
    private TableView <data_menu> TabelMenu;
    @FXML
    private TableView <data_keranjang> TabelKeranjang;
    @FXML
    private TableColumn<data_menu, Integer> tbid_menu;
    @FXML
    private TableColumn<data_menu, String> tbnama_menu;
    @FXML
    private TableColumn<data_menu, Integer> tbharga;
    @FXML
    private TableColumn<data_menu, Integer> tbstock;
    @FXML
    private TableColumn<data_keranjang, Integer> tbid_keranjang;
    @FXML
    private TableColumn<data_keranjang, String> tbnama_menu2;
    @FXML
    private TableColumn<data_keranjang, Integer> tbid_menu2;
    @FXML
    private TableColumn<data_keranjang, Integer> tbharga2;
    @FXML
    private TableColumn<data_keranjang, Integer> tbjumlah;
    @FXML
    private TableColumn<data_keranjang, Integer> tbtotal_pembelian;
    
    @FXML
    private TextField tfid_menu;
    @FXML
    private TextField tfnama_menu;
    @FXML
    private TextField tfharga;
    @FXML
    private TextField tfstock;
    @FXML
    private TextField tfjumlah;
    @FXML
    private TextField tftotal_pembelian;
    @FXML
    private TextField tfkembalian;
    @FXML
    private TextField tfbayar;

    ObservableList<data_menu> list_menu;
    
    ObservableList<data_keranjang> list_keranjang;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cbmeja.getItems().addAll("1","2","3","4","5","6","7","8","9");
        cbmeja.setValue("1");
        
        try {
            // TODO
            readTable();
        } catch (SQLException ex) {
            Logger.getLogger(KasirController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            readTable2();
        } catch (SQLException ex) {
            Logger.getLogger(KasirController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            itung_total();
        } catch (SQLException ex) {
            Logger.getLogger(KasirController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @FXML
    void tambahkeranjang(ActionEvent event) throws SQLException {
        String id_menu = tfid_menu.getText().trim();
        String nama_menu = tfnama_menu.getText().trim();
        String harga = tfharga.getText().trim();
        String jumlah = tfjumlah.getText().trim();
        Integer total_pembelian = Integer.parseInt(harga)*Integer.parseInt(jumlah);
        
        String sql = "INSERT INTO keranjang VALUES(NULL,'"+id_menu+"','"+nama_menu+"','"+harga+"','"+jumlah+"','"+total_pembelian+"')"; 
        java.sql.Connection conn = (Connection)koneksidb.KoneksiDB();
        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
        pst.execute();
        
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Data Berhasil Ditambahkan");
        alert.showAndWait();
        
        readTable2();
        itung_total();
    }
    
    @FXML
    void checkout(ActionEvent event) throws SQLException {
        String id_kasir = session.getId();
        String nama_kasir = session.getUsername();
        String total_pembelian = tftotal_pembelian.getText();
        String meja = (String) cbmeja.getValue();
        DateTimeFormatter dtf= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String waktu = dtf.format(now);
        
        String sql = "INSERT INTO transaksi VALUES(NULL,'"+id_kasir+"','"+nama_kasir+"','"+total_pembelian+"','"+meja+"','"+waktu+"')"; 
        java.sql.Connection conn = (Connection)koneksidb.KoneksiDB();
        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
        pst.execute();
        
        if(Integer.parseInt(tfbayar.getText())<Integer.parseInt(tftotal_pembelian.getText())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Pembayaran Kurang");
            alert.showAndWait();
        }else{
        
        Integer kembalian = Integer.parseInt(tfbayar.getText())-Integer.parseInt(tftotal_pembelian.getText());
        tfkembalian.setText(String.valueOf(kembalian));
        
        
        
        String sql2 = "DELETE FROM keranjang"; 
        java.sql.Connection conn2 = (Connection)koneksidb.KoneksiDB();
        java.sql.PreparedStatement pst2 = conn2.prepareStatement(sql2);
        pst2.execute();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Transaksi Berhasil");
        alert.showAndWait();
        
        readTable2();
        tftotal_pembelian.setText("");
        }
    }
    public void itung_total() throws SQLException{
    int sum = 0;
        String sql2 = "SELECT SUM(total_pembelian) as amount FROM keranjang"; 
        java.sql.Connection conn2 = (Connection)koneksidb.KoneksiDB();
        java.sql.PreparedStatement pst2 = conn2.prepareStatement(sql2);
        ResultSet rs = pst2.executeQuery();
        while(rs.next()){
            int c = (int) rs.getDouble("amount");
            sum = sum + c;
        }
        tftotal_pembelian.setText(String.valueOf(sum));
    }
    
    public void readTable() throws SQLException{
        tbid_menu.setCellValueFactory(new PropertyValueFactory<>("id_menu"));
        tbnama_menu.setCellValueFactory(new PropertyValueFactory<>("nama_menu"));
        tbharga.setCellValueFactory(new PropertyValueFactory<>("harga"));
        tbstock.setCellValueFactory(new PropertyValueFactory<>("stok"));
        
        list_menu = koneksidb.getDataMenu();
        TabelMenu.setItems(list_menu);
    }
    
    public void readTable2() throws SQLException{
        tbid_keranjang.setCellValueFactory(new PropertyValueFactory<>("id_keranjang"));
        tbid_menu2.setCellValueFactory(new PropertyValueFactory<>("id_menu"));
        tbnama_menu2.setCellValueFactory(new PropertyValueFactory<>("nama_menu"));
        tbharga2.setCellValueFactory(new PropertyValueFactory<>("harga"));
        tbjumlah.setCellValueFactory(new PropertyValueFactory<>("jumlah"));
        tbtotal_pembelian.setCellValueFactory(new PropertyValueFactory<>("total_pembelian"));
        
        list_keranjang = koneksidb.getDataKeranjang();
        TabelKeranjang.setItems(list_keranjang);
    }
    @FXML
    void TableClick(MouseEvent event) {
        data_menu selectedPerson = TabelMenu.getSelectionModel().getSelectedItem();
        tfid_menu.setText(String.valueOf(selectedPerson.getId_menu()));
        tfharga.setText(String.valueOf(selectedPerson.getHarga()));
        tfstock.setText(String.valueOf(selectedPerson.getStok()));
        tfnama_menu.setText(selectedPerson.getNama_menu());
    }
}
