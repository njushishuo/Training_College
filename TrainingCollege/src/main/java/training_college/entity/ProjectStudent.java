package training_college.entity;

import javax.persistence.*;

/**
 * Created by ss14 on 2017/3/15.
 */
@Entity
@Table(name = "project_student", schema = "training_college", catalog = "")
@IdClass(ProjectStudentPK.class)
public class ProjectStudent {
    private int pid;
    private int sid;

    @Id
    @Column(name = "pid")
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Id
    @Column(name = "sid")
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectStudent that = (ProjectStudent) o;

        if (pid != that.pid) return false;
        if (sid != that.sid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pid;
        result = 31 * result + sid;
        return result;
    }
}
