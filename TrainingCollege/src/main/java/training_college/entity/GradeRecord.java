package training_college.entity;

import javax.persistence.*;

/**
 * Created by ss14 on 2017/2/28.
 */
@Entity
@Table(name = "grade_record", schema = "training_college", catalog = "")
public class GradeRecord {
    private int id;
    private int orgSystemId;
    private String projectName;
    private String studentName;
    private int courseName;
    private int score;

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
    @Column(name = "course_name")
    public int getCourseName() {
        return courseName;
    }

    public void setCourseName(int courseName) {
        this.courseName = courseName;
    }

    @Basic
    @Column(name = "score")
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GradeRecord that = (GradeRecord) o;

        if (id != that.id) return false;
        if (orgSystemId != that.orgSystemId) return false;
        if (courseName != that.courseName) return false;
        if (score != that.score) return false;
        if (projectName != null ? !projectName.equals(that.projectName) : that.projectName != null) return false;
        if (studentName != null ? !studentName.equals(that.studentName) : that.studentName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + orgSystemId;
        result = 31 * result + (projectName != null ? projectName.hashCode() : 0);
        result = 31 * result + (studentName != null ? studentName.hashCode() : 0);
        result = 31 * result + courseName;
        result = 31 * result + score;
        return result;
    }
}
