
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Update() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user = request.getParameter("user"), pass = request.getParameter("pass"),
				name = request.getParameter("name"), age = request.getParameter("age"),
				phone = request.getParameter("phone");
		response.setContentType("text/html");
		Connection con;
		try {
			con = ConnectClass.getConnection();
			PreparedStatement ps = con
					.prepareStatement("update userdata set password=?,name=?,age=?,phone=? where username=?");
			ps.setString(1, pass);
			ps.setString(2, name);
			ps.setString(3, age);
			ps.setString(4, phone);
			ps.setString(5, user);
			ps.executeUpdate();
			request.setAttribute("username", user);
			request.setAttribute("password", pass);
			request.setAttribute("name", name);
			request.setAttribute("age", age);
			request.setAttribute("phone", phone);
			request.setAttribute("updated", 1);
			RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
			dis.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
