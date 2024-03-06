package JavaLaba5;

import JavaLaba5.Model.UserProfile;
import JavaLaba5.Service.AccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(urlPatterns = {"/"})
public class SessionsServlet extends HttpServlet {
    public void doGet(HttpServletRequest httpServletRequest,
                      HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("log.jsp").forward(httpServletRequest, httpServletResponse);
    }

    //sign in
    public void doPost(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse) throws IOException {
        String login = httpServletRequest.getParameter("login");
        String pass = httpServletRequest.getParameter("pass");

        if (login == null || pass == null) {
            httpServletResponse.setContentType("text/html;charset=utf-8");
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile profile = AccountService.getUserByLogin(login);
        if (profile == null || !profile.getPass().equals(pass)) {
            httpServletResponse.setContentType("text/html;charset=utf-8");
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        AccountService.addSession(httpServletRequest.getSession().getId(), profile);

        String currentURL = httpServletRequest.getRequestURL().toString();
        httpServletResponse.sendRedirect(ServletUtilities.makeNewUrl(currentURL,"/manager"));
    }
}
