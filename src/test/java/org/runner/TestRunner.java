package org.runner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.report.JVMReport;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(features="C:\\Users\\arvinth\\workspace\\Ebay.com\\src\\test\\resources\\Feature\\eBay.feature", 
glue="org.steprunner", monochrome=false,dryRun=false, snippets=SnippetType.CAMELCASE,
plugin = {"json:target\\ebayReport.json"}
, tags="@tag1"
)
public class TestRunner {

	@AfterClass
	public static void reportName() {
		JVMReport.toGenerateReport("target\\ebayReport.json");
	}
}
