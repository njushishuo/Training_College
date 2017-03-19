package training_college.entity;

import training_college.util.enumeration.CardStatus;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by ss14 on 2017/2/28.
 */
@Entity
public class Card {
    private int id;
    private int bankCardId;
    private int balance;
    private int score;
    private int level;
    private CardStatus status;
    private Date LastActivatedAt;

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
    @Column(name = "bank_card_id")
    public int getBankCardId() {
        return bankCardId;
    }

    public void setBankCardId(int bankCardId) {
        this.bankCardId = bankCardId;
    }


    @Basic
    @Column(name = "balance")
    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "score")
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Basic
    @Column(name = "level")
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    public CardStatus getStatus() {
        return status;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }

    @Basic
    @Column(name = "last_activated_at")
    public Date getLastActivatedAt() {
        return LastActivatedAt;
    }

    public void setLastActivatedAt(Date lastActivatedAt) {
        LastActivatedAt = lastActivatedAt;
    }
}
