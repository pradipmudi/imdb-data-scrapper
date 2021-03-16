package com.sezzle.imdb.scrapper.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringJoiner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sezzle.imdb.scrapper.model.MovieDescription;

public class MovieUrlDataUtil {
	
	// map to store the url of each movie to fetch the movie/show data respectively
	private static LinkedHashMap<Integer, String> movieUrlsMap = null;
	private static final Charset UTF_8 = Charset.forName("UTF-8");
	private static final Charset ISO = Charset.forName("ISO-8859-1");

	public static String getMovieShowsList(String chart_url, int items_count) throws IOException {
		movieUrlsMap = new LinkedHashMap<Integer, String>();
		Document doc = Jsoup.connect(chart_url).get();
		Elements movieUrls = doc.select("div.lister").select("tbody.lister-list");
		int index=0;
		for(Element row : movieUrls.select("tr")) {
	        Elements column = row.select("td");
	        Elements aTag = column.get(0).select("a");
	        String movieUrl = "https://www.imdb.com/"+aTag.get(0).attr("href").toString();
	        movieUrlsMap.put(++index, movieUrl);
	        
	        // index has reached the threshold value of movie items_count,
	        // we break from the loop and no further store value in the map
	         
	        if(index == items_count) break;
	    }
		
		return getMoviesData(movieUrlsMap);
	}
	
	public static String getMoviesData(LinkedHashMap<Integer, String> movieUrlsMap) throws IOException {
		// declaring list to add description of each movie
		List<MovieDescription> movieDescriptionList = new ArrayList<MovieDescription>();
		
		for(Integer index : movieUrlsMap.keySet()) {
			String movieUrl = movieUrlsMap.get(index);
			Document doc = Jsoup.connect(movieUrl).get();
			Elements ratingValue = doc.select("div.ratingValue > strong > span");
			Elements titleWrapper = doc.select("div.title_wrapper");
			Elements summaryWrapper = doc.select("div.plot_summary_wrapper > div.plot_summary > div.summary_text");
			Elements genreWrapper = titleWrapper.select("div.subtext");
			
			// getting the genres for the movie
			StringJoiner genres = new StringJoiner(",");
			for(Element element : genreWrapper) {
				String href = element.select("a").get(0).attr("href").toString();
				String[] genreArray = element.select("a").text().split(" ");
				// taking "length-4" cause we don't want to add the release date data from the last "a tag href"
				for(int i=0;i<genreArray.length-4;i++) {
					genres.add(genreArray[i]);
				}
			}
			
			// getting the movie name and year from titleWrapper h1 text
			String[] movieNameAndYear = titleWrapper.select("h1").text().split("\\("); 
			
			/*
			 * System.out.println("Title : "+movieNameAndYear[0]+"\nReleaseYear : "
			 * +movieNameAndYear[1].replace(")", ""));
			 * System.out.println("IMDb Rating : "+ratingValue.text());
			 * System.out.println("Duration : "+titleWrapper.select("div.subtext > time").
			 * text()); System.out.println("Genres : "+genres);
			 * System.out.println("Summary : "+summaryWrapper.text()+"\n\n");
			 */

			MovieDescription movieDescription = new MovieDescription();
			movieDescription.setTitle(movieNameAndYear[0]);
			movieDescription.setGenre(genres.toString());
			movieDescription.setDuration(titleWrapper.select("div.subtext > time").text());
			movieDescription.setImdb_rating(Double.valueOf(ratingValue.text()));
			movieDescription.setMovie_release_year(Integer.valueOf(movieNameAndYear[1].replace(")", "")));
			movieDescription.setSummary(new String(summaryWrapper.text().toString().getBytes(ISO), UTF_8));
			
			movieDescriptionList.add(movieDescription);
			
		}
		
		// clearing the URL map
		movieUrlsMap.clear();
		
		return JsonUtil.getInstance().toJson(movieDescriptionList);
		
	}
	

}
