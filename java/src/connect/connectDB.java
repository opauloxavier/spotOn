package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class connectDB {
	
	 public static Connection conn = null;
	 
	 public static void connect(){
		 if (conn == null) {
				try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/px_recommendations","root", "over5574");
	
				} catch (SQLException e) {
					System.out.println("Connection Failed! Check output console");
					e.printStackTrace();
					return;
				}
				
				if (conn != null) {
					System.out.println("Conexão OK");
				} else {
					System.out.println("Erro ao acessar o Banco");
				}
		 }
		 
		 else{
			 System.out.println("Ja conectou");
		 }
			
	 }
	 
	 public static ResultSet doQuery(String Query) throws SQLException{
		 
			if(conn==null) {
				connect();
			}
		 
		 Statement stmt = null;
		 ResultSet results = null;
		 try{
		 	stmt = conn.createStatement();
		    results = stmt.executeQuery(Query);
		 }
		 
		 catch (SQLException e ) {
			 	System.out.println(e);
		    }
		 
		 finally{
			 // if (stmt != null) {
				//  stmt.close();
			//}
		 }
		    return results;
	 }
	 
	 public static ArrayList<String> getCantores() throws SQLException{
		if(conn==null) {
			connect();
		}
		 
		 ArrayList<String> cantores = new ArrayList<String>();
		
		 ResultSet results = doQuery("Select * from px_cantores");
		 
		 while (results.next()) {
	 		 	cantores.add(results.getString("nome_cantor"));
	        }
		 
		 return cantores;
	 }
	 
	 public static void main(String[] argv) throws SQLException {
		 	connect();
		 	
		 	ArrayList<String>a = new ArrayList();
		 	
		 	a=getCantores();
		 	
		 	System.out.println(a.size());
		 
		 	
		  }


	
}
