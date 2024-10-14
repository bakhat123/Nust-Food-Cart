package com.example.layouts.model;

import java.util.Objects;

public class RatingData {
   private String recommendation;
   private String name;
   private float rating;

    public RatingData(String recommendation, String name, float rating) {
        this.recommendation = recommendation;
        this.name = name;
        this.rating = rating;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatingData that = (RatingData) o;
        return Float.compare(rating, that.rating) == 0 && Objects.equals(recommendation, that.recommendation) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recommendation, name, rating);
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "RatingData{" +
                "recommendation='" + recommendation + '\'' +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                '}';
    }
}
