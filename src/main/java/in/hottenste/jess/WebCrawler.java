package in.hottenste.jess;

import java.util.LinkedList;
import java.util.List;

public class WebCrawler {
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    public List<String> crawl(Internet internet) {
        final List<Page> pages = internet.getPages();
        final List<String> addresses = new LinkedList<>();

        final Page firstPage = dequeuePage(pages);
        addresses.add(firstPage.getAddress());
        return addresses;
    }

    private Page dequeuePage(List<Page> pages) {
        //Mutating pages
        return pages.remove(0);
    }
}
