package com.macy.view.beans.managed;

import com.macy.view.beans.session.HrmsSessionBean;
import com.macy.view.utils.JsfUtils;

import java.io.Serializable;

import oracle.adf.controller.TaskFlowId;

public class MstDynamicRegionBean
  implements Serializable
{
  private String taskFlowId = "/WEB-INF/com/macy/view/tasksflows/dashBoardTf.xml#dashBoardTf";

  public MstDynamicRegionBean()
  {
    //HrmsSessionBean sessionBean=(HrmsSessionBean)JsfUtils.getManagedBean("MstDynamicRegionBean");
    //sessionBean.setFirstName("Mukul");
    //sessionBean.setLastName("Gupta");
  }

  public TaskFlowId getDynamicTaskFlowId()
  {
    return TaskFlowId.parse(taskFlowId);
  }

  public void setDynamicTaskFlowId(String taskFlowId)
  {
    this.taskFlowId = taskFlowId;
  }
}
