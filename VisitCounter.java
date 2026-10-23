package ex1;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class VisitCounter extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        int visits = 0;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("visits")) {
                    visits = Integer.parseInt(cookie.getValue());
                    break;
                }
            }
        }

        visits++;

        Cookie counter = new Cookie("visits", String.valueOf(visits));
        counter.setMaxAge(86400);
        response.addCookie(counter);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><meta charset='UTF-8'><title>Visit Counter</title></head>");
        out.println("<body>");
        out.println("<h2>Welcome to my site!</h2>");
        out.println("<p>Number of visits: " + visits + "</p>");
        out.println("</body>");
        out.println("</html>");
    }
}