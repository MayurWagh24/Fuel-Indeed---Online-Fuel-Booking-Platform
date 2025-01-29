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
import fuel.in.getset.getsetvalues;

/**
 * Servlet implementation class AddDieselRates
 */
public class AddDieselRates extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDieselRates() {
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
int dRates  = Integer.parseInt(request.getParameter("dRates"));
		
		System.out.println("Diesel Rates:"+dRates);
		int sId=getsetvalues.getId();
		
		
		Connection con = Dbconnection.connect();
		
		try{
			
			
		PreparedStatement ps ;
		
		ps =con.prepareStatement("update fuelstation set dieselRate=? where sId=?");
		ps.setInt(2, sId);
		
		ps.setFloat(1, dRates);
		
		
		int updateCount = ps.executeUpdate();
        if (updateCount > 0) {
            // Redirect with success message
        	System.out.println("Diesel Rates updated successfully!"+(dRates));
            response.sendRedirect("2_AddDieselRates.html?message=Diesel Rates updated successfully!");
        } else {
            // Redirect with error message
        	System.out.println("Failed to update Diesel Rates");

            response.sendRedirect("2_AddDieselRates.html?message=Failed to update Diesel Rates.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        response.sendRedirect("2_AddDieselRates.html?message=An error occurred while updating Diesel Rates.");
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
