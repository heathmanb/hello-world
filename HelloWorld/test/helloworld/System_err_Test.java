/*
 * Copyright Heathman Innovative Solutions Jul 11, 2014.
 */

package helloworld;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardErrorStreamLog;
import org.junit.matchers.JUnitMatchers;

/**
 * Test of standard error from main execution.
 */
public class System_err_Test {
    @Rule
    public final StandardErrorStreamLog log = new StandardErrorStreamLog();

    @Test
    public void system_err_test() {
        HelloWorld.main(null);
        Assert.assertThat(log.getLog(), JUnitMatchers.containsString("This is JADE"));
    }
    
}
