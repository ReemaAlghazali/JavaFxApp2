/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication17;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author ريما
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Registration.findAll",
            query = "SELECT r FROM Registration r"),
    @NamedQuery(name = "Registration.findByCourseid",
            query = "SELECT r FROM Registration r WHERE r.courseid = :courseid"),
    @NamedQuery(name = "Registration.findByStudentid",
            query = "SELECT r FROM Registration r WHERE r.studentid = :studentid"),
    })
public class Registration implements Serializable {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    private Student studentid;
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    private Course courseid;
    private String semester;

    public Registration() {
    }

    public Registration(Student studentid, Course courseid, String semester) {
        this.studentid = studentid;
        this.courseid = courseid;
        this.semester = semester;
    }

    public Student getStudentid() {
        return studentid;
    }

    public void setStudentid(Student studentid) {
        this.studentid = studentid;
    }

    public Course getCourseid() {
        return courseid;
    }

    public void setCourseid(Course courseid) {
        this.courseid = courseid;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
    
    
}
