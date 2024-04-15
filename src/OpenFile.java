import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

class OpenFile implements FileAction {
    private String filePath;

    public OpenFile(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void execute() {
        if (filePath == null || filePath.isEmpty()) {
            System.out.println("Path is required. Please enter a valid file path.");
            return;
        }

        if (!filePath.toLowerCase().endsWith(".xml")) {
            System.out.println("Invalid file extension. File must end with .xml.");
            return;
        }

        System.out.println("Opening File...");
        try {
            File file = new File(filePath);

            if (!file.exists()) {
                System.out.println("File does not exist. Creating a new XML file.");
                createEmptyXMLFile(file);
                return;
            }

            System.out.println("Opened file: " + filePath);
            readFileContent(file);

        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please enter a valid file path.");
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    private void createEmptyXMLFile(File file) throws IOException {
        String xmlFilePath = filePath.endsWith(".xml") ? filePath : filePath + ".xml";
        file = new File(xmlFilePath);

        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            writer.println("<root>");
            writer.println("    <data></data>");
            writer.println("</root>");
        }
        System.out.println("New XML file created: " + file.getAbsolutePath());
    }

    private void readFileContent(File file) throws IOException {
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                System.out.println(fileScanner.nextLine());
            }
        }
    }
}
