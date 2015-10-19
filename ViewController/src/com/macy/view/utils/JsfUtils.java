package com.macy.view.utils;

import java.util.Locale;
import java.util.Map;

import java.util.ResourceBundle;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;

import javax.faces.application.NavigationHandler;

import javax.faces.component.UIComponent;

import oracle.adf.model.OperationBinding;

import javax.faces.context.FacesContext;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.jbo.ApplicationModule;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;

import org.apache.myfaces.trinidad.util.Service;

public class JsfUtils
{
  public JsfUtils()
  {
    super();
  }

  public static FacesContext getFacesContext()
  {
    FacesContext facesContext;
    facesContext = FacesContext.getCurrentInstance();
    return facesContext;
  }

  public static ELContext getELContext()
  {
    FacesContext facesContext;
    ELContext elContext;
    facesContext = getFacesContext();
    elContext = facesContext.getELContext();
    return elContext;
  }

  public static ExpressionFactory getExpressionFactory()
  {
    FacesContext fc;
    ELContext elc;
    ExpressionFactory ef;
    fc = getFacesContext();
    elc = getELContext();
    ef = fc.getApplication().getExpressionFactory();
    return ef;
  }

  public static void setEL(String expr, String value)
  {
    Object valToSet = value;
    if (isELExpr(value))
    {
      valToSet = getEL(value);
    }
    setEL(expr, valToSet);
  }

  public static void setEL(String expr, Object value)
  {
    ELContext elc;
    ExpressionFactory ef;
    elc = getELContext();
    ef = getExpressionFactory();
    ValueExpression ve = ef.createValueExpression(elc, expr, Object.class);
    ve.setValue(elc, value);
  }


  public static Object getEL(String expr)
  {
    FacesContext fc = FacesContext.getCurrentInstance();
    return fc.getApplication().evaluateExpressionGet(fc, expr,
                                                     Object.class);
  }


  private static boolean isELExpr(Object o)
  {
    if (o instanceof String)
    {
      String str = (String) o;
      str.trim();
      return str.startsWith("#{") && str.endsWith("}");
    }
    return false;
  }

  public static Object invokeMethod(String expr, Class[] paramTypes,
                                    Object[] params)
  {
    ELContext elc = getELContext();
    ExpressionFactory ef = getExpressionFactory();
    MethodExpression me =
      ef.createMethodExpression(elc, expr, Object.class, paramTypes);
    return me.invoke(elc, params);
  }

  public static Object invokeMethod(String expr, Class paramType,
                                    Object param)
  {
    return invokeMethod(expr, new Class[]
        { paramType }, new Object[]
        { param });
  }


  /**
   * Execute an AM method exposed as an operation and  bound to a page
   * definition that has two or more parameters.
   *
   * @param expr - e.g. #{bindings.amMehtod}
   * @return value if any.
   */

  public static Object invokeOperationBinding(String expr,
                                              String[] paramNames,
                                              Object[] params)
  {
    OperationBinding ob = (OperationBinding) getEL(expr);
    for (int i = 0; i < paramNames.length; i++)
    {
      ob.getParamsMap().put(paramNames[i], params[i]);
    }

    Object result = ob.execute();
    return result;
  }

  /**
   * Execute an AM method exposed as an operation and  bound to a page
   * definition that has a single parameter.
   *
   * @param expr
   * @param paramName
   * @param param
   * @return
   */
  public static Object invokeOperationBinding(String expr,
                                              String paramName,
                                              Object param)
  {

    Object result;

    if (!paramName.equals(""))
    {
      result = invokeOperationBinding(expr, new String[]
            { paramName }, new Object[]
            { param });
    }
    else
    {
      result = invokeOperationBinding(expr, new String[]
            { }, new Object[]
            { });
    }

    return result;
  }

  /**
   *
   * @return
   */
  public static Map<String, Object> getPageFlowScopeMap()
  {
    AdfFacesContext context = AdfFacesContext.getCurrentInstance();
    return context.getPageFlowScope();
  }


  public static Map<String, Object> getViewScopeMap()
  {
    AdfFacesContext context = AdfFacesContext.getCurrentInstance();
    return context.getViewScope();
  }

  /**
   * Get application module for an application module data control by name.
   * @param name application module data control name
   * @return ApplicationModule
   */
  public static ApplicationModule getApplicationModuleForDataControl(String name)
  {
    return (ApplicationModule) getEL("#{data." + name + ".dataProvider}");
  }

  public static Object getManagedBean(String bean_name)
  {
   return getELResolver().getValue(getELContext(), null,bean_name);
  }
  public static ELResolver getELResolver()
    {
      return getELContext().getELResolver();
    }
  public static String getStringFromResourceBundle(String resourceId,
                                                   String languageCode)
  {
    Locale locale = new Locale(languageCode);
    ResourceBundle bundle =
      ResourceBundle.getBundle("com/lifetech/portal/ui/LifeTechPortalUIBundle",
                               locale);
    String value = bundle.getString(resourceId);
    return value;
  }

  public static void showPopup(RichPopup popup, boolean visible)
  {
    try
    {
      if (popup != null)
      {
        if (visible)
        {
          RichPopup.PopupHints ph = new RichPopup.PopupHints();
          popup.show(ph);
        }
        else
        {
          popup.hide();
        }
      }
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }

  public static void showPopupAboveTheComp(RichPopup popup,UIComponent ui, boolean visible)
  {
    try
    {
      if (popup != null)
      {
        if (visible)
        {
          RichPopup.PopupHints hints = new RichPopup.PopupHints();
          if(ui!=null)
          {
          hints.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN_ID,
                    ui).add(RichPopup.PopupHints.HintTypes.HINT_LAUNCH_ID,
                               ui).add(RichPopup.PopupHints.HintTypes.HINT_ALIGN,
                                          RichPopup.PopupHints.AlignTypes.ALIGN_OVERLAP);
          }
          popup.show(hints);
        }
        else
        {
          popup.hide();
        }
      }
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }

  public static void addMessageWithCode(FacesMessage.Severity type,
                                        String code)
  {
    FacesContext fctx = FacesContext.getCurrentInstance();
    FacesMessage fm = new FacesMessage(code);
    fm.setSeverity(type);
    fctx.addMessage(fctx.getViewRoot().getId(), fm);
  }

  public static void addMessageWithCodeAndId(FacesMessage.Severity type,
                                             String code, String Id)
  {
    FacesContext fctx = FacesContext.getCurrentInstance();
    FacesMessage fm = new FacesMessage(code);
    fm.setSeverity(type);
    fctx.addMessage(Id, fm);
  }


  /**
   * This is a helper method to navigate to specified control flow case.
   * @param controlFlowCase
   */
  public static void navigateToControlFlowCase(String controlFlowCase)
  {
    FacesContext fctx = FacesContext.getCurrentInstance();
    Application application = fctx.getApplication();
    NavigationHandler navHandler = application.getNavigationHandler();
    navHandler.handleNavigation(fctx, null, controlFlowCase);
  }

  public static void restartHeaderFooter()
  {
    FacesContext ctx = FacesContext.getCurrentInstance();
    ExtendedRenderKitService erks =
      Service.getService(ctx.getRenderKit(), ExtendedRenderKitService.class);
    erks.addScript(ctx, "restartHeaderFooter()");
  }

  /* Returns the current view id */

  public static String getCurrentViewId()
  {
    /* 2 statements here so it can be debugged more easily */
    String currentViewId =
      FacesContext.getCurrentInstance().getViewRoot().getViewId();
    return currentViewId;
  }

}
