class PathSingleton {
    private static PathSingleton instance;
    private String path;

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
}