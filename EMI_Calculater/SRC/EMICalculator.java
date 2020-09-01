import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EMICalculator", urlPatterns = { "/index" })
public class EMICalculator extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // For GET-request
    // public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException

    // For POST-request
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //Get data from HTML
        double principal = Double.parseDouble(request.getParameter("principal_ammount"));
        double interest = Double.parseDouble(request.getParameter("rate_of_interest"));
        double time = Double.parseDouble(request.getParameter("time_period"));

        //Calculations
        double emiAmount = getEMIAmount(principal, interest, time);
        double totalAmount = getTotalAmount(emiAmount, time);
        double interestAmount = getInterestAmount(totalAmount, principal);

        // set the content-type
        response.setContentType("text/html");

        // connect to high-level output-stream
        PrintWriter out = response.getWriter();

        // creating result-page by using text-block feature of JDK-14
        out.println("<!DOCTYPE html>");
        out.println("<html><head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Result</title>");
        out.println("</head>");
        out.println("<body style='background-color: #c7e3f0'>");
        out.println("<div style='background-color:burlywood; font-size: large; text-align: center; margin-top:50px; margin: auto; width: 600px; border: 1px solid grey;'>");
        out.println("<h1>Simple EMI Calculator</h1></div>");
        out.println("<table style='background-color:burlywood; font-size: x-large; margin: auto; width: 600px; border: 1px solid grey'>");
        out.println("<tr>");
        out.println("<td>Principal Ammount (Rs.)<td>"+principal+"</td></tr>");
        out.println("<tr>");
        out.println("<td>Rate of Interest (% p.a)<td>"+interest+"</td></tr>");
        out.println("<tr>");
        out.println("<td>Time Period (in months)<td>"+request.getParameter("time_period")+"</td></tr>");
        out.println("</table>");
        out.println("<table style='background-color:burlywood; font-size: x-large; margin: auto; width: 600px; border: 1px solid grey'>");
        out.println("<tr>");
        out.println("<td>Monthlty EMI (Rs. per month)<td style='color: green;'>"+emiAmount+"</td></tr>");
        out.println("<tr>");
        out.println("<td>Total Amount Paid (Rs.)<td style='color: blue;'>"+totalAmount+"</td></tr>");
        out.println("<tr>");
        out.println("<td>Total Interest Paid (Rs.)<td style='color: red;'>"+interestAmount+"</td></tr>");
        out.println("</table>");
        out.println("<br><br>");
        out.println("<div style='font-size: xx-large; text-align: center;'>");
        out.println("<a href='/EMI'>Back to Home</a></div>");
        out.println("</body>");
        out.println("</html>");
    }

    private static double getEMIAmount(double principal, double interest, double time) {
        double num,emi;
        interest=(interest/1200);
        num=Math.pow((interest+1), time);
        emi=(principal*interest)*(num/(num-1));
        return Double.parseDouble(String.format("%.2f", emi));
    }

    private static double getTotalAmount(double emi, double time) {
        return Double.parseDouble(String.format("%.2f", (emi * time)));
    }

    private static double getInterestAmount(double totalAmount, double principal) {
        return Double.parseDouble(String.format("%.2f", (totalAmount - principal)));
    }
}
