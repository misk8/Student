//mayyas al-mohaimeed
package com.mycompany.studentdatamanager;

import java.io.Serializable;

/**
  ContactInfo - COMPOSITION inside Student Student cannot exist without
  ContactInfo
 */
public class ContactInfo implements Serializable {

    private static final long serialVersionUID = 3L;

    private String email;
    private String phone;

    public ContactInfo(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Email: " + email + " | Phone: " + phone;
    }
}
