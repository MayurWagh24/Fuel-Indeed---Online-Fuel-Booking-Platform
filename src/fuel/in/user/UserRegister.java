package fuel.in.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fuel.in.connect.Dbconnection;

/**
 * Servlet implementation class UserRegister
 */
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegister() {
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
		String name = request.getParameter("name");
		String city = request.getParameter("city");
		String number = request.getParameter("number");
		String address = request.getParameter("address");
		int pincode = Integer.parseInt(request.getParameter("pincode"));
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		
		
		
		
		Connection con = Dbconnection.connect();
		PreparedStatement ps ;
		try{
			int id=0;
		ps =con.prepareStatement("insert into user values(?,?,?,?,?,?,?,?)");
		ps.setInt(1,id);
		ps.setString(2, name);
		ps.setString(3,city);
		ps.setString(4,number );
		ps.setString(5, address);
		ps.setInt(6,pincode);
		ps.setString(7,email);
		ps.setString(8,password);
		
		
		int i=ps.executeUpdate();
		if(i>0)
		{
			System.out.println("Row Updated Count:"+i);
			response.sendRedirect("4_UserLogin.html");
		}else{
			response.sendRedirect("4_UserRegister.html");
		}
		
		
		
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
