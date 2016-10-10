package in.hottenste.jess;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class CrawlerResults {
    private final Set<String> successes;
    private final Set<String> skipped;
    private final Set<String> errors;

    public CrawlerResults() {
        successes = new LinkedHashSet<>();
        skipped = new LinkedHashSet<>();
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

    public void print(PrintStream out) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            out.println("Success:");
            out.println(mapper.writeValueAsString(successes));
            out.println();
            out.println("Skipped:");
            out.println(mapper.writeValueAsString(skipped));
            out.println();
            out.println("Error:");
            out.println(mapper.writeValueAsString(errors));
            out.flush();
        } catch (IOException e) {
            out.println("An unknown error occurred");
        }
    }
}
