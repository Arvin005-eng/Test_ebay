package org.report;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class JVMReport {
	public static void toGenerateReport(String json) {
		File file = new File("target");

		Configuration config = new Configuration(file, "eBay");

		config.addClassifications("Application", "eBay");
		config.addClassifications("Operation", "Testing");
		config.addClassifications("Tools", "selenium");

		List<String> lis = new LinkedList<>();

		lis.add(json);

		ReportBuilder r = new ReportBuilder(lis, config);
		r.generateReports();

	}
}
