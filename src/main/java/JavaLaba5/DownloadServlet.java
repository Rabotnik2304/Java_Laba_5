package JavaLaba5;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(urlPatterns = {"/DownloadServlet"})
public class DownloadServlet extends HttpServlet {
    //Загрузчик
    @Override
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException {

        String currentFilePath = httpServletRequest.getParameter("path");
        try (FileInputStream fileInputStream = new FileInputStream(currentFilePath)) {

            // Устанавливаем заголовок для скачивания файла
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"" + new File(currentFilePath).getName() + "\"");

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                httpServletResponse.getOutputStream().write(buffer, 0, bytesRead);
            }
        }
    }
}
