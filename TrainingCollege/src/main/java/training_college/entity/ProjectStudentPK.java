package training_college.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by ss14 on 2017/3/15.
 */
public class ProjectStudentPK implements Serializable {
    private int pid;
    private int sid;

    @Column(name = "pid")
    @Id
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Column(name = "sid")
    @Id
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

        ProjectStudentPK that = (ProjectStudentPK) o;

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
