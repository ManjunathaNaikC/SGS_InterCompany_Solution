package com.sgs.ics.view;

import com.sgs.ics.model.bc.am.SGSAppModuleImpl;
import com.sgs.ics.model.bc.commonutils.CommonUtils;
import com.sgs.ics.ui.utils.ADFUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.math.BigDecimal;

import java.net.MalformedURLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Arrays;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectManyChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelTabbed;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailItem;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.server.ViewObjectImpl;
import oracle.jbo.uicli.binding.JUCtrlListBinding;

import oracle.jbo.server.ViewDefImpl;
import oracle.jbo.server.ViewObjectImpl;
import java.util.TimeZone;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewObject;

import org.apache.myfaces.trinidad.model.UploadedFile;

public class ActionEventsBean {
    protected String SAVE_DATA="Commit";
    private RichPopup statisticspopupbind;
    private RichColumn invoiceColSelectBind;
    private RichSelectBooleanCheckbox invoiceCheckBoxSelectBind;
    private RichSelectBooleanCheckbox vouchercheckBoxSelectBind;
    private RichColumn voucherColSelectBind;
    private RichShowDetailItem bindStlmtPanelTab;
    private RichShowDetailItem bindStlmtVoucherTab;
    private RichShowDetailItem bindStlmtInvoiceTab;
    private RichPanelTabbed bindStlmtDashboardPanelTab;
    private RichSelectBooleanCheckbox bindSettlementRowCheckBox;
    private RichColumn bindSettlementSelectColumn;
    private RichSelectBooleanCheckbox bindNetSelectRecHeader;
    private RichColumn bindGeo2SelectCol;
    private RichSelectBooleanCheckbox bindSelectAllGeo2;
    private RichSelectBooleanCheckbox bindSelectAllGeo1;
    private RichColumn bindGeo1SelectCol;
    private RichSelectBooleanCheckbox bindStatCheckboxSelect;
    private RichColumn onStatSelectAllColumn;
    private RichPopup approvepoopupbind;
    private RichPopup rejectpopupbind;
    
    private RichInputText rejectionCommentsBind;
    private RichInputFile inputFileBind;
    private RichSelectOneChoice rejectionReasonLOVBind;
    
    private RichPopup invoiceApprovBind;
    private RichPopup invoiceConfirmBind;
    private RichPopup invoiceRejectBind;
    private RichPopup submitstatpopupbind;
    private RichPopup creditMemoPopupBind;
    private RichInputDate creditDateBind;
    private RichInputText percentageReversalBind;
   // private RichInputText completeReversalBind;
    private RichSelectOneChoice reversalReasonLovBind;
    private RichInputDate creditDateBindVal;
    private RichSelectBooleanCheckbox selectCreditRecordBind;
    private RichColumn selectRecordColBind;
    private RichPopup invoicecreditmemobindpopup;
    private RichColumn selectcolInvoiceDBBind;
    private RichSelectBooleanCheckbox selectcheckInvoiceBind;
    private Boolean chechCheckBox = false;
    private RichPanelGroupLayout percentageGroupBind;
    private RichInputFile inputFileBindFA;
    private RichPopup approvePopupFABind;

    public ActionEventsBean() {
    }
    
    

    public void onStatisticalDataSave(ActionEvent actionEvent) {
        executeBinding(SAVE_DATA);
        ADFUtils.saveNotifier();
    }
    
    public void onMarkUpRateDataSave(ActionEvent actionEvent) {
        executeBinding(SAVE_DATA);
        ADFUtils.saveNotifier();
    }
    
