
package Controller;

import db.DB;
import db.DbException;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginController implements Initializable {

    @FXML
    private Button btnEnter;
    @FXML
    private TextField userText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Button btnRegister;
    
    private Stage stage;
    private Parent root;
    private Scene scene;
    @FXML
    private Label infoLabel;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public TextField getUserText() {
        return userText;
    }

    public void setUserText(TextField userText) {
        this.userText = userText;
    }

    public PasswordField getPasswordText() {
        return passwordText;
    }

    public void setPasswordText(PasswordField passwordText) {
        this.passwordText = passwordText;
    }
    
    @FXML
    public void onClickRegister(ActionEvent e) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/View/registro.fxml"));
           stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    
    public boolean authenticateUser()throws ClassNotFoundException{
        Connection conn = new DB().getConnection();
        PreparedStatement test = null;
        ResultSet rs = null;
        try{
            test = conn.prepareStatement("select * from user where nome = ? and senha = ?");
            test.setString(1, userText.getText());
            test.setString(2, passwordText.getText());
            rs = test.executeQuery();
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        try{
            if(rs.next()){
                return true;
            }
            else{
                return false;
            }
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }
    }
    
    public void onClickEnter(ActionEvent e) throws IOException, ClassNotFoundException{
        boolean test = this.authenticateUser();
        if(test){
            Parent root = FXMLLoader.load(getClass().getResource("/View/menu.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            userText.setText("");
            passwordText.setText("");
            infoLabel.setText("Usuario ou senha incorretos");
        }
        
    }
}
