package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.DataTransferObject;

public class Customer implements DataTransferObject {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String zipcode;

    public long getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String setLastName(String lastName) {
        return this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public String setEmail(String email) {
        return this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public String setPhone(String phone) {
        return this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public String setAddress(String address) {
        return this.address = address;
    }
    public String getCity() {
        return city;
    }
    public String setCity(String city) {
        return this.city = city;
    }
    public String getState() {
        return state;
    }
    public String setState(String state) {
        return this.state = state;
    }
    public String getZipcode() {
        return zipcode;
    }
    public String setZipcode(String zipcode) {
        return this.zipcode = zipcode;
    }

    @Override
    public String toString(){
        return "Customer{" + "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
