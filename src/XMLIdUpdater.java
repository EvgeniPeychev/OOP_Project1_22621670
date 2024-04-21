import java.util.HashMap;

public class XMLIdUpdater {

    public String updateIdsInXML(String xmlContent) {
        StringBuilder updatedXmlContent = new StringBuilder(xmlContent);
        HashMap<String, Integer> idCounts = new HashMap<>();

        updateParentIds(updatedXmlContent, idCounts);

        return updatedXmlContent.toString();
    }

    private void updateParentIds(StringBuilder xmlContent, HashMap<String, Integer> idCounts) {
        int pos = 0;
        while (pos < xmlContent.length()) {
            int openTagIndex = xmlContent.indexOf("<", pos);
            if (openTagIndex == -1) {
                break;
            }

            int closeTagIndex = xmlContent.indexOf(">", openTagIndex);
            if (closeTagIndex == -1) {
                break;
            }

            String tag = xmlContent.substring(openTagIndex + 1, closeTagIndex);

            if (!tag.contains("/")) {
                int idIndex = xmlContent.indexOf("id=\"", openTagIndex);
                if (idIndex != -1 && idIndex < closeTagIndex) {
                    int idEndIndex = xmlContent.indexOf("\"", idIndex + 4);
                    if (idEndIndex != -1) {
                        String id = xmlContent.substring(idIndex + 4, idEndIndex);
                        int count = idCounts.getOrDefault(id, 0) + 1;
                        idCounts.put(id, count);

                        if (count > 1) {
                            String updatedId = id + "_" + count;
                            xmlContent.replace(idIndex + 4, idEndIndex, updatedId);
                            pos = idEndIndex + updatedId.length();
                            continue;
                        }
                    }
                }
            }
            pos = closeTagIndex + 1;
        }
    }
}