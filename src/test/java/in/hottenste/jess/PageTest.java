package in.hottenste.jess;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PageTest {
    @Test
    public void testHasAddress() throws Exception {
        String address = "http://foo.bar.com/p1";
        Page page = new Page(address, new ArrayList<>());
        assertThat(page.getAddress(), is(address));
    }
    @Test
    public void testHasLinks() throws Exception {
        List<String> links = ImmutableList.of("link1", "link2");
        Page page = new Page("address", links);
        assertThat(page.getLinks(), is(links));
    }

    @Test
    public void testCreateFromJSON() throws Exception {
        final String address = "http://foo.bar.com/p1";
        final String firstLink = "http://foo.bar.com/p2";
        String jsonString = createJsonString(address, firstLink);
        Page page = Page.createPageFromJsonString(jsonString);
        assertThat(page.getAddress(), is(address));
        assertThat(page.getLinks().get(0), is(firstLink));


    }

    private String createJsonString(String address, String firstLink) {
        return "{" +
                    "\"address\":\"" + address + "\"" +
                    "," +
                    "\"links\": [" +
                    "\"" + firstLink + "\"," +
                    "\"http://foo.bar.com/p3\"," +
                    "\"http://foo.bar.com/p4\"" +
                    "]" +
                    "}";
    }
}