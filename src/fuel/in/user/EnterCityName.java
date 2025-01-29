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
import fuel.in.getset.getsetcity;


/**
 * Servlet implementation class EnterCityName
 */
public class EnterCityName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnterCityName() {
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
       String cityname  = request.getParameter("cityname");
		
		System.out.println("city name:"+cityname);
		String city=cityname;
		getsetcity.setCity(city);
		
		
		Connection con = Dbconnection.connect();
		PreparedStatement pst;
		try{
			pst =con.prepareStatement("select * from fuelstation where city=?");
			pst.setString(1, cityname);
			
			int i = 0;
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				
				i= 1;
				System.out.println("Row Updated Count:"+i);
			}
			if(i>0){
				response.sendRedirect("4_ViewFuelStationByCity.jsp");
			}else{
				response.sendRedirect("4_EnterCityName.html");
			}
			
		
		
		
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
