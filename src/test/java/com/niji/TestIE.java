package com.niji;

import com.google.common.io.Files;
import com.niji.data.ProjectData;
import com.niji.factory.Instantiation;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

@CucumberOptions(
		plugin = { "html:target/report/ie", "json:target/json/ie/reportIE.json"},
		strict = true,
		features = {"src/test/resources/scenarios/desktop/"}//,
		//tags ={"@referrals"}
)

/**
 * @author gaetan.rageul
 *
 */
public class TestIE extends Instantiation {
	private TestNGCucumberRunner testNGCucumberRunner;

	public static String classe = TestIE.class.getSimpleName();

	@BeforeClass(alwaysRun = true)
	public void setUpClass() {
		System.out.println("Cucumber Test Class Before");
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}

	@Test(groups = "cucumber", description = "Runs LoginCandiate Feature", dataProvider = "features")
	public void feature(PickleEventWrapper eventwrapper,CucumberFeatureWrapper cucumberFeature) throws Throwable {
		testNGCucumberRunner.runScenario(eventwrapper.getPickleEvent());
	}

	@DataProvider
	public Object[][] features() {
		return testNGCucumberRunner.provideScenarios();
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass(){
		if (testNGCucumberRunner == null) {
			return;
		}
		testNGCucumberRunner.finish();
	}

	@AfterTest
	public static void reportJson() throws Exception{
		String xmlFiles = System.getProperty("suiteXmlFile");
		File reportJson = new File("target/json/"+xmlFiles);
		if (!reportJson.exists()) {
			reportJson.mkdirs();
			reportJson.setWritable(true, false);
			reportJson.setReadable(true, false);
			reportJson.setExecutable(true, false);
		}
		//}
		Files.copy(new File("target/json/ie/reportIE.json"), new File(new File("target/json/"+xmlFiles)+"/"+"reportIE.json"));
	}

	public static BufferedImage resize(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}
}