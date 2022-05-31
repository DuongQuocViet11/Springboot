package com.aptech.hellospring.controller;


import com.aptech.hellospring.entity.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {
    private static List<Student> list = new ArrayList<>();

    public StudentController() {
        list = new ArrayList<>();
        list.add(Student.builder().rollNumber("S001").fullName("Quoc Viet").email("duongquocv@gmail.com").build());
        list.add(Student.builder().rollNumber("S002").fullName("Quoc").email("quoc@gmail.com").build());
        list.add(Student.builder().rollNumber("S003").fullName("Viet").email("viet@gmail.com").build());
        list.add(Student.builder().rollNumber("S004").fullName("Thanh").email("thanh@gmail.com").build());
        list.add(Student.builder().rollNumber("S005").fullName("Duong").email("duong@gmail.com").build());
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Student> findAll(){
        return list;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Student save(@RequestBody Student student){
        list.add(student);
        return student;
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public Student findById(@PathVariable String id){
        int foundIndex = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRollNumber().equals(id)){
                foundIndex = i;
                break;
            }
        }
    if (foundIndex == -1){
        return null;
    }
    return list.get(foundIndex);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public boolean deleteById(@PathVariable String id){
        int foundIndex = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRollNumber().equals(id)){
                foundIndex = i;
                break;
            }
        }
        if (foundIndex == -1){
            return false;
        }
        list.remove(foundIndex);
        return true;
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public Student update(@PathVariable String id, @RequestBody Student updateStudent){
        int foundIndex = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRollNumber().equals(id)){
                foundIndex = i;
                break;
            }
        }
        if (foundIndex == -1){
            return null;
        }
        Student existing = list.get(foundIndex);
        existing.setFullName(updateStudent.getFullName());
        existing.setEmail(updateStudent.getEmail());
        existing.setPhone(updateStudent.getPhone());
        existing.setAddress(updateStudent.getAddress());
        existing.setNote(updateStudent.getNote());
        return existing;
    }
}
