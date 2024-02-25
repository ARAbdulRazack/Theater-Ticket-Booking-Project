package util;

import java.sql.Timestamp;

public class Movie {
    private int id;
    private int createdBy;
    private String name;
    private String directorName;
    private String heroName;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Movie(int id, int createdBy, String name, String directorName, String heroName, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.createdBy = createdBy;
        this.name = name;
        this.directorName = directorName;
        this.heroName = heroName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    public Movie(int createdBy, String name, String directorName, String heroName) {
        this.createdBy = createdBy;
        this.name = name;
        this.directorName = directorName;
        this.heroName = heroName;
    }

    public Movie(int id, int createdBy, String name, String directorName, String heroName) {
    	this.id = id;
    	this.createdBy = createdBy;
        this.name = name;
        this.directorName = directorName;
        this.heroName = heroName;
	}

	public int getId() {
        return id;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public String getName() {
        return name;
    }

    public String getDirectorName() {
        return directorName;
    }

    public String getHeroName() {
        return heroName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
}
