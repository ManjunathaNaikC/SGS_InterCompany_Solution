package com.sgs.ics.view;

import com.sgs.ics.model.bc.am.SGSAppModuleImpl;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.GregorianCalendar;

import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;

import oracle.binding.BindingContainer;

import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.server.ViewObjectImpl;

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
               String connectionUrl = "jdbc:sqlserver://localhost;instanceName=MSSQLSERVR;databasename=DEVINTER;integratedSecurity=true;";
                conn = DriverManager.getConnection(connectionUrl);
         //   conn = DriverManager.getConnection("jdbc:sqlserver://ASBCOLPS02:1433;databaseName=DEVINTER","EYUser","Ey@123");

        } catch (SQLException sqle) {
            // TODO: Add catch code
            sqle.printStackTrace();
        } finally {

        }
               
         return conn;   
        }
    
    public BindingContainer getBindings(){
        
        return (BindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        }
    
    private DCIteratorBinding getDCIteratorBindings(String iterName) {
        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        return bindings.findIteratorBinding(iterName);

    } 

    public void onSOAPAPICALLtest(ActionEvent actionEvent) {

               DCIteratorBinding    invoiceHeaderItr = getDCIteratorBindings("SgsIcInvoiceHeaderVO1Iterator");
               oracle.jbo.Row[] invoiceHeaderDatarows = invoiceHeaderItr.getAllRowsInRange();
        System.out.println("invoiceHeaderDatarows count :: "+invoiceHeaderDatarows.length);
        for (int i = 0; i < invoiceHeaderDatarows.length; i++) {
            System.out.println("InvoiceSeqNo :: "+invoiceHeaderDatarows[i].getAttribute("InvoiceSeqNo"));
            System.out.println("TransactionCategory :: "+invoiceHeaderDatarows[i].getAttribute("TransactionCategory"));
            System.out.println("SourceCurrency :: "+invoiceHeaderDatarows[i].getAttribute("SourceCurrency"));
            System.out.println("IuCustomer :: "+invoiceHeaderDatarows[i].getAttribute("IuCustomer"));
            System.out.println("InvoiceHeaderAmount :: "+invoiceHeaderDatarows[i].getAttribute("InvoiceHeaderAmount"));
            
            
            
            DCIteratorBinding    invoiceLineItr = getDCIteratorBindings("SgsIcInvoiceLineVO2Iterator");
            ViewObjectImpl voucherView = (ViewObjectImpl) invoiceLineItr.getViewObject();
            voucherView.setWhereClause("INVOICE_SEQ_NO="+invoiceHeaderDatarows[i].getAttribute("InvoiceSeqNo"));
            voucherView.executeQuery();
            oracle.jbo.Row[] invoiceLineDaterows = voucherView.getAllRowsInRange();
            System.out.println("invoiceLineDaterows count :: "+invoiceLineDaterows.length);
            for (int j = 0; j < invoiceLineDaterows.length; j++) {
                System.out.println("IcLineNo :: "+invoiceLineDaterows[i].getAttribute("IcLineNo"));
                System.out.println("FromOu :: "+invoiceLineDaterows[i].getAttribute("FromOu"));
                System.out.println("FromJobCode :: "+invoiceLineDaterows[i].getAttribute("FromJobCode"));
                System.out.println("FromDepartmentId :: "+invoiceLineDaterows[i].getAttribute("FromDepartmentId"));
                System.out.println("ToDepartmentId :: "+invoiceLineDaterows[i].getAttribute("ToDepartmentId"));
            }
            
            
            
        }
               
               
        
    }
    
    
    
    public void onSOAPAPICALL(ActionEvent actionEvent) {
        String seqNumber = null;
        String transactionCategory = null;
        String sourceCurrency = null;
        String iuCustomer = null;
        BigDecimal invoiceHdrAmt = null;
        String queryString =
        "SELECT  INVOICE_SEQ_NO,TRANSACTION_CATEGORY,SOURCE_CURRENCY,IU_CUSTOMER,INVOICE_HEADER_AMOUNT FROM IC_INVOICE_HEADER WHERE TRANSACTION_STATUS = 'Approved'";
        System.out.println("lineQuery :: "+queryString);
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
                seqNumber= (String) rs.getString(1);
                transactionCategory= (String) rs.getString(2);
                sourceCurrency= (String) rs.getString(3);
                iuCustomer= (String) rs.getString(4);
                invoiceHdrAmt= (BigDecimal) rs.getBigDecimal(5);
                System.out.println("InvoiceSeqNo :: "+seqNumber);
                System.out.println("TransactionCategory :: "+transactionCategory);
                System.out.println("SourceCurrency :: "+sourceCurrency);
                System.out.println("IuCustomer :: "+iuCustomer);
                System.out.println("InvoiceHeaderAmount :: "+invoiceHdrAmt);
                
                
                Connection lineConn = null;
                PreparedStatement linePst = null;
              String IcLineNo =null;
                String FromOu =null;
                String FromJobCode =null;
                String FromDepartmentId =null;
                String ToDepartmentId =null;


                try {
                    String lineQuery =
                        "SELECT IC_LINE_NO,FROM_BU,FROM_JOB_CODE,FROM_DEPARTMENT_ID,TO_DEPARTMENT_ID,TO_OU,TO_JOB_CODE FROM IC_INVOICE_LINE WHERE INVOICE_SEQ_NO=" +
                        seqNumber + "";
                    System.out.println("lineQuery :: "+lineQuery);
                    SGSAppModuleImpl amLine = new SGSAppModuleImpl();
                    lineConn = amLine.getDBConnection();
                    linePst = lineConn.prepareStatement(lineQuery);
                    ResultSet rsLine = linePst.executeQuery();
                    while (rsLine.next()) {
                            IcLineNo= (String) rsLine.getString(1);
                            FromOu= (String) rsLine.getString(2);
                            FromJobCode= (String) rsLine.getString(3);
                            FromDepartmentId= (String) rsLine.getString(4);
                            ToDepartmentId= (String) rsLine.getString(5);
                        
                            System.out.println("IcLineNo :: "+IcLineNo);
                            System.out.println("FromOu :: "+FromOu);
                            System.out.println("FromJobCode :: "+FromJobCode);
                            System.out.println("FromDepartmentId :: "+FromDepartmentId);
                            System.out.println("ToDepartmentId :: "+ToDepartmentId);
                        
                        }
                    
                } catch (SQLException sqle) {
                    // TODO: Add catch code
                    sqle.printStackTrace();
                } finally {
                    
                    try {
                        lineConn.close();
                        linePst.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
                
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
}
