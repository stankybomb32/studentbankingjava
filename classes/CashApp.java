package classes;

public abstract class CashApp {
    protected String name;
    protected String mobileNo;

    CashApp(String name, String mobileNo){
        this.name = name;
        this.mobileNo = mobileNo;
    }
    abstract void transferIn(double amount);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
