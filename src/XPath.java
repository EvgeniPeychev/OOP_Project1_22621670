import java.io.BufferedReader;
import java.io.StringReader;
import java.io.IOException;

public class XPath implements FileAction {
    private String xpath;

    public XPath(String xpath) {
        this.xpath = xpath;
    }

    @Override
    public void execute() {
        String fileContent = PathSingleton.getInstance().getContent();
        if (!(fileContent == null || fileContent.isEmpty())) {
            try (BufferedReader reader = new BufferedReader(new StringReader(fileContent))) {

                if (xpath.contains("=")) {
                    String[] parts = xpath.split("\\(", 2);
                    String parentElement = parts[0];
                    String attribute = parts[1].split("\\)")[0].split("=")[0];
                    String attributeValue = parts[1].split("\\)")[0].split("=")[1].replaceAll("\"", "");
                    String childElement = parts[1].split("\\)")[1].split("/")[1];
                    String line;
                    boolean insideParentElement = false;
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("<" + parentElement) && line.contains(attribute + "=\"" + attributeValue + "\"")) {
                            insideParentElement = true;
                        }
                        if (insideParentElement && line.contains("</" + parentElement + ">")) {
                            insideParentElement = false;
                        }

                        if (line.contains("<" + childElement + ">") && line.contains("</" + childElement + ">")) {
                            int startIndex = line.indexOf("<" + childElement + ">") + childElement.length() + 2;
                            int endIndex = line.indexOf("</" + childElement + ">");
                            if (startIndex < endIndex) {
                                String text = line.substring(startIndex, endIndex);
                                System.out.println("Text inside " + childElement + " of " + parentElement + " with " + attribute + " equals to " + attributeValue + ": " + text );
                            }
                        }
                    }

                }
                else if (xpath.contains("[")) {
                    String[] parts = xpath.split("\\[|\\]");
                    String[] elements = parts[0].split("/");
                    String parentElement = elements[0];
                    String childElement = elements[1];
                    int index = Integer.parseInt(parts[1]);

                    String line;
                    boolean insideParentElement = false;
                    int currentIndex = -1;
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("<" + parentElement)) {
                            insideParentElement = true;
                        } else if (line.contains("</" + parentElement + ">")) {
                            insideParentElement = false;
                        }

                        if (insideParentElement && line.contains("<" + childElement + ">")) {
                            currentIndex++;
                            if (currentIndex == index) {
                                int startIndex = line.indexOf("<" + childElement + ">") + childElement.length() + 2;
                                int endIndex = line.indexOf("</" + childElement + ">");
                                String text = line.substring(startIndex, endIndex);

                                System.out.println("Text inside " + childElement + " of " + parentElement + " at index " + index + ": " + text);
                                break;
                            }
                        }
                    }
                } else if (xpath.contains("@")) {
                    String elementName = xpath.substring(0, xpath.indexOf("("));
                    String attributeName = xpath.substring(xpath.indexOf("@") + 1, xpath.indexOf(")"));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("<" + elementName)) {
                            int startIndex = line.indexOf(attributeName + "=\"") + attributeName.length() + 2;
                            int endIndex = line.indexOf("\"", startIndex);
                            String attributeValue = line.substring(startIndex, endIndex);

                            System.out.println("Value of " + attributeName + " in " + elementName + ": " + attributeValue);
                        }
                    }
                } else if (xpath.contains("/")) {
                    String[] elements = xpath.split("/");
                    String parentElement = elements[0];
                    String childElement = elements[1];

                    String line;
                    boolean insideParentElement = false;
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("<" + parentElement)) {
                            insideParentElement = true;
                        } else if (line.contains("</" + parentElement + ">")) {
                            insideParentElement = false;
                        }

                        if (insideParentElement && line.contains("<" + childElement + ">")) {
                            int startIndex = line.indexOf("<" + childElement + ">") + childElement.length() + 2;
                            int endIndex = line.indexOf("</" + childElement + ">");
                            String text = line.substring(startIndex, endIndex);

                            System.out.println("Text inside " + childElement + " of " + parentElement + ": " + text);
                        }
                    }
                } else {
                    System.out.println("Invalid XPath. Usage: xpath <XPath>");
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("No file is currently open.");
        }
    }
}
