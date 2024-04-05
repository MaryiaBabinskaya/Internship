public interface JsonMapper {

    boolean verifyResourceField(String json);

    static JsonMapper defaultInstance() {
        JsonMapper result = new JsonMap();
        return result;
    }
}