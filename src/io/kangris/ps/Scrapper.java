package io.kangris.generatenumber;

import android.content.Context;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.common.*;
import com.google.appinventor.components.runtime.*;
import com.google.appinventor.components.runtime.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

@DesignerComponent(category = ComponentCategory.EXTENSION, description = "PS Scrapper<br>by kangris", iconName = "aiwebres/view.png", nonVisible = true, version = 1)
@SimpleObject(external = true)
public class Scrapper extends AndroidNonvisibleComponent implements Component {
 private static final String LOG_TAG = "Scrapper";
 private ComponentContainer container;
 private Context context;
 private Document doc;
 private String h1 = "";
 private String baseUrl = "https://play.google.com/store/apps/details?id=";
 private String packageName = "com.okcupid.okcupid";
 
 String title;
 String rating;
 String ratingCount;
 String numberOf5Stars;;
 String numberOf4Stars;
 String numberOf3Stars = doc.select("div.rating-bar-container.three").text();
 String numberOf2Stars = doc.select("div.rating-bar-container.two").text();
 String numberOf1Stars = doc.select("div.rating-bar-container.one").text();
 String fileSize = doc.select("div[itemprop=fileSize]").text();
 String description = doc.select("div[jsname=C4s9Ed]").text();
 String version = doc.select("div[itemprop=softwareVersion]").text();
 String category = doc.select("span[itemprop=genre]").text();
 Elements screenshot=  doc.select("img[data-expand-to=full-screenshot-0]");
 String logo = doc.select("img[class=cover-image]").get(0).absUrl("src");
 String screenshotUrl = screenshot.get(0).absUrl("src"); // 1st screenshot
 String datePublished;  
 String numDownloaded;
 
 public Generate(ComponentContainer container) {
    super(container.$form());
    this.container = container;
    context = (Context) container.$context();
 }
	
 @SimpleFunction
 public void Scrape(String packageName){
        // JSoup Example 2 - Reading HTML page from URL
        try {
            doc = Jsoup.connect(baseUrl + packageName).userAgent("Mozilla/4.0").get();

            String title = doc.title(); // title of the app
            String rating = doc.select("div.score").text(); // rating of the app
            String ratingCount = doc.select("div.reviews-stats").text(); //number of people who have rated
            String numberOf5Stars = doc.select("div.rating-bar-container.five").text();
            String numberOf4Stars = doc.select("div.rating-bar-container.four").text();
            String numberOf3Stars = doc.select("div.rating-bar-container.three").text();
            String numberOf2Stars = doc.select("div.rating-bar-container.two").text();
            String numberOf1Stars = doc.select("div.rating-bar-container.one").text();
            String fileSize = doc.select("div[itemprop=fileSize]").text();
            String description = doc.select("div[jsname=C4s9Ed]").text();
            String version = doc.select("div[itemprop=softwareVersion]").text();
            String category = doc.select("span[itemprop=genre]").text();
            Elements screenshot=  doc.select("img[data-expand-to=full-screenshot-0]");
            String logo = doc.select("img[class=cover-image]").get(0).absUrl("src");
            String screenshotUrl = screenshot.get(0).absUrl("src"); // 1st screenshot
            String datePublished = doc.select("div[itemprop=datePublished]").text();
            String numDownloaded = doc.select("div[itemprop=numDownloads]").text();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
