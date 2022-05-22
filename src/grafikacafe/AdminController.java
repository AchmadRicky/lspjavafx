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
import java.time.LocalDate;
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
import javafx.scene.control.ChoiceBox;
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
public class AdminController implements Initializable {

    @FXML
    private TextField tf_id_akun;
    @FXML
    private TextField tfusername;
    @FXML
    private TextField tfpassword;
    @FXML
    private ChoiceBox cblevel;
    @FXML
    private TableView<data_user> tabelUser;

    @FXML
    private TableColumn<data_user, String> tb_id_akun;

    @FXML
    private TableColumn<data_user, String> tb_level;

    @FXML
    private TableColumn<data_user, String> tb_username;
    
    ObservableList<data_user> list_user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cblevel.getItems().addAll("admin","manajer","kasir");
        cblevel.setValue("kasir");
        
        try {
            readTable();
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    
    
    @FXML
    void btnTambah(ActionEvent event) throws SQLException {
        
        String username = tfusername.getText().trim();
        String password = tfpassword.getText().trim();
        String level = (String) cblevel.getValue();
        
        String sql = "INSERT INTO akun VALUES(NULL,'"+username+"','"+password+"','"+level+"')"; 
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
    private void btnUpdate(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Apakah Anda Ingin Mengupdate Data?");
        alert.setHeaderText(null);
        alert.setContentText("Tekan OK untuk Update Data");
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            String id_akun = tf_id_akun.getText().trim();
            String username = tfusername.getText().trim();
            String password = tfpassword.getText().trim();
            String level = (String) cblevel.getValue();

            String query = "UPDATE akun SET username= '"+username+"', password = '"+password+"', level= '"+level+"' WHERE id_akun= '"+id_akun+"'";
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
    private void btnDelete(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Apakah Anda Ingin Menghapus Data?");
        alert.setHeaderText(null);
        alert.setContentText("Tekan OK untuk Hapus Data");
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            String query = "DELETE FROM akun WHERE id_akun = ?";
            java.sql.Connection conn = (Connection)koneksidb.KoneksiDB();
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, tf_id_akun.getText());
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
        tf_id_akun.setText("");
        tfusername.setText("");
        tfpassword.setText("");
        cblevel.setValue("");
    }
    
    @FXML
    private void TableClick(MouseEvent event) {
        data_user selectedPerson = tabelUser.getSelectionModel().getSelectedItem();
        tf_id_akun.setText(String.valueOf(selectedPerson.getId_akun()));
        tfusername.setText(selectedPerson.getUsername());
        tfpassword.setText(selectedPerson.getPassword());
        cblevel.setValue(selectedPerson.getLevel());
        
    }
    
    @FXML
    void dataaktivitas(ActionEvent event) throws IOException {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("data_aktivitas_admin.fxml"));
                    Parent root1 = fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.setMaximized(true);
                    stage.show();
    }
    public void readTable() throws SQLException{
        tb_id_akun.setCellValueFactory(new PropertyValueFactory<>("id_akun"));
        tb_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        tb_level.setCellValueFactory(new PropertyValueFactory<>("level"));
        
        list_user = koneksidb.getDataUser();
        tabelUser.setItems(list_user);
    }
    
}
