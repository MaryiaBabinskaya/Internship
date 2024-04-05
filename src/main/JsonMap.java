public class JsonMap implements JsonMapper {

    @Override
    public boolean verifyResourceField(String json) {
        // checks if JSON is empty
        if (json == null || json.isEmpty() || json.trim().charAt(0) != '{' || json.trim().charAt(json.trim().length() - 1) != '}') {
            return false;
        }

        // checks if contains "PolicyDocument" and "PolicyName"
        if (!containsKey(json, "PolicyDocument") || !containsKey(json, "PolicyName")) {
            return false;
        }

        // checks value type
        if (!checkValueType(json, "PolicyDocument", "{") || !checkValueType(json, "PolicyName", "\"")) {
            return false;
        }

        // replace all spaces
        String jsonWithoutSpaces = json.replaceAll("\\s+", "");

        // checks if Resource contains only "*"
        if (jsonWithoutSpaces.contains("\"Resource\":\"*\"")) {
            return !jsonWithoutSpaces.matches(".*\"Resource\":\"\\*\".*");
        }

        return true;

    }

    // checks if JSON contains key
    private boolean containsKey(String json, String key) {
        return json.contains("\"" + key + "\":");
    }

    // checks value of key in JSON
    private boolean checkValueType(String json, String key, String expectedType) {
        int keyIndex = json.indexOf("\"" + key + "\":");
        int colonIndex = json.indexOf(":", keyIndex);
        int valueStartIndex = json.indexOf(expectedType, colonIndex);
        int valueEndIndex = json.indexOf(expectedType, valueStartIndex + 1);
        return valueStartIndex != -1 && valueEndIndex != -1;
    }

}