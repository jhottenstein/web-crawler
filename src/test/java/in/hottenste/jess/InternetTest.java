package in.hottenste.jess;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;
import org.junit.Test;

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
        URL url = Resources.getResource("internet2");
        String jsonString = Resources.toString(url, Charsets.UTF_8);
        final Internet internet = Internet.createFromJsonString(jsonString);
        assertThat(getFirstAddress(internet), is("http://foo.bar.com/p1"));
    }

    private String getFirstAddress(Internet internet) {
        return internet.getPages().get(0).getAddress();
    }
}