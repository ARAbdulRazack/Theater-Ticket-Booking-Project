package util;

import java.sql.Timestamp;
import java.util.List;

public class Theater {
    private int id;
    private int createdBy;
    private String name;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private List<Movie> movies;

    // Constructors, getters, and setters...
    public Theater() {
        // Initialize any default values if needed
    }

    // Parameterized constructor
    public Theater(int createdBy, String name) {
        this.createdBy = createdBy;
        this.name = name;
    }
    
    // Example constructor
    public Theater(int id, int createdBy, String name, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.createdBy = createdBy;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    public Theater(int id, String name, List<Movie> movies) {
        this.id = id;
        this.name = name;
        this.movies = movies;
    }
    
    public List<Movie> getMovies() {
        return movies;
    }

    // Example getters
    public int getId() {
        return id;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public String getName() {
        return name;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
    
    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
