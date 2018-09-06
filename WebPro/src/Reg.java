
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
 * Servlet implementation class Reg
 */
@WebServlet("/Reg")
public class Reg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Reg() {
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
			PreparedStatement p = con.prepareStatement("select * from userdata where username=?");
			p.setString(1, user);
			int f = 0;
			ResultSet rs = p.executeQuery();
			if (rs.next()) {
				String a = rs.getString(1);
				if (a.equals(user)) {
					f = 1;
				}
			}
			if (f != 1) {
				p = con.prepareStatement("insert into userdata(username,password,name,age,phone) value(?,?,?,?,?)");
				p.setString(1, user);
				p.setString(2, pass);
				p.setString(3, name);
				p.setString(4, age);
				p.setString(5, phone);
				p.executeUpdate();
				request.setAttribute("created", 3);
				RequestDispatcher dis = request.getRequestDispatcher("Login.jsp");
				dis.forward(request, response);
			} else {
				request.setAttribute("uname", 2);
				RequestDispatcher dis = request.getRequestDispatcher("Login.jsp");
				dis.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
