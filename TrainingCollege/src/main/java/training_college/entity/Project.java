package training_college.entity;

import training_college.util.enumeration.AddStatus;
import training_college.util.enumeration.ModifyStatus;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by ss14 on 2017/2/28.
 */
@Entity
public class Project {
    private int id;
    private Organization organization;
    private String className;
    private Timestamp fromDate;
    private Timestamp toDate;
    private int totalPrice;
    private int maxStdCnt;
    private int curStdCnt;


    private AddStatus addStatus;
    private ModifyStatus modifyStatus;
    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Basic
    @Column(name = "max_std_cnt")
    public int getMaxStdCnt() {
        return maxStdCnt;
    }

    public void setMaxStdCnt(int maxStdCnt) {
        this.maxStdCnt = maxStdCnt;
    }

    @Basic
    @Column(name = "cur_std_cnt")
    public int getCurStdCnt() {
        return curStdCnt;
    }

    public void setCurStdCnt(int curStdCnt) {
        this.curStdCnt = curStdCnt;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "add_status")
    public AddStatus getAddStatus() {
        return addStatus;
    }

    public void setAddStatus(AddStatus addStatus) {
        this.addStatus = addStatus;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "modify_status")
    public ModifyStatus getModifyStatus() {
        return modifyStatus;
    }

    public void setModifyStatus(ModifyStatus modifyStatus) {
        this.modifyStatus = modifyStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (id != project.id) return false;
        if (maxStdCnt != project.maxStdCnt) return false;
        if (curStdCnt != project.curStdCnt) return false;
        if (className != null ? !className.equals(project.className) : project.className != null) return false;
        if (addStatus != null ? !addStatus.equals(project.addStatus) : project.addStatus != null) return false;
        if (modifyStatus != null ? !modifyStatus.equals(project.modifyStatus) : project.modifyStatus != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (className != null ? className.hashCode() : 0);
        result = 31 * result + maxStdCnt;
        result = 31 * result + curStdCnt;
        result = 31 * result + (addStatus != null ? addStatus.hashCode() : 0);
        result = 31 * result + (modifyStatus != null ? modifyStatus.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id", nullable = false)
    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }



}
