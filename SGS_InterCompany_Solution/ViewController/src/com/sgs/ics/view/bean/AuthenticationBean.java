package com.sgs.ics.view.bean;

import com.sgs.ics.model.bc.am.SGSAppModuleImpl;
import com.sgs.ics.ui.utils.ADFUtils;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.security.auth.Subject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import oracle.adf.model.BindingContext;

import oracle.adf.share.ADFContext;

import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import javax.servlet.http.HttpServletResponse;

import oracle.mds.internal.security.SecurityUtils;


public class AuthenticationBean {
    private String _username;
    private String _password;
    public AuthenticationBean() {

    }

    public String onLogout() {
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExternalContext ectx = fctx.getExternalContext();
        String url = ectx.getRequestContextPath() + "/adfAuthentication?logout=true&end_url=/faces/jsf/MainPage.jsf";
        try {
            ectx.redirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fctx.responseComplete();
        return null;
    }

    public void setUsername(String _username) {
        this._username = _username;
    }

    public String getUsername() {
        return _username;
    }

    public void setPassword(String _password) {
        this._password = _password;
    }

    public String getPassword() {
        return _password;
    }

    /*  public String doLogin() {
        FacesContext ctx = FacesContext.getCurrentInstance();

        if (_username == null || _password == null) {
            showError("Invalid credentials", "An incorrect username or password was specified.", null);
        } else {
            ExternalContext ectx = ctx.getExternalContext();
            HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
            try {
                request.login(_username, _password); // Servlet 3.0 login

                HttpSession session = request.getSession();
                session.setAttribute("success_url", "/faces/jsf/welcomePage.jsf");
                ViewObject sgsUsersByBuVO = sgsAppModule.findViewObject("SgsUsersByBuVO");
                sgsUsersByBuVO.setWhereClause("Username = '" + _username + "'");
                sgsUsersByBuVO.executeQuery();

                if (sgsUsersByBuVO.getEstimatedRowCount() > 0) {

                    ADFUtils.setSessionAttribute("usersBuId", sgsUsersByBuVO.first().getAttribute("BuId"));
                }
                _username = null;
                _password = null;
                redirect(ectx.getRequestContextPath() + "/adfAuthentication");
            } catch (ServletException fle) {
                showError("ServletException", "Login failed. Please verify the username and password and try again.",
                          null);
            }
        }
        return null;
    }*/

    //Last used login
    //  public String doLogin() {
    //
    //          FacesContext fctx = FacesContext.getCurrentInstance();
    //          ExternalContext ectx = fctx.getExternalContext();
    //
    //          try {
    //             // System.out.println("path:"+ectx.getRequestContextPath());
    //              if ((_username!=null && _username!="") && map.get(_username)!=null)
    //              ectx.redirect(ectx.getRequestContextPath()+"/faces/pages/MainPage.jsf");
    //              else
    //              showError("Invalid credentials", "An incorrect username or password was specified.", null);
    //
    //          } catch (IOException ie) {
    //              showError("IOException", "An error occurred during redirecting. Please consult logs for more information.",
    //                        ie);
    //          }
    //
    //          return null;
    //      }


    public BindingContainer getContainer() {

        return (BindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
    }
    
    public void setSessionScopeValue(String name, String value) {
        ADFContext adfCtx = ADFContext.getCurrent();
        Map sessionScope = adfCtx.getApplicationScope();
        sessionScope.put(name, value);
    }

    public String loginValidation() {
        // Add event code here...
        //        String status = "";
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExternalContext ectx = fctx.getExternalContext();

        BindingContainer bc = this.getContainer();
        OperationBinding ob = bc.getOperationBinding("loginValidationMethod");
        Map m = ob.getParamsMap();
        m.put("user", _username);
        m.put("pass", _password);
        ob.execute();
        setSessionScopeValue("_username",_username);      
        String useremail = getUserEmailId(_username);
        setSessionScopeValue("USER_EMAIL",useremail);
        
        try {
            if (ob.getResult() != null) {
                String userId = ob.getResult().toString();
                if (userId != null || userId.length() != 0) {
                    //            status = "success";
                    ectx.redirect(ectx.getRequestContextPath() + "/faces/pages/MainPage.jsf");

                } else {
                    showError("Invalid credentials", "An incorrect username or password was specified.", null);
                    //            status = "";
                }
            } else {
                showError("Invalid credentials", "An incorrect username or password was specified.", null);
            }

        } catch (IOException ie) {
            showError("IOException", "An error occurred during redirecting. Please consult logs for more information.",
                      ie);
        }
        return null;
    }
    
    public String getUserEmailId(String user){
        
        String userEmail = "none@gmail.com";
        String queryString =
            "select EMAIL_ID FROM USER_AUTHENTICATION WHERE USER_ID ='" + user + "'";
        Connection conn = null;
        PreparedStatement pst = null;
        System.out.println("Query :: " + queryString);
        try {
            SGSAppModuleImpl am = new SGSAppModuleImpl();
            conn = am.getDBConnection();
            String sqlIdentifier = queryString;
            pst = conn.prepareStatement(sqlIdentifier);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                userEmail= (String) rs.getString(1);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            try {
                conn.close();
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return userEmail;
    }


    private void redirect(String forwardUrl) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext ectx = ctx.getExternalContext();
        try {
            ectx.redirect(forwardUrl);
        } catch (IOException ie) {
            showError("IOException", "An error occurred during redirecting. Please consult logs for more information.",
                      ie);
        }
    }

    private void showError(String errType, String message, Exception e) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, errType, message);
        FacesContext.getCurrentInstance().addMessage("d2:it35", msg);
        if (e != null) {
            e.printStackTrace();
        }
    }

    public String logOut() throws ServletException, IOException {
        HttpServletResponse response = null;
                HttpServletRequest req = null;
                try {
                    ADFContext.getCurrent().getSessionScope().put("logoutFlag",true);                
                    

                    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                    response = (HttpServletResponse)externalContext.getResponse();
                    req = (HttpServletRequest)externalContext.getRequest();
                    externalContext.invalidateSession();
                    
                } catch (Exception ex) {            
                    ex.getMessage();
                } finally {
                    System.out.println("Logout Done");
                    response.sendRedirect((new StringBuilder()).append(req.getContextPath()).append("/faces/LoginPage.jspx").toString());
                    FacesContext.getCurrentInstance().responseComplete();
                }
        return null;
    }
}
