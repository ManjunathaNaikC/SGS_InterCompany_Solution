package com.sgs.ics.view.bean;

import com.sgs.ics.model.bc.am.SGSAppModuleImpl;
import com.sgs.ics.ui.utils.ADFUtils;
import com.sgs.ics.view.AllocationRun;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
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

import oracle.adf.share.logging.ADFLogger;


public class AuthenticationBean {
    private static final ADFLogger LOG = ADFLogger.createADFLogger(AuthenticationBean.class);
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


    public BindingContainer getContainer() {
        return (BindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public void setSessionScopeValue(String name, String value) {
        ADFContext adfCtx = ADFContext.getCurrent();
        Map sessionScope = adfCtx.getSessionScope();
        sessionScope.put(name, value);
    }

    public void setSessionScopePageList(ArrayList<String> pageList) {
        ADFContext adfCtx = ADFContext.getCurrent();
        ArrayList<String> appScope = (ArrayList<String>) adfCtx.getSessionScope();
        if (null != pageList && !(pageList.isEmpty())) {
            for (int i = 0; i < pageList.size(); i++) {
                appScope.add(pageList.get(i));
            }
        }
    }

    public String loginValidation() {
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExternalContext ectx = fctx.getExternalContext();

        BindingContainer bc = this.getContainer();
        OperationBinding ob = bc.getOperationBinding("loginValidationMethod");
        Map m = ob.getParamsMap();

        m.put("user", _username);
        m.put("pass", _password);
        ob.execute();

        setSessionScopeValue("_username", _username);
        String useremail = getUserEmailId(_username);
        setSessionScopeValue("USER_EMAIL", useremail);
        ArrayList<String> pageList = pageList(_username);
        ADFContext.getCurrent()
                  .getSessionScope()
                  .put("pageList", pageList);
        filterTenants(_username);
        try {
            if (null != _username && null != _password) {

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

            } else {

                showError("Invalid credentials", "An incorrect username or password was specified.", null);
            }


        } catch (IOException ie) {
            showError("IOException", "An error occurred during redirecting. Please consult logs for more information.",
                      ie);
        }
        return null;
    }

    public String getUserEmailId(String user) {

        String userEmail = "none@gmail.com";
        String queryString = "select EMAIL_ID FROM USER_AUTHENTICATION WHERE USER_ID ='" + user + "'";
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            SGSAppModuleImpl am = new SGSAppModuleImpl();
            conn = am.getDBConnection();
            String sqlIdentifier = queryString;
            pst = conn.prepareStatement(sqlIdentifier);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                userEmail = (String) rs.getString(1);
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


    public void filterTenants(String user) {

        String buList = null;
        String natureOfExpenseList = null;
        String inputProvider = null;
        String queryString =
            "SELECT BU_ID,NATURE_OF_EXPENSE,INPUT_PROVIDER FROM USER_DATA_TENANT WHERE USER_ID ='" + user + "'";
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            SGSAppModuleImpl am = new SGSAppModuleImpl();
            conn = am.getDBConnection();
            String sqlIdentifier = queryString;
            pst = conn.prepareStatement(sqlIdentifier);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                buList = (String) rs.getString(1);
                natureOfExpenseList = (String) rs.getString(2);
                inputProvider = (String) rs.getString(3);
            }

            if (null != buList) {
                ADFContext.getCurrent()
                          .getSessionScope()
                          .put("buList", buList);
            } else {
                ADFContext.getCurrent()
                          .getSessionScope()
                          .put("buList", "none");
            }

            if (null != natureOfExpenseList) {
                ADFContext.getCurrent()
                          .getSessionScope()
                          .put("natureOfExpenseList", natureOfExpenseList);
            } else {
                ADFContext.getCurrent()
                          .getSessionScope()
                          .put("natureOfExpenseList", "none");
            }

            if (null != inputProvider) {
                ADFContext.getCurrent()
                          .getSessionScope()
                          .put("inputProvider", inputProvider);
            } else {
                ADFContext.getCurrent()
                          .getSessionScope()
                          .put("inputProvider", "none");
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


    }

    private ArrayList<String> pageList(String userId) {
        ArrayList<String> pageList = new ArrayList<>();
        String queryString =
            "SELECT PAGE_ID from ROLE_PAGE_MAPPING WHERE ROLE_ID IN( SELECT ROLE_ID FROM [USER_ROLE_MAPPING] WHERE USER_ID='" +
            userId + "')";
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            SGSAppModuleImpl am = new SGSAppModuleImpl();
            conn = am.getDBConnection();
            String sqlIdentifier = queryString;
            pst = conn.prepareStatement(sqlIdentifier);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                pageList.add((String) rs.getString(1));
            }
            if (null == pageList) {
                pageList.add("NO_PAGES");
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


        return pageList;
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
            ADFContext.getCurrent()
                      .getSessionScope()
                      .put("logoutFlag", true);


            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            response = (HttpServletResponse) externalContext.getResponse();
            req = (HttpServletRequest) externalContext.getRequest();
            externalContext.invalidateSession();

        } catch (Exception ex) {
            ex.getMessage();
        } finally {
            LOG.info("Logout Done");
            response.sendRedirect((new StringBuilder()).append(req.getContextPath())
                                                       .append("/faces/LoginPage.jspx")
                                                       .toString());
            FacesContext.getCurrentInstance().responseComplete();
        }
        return null;
    }
}
