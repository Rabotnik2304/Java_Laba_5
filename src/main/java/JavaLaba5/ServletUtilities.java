package JavaLaba5;

public class ServletUtilities {
    //Просто утилитка, которая много где нужна. (При redirect-ах)
    public static String makeNewUrl(String currentURL, String redirectPath) {
        // Находим позицию последнего слеша
        int lastSlashIndex = currentURL.lastIndexOf("/");

        // Формируем новый URL, заменяя последний элемент
        return currentURL.substring(0, lastSlashIndex) + redirectPath;
    }
}
