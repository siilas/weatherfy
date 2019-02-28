package com.github.siilas.weatherfy.spotify.deserializer;

import org.junit.Assert;
import org.junit.Test;

public class DateYearDeserializerTest {
    
    @Test
    public void shouldGetYearOfCompleteDate() {
        Integer year = new DateYearDeserializer().getYear("2018-05-02");
        Assert.assertEquals(new Integer(2018), year);
    }

}
