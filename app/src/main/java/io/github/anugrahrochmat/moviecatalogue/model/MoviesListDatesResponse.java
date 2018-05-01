package io.github.anugrahrochmat.moviecatalogue.model;

import com.google.gson.annotations.SerializedName;

public class MoviesListDatesResponse {

    @SerializedName("maximum")
    private String maximum;
    @SerializedName("minimum")
    private String minimum;

    public String getMaximum() {
        return maximum;
    }

    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }

    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }
}
