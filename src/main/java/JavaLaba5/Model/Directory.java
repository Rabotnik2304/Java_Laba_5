package JavaLaba5.Model;

public class Directory extends FileSystemElement{
    private String path;
    private String name;
    private String date;
    public Directory(String name, String date,String path){
        this.name=name;
        this.date=date;
        this.path=path;
    }
    @Override
    public String getName() {
        return name+"/";
    }

    @Override
    public String getDate() {
        return date;
    }
    @Override
    public String getPath() {
        return path+"\\"+name;
    }
    @Override
    public String getSize() {
        return "";
    }
}
