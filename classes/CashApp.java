package classes;

public abstract class CashApp {
    protected String mobileNo;

    CashApp(){
        mobileNo = null;
    }

    CashApp(String mobileNo){
        this.mobileNo = mobileNo;
    }
    abstract void transferIn(double amount);

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
