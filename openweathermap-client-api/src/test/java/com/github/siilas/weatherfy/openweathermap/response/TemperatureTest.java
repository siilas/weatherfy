package com.github.siilas.weatherfy.openweathermap.response;

import org.junit.Assert;
import org.junit.Test;

public class TemperatureTest {

	@Test
	public void shouldBeAbove30Degrees() {
		for (double value = 35; value > 30; value--) {
			Temperature temperature = new Temperature(value);
			Assert.assertTrue(temperature.isAbove30Degrees());
		}
	}
	
	@Test
	public void shouldNotBeAbove30Degrees() {
		for (double value = 25; value <= 30; value++) {
			Temperature temperature = new Temperature(value);
			Assert.assertFalse(temperature.isAbove30Degrees());
		}
	}

	@Test
	public void shouldBeBetween15And30Degrees() {
		for (double value = 15; value <= 30; value++) {
			Temperature temperature = new Temperature(value);
			Assert.assertTrue(temperature.isBetween15And30Degrees());
		}
	}

	@Test
	public void shouldBeBetween10And14Degrees() {
		for (double value = 10; value < 15; value++) {
			Temperature temperature = new Temperature(value);
			Assert.assertTrue(temperature.isBetween10And14Degrees());
		}
	}

	@Test
	public void shouldBeBelow10Degrees() {
		for (double value = -2; value < 10; value++) {
			Temperature temperature = new Temperature(value);
			Assert.assertTrue(temperature.isBelow10Degrees());
		}
	}

}
