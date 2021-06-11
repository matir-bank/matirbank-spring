package xyz.matirbank.spring.models.requests;

public class SendMoneyRequest {
    
    String user_hash;
    Double amount;
    String remarks;
    
    public SendMoneyRequest(){}

    public String getUser_hash() {
        return user_hash;
    }

    public void setUser_hash(String user_hash) {
        this.user_hash = user_hash;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
}
