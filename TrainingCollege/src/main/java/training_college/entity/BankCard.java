package training_college.entity;

import javax.persistence.*;

/**
 * Created by ss14 on 2017/3/19.
 */
@Entity
@Table(name = "bank_card", schema = "training_college", catalog = "")
public class BankCard {
    private int id;
    private String cardNum;
    private int balance;

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
    @Column(name = "card_num")
    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    @Basic
    @Column(name = "balance")
    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankCard bankCard = (BankCard) o;

        if (id != bankCard.id) return false;
        if (balance != bankCard.balance) return false;
        if (cardNum != null ? !cardNum.equals(bankCard.cardNum) : bankCard.cardNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (cardNum != null ? cardNum.hashCode() : 0);
        result = 31 * result + balance;
        return result;
    }
}
