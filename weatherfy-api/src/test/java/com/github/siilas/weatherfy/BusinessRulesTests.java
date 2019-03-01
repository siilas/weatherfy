package com.github.siilas.weatherfy;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/BusinessRules.feature")
public class BusinessRulesTests {

}
