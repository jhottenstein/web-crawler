package in.hottenste.jess;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CrawlerResultsTest {

    @Test
    public void testGetters() throws Exception {
        Set<String> successes = Sets.newHashSet("Hello");
        Set<String> skipped = Sets.newHashSet("World");
        Set<String> errors = Sets.newHashSet("Cruel");

        final CrawlerResults crawlerResults = new CrawlerResults(successes, skipped, errors);
        assertThat(crawlerResults.getSuccesses(), is(successes));
        assertThat(crawlerResults.getSkipped(), is(skipped));
        assertThat(crawlerResults.getErrors(), is(errors));
    }

}