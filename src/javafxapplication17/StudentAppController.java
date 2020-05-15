/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication17;


import java.net.URL;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author ريما
 */
public class StudentAppController implements Initializable {

    @FXML
    private TextField txtFieldID;
    @FXML
    private TextField txtFieldName;
    @FXML
    private TextField txtFieldMajor;
    @FXML
    private TextField txtFieldGrade;
    @FXML
    private TableView<Student> tableView;
    @FXML
    private TableColumn<Student, Integer> tcID;
    @FXML
    private TableColumn<Student, String> tcName;
    @FXML
    private TableColumn<Student, String> tcMajor;
    @FXML
    private TableColumn<Student, Double> tcGrade;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnShow;
    @FXML
    private Button btnAddCourse;
    @FXML
    private Button btnExit;
    Statement statement;
    private EntityManagerFactory emf;
   Student s;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       tcID.setCellValueFactory(new PropertyValueFactory("id"));
        tcName.setCellValueFactory(new PropertyValueFactory("name"));
        tcMajor.setCellValueFactory(new PropertyValueFactory("major"));
        tcGrade.setCellValueFactory(new PropertyValueFactory("grade"));
        tableView.getSelectionModel().selectedItemProperty().addListener(
                event-> showSelectedStudent() );
       this.emf = Persistence.createEntityManagerFactory("JavaFXApplication17PU");
    }    

    @FXML
    private void btnAddhandle(ActionEvent event) {
        Student s = new Student();
        s.setId(Integer.parseInt(txtFieldID.getText()));
        s.setName(txtFieldName.getText());
        s.setMajor(txtFieldMajor.getText());
        s.setGrade(Double.parseDouble(txtFieldGrade.getText()));
        EntityManager em = this.emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(s);
        em.getTransaction().commit();
        em.close();
    }
    private void showSelectedStudent() {
         s = tableView.getSelectionModel().getSelectedItem();
        if(s != null){
        txtFieldID.setText(String.valueOf(s.getId()));
        txtFieldName.setText(s.getName());
        txtFieldMajor.setText(s.getMajor());
        txtFieldGrade.setText(String.valueOf(s.getGrade()));
    }
    }
    @FXML
    private void btnEdithandle(ActionEvent event) {
        EntityManager em = this.emf.createEntityManager();
        Student student1 = em.find(Student.class, tableView.getSelectionModel().getSelectedItem().getId());
        student1.setName(txtFieldName.getText());
        student1.setMajor(txtFieldMajor.getText());
        student1.setGrade(Double.parseDouble(txtFieldGrade.getText()));
            em.getTransaction().begin();
             em.merge(student1);
            em.getTransaction().commit();
            }
    

    @FXML
    private void btnDeletehandle(ActionEvent event) throws Exception {
    EntityManager em = emf.createEntityManager();
    Student s = em.find(Student.class, tableView.getSelectionModel().getSelectedItem().getId());
    em.getTransaction().begin();
    em.remove(s);
    em.getTransaction().commit();
    }

    @FXML
    private void btnShowhandle(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        List<Student> student = em.createNamedQuery("Student.findAll").getResultList();
        tableView.getItems().setAll(student);
        em.close();
    }

    @FXML
    private void btnAddCoursehandle(ActionEvent event) {
         try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddCourse.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
    } catch(Exception e) {
        e.printStackTrace();
    }
    }

    @FXML
    private void btnExithandle(ActionEvent event) {
        System.exit(0);
    }

   
}
