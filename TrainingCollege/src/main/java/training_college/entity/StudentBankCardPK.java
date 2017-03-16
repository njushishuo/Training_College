package training_college.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by ss14 on 2017/3/16.
 */
public class StudentBankCardPK implements Serializable {
    private int bankCardId;
    private int studentId;

    @Column(name = "bank_card_id")
    @Id
    public int getBankCardId() {
        return bankCardId;
    }

    public void setBankCardId(int bankCardId) {
        this.bankCardId = bankCardId;
    }

    @Column(name = "student_id")
    @Id
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentBankCardPK that = (StudentBankCardPK) o;

        if (bankCardId != that.bankCardId) return false;
        if (studentId != that.studentId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bankCardId;
        result = 31 * result + studentId;
        return result;
    }
}
