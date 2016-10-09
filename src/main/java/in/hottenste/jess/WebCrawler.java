package in.hottenste.jess;

import com.google.common.collect.Iterables;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WebCrawler {
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    public List<String> crawl(Internet internet) {
        final List<String> successes = new LinkedList<>();
        final Queue<String> workQueue = new LinkedList<>();

        workQueue.add(getFirstAddress(internet));

        while(!workQueue.isEmpty()) {
            //get address of next page
            final String nextAddress = workQueue.remove();
            final Page nextPage = internet.findPage(nextAddress);
            //add current page to successes and visited
            successes.add(nextPage.getAddress());

            //get links and add to work queue
            final List<String> links = nextPage.getLinks();
            Iterables.addAll(workQueue, links);
        }


        return successes;
    }

    private String getFirstAddress(Internet internet) {
        return internet.getPages().get(0).getAddress();
    }
}
