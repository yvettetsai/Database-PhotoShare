package edu.bu.cs.cs460.photoshare;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * A bean that handles new user data
 *
 * Original author G. Zervas <cs460tf@bu.edu>
 * Modified by Yvette Tsai (ytsai@bu.edu)
 */
public class NewUserBean {
  private String email = "";
  private String password1 = "";
  private String password2 = "";
  private String firstName = "";
  private String lastName = "";
  private String dateOfBirth = "";
  private int gender = -1;
  private String education = "";
  private String homeCity = "";
  private String homeState = "";
  private String homeCountry = "";
  private String currentCity = "";
  private String currentState = "";
  private String currentCountry = "";
  
  public String saySomething() {
    System.out.println("Hello!");
    return "Test";
  }
  
  public String getEmail() {
    return email;
  }

  public String getPassword1() {
    return password1;
  }

  public String getPassword2() {
    return password2;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword1(String password1) {
    this.password1 = password1;
  }

  public void setPassword2(String password2) {
    this.password2 = password2;
  }
  
  public String getFirstName()
  {
    return firstName;
  }
  
  public String getLastName()
  {
    return lastName;
  }
  
  public String getDateOfBirth()
  {
    return dateOfBirth;
  }
  
  public int getGender()
  {
    return gender;
  }
  
  public String getEducation()
  {
    return education;
  }
  
  public String getHCity()
  {
    return homeCity;
  }
  
  public String getHState()
  {
    return homeState;
  }
  
  public String getHCountry()
  {
    return homeCountry;
  }
  
  public String getCCity()
  {
    return currentCity;
  }
  
  public String getCState()
  {
    return currentState;
  }
  
  public String getCCountry()
  {
    return currentCountry;
  }
  
  public void setFirstName(String fn)
  {
    firstName = fn;
  }
  
  public void setLastName(String ln)
  {
    lastName = ln;
  }
  
  public void setDateOfBirth(String dob)
  {
    dateOfBirth = dob;
  }
  
  public void setGender(String g)
  {
    if(g.equalsIgnoreCase("f"))
      gender = 1;
    gender = 0;
  }
  
  public void setEdu(String edu)
  {
    education = edu;
  }
  
  public void setHCity(String hc)
  {
    homeCity = hc;
  }
  
  public void setHState(String hs)
  {
    homeState = hs;
  }
  
  public void setHCountry(String hc)
  {
    homeCountry = hc;
  }
  
  public void setCCity(String cc)
  {
    currentCity = cc;
  }
  
  public void setCState(String cs)
  {
    currentState = cs;
  }
  
  public void setCCountry(String cc)
  {
    currentCountry = cc;
  }
}
