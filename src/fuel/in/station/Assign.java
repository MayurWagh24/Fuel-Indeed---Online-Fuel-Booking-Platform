package fuel.in.station;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fuel.in.connect.Dbconnection;
import fuel.in.getset.getsetvalues;

/**
 * Servlet implementation class Assign
 */
public class Assign extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Assign() {
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
		int dId =Integer.parseInt(request.getParameter("did"));
		String dcode=request.getParameter("deliverycode");
		int sId=getsetvalues.getId();
		float quantity=0,pbill;
		String fueltype=null;
		int prate=0,drate=0,bId=0;
		
		Connection con = Dbconnection.connect();
		try{
			PreparedStatement pst  = con.prepareStatement("select * from bookings where stationId=?");
			pst.setInt(1,sId);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				fueltype=rs.getString(5);
				bId=rs.getInt(1);
				quantity=rs.getFloat(6);
			}
			
			
			
			PreparedStatement psmt  = con.prepareStatement("select * from fuelstation where sId=?");
			psmt.setInt(1,sId);
			ResultSet rst = psmt.executeQuery();
			while(rst.next()){
				prate=rst.getInt(10);
				drate=rst.getInt(12);
				
			}
			if("Petrol".equals(fueltype)){
				pbill=quantity*prate;
				System.out.println("petrol Booking = "+pbill);

				
			}else{
				pbill=quantity*drate;
				System.out.println("Disel Booking = "+pbill);
				
				
			}
			
			
			PreparedStatement ps  = con.prepareStatement("update bookings set status=? , deliveryCode=? , deliveryPersonId=?,totalBill=? where stationId=? and id=?");
			ps.setString(2,dcode);
			String status = ("Assigned");
			ps.setString(1,status);
			ps.setInt(3, dId);
			ps.setFloat(4, pbill);
			ps.setInt(5, sId);
			ps.setInt(6, bId);
			int i=ps.executeUpdate();
			if(i>0){
				System.out.println("updated bookings count = "+i);
				response.sendRedirect("2_ViewPendingBookings.jsp");
			}else{
				response.sendRedirect("2_ViewPendingBookings.jsp");
			}
				 
		      
		        
		       
		}catch(Exception e){
				e.printStackTrace();
			}
		doGet(request, response);
	}

}
