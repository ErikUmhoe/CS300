import java.io.IOException;
import java.util.function.Function;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
 
public class NetWikiLinkFunction implements Function<String,String> {
    @Override
    public String apply(String t) {
        try {
            // Download a Wikipedia page, using t in their internal link format: /wiki/Some_Subject 
            Document doc = Jsoup.connect("https://en.wikipedia.org" + t).get();
            // Use .css selector to retrieve a collection of links from this page's description
            //     "p a" selects links within paragraphs
            //     ":not(span a)" skips pronunciations
            //     ":not(sup a)" skips citations
            Elements links = doc.select("p a:not(span a):not(sup a)");
            // return the link attribute from the first element of this list
            return links.get(0).attr("href");
        // Otherwise return an appropriate error message:
        } catch(IOException|IllegalArgumentException e) { 
            return "FAILED to find wikipedia page: " + t; 
        } catch(IndexOutOfBoundsException e) { 
            return "FAILED to find a link in wikipedia page: " + t; 
        }			
    }
}