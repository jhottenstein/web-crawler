package in.hottenste.jess;

import java.util.List;

public class CrawlerResults {
    private List<String> successes;
    private List<String> skipped;

    public CrawlerResults(List<String> successes, List<String> skipped) {
        this.successes = successes;
        this.skipped = skipped;
    }

    public List<String> getSuccesses() {
        return successes;
    }

    public List<String> getSkipped() {
        return skipped;
    }
}
