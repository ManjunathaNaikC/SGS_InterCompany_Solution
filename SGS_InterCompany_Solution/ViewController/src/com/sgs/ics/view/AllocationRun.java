package com.sgs.ics.view;

import com.sgs.ics.model.bc.am.SGSAppModuleImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;

import java.io.StringReader;

import java.math.BigDecimal;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

import java.sql.Connection;
import java.sql.DriverManager;
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

import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.server.ViewObjectImpl;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

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
//               String connectionUrl = "jdbc:sqlserver://localhost;instanceName=MSSQLSERVR;databasename=DEVINTER;integratedSecurity=true;";
//                conn = DriverManager.getConnection(connectionUrl);
            conn = DriverManager.getConnection("jdbc:sqlserver://ASBCOLPS02:1433;databaseName=DEVINTER","EYUser","Ey@123");

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
        String seqNumber = null;
        String transactionCategory = null;
        String sourceCurrency = null;
        String iuCustomer = null;
        BigDecimal invoiceHdrAmt = null;
        String headerBu = null;
        String wsURL ="http://asbcolps02:1111/PSIGW/PeopleSoftServiceListeningConnector/PSFT_EP/CI_CI_HM_BI_INTFC.1.wsdl";
        URL url = null;
        URLConnection connection = null;
        HttpURLConnection httpConn = null;
        String responseString = null;
        String outputString = "";
        OutputStream out = null;
        InputStreamReader isr = null;
        BufferedReader in = null;
   
        
     
        String queryString =
        "SELECT TOP 5 INVOICE_SEQ_NO,TRANSACTION_CATEGORY,SOURCE_CURRENCY,IU_CUSTOMER,INVOICE_HEADER_AMOUNT,SOURCE_BU FROM IC_INVOICE_HEADER WHERE TRANSACTION_STATUS = 'Invoiced In PeopleSoft' AND  ATTRIBUTE5 <> 'Y'";
        System.out.println("queryString :: "+queryString);
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
                headerBu =(String) rs.getString(6);
                System.out.println("InvoiceSeqNo :: "+seqNumber);
                System.out.println("TransactionCategory :: "+transactionCategory);
                System.out.println("SourceCurrency :: "+sourceCurrency);
                System.out.println("IuCustomer :: "+iuCustomer);
                System.out.println("InvoiceHeaderAmount :: "+invoiceHdrAmt);
                System.out.println("headerBu :: "+headerBu);
                
                Connection lineConn = null;
                PreparedStatement linePst = null;
                String IcLineNo =null;
                String FromOu =null;
                String FromJobCode =null;
                String FromDepartmentId =null;
                String ToDepartmentId =null;
                String toOU =null;
                String toJobCode =null;
                String toBu =null;


                try {
                    String lineQuery =
                        "SELECT IC_LINE_NO,FROM_BU,FROM_JOB_CODE,FROM_DEPARTMENT_ID,TO_DEPARTMENT_ID,TO_OU,TO_JOB_CODE,TO_BU FROM IC_INVOICE_LINE WHERE INVOICE_SEQ_NO=" +
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
                            toOU =(String) rsLine.getString(6);
                            toJobCode =(String) rsLine.getString(7);
                            toBu =(String) rsLine.getString(8);
                        
                            System.out.println("IcLineNo :: "+IcLineNo);
                            System.out.println("FromOu :: "+FromOu);
                            System.out.println("FromJobCode :: "+FromJobCode);
                            System.out.println("FromDepartmentId :: "+FromDepartmentId);
                            System.out.println("ToDepartmentId :: "+ToDepartmentId);
                        
                            System.out.println("toOU :: "+toOU);
                            System.out.println("toJobCode :: "+toJobCode);
                            System.out.println("toBu :: "+toBu);
                        
                            StringBuilder sb=new StringBuilder();
//                            System.out.println("<================================START===================================>");
//                            String  soapBuild="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:m83=\"http://asbcolps02:1111/Enterprise/Tools/schemas/M835922.V1\">\n" + 
//                              "   <soapenv:Header/>\n" + 
//                              "   <soapenv:Body>\n" + 
//                              "      <m83:Create__CompIntfc__CI_HM_BI_INTFC>";
//                              sb.append(soapBuild);
//                        
//                            String headerXml="      <m83:INTFC_ID_ADJ>"+seqNumber+"</m83:INTFC_ID_ADJ>\n" + 
//                            "      <m83:TYPE_RTB>"+transactionCategory+"</m83:TYPE_RTB>\n" + 
//                            "      <!--Optional:-->\n" + 
//                            "      <m83:BASE_CURRENCY>"+sourceCurrency+"</m83:BASE_CURRENCY>\n" + 
//                            "      <!--Optional:-->\n" + 
//                            "      <m83:BILL_TO_CUST_ID>"+iuCustomer+"</m83:BILL_TO_CUST_ID>\n" + 
//                            "      <!--Optional:-->\n" + 
//                            "      <m83:UNIT_AMT>"+invoiceHdrAmt+"</m83:UNIT_AMT>\n" + 
//                            "      <!--Optional:-->";
//                            
//                            sb.append(headerXml);
//                        
//                        String lineBuild="      <m83:INTFC_LINE_NUM_ADJ>"+IcLineNo+"</m83:INTFC_LINE_NUM_ADJ>\n" + 
//                        "      <m83:OPERATING_UNIT>"+FromOu+"</m83:OPERATING_UNIT>\n" + 
//                        "      <m83:JOBCODE>"+FromJobCode+"</m83:JOBCODE>\n" + 
//                        "      <m83:DEPTID>"+FromDepartmentId+"</m83:DEPTID>\n" + 
//                        "      <m83:DEPTID2>"+ToDepartmentId+"</m83:DEPTID2>\n";
//                        
//                        sb.append(lineBuild);
//                        
//                            String xmlEnd="  </m83:Create__CompIntfc__CI_HM_BI_INTFC>\n" + 
//                            "   </soapenv:Body>\n" + 
//                            "</soapenv:Envelope>";
//                            sb.append(xmlEnd);
                        
                       String xmlString="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:m83=\"http://asbcolps02:1111/Enterprise/Tools/schemas/M835922.V1\">\n" + 
                        "   <soapenv:Header/>\n" + 
                        "   <soapenv:Body>\n" + 
                        "      <m83:Create__CompIntfc__CI_HM_BI_INTFC>\n" + 
                        "         <m83:INTFC_ID_ADJ>"+seqNumber+"</m83:INTFC_ID_ADJ>\n" + 
                        "         <m83:INTFC_LINE_NUM_ADJ>"+IcLineNo+"</m83:INTFC_LINE_NUM_ADJ>\n" + 
                        "         <m83:BUSINESS_UNIT>"+headerBu+"</m83:BUSINESS_UNIT>\n" + 
                        "         <m83:BUSINESS_UNIT_GL>"+toBu+"</m83:BUSINESS_UNIT_GL>\n" + 
                        "         <m83:BILL_TO_CUST_ID>"+iuCustomer+"</m83:BILL_TO_CUST_ID>\n" + 
                        "         <!--Optional:-->\n" + 
                        "         <m83:ADDRESS_SEQ_NUM>1</m83:ADDRESS_SEQ_NUM>\n" + 
                        "         <!--Optional:-->\n" + 
                        "         <m83:ITEMNAME_STRING>Travel Reimbursement</m83:ITEMNAME_STRING>\n" + 
                        "         <!--Optional:-->\n" + 
                        "         <m83:TYPE_RTB>"+transactionCategory+"</m83:TYPE_RTB>\n" + 
                        "         <!--Optional:-->\n" + 
                        "         <m83:ACCOUNT>1490000</m83:ACCOUNT>\n" + 
                        "         <!--Optional:-->\n" + 
                        "         <m83:DEPTID>2100100</m83:DEPTID>\n" + 
                        "         <!--Optional:-->\n" + 
                        "         <m83:OPERATING_UNIT>"+FromOu+"</m83:OPERATING_UNIT>\n" + 
                        "         <!--Optional:-->\n" + 
                        "         <m83:JOBCODE>"+FromJobCode+"</m83:JOBCODE>\n" + 
                        "         <!--Optional:-->\n" + 
                        "         <m83:BI_CURRENCY_CD>USD</m83:BI_CURRENCY_CD>\n" + 
                        "         <!--Optional:-->\n" + 
                        "         <m83:BASE_CURRENCY>"+sourceCurrency+"</m83:BASE_CURRENCY>\n" + 
                        "         <!--Optional:-->\n" + 
                        "         <m83:CUR_RT_TYPE>CRRNT</m83:CUR_RT_TYPE>\n" + 
                        "         <!--Optional:-->\n" + 
                        "         <m83:INVOICE_DT>2023/03/22</m83:INVOICE_DT>\n" + 
                        "         <!--Optional:-->\n" + 
                        "         <m83:ACCOUNTING_DT>2023/03/22</m83:ACCOUNTING_DT>\n" + 
                        "         <!--Optional:-->\n" + 
                        "         <m83:UNIT_OF_MEASURE>EA</m83:UNIT_OF_MEASURE>\n" + 
                        "         <!--Optional:-->\n" + 
                        "         <m83:QTY>1</m83:QTY>\n" + 
                        "         <!--Optional:-->\n" + 
                        "         <m83:UNIT_AMT>"+invoiceHdrAmt+"</m83:UNIT_AMT>\n" + 
                        "         <!--Optional:-->\n" + 
                        "         <m83:NET_EXTENDED_AMT>1000</m83:NET_EXTENDED_AMT>\n" + 
                        "         <!--Optional:-->\n" + 
                        "         <m83:GROSS_EXTENDED_AMT>1000</m83:GROSS_EXTENDED_AMT>\n" + 
                        "         <!--Optional:-->\n" + 
                        "         <m83:WTHD_AMT_CODES>W230A</m83:WTHD_AMT_CODES>\n" + 
                        "         <!--Optional:-->\n" + 
                        "         <m83:ACCOUNT2>2395000</m83:ACCOUNT2>\n" + 
                        "         <!--Optional:-->\n" + 
                        "         <m83:TO_BUSINESS_UNIT>"+toBu+"</m83:TO_BUSINESS_UNIT>\n" + 
                        "         <!--Optional:-->\n" + 
                        "         <m83:DEPTID2>"+ToDepartmentId+"</m83:DEPTID2>\n" + 
                        "         <!--Optional:-->\n" + 
                        "         <m83:OPERATING_UNIT_TO>"+FromOu+"</m83:OPERATING_UNIT_TO>\n" + 
                        "         <!--Optional:-->\n" + 
                        "         <m83:PV_JOBCODE_TO>35124</m83:PV_JOBCODE_TO>\n" + 
                        "      </m83:Create__CompIntfc__CI_HM_BI_INTFC>\n" + 
                        "   </soapenv:Body>\n" + 
                        "</soapenv:Envelope>";
                        
                            sb.append(xmlString);
                System.out.println("XMLBUILDER==>"+sb.toString());   
                            System.out.println("<================================END===================================>");       
                           // sb=null;
                        
                           try {
                            url = new URL(wsURL);
                            connection = url.openConnection();  // Need to check the standard
                            System.out.println("connection" + connection);
                            httpConn = (HttpURLConnection) connection;
                            System.out.println("xmlInput" + xmlString);
                            byte[] buffer = new byte[xmlString.length()];
                            buffer = xmlString.getBytes();

                            String SOAPAction = "CI_HM_INTFC_BI_CI_C.V1";
                            // Set the appropriate HTTP parameters.

                            httpConn.setRequestProperty("Content-Length", String.valueOf(buffer.length));

                            httpConn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");


                            httpConn.setRequestProperty("SOAPAction", SOAPAction);
                            httpConn.setRequestMethod("POST");
                            httpConn.setDoOutput(true);
                            httpConn.setDoInput(true);
                            out = httpConn.getOutputStream();
                            System.out.println("Just before write");
                            out.write(buffer);
                            out.close();
                            System.out.println("out " + out);
                            // Read the response and write it to standard out.
                            System.out.println("Error Stream"+httpConn.getErrorStream());
                                                isr = new InputStreamReader(httpConn.getInputStream());
                                            
                            
                                                in = new BufferedReader(isr);
                            
                                                while ((responseString = in.readLine()) != null)
                                                {
                                                    outputString = outputString + responseString;
                                                }
                                                System.out.println(outputString);
                                          
                            
                                                // Get the response from the web service call
                                                Document document = parseXmlFile(outputString);
                            
                                                NodeList nodeLst = document.getElementsByTagName("m83:Create__CompIntfc__CI_HM_BI_INTFCResponse");
                            System.out.println("nodeLst.getLength()"+nodeLst.getLength());
                            if(nodeLst.getLength()>0){
                             
                                                String webServiceResponse = nodeLst.item(0).getTextContent();
                                                System.out.println("The response from the web service call is : " + webServiceResponse);
                                                
                                                
                            }
                        
                               
                        } catch (MalformedURLException murle) {
                            // TODO: Add catch code
                            murle.printStackTrace();
                        } catch (ProtocolException pe) {
                            // TODO: Add catch code
                            pe.printStackTrace();
                        } catch (IOException ioe) {
                            // TODO: Add catch code
                            ioe.printStackTrace();
                        }
                        
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
