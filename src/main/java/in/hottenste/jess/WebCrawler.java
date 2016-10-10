package in.hottenste.jess;

import com.google.common.base.Charsets;
import com.google.common.collect.Iterables;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class WebCrawler {


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
                //add current page to successes and visited if found
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

    public static void main( String[] args )
    {
        final WebCrawler webCrawler = new WebCrawler();
        String fileNumber = getFileNumber();
        try {
            final String jsonString = loadStringFromResource("internet" + fileNumber);
            final Internet internet = Internet.createFromJsonString(jsonString);
            final CrawlerResults crawl = webCrawler.crawl(internet);
            crawl.print(System.out);
        } catch (IOException e) {
            System.out.println("That internet wasn't found.");
        }

    }

    private static String getFileNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Which internet would you like to crawl? (1 or 2): ");
        return scanner.next();
    }

    static String loadStringFromResource(String resourceFile) throws IOException {
        URL url = Resources.getResource(resourceFile);
        return Resources.toString(url, Charsets.UTF_8);
    }

}
