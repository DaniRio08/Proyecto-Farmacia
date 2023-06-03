package entorns;

import java.sql.*;


public class MariaDB {
	private Connection con;
	private Statement st;
	
	//MÉTODES
	
	//Métode que inicia una conexió amb la base de dades
	public void startConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Error a MariaDB.startConnecction.Class: " + e.getMessage());
		}
		
		String url = "jdbc:mysql://localhost:3306/farmacia";
		//Estableix la conexió amb la base de dades desitjada
		try {
            con = DriverManager.getConnection(url, "root", "1234");
        } catch (SQLException e) {
            System.err.println("Error al iniciar la conexió amb la base de dades: " + e.getMessage());
        }
		
		try {
			st = con.createStatement();
		} catch (SQLException e) {
			System.err.println("Error al preparar el statement: " + e.getMessage());
		}
	}
	
	//Métode que serveix per carregar una consulta
	public ResultSet loadSelect(String query) {
		ResultSet rs;
		
        rs = null;
		try {
			rs = st.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("Error al executar la query a la base de dades 'Farmacia': " + e.getMessage());
		}
		return rs;
	}
	
	//Métode per modificar la taula doctor
	public void updateDoctor (String query) {
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Error al fer una modificació de la taula doctor: "+ e.getMessage());
		}
	}
	
	//Métode per modificar la taula xip
	public boolean updateXip (String query) {
		try {
			st.executeUpdate(query);
			return true;
		} catch (SQLException e) {
			System.out.println("Error al fer una modificació de la taula xip: " + e.getMessage());
			return false;
		}
	}
	
	
	//Métode que tanca la conexió
	public void closeConection() {
		try {
			st.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("Error al tancar la conexió: " + e.getMessage());
		}
	}
 }