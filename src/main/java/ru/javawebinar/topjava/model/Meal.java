package ru.javawebinar.topjava.model;

import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * GKislin
 * 11.01.2015.
 */

@NamedQueries({
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal WHERE id=:id and user.id=:user_id"),
        @NamedQuery(name = Meal.UPDATE, query = "UPDATE Meal m SET m.calories=:calories, m.dateTime=:dateTime, m.description=:description WHERE m.id=:id and m.user.id=:user_id"),
        @NamedQuery(name = Meal.BY_ID, query = "SELECT m FROM Meal m WHERE m.id=:id and m.user.id=:user_id"),
        @NamedQuery(name = Meal.ALL, query = "SELECT m FROM Meal m WHERE m.user.id=:user_id ORDER BY m.dateTime DESC"),
        @NamedQuery(name = Meal.BETWEEN_DATETIMES, query = "SELECT m FROM Meal m WHERE m.user.id=:user_id AND m.dateTime>=:startDate and m.dateTime<=:endDate ORDER BY m.dateTime DESC")
})

@Entity
@Table(name = "meals", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_time"}, name = "meals_unique_user_datetime_idx")})
public class Meal extends BaseEntity {

    public static final String DELETE = "Meal.delete";
    public static final String UPDATE = "Meal.update";
    public static final String BY_ID = "Meal.getById";
    public static final String ALL = "Meal.getAll";
    public static final String BETWEEN_DATETIMES = "Meal.getBetween";

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "description", nullable = false)
    @NotEmpty
    private String description;

    @Column(name = "calories")
    @Digits(fraction = 0, integer = 4)
    private int calories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(foreignKeyDefinition = "id"))
    private User user;

    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
