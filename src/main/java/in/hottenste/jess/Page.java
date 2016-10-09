package in.hottenste.jess;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class Page {
    private String address;
    private List<String> links;

    public Page(String address, List<String> links) {
        this.address = address;
        this.links = links;
    }

    //required for the ObjectMapper
    private Page() {
    }

    static Page createPageFromJsonString(String jsonString) throws java.io.IOException {
        //is creating a mapper expensive?
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, Page.class);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }
}
