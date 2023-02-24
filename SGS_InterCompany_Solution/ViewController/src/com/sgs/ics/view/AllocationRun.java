package com.sgs.ics.view;

import com.sgs.ics.model.bc.am.SGSAppModuleImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.GregorianCalendar;

import javax.faces.event.ActionEvent;

import oracle.adf.share.logging.ADFLogger;

public class AllocationRun {
    public AllocationRun() {
    }
    private static final ADFLogger LOG = ADFLogger.createADFLogger(AllocationRun.class);

    public void allocationRun(ActionEvent actionEvent) {
        // Add event code here...
        System.out.println("inside allocationRun**********************");
        Connection conn = null;
        PreparedStatement pst = null;
        java.util.Calendar cal = new GregorianCalendar();
        try {
            
            conn = getDBConnection();
            String SPsql = "EXEC UPKG_INITIAL_PRC ?,?"; // for stored proc taking 2 parameters
            //Connection con = SmartPoolFactory.getConnection();   // java.sql.Connection
            PreparedStatement ps = conn.prepareStatement(SPsql);
            ps.setEscapeProcessing(true);
            //            ps.setQueryTimeout(10);
            ps.setInt(1, 3);
            ps.setDate(2,new java.sql.Date(cal.getTime().getTime()), cal);
            ps.execute();
        } catch (SQLException sqle) {
            // TODO: Add catch code
            sqle.printStackTrace();
        } finally {

        }
    }
    
    public Connection getDBConnection() {
            Connection conn = null;
        try {
    //           String connectionUrl = "jdbc:sqlserver://localhost;instanceName=SQLEXPRESS;databasename=SGS_NEW;integratedSecurity=true;";
    //            conn = DriverManager.getConnection(connectionUrl);
            conn = DriverManager.getConnection("jdbc:sqlserver://ASBCOLPS02:1433;databaseName=DEVINTER","EYUser","Ey@123");

        } catch (SQLException sqle) {
            // TODO: Add catch code
            sqle.printStackTrace();
        } finally {

        }
               
         return conn;   
        }
}
