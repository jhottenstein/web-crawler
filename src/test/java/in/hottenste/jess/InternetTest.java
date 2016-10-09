package in.hottenste.jess;

import com.google.common.base.Charsets;
import java.util.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class InternetTest {
    @Test
    public void testGetPages() throws Exception {
        final ImmutableList<Page> pages = ImmutableList.of(new Page("foo", new ArrayList<>()));
        final Internet internet = new Internet(pages);
        assertThat(internet.getPages(), is(pages));
    }

    @Test
    public void testCreateInternetFromJsonString() throws Exception {
        final String resourceFile = "internet2";
        String jsonString = loadStringFromResource(resourceFile);
        final Internet internet = Internet.createFromJsonString(jsonString);
        assertThat(getFirstAddress(internet), is("http://foo.bar.com/p1"));
    }

    @Test
    public void testFindPage() throws Exception {
        final String resourceFile = "internet2";
        String jsonString = loadStringFromResource(resourceFile);
        final Internet internet = Internet.createFromJsonString(jsonString);
        final Page page = internet.findPage("http://foo.bar.com/p1").get();
        assertThat(page.getAddress(), is("http://foo.bar.com/p1"));
        assertThat(page.getLinks().get(0), is("http://foo.bar.com/p2"));
    }

    @Test
    public void testFindPageError() throws Exception {
        final String resourceFile = "internet1";
        String jsonString = loadStringFromResource(resourceFile);
        final Internet internet = Internet.createFromJsonString(jsonString);
        final Optional<Page> page = internet.findPage("http://foo.bar.com/p3");
        assertThat(page.isPresent(), is(false));
    }

    private String loadStringFromResource(String resourceFile) throws IOException {
        URL url = Resources.getResource(resourceFile);
        return Resources.toString(url, Charsets.UTF_8);
    }

    private String getFirstAddress(Internet internet) {
        return internet.getPages().get(0).getAddress();
    }
}