/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication17;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author ريما
 */
public class AddCourseController implements Initializable {

    @FXML
    private TextField txtFieldStudent;
    @FXML
    private TextField txtFieldCourse;
    @FXML
    private TextField txtFieldSemester;
    @FXML
    private TableView<Registration> tableView;
    @FXML
    private TableColumn<Registration, Integer> tcStudent;
    @FXML
    private TableColumn<Registration, Integer> tcCourse;
    @FXML
    private TableColumn<Registration, String> tcSemester;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnShow;
    @FXML
    private Button btnExit;
    Student s;
    private EntityManagerFactory emf;
    private Student st;
    private Course c;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         tcStudent.setCellValueFactory(new PropertyValueFactory("studentid"));
        tcCourse.setCellValueFactory(new PropertyValueFactory("courseid"));
        tcSemester.setCellValueFactory(new PropertyValueFactory("semester"));
   //    this.emf = Persistence.createEntityManagerFactory("PR320202PU");
    }    

    @FXML
    private void btnAddhandle(ActionEvent event) {
         Registration r = new Registration();
        r.setStudentid(st);
        r.setCourseid(c);
        r.setSemester(txtFieldSemester.getText());
        EntityManager em = this.emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(r);
        em.getTransaction().commit();
        em.close();
    }

    @FXML
    private void btnShowhandle(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        List<Registration> re = em.createNamedQuery("Registration.findAll").getResultList();
        tableView.getItems().setAll(re);
        em.close();
    }

    @FXML
    private void btnExithandle(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void txtFieldStudenthandle(ActionEvent event) {
        EntityManager em = this.emf.createEntityManager();
        try{
        s = (Student) em.createNamedQuery("Registration.findByStudentid")
                .setParameter("id", Integer.parseInt(txtFieldStudent.getText()))
                .getSingleResult();
     //   txtFieldDeptName.setText(department.getName());
    //    txtFieldDeptLocation.setText(department.getLocation());
   //     txtFieldEmpDeptID.setText(department.getId()+"");
        }catch(NoResultException ex){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Retrieving");
            alert.setContentText("No records found");
            alert.showAndWait();
        }
        em.close();
    }

    @FXML
    private void txtFieldCoursehandle(ActionEvent event) {
         EntityManager em = this.emf.createEntityManager();
        try{
        Course c = (Course) em.createNamedQuery("Registration.findByCourseid")
                .setParameter("id", Integer.parseInt(txtFieldCourse.getText()))
                .getSingleResult();
     //   txtFieldEmpName.setText(employee.getName());
     //   txtFieldEmpDeptID.setText(employee.getDept_id()+"");
     //   txtFieldEmpSalary.setText(employee.getSalary()+"");
        }catch(NoResultException ex){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Retrieving");
            alert.setContentText("No records found");
            alert.showAndWait();
    }
    
}}
