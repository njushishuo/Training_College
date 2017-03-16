package training_college.entity;

import training_college.util.enumeration.PayMethod;
import training_college.util.enumeration.SelectMethod;
import training_college.util.enumeration.UserType;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by ss14 on 2017/3/14.
 */
@Entity
@Table(name = "drop_record", schema = "training_college", catalog = "")
public class DropRecord {
    private int id;
    private String orgSystemId;
    private String projectName;
    private String studentName;
    private int price;
    private int payment;
    private SelectMethod selectMethod;
    private UserType userType;
    private PayMethod payMethod;
    private Timestamp date;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "org_system_id")
    public String getOrgSystemId() {
        return orgSystemId;
    }

    public void setOrgSystemId(String orgSystemId) {
        this.orgSystemId = orgSystemId;
    }

    @Basic
    @Column(name = "project_name")
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "student_name")
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Basic
    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "payment")
    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }


    @Enumerated(EnumType.STRING)
    @Column(name = "select_method")
    public SelectMethod getSelectMethod() {
        return selectMethod;
    }

    public void setSelectMethod(SelectMethod selectMethod) {
        this.selectMethod = selectMethod;
    }


    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "pay_method")
    public PayMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PayMethod payMethod) {
        this.payMethod = payMethod;
    }

    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DropRecord that = (DropRecord) o;

        if (id != that.id) return false;
        if (orgSystemId != null ? !orgSystemId.equals(that.orgSystemId) : that.orgSystemId != null) return false;
        if (projectName != null ? !projectName.equals(that.projectName) : that.projectName != null) return false;
        if (studentName != null ? !studentName.equals(that.studentName) : that.studentName != null) return false;
        if (userType != null ? !userType.equals(that.userType) : that.userType != null) return false;
        if (payMethod != null ? !payMethod.equals(that.payMethod) : that.payMethod != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (orgSystemId != null ? orgSystemId.hashCode() : 0);
        result = 31 * result + (projectName != null ? projectName.hashCode() : 0);
        result = 31 * result + (studentName != null ? studentName.hashCode() : 0);
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        result = 31 * result + (payMethod != null ? payMethod.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
