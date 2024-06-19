package demoQA.POJO;

import java.util.Map;
import java.util.List;

public class AddListOfBooks {

    private String userId;
    private List<Map<String, String>> collectionOfIsbns;

    public AddListOfBooks() {

    }

    public AddListOfBooks(String userId, List<Map<String, String>> collectionOfIsbns) {
        this.userId = userId;
        this.collectionOfIsbns = collectionOfIsbns;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Map<String, String>> getCollectionOfIsbns() {
        return collectionOfIsbns;
    }

    public void setCollectionOfIsbns(List<Map<String, String>> collectionOfIsbns) {
        this.collectionOfIsbns = collectionOfIsbns;
    }
}
