package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_03_Priority {
  @Test(description ="jira_01- create new employee success")
  public void endUser_create_new_employee() {
	Assert.assertTrue(false);
  }
  @Test (enabled =false)
  public void endUser_view_employee() {
	  
}
  @Test(enabled =false)
  public void endUser_edit_employee() {
	  
}
  @Test(enabled =false)
  public void endUser_move_employee() {
	  
}
}
