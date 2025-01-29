package fuel.in.station;

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
 * Servlet implementation class AddDeliveryPerson
 */
public class AddDeliveryPerson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDeliveryPerson() {
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
		int sId = Integer.parseInt(request.getParameter("sId"));
		String name = request.getParameter("name");
		String city = request.getParameter("city");
		String number =request.getParameter("number");
		String address = request.getParameter("address");
		int pincode = Integer.parseInt(request.getParameter("pincode"));
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		Connection con = Dbconnection.connect();
		PreparedStatement ps ;
		try{
			int id=0;
		ps =con.prepareStatement("insert into deliveryperson values(?,?,?,?,?,?,?,?,?)");
		ps.setInt(1,id);
		ps.setInt(2,sId);
		ps.setString(3, name);
		ps.setString(4,city);
		ps.setString(5,number );
		ps.setString(6, address);
		ps.setInt(7,pincode);
		ps.setString(8,email);
		ps.setString(9,password);
		
		
		int i=ps.executeUpdate();
		if(i>0)
		{
			System.out.println("Row Updated Count:"+i);
			response.sendRedirect("2_UpdateDeliveryPerson.html");
		}else{
			response.sendRedirect("2_AddDeliveryPerson.html");
		}
		
		
		
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
