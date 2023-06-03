package entorns;

import java.io.IOException;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServMedicines
 */
@WebServlet("/ServMedicines")
public class ServMedicines extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServMedicines() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mail = request.getParameter("mail");
		Long session = Long.parseLong(request.getParameter("session"));
		String resposta = "[]";
			
		Doctor d = new Doctor();
		if (d.isLogged(mail, session)) {
			d.load(mail);
			resposta = d.getMedicines();
		} else {
			System.out.println("El doctor no está loggeado, no puede acceder a los datos de los pacientes");
		}
		response.getWriter().append(resposta);
	}
}
