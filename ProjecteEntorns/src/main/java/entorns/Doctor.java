
package entorns;

import java.sql.*;

import java.time.LocalDate;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class Doctor extends Person {
	//Atributs
	private String pass;
	private LocalDate last_log;
	private long session;
	private ArrayList<Xip> releaseList;
	
	//Scanners
	Scanner sc = new Scanner(System.in);

	//Constructors
	public Doctor() {
		super();
		this.setReleaseList();
	}

	public Doctor(String name, String mail, String pass, LocalDate last_log, long session) {
		super(name, mail);
		this.setPass(pass);
		this.setLastLog(last_log);
		this.setSession(session);
		this.setReleaseList();
	}

	
	//MÉTODES
	
	
	//Métode heredat de la classe persona que carrega a l'objecte les dades del doctor
	public void load (String id) {
		String query = "SELECT * FROM doctor where mail ='"+id+"';";
		MariaDB bd = new MariaDB();
		bd.startConnection();
		ResultSet rs = bd.loadSelect(query);
		
		try {
	        if (rs.next()) { // Verifica si hi ha cualcuna fila el resultSet
	            this.setName(rs.getString("name"));
	            this.setMail(rs.getString("mail"));
	            this.setPass(rs.getString("pass"));
	        } else {
	            System.out.println("No se encontró ningún registro para el correo: " + id);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al cargar el doctor: " + e.getMessage());
	    } finally {
	    	bd.closeConection();
	    }
	}
	
	//Métode per fer login del doctor
	public void login (String mail, String pass) {
		String query = "SELECT * FROM doctor where mail ='"+mail+"' AND pass='"+pass+"';";
		MariaDB bd = new MariaDB();
		bd.startConnection();
		ResultSet rs = bd.loadSelect(query);
		try {
			if (rs.next()) {
				this.setLastLog(LocalDate.now());
				Random random = new Random();
				String code = "";
				for (int i= 0; i<10;i++) {
					code+=random.nextInt(10);
				}
				long session = Long.parseLong(code);
				
				this.setSession(session);
								
				query = "UPDATE doctor SET last_log= '"+this.getLastLog()+"',session= '"+this.getSession()+"' WHERE mail='"+mail+"';";
				bd.updateDoctor(query);
				
				this.load(mail);
			} else {
				System.out.println("El doctor no existe y por lo tanto no se han podido cargar los datos.");
			}
		} catch (NumberFormatException e) {
			System.out.println("Error en el formato del número de sesion: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Error en doctor.login" + e.getMessage());
		} finally {
	    	bd.closeConection();
	    }
	}
	
	//Métode que retorna un boolea que indica true si troba el mail amb la session en data
	public boolean isLogged(String mail, long Session) {
		String query = "SELECT * FROM doctor where mail ='"+mail+"' and session = '"+Session+"';";
		MariaDB bd = new MariaDB();
		bd.startConnection();
		ResultSet rs = bd.loadSelect(query);
		
		LocalDate lastLogged = null;
		
		try {
	        if (rs.next()) { // Verifica si hi ha cualcuna fila el resultSet
	        	java.sql.Date sqlDate = rs.getDate("last_log");
	            lastLogged = sqlDate.toLocalDate();
	        } else {
	            System.out.println("No se encontró ningún registro para el correo: " + mail);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al comprobar si el doctor está loggeado: " + e.getMessage());
	    } finally {
	    	bd.closeConection();
	    }
		return lastLogged != null && lastLogged.isEqual(LocalDate.now());
	}
	
	//Métode que carrega a l'array del doctor tots el xips que estiguin en data vinculats a ell
	public void loadReleaseList() {
		String query = "SELECT * FROM xip where doctor_mail ='"+this.mail+"';";
		MariaDB bd = new MariaDB();
		bd.startConnection();
		ResultSet rs = bd.loadSelect(query);
		
		try {
			while (rs.next()) {
				Xip xip = new Xip();
				int id_xip = rs.getInt("xip_id");
				xip.load(id_xip);
				this.getReleaseList().add(xip);
			}
		} catch (SQLException e) {
	        System.out.println("Error al cargar la lista de xips del doctor: " + e.getMessage());
	    } finally {
	    	bd.closeConection();
	    }
	}
	
	//Métode que retorna un String que correspon a una taula HTML de tots els xips d'alta, vigents y vinculats al doctor
	public String getTable() {
		String html =
		"<h1>Tabla de los chips asociados al doctor " + this.name + "</h1>" +
		"<table>" +
	    "<tr>" +
	        "<th>ID Chip</th>" +
	        "<th>Medicamento</th>" +
	        "<th>Paciente</th>" +
	        "<th>Fecha finalización (yyyy-mm-dd)</th>" +
	    "</tr>";
		
		for (int i = 0; i < this.releaseList.size(); i++) {
			if (this.releaseList.get(i).getEndDate().isAfter(LocalDate.now()))//Comporbam que el xip està vigent
				html+= 
				 "<tr>" +
			        "<td>"+this.releaseList.get(i).getId()+"</th>" +
			        "<td>"+this.releaseList.get(i).getMedicine().getName()+"</th>" +
			        "<td>"+this.releaseList.get(i).getPatient().getName()+"</th>" +
			        "<td>"+this.releaseList.get(i).getEndDate()+"</th>" +
			    "</tr>";
			
		}
	    html += "</table>";	    
		return html;
	}
	
	//Métode que cerca tots els pacients a la base de dades y els retorna en forma de JSON
	public String getPatients() {
		String jsonString = null;
		JSONArray ja = new JSONArray();
		
		//Consulta a la base de dades que retorna tots els pacients
		String query = "SELECT * FROM patient;";
		MariaDB bd = new MariaDB();
		bd.startConnection();
		ResultSet rs = bd.loadSelect(query);
		
		
		try {
			while (rs.next()) {
				String mailPacient = rs.getString("mail");
				ja.put(mailPacient);
			}
			jsonString = ja.toString();
		} catch (SQLException e) {
	        System.out.println("Error al obtener los pacientes de la base de datos: " + e.getMessage());
	    } finally {
	    	bd.closeConection();
	    }
		return jsonString;
	}
	
	//Métode que cerca els medicaments a la base de dades y retorna un array d'objectes JSON
	public String getMedicines() {
		String jsonString = null;
		JSONArray ja = new JSONArray();
		
		//Consulta a la base de dades que retorna tots els medicaments
		String query = "SELECT * FROM medicine;";
		MariaDB bd = new MariaDB();
		bd.startConnection();
		ResultSet rs = bd.loadSelect(query);
		
		
		try {
			while (rs.next()) {
				Medicine med = new Medicine();
				med.setId(rs.getInt("med_id"));
				med.setName(rs.getString("name"));
				med.setTMax(rs.getInt("tmax"));
				med.setTMin(rs.getInt("tmin"));
				JSONObject json = new JSONObject(med);
				ja.put(json);
			}
			jsonString = ja.toString();
		} catch (SQLException e) {
	        System.out.println("Error al obtener los pacientes de la base de datos: " + e.getMessage());
	    } finally {
	    	bd.closeConection();
	    }
		return jsonString;
	}
	
	public String insertarXip(int idXip, String mail, int idMed, String mailP, String date) {
		MariaDB bd = new MariaDB();
		bd.startConnection();
		String query = "INSERT INTO xip VALUES ("+idXip+", '"+mail+"', "+idMed+", '"+mailP+"', '"+date+"');";
		boolean resultado = bd.updateXip(query);
		bd.closeConection();
		if (resultado) {
			return "ok";
		} else {
			return "not ok";
		}
	}
	
	
	//Getters y Setters
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

	public LocalDate getLastLog() {
		return last_log;
	}
	public void setLastLog(LocalDate last_log) {
		this.last_log = last_log;
	}

	public long getSession() {
		return session;
	}
	public void setSession(long session) {
		this.session = session;
	}

	public ArrayList<Xip> getReleaseList() {
		return releaseList;
	}
	public void setReleaseList() {
		this.releaseList = new ArrayList<Xip>();
	}
	
}