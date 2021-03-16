package com.sezzle.imdb.scrapper.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MovieUrlUtil {
	
	// map to store the url of each movie to fetch the movie/show data respectively
	private static Map<Integer, String> movieUrlsMap = null;

	public static String getMovieShowsList(String chart_url, int items_count) throws IOException {
		movieUrlsMap = new HashMap<Integer, String>();
		Document doc = Jsoup.connect(chart_url).get();
		Elements movieUrls = doc.select("div.lister").select("tbody.lister-list");
		int index=0;
		for(Element row : movieUrls.select("tr")) {
	        Elements column = row.select("td");
	        Elements aTag = column.get(0).select("a");
	        String movieUrl = "https://www.imdb.com/"+aTag.get(0).attr("href").toString();
	        movieUrlsMap.put(++index, movieUrl);
//	        System.out.println(index+". "+movieUrl);
//	        System.out.print(" ");
//	        System.out.println(column.text());
	    }
		System.out.println(movieUrlsMap.toString());
		return null;
	}
	
	public static void main(String[] args) throws IOException {
		getMovieShowsList("https://www.imdb.com/india/top-rated-indian-movies/", 1);
	}

}
