package fuel.in.dperson;

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
 * Servlet implementation class EnterDeliveryCode
 */
public class EnterDeliveryCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnterDeliveryCode() {
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
	String dcode  = request.getParameter("dcode");
		
		System.out.println("delivery code:"+dcode);
		int sId=0;
		float pQuantity=0,dQuantity=0,quantity=0,tQuantity=0;
		String fuelType=null;
		
		Connection con = Dbconnection.connect();
		 int dId=getsetvalues.getId();
		
		try{
			PreparedStatement psm  = con.prepareStatement("select * from deliveryperson where dId=?");
			psm.setInt(1,dId);
			ResultSet rst = psm.executeQuery();
			while(rst.next()){
				sId=rst.getInt(2);
				}
			
			PreparedStatement psmt  = con.prepareStatement("select * from fuelstation where sId=?");
			psmt.setInt(1,sId);
			ResultSet r = psmt.executeQuery();
			while(r.next()){
				pQuantity=r.getFloat(9);
				dQuantity=r.getFloat(11);
				
				}
			PreparedStatement p  = con.prepareStatement("select * from bookings where deliveryPersonId=?");
			p.setInt(1,dId);
			ResultSet rp = p.executeQuery();
			while(rp.next()){
				fuelType=rp.getString(5);
				quantity=rp.getFloat(6);
				}
			if("Petrol".equals(fuelType)){
				tQuantity=pQuantity-quantity;
				
				PreparedStatement s ;
				
				s =con.prepareStatement("update fuelstation set petrolQty=? where sId=?");
				s.setFloat(1, tQuantity);
				s.setInt(2, sId);
				s.executeUpdate();
				System.out.println(" updated petrol Quantity = "+tQuantity);

				
			}else{
				tQuantity=dQuantity-quantity;
				PreparedStatement sp ;
				sp =con.prepareStatement("update fuelstation set dieselQty=? where sId=?");
				sp.setFloat(1, tQuantity);
				sp.setInt(2, sId);
				sp.executeUpdate();
				System.out.println(" updated petrol Quantity = "+tQuantity);

				
				
			}
			
			
			String status=("Delivered");
		PreparedStatement ps ;
		
		ps =con.prepareStatement("update bookings set status=? where deliveryCode=?");
		ps.setString(2, dcode);
		
		ps.setString(1, status);
		
		
		 int ni=ps.executeUpdate();
		 if(ni>0){
			 System.out.println("Booking Updated Count:"+ni);
			 response.sendRedirect("3_ViewAssignedBookings.jsp");
		 }else{
			 response.sendRedirect("3_ViewAssignedBookings.jsp");
		 }
		 
		
		
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
