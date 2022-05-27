/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafikacafe;

import database.koneksidb;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SAMSUNG
 */
public class Data_transaksiController implements Initializable {

    @FXML
    private TableView<data_transaksi> TabelTransaksi;
    @FXML
    private TableColumn<data_transaksi, Integer> tbid_transaksi;
    @FXML
    private TableColumn<data_transaksi, Integer> tbid_kasir;
    @FXML
    private TableColumn<data_transaksi, String> tbnama_kasir;
    @FXML
    private TableColumn<data_transaksi, Integer> tbtotal_pembelian;
    @FXML
    private TableColumn<data_transaksi, Integer> tbmeja;
    @FXML
    private TableColumn<data_transaksi, String> tbwaktu;
    @FXML
    private ComboBox<String> cbnama_kasir;
    @FXML
    private DatePicker tanggalmulai;

    @FXML
    private DatePicker tanggalselesai;
    
    ObservableList<data_transaksi> list_transaksi;

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            readTable();
        } catch (SQLException ex) {
            Logger.getLogger(Data_transaksiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            // TODO
            populateCategories();
        } catch (SQLException ex) {
            Logger.getLogger(Data_transaksiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    public void readTable() throws SQLException{
        tbid_transaksi.setCellValueFactory(new PropertyValueFactory<>("id_transaksi"));
        tbid_kasir.setCellValueFactory(new PropertyValueFactory<>("id_kasir"));
        tbnama_kasir.setCellValueFactory(new PropertyValueFactory<>("nama_kasir"));
        tbtotal_pembelian.setCellValueFactory(new PropertyValueFactory<>("total_pembelian"));
        tbmeja.setCellValueFactory(new PropertyValueFactory<>("meja"));
        tbwaktu.setCellValueFactory(new PropertyValueFactory<>("waktu"));
        
        list_transaksi = koneksidb.getDataTransaksi();
        TabelTransaksi.setItems(list_transaksi);
    }
    
    public void populateCategories() throws SQLException {
        java.sql.Connection conn = koneksidb.KoneksiDB();
        Statement st;
        ObservableList<String> list = FXCollections.observableArrayList();

        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM akun WHERE level = 'kasir'");
            while (rs.next()) {
                list.add(rs.getString("username"));

            }
        } catch (Exception ex) {

        }

        cbnama_kasir.setItems(null);
        cbnama_kasir.setItems(list);

        //To change body of generated methods, choose Tools | Templates.
    }
    @FXML
    private void filter(ActionEvent event) {
        String combotransaksi = (String) cbnama_kasir.getValue();
        try{
        List<data_transaksi> transaksian = new ArrayList<>();
            String filter = "";
            
            if(combotransaksi!=null){
                filter = "WHERE nama_kasir = '"+combotransaksi+"'";
            }else {
                System.out.println("Value combobox null!");
            }
            
            java.sql.Connection conn = koneksidb.KoneksiDB();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM transaksi "+filter+"");
            while (rs.next()) {
                transaksian.add(new data_transaksi(
                        rs.getInt("id_transaksi"),
                        rs.getInt("id_kasir"),
                        rs.getInt("total_pembelian"),
                        rs.getInt("meja"),
                        rs.getString("nama_kasir"),
                        rs.getString("waktu")
                ));
            }
            refreshList1(transaksian);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private void refreshList1(List<data_transaksi> transaksian) {
        ObservableList<data_transaksi> dokterModels = FXCollections.observableArrayList(transaksian);
        FilteredList<data_transaksi> filteredData = new FilteredList<>(dokterModels,predicate -> true);
        cbnama_kasir.valueProperty().addListener((observableValue, oldValue, newValue) -> {
        filteredData.setPredicate(dokter ->{
                    if (newValue == null|| newValue.isEmpty())
                        return true;
                    else{
                        return false;
                    }
        });
        });
        

        SortedList<data_transaksi> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(TabelTransaksi.comparatorProperty());//BINDING
        TabelTransaksi.setItems(sortedData);
    }
    
    @FXML
    private void filter2(ActionEvent event) {
        LocalDate tglmulai = tanggalmulai.getValue();
        LocalDate tglselesai = tanggalselesai.getValue();
        try{
        List<data_transaksi> transaksian = new ArrayList<>();
            String filter = "";
            
            if(tglmulai!=null){
                filter = "WHERE waktu BETWEEN '"+tglmulai+"' AND '"+tglselesai+"'";
            }else {
                System.out.println("Value null!");
            }
            
            java.sql.Connection conn = koneksidb.KoneksiDB();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM transaksi "+filter+"");
            while (rs.next()) {
                transaksian.add(new data_transaksi(
                        rs.getInt("id_transaksi"),
                        rs.getInt("id_kasir"),
                        rs.getInt("total_pembelian"),
                        rs.getInt("meja"),
                        rs.getString("nama_kasir"),
                        rs.getString("waktu")
                ));
            }
            refreshList1(transaksian);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    void datapendapatan(ActionEvent event) throws IOException {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("data_pendapatan.fxml"));
                    Parent root1 = fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.setMaximized(true);
                    stage.show();
    }
    
    private void refreshList2(List<data_transaksi> transaksian) {
        ObservableList<data_transaksi> dokterModels = FXCollections.observableArrayList(transaksian);
        FilteredList<data_transaksi> filteredData = new FilteredList<>(dokterModels,predicate -> true);
        cbnama_kasir.valueProperty().addListener((observableValue, oldValue, newValue) -> {
        filteredData.setPredicate(dokter ->{
                    if (newValue == null|| newValue.isEmpty())
                        return true;
                    else{
                        return false;
                    }
        });
        });
        

        SortedList<data_transaksi> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(TabelTransaksi.comparatorProperty());//BINDING
        TabelTransaksi.setItems(sortedData);
    }
    
}
