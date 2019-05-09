package com.github.siilas.weatherfy.api.nonfuncionalrequirements;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/NonFunctionalRequirements.feature")
public class NonFunctionalRequirementsTest {

}
