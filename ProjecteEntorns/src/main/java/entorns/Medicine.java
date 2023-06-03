package entorns;

import java.sql.ResultSet;

import java.sql.SQLException;

public class Medicine {
	//Atributs
	private int id;
	private String name;
	private float tMax;
	private float tMin;
	
	//Constructors
	public Medicine() {
		
	}
	
	public Medicine(int id, String name, float tMax, float tMin) {
		this.setId(id);
		this.setName(name);
		this.setTMax(tMax);
		this.setTMin(tMin);
	}
	
	
	//MÉTODES
	
	//Métode que carrega les dades del xip a la base de dades
	public void load (int id) {
		String query = "SELECT * FROM medicine where med_id ="+id+";";
		MariaDB bd = new MariaDB();
		bd.startConnection();
		ResultSet rs = bd.loadSelect(query);
		
		try {
	        if (rs.next()) { // Verifica si hi ha cualcuna fila el resultSet
	        	this.setId(rs.getInt("med_id"));
	            this.setName(rs.getString("name"));
	            this.setTMax(rs.getInt("tmax"));
	            this.setTMin(rs.getInt("tmin"));
	        } else {
	            System.out.println("No se encontró ningún registro para el id: " + id);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al cargar el medicamento: " + e.getMessage());
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

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public float getTMax() {
		return tMax;
	}
	public void setTMax(float tMax) {
		this.tMax = tMax;
	}

	public float getTMin() {
		return tMin;
	}
	public void setTMin(float tMin) {
		this.tMin = tMin;
	}
}
