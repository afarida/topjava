package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.Profiles;

import javax.sql.DataSource;
import java.time.LocalDateTime;

@Repository
@Configuration
@Profile(Profiles.POSTGRES)
public class JdbcPostgresMealRepositoryImpl extends JdbcMealRepositoryImpl {

    @Autowired
    public JdbcPostgresMealRepositoryImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    LocalDateTime getDateTime(LocalDateTime dateTime) {
        return dateTime;
    }
}
