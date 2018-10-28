package googleDemo.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	private static ExtentReports extent;
	private static ExtentHtmlReporter htmlReporter;
	private static String filePath= "./extentreport.html";
	
	///   D:\\Workspace_NTT\\googleDemo\
	
	public static ExtentReports getExtent()
	{
		if(extent!=null)
			return extent;
		extent= new ExtentReports();
		extent.attachReporter(getHtmlReporter());
		return extent;
	}
	
	private static ExtentHtmlReporter getHtmlReporter()
	{
		htmlReporter= new ExtentHtmlReporter(filePath);
		Theme theme = Theme.STANDARD;
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Google Demo");
		htmlReporter.config().setReportName("Google Demo Report");
		htmlReporter.config().setTheme(theme);
		return htmlReporter;
	}

	

}
