package training_college.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by ss14 on 2017/3/14.
 */
@Entity
@Table(name = "post_project", schema = "training_college", catalog = "")
public class PostProject {
    private int pid;
    private String className;
    private Date fromDate;
    private Date toDate;
    private int totalPrice;

    @Id
    @Column(name = "pid")
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "class_name")
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Basic
    @Column(name = "from_date")
    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    @Basic
    @Column(name = "to_date")
    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @Basic
    @Column(name = "total_price")
    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int price) {
        this.totalPrice = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostProject that = (PostProject) o;

        if (pid != that.pid) return false;
        if (totalPrice != that.totalPrice) return false;
        if (className != null ? !className.equals(that.className) : that.className != null) return false;
        if (fromDate != null ? !fromDate.equals(that.fromDate) : that.fromDate != null) return false;
        if (toDate != null ? !toDate.equals(that.toDate) : that.toDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pid;
        result = 31 * result + (className != null ? className.hashCode() : 0);
        result = 31 * result + (fromDate != null ? fromDate.hashCode() : 0);
        result = 31 * result + (toDate != null ? toDate.hashCode() : 0);
        result = 31 * result + totalPrice;
        return result;
    }
}
