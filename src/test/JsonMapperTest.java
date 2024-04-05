import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonMapperTest {

    //The first test checks the json from the file
    //I presented the same text in the second test. In both cases, it passes

    @Test //in this test please write your own filePath, and it will read from it
    void verifyResourceFieldWithSingleAsteriskFile() throws IOException {
        JsonMapper jsonMapper = JsonMapper.defaultInstance();
        String json = JsonFileReader.readJsonFromFile("C:\\Users\\User\\Desktop\\Java_MY\\Internship\\src\\test\\Test.json");
        assertFalse(jsonMapper.verifyResourceField(json));
    }

    @Test
    void verifyResourceFieldWithSingleAsterisk() {
        JsonMapper jsonMapper = JsonMapper.defaultInstance();
        assertFalse(jsonMapper.verifyResourceField("{\"PolicyName\":\"root\",\"PolicyDocument\":{\"Version\":\"2012-10-17\",\"Statement\":[{\"Sid\":\"IamListAccess\",\"Effect\":\"Allow\",\"Action\":[\"iam:ListRoles\",\"iam:ListUsers\"],\"Resource\":\"*\"}]}}"));
    }

    @Test
    void verifyResourceFieldWithNonAsteriskValue() {
        JsonMapper jsonMapper = JsonMapper.defaultInstance();
        assertTrue(jsonMapper.verifyResourceField("{\"PolicyName\":\"root\",\"PolicyDocument\":{\"Version\":\"2012-10-17\",\"Statement\":[{\"Sid\":\"IamListAccess\",\"Effect\":\"Allow\",\"Action\":[\"iam:ListRoles\",\"iam:ListUsers\"],\"Resource\":\"arn:aws:iam::123456789012:user/*\"}]}}"));
    }

    @Test
    void verifyResourceFieldWithEmptyJson() {
        JsonMapper jsonMapper = JsonMapper.defaultInstance();
        assertFalse(jsonMapper.verifyResourceField("{}"));
    }

    @Test
    void verifyResourceFieldWithNullJson() {
        JsonMapper jsonMapper = JsonMapper.defaultInstance();
        assertFalse(jsonMapper.verifyResourceField(null));
    }

    @Test
    void verifyResourceFieldWithSpecialCharacters() {
        JsonMapper jsonMapper = JsonMapper.defaultInstance();
        assertTrue(jsonMapper.verifyResourceField("{\"PolicyName\":\"root\",\"PolicyDocument\":{\"Version\":\"2012-10-17\",\"Statement\":[{\"Sid\":\"IamListAccess\",\"Effect\":\"Allow\",\"Action\":[\"iam:ListRoles\",\"iam:ListUsers\"],\"Resource\":\"special:characters/\\\"*\"}]}}"));
    }

    @Test
    void verifyResourceFieldWithInvalidFormat() {
        JsonMapper jsonMapper = JsonMapper.defaultInstance();
        assertFalse(jsonMapper.verifyResourceField("{invalid json}"));
    }

    @Test
    void verifyResourceFieldWithDifferentValueTypes() {
        JsonMapper jsonMapper = JsonMapper.defaultInstance();
        assertFalse(jsonMapper.verifyResourceField("{\"Resource\":12345}"));
    }

}