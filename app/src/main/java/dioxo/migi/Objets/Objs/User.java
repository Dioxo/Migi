package dioxo.migi.Objets.Objs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import dioxo.migi.Objets.Objs.Seguridad.Encriptar;
import dioxo.migi.Objets.Objs.Seguridad.Encriptar_Interface;

public class User {
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("nickName")
    @Expose
    private String nickName;
    private String password;

    public User(String email, String password, String nickName) {
        this.email = email;
        this.nickName = nickName;

        Encriptar_Interface encriptar = new Encriptar();
        try {
            this.password = encriptar.generateStorngPasswordHash(password);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }


    }
    public User(String email, String nickName) {
        super();
        this.email = email;
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
