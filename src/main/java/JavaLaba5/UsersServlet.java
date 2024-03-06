package JavaLaba5;

import JavaLaba5.Model.UserProfile;
import JavaLaba5.Service.AccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
@WebServlet(urlPatterns = {"/registration"})
public class UsersServlet extends HttpServlet {

    public void doGet(HttpServletRequest httpServletRequest,
                      HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("registration.jsp").forward(httpServletRequest, httpServletResponse);
    }

    public void doPost(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String email = httpServletRequest.getParameter("email");
        String login = httpServletRequest.getParameter("login");
        String pass = httpServletRequest.getParameter("pass");

        if (email==null || login == null || pass == null) {
            httpServletResponse.setContentType("text/html;charset=utf-8");
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile profile = new UserProfile(login,pass,email);
        if (AccountService.getUserByLogin(login)==null) {
            AccountService.addNewUser(profile);
            AccountService.addSession(httpServletRequest.getSession().getId(), profile);

            // Создание новой папки для пользователя
            File folder = new File("C:\\Users\\Informant\\fileManager\\" + login);
            boolean isCreationSuccess = folder.mkdir();
            if (!isCreationSuccess){
                httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

            String currentURL = httpServletRequest.getRequestURL().toString();
            httpServletResponse.sendRedirect(ServletUtilities.makeNewUrl(currentURL,"/manager"));
        }
        else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
