import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//<url-pattern>/update</url-pattern>
public class UpdateDataServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // set Context-type
        response.setContentType("text/html");

        // get output-stream
        PrintWriter out = response.getWriter();

        //respone web-page
        out.println("<!DOCTYPE html>");
        out.println("<html></html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Update Student's Details</title>");
        out.println("</head><body>");
        out.println("<h2 style='text-align: center;'><a href='./index.html'>Back to Home</a></h2>");
        out.println("<div style='margin-left: 570px; margin-top: 40px;'>");
        out.println("<h3><a href='./update.html'>Click here to Search again</a></h3></div><br>");

        // get parameter
        int stuID = Integer.parseInt(request.getParameter("sid"));

        try {

            // get Database Connection
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();

            // sql-query
            String sql = "SELECT * FROM demo.studata WHERE id=" + stuID;

            // get result-set
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {

                // geting data from result-set
                String stuName = resultSet.getString(2);
                String computerMarks = resultSet.getString(6);
                String mathsMarks = resultSet.getString(7);
                String scienceMarks = resultSet.getString(8);
                String englishMarks = resultSet.getString(9);
                String hindiMarks = resultSet.getString(10);

                out.println("<div style='font-size: large; margin-left: 400px; margin-top: 10px;'>");
                out.println("<form action='editdata' method='POST'>");
                out.println("<fieldset style='block-size: auto; width: 550px;'>");
                out.println("<legend>Edit Student's Data</legend>");
                out.println("<table style='border-spacing: 15px;'>");
                out.println("<tr><td><label for='sname'>Student's Name :</label></td>");
                out.println("<td><input style='font-size: medium;' type='text' name='sname' placeholder='" + stuName + "' size='40' required></td></tr>");
                out.println("<tr><td></td></tr>");
                out.println("<tr><td>Subject's Details :-</td></tr>");
                out.println("<tr><td><label for='computer'>Computer :</label></td>");
                out.println("<td><input style='font-size: medium;' type='text' name='computer' placeholder='" + computerMarks + "' size='40' required></td></tr>");
                out.println("<tr><td><label for='maths'>Mathematics :</label></td>");
                out.println("<td><input style='font-size: medium;' type='text' name='maths' placeholder='" + mathsMarks + "' size='40' required></td></tr>");
                out.println("<tr><td><label for='science'>Science :</label></td>");
                out.println("<td><input style='font-size: medium;' type='text' name='science' placeholder='" + scienceMarks + "' size='40' required></td></tr>");
                out.println("<tr><td><label for='english'>English :</label></td>");
                out.println("<td><input style='font-size: medium;' type='text' name='english' placeholder='" + englishMarks + "' size='40' required></td></tr>");
                out.println("<tr><td><label for='hindi'>Hindi :</label></td>");
                out.println("<td><input style='font-size: medium;' type='text' name='hindi' placeholder='" + hindiMarks + "' size='40' required></td></tr>");
                
                //sending Student's ID
                int sid=stuID;
                out.println("<tr><td><input type='hidden' name='sid' value='" +sid+ "'></td></tr>");

                out.println("<tr><td><button style='font-size: large; margin-top: 10px;' type='submit'>Update</button></td></tr>");
                out.println("</table></fieldset></form></div>");
                out.println("</body></html>");
            }
            else{
                out.println("<div style='font-size: large; margin-left: 500px; margin-top: 10px;'>");
                out.println("<h2>Student's ID NOT FOUND !!</h2></div>");
                out.println("</body></html>");
            }
        }
        catch (Exception e) {e.printStackTrace();}
    }
}
