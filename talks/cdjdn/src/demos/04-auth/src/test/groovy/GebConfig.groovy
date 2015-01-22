import geb.buildadapter.BuildAdapterFactory
import org.openqa.selenium.firefox.FirefoxDriver

if (!BuildAdapterFactory.getBuildAdapter(this.class.classLoader).reportsDir) {
  reportsDir = "build/geb"
}

driver = {
    def driverInstance = new FirefoxDriver()
    driverInstance.manage().window().maximize()
    driverInstance
}
