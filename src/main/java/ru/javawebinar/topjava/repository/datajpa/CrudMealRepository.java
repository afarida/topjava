package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.util.List;

/**
 * gkislin
 * 02.10.2016
 */
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    @Query("UPDATE Meal m SET m.dateTime=:dateTime, m.description=:description, m.calories=:calories " +
            "WHERE m.id=:id AND m.user.id=:userId")
    int update(@Param("dateTime") LocalDateTime dateTime,
               @Param("description") String description,
               @Param("calories") int calories,
               @Param("userId") int userId);

    @Override
    @Transactional
    Meal save(Meal s);

    @Transactional
    @Modifying
    @Query("DELETE FROM Meal WHERE id=:id AND user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT m FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
    Meal get(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT m FROM Meal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC")
    List<Meal> getAll(@Param("userId") int userId);

    @Query("SELECT m FROM Meal m WHERE m.user.id=:userId AND " +
            "m.dateTime BETWEEN :startDate AND :endDate ORDER BY m.dateTime DESC")
    List<Meal> getBetween(@Param("startDate") LocalDateTime startDate,
                          @Param("endDate") LocalDateTime endDate,
                          @Param("userId") int userId);

    @Query("SELECT u FROM User u WHERE u.id=:id")
    User getUser(@Param("id") int id);
}
