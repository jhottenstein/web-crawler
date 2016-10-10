package in.hottenste.jess;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class Page {
    private static final ObjectMapper mapper = new ObjectMapper();
    private String address;
    private List<String> links;

    static Page createFromJsonString(String jsonString) throws java.io.IOException {
        return mapper.readValue(jsonString, Page.class);
    }

    public Page(String address, List<String> links) {
        this.address = address;
        this.links = links;
    }

    public String getAddress() {
        return address;
    }

    public List<String> getLinks() {
        return links;
    }

    //required for the ObjectMapper
    private Page() {}

    //required for the ObjectMapper
    private void setAddress(String address) {
        this.address = address;
    }

    //required for the ObjectMapper
    private void setLinks(List<String> links) {
        this.links = links;
    }
}
