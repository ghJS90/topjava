package ru.javawebinar.topjava.service.datajpa;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.ActiveDbProfileResolver;
import ru.javawebinar.topjava.service.UserServiceTest;

import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;

@ActiveProfiles(resolver = ActiveDbProfileResolver.class, value = "datajpa")
public class DatajpaUserServiceTest extends UserServiceTest {
    private static final Logger log = getLogger("result");
    private static final StringBuilder results = new StringBuilder();

    @Rule
    public final Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void finished(long nanos, Description description) {
            String result = String.format("\n%-29s %7d", description.getMethodName(), TimeUnit.NANOSECONDS.toMillis(nanos));
            results.append(result);
            log.info(result + " ms\n");
        }
    };

    @AfterClass
    public static void printResult() {
        log.info("\n-------------------------------------" +
                "\nDatajpaUserServiceTest   Duration, ms" +
                "\n-------------------------------------" +
                results +
                "\n-------------------------------------");
    }
}
