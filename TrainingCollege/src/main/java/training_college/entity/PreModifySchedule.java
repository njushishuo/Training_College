package training_college.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ss14 on 2017/3/11.
 */
@Entity
@Table(name = "pre_modify_schedule", schema = "training_college", catalog = "")
@IdClass(PreModifySchedulePK.class)
public class PreModifySchedule {
    private int projectId;
    private int courseId;
    private Timestamp fromDate;
    private Timestamp toDate;
    private int price;

    @Id
    @Column(name = "project_id")
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Id
    @Column(name = "course_id")
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "from_date")
    public Timestamp getFromDate() {
        return fromDate;
    }

    public void setFromDate(Timestamp fromDate) {
        this.fromDate = fromDate;
    }

    @Basic
    @Column(name = "to_date")
    public Timestamp getToDate() {
        return toDate;
    }

    public void setToDate(Timestamp toDate) {
        this.toDate = toDate;
    }

    @Basic
    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PreModifySchedule that = (PreModifySchedule) o;

        if (projectId != that.projectId) return false;
        if (courseId != that.courseId) return false;
        if (price != that.price) return false;
        if (fromDate != null ? !fromDate.equals(that.fromDate) : that.fromDate != null) return false;
        if (toDate != null ? !toDate.equals(that.toDate) : that.toDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = projectId;
        result = 31 * result + courseId;
        result = 31 * result + (fromDate != null ? fromDate.hashCode() : 0);
        result = 31 * result + (toDate != null ? toDate.hashCode() : 0);
        result = 31 * result + price;
        return result;
    }
}
