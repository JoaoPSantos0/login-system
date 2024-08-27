
package Controller;


import DAO.UsuarioDAO;
import Model.Usuario;
import db.DB;
import db.DbException;
import java.io.IOException;
import java.sql.Connection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;



public class RegistroController implements Initializable {

    @FXML
    private TextField textName;
    @FXML
    private TextField textEmail;
    private DatePicker dataPicker;
    @FXML
    private PasswordField textPassword;
    @FXML
    private PasswordField textConfirmPassword;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnSave;
    
    protected Usuario user;
    @FXML
    private Label labelInfo;
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    
    UsuarioDAO userDAO = null;
    @FXML
    private Label labelInfo2;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    public TextField getTextName() {
        return textName;
    }

    public void setTextName(TextField textName) {
        this.textName = textName;
    }

    public TextField getTextEmail() {
        return textEmail;
    }

    public void setTextEmail(TextField textEmail) {
        this.textEmail = textEmail;
    }

    public DatePicker getDataPicker() {
        return dataPicker;
    }

    public void setDataPicker(DatePicker dataPicker) {
        this.dataPicker = dataPicker;
    }

    public PasswordField getTextPassword() {
        return textPassword;
    }

    public void setTextPassword(PasswordField textPassword) {
        this.textPassword = textPassword;
    }

    public PasswordField getTextConfirmPassword() {
        return textConfirmPassword;
    }

    public void setTextConfirmPassword(PasswordField textConfirmPassword) {
        this.textConfirmPassword = textConfirmPassword;
    }
    
    @FXML
    public void cadastrarUsuario() throws ClassNotFoundException{
        
        Connection conn = new DB().getConnection();
        PreparedStatement test = null;
        ResultSet rs = null;
        
        
        //Logica para conferir se o usuario ja existe no banco;
        try{
            test = conn.prepareStatement("select * from user where nome = ? and senha = ?");
            test.setString(1, textName.getText());
            test.setString(2, textPassword.getText());
            rs = test.executeQuery();
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        
        UsuarioDAO user = new UsuarioDAO();
        try{
            if(rs.next()){
                labelInfo2.setText("User already exists");
                textName.setText("");
                textPassword.setText("");
                textEmail.setText("");
                textConfirmPassword.setText("");
            }
            else{
                user.registerUser(textName.getText(), textEmail.getText(), textPassword.getText());
                labelInfo.setText("Registrado com sucesso");
            }
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(test);
            DB.closeResultSet(rs);
        }
    }
    
    @FXML
    public void returnToLogin(ActionEvent e) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/View/login.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
   


    
    
}
