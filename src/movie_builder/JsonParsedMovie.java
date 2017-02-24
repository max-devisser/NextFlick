package src.movie_builder;

import java.util.List;

public class JsonParsedMovie {
	public class Collection {

	    public Integer id;
	    public String backdrop_path;
	    public String name;
	    public String poster_path;
	    public List<Part> parts;
	    public String overview;

	}

    public Integer id;

    public Boolean adult;
    public String backdrop_path;
    public Collection belongs_to_collection;
    public Integer budget;
    public List<Genre> genres;
    public String homepage;
    public String imdb_id;
    public String original_title;
    public String overview;
    public Double popularity;
    public String poster_path;
    public List<ProductionCompany> production_companies;
    public List<ProductionCountry> production_countries;
    public Date release_date;
    public Integer revenue;
    public Integer runtime;
    public List<SpokenLanguage> spoken_languages;
    public Status status;
    public String tagline;
    public String title;
    public Double vote_average;
public Integer vote_count;
	public Credits credits;
}
