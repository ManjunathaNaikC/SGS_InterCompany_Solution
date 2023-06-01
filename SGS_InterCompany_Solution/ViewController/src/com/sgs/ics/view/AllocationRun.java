package com.sgs.ics.view;

import com.sgs.ics.model.bc.am.SGSAppModuleImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;

import java.math.BigDecimal;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.GregorianCalendar;

import javax.faces.event.ActionEvent;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;

import oracle.jbo.server.ViewObjectImpl;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class AllocationRun {
    public AllocationRun() {
    }
    private static final ADFLogger LOG = ADFLogger.createADFLogger(AllocationRun.class);
    private SGSAppModuleImpl am = new SGSAppModuleImpl();

    public void allocationRun(ActionEvent actionEvent) {
        LOG.info("Inside allocationRun**********************");
        Connection conn = null;
        PreparedStatement ps = null;
        java.util.Calendar cal = new GregorianCalendar();
        try {
            conn = am.getDBConnection();
            String SPsql = "EXEC UPKG_INITIAL_PRC ?,?"; // for stored proc taking 2 parameters
            ps = conn.prepareStatement(SPsql);
            ps.setEscapeProcessing(true);
            ps.setInt(1, 3);
            ps.setDate(2, new java.sql.Date(cal.getTime().getTime()), cal);
            ps.execute();
        } catch (SQLException sqle) {
            // TODO: Add catch code
            sqle.printStackTrace();
        } finally {
            try {
                conn.close();
                ps.close();
            } catch (SQLException e) {
            }


        }
    }
    
    

    //    public Connection getDBConnection() {
    //        Connection conn = null;
    //        try {
    //
    //            String connectionUrl =
    //                "jdbc:sqlserver://localhost;instanceName=SQLEXPRESS;databasename=DEVINTER;integratedSecurity=true;";
    //            conn = DriverManager.getConnection(connectionUrl);
    //            //conn = DriverManager.getConnection("jdbc:sqlserver://ASBCOLPS02:1433;databaseName=DEVINTER","EYUser","Ey@123");
    //
    //
    //        } catch (SQLException sqle) {
    //            // TODO: Add catch code
    //            sqle.printStackTrace();
    //        } finally {
    //
    //        }
    //
    //        return conn;
    //    }

    public BindingContainer getBindings() {

        return (BindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    private DCIteratorBinding getDCIteratorBindings(String iterName) {
        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        return bindings.findIteratorBinding(iterName);

    }

    public void onSOAPAPICALLtest(ActionEvent actionEvent) {

        DCIteratorBinding invoiceHeaderItr = getDCIteratorBindings("SgsIcInvoiceHeaderVO1Iterator");
        oracle.jbo.Row[] invoiceHeaderDatarows = invoiceHeaderItr.getAllRowsInRange();
        LOG.info("invoiceHeaderDatarows count :: " + invoiceHeaderDatarows.length);
        for (int i = 0; i < invoiceHeaderDatarows.length; i++) {
            LOG.info("InvoiceSeqNo :: " + invoiceHeaderDatarows[i].getAttribute("InvoiceSeqNo"));
            LOG.info("TransactionCategory :: " + invoiceHeaderDatarows[i].getAttribute("TransactionCategory"));
            LOG.info("SourceCurrency :: " + invoiceHeaderDatarows[i].getAttribute("SourceCurrency"));
            LOG.info("IuCustomer :: " + invoiceHeaderDatarows[i].getAttribute("IuCustomer"));
            LOG.info("InvoiceHeaderAmount :: " + invoiceHeaderDatarows[i].getAttribute("InvoiceHeaderAmount"));


            DCIteratorBinding invoiceLineItr = getDCIteratorBindings("SgsIcInvoiceLineVO2Iterator");
            ViewObjectImpl voucherView = (ViewObjectImpl) invoiceLineItr.getViewObject();
            voucherView.setWhereClause("INVOICE_SEQ_NO=" + invoiceHeaderDatarows[i].getAttribute("InvoiceSeqNo"));
            voucherView.executeQuery();
            oracle.jbo.Row[] invoiceLineDaterows = voucherView.getAllRowsInRange();
            LOG.info("invoiceLineDaterows count :: " + invoiceLineDaterows.length);
            for (int j = 0; j < invoiceLineDaterows.length; j++) {
                LOG.info("IcLineNo :: " + invoiceLineDaterows[i].getAttribute("IcLineNo"));
                LOG.info("FromOu :: " + invoiceLineDaterows[i].getAttribute("FromOu"));
                LOG.info("FromJobCode :: " + invoiceLineDaterows[i].getAttribute("FromJobCode"));
                LOG.info("FromDepartmentId :: " + invoiceLineDaterows[i].getAttribute("FromDepartmentId"));
                LOG.info("ToDepartmentId :: " + invoiceLineDaterows[i].getAttribute("ToDepartmentId"));
            }


        }


    }

    private Document parseXmlFile(String in) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(in));
            return db.parse(is);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void onSOAPAPICALL(ActionEvent actionEvent) {
        System.out.println("inside Invoice API**********************");
        Connection conn = null;
        PreparedStatement pst = null;
        java.util.Calendar cal = new GregorianCalendar();
        try {

            conn = am.getDBConnection();
            String SPsql = "EXEC USP_PushInvoiceToPeoplesoft"; // for stored proc taking 2 parameters
            //Connection con = SmartPoolFactory.getConnection();   // java.sql.Connection
            PreparedStatement ps = conn.prepareStatement(SPsql);
            ps.setEscapeProcessing(true);
            //            ps.setQueryTimeout(10);

            ps.execute();
        } catch (SQLException sqle) {
            // TODO: Add catch code
            sqle.printStackTrace();
        } finally {

        }

    }

    public void onUpdateBillingInformation(ActionEvent actionEvent) {
        System.out.println("inside Global Sync**********************");
        Connection conn = null;
        PreparedStatement pst = null;
        java.util.Calendar cal = new GregorianCalendar();
        try {

            conn = am.getDBConnection();
            String SPsql = "EXEC USP_GlobalSync"; // for stored proc taking 2 parameters
            //Connection con = SmartPoolFactory.getConnection();   // java.sql.Connection
            PreparedStatement ps = conn.prepareStatement(SPsql);
            ps.setEscapeProcessing(true);
            //            ps.setQueryTimeout(10);

            ps.execute();
        } catch (SQLException sqle) {
            // TODO: Add catch code
            sqle.printStackTrace();
        } finally {

        }

    }

    public void onDEPOSITAPI(ActionEvent actionEvent) {

        System.out.println("inside Deposit**********************");
        Connection conn = null;
        PreparedStatement pst = null;
        java.util.Calendar cal = new GregorianCalendar();
        try {

            conn = am.getDBConnection();
            String SPsql = "EXEC USP_DEPOSITCREATION"; // for stored proc taking 2 parameters
            //Connection con = SmartPoolFactory.getConnection();   // java.sql.Connection
            PreparedStatement ps = conn.prepareStatement(SPsql);
            ps.setEscapeProcessing(true);
            //            ps.setQueryTimeout(10);

            ps.execute();
        } catch (SQLException sqle) {
            // TODO: Add catch code
            sqle.printStackTrace();
        } finally {

        }

    }


    public void onAPPAYMENTCALL(ActionEvent actionEvent) {
        // System.out.println("Inside ap payment**********************");
        LOG.info("Inside ap payment**********************");
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = am.getDBConnection();
            String SPsql = "EXEC USP_PUSH_AR_PAYMENT";
            ps = conn.prepareStatement(SPsql);
            ps.execute();
        } catch (SQLException sqle) {
            // TODO: Add catch code
            sqle.printStackTrace();
        } finally {
            try {
                conn.close();
                ps.close();
            } catch (SQLException e) {
            }

        }

    }
}
