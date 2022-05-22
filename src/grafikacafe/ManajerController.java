/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafikacafe;

import database.koneksidb;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SAMSUNG
 */
public class ManajerController implements Initializable {
    @FXML
    private TableView<data_menu> TabelMenu;
    @FXML
    private TableColumn<data_menu, Integer> tbid_menu;
    @FXML
    private TableColumn<data_menu, String> tbnama_menu;
    @FXML
    private TableColumn<data_menu, Integer> tbharga;
    @FXML
    private TableColumn<data_menu, Integer> tbstock;
    @FXML
    private TextField tfid_menu;
    @FXML
    private TextField tfnama_menu;
    @FXML
    private TextField tfharga;
    @FXML
    private TextField tfstock;

    
    ObservableList<data_menu> list_menu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            readTable();
        } catch (SQLException ex) {
            Logger.getLogger(ManajerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    void TableClick(MouseEvent event) {
        data_menu selectedPerson = TabelMenu.getSelectionModel().getSelectedItem();
        tfid_menu.setText(String.valueOf(selectedPerson.getId_menu()));
        tfharga.setText(String.valueOf(selectedPerson.getHarga()));
        tfstock.setText(String.valueOf(selectedPerson.getStok()));
        tfnama_menu.setText(selectedPerson.getNama_menu());
    }
    @FXML
    void btnTambah(ActionEvent event) throws SQLException {
        String nama_menu = tfnama_menu.getText().trim();
        String harga = tfharga.getText().trim();
        String stok = tfstock.getText().trim();
        
        String sql = "INSERT INTO menu VALUES(NULL,'"+nama_menu+"','"+harga+"','"+stok+"')"; 
        java.sql.Connection conn = (Connection)koneksidb.KoneksiDB();
        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
        pst.execute();
        
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Data Berhasil Ditambahkan");
        alert.showAndWait();
        
        readTable();
    }
    @FXML
    void btnUpdate(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Apakah Anda Ingin Mengupdate Data?");
        alert.setHeaderText(null);
        alert.setContentText("Tekan OK untuk Update Data");
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            String id_menu = tfid_menu.getText().trim();
            String nama_menu = tfnama_menu.getText().trim();
            String harga = tfharga.getText().trim();
            String stok = tfstock.getText().trim();

            String query = "UPDATE menu SET nama_menu= '"+nama_menu+"', harga = '"+harga+"', stok= '"+stok+"' WHERE id_menu= '"+id_menu+"'";
            java.sql.Connection conn = (Connection)koneksidb.KoneksiDB();
            PreparedStatement pst = conn.prepareStatement(query);
            pst.execute();
        
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Information Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Data Berhasil DiUpdate");
            alert1.showAndWait();
        } else {
            alert.close();
        }
        readTable();
    }
    @FXML
    void btnDelete(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Apakah Anda Ingin Menghapus Data?");
        alert.setHeaderText(null);
        alert.setContentText("Tekan OK untuk Hapus Data");
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            String query = "DELETE FROM menu WHERE id_menu= ?";
            java.sql.Connection conn = (Connection)koneksidb.KoneksiDB();
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, tfid_menu.getText());
            pst.execute();
        
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Information Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("Data Berhasil Dihapus");
            alert1.showAndWait();
        } else {
            alert.close();
        }

        readTable();
        tfid_menu.setText("");
        tfharga.setText("");
        tfstock.setText("");
        tfnama_menu.setText("");
    }
    @FXML
    void dataaktivitas(ActionEvent event) throws IOException {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("data_aktivitas_manajer.fxml"));
                    Parent root1 = fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.setMaximized(true);
                    stage.show();
    }
    public void readTable() throws SQLException{
        tbid_menu.setCellValueFactory(new PropertyValueFactory<>("id_menu"));
        tbnama_menu.setCellValueFactory(new PropertyValueFactory<>("nama_menu"));
        tbharga.setCellValueFactory(new PropertyValueFactory<>("harga"));
        tbstock.setCellValueFactory(new PropertyValueFactory<>("stok"));
        
        list_menu = koneksidb.getDataMenu();
        TabelMenu.setItems(list_menu);
    }
}
