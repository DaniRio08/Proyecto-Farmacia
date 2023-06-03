package entorns;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Release
 */
@WebServlet("/Release")
public class Release extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Release() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mail = request.getParameter("mail");
		Long session = Long.parseLong(request.getParameter("session"));
		int idXip = Integer.parseInt(request.getParameter("idXip"));
		int idMed = Integer.parseInt(request.getParameter("idMed"));
		String mailP = request.getParameter("mailP");
		String date = request.getParameter("date");
		
		Doctor d = new Doctor();
		if (d.isLogged(mail, session)) {
			d.load(mail);
			String respuesta = d.insertarXip(idXip, mail, idMed, mailP, date);
			response.getWriter().append(respuesta);
		}
	}

}
