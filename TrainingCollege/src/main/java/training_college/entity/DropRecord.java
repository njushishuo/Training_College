package training_college.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ss14 on 2017/2/28.
 */
@Entity
@Table(name = "drop_record", schema = "training_college", catalog = "")
public class DropRecord {
    private int id;
    private int orgSystemId;
    private int projectName;
    private String studentName;
    private Timestamp date;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "org_system_id")
    public int getOrgSystemId() {
        return orgSystemId;
    }

    public void setOrgSystemId(int orgSystemId) {
        this.orgSystemId = orgSystemId;
    }

    @Basic
    @Column(name = "project_name")
    public int getProjectName() {
        return projectName;
    }

    public void setProjectName(int projectName) {
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
        if (orgSystemId != that.orgSystemId) return false;
        if (projectName != that.projectName) return false;
        if (studentName != null ? !studentName.equals(that.studentName) : that.studentName != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + orgSystemId;
        result = 31 * result + projectName;
        result = 31 * result + (studentName != null ? studentName.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
