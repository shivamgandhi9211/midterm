package com.georgiancollege.test1;

public class Employee {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String province;
    private String phone; //As the phone numbers are in "555-555-5555" so the String Datatype is  suitable choice.

    public Employee(int employeeId, String firstName, String lastName, String address, String city, String province, String phone) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.province = province;
        this.phone = phone;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        if (employeeId <= 200465000) {
            throw new IllegalArgumentException("Employee ID should be greater than 200465000.");
        }
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().length() <= 1) {
            throw new IllegalArgumentException("First name must be more than 1 character.");
        }
        this.firstName = firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().length() <= 1) {
            throw new IllegalArgumentException("Last name must be more than 1 character.");
        }
        this.lastName = lastName.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null || address.trim().length() <= 5) {
            throw new IllegalArgumentException("Address must be more than 5 characters.");
        }
        this.address = address.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (city == null || city.trim().length() <= 3) {
            throw new IllegalArgumentException("City must be more than 3 characters.");
        }
        this.city = city.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        String[] validProvinces = {"AB", "BC", "MB", "NB", "NL", "NS", "NT", "NU", "ON", "PE", "QC", "SK", "YT"};
        for (String validProvince : validProvinces) {
            if (province != null && province.equals(validProvince)) {
                this.province = province;
                return;
            }
        }
        throw new IllegalArgumentException("Province should be one of the following: AB, BC, MB, NB, NL, NS, NT, NU, ON, PE, QC, SK, YT.");
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone == null || !phone.matches("^\\d{3}-\\d{3}-\\d{4}$")) {
            throw new IllegalArgumentException("Phone should match the North American dialing plan (e.g., XXX-XXX-XXXX).");
        }
        this.phone = phone;
    }

}
