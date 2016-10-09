package in.hottenste.jess;

import com.google.common.collect.Iterables;

import java.util.*;

public class WebCrawler {
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    public CrawlerResults crawl(Internet internet) {
        final Set<String> visited = new HashSet<>();
        final Queue<String> workQueue = createWorkQueue(internet);

        final CrawlerResults results = new CrawlerResults();

        while(!workQueue.isEmpty()) {
            //get address of next page - skip if visited
            final String nextAddress = workQueue.remove();
            if (visited.contains(nextAddress)) {
                results.addSkipped(nextAddress);
            } else {
                //add current page to successes and visited
                final Optional<Page> page = internet.findPage(nextAddress);
                if (page.isPresent()) {
                    final Page nextPage = page.get();
                    results.addSuccess(nextPage.getAddress());
                    visited.add(nextPage.getAddress());

                    addLinksToWorkQueue(workQueue, nextPage);
                } else {
                    results.addError(nextAddress);
                }
            }
        }

        return results;
    }

    private Queue<String> createWorkQueue(Internet internet) {
        final Queue<String> workQueue = new LinkedList<>();
        final List<Page> pages = internet.getPages();
        if (!pages.isEmpty()) {
            workQueue.add(pages.get(0).getAddress());
        }
        return workQueue;
    }

    private void addLinksToWorkQueue(Queue<String> workQueue, Page nextPage) {
        final List<String> links = nextPage.getLinks();
        Iterables.addAll(workQueue, links);
    }

}
