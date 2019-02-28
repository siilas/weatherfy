package com.github.siilas.weatherfy.openweathermap.response;

import org.junit.Assert;
import org.junit.Test;

public class TemperatureTest {

	@Test
	public void shouldBeAbove30Degrees() {
		Temperature temperature = new Temperature(35.0);
		Assert.assertTrue(temperature.isAbove30Degrees());
	}

	@Test
	public void shouldBeBetween15And30Degrees() {
		Temperature temperature = new Temperature(15.0);
		Assert.assertTrue(temperature.isBetween15And30Degrees());
	}

	@Test
	public void shouldBeBetween10And14Degrees() {
		Temperature temperature = new Temperature(14.0);
		Assert.assertTrue(temperature.isBetween10And14Degrees());
	}

	@Test
	public void shouldBeBelow10Degrees() {
		Temperature temperature = new Temperature(5.0);
		Assert.assertTrue(temperature.isBelow10Degrees());
	}

}
