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
import fuel.in.getset.getsetArea;

public class EnterAreaName extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EnterAreaName() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Areaname = request.getParameter("AreaName");
        System.out.println("Area name: " + Areaname);  // Debugging line

        // Set the area in getsetArea
        getsetArea.setArea(Areaname);

        Connection con = Dbconnection.connect();
        PreparedStatement pst;
        try {
            pst = con.prepareStatement("SELECT * FROM fuelstation WHERE area=?");
            pst.setString(1, Areaname);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                // If fuel stations are found, redirect to the JSP page
                response.sendRedirect("4_ViewFuelStationByArea.jsp");
            } else {
                // If no stations are found, redirect back to the form
                response.sendRedirect("4_EnterAreaName.html");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
