/*
 * Copyright Heathman Innovative Solutions Jul 11, 2014.
 */
package helloworld;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardErrorStreamLog;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import static org.junit.matchers.JUnitMatchers.both;
import static org.junit.matchers.JUnitMatchers.containsString;

/**
 * HelloWorld integration tests.
 *
 * @author Brad
 */
public class HelloWorldTest {

    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();
    @Rule
    public final StandardErrorStreamLog errLog = new StandardErrorStreamLog();

    /**
     * Standard out and err tests.
     */
    @Test
    public void system_out_test() {
        HelloWorld.main(null);
        Assert.assertThat("System.out test", log.getLog(),
                both(
                        containsString("Hello World"))
                .and(containsString("Hello World from HAL"))
        );
        Assert.assertThat("System.err test", errLog.getLog(),
                containsString("This is JADE")
        );
    }

    @AfterClass
    public static void shut_down_JADE() {
        jade.core.Runtime.instance().shutDown();
    }
}
