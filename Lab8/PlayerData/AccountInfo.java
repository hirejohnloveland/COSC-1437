package Lab8.PlayerData;

public class AccountInfo {

    public AccountInfo(AccountInfo acc) {
        this.password = acc.password;
        this.ID = acc.ID;
        this.billingAddress = acc.billingAddress;
        this.phoneNumber = acc.phoneNumber;
    }

    public AccountInfo(String password, int iD, String billingAddress, String phoneNumber) {
        this.password = password;
        ID = iD;
        this.billingAddress = billingAddress;
        this.phoneNumber = phoneNumber;
    }

    String password;
    int ID;
    String billingAddress;
    String phoneNumber;

}
