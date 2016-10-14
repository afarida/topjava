package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
@Configuration
@Profile(Profiles.HSQLDB)
public class JdbcHsqlDbMealRepositoryImpl extends JdbcMealRepositoryImpl implements MealRepository {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    public JdbcHsqlDbMealRepositoryImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    Object getDateTime(LocalDateTime dateTime)
    {
        return dateTime.format(DATE_TIME_FORMATTER);
    }
}
