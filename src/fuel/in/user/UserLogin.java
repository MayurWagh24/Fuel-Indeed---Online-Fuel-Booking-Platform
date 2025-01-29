package fuel.in.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fuel.in.connect.Dbconnection;
import fuel.in.getset.getsetvalues;

/**
 * Servlet implementation class UserLogin
 */
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println("Email:"+email+""+"password:"+password);
		
		Connection con = Dbconnection.connect();
		PreparedStatement ps ;
		try{
		ps =con.prepareStatement("select * from user where email=? and password=?");
		ps.setString(1, email);
		ps.setString(2, password);
		int i = 0;
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			int uId=rs.getInt(1);
			getsetvalues.setId(uId);
			i= 1;
			System.out.println("Valid User");
		}
		if(i>0)
		{
			response.sendRedirect("4_UserDashboard.html");
		}
		else{
			response.sendRedirect("4_UserLogin.html");
		}
		
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
