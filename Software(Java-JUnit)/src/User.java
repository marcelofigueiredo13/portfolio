import exceptions.InvalidUserAgeException;
import exceptions.InvalidUserNameException;
import exceptions.InvalidUserPasswordException;

public class User {
    private Double latitude;
    private Double longitude;
    private String name;
    private String password;
    private Integer age;
    private Boolean active = true;

    public User(Double latitude, Double longitude, String name, String password, Integer age) throws InvalidUserNameException, InvalidUserAgeException, InvalidUserPasswordException {
        this.latitude = latitude;
        this.longitude = longitude;

        if(name == null || name.equals("") || !name.matches("[a-zA-Z0-9]*"))
            throw new InvalidUserNameException();
        else
            this.name = name;

        if(password == null || password.equals(""))
            throw new InvalidUserPasswordException();
        else
            this.password = password;

        if(age < 18 || age > 99)
            throw new InvalidUserAgeException();
        else
            this.age = age;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
