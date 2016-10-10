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

    @Test
    public void testAddSuccess() throws Exception {
        final CrawlerResults crawlerResults = new CrawlerResults();
        final String success = "Hello";
        Set<String> successes = Sets.newHashSet(success);

        final boolean isAdded = crawlerResults.addSuccess(success);
        assertThat(isAdded, is(true));
        assertThat(crawlerResults.getSuccesses(), is(successes));

        final boolean isAddedAgain = crawlerResults.addSuccess(success);
        assertThat(isAddedAgain, is(false));
    }

    @Test
    public void testAddSkipped() throws Exception {
        final CrawlerResults crawlerResults = new CrawlerResults();
        final String skipped = "Hello";
        Set<String> skippeds = Sets.newHashSet(skipped);

        final boolean isAdded = crawlerResults.addSkipped(skipped);
        assertThat(isAdded, is(true));
        assertThat(crawlerResults.getSkipped(), is(skippeds));

        final boolean isAddedAgain = crawlerResults.addSkipped(skipped);
        assertThat(isAddedAgain, is(false));
    }

    @Test
    public void testAddError() throws Exception {
        final CrawlerResults crawlerResults = new CrawlerResults();
        final String error = "Hello";
        Set<String> errors = Sets.newHashSet(error);

        final boolean isAdded = crawlerResults.addError(error);
        assertThat(isAdded, is(true));
        assertThat(crawlerResults.getErrors(), is(errors));

        final boolean isAddedAgain = crawlerResults.addError(error);
        assertThat(isAddedAgain, is(false));
    }
}