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
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author SAMSUNG
 */
public class Data_aktivitas_adminController implements Initializable {

    @FXML
    private TableView<data_log> TabelAktivitas;

    @FXML
    private TableColumn<data_log, Integer> tbid_kasir;

    @FXML
    private TableColumn<data_log, Integer> tbid_log;

    @FXML
    private TableColumn<data_log, String> tbketerangan;

    @FXML
    private TableColumn<data_log, String> tbnama_kasir;

    @FXML
    private TableColumn<data_log, String> tbwaktu;

    
    ObservableList<data_log> list_log;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            readTable();
        } catch (SQLException ex) {
            Logger.getLogger(Data_aktivitas_adminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public void readTable() throws SQLException{
        tbid_log.setCellValueFactory(new PropertyValueFactory<>("id_log"));
        tbid_kasir.setCellValueFactory(new PropertyValueFactory<>("id_kasir"));
        tbnama_kasir.setCellValueFactory(new PropertyValueFactory<>("nama_kasir"));
        tbwaktu.setCellValueFactory(new PropertyValueFactory<>("waktu"));
        tbketerangan.setCellValueFactory(new PropertyValueFactory<>("keterangan"));
        
        list_log = koneksidb.getDataLog();
        TabelAktivitas.setItems(list_log);
    }
}
