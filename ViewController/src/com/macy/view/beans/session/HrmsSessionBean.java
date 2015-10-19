package com.macy.view.beans.session;

import java.io.Serializable;

public class HrmsSessionBean implements Serializable
{
  private String firstName =null;
  private enum Orientation {PORTRAIT, LANDSCAPE};
  private Orientation _orientation = Orientation.LANDSCAPE;
  public HrmsSessionBean()
  { 
    super();
  }
  public void setOrientation(HrmsSessionBean.Orientation _orientation)
  {
    this._orientation = _orientation;
  }

  public HrmsSessionBean.Orientation getOrientation()
  {
    return _orientation;
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
  private String lastName =null;
 
}
