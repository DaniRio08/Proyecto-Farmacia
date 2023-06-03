package entorns;

import java.util.*;

public abstract class Person {
	//Atributs
	protected String name;
	protected String mail;
	
	//Scanners
	Scanner sc = new Scanner(System.in);
	
	//Constructors
	public Person() {
	}

	public Person(String name, String mail) {
		this.setName(name);
		this.setMail(mail);
	}
	
	//Métodes
	public abstract void load(String id);
	
	
	//Getters y Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		while (name.length() > 100) {
			System.out.println("El nombre es demasiado largo");
			name = sc.nextLine();
		}
		this.name = name;
	}

	public String getMail() {
		return mail;
	}
	
	//Comprobació de que el mail de la persona tingui estructura de correu electrònic
	public void setMail(String mail) {
		while ((!mail.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) || (mail.length() > 50)) {
            System.out.print("El correo electrónico no es válido, asegúrate de que contiene '@': ");
            mail = sc.nextLine();
        }
		this.mail = mail;
	}
	
}