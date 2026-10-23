package ex1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class firstservlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String birthDate = request.getParameter("birthDate");
        String sex = request.getParameter("sex");

        Cookie c1 = new Cookie("firstName",
                URLEncoder.encode(firstName, StandardCharsets.UTF_8));
        Cookie c2 = new Cookie("LastName",
                URLEncoder.encode(lastName, StandardCharsets.UTF_8));
        Cookie c3 = new Cookie("birthDay",
                URLEncoder.encode(birthDate, StandardCharsets.UTF_8));
        Cookie c4 = new Cookie("Sex",
                URLEncoder.encode(sex, StandardCharsets.UTF_8));

        c1.setMaxAge(86400);
        c2.setMaxAge(86400);
        c3.setMaxAge(86400);
        c4.setMaxAge(86400);

        response.addCookie(c1);
        response.addCookie(c2);
        response.addCookie(c3);
        response.addCookie(c4);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><meta charset='UTF-8'><title>Informations personnelles</title></head>");
        out.println("<body>");
        out.println("<h2>Informations personnelles</h2>");
        out.println("<table border='1'>");
        out.println("<tr><th>Prenom</th><th>Nom</th><th>Date de naissance</th><th>Sexe</th></tr>");
        out.println("<tr><td>" + firstName + "</td><td>" + lastName + "</td><td>" + birthDate + "</td><td>" + sex + "</td></tr>");
        out.println("</table>");
        out.println("<p><a href='ReadCookie'>Lire les cookies</a></p>");
        out.println("<p><a href='VisitCounter'>Tester le compteur de visites (Cookies)</a></p>");
        out.println("<p><a href='ShowSession'>Tester la session</a></p>");
        out.println("</body>");
        out.println("</html>");
    }
}