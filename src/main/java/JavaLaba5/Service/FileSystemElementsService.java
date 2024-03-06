package JavaLaba5.Service;

import JavaLaba5.Model.FileSystemElement;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileSystemElementsService {
    //Сервис, что предоставляет файлы и папки, для мэнеджера файлов.
    private static List<FileSystemElement> fileSystemElementsInCurrentDir = new ArrayList<>();
    private static String currentDirectoryPath = "";
    public static List<FileSystemElement> GetFileSystemElementsFromCurrentDir(String path) {
        currentDirectoryPath = path;
        fileSystemElementsInCurrentDir = new ArrayList<>();
        // Создаем объект File, представляющий текущую директорию
        File directory = new File(currentDirectoryPath);

        // Получаем список элементов текущей директории
        File[] files = directory.listFiles();

        // Выводим список элементов текущей директории на экран
        Arrays.stream(files).forEach(FileSystemElementsService::GetFileSystemElement);
        return fileSystemElementsInCurrentDir;
    }

    private static void GetFileSystemElement(File fileSystemElement) {
        if (fileSystemElement.isFile()) {
            String currentFileName = fileSystemElement.getName();
            String currentFileSize = "";
            String currentFileModificationDate = "";
            try {
                BasicFileAttributes attributes = Files.readAttributes(Paths.get(fileSystemElement.getPath()), BasicFileAttributes.class);
                long createDate = attributes.creationTime().toMillis();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                currentFileModificationDate = dateFormat.format(createDate);
                currentFileSize = String.valueOf(attributes.size());
            } catch (java.io.IOException exception) {
                exception.printStackTrace();
            }
            fileSystemElementsInCurrentDir.add(new JavaLaba5.Model.File(
                    currentFileName,
                    currentFileModificationDate,
                    currentFileSize,
                    currentDirectoryPath));
        } else {
            String currentDirName = fileSystemElement.getName();
            String currentDirModificationDate = "";
            try {
                BasicFileAttributes attributes = Files.readAttributes(Paths.get(fileSystemElement.getPath()), BasicFileAttributes.class);
                long createDate = attributes.creationTime().toMillis();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                currentDirModificationDate = dateFormat.format(createDate);

            } catch (java.io.IOException exception) {
                exception.printStackTrace();
            }
            fileSystemElementsInCurrentDir.add(new JavaLaba5.Model.Directory(
                    currentDirName,
                    currentDirModificationDate,
                    currentDirectoryPath));
        }
    }
}
