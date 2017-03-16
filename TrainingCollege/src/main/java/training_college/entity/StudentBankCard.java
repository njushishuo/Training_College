package training_college.entity;

import javax.persistence.*;

/**
 * Created by ss14 on 2017/3/16.
 */
@Entity
@Table(name = "student_bank_card", schema = "training_college", catalog = "")
@IdClass(StudentBankCardPK.class)
public class StudentBankCard {
    private int bankCardId;
    private int studentId;

    @Id
    @Column(name = "bank_card_id")
    public int getBankCardId() {
        return bankCardId;
    }

    public void setBankCardId(int bankCardId) {
        this.bankCardId = bankCardId;
    }

    @Id
    @Column(name = "student_id")
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

        StudentBankCard that = (StudentBankCard) o;

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
