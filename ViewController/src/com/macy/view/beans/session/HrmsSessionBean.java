package com.macy.view.beans.session;

import java.io.Serializable;

public class HrmsSessionBean implements Serializable
{
  private String firstName =null;
  private String lastName =null;

  public HrmsSessionBean()
  { 
    super();
  }
  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  public String getLastName()
  {
    return lastName;
  }
 
}
