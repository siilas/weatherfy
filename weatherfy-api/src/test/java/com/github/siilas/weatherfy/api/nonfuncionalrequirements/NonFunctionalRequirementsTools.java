package com.github.siilas.weatherfy.api.nonfuncionalrequirements;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.siilas.weatherfy.api.tools.WeatherfyTools;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public abstract class NonFunctionalRequirementsTools extends WeatherfyTools {

}
