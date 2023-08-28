package pl.coderslab.model;

import java.time.LocalDate;

public class Plan {
    private int id;
    private String name;
    private String description;
    private LocalDate created;
    private int adminId;

    public Plan() {
    }

    public Plan(String name, String description, LocalDate datetime, int adminId) {
        this.name = name;
        this.description = description;
        this.created = datetime;
        this.adminId = adminId;
    }

    public Plan(int id, String name, String description, LocalDate datetime, int adminId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = datetime;
        this.adminId = adminId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate datetime) {
        this.created = datetime;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", datetime=" + created +
                ", adminId=" + adminId +
                '}';
    }
}
