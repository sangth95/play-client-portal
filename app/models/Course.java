package models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.List;

public class Course{

	public Course(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	private Integer id;

	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static Course[] parseToCourseList(JsonNode objectNode) {
		List<Course> courseList = new ArrayList<Course>();
		for (JsonNode objectChildNode : objectNode) {
			Course course = new Course(
					Integer.parseInt(objectChildNode.get("id").toString()),
					objectChildNode.get("name").toString()
			);
			courseList.add(course);
		}
		return courseList.toArray(new Course[courseList.size()]);
	}
}
