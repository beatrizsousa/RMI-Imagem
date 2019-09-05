/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexão;

import java.sql.*;
import javax.swing.*;

public class Conexao {
    final private String driver = "com.mysql.jdbc.Driver";
    final private String url = "jdbc:mysql://localhost:3306/imagens";
    
    final private String usuario = "root";
    final private String senha = "gameofufpi";
    public Connection con;
    public Statement statement;
    public ResultSet rs; 
    
    public boolean conecta() {
	boolean result = true;
	try{
	   Class.forName(driver);
	   con = DriverManager.getConnection(url, usuario, senha);
	}catch(ClassNotFoundException Driver){
            JOptionPane.showMessageDialog(null, "Driver não localizado: "+Driver);
            result = false;	
	}catch(SQLException Fonte){
            JOptionPane.showMessageDialog(null, "Deu erro na conexão com o Fonte de dados: "+Fonte);
            result = false;	
	}
	return result;
    }
    
    public boolean desconecta() {
	boolean result = true;
	try{
            con.close();
           
	}catch(SQLException fecha){
            JOptionPane.showMessageDialog(null, "Não foi possível fechar o banco de dados: "+fecha.getMessage());
            result = false;
	}
        return result;
    }
    
    public boolean executarSQL(String sql)
    {
       try {
           PreparedStatement sentencia = con.prepareStatement(sql);
          sentencia.execute();
          return true;
       } catch (SQLException ex) {
            return false;
       }
    }
    
    public void executeSQLSelect(String sql)
    {
	try
	{
		statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rs = statement.executeQuery(sql);
	}
	catch(SQLException sqlex)
	{
		JOptionPane.showMessageDialog(null, "Não foi possível executar o comando SQL: "+sqlex+", o sql passado foi: "+sql);
	}	
    }
    
}

