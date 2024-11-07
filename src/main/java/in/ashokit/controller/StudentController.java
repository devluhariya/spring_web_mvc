package in.ashokit.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.binding.Student;
import in.ashokit.entity.studentEntity;
import in.ashokit.repository.StudentRepository;

@Controller
public class StudentController {
	@Autowired
	private StudentRepository repo;
	
	//method to load the student form
	@GetMapping("/")
	public String loadForm(Model model) {
		loadFormData(model);
		
		return "index";
	}
	private void loadFormData(Model model) {
		List<String> coursesList = new ArrayList();
		coursesList.add("Java");
		coursesList.add("Python");
		coursesList.add("Data Analyst");
		coursesList.add("DevOps");
		coursesList.add("AWS");
		coursesList.add("UI/UX");
		
		List<String> timingsList = new ArrayList();
		timingsList.add("Morning");
		timingsList.add("Afternoon");
		timingsList.add("Evening");
		
		model.addAttribute("courses", coursesList);
		model.addAttribute("timings", timingsList);
		
		Student student = new Student();
		model.addAttribute("student", student);
	}
	//method to save student form data
	@PostMapping("/save")
	public String handleSubmit(Student s, Model model) {
		System.out.println(s);
		//logic to save
		studentEntity entity = new studentEntity();
		//copy data from binding obj to entity obj
		BeanUtils.copyProperties(s, entity);
		entity.setTimings(Arrays.toString(s.getTimings()));
		repo.save(entity);
		
		loadFormData(model);
		
		model.addAttribute("msg", "Student saved!!");
		return "index";
	}
	//method to display show student data
	@GetMapping("/viewStudents")
	public String getStudentsData(Model model) {
		//logic to fetch and send student data
		List<studentEntity> studentslist = repo.findAll();
		model.addAttribute("students", studentslist);
		return "data";
	}
}
