public class JsonMap implements JsonMapper {

    @Override
    public boolean verifyResourceField(String json) {
        if (json == null || json.isEmpty() || json.equals("{}")) { // json is Empty
            return false;
        }

        return !json.replaceAll("\\s+", "").contains("\"Resource\":\"*\"");
    }
}