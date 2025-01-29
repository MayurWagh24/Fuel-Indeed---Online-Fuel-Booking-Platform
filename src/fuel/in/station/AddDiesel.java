package fuel.in.station;

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
 * Servlet implementation class AddDiesel
 */
public class AddDiesel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDiesel() {
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
Float ndquantity  = Float.parseFloat(request.getParameter("dquantity"));
		
		System.out.println("Diesel Quantity:"+ndquantity);
		int sId=getsetvalues.getId();
		float dquantity=0;
		
		Connection con = Dbconnection.connect();
		PreparedStatement pst;
		try{
			pst =con.prepareStatement("select * from fuelstation where sId=?");
			pst.setInt(1, sId);
			
			int i = 0;
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				 dquantity =rs.getFloat(11);
				
				i= 1;
				System.out.println("Row Updated Count:"+i);
			}
			
		PreparedStatement ps ;
		
		ps =con.prepareStatement("update fuelstation set dieselQty=? where sId=?");
		ps.setInt(2, sId);
		dquantity = ndquantity+dquantity;
		ps.setFloat(1, dquantity);
		
		
		 int ni=ps.executeUpdate();
		 if (ni > 0) {
             // Redirect with success message
         	System.out.println("Diesel quantity updated successfully!"+dquantity );
             response.sendRedirect("2_AddPetrol.html?message=Diesel quantity updated successfully!");
         } else {
             // Redirect with error message
         	System.out.println("Failed to update Diesel quantity");

             response.sendRedirect("2_AddDiesel.html?message=Failed to update Diesel quantity.");
         }
     } catch (SQLException e) {
         e.printStackTrace();
         response.sendRedirect("2_AddDiesel.html?message=An error occurred while updating petrol quantity.");
     } finally {
         try {
             if (con != null) {
                 con.close();
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
     }
		doGet(request, response);
	}

}
