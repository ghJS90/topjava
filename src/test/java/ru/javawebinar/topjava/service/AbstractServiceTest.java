package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class AbstractServiceTest {
    @Test
    public void create() {
    }

    @Test
    public void duplicateMailCreate() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void deletedNotFound() {
    }

    @Test
    public void get() {
    }

    @Test
    public void getNotFound() {
    }

    @Test
    public void getByEmail() {
    }

    @Test
    public void update() {
    }

    @Test
    public void getAll() {
    }
}
