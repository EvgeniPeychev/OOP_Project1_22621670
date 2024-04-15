
class PathSingleton {
    private static PathSingleton instance;
    private String path;
    private String content;

    private PathSingleton() {

    }

    public static PathSingleton getInstance() {
        if (instance == null) {
            instance = new PathSingleton();
        }
        return instance;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}
