package com.ritvik.cli.commands;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.IOException;
import java.util.Objects;

@ShellComponent
@ShellCommandGroup(value = "Web Scraper")
public class WebScraperCommand {
    private String title;
    private String author;
    public static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    @ShellMethod(value = "get latest developer news",key = "news")
    private void extractData() {
        try {
            String url = "https://sanantonioreport.org/technology";
            Document document = Jsoup.connect(url).get();
            Elements elements = document.getElementsByClass("entry-wrapper");
            for (Element element: elements) {
                if (!element.select("h2.entry-title").isEmpty()){
                    title = Objects.requireNonNull(element.select("h2.entry-title").first()).text();
                }else{
                    title = Objects.requireNonNull(element.select("h3.entry-title").first()).text();
                }
                author = Objects.requireNonNull(element.select("div.entry-meta").first()).text().split(" ")[1];
                System.out.println(ANSI_YELLOW+title+ANSI_RESET);
                System.out.println(ANSI_RED+author+ANSI_RESET);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
