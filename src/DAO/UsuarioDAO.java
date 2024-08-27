
package DAO;

import Model.Usuario;
import db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import db.DbException;
import java.sql.ResultSet;


public class UsuarioDAO {

    public UsuarioDAO() {
        
        
    }
    
    
    
    public void registerUser(String nome, String email, String senha) throws ClassNotFoundException{
        Connection conn = new DB().getConnection();
        try{
            String sql = "insert into user (nome, senha,email) values (?,?,?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, nome);
            st.setString(2,email);
            st.setString(3, senha);
            st.execute();
            //ResultSet rs = st.executeQuery(sql);
            DB.closeConnection();
            
        }catch(SQLException e){
        
            throw new DbException(e.getMessage());        
        }
        
        
    }
}
