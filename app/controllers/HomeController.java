package controllers;

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

}
