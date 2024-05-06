package co.com.example;

import org.junit.jupiter.api.Test;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;

import java.io.IOException;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class PerformanceTest {

    @Test
    public void testPerformance() throws IOException {
        TestPlanStats stats = testPlan(
                threadGroup(1, 10,
                        httpSampler("https://blazedemo.com/index.php")
                ),
                resultsTreeVisualizer(),
                htmlReporter("reports")
        ).run();
        assertThat(stats.overall().sampleTimePercentile99()).isLessThan(Duration.ofSeconds(5));
    }

}