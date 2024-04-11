import java.io.IOException;

class CloseFile implements FileAction {
    private String filePath;

    public CloseFile(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void execute() {
        if (filePath == null || filePath.isEmpty()) {
            System.out.println("No file is currently open.");
            return;
        }

        System.out.println("Closing File...");

        filePath = null;
        System.out.println("Successfully closed " + filePath);
    }
}