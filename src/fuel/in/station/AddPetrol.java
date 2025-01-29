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
 * Servlet implementation class AddPetrol
 */
public class AddPetrol extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPetrol() {
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
	        Float npquantity = Float.parseFloat(request.getParameter("pquantity"));
	        int sId = getsetvalues.getId();
	        float pquantity = 0;

	        Connection con = Dbconnection.connect();
	        PreparedStatement pst;

	        try {
	            // Fetch current petrol quantity
	            pst = con.prepareStatement("SELECT petrolQty FROM fuelstation WHERE sId = ?");
	            pst.setInt(1, sId);
	            ResultSet rs = pst.executeQuery();

	            if (rs.next()) {
	                pquantity = rs.getFloat("petrolQty");
	                System.out.println("Added Petrol Quantity: "+pquantity);
	            }

	            // Update petrol quantity
	            PreparedStatement ps = con.prepareStatement("UPDATE fuelstation SET petrolQty = ? WHERE sId = ?");
	            ps.setFloat(1, pquantity + npquantity);
	            ps.setInt(2, sId);

	            int updateCount = ps.executeUpdate();
	            if (updateCount > 0) {
	                // Redirect with success message
	            	System.out.println("Petrol quantity updated successfully!"+(pquantity + npquantity));
	                response.sendRedirect("2_AddPetrol.html?message=Petrol quantity updated successfully!");
	            } else {
	                // Redirect with error message
	            	System.out.println("Failed to update petrol quantity");

	                response.sendRedirect("2_AddPetrol.html?message=Failed to update petrol quantity.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            response.sendRedirect("2_AddPetrol.html?message=An error occurred while updating petrol quantity.");
	        } finally {
	            try {
	                if (con != null) {
	                    con.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}