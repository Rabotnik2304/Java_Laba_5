package JavaLaba5.Model;

public class File extends FileSystemElement{
    private String path;
    private String name;
    private String date;
    private String size;
    public File(String name, String date, String size,String path){
        this.name=name;
        this.date=date;
        this.size=size;
        this.path=path;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public String getSize() {
        return size+" B";
    }
    @Override
    public String getPath() {
        return path+"\\"+name;
    }
}
