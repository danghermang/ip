/**
 * Created by dangh on 14.05.2017.
 */
package com.frontpagenews.parsers;

import com.frontpagenews.models.ArticleModel;
import com.frontpagenews.models.SourceModel;
import com.frontpagenews.services.ArticleService;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TechCrunchParser {
    @Autowired
    ArticleService articleService;

    @Scheduled(initialDelay = 5000, fixedDelay=3600000)

    public void parseAll() {
        try {
            Document doc = Jsoup.connect("http://feeds.feedburner.com/TechCrunch/").get();
            Elements links = doc.select("item");

            for (Element link : links) {
                String url= link.getElementsByTag("link").text();
                parse(url);
            }
        } catch (IOException e){
            System.out.println (e.toString());
        }
    }


    private void parse(String url) throws IOException {
        try{
        Document doc=Jsoup.connect(url).get();
        String f_site= url;
        Elements title = doc.select("h1");
        String f_title = title.text();
        //System.out.println(f_title);



        Elements content = doc.getElementsByClass("article-entry text");
        String f_content = content.text();
        //System.out.println(f_content);

        Elements image= content.select("img");
        String f_image= image.attr("src");
        //System.out.println(f_image);

        Elements tags = doc.select("meta[name=sailthru.tags]");
        List<String> f_tags = new ArrayList<String>();
        String baseString= tags.attr("content");
        //System.out.println(baseString);
        String token="";
        for(int i=0;i<baseString.length();i++){
            if(baseString.charAt(i)==',')
            {
                if(token!="") {
                    f_tags.add(token);
                    //System.out.println(token);
                }
                token="";
                i++;
            }
            else {
                token += baseString.charAt(i);
            }
        }

        Elements author = doc.select("meta[name = author]");
        String f_author = author.attr("content");
        //System.out.println(f_author);

        Elements date= doc.getElementsByClass("timestamp");
        String d = date.attr("datetime");
        Date f_date = null;
        try{
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            f_date = df.parse(d);
            String newDateString = df.format(f_date);
            //System.out.println(newDateString);
            //System.out.println(f_date);

        } catch  (ParseException e) {
            e.printStackTrace();
        }



            SourceModel source = new SourceModel();
            source.setSite(f_site);
            source.setDate(f_date);
            source.setAuthor(f_author);

            ArticleModel article = new ArticleModel();
            article.setTitle(f_title);
            article.setContent(f_content);
            article.setImageUrl(f_image);
            article.setTags(f_tags);
            article.setSource(source);

//            System.out.println (article);
            articleService.save(article);

        }catch (IOException e){
            System.out.println (e.toString());
        }
        System.out.println("\n");
    }

    public static void main(String[] args){
        TechCrunchParser p = null;
        p = new TechCrunchParser();
        p.parseAll();
    }
}

