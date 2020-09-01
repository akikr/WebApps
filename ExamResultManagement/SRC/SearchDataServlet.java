import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//<url-pattern>/search</url-pattern>
public class SearchDataServlet extends HttpServlet {

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
        out.println("<title>Search Student's Details</title>");
        out.println("</head><style>");
        out.println("table, td {");
        out.println("border: 1px solid black;");
        out.println("border-collapse: collapse;");
        out.println("width: 450px;");
        out.println("padding: 5px;");
        out.println("text-align: left;");
        out.println("font-size: large;}");
        out.println("</style><body>");
        out.println("<h2 style='text-align: center;'><a href='./index.html'>Back to Home</a></h2>");

        // get parameter
        int stuID = Integer.parseInt(request.getParameter("sid"));

        try {
            //get Database Connection
            Connection connection=DatabaseConnection.getConnection();
            Statement statement=connection.createStatement();

            //sql-query
            String sql="SELECT * FROM demo.studata WHERE id="+stuID;

            //get result-set
            ResultSet resultSet=statement.executeQuery(sql);

            if(resultSet.next()){

                //geting data from result-set
                String stuName=resultSet.getString(2);
                String stuDivision=resultSet.getString(3);
                String stuPercent=resultSet.getString(4);
                String totalMarks=resultSet.getString(5);
                String computerMarks=resultSet.getString(6);
                String mathsMarks=resultSet.getString(7);
                String scienceMarks=resultSet.getString(8);
                String englishMarks=resultSet.getString(9);
                String hindiMarks=resultSet.getString(10);
    
                out.println("<div style='margin-left: 570px; margin-top: 40px;'>");
                out.println("<h3><a href='./search.html'>Click here to Search again</a></h3>");
                out.println("</div>");
                out.println("<br><div style='font-size: x-large; font-weight: bold; margin-left: 450px; margin-top: 10px;'>");
                out.println("<table>");
                out.println("<tr><td>Student's ID :</td><td>" + stuID + "</td></tr>");
                out.println("<tr><td>Student's Name :</td><td>" + stuName + "</td></tr>");
                out.println("<tr><td>Division :</td><td>" + stuDivision + "</td></tr>");
                out.println("<tr><td>Percentage :</td><td>" + stuPercent + "%</td></tr>");
                out.println("</table></div>");
    
                out.println("<br><div style='font-size: x-large; margin-left: 450px; margin-top: 10px;'>");
                out.println("<table>");
                out.println("<tr><td><b>Subjects:-</b></td><td><b>Marks:-</b></td></tr>");
                out.println("<tr><td>Computer :</td><td>" + computerMarks + "</td></tr>");
                out.println("<tr><td>Mathematics :</td><td>" + mathsMarks + "</td></tr>");
                out.println("<tr><td>Sicence :</td><td>" + scienceMarks + "</td></tr>");
                out.println("<tr><td>English :</td><td>" + englishMarks + "</td></tr>");
                out.println("<tr><td>Hindi :</td><td>" + hindiMarks + "</td></tr>");
                out.println("<tr><td><b>Total Marks :</b></td><td><b>" + totalMarks + "</b></td></tr>");
                out.println("</table></div>");
                out.println("</body></html>");
            }
            else{
                out.println("<div style='margin-left: 570px; margin-top: 40px;'>");
                out.println("<h3><a href='./search.html'>Click here to Search again</a></h3>");
                out.println("Student's ID NOT FOUND !!");
                out.println("</div>");
                out.println("</body></html>");
            }
        } catch (Exception e) { e.printStackTrace(); }

    }
}
