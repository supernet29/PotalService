package kr.ac.jejunu;

/**
 * Created by super on 2017-03-15.
 */
public class User {
    public User(){}
    public User(long id, String name, String password){
        this.id = id;
        this.name = name;
        this.password = password;
    }

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

    private long id;
    private String name;
    private String password;

}
