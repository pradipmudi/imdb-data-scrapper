package com.sezzle.imdb.scrapper;

import java.io.IOException;

import com.sezzle.imdb.scrapper.util.MovieUrlUtil;

public class Driver {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String chart_url = args[0];
		int items_count = Integer.valueOf(args[1]);
		String response = MovieUrlUtil.getMovieShowsList(chart_url,items_count);
		System.out.println();

	}

}
