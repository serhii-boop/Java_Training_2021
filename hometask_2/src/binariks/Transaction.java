package binariks;

public class Transaction {

    private Integer id;
    public String userName;
    private Integer amount;

    public Transaction(Integer id, String userName, Integer amount) {
        this.id = id;
        this.userName = userName;
        this.amount = amount;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
