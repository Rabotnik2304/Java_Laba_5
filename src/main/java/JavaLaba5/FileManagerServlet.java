package JavaLaba5;

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
    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException, ServletException {
        String currentDirPath;
        String pathFromRequest=httpServletRequest.getParameter("path");
        if(httpServletRequest.getParameter("path") != null){
            currentDirPath=pathFromRequest;
        }else{
            currentDirPath = new File(".").getCanonicalPath();
        }

        httpServletRequest.setAttribute("currentDirPath",currentDirPath);
        httpServletRequest.setAttribute("list",
                FileSystemElementsService.GetFileSystemElementsFromCurrentDir(currentDirPath));

        String parentDirPath = new File(currentDirPath).getParent();
        if(parentDirPath==null){
            parentDirPath=currentDirPath;
        }
        httpServletRequest.setAttribute("parentDirPath",parentDirPath);

        Date generationDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy HH:mm:ss");

        httpServletRequest.setAttribute("generationTime",dateFormat.format(generationDate));
        httpServletRequest.getRequestDispatcher("manager.jsp").forward(httpServletRequest, httpServletResponse);
    }
}
