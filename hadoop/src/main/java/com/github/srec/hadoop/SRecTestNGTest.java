package com.github.srec.hadoop;

import com.github.srec.play.Player;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Test
public class SRecTestNGTest {
    @Parameters("script")
    public void test(String script) throws IOException, InvocationTargetException, InterruptedException {
        new Player().init().play(IOUtils.toInputStream(script), null);
    }
}