/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafikacafe;

import database.koneksidb;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author SAMSUNG
 */
public class loginController implements Initializable {
    
    @FXML
    private Button btnLogin;

    @FXML
    private Label labelPeringatan;
    
    private String labelMessage = "";
    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    
    @FXML
    void login(ActionEvent event) throws SQLException, IOException {

String query = "SELECT * FROM akun WHERE username = ?";
        java.sql.Connection conn = (Connection)koneksidb.KoneksiDB();
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, username.getText());
        ResultSet result = st.executeQuery();

        if (result.next()) {
            if (password.getText().equals(result.getString("password"))) {
                if ("kasir".equals(result.getString("level"))) {
                    session.setGetId(result.getString("id_akun"));
                    session.setGetUsername(result.getString("username"));
                    session.setGetLevel(result.getString("level"));
                    ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("kasir.fxml"));
                    Parent root1 = fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.setMaximized(true);
                    stage.show();
                }else if("manajer".equals(result.getString("level"))) {
                    ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("manajer.fxml"));
                    Parent root1 = fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.setMaximized(true);
                    stage.show();
                }else {
                    ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin.fxml"));
                    Parent root1 = fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.setMaximized(true);
                    stage.show();
                }
            } else {
                labelMessage="Password Salah";
            }
        } else {
            labelMessage="Username tidak ditemukan";
        }
        labelPeringatan.setText(labelMessage);
        }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
