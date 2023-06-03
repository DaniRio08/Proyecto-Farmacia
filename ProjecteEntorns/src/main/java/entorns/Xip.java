package entorns;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Xip {
	//Atributs
	private int id; //identificador físic del xip
	private Medicine medicine;
	private Patient patient;
	private LocalDate endDate;
	
	//Constructors
	public Xip() {
		
	}
	
	public Xip(int id, Medicine medicine, Patient patient, LocalDate endDate) {
		this.setId(id);
		this.setMedicine(medicine);
		this.setPatient(patient);
		this.setEndDate(endDate);
	}
	
	
	//MÉTODES
	
	//Métode que carrega les dades del xip a la base de dades
	public void load (int id) {
		String query = "SELECT * FROM xip where xip_id ="+id+";";
		MariaDB bd = new MariaDB();
		bd.startConnection();
		ResultSet rs = bd.loadSelect(query);
		
		try {
	        if (rs.next()) { // Verifica si hi ha cualcuna fila el resultSet
	        	this.setId(rs.getInt("xip_id"));
	        	java.sql.Date sqlDate = rs.getDate("end_date");
	        	this.setEndDate(sqlDate.toLocalDate());
	        	Patient p = new Patient();
	        	p.load(rs.getString("patient_mail"));
	        	this.setPatient(p);
	        	Medicine m = new Medicine();
	        	m.load(rs.getInt("id_medicine"));
	        	this.setMedicine(m);
	        } else {
	            System.out.println("No se encontró ningún registro para el id: " + id);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al cargar el xip: " + e.getMessage());
	    } finally {
	    	bd.closeConection();
	    }
	}
	
	//Getters y Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Medicine getMedicine() {
		return medicine;
	}
	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
}