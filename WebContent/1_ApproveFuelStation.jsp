<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="java.sql.*"%>
<%@ page import="fuel.in.connect.Dbconnection"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Approve</title>
</head>
<body>
	<%
int sId =Integer.parseInt(request.getParameter("id"));
Connection con = Dbconnection.connect();
try{
	PreparedStatement ps  = con.prepareStatement("update fuelstation set status=? where sId=?");
	ps.setInt(2,sId);
	String status = ("Approved");
	ps.setString(1,status);
	int i=ps.executeUpdate();
	if(i>0){
		System.out.println("updated row count = "+i);
		
		response.sendRedirect("1_ViewPendingFuelStation.jsp");
	}else{
		System.out.println("updated row count = "+i);
	}
}catch(Exception e){
		e.printStackTrace();
	}
	
%>

	
</body>
</html>