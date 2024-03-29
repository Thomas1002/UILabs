import course.labs.todomanager.ToDoManagerActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;

public class TestCancel extends
		ActivityInstrumentationTestCase2<ToDoManagerActivity> {
	private Solo solo;

	public TestCancel() {
		super(ToDoManagerActivity.class);
	}

	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation());
		getActivity();
	}

	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

	public void testRun() {

		int timeout = 10;

		// Wait for activity: 'course.labs.todomanager.ToDoManagerActivity'
		assertTrue(
				"'course.labs.todomanager.ToDoManagerActivity' not found",
				solo.waitForActivity(
						ToDoManagerActivity.class, 2000));

		// Click on action bar item
		solo.clickOnActionBarItem(0x1);

		// Click on Add New ToDo Item
		solo.clickOnView(solo.getView(course.labs.todomanager.R.id.footerView));

		// Wait for activity: 'course.labs.todomanager.AddToDoActivity'
		assertTrue(
				"course.labs.todomanager.AddToDoActivity is not found!",
				solo.waitForActivity(course.labs.todomanager.AddToDoActivity.class));

		// Hide the soft keyboard
		solo.hideSoftKeyboard();
		// Enter the text: 't3'
		solo.clearEditText((android.widget.EditText) solo
				.getView(course.labs.todomanager.R.id.title));
		solo.enterText((android.widget.EditText) solo
				.getView(course.labs.todomanager.R.id.title), "t3");
		// Hide the soft keyboard
		solo.hideSoftKeyboard();
		// Click on Done:
		solo.clickOnView(solo.getView(course.labs.todomanager.R.id.statusDone));
		// Click on High
		solo.clickOnView(solo
				.getView(course.labs.todomanager.R.id.highPriority));

		// Click on Cancel
		solo.clickOnView(solo
				.getView(course.labs.todomanager.R.id.cancelButton));

		// Click on Add New ToDo Item
		solo.clickOnView(solo.getView(course.labs.todomanager.R.id.footerView));
		// Hide the soft keyboard
		solo.hideSoftKeyboard();
		// Enter the text: 't4'
		solo.clearEditText((android.widget.EditText) solo
				.getView(course.labs.todomanager.R.id.title));
		solo.enterText((android.widget.EditText) solo
				.getView(course.labs.todomanager.R.id.title), "t4");

		// Hide the soft keyboard
		solo.hideSoftKeyboard();
		// Click on Done:
		solo.clickOnView(solo.getView(course.labs.todomanager.R.id.statusDone));
		// Click on Low
		solo.clickOnView(solo.getView(course.labs.todomanager.R.id.lowPriority));

		// Click on Submit
		solo.clickOnView(solo
				.getView(course.labs.todomanager.R.id.submitButton));

		// Wait for activity: 'course.labs.todomanager.ToDoManagerActivity'
		assertTrue(
				"course.labs.todomanager.ToDoManagerActivity is not found!",
				solo.waitForActivity(ToDoManagerActivity.class));

		assertFalse("'A ToDoItem with title, t3', was found",
				solo.searchText("t3"));

		// Click on action bar item to dump items to log
		solo.clickOnActionBarItem(0x2);
		
		assertTrue("Menu didn't close", solo.waitForDialogToClose());

		// Wait for Log Message 'Title:t4,Priority:LOW,Status:NOT DONE'
		assertTrue("Log message 'Title:t4,Priority:LOW,Status:NOT DONE' not found",
				solo.waitForLogMessage("Title:t4,Priority:LOW,Status:DONE",timeout));

	}
}
