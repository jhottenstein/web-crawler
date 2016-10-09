package in.hottenste.jess;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class Internet {
    private List<Page> pages;

    public static Internet createFromJsonString(String jsonString) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, Internet.class);
    }

    public Internet(List<Page> pages) {
        this.pages = pages;
    }

    public List<Page> getPages() {
        return pages;
    }

    //needed for ObjectMapper
    private Internet() {
    }

    //needed for ObjectMapper
    private void setPages(List<Page> pages) {
        this.pages = pages;
    }
}
