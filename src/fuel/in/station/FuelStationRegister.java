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
 * Servlet implementation class FuelStationRegister
 */
public class FuelStationRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FuelStationRegister() {
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
		String name = request.getParameter("name");
		String otime = request.getParameter("otime");
		String ctime = request.getParameter("ctime");
		String address = request.getParameter("address");
		String area = request.getParameter("area");
		String city = request.getParameter("city");
		int pincode = Integer.parseInt(request.getParameter("pincode"));
		float pquantity = Float.parseFloat(request.getParameter("pquantity"));
		int prate = Integer.parseInt(request.getParameter("prate"));
		float dquantity = Float.parseFloat(request.getParameter("dquantity"));
		int drate = Integer.parseInt(request.getParameter("drate"));
		String number = request.getParameter("number");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String status = ("Pending");
		
		
		
		
		
		Connection con = Dbconnection.connect();
		PreparedStatement ps ;
		try{
			int id=0;
		ps =con.prepareStatement("insert into fuelstation values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		ps.setInt(1,id);
		ps.setString(2, name);
		ps.setString(3, otime);
		ps.setString(4, ctime);
		ps.setString(5, address);
		ps.setString(6, area);
		ps.setString(7,city);
		ps.setInt(8,pincode);
		ps.setFloat(9,  pquantity);
		ps.setInt(10, prate);
		ps.setFloat(11, dquantity);
		ps.setInt(12,drate);
		ps.setString(13,number );
		ps.setString(14,email);
		ps.setString(15,password);
		ps.setString(16,status);
		
		int i=ps.executeUpdate();
		if(i>0)
		{
			System.out.println("Row Updated Count:"+i);
			response.sendRedirect("2_FuelStationLogin.html");
		}else{
			response.sendRedirect("2_FuelStationRegister.html");
		}
		
		
		
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
