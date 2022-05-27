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
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author SAMSUNG
 */
public class Data_pendapatanController implements Initializable {

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
    private TableColumn<data_transaksi, String> tbwaktu;
    @FXML
    private DatePicker tanggalmulai;
    @FXML
    private DatePicker tanggalselesai;
    @FXML
    private ComboBox nama_kasir;
    @FXML
    private TextField tftotal_pendapatan;

    
    ObservableList<data_transaksi> list_transaksi;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nama_kasir.getItems().addAll("1","2","3","4","5","6","7","8","9");
        nama_kasir.setValue("1");
        
        try {
            // TODO
            readTable();
        } catch (SQLException ex) {
            Logger.getLogger(Data_pendapatanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            itung_total();
        } catch (SQLException ex) {
            Logger.getLogger(Data_pendapatanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    public void itung_total() throws SQLException{
    int sum = 0;
        String sql2 = "SELECT SUM(total_pembelian) as amount FROM transaksi"; 
        java.sql.Connection conn2 = (Connection)koneksidb.KoneksiDB();
        java.sql.PreparedStatement pst2 = conn2.prepareStatement(sql2);
        ResultSet rs = pst2.executeQuery();
        while(rs.next()){
            int c = (int) rs.getDouble("amount");
            sum = sum + c;
        }
        tftotal_pendapatan.setText(String.valueOf(sum));
    }
    @FXML
    private void filter2(ActionEvent event) throws SQLException {
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
            refreshList2(transaksian);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        int sum1 = 0;
        String sql = "SELECT SUM(total_pembelian) as amount FROM transaksi WHERE waktu BETWEEN '"+tglmulai+"' AND '"+tglselesai+"'"; 
        java.sql.Connection conn = (Connection)koneksidb.KoneksiDB();
        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            int c = (int) rs.getDouble("amount");
            sum1 = sum1 + c;
        }
        tftotal_pendapatan.setText(String.valueOf(sum1));
    }
    
    public void readTable() throws SQLException{
        tbid_transaksi.setCellValueFactory(new PropertyValueFactory<>("id_transaksi"));
        tbid_kasir.setCellValueFactory(new PropertyValueFactory<>("id_kasir"));
        tbnama_kasir.setCellValueFactory(new PropertyValueFactory<>("nama_kasir"));
        tbtotal_pembelian.setCellValueFactory(new PropertyValueFactory<>("total_pembelian"));
        tbwaktu.setCellValueFactory(new PropertyValueFactory<>("waktu"));
        
        list_transaksi = koneksidb.getDataTransaksi();
        TabelTransaksi.setItems(list_transaksi);
    }
    private void refreshList2(List<data_transaksi> transaksian) {
        ObservableList<data_transaksi> dokterModels = FXCollections.observableArrayList(transaksian);
        FilteredList<data_transaksi> filteredData = new FilteredList<>(dokterModels,predicate -> true);
        nama_kasir.valueProperty().addListener((observableValue, oldValue, newValue) -> {
        filteredData.setPredicate(dokter ->{
                    if (newValue == null)
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
