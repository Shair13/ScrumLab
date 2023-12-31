package pl.coderslab.model;

import java.sql.Timestamp;

public class Recipe {
    private int id;
    private String name;
    private String ingredients;
    private String description;
    private Timestamp created;
    private Timestamp updated;
    private String preparation_time;
    private String preparation;
    private int admin_id;

    public Recipe() {
    }

    public Recipe(String name, String ingredients, String description, String preparation_time, String preparation, int admin_id) {
        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
        this.preparation_time = preparation_time;
        this.preparation = preparation;
        this.admin_id = admin_id;
    }

    public Recipe(String name, String ingredients, String description, String preparation_time, String preparation, int admin_id, int id) {
        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
        this.preparation_time = preparation_time;
        this.preparation = preparation;
        this.admin_id = admin_id;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public String getPreparation_time() {
        return preparation_time;
    }

    public void setPreparation_time(String preparation_time) {
        this.preparation_time = preparation_time;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }
}