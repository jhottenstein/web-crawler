package in.hottenste.jess;

import com.google.common.collect.Iterables;

import java.util.*;

public class WebCrawler {
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    public CrawlerResults crawl(Internet internet) {
        final List<String> successes = new LinkedList<>();
        final Set<String> skipped = new HashSet<>();
        final Set<String> errors = new HashSet<>();

        final Queue<String> workQueue = new LinkedList<>();
        final Set<String> visited = new HashSet<>();

        workQueue.add(getFirstAddress(internet));

        while(!workQueue.isEmpty()) {
            //get address of next page - skip if visited
            final String nextAddress = workQueue.remove();
            if (visited.contains(nextAddress)) {
                if (!skipped.contains(nextAddress)) {
                    skipped.add(nextAddress);
                }
            } else {
                //add current page to successes and visited
                final Optional<Page> page = internet.findPage(nextAddress);
                if (page.isPresent()) {
                    final Page nextPage = page.get();
                    successes.add(nextPage.getAddress());
                    visited.add(nextPage.getAddress());

                    addLinksToWorkQueue(workQueue, nextPage);
                } else {
                    errors.add(nextAddress);
                }
            }
        }

        return new CrawlerResults(successes, skipped, errors);
    }

    private void addLinksToWorkQueue(Queue<String> workQueue, Page nextPage) {
        final List<String> links = nextPage.getLinks();
        Iterables.addAll(workQueue, links);
    }

    private String getFirstAddress(Internet internet) {
        //TODO protect against empty Pages
        return internet.getPages().get(0).getAddress();
    }
}
