package io.github.anugrahrochmat.moviecatalogue.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesListResponse {
    @SerializedName("results")
    private List<Movie> results = null;
    @SerializedName("page")
    private Integer page;
    @SerializedName("total_results")
    private Integer totalResults;
    @SerializedName("dates")
    private MoviesListDatesResponse dates;
    @SerializedName("total_pages")
    private Integer totalPages;

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public MoviesListDatesResponse getDates() {
        return dates;
    }

    public void setDates(MoviesListDatesResponse dates) {
        this.dates = dates;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
