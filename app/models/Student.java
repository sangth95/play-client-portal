package models;


import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;


public class Student{

	private Integer id;

	private String name;

	private Integer class_id;
	
	
	public Student() {
		super();
	}

	
	
	public Student(Integer id, String name, Integer class_id) {
		super();
		this.id = id;
		this.name = name;
		this.class_id = class_id;
	}



	public Student(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

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
	
	
	
	public Integer getClass_id() {
		return class_id;
	}

	public void setClass_id(Integer class_id) {
		this.class_id = class_id;
	}


	private Course course;
	



	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	
	public static Student[] parseToStudentsList(JsonNode jsonNode) {
		List<Student> studentsList = new ArrayList<Student>();
		for(JsonNode jsonChileNode : jsonNode) {
			Student student = new Student(
					Integer.parseInt(jsonChileNode.get("id").toString()),
					jsonChileNode.get("name").toString(),
					Integer.parseInt(jsonChileNode.get("class_id").toString())
					);
			studentsList.add(student);
		}
		Student[] students = studentsList.toArray(new Student[studentsList.size()]);
		return students;
	}
}
