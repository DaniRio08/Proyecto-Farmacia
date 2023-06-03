package entorns;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Patient extends Person {
	//No té atributs nous a part del de Person
	
	//Constructors
	public Patient() {
		super();
	}

	public Patient(String name, String mail) {
		super(name, mail);
	}

	
	//MÉTODES
	
	//Métode heredat de la classe persona que carrega a l'objecte les dades del pacient
	public void load (String id) {
		String query = "SELECT * FROM patient where mail ='"+id+"';";
		MariaDB bd = new MariaDB();
		bd.startConnection();
		ResultSet rs = bd.loadSelect(query);
		
		try {
	        if (rs.next()) { // Verifica si hi ha cualcuna fila el resultSet
	            this.setName(rs.getString("name"));
	            this.setMail(rs.getString("mail"));
	        } else {
	            System.out.println("No se encontró ningún registro para el correo: " + id);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al cargar el paciente: " + e.getMessage());
	    } finally {
	    	bd.closeConection();
	    }
	}
}