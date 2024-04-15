import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class SaveAsFile implements FileAction {
    private String filePath;

    public SaveAsFile(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void execute() {
        String filePathCheck = PathSingleton.getInstance().getPath();


        if (filePathCheck == null || filePathCheck.isEmpty()) {
            System.out.println("No file is currently open.");
            return;
        }

        if (!filePath.toLowerCase().endsWith(".xml")) {
            System.out.println("File must have an extension of .XML");
            return;
        }

        System.out.println("Saving File As...");

        try {
            File file = new File(filePath);
            FileWriter writer = new FileWriter(file);


            writer.close();
            System.out.println("Successfully saved " + file.getName()+" in " + filePath );
        } catch (IOException e) {
            System.out.println("Error saving the file: " + e.getMessage());
        }
    }
}
