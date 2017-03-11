package training_college.entity;

import javax.persistence.*;

/**
 * Created by ss14 on 2017/3/11.
 */
@Entity
@Table(name = "new_schedule", schema = "training_college", catalog = "")
@IdClass(NewSchedulePK.class)
public class NewSchedule {
    private int projectId;
    private int courseId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewSchedule that = (NewSchedule) o;

        if (projectId != that.projectId) return false;
        if (courseId != that.courseId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = projectId;
        result = 31 * result + courseId;
        return result;
    }
}
