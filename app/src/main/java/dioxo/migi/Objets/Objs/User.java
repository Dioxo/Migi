package dioxo.migi.Objets.Objs;

public class User {
    private String email;
    private String password;
    private String nickName;

    public User(String email, String password, String nickName) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNickName() {
        return nickName;
    }

}
