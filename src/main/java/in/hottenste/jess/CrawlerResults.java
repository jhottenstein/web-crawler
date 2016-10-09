package in.hottenste.jess;

import java.util.HashSet;
import java.util.Set;

public class CrawlerResults {
    private final Set<String> successes;
    private final Set<String> skipped;
    private final Set<String> errors;

    public CrawlerResults() {
        successes = new HashSet<>();
        skipped = new HashSet<>();
        errors = new HashSet<>();
    }

    public CrawlerResults(Set<String> successes, Set<String> skipped, Set<String> errors) {
        this.successes = successes;
        this.skipped = skipped;
        this.errors = errors;
    }

    public Set<String> getSuccesses() {
        return successes;
    }

    public Set<String> getSkipped() {
        return skipped;
    }

    public Set<String> getErrors() {
        return errors;
    }

    public boolean addSuccess(String nextAddress) {
        return successes.add(nextAddress);
    }

    public boolean addSkipped(String nextAddress) {
        return skipped.add(nextAddress);
    }

    public boolean addError(String nextAddress) {
        return errors.add(nextAddress);
    }
}
