/*
 * Copyright Heathman Innovative Solutions Jul 11, 2014.
 */

package helloworld;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.matchers.JUnitMatchers;

//    @Before

//    public void setup() {
public class System_out_Test {
    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();

    @Test
    public void system_out_test() {
        HelloWorld.main(null);
        Assert.assertThat(log.getLog(), JUnitMatchers.both(JUnitMatchers.containsString("Hello World")).and(JUnitMatchers.containsString("Hello World from HAL")));
    }
    
}
