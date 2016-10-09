package in.hottenste.jess;

import com.google.common.base.Charsets;
import com.google.common.collect.Sets;
import com.google.common.io.Resources;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.*;

public class WebCrawlerTest {
    @Test
    public void testSetup() {
        WebCrawler webCrawler = new WebCrawler();
        assertThat(webCrawler, instanceOf(WebCrawler.class));
    }

    @Test
    public void testCrawlSimple() throws Exception {
        final WebCrawler webCrawler = new WebCrawler();
        Internet internet = Internet.createFromJsonString(loadStringFromResource("simple_internet"));
        final Set<String> expected = Sets.newHashSet(
                "http://foo.bar.com/p1",
                "http://foo.bar.com/p2",
                "http://foo.bar.com/p3"
                );

        final CrawlerResults crawlerResults = webCrawler.crawl(internet);
        assertThat(crawlerResults.getSuccesses(), is(expected));
    }

    @Test
    public void testCrawlCyclicGraph() throws Exception {
        final WebCrawler webCrawler = new WebCrawler();
        Internet internet = Internet.createFromJsonString(loadStringFromResource("internet2"));
        final Set<String> expectedSuccesses = Sets.newHashSet(
                "http://foo.bar.com/p1",
                "http://foo.bar.com/p2",
                "http://foo.bar.com/p3",
                "http://foo.bar.com/p4",
                "http://foo.bar.com/p5"
        );
        final Set<String> expectedSkipped = Sets.newHashSet("http://foo.bar.com/p1");

        final CrawlerResults crawlerResults = webCrawler.crawl(internet);
        assertThat(crawlerResults.getSuccesses(), is(expectedSuccesses));
        assertThat(crawlerResults.getSkipped(), is(expectedSkipped));
    }


    @Test
    public void testCrawlGraphWithBrokenLinks() throws Exception {
        final WebCrawler webCrawler = new WebCrawler();
        Internet internet = Internet.createFromJsonString(loadStringFromResource("internet1"));
        final Set<String> expectedSuccesses = Sets.newHashSet(
                "http://foo.bar.com/p1",
                "http://foo.bar.com/p2",
                "http://foo.bar.com/p4",
                "http://foo.bar.com/p5",
                "http://foo.bar.com/p6"
        );
        final Set<String> expectedSkipped = Sets.newHashSet(
                "http://foo.bar.com/p2",
                "http://foo.bar.com/p4",
                "http://foo.bar.com/p1",
                "http://foo.bar.com/p5"
        );
        final Set<String> expectedErrors = Sets.newHashSet(
                "http://foo.bar.com/p3",
                "http://foo.bar.com/p7"
        );

        final CrawlerResults crawlerResults = webCrawler.crawl(internet);
        assertThat(crawlerResults.getSuccesses(), is(expectedSuccesses));
        assertThat(crawlerResults.getSkipped(), is(expectedSkipped));
        assertThat(crawlerResults.getErrors(), is(expectedErrors));
    }


    @Test
    public void testCrawlEmpty() throws Exception {
        final WebCrawler webCrawler = new WebCrawler();
        Internet internet = Internet.createFromJsonString("{\"pages\": []}");

        final CrawlerResults crawlerResults = webCrawler.crawl(internet);
        assertThat(crawlerResults.getSuccesses(), is(Sets.newHashSet()));

    }


    private String loadStringFromResource(String resourceFile) throws IOException {
        URL url = Resources.getResource(resourceFile);
        return Resources.toString(url, Charsets.UTF_8);
    }
}
