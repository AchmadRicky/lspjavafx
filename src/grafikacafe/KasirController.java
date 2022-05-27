/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafikacafe;

import database.koneksidb;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    ObservableList<data_menu> list_menu;
    
    ObservableList<data_keranjang> list_keranjang;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