    public Object executeBinding(String binding) {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding(binding);
        Object result = operationBinding.execute();
        return result;
    }
    public BindingContainer getBindings(){
        
        return (BindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        }
    
    private DCIteratorBinding getDCIteratorBindings(String iterName) {
        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        return bindings.findIteratorBinding(iterName);

    } 

    public void onDeleteStatisticalData(ActionEvent actionEvent) {
        executeBinding("DeleteStatisticalData");
        executeBinding(SAVE_DATA);      
        ADFUtils.deleteNotifier();
    }
    
    public void onDeleteMarkUpData(ActionEvent actionEvent) {
        executeBinding("DeleteMarkUpData");
        executeBinding(SAVE_DATA);      
        ADFUtils.deleteNotifier();
    }

    public void onDocumentsDelete(ActionEvent actionEvent) {
        executeBinding("DeleteDocs");
        executeBinding(SAVE_DATA);      
        ADFUtils.deleteNotifier();
    }

    public void onMarkupDelete(ActionEvent actionEvent) {
        executeBinding("DeleteMarkUp");
        executeBinding(SAVE_DATA);      
        ADFUtils.deleteNotifier();
        
    }

    public void onIdenComboDelete(ActionEvent actionEvent) {
        // Add event code here...
        executeBinding("DeleteIdenCombo");
        executeBinding(SAVE_DATA);      
        ADFUtils.deleteNotifier();
    }

    public void onTargetComboDelete(ActionEvent actionEvent) {
        // Add event code here...
        executeBinding("DeleteTargetCombo");
        executeBinding(SAVE_DATA);      
        ADFUtils.deleteNotifier();
    }

    public void onCrossChargeComboDelete(ActionEvent actionEvent) {
        // Add event code here...
        executeBinding("DeleteCrossCharge");
        executeBinding(SAVE_DATA);      
        ADFUtils.deleteNotifier();
    }

    public void onIdenComboCreate(ActionEvent actionEvent) {
      DCIteratorBinding dcIteratorbinding = getDCIteratorBindings("SgsCostIdentificationRuleVOIterator");
      Row row = dcIteratorbinding.getCurrentRow();
      String costIdentificationId = (String) row.getAttribute("CostIdentificationId");
      System.out.println("costIdentificationId :: "+costIdentificationId);
      String costIdentificationName = (String) row.getAttribute("CostIdentificationName");
        System.out.println("costIdentificationName :: "+costIdentificationName);
        if(null != costIdentificationName && !(costIdentificationName.isEmpty())){    
            executeBinding("CreateInsertIdenCombo");
        }else{
            createNotifier("Please Create Cost Identification Header Record.");
        }
    }
    

    public  void createNotifier( String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        if(null == message){
            message="Before creating child record, Please create header record.";
        }
        String messageText = message;
        FacesMessage fm = new FacesMessage(messageText);
        fm.setSeverity(FacesMessage.SEVERITY_WARN);
        context.addMessage(null, fm);
    }

    public void onTargetComboCreate(ActionEvent actionEvent) {
        DCIteratorBinding dcIteratorbinding = getDCIteratorBindings("SgsCostIdentificationRuleVOIterator");
        Row row = dcIteratorbinding.getCurrentRow();
        String costIdentificationId = (String) row.getAttribute("CostIdentificationId");
        System.out.println("costIdentificationId :: "+costIdentificationId);
        String costIdentificationName = (String) row.getAttribute("CostIdentificationName");
          System.out.println("costIdentificationName :: "+costIdentificationName);
          if(null != costIdentificationName && !(costIdentificationName.isEmpty())){    
              executeBinding("CreateInsertTargetCombo");
          } else {
              createNotifier("Please Create Cost Identification Header Record.");
          }
    }

    public void onCrossChargeCreate(ActionEvent actionEvent) {
        DCIteratorBinding dcIteratorbinding = getDCIteratorBindings("SgsCostIdentificationRuleVOIterator");
        Row row = dcIteratorbinding.getCurrentRow();
        String costIdentificationId = (String) row.getAttribute("CostIdentificationId");
        System.out.println("costIdentificationId :: "+costIdentificationId);
        String costIdentificationName = (String) row.getAttribute("CostIdentificationName");
          System.out.println("costIdentificationName :: "+costIdentificationName);
          if(null != costIdentificationName && !(costIdentificationName.isEmpty())){    
              executeBinding("CreateInsertCrossCombo");
          } else {
              createNotifier("Please Create Cost Identification Header Record.");
          }
    }

    public void onCostIdentifierDelete(ActionEvent actionEvent) {
        executeBinding("DeleteCostIdentifier");
        executeBinding(SAVE_DATA);      
        ADFUtils.deleteNotifier();
    }

    public void onCostIdentifierDelete(DialogEvent dialogEvent) {
        executeBinding("DeleteCostIdentifier");
        executeBinding(SAVE_DATA);      
        ADFUtils.deleteNotifier();
    }


    public void onSelectionOfNatureOfExpense(ValueChangeEvent valueChangeEvent) {

        String natureOfExpense = "0";
        BindingContainer bc = this.getBindingsCont();
        JUCtrlListBinding listBindings = (JUCtrlListBinding) bc.get("NatureOfExpenseSearch");
        Object str[] = listBindings.getSelectedValues();
        System.out.println("str :: " + Arrays.toString(str));

        for (Object o : str) {
            natureOfExpense += "," + o;
        }
        System.out.println("natureOfExpense :: " + natureOfExpense);
        String natureOfExpenseString = natureOfExpense.toString();
        System.out.println("natureOfExpenseString :: " + natureOfExpenseString);
        ViewObjectImpl viewImpl = null;
        viewImpl = (ViewObjectImpl) getDCIteratorBindings("SgsCostIdentificationRuleVOIterator").getViewObject();
        viewImpl.setFullSqlMode(ViewObjectImpl.FULLSQL_MODE_AUGMENTATION);
        viewImpl.setWhereClause(" NATURE_OF_EXPENSE IN ( " + natureOfExpenseString + ")");
        System.out.println("viewImpl getQuery :: " + viewImpl.getQuery());
        viewImpl.executeQuery();

    }

    public BindingContainer getBindingsCont() {
         return BindingContext.getCurrent().getCurrentBindingsEntry();
     }

    public void onNatureOfExpenseSearch(ActionEvent actionEvent) {

        BindingContainer bc = this.getBindingsCont();
        String natureOfExpense = "0";
        JUCtrlListBinding listBindings = (JUCtrlListBinding) bc.get("NatureOfExpenseSearch");
        Object str[] = listBindings.getSelectedValues();
        System.out.println("str :: " + Arrays.toString(str));
        for (Object o : str) {
            natureOfExpense += "," + o;
        }
        String natureOfExpenseString = natureOfExpense.toString();
        System.out.println("natureOfExpenseString :: " + natureOfExpenseString);

        String queryString =
            "select LOOKUP_CODE FROM SGS_LOOKUP_TABLE WHERE LOOKUP_TYPE='NATURE_OF_EXPENSE' AND LOOKUP_ID IN (" +
            natureOfExpenseString + ")";
        String natureOfExpenseLookupCode = "'" + "none" + "'";
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
                natureOfExpenseLookupCode += "," + "'" + (String) rs.getString(1) + "'";
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
        ViewObjectImpl viewImpl = null;
        viewImpl = (ViewObjectImpl) getDCIteratorBindings("SgsCostIdentificationRuleVOIterator").getViewObject();
        viewImpl.setFullSqlMode(ViewObjectImpl.FULLSQL_MODE_AUGMENTATION);
        viewImpl.setWhereClause(" NATURE_OF_EXPENSE IN ( " + natureOfExpenseLookupCode + ")");
        System.out.println("viewImpl getQuery :: " + viewImpl.getQuery());
        viewImpl.executeQuery();


    }

    public void onCostIdentificationSave(ActionEvent actionEvent) {
        executeBinding(SAVE_DATA);
        ADFUtils.saveNotifier();
    }

    public void onStdRateChildDelete(ActionEvent actionEvent) {
        executeBinding("DeleteStdRate");
        executeBinding(SAVE_DATA);
        ADFUtils.deleteNotifier();
    }

    public void onStdRateChildSave(ActionEvent actionEvent) {
        String inputProvider=null;
        DCIteratorBinding dcIteratorbinding = getDCIteratorBindings("SgsStandardRateSetupVO1Iterator");
        Row row = dcIteratorbinding.getCurrentRow();
        System.out.println("Input provider :: "+row.getAttribute("INPUTPROVIDER"));
        ADFContext.getCurrent().getSessionScope().put("INPUTPROVIDER",row.getAttribute("INPUTPROVIDER"));
        executeBinding(SAVE_DATA);
        ADFUtils.saveNotifier();
    }

    
    public void onGSTDelete(DialogEvent dialogEvent) {
        // Add event code here...
        executeBinding("DeleteGst");
        executeBinding(SAVE_DATA);
        ADFUtils.deleteNotifier();
    }
    
    
    public void genericSave(ActionEvent actionEvent) {
            executeBinding(SAVE_DATA);
            ADFUtils.saveNotifier();
        }

    public void onGSTChildDelete(DialogEvent dialogEvent) {
        // Add event code here...
        executeBinding("DeleteGstChild");
        executeBinding(SAVE_DATA);
        ADFUtils.deleteNotifier();
    }

    public void onBUDelete(DialogEvent dialogEvent) {
        // Add event code here...
        executeBinding("DeleteBU");
        executeBinding(SAVE_DATA);
        ADFUtils.deleteNotifier();
    }

    
//    public void genericSave(ActionEvent actionEvent) {
//        executeBinding(SAVE_DATA);
//        ADFUtils.saveNotifier();
//    }


    public void onTdsWhtDelete(DialogEvent dialogEvent) {
        // Add event code here...
        executeBinding("DeleteTdsWht");
                        executeBinding(SAVE_DATA);
                        ADFUtils.deleteNotifier();
    }

//    public void genericSave(ActionEvent actionEvent) {
//        // Add event code here...
//        executeBinding(SAVE_DATA);
//        ADFUtils.saveNotifier();
////    }
//        public void genericSave(ActionEvent actionEvent) {
//        executeBinding(SAVE_DATA);
//        ADFUtils.saveNotifier();
//    }

    public void onTdsWhtSave(ActionEvent actionEvent) {
        // Add event code here...
        executeBinding(SAVE_DATA);
        ADFUtils.saveNotifier();
    }

    public void onWhtIdenDelete(DialogEvent dialogEvent) {
        // Add event code here...
        executeBinding("DeleteWhtIden");
                        executeBinding(SAVE_DATA);
                        ADFUtils.deleteNotifier();
        }

    public void onWhtRateDelete(DialogEvent dialogEvent) {
        // Add event code here...
        executeBinding("DeleteWhtRate");
                        executeBinding(SAVE_DATA);
                        ADFUtils.deleteNotifier();
    }

    public void onVatDelete(DialogEvent dialogEvent) {
          // Add event code here...
          executeBinding("DeleteVat");
          executeBinding(SAVE_DATA);
          ADFUtils.deleteNotifier();
      }
    public void onVatTaxDelete(DialogEvent dialogEvent) {
          // Add event code here...
          executeBinding("DeleteVatTax");
          executeBinding(SAVE_DATA);
          ADFUtils.deleteNotifier();
      }
    public void onStandardRateDelete(DialogEvent dialogEvent) {
          // Add event code here...
          executeBinding("DeleteStandardRate");
          executeBinding(SAVE_DATA);
          ADFUtils.deleteNotifier();
      }

    public void onCopyFromPreviousMonthActionEvent(ActionEvent actionEvent) {
        GregorianCalendar gcal = new GregorianCalendar();
              System.out.println("Current date: " + gcal.getTime());
              // past month
              gcal.add((GregorianCalendar.MONTH), -1);
//               String month[] = { "Jan", "Feb", "Mar", "Apr",
//                                   "May", "Jun", "Jul", "Aug",
//                                   "Sep", "Oct", "Nov", "Dec" };
        String month[] = { "1", "2", "3", "4",
                            "5", "6", "7", "8",
                            "9", "10", "11", "12" };
                                   
        System.out.println("Modified date (Previous Month) Month: " +   month[gcal.get(Calendar.MONTH)]);
        System.out.println("Modified date (Previous Month) Month: " +   month[gcal.get(Calendar.MONTH)]); 
        System.out.println("Modified date (Previous Month)  Year : " + gcal.get(Calendar.YEAR));
        
        
        ViewObjectImpl viewImpl = null;
        viewImpl = (ViewObjectImpl) getDCIteratorBindings("SgsStatisticalPreviousMonthVO1Iterator").getViewObject();
        viewImpl.setFullSqlMode(ViewObjectImpl.FULLSQL_MODE_AUGMENTATION);
        viewImpl.setWhereClause("MONTH(TRANSACTION_PERIOD) = '"+ month[gcal.get(Calendar.MONTH)]+"'  and YEAR(TRANSACTION_PERIOD) ='"+gcal.get(Calendar.YEAR) +"'");
        System.out.println("viewImpl getQuery :: " + viewImpl.getQuery());
        viewImpl.executeQuery();
        
        
        
        
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        this.statisticspopupbind.show(hints);
               
    }
    
//    
//    public static void main(String []args){
//        
//
//        GregorianCalendar gcal = new GregorianCalendar();
//              System.out.println("Current date: " + gcal.getTime());
//              // past month
//              gcal.add((GregorianCalendar.MONTH), -1);
//               String month[] = { "1", "2", "3", "4",
//                                   "5", "6", "7", "8",
//                                   "9", "10", "11", "12" };
//                                   
//            
//        System.out.println("Modified date (Previous Month) Month: " +   month[gcal.get(Calendar.MONTH)]);
//              
//      System.out.println("Modified date (Previous Month) Month: " +   gcal.get(Calendar.MONTH));
//        System.out.println("Modified date (Previous Month)  Year : " + gcal.get(Calendar.YEAR));
//        
//        
//        
//    }

    public void setStatisticspopupbind(RichPopup statisticspopupbind) {
        this.statisticspopupbind = statisticspopupbind;
    }

    public RichPopup getStatisticspopupbind() {
        return statisticspopupbind;
    }

    public void onStatDataUpdate(ActionEvent actionEvent) {
        // Add event code here...
        
        DCIteratorBinding previousMonthData =  null;
        DCIteratorBinding    statisticalData = getDCIteratorBindings("SgsStatisticalDataVO1Iterator");
              try {
                   previousMonthData = getDCIteratorBindings("SgsStatisticalPreviousMonthVO1Iterator");

                  oracle.jbo.Row[] previousMonthDatarows = previousMonthData.getAllRowsInRange();
                  
                  System.out.println("previousMonthDatarows:: "+previousMonthDatarows.length);
                  CommonUtils util= new CommonUtils();
                  Object user= (Object)util.getSessionScopeValue("_username").toString();

                  for (int i = 0; i < previousMonthDatarows.length; i++) {
                      System.out.println("value :: "+previousMonthDatarows[i].getAttribute("STATISTICALDATACATEGORY"));
                      executeBinding("CreateStatisticalData");
                      //previousMonthDatarows[i].getAttribute("StatisticalDataCategory");
                      Row row = statisticalData.getCurrentRow();
                      row.setAttribute("StatisticalDataCategory", previousMonthDatarows[i].getAttribute("STATISTICALDATACATEGORY"));                      
                      row.setAttribute("FROMOU", previousMonthDatarows[i].getAttribute("FROMOU"));
                      row.setAttribute("FROMJOBCODE", previousMonthDatarows[i].getAttribute("FROMJOBCODE"));
                      row.setAttribute("FROMDEPTID", previousMonthDatarows[i].getAttribute("FROMDEPTID"));
                      row.setAttribute("FROMBU", previousMonthDatarows[i].getAttribute("FROMBU"));
                      row.setAttribute("NATUREOFEXPENSE", previousMonthDatarows[i].getAttribute("NATUREOFEXPENSE"));
                      row.setAttribute("InputProvider", previousMonthDatarows[i].getAttribute("InputProvider"));
                      row.setAttribute("GLACCOUNT", previousMonthDatarows[i].getAttribute("GLACCOUNT"));
                      row.setAttribute("CREATEDDATE", new java.sql.Date(new Date().getTime()));
                      row.setAttribute("UpdatedBy", user);
                      row.setAttribute("UpdatedDate", new java.sql.Date(new Date().getTime()));
                      row.setAttribute("CreatedBy", user);
                      row.setAttribute("ValidityTill", previousMonthDatarows[i].getAttribute("VALIDITYTILL"));
                      row.setAttribute("ValidityFrom", previousMonthDatarows[i].getAttribute("VALIDITYFROM"));
                      row.setAttribute("RejectionComments", previousMonthDatarows[i].getAttribute("REJECTIONCOMMENTS"));
                      row.setAttribute("RejectedReason", previousMonthDatarows[i].getAttribute("REJECTEDREASON"));
                      row.setAttribute("TargetAmount", previousMonthDatarows[i].getAttribute("TARGETAMOUNT"));
                      row.setAttribute("EmployeeId", previousMonthDatarows[i].getAttribute("EMPLOYEEID"));
                      row.setAttribute("Currency", previousMonthDatarows[i].getAttribute("CURRENCY"));
                      row.setAttribute("CostGroup", previousMonthDatarows[i].getAttribute("COSTGROUP"));
                      row.setAttribute("UnitOfMeasure", previousMonthDatarows[i].getAttribute("UNITOFMEASURE"));
                      row.setAttribute("STATISTICALDATA", previousMonthDatarows[i].getAttribute("STATISTICALDATA"));
                      row.setAttribute("ToDepartmentId", previousMonthDatarows[i].getAttribute("TODEPARTMENTID"));
                      row.setAttribute("ToOperatingUnit", previousMonthDatarows[i].getAttribute("TOOPERATINGUNIT"));
                      row.setAttribute("ToJobCode", previousMonthDatarows[i].getAttribute("TOJOBCODE"));
                      row.setAttribute("ToBusinessUnit", previousMonthDatarows[i].getAttribute("TOBUSINESSUNIT"));
                      
                      
                      
                     // previousMonthDatarows[i].getAttribute("StatisticalDataCategory");
                      System.out.println("value :: "+previousMonthDatarows[i].getAttribute("STATISTICALDATACATEGORY"));
              
                  } 
              } catch (Exception e) {
                  // TODO: Add catch code
                  e.printStackTrace(); 

              }
        executeBinding(SAVE_DATA);
        ViewObject object = statisticalData.getViewObject();
        object.executeQuery();
        ADFUtils.saveNotifier();
    }

    public void onCloseOfStatDataEvent(ActionEvent actionEvent) {
        // Add event code here...
        this.statisticspopupbind.hide();
    }

    public void onCancelOfStatDataEvent(ActionEvent actionEvent) {
        // Add event code here...
        executeBinding("Rollback");
    }
    
    
    public void onTPADocsUpload(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        if (valueChangeEvent.getNewValue() != null) {
            
            String filePath1 = ADFUtils.getPath();
            if(filePath1.equalsIgnoreCase("NOPATH")){
                FacesContext context = FacesContext.getCurrentInstance();
                String messageText = "Please setup the system path to upload the file.";
                FacesMessage fm = new FacesMessage(messageText);
                fm.setSeverity(FacesMessage.SEVERITY_INFO);
                context.addMessage(null, fm);
            }else{
            try {
                UploadedFile uploadedFile = (UploadedFile) valueChangeEvent.getNewValue();
                if (null != uploadedFile) {
                    InputStream inputStream = null;
                    inputStream = uploadedFile.getInputStream();
                    BufferedInputStream bfi = new BufferedInputStream(inputStream);
                    String fileName = uploadedFile.getFilename();
                    String path = null;
                    // String filePath1 = "D:\\FilesStoragePath\\";

                   
                    System.out.println("filePath1" + filePath1);
                    
                    String tokens = uploadedFile.getFilename();
                    String fileNames = uploadedFile.getFilename();
                    String contentType = uploadedFile.getContentType();
                    //                                        String fileNameWithOutExt = FilenameUtils.removeExtension(tokens);
                    //                                        String fileNameWithExt = FilenameUtils.getExtension(tokens);
                    //                                        DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
                    //                                        Date date = new Date();
                    //                                        String fileNames = fileNameWithOutExt + "_" + dateFormat.format(date) + "." + fileNameWithExt;
                    path = filePath1 + fileNames;
                    saveFile(path, fileName, bfi);

                    DCIteratorBinding docs = getDCIteratorBindings("SgsTpaDocAttachment1VO2Iterator");
                    Row row = docs.getCurrentRow();
                    // row.setAttribute("FileContent",sbyte);
                    //row.setAttribute("EffectiveFrom",new java.sql.Date(new java.util.Date().getTime()));
                    row.setAttribute("Attachment", fileName);
                    row.setAttribute("Attribute1", path);
                    row.setAttribute("Attribute2", contentType);

                    System.out.println("File path and file Name in downlaod" + path + fileName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    }


    public void saveFile(String filePath, String fileName, BufferedInputStream in) throws MalformedURLException,
                                                                                          IOException {
        FileOutputStream fout = null;
        try {
            File files = new File(filePath);
            if (!files.exists()) {
                if (files.mkdirs()) {
                    System.out.println("Multiple directories are created!");
                } else {
                    System.out.println("Failed to create multiple directories!");
                }
            }
            fout = new FileOutputStream(filePath + fileName);
            byte data[] = new byte[8192];
            int count;
            while ((count = in.read(data, 0, 8192)) != -1) {
                fout.write(data, 0, count);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (in != null)
                in.close();
            if (fout != null)
                fout.close();
        }
    }

    public void onTpaDocsDownload(FacesContext facesContext, OutputStream outputStream) {
        DCBindingContainer bindingContainer =
            (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding imageIter = (DCIteratorBinding) bindingContainer.get("SgsTpaDocAttachment1VO2Iterator");
        ViewObject vo = imageIter.getViewObject();
        oracle.jbo.Row currentRow = (oracle.jbo.Row) vo.getCurrentRow();

        String filePath = (String) currentRow.getAttribute("Attribute1");
        String fileName = (String) currentRow.getAttribute("Attachment");
        System.out.println("filePath :: " + filePath);
        System.out.println("fileName :: " + fileName);


        try {
            if (null != filePath && null != fileName) {
                File f = new File(filePath + fileName);
                FileInputStream fis;
                byte[] b;

                fis = new FileInputStream(f);
                int n;
                while ((n = fis.available()) > 0) {
                    b = new byte[n];
                    int result = fis.read(b);

                    outputStream.write(b, 0, b.length);
                    if (result == -1)
                        break;
                }
                outputStream.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onSelectAllVouchers(ValueChangeEvent valueChangeEvent) {
        System.out.println("Checkbox At Vouchers:: " + valueChangeEvent.getNewValue());
        DCIteratorBinding voucherData = null;
        voucherData = getDCIteratorBindings("SgsStlmtVoucherVO1Iterator");
        oracle.jbo.Row[] voucherDatarows = voucherData.getAllRowsInRange();
        if ((Boolean)valueChangeEvent.getNewValue()) {   
            System.out.println("voucherDatarows:: " + voucherDatarows.length);
            for (int i = 0; i < voucherDatarows.length; i++) {
                System.out.println("voucher period  :: " + voucherDatarows[i].getAttribute("Period"));
                System.out.println("value :: " + voucherDatarows[i].getAttribute("SelectRecord"));
//                Row row = voucherData.getCurrentRow();
//                row.setAttribute("SelectRecord", "Yes");
//                vouchercheckBoxSelectBind.setValue("Yes");
                 voucherDatarows[i].setAttribute("SelectRecord", "Yes");
                System.out.println("row :: " + voucherDatarows[i].getAttribute("SelectRecord"));
            }

        }else {     
            System.out.println("voucherDatarows else:: " + voucherDatarows.length);
            for (int i = 0; i < voucherDatarows.length; i++) {
                System.out.println("voucher period  :: " + voucherDatarows[i].getAttribute("Period"));
                System.out.println("value :: " + voucherDatarows[i].getAttribute("SelectRecord"));
//                Row row = voucherData.getCurrentRow();
//                row.setAttribute("SelectRecord", "No");
//                vouchercheckBoxSelectBind.setValue("No");
                voucherDatarows[i].setAttribute("SelectRecord", "No");
                System.out.println("row :: " + voucherDatarows[i].getAttribute("SelectRecord"));
            }
            
        }
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(vouchercheckBoxSelectBind);
        AdfFacesContext.getCurrentInstance().addPartialTarget(voucherColSelectBind);

    }

    public void onSelectAllInvoice(ValueChangeEvent valueChangeEvent) {
        System.out.println("Checkbox At Invoices :: " + valueChangeEvent.getNewValue());
        DCIteratorBinding invoiceData = null;
        invoiceData = getDCIteratorBindings("SgsStlmtInvVO1Iterator");
        oracle.jbo.Row[] invoiceDatarows = invoiceData.getAllRowsInRange();
        if ((Boolean)valueChangeEvent.getNewValue()) {
            System.out.println("invoices Datarows:: " + invoiceDatarows.length);
            for (int i = 0; i < invoiceDatarows.length; i++) {
                System.out.println("invoice period  :: " + invoiceDatarows[i].getAttribute("Period"));
                System.out.println("invoice  :: " + invoiceDatarows[i].getAttribute("SelectRecordInvoice"));
//                Row row = invoiceData.getCurrentRow();
//                row.setAttribute("SelectRecordInvoice", "Yes");
                 invoiceDatarows[i].setAttribute("SelectRecordInvoice", "Yes");
              //  invoiceCheckBoxSelectBind.setValue("Yes");
                System.out.println(" invoice SelectRecordInvoice :: " + invoiceDatarows[i].getAttribute("SelectRecordInvoice"));
            }               
        }else {
            System.out.println("invoices Datarows else:: " + invoiceDatarows.length);
            for (int i = 0; i < invoiceDatarows.length; i++) {
                System.out.println("invoice period  :: " + invoiceDatarows[i].getAttribute("Period"));
                System.out.println("invoice  :: " + invoiceDatarows[i].getAttribute("SelectRecordInvoice"));
//                Row row = invoiceData.getCurrentRow();
//                row.setAttribute("SelectRecordInvoice", "No");
                invoiceDatarows[i].setAttribute("SelectRecordInvoice", "No");
               // invoiceCheckBoxSelectBind.setValue("No");
                System.out.println(" invoice rows :: " + invoiceDatarows[i].getAttribute("SelectRecordInvoice"));
            }
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(invoiceCheckBoxSelectBind);
        AdfFacesContext.getCurrentInstance().addPartialTarget(invoiceColSelectBind);
    }

    public void setInvoiceColSelectBind(RichColumn invoiceColSelectBind) {
        this.invoiceColSelectBind = invoiceColSelectBind;
    }

    public RichColumn getInvoiceColSelectBind() {
        return invoiceColSelectBind;
    }

    public void setInvoiceCheckBoxSelectBind(RichSelectBooleanCheckbox invoiceCheckBoxSelectBind) {
        this.invoiceCheckBoxSelectBind = invoiceCheckBoxSelectBind;
    }

    public RichSelectBooleanCheckbox getInvoiceCheckBoxSelectBind() {
        return invoiceCheckBoxSelectBind;
    }

    public void setVouchercheckBoxSelectBind(RichSelectBooleanCheckbox vouchercheckBoxSelectBind) {
        this.vouchercheckBoxSelectBind = vouchercheckBoxSelectBind;
    }

    public RichSelectBooleanCheckbox getVouchercheckBoxSelectBind() {
        return vouchercheckBoxSelectBind;
    }

    public void setVoucherColSelectBind(RichColumn voucherColSelectBind) {
        this.voucherColSelectBind = voucherColSelectBind;
    }

    public RichColumn getVoucherColSelectBind() {
        return voucherColSelectBind;
    }


    public void setBindStlmtVoucherTab(RichShowDetailItem bindStlmtVoucherTab) {
        this.bindStlmtVoucherTab = bindStlmtVoucherTab;
    }

    public RichShowDetailItem getBindStlmtVoucherTab() {
        return bindStlmtVoucherTab;
    }

    public void setBindStlmtInvoiceTab(RichShowDetailItem bindStlmtInvoiceTab) {
        this.bindStlmtInvoiceTab = bindStlmtInvoiceTab;
    }

    public RichShowDetailItem getBindStlmtInvoiceTab() {
        return bindStlmtInvoiceTab;
    }

    public void setBindStlmtDashboardPanelTab(RichPanelTabbed bindStlmtDashboardPanelTab) {
        this.bindStlmtDashboardPanelTab = bindStlmtDashboardPanelTab;
    }

    public RichPanelTabbed getBindStlmtDashboardPanelTab() {
        return bindStlmtDashboardPanelTab;
    }

    public void onVoucherTabArInvoice(ActionEvent actionEvent) {
        bindStlmtVoucherTab.setDisclosed(false);
        bindStlmtInvoiceTab.setDisclosed(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindStlmtInvoiceTab);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindStlmtVoucherTab);
    }

    public void onInvoiceTabArVoucher(ActionEvent actionEvent) {
        bindStlmtInvoiceTab.setDisclosed(false);
        bindStlmtVoucherTab.setDisclosed(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindStlmtInvoiceTab);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindStlmtVoucherTab);
    }

    public void onCreateSettlementSelectAll(ValueChangeEvent valueChangeEvent) {
        System.out.println("Checkbox At create settlement :: " + valueChangeEvent.getNewValue());
        DCIteratorBinding settlementData = null;
        settlementData = getDCIteratorBindings("SgsCreateSettlementVO1Iterator");
        oracle.jbo.Row[] settlementDataDatarows = settlementData.getAllRowsInRange();
        if ((Boolean)valueChangeEvent.getNewValue()) {
            System.out.println("settlementDataDatarows Datarows:: " + settlementDataDatarows.length);
            for (int i = 0; i < settlementDataDatarows.length; i++) {
                System.out.println(" SELECTRECORDS :: " + settlementDataDatarows[i].getAttribute("SELECTRECORDS"));
                 settlementDataDatarows[i].setAttribute("SELECTRECORDS", "Yes");
                System.out.println(" settlementDataDatarows SELECTRECORDS :: " + settlementDataDatarows[i].getAttribute("SELECTRECORDS"));
            }               
        }else {
            System.out.println("settlementDataDatarows Datarows else:: " + settlementDataDatarows.length);
            for (int i = 0; i < settlementDataDatarows.length; i++) {
                System.out.println("SELECTRECORDS  :: " + settlementDataDatarows[i].getAttribute("SELECTRECORDS"));
                settlementDataDatarows[i].setAttribute("SELECTRECORDS", "No");
                System.out.println(" SELECTRECORDS rows :: " + settlementDataDatarows[i].getAttribute("SELECTRECORDS"));
            }
        }
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindSettlementRowCheckBox);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(bindSettlementSelectColumn);
    }

    public void setBindSettlementRowCheckBox(RichSelectBooleanCheckbox bindSettlementRowCheckBox) {
        this.bindSettlementRowCheckBox = bindSettlementRowCheckBox;
    }

    public RichSelectBooleanCheckbox getBindSettlementRowCheckBox() {
        return bindSettlementRowCheckBox;
    }

    public void setBindSettlementSelectColumn(RichColumn bindSettlementSelectColumn) {
        this.bindSettlementSelectColumn = bindSettlementSelectColumn;
    }

    public RichColumn getBindSettlementSelectColumn() {
        return bindSettlementSelectColumn;
    }

    public void onSelectAllForSettlement(ValueChangeEvent valueChangeEvent) {
        System.out.println("Checkbox At create settlement 11 :: " + valueChangeEvent.getNewValue());
    }

    public void onGenerateSettlementEvent(ActionEvent actionEvent) {
        DCIteratorBinding dcIteratorbinding = getDCIteratorBindings("CreateStlmtRVO1Iterator");
        Row row = dcIteratorbinding.getCurrentRow();
        String PAYMENTID = (String) row.getAttribute("PAYMENTID");
        Date PAYMENTDATE = (Date) row.getAttribute("PAYMENTDATE");
        String DISBUSEMENTBANKACCOUNT = (String) row.getAttribute("DISBUSEMENTBANKACCOUNT");
        String RECEIVERBANKACCOUNT = (String) row.getAttribute("RECEIVERBANKACCOUNT");
        String PAYMENTMETHOD = (String) row.getAttribute("PAYMENTMETHOD");
        String TRANSACTIONREFERENCENO = (String) row.getAttribute("TRANSACTIONREFERENCENO");
        String PAYMENTCURRENCY = (String) row.getAttribute("PAYMENTCURRENCY");
        BigDecimal TRXAMOUNT = (BigDecimal) row.getAttribute("TRXAMOUNT");
        
        System.out.println("PAYMENTID 11 :: " + PAYMENTID);

        System.out.println("PAYMENTDATE 11 :: " + PAYMENTDATE);
        System.out.println("DISBUSEMENTBANKACCOUNT 11 :: " + DISBUSEMENTBANKACCOUNT);
        System.out.println("RECEIVERBANKACCOUNT 11 :: " + RECEIVERBANKACCOUNT);
        System.out.println("PAYMENTMETHOD 11 :: " + PAYMENTMETHOD);
        System.out.println("TRANSACTIONREFERENCENO 11 :: " + TRANSACTIONREFERENCENO);
        System.out.println("PAYMENTCURRENCY 11 :: " + PAYMENTCURRENCY);
        System.out.println("TRXAMOUNT 11 :: " + TRXAMOUNT);
        
        //SgsStlmtVoucherVO1Iterator
       // SgsStlmtInvVO1Iterator
        
    }
    

    public void onCreateSettlementSearch(ActionEvent actionEvent) {
        DCIteratorBinding dcIteratorbinding = getDCIteratorBindings("CreateStlmtRVO1Iterator");
        Row row = dcIteratorbinding.getCurrentRow();
        String PAYMENTID = (String) row.getAttribute("PAYMENTID");
        String ICCUSTOMERGEO = (String) row.getAttribute("ICCUSTOMERGEO");
        String ICCUSTOMERBU = (String) row.getAttribute("ICCUSTOMERBU");
        String ICSUPPLIERGEO = (String) row.getAttribute("ICSUPPLIERGEO");
        String ICSUPPLIERBU = (String) row.getAttribute("ICSUPPLIERBU");

        DCIteratorBinding iteratorBinding = getDCIteratorBindings("SgsStlmtVoucherVO1Iterator");
        RowSetIterator rowSetIterator = iteratorBinding.getRowSetIterator();
        ViewObjectImpl voucherView = (ViewObjectImpl) iteratorBinding.getViewObject();
        ViewCriteria criteria = voucherView.getViewCriteria("SgsCreateStlmtVoucherVOCriteria");
        voucherView.applyViewCriteria(criteria);
        voucherView.setNamedWhereClauseParam("bCusBu", ICCUSTOMERBU);
        voucherView.setNamedWhereClauseParam("bCusGeo", ICCUSTOMERGEO);
        voucherView.setNamedWhereClauseParam("bSupBu", ICSUPPLIERBU);
        voucherView.setNamedWhereClauseParam("bSupGeo", ICSUPPLIERGEO);
        voucherView.executeQuery();


        oracle.jbo.Row[] voucherDatarows = voucherView.getAllRowsInRange();
        System.out.println(" voucherDatarows length :: " + voucherDatarows.length);
        DCIteratorBinding createSettlementData = getDCIteratorBindings("SgsCreateSettlementVO1Iterator");
        for (int i = 0; i < voucherDatarows.length; i++) {
            System.out.println(" PsVoucherNo :: " + voucherDatarows[i].getAttribute("PsVoucherNo"));
            executeBinding("CreateSettlement");
            Row row1 = createSettlementData.getCurrentRow();
            row1.setAttribute("DATE", voucherDatarows[i].getAttribute("AcctDate"));
            row1.setAttribute("NETAMOUNTPAYABLE", voucherDatarows[i].getAttribute("NetAmountPayable"));
            row1.setAttribute("OUTSTANDINGAMOUNT", voucherDatarows[i].getAttribute("OsAmountPayable"));
            row1.setAttribute("PAYMENTSTATUS", voucherDatarows[i].getAttribute("PaymentStatus"));
            row1.setAttribute("PSINVOICENUMBER", voucherDatarows[i].getAttribute("RefToArInvoice"));
            row1.setAttribute("PSVOUCHERNUMBER", voucherDatarows[i].getAttribute("PsVoucherNo"));
            row1.setAttribute("SETTLEMENTAMOUNT", voucherDatarows[i].getAttribute("StlmtAmount"));
            row1.setAttribute("SETTLEMENTSTATUS", voucherDatarows[i].getAttribute("StlmtStatus"));
            row1.setAttribute("TRANSACTIONCURRENCY", voucherDatarows[i].getAttribute("TxnCurrency"));

        }
        executeBinding(SAVE_DATA);
        ViewObjectImpl data = (ViewObjectImpl) getDCIteratorBindings("SgsCreateSettlementVO1Iterator").getViewObject();
        data.executeQuery();


    }

    public void onCreateSettlementSave(ActionEvent actionEvent) {
        executeBinding(SAVE_DATA);
        
        ViewObjectImpl data = (ViewObjectImpl) getDCIteratorBindings("SgsCreateSettlementVO1Iterator").getViewObject();
        data.executeQuery();
        ADFUtils.saveNotifier();
    }

    public void onNettingHeaderSelectRecord(ValueChangeEvent valueChangeEvent) {
        System.out.println("Checkbox At Netting :: " + valueChangeEvent.getNewValue());
        DCIteratorBinding settlementData = null;
        settlementData = getDCIteratorBindings("SgsNettingInvoiceVoucherVO1Iterator");
        oracle.jbo.Row[] NettingDataDatarows = settlementData.getAllRowsInRange();
        if ((Boolean)valueChangeEvent.getNewValue()) {
            System.out.println("NettingDataDatarows Datarows:: " + NettingDataDatarows.length);
            for (int i = 0; i < NettingDataDatarows.length; i++) {
                System.out.println(" SELECTRECORDS :: " + NettingDataDatarows[i].getAttribute("SelectedNettingRec"));
                 NettingDataDatarows[i].setAttribute("SelectedNettingRec", "Yes");
                System.out.println(" NettingDataDatarows SELECTRECORDS :: " + NettingDataDatarows[i].getAttribute("SelectedNettingRec"));
            }               
        }else {
            System.out.println("NettingDataDatarows Datarows else:: " + NettingDataDatarows.length);
            for (int i = 0; i < NettingDataDatarows.length; i++) {
                System.out.println("SelectedNettingRec  :: " + NettingDataDatarows[i].getAttribute("SelectedNettingRec"));
                NettingDataDatarows[i].setAttribute("SelectedNettingRec", "No");
                System.out.println(" SelectedNettingRec rows :: " + NettingDataDatarows[i].getAttribute("SelectedNettingRec"));
            }
        }
    }

    public void setBindNetSelectRecHeader(RichSelectBooleanCheckbox bindNetSelectRecHeader) {
        this.bindNetSelectRecHeader = bindNetSelectRecHeader;
    }

    public RichSelectBooleanCheckbox getBindNetSelectRecHeader() {
        return bindNetSelectRecHeader;
    }

    public void onClickOfNettingHeaderLink(ActionEvent actionEvent) {

        DCIteratorBinding nettingData = getDCIteratorBindings("SgsNettingInvoiceVoucherVO1Iterator");
        Row row = nettingData.getCurrentRow();
        String geos = (String) row.getAttribute("NettingId");
        System.out.println("Geos :: "+geos);
        if (null != geos && !geos.isEmpty()) {
            String[] result = new String[2];
            // String s = "USA-IND";
            result = geos.split("\\-", 0); // splitting the string at "-"
            String Geo1 = result[0];
            String Geo2 = result[1];
            if (null != Geo1 && !Geo1.isEmpty()) {
                ViewObjectImpl viewImpl1 = null;
                viewImpl1 = (ViewObjectImpl) getDCIteratorBindings("SgsNettingGeo1VO1Iterator").getViewObject();
                viewImpl1.setFullSqlMode(ViewObjectImpl.FULLSQL_MODE_AUGMENTATION);
                viewImpl1.setWhereClause(" GEOGRAPHY_1 IN ( '" + Geo1 + "')");
                System.out.println("viewImpl1 getQuery :: " + viewImpl1.getQuery());
                viewImpl1.executeQuery();
                
                
                ViewObjectImpl viewImplsum = null;
                viewImplsum = (ViewObjectImpl) getDCIteratorBindings("SgsGeo1SumValuesVO1Iterator").getViewObject();
                viewImplsum.setFullSqlMode(ViewObjectImpl.FULLSQL_MODE_AUGMENTATION);
                viewImplsum.setWhereClause(" GEOGRAPHY_1 IN ( '" + Geo1 + "')");
                System.out.println("viewImplsum getQuery :: " + viewImplsum.getQuery());
                viewImplsum.executeQuery();
                
                
            }
            if (null != Geo2 && !Geo2.isEmpty()) {
                ViewObjectImpl viewImpl2 = null;
                viewImpl2 = (ViewObjectImpl) getDCIteratorBindings("SgsNettingGeo2VO1Iterator").getViewObject();
                viewImpl2.setFullSqlMode(ViewObjectImpl.FULLSQL_MODE_AUGMENTATION);
                viewImpl2.setWhereClause(" GEOGRAPHY_2 IN ( '" + Geo2 + "')");
                System.out.println("viewImpl2 getQuery :: " + viewImpl2.getQuery());
                viewImpl2.executeQuery();
                
                
                ViewObjectImpl viewImplsum = null;
                viewImplsum = (ViewObjectImpl) getDCIteratorBindings("SgsGeo2SumValuesVO1Iterator").getViewObject();
                viewImplsum.setFullSqlMode(ViewObjectImpl.FULLSQL_MODE_AUGMENTATION);
                viewImplsum.setWhereClause(" GEOGRAPHY_2 IN ( '" + Geo2 + "')");
                System.out.println("viewImplsum getQuery :: " + viewImplsum.getQuery());
                viewImplsum.executeQuery();
            }
        }

    }

    public void onNettingGeo2SelectRec(ValueChangeEvent valueChangeEvent) {
        System.out.println("Checkbox At Netting Geo2 :: " + valueChangeEvent.getNewValue());
        DCIteratorBinding geo2Data = null;
        geo2Data = getDCIteratorBindings("SgsNettingGeo2VO1Iterator");
        oracle.jbo.Row[] geo2Datarows = geo2Data.getAllRowsInRange();
        if ((Boolean)valueChangeEvent.getNewValue()) {
            System.out.println("geo2Datarows Datarows:: " + geo2Datarows.length);
            for (int i = 0; i < geo2Datarows.length; i++) {
                System.out.println(" SelectGeo2Rec :: " + geo2Datarows[i].getAttribute("SelectGeo2Rec"));
                 geo2Datarows[i].setAttribute("SelectGeo2Rec", "Yes");
                System.out.println(" geo2Datarows SELECTRECORDS :: " + geo2Datarows[i].getAttribute("SelectGeo2Rec"));
            }               
        }else {
            System.out.println("NettingDataDatarows Datarows else:: " + geo2Datarows.length);
            for (int i = 0; i < geo2Datarows.length; i++) {
                System.out.println("SelectGeo2Rec  :: " + geo2Datarows[i].getAttribute("SelectGeo2Rec"));
                geo2Datarows[i].setAttribute("SelectGeo2Rec", "No");
                System.out.println(" SelectGeo2Rec rows :: " + geo2Datarows[i].getAttribute("SelectGeo2Rec"));
            }
        }
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindGeo2SelectCol);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindSelectAllGeo2);
    }

    public void onNettingGeo1SelectRec(ValueChangeEvent valueChangeEvent) {
        System.out.println("Checkbox At Netting Geo1 :: " + valueChangeEvent.getNewValue());
        DCIteratorBinding geo1Data = null;
        geo1Data = getDCIteratorBindings("SgsNettingGeo1VO1Iterator");
        oracle.jbo.Row[] geo1Datarows = geo1Data.getAllRowsInRange();
        if ((Boolean)valueChangeEvent.getNewValue()) {
            System.out.println("geo1Datarows Datarows:: " + geo1Datarows.length);
            for (int i = 0; i < geo1Datarows.length; i++) {
                System.out.println(" SelectGeo1Rec :: " + geo1Datarows[i].getAttribute("SelectGeo1Rec"));
                 geo1Datarows[i].setAttribute("SelectGeo1Rec", "Yes");
                System.out.println(" geo1Datarows SELECTRECORDS :: " + geo1Datarows[i].getAttribute("SelectGeo1Rec"));
            }               
        }else {
            System.out.println("geo1Datarows Datarows else:: " + geo1Datarows.length);
            for (int i = 0; i < geo1Datarows.length; i++) {
                System.out.println("SelectGeo1Rec  :: " + geo1Datarows[i].getAttribute("SelectGeo1Rec"));
                geo1Datarows[i].setAttribute("SelectGeo1Rec", "No");
                System.out.println(" SelectGeo1Rec rows :: " + geo1Datarows[i].getAttribute("SelectGeo1Rec"));
            }
        }
        
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindSelectAllGeo1);
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindGeo1SelectCol);
    }

    public void setBindGeo2SelectCol(RichColumn bindGeo2SelectCol) {
        this.bindGeo2SelectCol = bindGeo2SelectCol;
    }

    public RichColumn getBindGeo2SelectCol() {
        return bindGeo2SelectCol;
    }

    public void setBindSelectAllGeo2(RichSelectBooleanCheckbox bindSelectAllGeo2) {
        this.bindSelectAllGeo2 = bindSelectAllGeo2;
    }

    public RichSelectBooleanCheckbox getBindSelectAllGeo2() {
        return bindSelectAllGeo2;
    }

    public void setBindSelectAllGeo1(RichSelectBooleanCheckbox bindSelectAllGeo1) {
        this.bindSelectAllGeo1 = bindSelectAllGeo1;
    }

    public RichSelectBooleanCheckbox getBindSelectAllGeo1() {
        return bindSelectAllGeo1;
    }

    public void setBindGeo1SelectCol(RichColumn bindGeo1SelectCol) {
        this.bindGeo1SelectCol = bindGeo1SelectCol;
    }

    public RichColumn getBindGeo1SelectCol() {
        return bindGeo1SelectCol;
    }
    
    
    public void onStatisticalSubmitAproval(ActionEvent actionEvent) {

//       ArrayList<String> pageList=  (ArrayList<String>) ADFContext.getCurrent().getSessionScope().get("pageList");
//       System.out.println("Page List ::"+pageList.toString());
//        if(null != pageList && !(pageList.isEmpty()) && pageList.contains("ALL_PAGE")){
//            System.out.println("Admin"+pageList.toString());
//            RichPopup.PopupHints hints = new RichPopup.PopupHints();
//           this.submitstatpopupbind.show(hints);
//        }else{
//            
            
            DCIteratorBinding statData = null;
            statData = getDCIteratorBindings("SgsStatisticalDataVO1Iterator");
            oracle.jbo.Row[] statDataDatarows = statData.getAllRowsInRange();
            for (int i = 0; i < statDataDatarows.length; i++) {
                System.out.println(" StatSelectedRecord At Approve:: " + statDataDatarows[i].getAttribute("StatSelectedRecord"));
                System.out.println(" InputProvider At Approve:: " + statDataDatarows[i].getAttribute("InputProvider"));
                if (null != statDataDatarows[i].getAttribute("StatSelectedRecord") &&
                    statDataDatarows[i].getAttribute("StatSelectedRecord").equals("Yes")) {
                    
                        statDataDatarows[i].setAttribute("APPROVESTATUS", "Submitted For Approval");
                    }
            } 
            
            executeBinding(SAVE_DATA);
            submitstatpopupbind.hide();
     //  }
    }

    public void onStatisticalApprove(ActionEvent actionEvent) {

       ArrayList<String> pageList=  (ArrayList<String>) ADFContext.getCurrent().getSessionScope().get("pageList");
       System.out.println("Page List ::"+pageList.toString());
        if(null != pageList && !(pageList.isEmpty()) && pageList.contains("ALL_PAGE")){
            System.out.println("Admin"+pageList.toString());
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            this.approvepoopupbind.show(hints);
        }else{
            DCIteratorBinding statData = null;
            statData = getDCIteratorBindings("SgsStatisticalDataVO1Iterator");
            oracle.jbo.Row[] statDataDatarows = statData.getAllRowsInRange();
            for (int i = 0; i < statDataDatarows.length; i++) {
                System.out.println(" StatSelectedRecord At Approve:: " + statDataDatarows[i].getAttribute("StatSelectedRecord"));
                System.out.println(" InputProvider At Approve:: " + statDataDatarows[i].getAttribute("InputProvider"));
                if (null != statDataDatarows[i].getAttribute("StatSelectedRecord") &&
                    statDataDatarows[i].getAttribute("StatSelectedRecord").equals("Yes")) {
                    
                        statDataDatarows[i].setAttribute("APPROVESTATUS", "Approved");
                    }
                
            } 
            
            executeBinding(SAVE_DATA);
            approvepoopupbind.hide();
        }
    }

    public void onStatisticalReject(ActionEvent actionEvent) {
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        this.rejectpopupbind.show(hints);
    }

    public void onStatisticalSelectAll(ValueChangeEvent valueChangeEvent) {
        //SgsStatisticalDataVO1Iterator
        System.out.println("Checkbox At Statistical :: " + valueChangeEvent.getNewValue());
        DCIteratorBinding statData = null;
        statData = getDCIteratorBindings("SgsStatisticalDataVO1Iterator");
        oracle.jbo.Row[] statDataDatarows = statData.getAllRowsInRange();
        if ((Boolean)valueChangeEvent.getNewValue()) {
            System.out.println("statDataDatarows Datarows:: " + statDataDatarows.length);
            for (int i = 0; i < statDataDatarows.length; i++) {
                System.out.println(" StatSelectedRecord :: " + statDataDatarows[i].getAttribute("StatSelectedRecord"));
                 statDataDatarows[i].setAttribute("StatSelectedRecord", "Yes");
                System.out.println(" geo1Datarows SELECTRECORDS :: " + statDataDatarows[i].getAttribute("StatSelectedRecord"));
            }               
        }else {
            System.out.println("geo1Datarows Datarows else:: " + statDataDatarows.length);
            for (int i = 0; i < statDataDatarows.length; i++) {
                System.out.println("StatSelectedRecord  :: " + statDataDatarows[i].getAttribute("StatSelectedRecord"));
                statDataDatarows[i].setAttribute("StatSelectedRecord", "No");
                System.out.println(" StatSelectedRecord rows :: " + statDataDatarows[i].getAttribute("StatSelectedRecord"));
               
            }
            
         
        }
        
                AdfFacesContext.getCurrentInstance().addPartialTarget(bindStatCheckboxSelect);
                AdfFacesContext.getCurrentInstance().addPartialTarget(onStatSelectAllColumn);
    }

    public void setBindStatCheckboxSelect(RichSelectBooleanCheckbox bindStatCheckboxSelect) {
        this.bindStatCheckboxSelect = bindStatCheckboxSelect;
    }

    public RichSelectBooleanCheckbox getBindStatCheckboxSelect() {
        return bindStatCheckboxSelect;
    }

    public void setOnStatSelectAllColumn(RichColumn onStatSelectAllColumn) {
        this.onStatSelectAllColumn = onStatSelectAllColumn;
    }

    public RichColumn getOnStatSelectAllColumn() {
        return onStatSelectAllColumn;
    }

    public void setApprovepoopupbind(RichPopup approvepoopupbind) {
        this.approvepoopupbind = approvepoopupbind;
    }

    public RichPopup getApprovepoopupbind() {
        return approvepoopupbind;
    }

    public void setRejectpopupbind(RichPopup rejectpopupbind) {
        this.rejectpopupbind = rejectpopupbind;
    }

    public RichPopup getRejectpopupbind() {
        return rejectpopupbind;
    }



    public void setRejectionCommentsBind(RichInputText rejectionCommentsBind) {
        this.rejectionCommentsBind = rejectionCommentsBind;
    }

    public RichInputText getRejectionCommentsBind() {
        return rejectionCommentsBind;
    }

    public void onRejectionReasonSave(ActionEvent actionEvent) {
        DCIteratorBinding statData = null;
        statData = getDCIteratorBindings("SgsStatisticalDataVO1Iterator");
        oracle.jbo.Row[] statDataDatarows = statData.getAllRowsInRange();
        for (int i = 0; i < statDataDatarows.length; i++) {
            System.out.println(" StatSelectedRecord At Reject:: " +
                               statDataDatarows[i].getAttribute("StatSelectedRecord"));
            System.out.println(" InputProvider At Reject:: " + statDataDatarows[i].getAttribute("InputProvider"));
            if (null != statDataDatarows[i].getAttribute("StatSelectedRecord") &&
                statDataDatarows[i].getAttribute("StatSelectedRecord").equals("Yes")) {
                if (null != rejectionReasonLOVBind.getValue()) {
                    String reason = (String) rejectionReasonLOVBind.getValue();
                    System.out.println("reason" + reason);
                    statDataDatarows[i].setAttribute("RejectedReason", reason);
                }
                if (null != rejectionCommentsBind.getValue()) {
                    String comments = (String) rejectionCommentsBind.getValue();
                    System.out.println("comments" + comments);
                    statDataDatarows[i].setAttribute("RejectionComments", comments);
                }
                
                statDataDatarows[i].setAttribute("APPROVESTATUS", "Rejected");
            }
        }
        executeBinding(SAVE_DATA);
        rejectionReasonLOVBind.setValue(null);
        rejectionCommentsBind.setValue(null);
        rejectpopupbind.hide();
    }

    public void setInputFileBind(RichInputFile inputFileBind) {
        this.inputFileBind = inputFileBind;
    }

    public RichInputFile getInputFileBind() {
        return inputFileBind;
    }

    public void onAdminEmilFileAttachment(ActionEvent actionEvent) {
        DCIteratorBinding statData = null;
        statData = getDCIteratorBindings("SgsStatisticalDataVO1Iterator");
        oracle.jbo.Row[] statDataDatarows = statData.getAllRowsInRange();
        for (int i = 0; i < statDataDatarows.length; i++) {
            System.out.println(" StatSelectedRecord At File Attachment:: " +
                               statDataDatarows[i].getAttribute("StatSelectedRecord"));
            System.out.println(" InputProvider At File Attachment:: " +
                               statDataDatarows[i].getAttribute("InputProvider"));
            if (null != statDataDatarows[i].getAttribute("StatSelectedRecord") &&
                statDataDatarows[i].getAttribute("StatSelectedRecord").equals("Yes")) {
                if (null != inputFileBind.getValue()) {
                    UploadedFile uploadedFile = (UploadedFile) inputFileBind.getValue();
                    if (null != uploadedFile.getFilename()) {
                        String fileName = (String) uploadedFile.getFilename();
                        System.out.println("fileName" + fileName);
                        statDataDatarows[i].setAttribute("EMAILATTACHMENT", fileName);
                        statDataDatarows[i].setAttribute("APPROVESTATUS", "Approved");
                    }

                }


            }
        }
        executeBinding(SAVE_DATA);
        inputFileBind.setValue(null);
        inputFileBind.resetValue();
        AdfFacesContext.getCurrentInstance().addPartialTarget(inputFileBind);
        approvepoopupbind.hide();
    }


    public void onFAFileAttachment(ActionEvent actionEvent) {
        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding faiter = (DCIteratorBinding) bindings.get("SgsFixedAssetsTxnVO1Iterator");
        ViewObject faVO = faiter.getViewObject();

        oracle.jbo.Row[] selectedRows = faVO.getFilteredRows("Selected", true);
        System.out.println("*****Selected rows****" + selectedRows.length);
        for (oracle.jbo.Row rw : selectedRows) {
            if (null != inputFileBindFA.getValue()) {
                UploadedFile uploadedFile = (UploadedFile) inputFileBindFA.getValue();
                if (null != uploadedFile.getFilename()) {
                    String fileName = (String) uploadedFile.getFilename();
                    System.out.println("fileName" + fileName);
                    rw.setAttribute("ATTACHMENT", fileName);
                    rw.setAttribute("Status", "Approved");
                }
            }


        }
        executeBinding(SAVE_DATA);
        inputFileBindFA.setValue(null);
        inputFileBindFA.resetValue();
        AdfFacesContext.getCurrentInstance().addPartialTarget(inputFileBindFA);
        approvePopupFABind.hide();

    }

    public void setRejectionReasonLOVBind(RichSelectOneChoice rejectionReasonLOVBind) {
        this.rejectionReasonLOVBind = rejectionReasonLOVBind;
    }

    public RichSelectOneChoice getRejectionReasonLOVBind() {
        return rejectionReasonLOVBind;
    }

    public void onStdRateFormSave(ActionEvent actionEvent) {
        // Add event code here...
        executeBinding(SAVE_DATA);
        String inputProvider=null;
        DCIteratorBinding dcIteratorbinding = getDCIteratorBindings("SgsStandardRateSetupVO1Iterator");
        Row row = dcIteratorbinding.getCurrentRow();
        System.out.println("Input provider :: "+row.getAttribute("INPUTPROVIDER"));
        ADFContext.getCurrent().getSessionScope().put("INPUTPROVIDER",row.getAttribute("INPUTPROVIDER"));
    }

    public void onStdRateRowSelection(ActionEvent actionEvent) {
        // Add event code here...
        DCIteratorBinding dcIteratorbinding = getDCIteratorBindings("SgsStandardRateSetupVO1Iterator");
        Row row = dcIteratorbinding.getCurrentRow();
        System.out.println("Input provider :: "+row.getAttribute("INPUTPROVIDER"));
        ADFContext.getCurrent().getSessionScope().put("INPUTPROVIDER",row.getAttribute("INPUTPROVIDER"));
    }
    
    public void onInvoiceSave(ActionEvent actionEvent) {
        System.out.println("inside onInvoiceSave");
//        DCIteratorBinding statData = null;
//        statData = getDCIteratorBindings("SgsStatisticalDataVO1Iterator");
//        oracle.jbo.Row[] statDataDatarows = statData.getAllRowsInRange();
//        for (int i = 0; i < statDataDatarows.length; i++) {
//            System.out.println(" StatSelectedRecord At File Attachment:: " +
//                               statDataDatarows[i].getAttribute("StatSelectedRecord"));
//            System.out.println(" InputProvider At File Attachment:: " +
//                               statDataDatarows[i].getAttribute("InputProvider"));
//            if (null != statDataDatarows[i].getAttribute("StatSelectedRecord") &&
//                statDataDatarows[i].getAttribute("StatSelectedRecord").equals("Yes")) {
//                if (null != inputFileBind.getValue()) {
//                    UploadedFile uploadedFile = (UploadedFile) inputFileBind.getValue();
//                    if (null != uploadedFile.getFilename()) {
//                        String fileName = (String) uploadedFile.getFilename();
//                        System.out.println("fileName" + fileName);
//                        statDataDatarows[i].setAttribute("EMAILATTACHMENT", fileName);
//                        statDataDatarows[i].setAttribute("APPROVESTATUS", "Approved");
//                    }
//
//                }
//
//
//            }
//        }
//        executeBinding(SAVE_DATA);
//        inputFileBind.setValue(null);
//        inputFileBind.resetValue();
//        AdfFacesContext.getCurrentInstance().addPartialTarget(inputFileBind);
//        approvepoopupbind.hide();
    }
    
    public void onInvoiceApprove(ActionEvent actionEvent) {
        // Add event code here...
        //
//        DCIteratorBinding dcIteratorbinding = getDCIteratorBindings("SgsIcInvoiceHeaderVO1Iterator");
//        Row row = dcIteratorbinding.getCurrentRow();
//        row.setAttribute("TransactionStatus", "Approved");
        

       DCIteratorBinding statData = getDCIteratorBindings("SgsIcInvoiceHeaderVO1Iterator");
        oracle.jbo.Row[] rows = statData.getAllRowsInRange();
        for (int i = 0; i < rows.length; i++) {
            System.out.println(" selectInvoiceRecord " + rows[i].getAttribute("selectInvoiceRecord"));
            if (null != rows[i].getAttribute("selectInvoiceRecord") &&
                rows[i].getAttribute("selectInvoiceRecord").equals("Yes")) {
                rows[i].setAttribute("TransactionStatus", "Approved");
            }
        }
        executeBinding(SAVE_DATA);
        invoiceApprovBind.hide();
    }
    
    public void onInvoiceReject(ActionEvent actionEvent) {
        // Add event code here...
//        DCIteratorBinding dcIteratorbinding = getDCIteratorBindings("SgsIcInvoiceHeaderVO1Iterator");
//        Row row = dcIteratorbinding.getCurrentRow();
//        row.setAttribute("TransactionStatus", "Rejected");
        
       DCIteratorBinding invoiceData = getDCIteratorBindings("SgsIcInvoiceHeaderVO1Iterator");
        oracle.jbo.Row[] rows = invoiceData.getAllRowsInRange();
        for (int i = 0; i < rows.length; i++) {
            System.out.println(" selectInvoiceRecord  " +rows[i].getAttribute("selectInvoiceRecord"));
            if (null != rows[i].getAttribute("selectInvoiceRecord") &&
                rows[i].getAttribute("selectInvoiceRecord").equals("Yes")) {
                rows[i].setAttribute("TransactionStatus", "Rejected");
            }
        }
        executeBinding(SAVE_DATA);
        invoiceRejectBind.hide();
    }
    
    public void onInvoiceConfirm(ActionEvent actionEvent) {
        // Add event code here...
//        DCIteratorBinding dcIteratorbinding = getDCIteratorBindings("SgsIcInvoiceHeaderVO1Iterator");
//        Row row = dcIteratorbinding.getCurrentRow();
//        row.setAttribute("TransactionStatus", "Confirmed for Invoicing");
        DCIteratorBinding invoiceData = getDCIteratorBindings("SgsIcInvoiceHeaderVO1Iterator");
         oracle.jbo.Row[] rows = invoiceData.getAllRowsInRange();
         for (int i = 0; i < rows.length; i++) {
             System.out.println(" selectInvoiceRecord  " +rows[i].getAttribute("selectInvoiceRecord"));
             if (null != rows[i].getAttribute("selectInvoiceRecord") &&
                 rows[i].getAttribute("selectInvoiceRecord").equals("Yes")) {
                 rows[i].setAttribute("TransactionStatus", "Confirmed for Invoicing");
                 SGSAppModuleImpl am = new SGSAppModuleImpl();
                 String voucherNumber = "PS_Voucher_"+am.getDBSequence1("SEQ_IC_INVOICE_HEADER_VOUCHER");
                 String invoiceNumber = "PS_Invoice_"+am.getDBSequence1("SEQ_IC_INVOICE_HEADER_INVOICE");
                 rows[i].setAttribute("ReferenceVoucherNum", voucherNumber);
                 rows[i].setAttribute("ReferenceInvoiceNum", invoiceNumber);
             }
         }
        
        executeBinding(SAVE_DATA);
        invoiceConfirmBind.hide();
    }
    public void onInvoiceApproveNo(ActionEvent actionEvent) {
        // Add event code here...
        invoiceApprovBind.hide();
    }
    public void onInvoiceRejectNo(ActionEvent actionEvent) {
        // Add event code here...
        invoiceRejectBind.hide();
    }
    public void onInvoiceConfirmNo(ActionEvent actionEvent) {
        // Add event code here...
        invoiceConfirmBind.hide();
    }

    public void setInvoiceApprovBind(RichPopup invoiceApprovBind) {
        this.invoiceApprovBind = invoiceApprovBind;
    }

    public RichPopup getInvoiceApprovBind() {
        return invoiceApprovBind;
    }
    
    
    public void setInvoiceRejectBind(RichPopup invoiceRejectBind) {
        this.invoiceRejectBind = invoiceRejectBind;
    }

    public RichPopup getInvoiceRejectBind() {
        return invoiceRejectBind;
    }
    
    public void setInvoiceConfirmBind(RichPopup invoiceConfirmBind) {
        this.invoiceConfirmBind = invoiceConfirmBind;
    }

    public RichPopup getInvoiceConfirmBind() {
        return invoiceConfirmBind;
    }

    public void setSubmitstatpopupbind(RichPopup submitstatpopupbind) {
        this.submitstatpopupbind = submitstatpopupbind;
    }

    public RichPopup getSubmitstatpopupbind() {
        return submitstatpopupbind;
    }

    public void onStatSubmitNo(ActionEvent actionEvent) {
        // Add event code here...
        submitstatpopupbind.hide();
    }

    public void setCreditMemoPopupBind(RichPopup creditMemoPopupBind) {
        this.creditMemoPopupBind = creditMemoPopupBind;
    }

    public RichPopup getCreditMemoPopupBind() {
        return creditMemoPopupBind;
    }

    public void creditMemoSubmitApprovals(ActionEvent actionEvent) {
        System.out.println("creditDateBindVal "+creditDateBindVal.getValue());
        System.out.println("percentageReversalBind "+percentageReversalBind.getValue());
       

        String reversalReason="NONE";


 
//            java.sql.Date creditDate = (java.sql.Date) creditDateBindVal.getValue();
//            System.out.println("creditDate 11"+creditDate);
//            DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
//            System.out.println("formatter 11"+formatter);
//            java.sql.Date dateCredit = (java.sql.Date) formatter.parse(creditDate.toString());
//            System.out.println("dateCredit 11"+dateCredit);
            
            

   
       DCIteratorBinding creditData = getDCIteratorBindings("SgsInvoiceCreditMemoVO1Iterator");
        oracle.jbo.Row[] row = creditData.getAllRowsInRange();
        for (int i = 0; i < row.length; i++) {
            System.out.println(" SelectCreditRecord At Approve:: " + row[i].getAttribute("SelectCreditRecord"));
//            if (null != row[i].getAttribute("SelectCreditRecord") &&
//                row[i].getAttribute("SelectCreditRecord").equals("Yes")) {   
                
                
                    System.out.println("Credit Date :: "+creditDateBindVal.getValue());
                    if(null != creditDateBindVal.getValue()){
                        java.util.Date utilDate = (java.util.Date) creditDateBindVal.getValue();
                        System.out.println("Util date in Java : " + utilDate); 
                        // contains only date information without time 
                        // java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                        DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
                        System.out.println("formatter 11"+formatter);
                        java.sql.Date dateCredit = new java.sql.Date(utilDate.getTime());
                        //java.sql.Date dateCredit = (java.sql.Date) formatter.parse(utilDate.toString());
                        System.out.println("dateCredit 11"+dateCredit);

                      //  row[i].setAttribute("Period",dateCredit);
                        System.out.println("Period ::"+row[i].getAttribute("Period"));
                    }
                    
//                     if(null != completeReversalBind.getValue()){
//                         row[i].setAttribute("InvoiceAmount", completeReversalBind.getValue());
//                         System.out.println("InvoiceAmount ::"+row[i].getAttribute("InvoiceAmount"));
//                     }
                    
                     if(null != percentageReversalBind.getValue()){
                        // if(null != completeReversalBind.getValue()){
                             String  str= (String)percentageReversalBind.getValue();
                             double reversalPerc = Double.parseDouble(str);
                             System.out.println("reversalPerc ::"+reversalPerc);
                             //String  str1= (String)completeReversalBind.getValue();
                           //  double invoiceAmount = Double.parseDouble(str1);
                          Object invAmt =   row[i].getAttribute("InvoiceAmount");
                          double invAmt2 = new Double(invAmt.toString());
                             System.out.println("invoiceAmount ::"+invAmt2);
                          
                        // double time = 200.3456;
                         DecimalFormat df = new DecimalFormat("#.##");      
                       double invoiceAmount = Double.valueOf(df.format(invAmt2));
                          
                             double reversalAmt = (Double)((reversalPerc / 100)*(invoiceAmount));
                             System.out.println("reversalAmt ::"+reversalAmt);
                             double revAmt = Double.valueOf(df.format(reversalAmt));
                             
                             row[i].setAttribute("ReversalAmount",revAmt);
                             System.out.println("ReversalAmount ::"+row[i].getAttribute("ReversalAmount"));
                        
                             row[i].setAttribute("PERCENTAGEREVERSAL",reversalPerc);
                            
                         
                      //   }
                        
                     }
                        
                    BindingContainer bc = this.getBindingsCont();
                    JUCtrlListBinding list = (JUCtrlListBinding) bc.get("ReversalReasonLOVVO1");
                    String selectedRow = (String) list.getSelectedValue();
                    
                    System.out.println("selectedRow 00"+selectedRow);
                    
                    if(null != selectedRow){
                        String type="REVERSAL_REASON";
                       reversalReason= getLookupCode(selectedRow, type);
                    }
                    //        if (null != selectedRow) {
                    //            reversalReason = (String) selectedRow.getAttribute("LOOKUPCODE");
                    //        }
                    System.out.println("reversalReason 11"+reversalReason);
                     
                     if(null != reversalReason){
                         //row[i].setAttribute("REVERSALREASON", reversalReasonLovBind.getValue());
                         row[i].setAttribute("REVERSALREASON", reversalReason);
                         System.out.println("REVERSALREASON ::"+row[i].getAttribute("REVERSALREASON"));
                     }
            
            
            
            BindingContainer bc1 = this.getBindingsCont();
            JUCtrlListBinding list1 = (JUCtrlListBinding) bc1.get("ReversalTypeLOVVO1");
            String selectedRow1 = (String) list1.getSelectedValue();
            
            System.out.println("selectedRow1 00"+selectedRow1);
            String reversalType="NONE";
            if(null != selectedRow1){
                String type="REVERSAL_TYPE";
               reversalType= getLookupCode(selectedRow1, type);
            }
            //        if (null != selectedRow) {
            //            reversalReason = (String) selectedRow.getAttribute("LOOKUPCODE");
            //        }
            System.out.println("reversalType 11"+reversalType);
             
             if(null != reversalType){
                 //row[i].setAttribute("REVERSALREASON", reversalReasonLovBind.getValue());
                 row[i].setAttribute("REVERSALTYPE", reversalType);
                 System.out.println("reversalType ::"+row[i].getAttribute("REVERSALTYPE"));
             }
            
            row[i].setAttribute("STATUS", "New");
                
              //  }
        }
       
      
        this.creditDateBindVal.setValue(null);
        this.percentageReversalBind.setValue(null);
        //this.completeReversalBind.setValue(null);
        //this.reversalReasonLovBind.setValue(null);
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(creditDateBindVal);
        AdfFacesContext.getCurrentInstance().addPartialTarget(percentageReversalBind);
     //   AdfFacesContext.getCurrentInstance().addPartialTarget(completeReversalBind);
       // AdfFacesContext.getCurrentInstance().addPartialTarget(reversalReasonLovBind);
       executeBinding(SAVE_DATA);
      // invoicecreditmemobindpopup.hide();
    }

    public void onCreditMemoClose(ActionEvent actionEvent) {
        // Add event code here...
        creditMemoPopupBind.hide();
    }

    public void setCreditDateBind(RichInputDate creditDateBind) {
        this.creditDateBind = creditDateBind;
    }

    public RichInputDate getCreditDateBind() {
        return creditDateBind;
    }

    public void setPercentageReversalBind(RichInputText percentageReversalBind) {
        this.percentageReversalBind = percentageReversalBind;
    }

    public RichInputText getPercentageReversalBind() {
        return percentageReversalBind;
    }

//    public void setCompleteReversalBind(RichInputText completeReversalBind) {
//        this.completeReversalBind = completeReversalBind;
//    }
//
//    public RichInputText getCompleteReversalBind() {
//        return completeReversalBind;
//    }

    public void setReversalReasonLovBind(RichSelectOneChoice reversalReasonLovBind) {
        this.reversalReasonLovBind = reversalReasonLovBind;
    }

    public RichSelectOneChoice getReversalReasonLovBind() {
        return reversalReasonLovBind;
    }

    public void setCreditDateBindVal(RichInputDate creditDateBindVal) {
        this.creditDateBindVal = creditDateBindVal;
    }

    public RichInputDate getCreditDateBindVal() {
        return creditDateBindVal;
    }

    public void onCreditMemoSelectAll(ValueChangeEvent valueChangeEvent) {
        System.out.println("Checkbox At Credit Memo :: " + valueChangeEvent.getNewValue());
       DCIteratorBinding  invoiceCreditData = getDCIteratorBindings("SgsInvoiceCreditMemoVO1Iterator");
        oracle.jbo.Row[] invoiceCreditDatarows = invoiceCreditData.getAllRowsInRange();
        if ((Boolean)valueChangeEvent.getNewValue()) {
            System.out.println("invoiceCreditDatarows:: " + invoiceCreditDatarows.length);
            for (int i = 0; i < invoiceCreditDatarows.length; i++) {
                System.out.println("invoice  :: " + invoiceCreditDatarows[i].getAttribute("SelectCreditRecord"));
                 invoiceCreditDatarows[i].setAttribute("SelectCreditRecord", "Yes");
                System.out.println(" SelectCreditRecord :: " + invoiceCreditDatarows[i].getAttribute("SelectCreditRecord"));
            }               
        }else {
            System.out.println("invoices Datarows else:: " + invoiceCreditDatarows.length);
            for (int i = 0; i < invoiceCreditDatarows.length; i++) {
                System.out.println("invoice  :: " + invoiceCreditDatarows[i].getAttribute("SelectCreditRecord"));
                invoiceCreditDatarows[i].setAttribute("SelectCreditRecord", "No");
                System.out.println("  SelectCreditRecord :: " + invoiceCreditDatarows[i].getAttribute("SelectCreditRecord"));
            }
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(selectCreditRecordBind);
        AdfFacesContext.getCurrentInstance().addPartialTarget(selectRecordColBind);
    }

    public void setSelectCreditRecordBind(RichSelectBooleanCheckbox selectCreditRecordBind) {
        this.selectCreditRecordBind = selectCreditRecordBind;
    }

    public RichSelectBooleanCheckbox getSelectCreditRecordBind() {
        return selectCreditRecordBind;
    }

    public void setSelectRecordColBind(RichColumn selectRecordColBind) {
        this.selectRecordColBind = selectRecordColBind;
    }

    public RichColumn getSelectRecordColBind() {
        return selectRecordColBind;
    }

    public void onCreditMemoSave(ActionEvent actionEvent) {
        // Add event code here...
        executeBinding(SAVE_DATA);
        ADFUtils.saveNotifier();
    }

    public void onCreduitMemoInvoke(ActionEvent actionEvent) {
        executeBinding("CreateInsertCredit");
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        this.creditMemoPopupBind.show(hints);
        
    }

    public void onCreditMemosClose(ActionEvent actionEvent) {
        // Add event code here...
        invoicecreditmemobindpopup.hide();
    }

    public void setInvoicecreditmemobindpopup(RichPopup invoicecreditmemobindpopup) {
        this.invoicecreditmemobindpopup = invoicecreditmemobindpopup;
    }

    public RichPopup getInvoicecreditmemobindpopup() {
        return invoicecreditmemobindpopup;
    }
    
    
    public void downloadFileListener(FacesContext facesContext, OutputStream outputStream) throws IOException 
        {
        String filePath1 = ADFUtils.getPath();
        System.out.println("File Path :: "+filePath1);
        if(filePath1.equalsIgnoreCase("NOPATH")){}else{
            //"C://Users//raja//template//TPA_Master.xlsx"
        File filed = new File(filePath1+"TPA_Master.xlsx"); 
        FileInputStream fis; byte[] b; 
        try 
        { 
        fis = new FileInputStream(filed); int n; while ((n = fis.available()) > 0) 
        { b = new byte[n]; int result = fis.read(b); outputStream.write(b, 0, b.length); 
        if (result == -1) break; } } catch (IOException e) { e.printStackTrace(); } outputStream.flush(); 
            
        }
    }

    public void onInvoiceSelectAll(ValueChangeEvent valueChangeEvent) {
        System.out.println("Checkbox At Invoice DB :: " + valueChangeEvent.getNewValue());
        DCIteratorBinding  invoiceDBData = getDCIteratorBindings("SgsIcInvoiceHeaderVO1Iterator");
        oracle.jbo.Row[] invoiceDBDatarows = invoiceDBData.getAllRowsInRange();
        if ((Boolean)valueChangeEvent.getNewValue()) {
            System.out.println("invoiceDBDatarows:: " + invoiceDBDatarows.length);
            for (int i = 0; i < invoiceDBDatarows.length; i++) {
                System.out.println("invoiceDBDatarows  :: " + invoiceDBDatarows[i].getAttribute("selectInvoiceRecord"));
                 invoiceDBDatarows[i].setAttribute("selectInvoiceRecord", "Yes");
                System.out.println(" selectInvoiceRecord :: " + invoiceDBDatarows[i].getAttribute("selectInvoiceRecord"));
            }               
        }else {
            System.out.println("invoices Datarows else:: " + invoiceDBDatarows.length);
            for (int i = 0; i < invoiceDBDatarows.length; i++) {
                System.out.println("invoiceDBDatarows  :: " + invoiceDBDatarows[i].getAttribute("selectInvoiceRecord"));
                invoiceDBDatarows[i].setAttribute("selectInvoiceRecord", "No");
                System.out.println("  selectInvoiceRecord :: " + invoiceDBDatarows[i].getAttribute("selectInvoiceRecord"));
            }
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(selectcolInvoiceDBBind);
        AdfFacesContext.getCurrentInstance().addPartialTarget(selectcheckInvoiceBind);
    }

    public void setSelectcolInvoiceDBBind(RichColumn selectcolInvoiceDBBind) {
        this.selectcolInvoiceDBBind = selectcolInvoiceDBBind;
    }

    public RichColumn getSelectcolInvoiceDBBind() {
        return selectcolInvoiceDBBind;
    }

    public void setSelectcheckInvoiceBind(RichSelectBooleanCheckbox selectcheckInvoiceBind) {
        this.selectcheckInvoiceBind = selectcheckInvoiceBind;
    }

    public RichSelectBooleanCheckbox getSelectcheckInvoiceBind() {
        return selectcheckInvoiceBind;
    }

    public void onCreditTransactionActionButton(ActionEvent actionEvent) {

        DCIteratorBinding invoiceData = getDCIteratorBindings("SgsIcInvoiceHeaderVO1Iterator");

                    DCIteratorBinding creditData = getDCIteratorBindings("SgsInvoiceCreditMemoVO1Iterator");

                  oracle.jbo.Row[] invoiceDatarows = invoiceData.getAllRowsInRange();
                  
                  System.out.println("invoiceDatarows:: "+invoiceDatarows.length);
                  CommonUtils util= new CommonUtils();
                  Object user= (Object)util.getSessionScopeValue("_username").toString();

                  for (int i = 0; i < invoiceDatarows.length; i++) {
                      if (null != invoiceDatarows[i].getAttribute("selectInvoiceRecord") &&
                          invoiceDatarows[i].getAttribute("selectInvoiceRecord").equals("Yes")) { 
                              executeBinding("CreateInsertCredit");
                              Row row = creditData.getCurrentRow();
                              
                              
                              
//                              String sDate1=(String)invoiceDatarows[i].getAttribute("Period");
//                              System.out.println("sDate1:: "+sDate1);
//                              SimpleDateFormat sdf2 = new SimpleDateFormat("Mmm dd yyyy hh:mmss");
//                              System.out.println("sdf2:: "+sdf2);
//                try {
//                    System.out.println("date2:: "+sDate1);
//                    java.util.Date date2 = sdf2.parse(sDate1);
//                    System.out.println("date2:: "+date2);
//                } catch (ParseException e) {
//                }
//                //                              Date parsedDate = sdf.parse(date);
////                              SimpleDateFormat print = new SimpleDateFormat("Mmm dd yyyy hh:mmss");
////                              System.out.println(print.format(parsedDate));
//                              
//                        
//                try {
//                    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
//                    System.out.println("sdf1:: "+sdf1);
//                    java.util.Date date = sdf1.parse(sDate1);
//                    System.out.println("date:: "+date);
//                    java.sql.Date date1 = new java.sql.Date(date.getTime()); 
//                    //java.sql.Date date1 = new java.sql.Date((new SimpleDateFormat("dd-MMM-yyyy").parse(sDate1));
//                    System.out.println(sDate1 + "\t" + date1);
//                    row.setAttribute("Period",date1);
//                    System.out.println("Period :: "+row.getAttribute("Period"));
//                } catch (ParseException pe) {
//                    // TODO: Add catch code
//                    pe.printStackTrace();
//                } 
                              row.setAttribute("InvoiceSeqNo" ,invoiceDatarows[i].getAttribute("InvoiceSeqNo"));
                              row.setAttribute("Period",invoiceDatarows[i].getAttribute("Period"));
                              row.setAttribute("TransactionCategory", invoiceDatarows[i].getAttribute("TransactionCategory"));
                              row.setAttribute("PsftVoucherRef",invoiceDatarows[i].getAttribute("ReferenceVoucherNum"));
                              row.setAttribute("PsftInvoiceRef",invoiceDatarows[i].getAttribute("ReferenceInvoiceNum"));      
                              //row.setAttribute("NatureOfExpense",null);
                              row.setAttribute("FromBu",      invoiceDatarows[i].getAttribute("SourceBu"));
                              row.setAttribute("ToBu",invoiceDatarows[i].getAttribute("TargetBu"));
                              row.setAttribute("InvoiceAmount",invoiceDatarows[i].getAttribute("ALLOCATEDHEADERAMOUNT"));       
                              //row.setAttribute("ReversalAmount",null);      
                              row.setAttribute("InputProvider",invoiceDatarows[i].getAttribute("InputProvider"));     
                              row.setAttribute("CreatedDate",invoiceDatarows[i].getAttribute("CreatedDate"));
                              row.setAttribute("CreatedBy",invoiceDatarows[i].getAttribute("CreatedBy"));
                              row.setAttribute("UpdatedDate",invoiceDatarows[i].getAttribute("UpdatedDate"));
                              row.setAttribute("UpdatedBy",invoiceDatarows[i].getAttribute("UpdatedBy"));
                              row.setAttribute("REVERSALREASON",invoiceDatarows[i].getAttribute("REVERSALREASON"));
                          }
                  }
        executeBinding(SAVE_DATA);
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        this.invoicecreditmemobindpopup.show(hints);
        
    }
    
    
    public String getLookupCode(String reason, String lookupType){
        //REVERSAL_REASON
        String revReason = null;
        String queryString =
        "SELECT LOOKUP_CODE from SGS_LOOKUP_TABLE WHERE MEANING='" + reason + "' AND LOOKUP_TYPE='" + lookupType + "' AND ENABLED='Y'";
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
                revReason= (String) rs.getString(1);
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
        
        return revReason;
    }

    public void onCreditPopupClose(ActionEvent actionEvent) {
        // Add event code here...
        invoicecreditmemobindpopup.hide();
    }

    public void onSettlementTypeVL(ValueChangeEvent valueChangeEvent) {
        System.out.println("Settlement Type:: "+valueChangeEvent.getNewValue());
    }

    public void onCompleteReversalChechBox(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        System.out.println(" Check At Complete Reversal :: "+(Boolean)valueChangeEvent.getNewValue());
        if((Boolean)valueChangeEvent.getNewValue()){
           
           // this.chechCheckBox =true;
            percentageReversalBind.setVisible(true);
            percentageGroupBind.setVisible(true);
        }else{
           
         //   this.chechCheckBox =false;
            percentageReversalBind.setVisible(false);
            percentageGroupBind.setVisible(false);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(percentageReversalBind);
        AdfFacesContext.getCurrentInstance().addPartialTarget(percentageGroupBind);
    }

    public void setChechCheckBox(Boolean chechCheckBox) {
        this.chechCheckBox = chechCheckBox;
    }

    public Boolean getChechCheckBox() {
        return chechCheckBox;
    }

    public void setPercentageGroupBind(RichPanelGroupLayout percentageGroupBind) {
        this.percentageGroupBind = percentageGroupBind;
    }

    public RichPanelGroupLayout getPercentageGroupBind() {
        return percentageGroupBind;
    }

    public void onReversalTypeVL(ValueChangeEvent valueChangeEvent) {
        System.out.println("Reversal Type :: "+valueChangeEvent.getNewValue());
        String reverseTypeVal= (String)valueChangeEvent.getNewValue();
        String type="REVERSAL_TYPE";
        if(null != reverseTypeVal && !(reverseTypeVal.equalsIgnoreCase(""))){
            
      
        String  reversalType= getLookupCode(reverseTypeVal, type);
        
        if(null != valueChangeEvent.getNewValue() && reversalType.equalsIgnoreCase("PERCENTAGE_REVERSAL")){
           
           // this.chechCheckBox =true;
            percentageReversalBind.setVisible(true);
            percentageGroupBind.setVisible(true);
        }else{
           
         //   this.chechCheckBox =false;
            percentageReversalBind.setVisible(false);
            percentageGroupBind.setVisible(false);
        }
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(percentageReversalBind);
        AdfFacesContext.getCurrentInstance().addPartialTarget(percentageGroupBind);
    }
    
    public void onConfirmingInvoiceEvent(ActionEvent actionEvent) {
        DCIteratorBinding dcIteratorbinding = getDCIteratorBindings("SgsFixedAssetsTxnVO1Iterator");
        Row row = dcIteratorbinding.getCurrentRow();
        row.setAttribute("Status","Confirmed for Invoicing");
    }
    
    
    public void importFromTxn(ActionEvent actionEvent) {
            // Add event code here...
            System.out.println("inside Import from Transaction**********************");
            Connection conn = null;
            PreparedStatement pst = null;
            
            try {
                
                conn = getDBConnection();
                String SPsql = "EXEC USP_SCN_FA_IMPORT"; // for stored proc
                //Connection con = SmartPoolFactory.getConnection();   // java.sql.Connection
                PreparedStatement ps = conn.prepareStatement(SPsql);
                
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
                   String connectionUrl = "jdbc:sqlserver://localhost;instanceName=SQLEXPRESS;databasename=SGS_NEW;integratedSecurity=true;";
                    conn = DriverManager.getConnection(connectionUrl);
    //            conn = DriverManager.getConnection("jdbc:sqlserver://ASBCOLPS02:1433;databaseName=DEVINTER","EYUser","Ey@123");

            } catch (SQLException sqle) {
                // TODO: Add catch code
                sqle.printStackTrace();
            } finally {

            }
                   
             return conn;   
            }
        
        
    public void selectAllCheckboxValueChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        boolean isSelected = ((Boolean) valueChangeEvent.getNewValue()).booleanValue();
        System.out.println("*****is Selected***" + isSelected);
        //           String voName = (String)AdfFacesContext.getCurrentInstance().getPageFlowScope().get("voName");
        //           String iteratorName = voName + "Iterator";
        //           System.out.println("******"+iteratorName);
        String SelectedAttribute = "Selected";
        DCBindingContainer bindingContainer =
            (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dciter = bindingContainer.findIteratorBinding("SgsFixedAssetsTxnVO1Iterator");
        ViewObject vo = dciter.getViewObject();
        oracle.jbo.Row row = null;
        vo.reset();
        RowSetIterator rs = vo.createRowSetIterator(null);
        rs.reset();
        while (rs.hasNext()) {
            row = rs.next();
            if (isSelected) {
                row.setAttribute(SelectedAttribute, "true");
                System.out.println("****selected***" + row.getAttribute(SelectedAttribute));
            } else {
                row.setAttribute(SelectedAttribute, "false");
                System.out.println("****Unselected***" + row.getAttribute(SelectedAttribute));
            }
        }
        rs.closeRowSetIterator();
        //Refresh the table
        AdfFacesContext.getCurrentInstance().addPartialTarget(valueChangeEvent.getComponent()
                                                                              .getParent()
                                                                              .getParent());

    }



    public void onAddFAButton(ActionEvent actionEvent) {
        // Add event code here...
    }

    public void setInputFileBindFA(RichInputFile inputFileBindFA) {
        this.inputFileBindFA = inputFileBindFA;
    }

    public RichInputFile getInputFileBindFA() {
        return inputFileBindFA;
    }

    public void setApprovePopupFABind(RichPopup approvePopupFABind) {
        this.approvePopupFABind = approvePopupFABind;
    }

    public RichPopup getApprovePopupFABind() {
        return approvePopupFABind;
    }
}

