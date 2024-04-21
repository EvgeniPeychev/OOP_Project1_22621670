public class Print implements FileAction{
    @Override
    public void execute() {
        String filePath = PathSingleton.getInstance().getPath();
        String fileContent = PathSingleton.getInstance().getContent();
        if (filePath == null || filePath.isEmpty()) {
            System.out.println("No file is currently open.");
            return;
        }

        System.out.println(fileContent);

    }
}


