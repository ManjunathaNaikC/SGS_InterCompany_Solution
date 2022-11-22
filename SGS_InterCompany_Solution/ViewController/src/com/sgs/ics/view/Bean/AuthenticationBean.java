package com.sgs.ics.view.Bean;

import com.sgs.ics.ui.utils.ADFUtils;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import oracle.jbo.ApplicationModule;
import oracle.jbo.ViewObject;

public class AuthenticationBean {
    private String _username;
    private String _password;
    private ApplicationModule sgsAppModule = ADFUtils.getApplicationModuleForDataControl("SGSAppModuleDataControl");

    public AuthenticationBean() {
    }

    public String onLogout() {
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExternalContext ectx = fctx.getExternalContext();
        String url =
            ectx.getRequestContextPath() + "/adfAuthentication?logout=true&end_url=/faces/jsf/newWelcomePage.jsf";
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

    public String doLogin() {
        FacesContext ctx = FacesContext.getCurrentInstance();
//        List<Integer> buIdsList = new ArrayList<Integer>();
        if (_username == null || _password == null) {
            showError("Invalid credentials", "An incorrect username or password was specified.", null);
        } else {
            ExternalContext ectx = ctx.getExternalContext();
            HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
            try {
                request.login(_username, _password); // Servlet 3.0 login

                HttpSession session = request.getSession();
                session.setAttribute("success_url", "/faces/jsf/welcomePage.jsf");
                ViewObject sgsUsersByBuVO = sgsAppModule.findViewObject("SgsUserAuthenticationVO");
                sgsUsersByBuVO.setWhereClause("Username = '" + _username + "'");
                sgsUsersByBuVO.executeQuery();
                System.out.println(sgsUsersByBuVO.getEstimatedRowCount());
                if (sgsUsersByBuVO.getEstimatedRowCount() > 0) {
//                    ArrayList<Integer> buIds = new ArrayList<>();
//                    for (int i = 0; i < sgsUsersByBuVO.getEstimatedRowCount(); i++) {
//                        Row row = sgsUsersByBuVO.next();
//                        buIds.add((Integer)row.getAttribute("BuId"));
//                    }
//                    System.out.println(buIds);
//                    System.out.println(buIds);
//                    ADFUtils.setSessionAttribute("usersBuId", buIds);
                    ADFUtils.setSessionAttribute("usersBuId", sgsUsersByBuVO.first().getAttribute("USERID"));
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
}