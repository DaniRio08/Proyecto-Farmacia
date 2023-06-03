package entorns;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServXips
 */
@WebServlet("/ServXips")
public class ServXips extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServXips() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mail = request.getParameter("mail");
		Long session = Long.parseLong(request.getParameter("session"));
		
		Doctor d = new Doctor();
		if (d.isLogged(mail, session)) {
			d.load(mail);
			d.loadReleaseList();
			String tabla = d.getTable();
			response.getWriter().append(tabla);
		} else {
			System.out.println("El doctor no está loggeado, no puede acceder a los datos de los xips");
			response.getWriter().append("notLogged");
		}
	}

}
