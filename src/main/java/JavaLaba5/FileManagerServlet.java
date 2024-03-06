package JavaLaba5;

import JavaLaba5.Model.UserProfile;
import JavaLaba5.Service.AccountService;
import JavaLaba5.Service.FileSystemElementsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = {"/manager"})
public class FileManagerServlet extends HttpServlet {
    //Файловый мэнеджер
    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException, ServletException {

        UserProfile currentUser = AccountService.getUserBySessionId(httpServletRequest.getSession().getId());
        if (currentUser == null) {
            String currentURL = httpServletRequest.getRequestURL().toString();
            httpServletResponse.sendRedirect(ServletUtilities.makeNewUrl(currentURL, "/log"));
            return;
        }

        String currentDirPath;
        String pathToUserDir = "C:\\Users\\Informant\\fileManager\\" + currentUser.getLogin();
        String pathFromRequest = httpServletRequest.getParameter("path");
        if (httpServletRequest.getParameter("path") != null) {
            if (!pathFromRequest.startsWith(pathToUserDir)) {
                currentDirPath = pathToUserDir;
            } else {
                currentDirPath = pathFromRequest;
            }
        } else {
            currentDirPath = pathToUserDir;
        }

        httpServletRequest.setAttribute("currentDirPath", currentDirPath);
        httpServletRequest.setAttribute("list",
                FileSystemElementsService.GetFileSystemElementsFromCurrentDir(currentDirPath));

        String parentDirPath = new File(currentDirPath).getParent();
        if (parentDirPath == null) {
            parentDirPath = currentDirPath;
        }
        httpServletRequest.setAttribute("parentDirPath", parentDirPath);

        Date generationDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy HH:mm:ss");

        httpServletRequest.setAttribute("generationTime", dateFormat.format(generationDate));
        httpServletRequest.getRequestDispatcher("manager.jsp").forward(httpServletRequest, httpServletResponse);
    }

    //Выход из системы
    public void doPost(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse) throws IOException {
        String sessionId = httpServletRequest.getSession().getId();
        AccountService.deleteSession(sessionId);
        String currentURL = httpServletRequest.getRequestURL().toString();
        httpServletResponse.sendRedirect(ServletUtilities.makeNewUrl(currentURL, "/log"));
    }
}
