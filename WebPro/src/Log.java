
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Log
 */
@WebServlet("/Log")
public class Log extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Log() {
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
		response.getWriter().append("AKILESH: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user = request.getParameter("user"), pass = request.getParameter("pass");
		response.setContentType("text/html");
		Connection con;
		PrintWriter pw = response.getWriter();
		try {
			con = ConnectClass.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from userdata where username=?");
			ps.setString(1, user);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString(2).equals(pass)) {
					request.setAttribute("username", user);
					request.setAttribute("password", pass);
					request.setAttribute("name", rs.getString(3));
					request.setAttribute("age", rs.getString(4));
					request.setAttribute("phone", rs.getString(5));
					RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
					dis.forward(request, response);
				} else {
					request.setAttribute("check", 1);
					RequestDispatcher dis = request.getRequestDispatcher("Login.jsp");
					dis.forward(request, response);
				}
			} else {
				request.setAttribute("check", 1);
				RequestDispatcher dis = request.getRequestDispatcher("Login.jsp");
				dis.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
