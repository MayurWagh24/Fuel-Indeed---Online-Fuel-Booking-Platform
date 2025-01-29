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
import fuel.in.getset.getsetvalues;

public class ChangeUserPassword extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ChangeUserPassword() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        int uId = getsetvalues.getId();

        // Check if old password is correct (this can be done by querying the database)
        try {
            Connection con = Dbconnection.connect();
            PreparedStatement ps = con.prepareStatement("SELECT password FROM user WHERE uId = ?");
            ps.setInt(1, uId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String dbPassword = rs.getString("password");

                // Check if the old password matches the stored password
                if (!dbPassword.equals(oldPassword)) {
                    response.sendRedirect("4_ChangeUserPassword.html?error=" + "Old password is incorrect.");
                    return;
                }

                // Check if new password and confirm password match
                if (!newPassword.equals(confirmPassword)) {
                    response.sendRedirect("4_ChangeUserPassword.html?error=" + "New password and confirm password do not match.");
                    return;
                }

                // Update the password in the database
                ps = con.prepareStatement("UPDATE user SET password = ? WHERE uId = ?");
                ps.setString(1, newPassword);
                ps.setInt(2, uId);

                int ni = ps.executeUpdate();
                if (ni > 0) {
                    response.sendRedirect("4_ChangeUserPassword.html?success=" + "Password changed successfully.");
                } else {
                    response.sendRedirect("4_ChangeUserPassword.html?error=" + "Password change failed. Please try again.");
                }

            } else {
                response.sendRedirect("4_ChangeUserPassword.html?error=" + "User not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("4_ChangeUserPassword.html?error=" + "An error occurred. Please try again.");
        }
    }
}
