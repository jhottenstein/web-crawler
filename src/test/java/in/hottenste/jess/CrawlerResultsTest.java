package in.hottenste.jess;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CrawlerResultsTest {

    @Test
    public void testGetters() throws Exception {
        List<String> successes = Lists.newArrayList("Hello");
        List<String> skipped = Lists.newArrayList("World");

        final CrawlerResults crawlerResults = new CrawlerResults(successes, skipped);
        assertThat(crawlerResults.getSuccesses(), is(successes));
        assertThat(crawlerResults.getSkipped(), is(skipped));
    }

}