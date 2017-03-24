package kr.ac.jejunu;

/**
 * Created by super on 2017-03-15.
 */
public class User {
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    long id;
    String name;
    String password;

}
