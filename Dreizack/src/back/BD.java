package back;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BD {
	
	public Connection con = null;
	public PreparedStatement st = null;
	public ResultSet rs = null;
	
	private String driver = "";
	private String databaseName = "";
	private String url = "";
	private String login = "";
	private String password = ""; 
	
	public BD() {
		try {
			BufferedReader br = 
				new BufferedReader(new FileReader("banco.txt"));
			driver = br.readLine();
			databaseName = br.readLine();
			url = br.readLine()+databaseName;
			login = br.readLine();
			password = br.readLine();
			br.close();
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}
	
	public boolean getConnection() {
		try {
			Class.forName(driver); //carrega o driver
			con = DriverManager.getConnection(url,login,password);
			System.out.println("Conectou...");
			return true;
		}
		catch(SQLException e) {
			System.out.println("Falha na conexão " + e);
		}
		catch(ClassNotFoundException e) {
			System.out.println("Driver não encontrado!");
		}
		return false;
	}

	public void close() {
		try {
			if(rs!=null) rs.close();
		}
		catch(SQLException e) {}
		try {
			if(st!=null) st.close();
		}
		catch(SQLException e) {}
		try {
			if(con!=null) {
				con.close();
				System.out.println("Desconectou...");
			}
		}
		catch(SQLException e) {}
	}

	public static void main(String[] args) {
		BD bd = new BD();
		bd.getConnection();
		//executar uma ação
		bd.close();
	}
}