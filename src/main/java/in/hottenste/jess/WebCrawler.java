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
        final List<String> skipped = new LinkedList<>();

        final Queue<String> workQueue = new LinkedList<>();
        final Set<String> visited = new HashSet<>();

        workQueue.add(getFirstAddress(internet));

        while(!workQueue.isEmpty()) {
            //get address of next page - skip if visited
            final String nextAddress = workQueue.remove();
            if (visited.contains(nextAddress)) {
                skipped.add(nextAddress);
            } else {
                //add current page to successes and visited
                final Page nextPage = internet.findPage(nextAddress).get();
                successes.add(nextPage.getAddress());
                visited.add(nextPage.getAddress());

                addLinksToWorkQueue(workQueue, nextPage);
            }
        }

        return new CrawlerResults(successes, skipped);
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
