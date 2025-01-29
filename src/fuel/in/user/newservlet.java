package fuel.in.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fuel.in.connect.Dbconnection;
import fuel.in.getset.getsetvalues;

/**
 * Servlet implementation class newservlet
 */
public class newservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public newservlet() {
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
		int sId=Integer.parseInt(request.getParameter("id"));
	    
    	Connection con = Dbconnection.connect();
        try {
        	int uId=getsetvalues.getId();
            String fuelType = request.getParameter("fuelType");
            float fuelQuantity = Float.parseFloat(request.getParameter("fuelQuantity"));
            Date date = Date.valueOf(request.getParameter("date"));
            String status = ("pending");
            String dcode=("null");
            PreparedStatement pstmt = con.prepareStatement("insert into bookings values(?,?,?,?,?,?,?,?,?,?)");
            pstmt.setInt(1, 0);
            pstmt.setInt(2, uId);
            pstmt.setInt(3, sId);
            pstmt.setInt(4, 0);
            pstmt.setString(5, fuelType);
            pstmt.setFloat(6,fuelQuantity);
            pstmt.setInt(7, 0);
            pstmt.setDate(8, date);
            pstmt.setString(9, status);
            pstmt.setString(10, dcode);
            pstmt.executeUpdate();

            System.out.println("Fuel information inserted successfully.");
        }catch(Exception e)
        {
        	e.printStackTrace();
        }
    
		doGet(request, response);
	}

}
