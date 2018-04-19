/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import model.*;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author GiaHieu
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @RequestMapping("")
    public String showAll(ModelMap model) {
        StudentDAO st = new StudentDAO();
        List<Student> list = new ArrayList<Student>();
        list = st.showStudent("");
        model.addAttribute("listStudent", list);

        Student sv = new Student();
        sv = list.get(0);
        model.addAttribute("student", sv);
        return "student";
    }

    @ModelAttribute("major")
    public List<Major> getMajor() {
        MajorDAO mj = new MajorDAO();
        List<Major> major = new ArrayList<>();
        major = mj.listMajor();
        return major;
    }

    @RequestMapping("edit")
    public String edit(HttpServletRequest request, ModelMap model) {
        int masv = Integer.parseInt(request.getParameter("txtId"));
        String name = request.getParameter("txtName");
        String mark = request.getParameter("txtMark");
        String major = request.getParameter("txtMajor");
        Student sv = new Student(masv, name, mark, major);
        model.addAttribute("student", sv);

        List<Student> list = new ArrayList<Student>();
        list = StudentDAO.showStudent("");
        model.addAttribute("listStudent", list);
        return "student";
    }

    @RequestMapping("delete")
    public String delete(HttpServletRequest request, ModelMap model,
            @ModelAttribute("student") Student student) {
        int masv = Integer.parseInt(request.getParameter("txtId"));
        StudentDAO.deleteStudent(masv);

        List<Student> list = new ArrayList<Student>();
        list = StudentDAO.showStudent("");
        model.addAttribute("listStudent", list);
        return "student";
    }

    @RequestMapping(params = "btnInsert")
    public String insert(@ModelAttribute("student") Student student, ModelMap model) {
        StudentDAO.insertStudent(student);

        List<Student> list = new ArrayList<Student>();
        list = StudentDAO.showStudent("");
        model.addAttribute("listStudent", list);
        return "student";
    }

    @RequestMapping(params = "btnUpdate")
    public String update(@ModelAttribute("student") Student student, ModelMap model) {
        StudentDAO.updateStudent(student);

        List<Student> list = new ArrayList<Student>();
        list = StudentDAO.showStudent("");
        model.addAttribute("listStudent", list);
        return "student";
    }

}
