package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	public Connection getConexao(){
		Connection conexao = null;
		
		try {		
			Class.forName("oracle.jdbc.driver.OracleDriver");			
			conexao = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.5:1521:DBACENTER", "BUILDPACKAGE", "BUILDPACKAGE");
			System.out.println("Conectou!!");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return conexao;
	}
}
