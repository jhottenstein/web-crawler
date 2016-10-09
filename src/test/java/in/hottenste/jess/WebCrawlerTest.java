package in.hottenste.jess;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class WebCrawlerTest {
    @Test
    public void testSetup() {
        WebCrawler webCrawler = new WebCrawler();
        assertThat(webCrawler, instanceOf(WebCrawler.class));
    }
}
