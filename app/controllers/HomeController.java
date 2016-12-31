package controllers;

import models.Course;
import models.Student;
import play.libs.ws.*;
import play.libs.ws.WSRequest;
import play.mvc.*;
import views.html.*;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */

	 @Inject WSClient ws;
	
    public Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    //Get all students 
    public CompletionStage<Result> getStudentList() {
    	String url = "http://localhost:3000/student";
    	
    	WSRequest request = ws.url(url);
    	
    	return (CompletionStage<Result>)request.get().thenApply(response ->
    	ok(
    			studentForm.render(Student.parseToStudentsList(response.asJson()))
    	));
    }

    //get Student by Id
    public CompletionStage<Result> getStudentById(Integer id) {
    	String url = "http://localhost:3000/student/" + id;
    	
    	WSRequest request = ws.url(url);
    	
    	return request.get().thenApply(response ->
    	ok(response.asJson().get("response").toString())
    			);
    }


    //get all course
	public CompletionStage<Result> getCourseList() {
    	String url = "http://localhost:3000/courses";

    	WSRequest request = ws.url(url);

    	return (CompletionStage<Result>) request.get().thenApply(wsResponse ->
				ok(
						courseForm.render(Course.parseToCourseList(wsResponse.asJson()))
				)
		);
	}

	//get Course by Id
	public CompletionStage<Result> getCourseById(Integer id) {
    	String url = "http://localhost:3000/courses/" + id;

    	WSRequest wsRequest = ws.url(url);

    	return wsRequest.get().thenApply(wsResponse ->
			ok(wsResponse.asJson().get("response").toString())
		);
	}

	public CompletionStage<Result> getAllStudentsOfCourseById(Integer id) {
		String url = "http://localhost:3000/courses/all/" + id;
		WSRequest wsRequest = ws.url(url);
		return wsRequest.get().thenApply(wsResponse ->
			ok(studentForm.render(Student.parseToStudentsList(wsResponse.asJson())))
		);
	}


	//create course
	public Result createCourse() {
    	return ok(createCourseForm.render());
	}

	public CompletionStage<Result> createCourseResult(String name) {
    	String url = "http://localhost:3000/courses";
    	WSRequest wsRequest = ws.url(url);
    	wsRequest.setQueryParameter("name", name);
    	return wsRequest.post("content").thenApply(wsResponse ->
				ok(wsRequest.toString())
		);
	}
}
