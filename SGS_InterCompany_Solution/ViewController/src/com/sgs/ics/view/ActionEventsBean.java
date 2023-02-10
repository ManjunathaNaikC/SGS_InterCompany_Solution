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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectManyChoice;
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
        viewImpl.setWhereClause("MONTH(VALIDITY_TILL) = '"+ month[gcal.get(Calendar.MONTH)]+"'  and YEAR(VALIDITY_TILL) ='"+gcal.get(Calendar.YEAR) +"'");
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

                    DCIteratorBinding docs = getDCIteratorBindings("SgsTpaDocTypeVOIterator");
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
        DCIteratorBinding imageIter = (DCIteratorBinding) bindingContainer.get("SgsTpaDocTypeVOIterator");
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
}

