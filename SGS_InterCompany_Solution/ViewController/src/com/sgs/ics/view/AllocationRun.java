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
        String seqNumber = null;
        String transactionCategory = null;
        String sourceCurrency = null;
        String iuCustomer = null;
        BigDecimal invoiceHdrAmt = null;
        String headerBu = null;
        String wsURL =
            "http://asbcolps02:1111/PSIGW/PeopleSoftServiceListeningConnector/PSFT_EP/CI_CI_HM_BI_INTFC.1.wsdl";
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

        Connection conn = null;
        PreparedStatement pst = null;

        try {
            SGSAppModuleImpl am = new SGSAppModuleImpl();
            conn = am.getDBConnection();
            String sqlIdentifier = queryString;
            pst = conn.prepareStatement(sqlIdentifier);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                seqNumber = (String) rs.getString(1);
                transactionCategory = (String) rs.getString(2);
                sourceCurrency = (String) rs.getString(3);
                iuCustomer = (String) rs.getString(4);
                invoiceHdrAmt = (BigDecimal) rs.getBigDecimal(5);
                headerBu = (String) rs.getString(6);
                LOG.info("InvoiceSeqNo :: " + seqNumber);
                LOG.info("TransactionCategory :: " + transactionCategory);
                LOG.info("SourceCurrency :: " + sourceCurrency);
                LOG.info("IuCustomer :: " + iuCustomer);
                LOG.info("InvoiceHeaderAmount :: " + invoiceHdrAmt);
                LOG.info("headerBu :: " + headerBu);

                Connection lineConn = null;
                PreparedStatement linePst = null;
                String IcLineNo = null;
                String FromOu = null;
                String FromJobCode = null;
                String FromDepartmentId = null;
                String ToDepartmentId = null;
                String toOU = null;
                String toJobCode = null;
                String toBu = null;


                try {
                    String lineQuery =
                        "SELECT IC_LINE_NO,FROM_BU,FROM_JOB_CODE,FROM_DEPARTMENT_ID,TO_DEPARTMENT_ID,TO_OU,TO_JOB_CODE,TO_BU FROM IC_INVOICE_LINE WHERE INVOICE_SEQ_NO=" +
                        seqNumber + "";
                    LOG.info("lineQuery :: " + lineQuery);
                    SGSAppModuleImpl amLine = new SGSAppModuleImpl();
                    lineConn = amLine.getDBConnection();
                    linePst = lineConn.prepareStatement(lineQuery);
                    ResultSet rsLine = linePst.executeQuery();
                    while (rsLine.next()) {
                        IcLineNo = (String) rsLine.getString(1);
                        FromOu = (String) rsLine.getString(2);
                        FromJobCode = (String) rsLine.getString(3);
                        FromDepartmentId = (String) rsLine.getString(4);
                        ToDepartmentId = (String) rsLine.getString(5);
                        toOU = (String) rsLine.getString(6);
                        toJobCode = (String) rsLine.getString(7);
                        toBu = (String) rsLine.getString(8);

                        LOG.info("IcLineNo :: " + IcLineNo);
                        LOG.info("FromOu :: " + FromOu);
                        LOG.info("FromJobCode :: " + FromJobCode);
                        LOG.info("FromDepartmentId :: " + FromDepartmentId);
                        LOG.info("ToDepartmentId :: " + ToDepartmentId);

                        LOG.info("toOU :: " + toOU);
                        LOG.info("toJobCode :: " + toJobCode);
                        LOG.info("toBu :: " + toBu);

                        StringBuilder sb = new StringBuilder();
                        //                            LOG.info("<================================START===================================>");
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

                        String xmlString =
                            "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:m83=\"http://asbcolps02:1111/Enterprise/Tools/schemas/M835922.V1\">\n" +
                            "   <soapenv:Header/>\n" + "   <soapenv:Body>\n" +
                            "      <m83:Create__CompIntfc__CI_HM_BI_INTFC>\n" + "         <m83:INTFC_ID_ADJ>" +
                            seqNumber + "</m83:INTFC_ID_ADJ>\n" + "         <m83:INTFC_LINE_NUM_ADJ>" + IcLineNo +
                            "</m83:INTFC_LINE_NUM_ADJ>\n" + "         <m83:BUSINESS_UNIT>" + headerBu +
                            "</m83:BUSINESS_UNIT>\n" + "         <m83:BUSINESS_UNIT_GL>" + toBu +
                            "</m83:BUSINESS_UNIT_GL>\n" + "         <m83:BILL_TO_CUST_ID>" + iuCustomer +
                            "</m83:BILL_TO_CUST_ID>\n" + "         <!--Optional:-->\n" +
                            "         <m83:ADDRESS_SEQ_NUM>1</m83:ADDRESS_SEQ_NUM>\n" + "         <!--Optional:-->\n" +
                            "         <m83:ITEMNAME_STRING>Travel Reimbursement</m83:ITEMNAME_STRING>\n" +
                            "         <!--Optional:-->\n" + "         <m83:TYPE_RTB>" + transactionCategory +
                            "</m83:TYPE_RTB>\n" + "         <!--Optional:-->\n" +
                            "         <m83:ACCOUNT>1490000</m83:ACCOUNT>\n" + "         <!--Optional:-->\n" +
                            "         <m83:DEPTID>2100100</m83:DEPTID>\n" + "         <!--Optional:-->\n" +
                            "         <m83:OPERATING_UNIT>" + FromOu + "</m83:OPERATING_UNIT>\n" +
                            "         <!--Optional:-->\n" + "         <m83:JOBCODE>" + FromJobCode +
                            "</m83:JOBCODE>\n" + "         <!--Optional:-->\n" +
                            "         <m83:BI_CURRENCY_CD>USD</m83:BI_CURRENCY_CD>\n" + "         <!--Optional:-->\n" +
                            "         <m83:BASE_CURRENCY>" + sourceCurrency + "</m83:BASE_CURRENCY>\n" +
                            "         <!--Optional:-->\n" + "         <m83:CUR_RT_TYPE>CRRNT</m83:CUR_RT_TYPE>\n" +
                            "         <!--Optional:-->\n" + "         <m83:INVOICE_DT>2023/03/22</m83:INVOICE_DT>\n" +
                            "         <!--Optional:-->\n" +
                            "         <m83:ACCOUNTING_DT>2023/03/22</m83:ACCOUNTING_DT>\n" +
                            "         <!--Optional:-->\n" + "         <m83:UNIT_OF_MEASURE>EA</m83:UNIT_OF_MEASURE>\n" +
                            "         <!--Optional:-->\n" + "         <m83:QTY>1</m83:QTY>\n" +
                            "         <!--Optional:-->\n" + "         <m83:UNIT_AMT>" + invoiceHdrAmt +
                            "</m83:UNIT_AMT>\n" + "         <!--Optional:-->\n" +
                            "         <m83:NET_EXTENDED_AMT>1000</m83:NET_EXTENDED_AMT>\n" +
                            "         <!--Optional:-->\n" +
                            "         <m83:GROSS_EXTENDED_AMT>1000</m83:GROSS_EXTENDED_AMT>\n" +
                            "         <!--Optional:-->\n" +
                            "         <m83:WTHD_AMT_CODES>W230A</m83:WTHD_AMT_CODES>\n" +
                            "         <!--Optional:-->\n" + "         <m83:ACCOUNT2>2395000</m83:ACCOUNT2>\n" +
                            "         <!--Optional:-->\n" + "         <m83:TO_BUSINESS_UNIT>" + toBu +
                            "</m83:TO_BUSINESS_UNIT>\n" + "         <!--Optional:-->\n" + "         <m83:DEPTID2>" +
                            ToDepartmentId + "</m83:DEPTID2>\n" + "         <!--Optional:-->\n" +
                            "         <m83:OPERATING_UNIT_TO>" + FromOu + "</m83:OPERATING_UNIT_TO>\n" +
                            "         <!--Optional:-->\n" + "         <m83:PV_JOBCODE_TO>35124</m83:PV_JOBCODE_TO>\n" +
                            "      </m83:Create__CompIntfc__CI_HM_BI_INTFC>\n" + "   </soapenv:Body>\n" +
                            "</soapenv:Envelope>";

                        sb.append(xmlString);
                        LOG.info("XMLBUILDER==>" + sb.toString());
                        LOG.info("<================================END===================================>");
                        // sb=null;

                        try {
                            url = new URL(wsURL);
                            connection = url.openConnection(); // Need to check the standard
                            LOG.info("connection" + connection);
                            httpConn = (HttpURLConnection) connection;
                            LOG.info("xmlInput" + xmlString);
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
                            LOG.info("Just before write");
                            out.write(buffer);
                            out.close();
                            LOG.info("out " + out);
                            // Read the response and write it to standard out.
                            LOG.info("Error Stream" + httpConn.getErrorStream());
                            isr = new InputStreamReader(httpConn.getInputStream());


                            in = new BufferedReader(isr);

                            while ((responseString = in.readLine()) != null) {
                                outputString = outputString + responseString;
                            }
                            LOG.info(outputString);


                            // Get the response from the web service call
                            Document document = parseXmlFile(outputString);

                            NodeList nodeLst =
                                document.getElementsByTagName("m83:Create__CompIntfc__CI_HM_BI_INTFCResponse");
                            LOG.info("nodeLst.getLength()" + nodeLst.getLength());
                            if (nodeLst.getLength() > 0) {

                                String webServiceResponse = nodeLst.item(0).getTextContent();
                                LOG.info("The response from the web service call is : " + webServiceResponse);


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

    public void onUpdateBillingInformation(ActionEvent actionEvent) {

        String wsURL =
            "http://asbcolps02:1111/PSIGW/PeopleSoftServiceListeningConnector/PSFT_EP/CI_CI_BI_AP_AR_WTH.1.wsdl";
        URL url = null;
        URLConnection connection = null;
        HttpURLConnection httpConn = null;
        String responseString = null;
        String outputString = "";
        OutputStream out = null;
        InputStreamReader isr = null;
        BufferedReader in = null;

        StringBuilder sb = new StringBuilder();

        String xmlString =
            "<soapenv:Envelope xmlns:soapenv=http://schemas.xmlsoap.org/soap/envelope/ xmlns:m36=http://asbcolps02:1111/Enterprise/Tools/schemas/M367830.V1>\n" +
            "   <soapenv:Header/>\n" + "   <soapenv:Body>\n" + "      <m36:Get__CompIntfc__CI_BI_AP_AR_WTH>\n" +
            "         <m36:WORKBENCH_ID>100</m36:WORKBENCH_ID>\n" + "      </m36:Get__CompIntfc__CI_BI_AP_AR_WTH>\n" +
            "   </soapenv:Body>\n" + "</soapenv:Envelope>";

        sb.append(xmlString);
        LOG.info("XMLBUILDER For Update Builling==>" + sb.toString());
        LOG.info("<================================END===================================>");
        // sb=null;

        try {
            url = new URL(wsURL);
            connection = url.openConnection(); // Need to check the standard
            LOG.info("connection" + connection);
            httpConn = (HttpURLConnection) connection;
            LOG.info("xmlInput" + xmlString);
            byte[] buffer = new byte[xmlString.length()];
            buffer = xmlString.getBytes();

            String SOAPAction = "CI_CI_BI_AP_AR_WTH_G.V1";
            // Set the appropriate HTTP parameters.

            httpConn.setRequestProperty("Content-Length", String.valueOf(buffer.length));

            httpConn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");


            httpConn.setRequestProperty("SOAPAction", SOAPAction);
            httpConn.setRequestMethod("GET");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            out = httpConn.getOutputStream();

            out.write(buffer);
            out.close();
            LOG.info("out " + out);
            // Read the response and write it to standard out.
            LOG.info("Error Stream" + httpConn.getErrorStream());
            isr = new InputStreamReader(httpConn.getInputStream());


            in = new BufferedReader(isr);

            while ((responseString = in.readLine()) != null) {
                outputString = outputString + responseString;
            }
            LOG.info(outputString);


            // Get the response from the web service call
            Document document = parseXmlFile(outputString);

            NodeList nodeLst = document.getElementsByTagName("m36:Get__CompIntfc__CI_BI_AP_AR_WTHResponse");
            LOG.info("nodeLst.getLength()" + nodeLst.getLength());
            if (nodeLst.getLength() > 0) {

                String webServiceResponse0 = nodeLst.item(0).getTextContent();
                String webServiceResponse1 = nodeLst.item(1).getTextContent();
                String webServiceResponse2 = nodeLst.item(2).getTextContent();
                String webServiceResponse3 = nodeLst.item(3).getTextContent();
                LOG.info("webServiceResponse0 : " + webServiceResponse0);
                LOG.info("webServiceResponse1 : " + webServiceResponse1);
                LOG.info("webServiceResponse2 : " + webServiceResponse2);
                LOG.info("webServiceResponse3 : " + webServiceResponse3);


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

    public void onDEPOSITAPI(ActionEvent actionEvent) {
        String wsURL =
            "http://asbcolps02:1111/PSIGW/PeopleSoftServiceListeningConnector/PSFT_EP/CI_IU_AR_DEPOSIT.1.wsdl";
        URL url = null;
        URLConnection connection = null;
        HttpURLConnection httpConn = null;
        String responseString = null;
        String outputString = "";
        OutputStream out = null;
        InputStreamReader isr = null;
        BufferedReader in = null;
        StringBuilder sb = new StringBuilder();
        String xmlString =
            "<soapenv:Envelope xmlns:soapenv=http://schemas.xmlsoap.org/soap/envelope/ xmlns:m10=http://asbcolps02:1111/Enterprise/Tools/schemas/M1048758.V1>\n" +
            "       <soapenv:Header/>\n" + "               <soapenv:Body>\n" +
            "                       <m10:Create__CompIntfc__IU_AR_DEPOSIT>\n" +
            "                               <m10:IU_WB_ID>VI_125</m10:IU_WB_ID>\n" +
            "                               <m10:LOCKBOX_ID>125</m10:LOCKBOX_ID>\n" +
            "                               <m10:LOCKBOX_BATCH_ID>?</m10:LOCKBOX_BATCH_ID>\n" +
            "                               <!--Optional:-->\n" +
            "                               <m10:DEPOSIT_BU>IND02</m10:DEPOSIT_BU>\n" +
            "                               <!--Optional:-->\n" +
            "                               <m10:BUSINESS_UNIT>IND02</m10:BUSINESS_UNIT>\n" +
            "                               <!--Optional:-->\n" +
            "                               <m10:REF_VALUE>AR_1234</m10:REF_VALUE>\n" +
            "                               <!--Optional:-->\n" +
            "                               <m10:PAYMENT_CURRENCY>USD</m10:PAYMENT_CURRENCY>\n" +
            "                               <!--Optional:-->\n" +
            "                               <m10:PAYMENT_AMT>5000</m10:PAYMENT_AMT>\n" +
            "                               <!--Optional:-->\n" +
            "                               <m10:CUST_SETID></m10:CUST_SETID>\n" +
            "                               <!--Optional:-->\n" +
            "                               <m10:CUST_ID>SGS_CRP01</m10:CUST_ID>\n" +
            "                               <!--Optional:-->\n" +
            "                               <m10:BAL_CURRENCY></m10:BAL_CURRENCY>\n" +
            "                               <!--Optional:-->\n" +
            "                               <m10:PAYMENT_ID>PI_12345</m10:PAYMENT_ID>\n" +
            "                               <!--Optional:-->\n" +
            "                               <m10:PAYMENT_METHOD>CHK</m10:PAYMENT_METHOD>\n" +
            "                               <!--Optional:-->\n" +
            "                               <m10:RECEIVED_DT>2023-03-03</m10:RECEIVED_DT>\n" +
            "                               <!--Optional:-->\n" +
            "                               <m10:ACCOUNTING_DT>2023-03-03</m10:ACCOUNTING_DT>\n" +
            "                               <!--Optional:-->\n" +
            "                               <m10:BNK_ID_NBR>CHK1</m10:BNK_ID_NBR>\n" +
            "                               <!--Optional:-->\n" +
            "                               <m10:BANK_ACCOUNT_NUM>101335046</m10:BANK_ACCOUNT_NUM>\n" +
            "                       </m10:Create__CompIntfc__IU_AR_DEPOSIT>\n" + "               </soapenv:Body>\n" +
            "       </soapenv:Envelope>;\n";

        sb.append(xmlString);
        LOG.info("XMLBUILDER==>" + sb.toString());
        LOG.info("<================================END===================================>");
        // sb=null;

        try {
            url = new URL(wsURL);
            connection = url.openConnection(); // Need to check the standard
            LOG.info("connection" + connection);
            httpConn = (HttpURLConnection) connection;
            LOG.info("xmlInput" + xmlString);
            byte[] buffer = new byte[xmlString.length()];
            buffer = xmlString.getBytes();

            String SOAPAction = "CI_IU_AR_DEPOSIT_C.V1";
            // Set the appropriate HTTP parameters.

            httpConn.setRequestProperty("Content-Length", String.valueOf(buffer.length));

            httpConn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");


            httpConn.setRequestProperty("SOAPAction", SOAPAction);
            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            out = httpConn.getOutputStream();
            LOG.info("Just before write");
            out.write(buffer);
            out.close();
            LOG.info("out " + out);
            // Read the response and write it to standard out.
            LOG.info("Error Stream" + httpConn.getErrorStream());
            isr = new InputStreamReader(httpConn.getInputStream());


            in = new BufferedReader(isr);

            while ((responseString = in.readLine()) != null) {
                outputString = outputString + responseString;
            }
            LOG.info(outputString);


            // Get the response from the web service call
            Document document = parseXmlFile(outputString);

            NodeList nodeLst = document.getElementsByTagName("m10:Create__CompIntfc__IU_AR_DEPOSITResponse");
            LOG.info("nodeLst.getLength()" + nodeLst.getLength());
            if (nodeLst.getLength() > 0) {

                String webServiceResponse = nodeLst.item(0).getTextContent();
                LOG.info("The response from the web service call is : " + webServiceResponse);


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
