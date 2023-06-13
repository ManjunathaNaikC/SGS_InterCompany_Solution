package com.sgs.ics.view;

import com.sgs.ics.model.bc.am.SGSAppModuleImpl;
import com.sgs.ics.model.bc.commonutils.CommonUtils;
import com.sgs.ics.ui.utils.ADFUtils;

import com.sgs.ics.ui.utils.JSFUtil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.net.MalformedURLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.time.Month;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import java.util.Objects;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.naming.NamingException;

import javax.swing.text.View;

import oracle.adf.model.AttributeBinding;
import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelTabbed;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailItem;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;


import oracle.adf.view.rich.event.PopupCanceledEvent;
import oracle.adf.view.rich.render.ClientEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewObject;
import oracle.jbo.server.ViewObjectImpl;
import oracle.jbo.uicli.binding.JUCtrlListBinding;

import oracle.jbo.uicli.binding.JUCtrlValueBinding;

import org.apache.myfaces.trinidad.model.UploadedFile;


public class ActionEventsBean {

    private static final ADFLogger LOG = ADFLogger.createADFLogger(ActionEventsBean.class);
    private SGSAppModuleImpl am = new SGSAppModuleImpl();
    protected String SAVE_DATA = "Commit";
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
    private RichSelectOneChoice reversalReasonLovBind;
    private RichInputDate creditDateBindVal;
    private RichSelectBooleanCheckbox selectCreditRecordBind;
    private RichColumn selectRecordColBind;
    private RichPopup invoicecreditmemobindpopup;
    private RichColumn selectcolInvoiceDBBind;
    private RichSelectBooleanCheckbox selectcheckInvoiceBind;
    private RichInputText inputTransactionAmount;
    private RichInputText inputBankCharge;
    private RichTable tablePaymentRecords;
    private Boolean chechCheckBox = false;
    private RichPanelGroupLayout percentageGroupBind;
    private RichInputFile inputFileBindFA;
    private RichPopup approvePopupFABind;
    private String balanceOutputText;
    private RichOutputText balanceOutputText1;
    private RichTable tablePaymentRecords1;

    private RichSelectBooleanCheckbox bindPaymentCheck;
    private RichSelectBooleanCheckbox bindReceiptCheck;
    private RichInputText bindPsInvoiceNumber;
    private RichInputText bindPsVoucherNumber;
    private RichSelectOneChoice purposeCodeLov;
    private RichInputDate paymentDate;
    private RichSelectOneChoice payBankName;
    private RichSelectOneChoice payBankCode;
    private RichSelectOneChoice paymentMethod;
    private RichSelectOneChoice paymentCurrency;
    private RichSelectOneChoice recPurposeCode;
    private RichInputDate receiptDate;
    private RichSelectOneChoice recBankName;
    private RichSelectOneChoice recBankCode;
    private RichSelectOneChoice recCurrency;
    private RichPopup invoiceConfirmProcessPopup;
    private RichInputText paymentTxnNum;
    private RichInputText recTxnRefNum;

    private RichOutputText txnAmtOnReceipt;
    private Double totalSettlementAmount;
    private RichOutputText totalSettleOutput;


    private RichPopup invoiceApproveYesNoPopup;
    private RichInputText bindPaymentRefNum;
    private RichPopup faRejectBind;
    private RichTable taxRateAppWhtBind;
    private RichColumn bindTaxRateIdenWht;
    private RichColumn taxRateIdenColumnBind;
    private RichColumn docTypeBind;
    private RichColumn dueDateBind;
    private boolean trcReadOnly1;
    private boolean taxRate;
    private boolean taxRateNoDoc;
    private RichPopup nettingovertidepopup;
    private RichInputText icAllowableLimitBind;
    private RichInputText ccAllowableLimit;
    private RichInputText nettingRemarksBind;
    private RichColumn oncolumnSelectRecord;
    private RichSelectBooleanCheckbox nettingselectcheckbox;
    private RichSelectBooleanCheckbox onNettingSelectAll;
    private RichInputText netIcAllLimCalBind;
    private RichColumn onIcRecvColumnSelectRecord;
    private RichSelectBooleanCheckbox netIcRecvselectcheckbox;
    private RichColumn onIcPayColumnSelectRecord;
    private RichSelectBooleanCheckbox netIcPayselectcheckbox;
    private RichColumn onNetArColRecColumnSelectRecord;
    private RichSelectBooleanCheckbox netNetArColRecselectcheckbox;
    private RichColumn onNetArColPayColumnSelectRecord;
    private RichSelectBooleanCheckbox netNetArColPayselectcheckbox;
    private RichInputText netCcAllLimCal;
    private RichInputText otherCommentBind;
    private RichTable netHeaderTableData;
    private RichPopup submitstatpopupattachbind;
    private RichSelectBooleanCheckbox zeroBalanceCheckBind;


    public void setTotalSettlementAmount(Double totalSettlementAmount) {
        this.totalSettlementAmount = totalSettlementAmount;
    }

    public Double getTotalSettlementAmount() {
        return totalSettlementAmount;
    }

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

    public BindingContainer getBindings() {
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
        executeBinding("DeleteIdenCombo");
        executeBinding(SAVE_DATA);
        ADFUtils.deleteNotifier();
    }

    public void onTargetComboDelete(ActionEvent actionEvent) {
        executeBinding("DeleteTargetCombo");
        executeBinding(SAVE_DATA);
        ADFUtils.deleteNotifier();
    }

    public void onCrossChargeComboDelete(ActionEvent actionEvent) {
        executeBinding("DeleteCrossCharge");
        executeBinding(SAVE_DATA);
        ADFUtils.deleteNotifier();
    }

    public void onIdenComboCreate(ActionEvent actionEvent) {
        DCIteratorBinding dcIteratorbinding = getDCIteratorBindings("SgsCostIdentificationRuleVOIterator");
        Row row = dcIteratorbinding.getCurrentRow();
        String costIdentificationId = (String) row.getAttribute("CostIdentificationId");

        String costIdentificationName = (String) row.getAttribute("CostIdentificationName");

        if (null != costIdentificationName && !(costIdentificationName.isEmpty())) {
            executeBinding("CreateInsertIdenCombo");
        } else {
            createNotifier("Please Create Cost Identification Header Record.");
        }
    }


    public void createNotifier(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        if (null == message) {
            message = "Before creating child record, Please create header record.";
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
        String costIdentificationName = (String) row.getAttribute("CostIdentificationName");
        if (null != costIdentificationName && !(costIdentificationName.isEmpty())) {
            executeBinding("CreateInsertTargetCombo");
        } else {
            createNotifier("Please Create Cost Identification Header Record.");
        }
    }

    public void onCrossChargeCreate(ActionEvent actionEvent) {
        DCIteratorBinding dcIteratorbinding = getDCIteratorBindings("SgsCostIdentificationRuleVOIterator");
        Row row = dcIteratorbinding.getCurrentRow();
        String costIdentificationId = (String) row.getAttribute("CostIdentificationId");
        String costIdentificationName = (String) row.getAttribute("CostIdentificationName");
        if (null != costIdentificationName && !(costIdentificationName.isEmpty())) {
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


        for (Object o : str) {
            natureOfExpense += "," + o;
        }

        String natureOfExpenseString = natureOfExpense.toString();

        ViewObjectImpl viewImpl = null;
        viewImpl = (ViewObjectImpl) getDCIteratorBindings("SgsCostIdentificationRuleVOIterator").getViewObject();
        viewImpl.setFullSqlMode(ViewObjectImpl.FULLSQL_MODE_AUGMENTATION);
        viewImpl.setWhereClause(" NATURE_OF_EXPENSE IN ( " + natureOfExpenseString + ")");

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
        for (Object o : str) {
            natureOfExpense += "," + o;
        }
        String natureOfExpenseString = natureOfExpense.toString();


        String queryString =
            "select LOOKUP_CODE FROM SGS_LOOKUP_TABLE WHERE LOOKUP_TYPE='NATURE_OF_EXPENSE' AND LOOKUP_ID IN (" +
            natureOfExpenseString + ")";
        String natureOfExpenseLookupCode = "'" + "none" + "'";
        Connection conn = null;
        PreparedStatement pst = null;

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
        String inputProvider = null;
        DCIteratorBinding dcIteratorbinding = getDCIteratorBindings("SgsStandardRateSetupVO1Iterator");
        Row row = dcIteratorbinding.getCurrentRow();
        ADFContext.getCurrent()
                  .getSessionScope()
                  .put("INPUTPROVIDER", row.getAttribute("INPUTPROVIDER"));
        executeBinding(SAVE_DATA);
        ADFUtils.saveNotifier();
    }


    public void onGSTDelete(DialogEvent dialogEvent) {
        executeBinding("DeleteGst");
        executeBinding(SAVE_DATA);
        ADFUtils.deleteNotifier();
    }


    public void genericSave(ActionEvent actionEvent) {
        executeBinding(SAVE_DATA);
        ADFUtils.saveNotifier();
    }

    public void onGSTChildDelete(DialogEvent dialogEvent) {
        executeBinding("DeleteGstChild");
        executeBinding(SAVE_DATA);
        ADFUtils.deleteNotifier();
    }

    public void onBUDelete(DialogEvent dialogEvent) {
        executeBinding("DeleteBU");
        executeBinding(SAVE_DATA);
        ADFUtils.deleteNotifier();
    }


    public void onTdsWhtDelete(DialogEvent dialogEvent) {
        executeBinding("DeleteTdsWht");
        executeBinding(SAVE_DATA);
        ADFUtils.deleteNotifier();
    }

    public void onTdsWhtSave(ActionEvent actionEvent) {
        executeBinding(SAVE_DATA);
        ADFUtils.saveNotifier();

    }

    public void onWhtIdenDelete(DialogEvent dialogEvent) {
        executeBinding("DeleteWhtIden");
        executeBinding(SAVE_DATA);
        ADFUtils.deleteNotifier();
    }

    public void onWhtRateDelete(DialogEvent dialogEvent) {
        executeBinding("DeleteWhtRate");
        executeBinding(SAVE_DATA);
        ADFUtils.deleteNotifier();
    }

    public void onVatDelete(DialogEvent dialogEvent) {
        executeBinding("DeleteVat");
        executeBinding(SAVE_DATA);
        ADFUtils.deleteNotifier();
    }

    public void onVatTaxDelete(DialogEvent dialogEvent) {
        executeBinding("DeleteVatTax");
        executeBinding(SAVE_DATA);
        ADFUtils.deleteNotifier();
    }

    public void onStandardRateDelete(DialogEvent dialogEvent) {
        executeBinding("DeleteStandardRate");
        executeBinding(SAVE_DATA);
        ADFUtils.deleteNotifier();
    }

    public void onCopyFromPreviousMonthActionEvent(ActionEvent actionEvent) {
        GregorianCalendar gcal = new GregorianCalendar();
        gcal.add((GregorianCalendar.MONTH), -1);
        String month[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };


        ViewObjectImpl viewImpl = null;
        viewImpl = (ViewObjectImpl) getDCIteratorBindings("SgsStatisticalPreviousMonthVO1Iterator").getViewObject();
        viewImpl.setFullSqlMode(ViewObjectImpl.FULLSQL_MODE_AUGMENTATION);
        viewImpl.setWhereClause("MONTH(TRANSACTION_PERIOD) = '" + month[gcal.get(Calendar.MONTH)] +
                                "'  AND YEAR(TRANSACTION_PERIOD) ='" + gcal.get(Calendar.YEAR) + "'");
        viewImpl.executeQuery();

        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        this.statisticspopupbind.show(hints);


    }


    public void setStatisticspopupbind(RichPopup statisticspopupbind) {
        this.statisticspopupbind = statisticspopupbind;
    }

    public RichPopup getStatisticspopupbind() {
        return statisticspopupbind;
    }

    public void onStatDataUpdate(ActionEvent actionEvent) {

        DCIteratorBinding previousMonthData = null;
        DCIteratorBinding statisticalData = getDCIteratorBindings("SgsStatisticalDataVO1Iterator");
        try {
            previousMonthData = getDCIteratorBindings("SgsStatisticalPreviousMonthVO1Iterator");
            oracle.jbo.Row[] previousMonthDatarows = previousMonthData.getAllRowsInRange();
            CommonUtils util = new CommonUtils();
            Object user = (Object) util.getSessionScopeValue("_username").toString();

            for (int i = 0; i < previousMonthDatarows.length; i++) {

                executeBinding("CreateStatisticalData");

                Row row = statisticalData.getCurrentRow();
                row.setAttribute("StatisticalDataCategory",
                                 previousMonthDatarows[i].getAttribute("STATISTICALDATACATEGORY"));
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
                row.setAttribute("TRANSACTIONPERIOD", previousMonthDatarows[i].getAttribute("TRANSACTIONPERIOD"));


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
        this.statisticspopupbind.hide();
    }

    public void onCancelOfStatDataEvent(ActionEvent actionEvent) {
        executeBinding("Rollback");
    }


    public void onTPADocsUpload(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue() != null) {

            String filePath1 = ADFUtils.getPath();
            if (filePath1.equalsIgnoreCase("NOPATH")) {
                FacesContext context = FacesContext.getCurrentInstance();
                String messageText = "Please setup the system path to upload the file.";
                FacesMessage fm = new FacesMessage(messageText);
                fm.setSeverity(FacesMessage.SEVERITY_INFO);
                context.addMessage(null, fm);
            } else {
                try {
                    UploadedFile uploadedFile = (UploadedFile) valueChangeEvent.getNewValue();
                    if (null != uploadedFile) {
                        // InputStream inputStream = null;
                        // inputStream = uploadedFile.getInputStream();
                        // BufferedInputStream bfi = new BufferedInputStream(inputStream);
                        // String fileName = uploadedFile.getFilename();
                        String path = null;


                        String tokens = uploadedFile.getFilename();
                        String fileName = uploadedFile.getFilename();
                        String contentType = uploadedFile.getContentType();

                        // path = filePath1 + fileNames;
                        saveFile(filePath1, fileName, uploadedFile);

                        DCIteratorBinding docs = getDCIteratorBindings("SgsTpaDocAttachment1VO2Iterator");
                        Row row = docs.getCurrentRow();

                        row.setAttribute("Attachment", fileName);
                        row.setAttribute("Attribute1", path);
                        row.setAttribute("Attribute2", contentType);


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void saveFile(String folderPath, String fileName, UploadedFile file) throws MalformedURLException,

                                                                                       IOException {

        InputStream inputStream = null;

        try {

            File folder = new File(folderPath);

            if (!folder.exists()) {

                folder.mkdirs();

            }


            // Create the output file path

            String filePath = folderPath + File.separator + fileName;
            File outputFile = new File(filePath);

            // Save the uploaded file to the file system

            FileOutputStream out = new FileOutputStream(outputFile);

            inputStream = file.getInputStream();

            byte[] buffer = new byte[8192];

            int bytesRead = 0;

            while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {

                out.write(buffer, 0, bytesRead);

            }

            out.flush();

            out.close();

        } catch (Exception ex) {

            // handle exception

            ex.printStackTrace();
        } finally {

            try {

                inputStream.close();

            } catch (IOException e) {

            }

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

    public void onFADocsDownload(FacesContext facesContext, OutputStream outputStream) {
        DCBindingContainer bindingContainer =
            (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding imageIter = (DCIteratorBinding) bindingContainer.get("SgsFixedAssetsTxnVO1Iterator");
        ViewObject vo = imageIter.getViewObject();
        oracle.jbo.Row currentRow = (oracle.jbo.Row) vo.getCurrentRow();
        String filePath = (String) currentRow.getAttribute("ATTRIBUTE1");
        String fileName = (String) currentRow.getAttribute("ATTACHMENT");

        String filePath1 = ADFUtils.getPath();
        LOG.info("File Path :: " + filePath1);

        try {
            if (filePath1.equalsIgnoreCase("NOPATH")) {
                System.out.println("No Path selected");
            }

            else if (null != filePath1 && null != fileName) {
                File f = new File(filePath1 + File.separator + fileName);
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

    //    public void onFADocsDownload(FacesContext facesContext, OutputStream outputStream) {
    //        DCBindingContainer bindingContainer =
    //            (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
    //        DCIteratorBinding imageIter = (DCIteratorBinding) bindingContainer.get("SgsFixedAssetsTxnVO1Iterator");
    //        ViewObject vo = imageIter.getViewObject();
    //        oracle.jbo.Row currentRow = (oracle.jbo.Row) vo.getCurrentRow();
    //        String filePath = (String) currentRow.getAttribute("ATTRIBUTE1");
    //        String fileName = (String) currentRow.getAttribute("ATTACHMENT");
    //
    //            String filePath1 = ADFUtils.getPath();
    //            LOG.info("File Path :: " + filePath1);
    //            if (filePath1.equalsIgnoreCase("NOPATH")) {
    //            } else {
    //                File filed = new File(filePath1 + fileName);
    //            if (filePath != null && !filePath.isEmpty() && fileName != null && !fileName.isEmpty()) {
    //
    //                String fileFullPath = filePath + File.separator + fileName;
    //                File file = new File(fileFullPath);
    //
    //
    //                FileInputStream fis = new FileInputStream(file);
    //                byte[] buffer = new byte[4096];
    //                int bytesRead;
    //                while ((bytesRead = fis.read(buffer)) != -1) {
    //                    outputStream.write(buffer, 0, bytesRead);
    //                }
    //                outputStream.flush();
    //            }
    //        }
    //
    //    }


    public void onSelectAllVouchers(ValueChangeEvent valueChangeEvent) {

        DCIteratorBinding voucherData = null;
        voucherData = getDCIteratorBindings("SgsStlmtVoucherVO1Iterator");
        oracle.jbo.Row[] voucherDatarows = voucherData.getAllRowsInRange();
        if ((Boolean) valueChangeEvent.getNewValue()) {

            for (int i = 0; i < voucherDatarows.length; i++) {
                voucherDatarows[i].setAttribute("SelectRecord", "Yes");
            }

        } else {

            for (int i = 0; i < voucherDatarows.length; i++) {
                voucherDatarows[i].setAttribute("SelectRecord", "No");
            }

        }

        AdfFacesContext.getCurrentInstance().addPartialTarget(vouchercheckBoxSelectBind);
        AdfFacesContext.getCurrentInstance().addPartialTarget(voucherColSelectBind);

    }

    public void onSelectAllInvoice(ValueChangeEvent valueChangeEvent) {
        DCIteratorBinding invoiceData = null;
        invoiceData = getDCIteratorBindings("SgsStlmtInvVO1Iterator");
        oracle.jbo.Row[] invoiceDatarows = invoiceData.getAllRowsInRange();
        if ((Boolean) valueChangeEvent.getNewValue()) {
            for (int i = 0; i < invoiceDatarows.length; i++) {
                invoiceDatarows[i].setAttribute("SelectRecordInvoice", "Yes");
            }
        } else {
            for (int i = 0; i < invoiceDatarows.length; i++) {
                invoiceDatarows[i].setAttribute("SelectRecordInvoice", "No");
            }
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(invoiceCheckBoxSelectBind);
        AdfFacesContext.getCurrentInstance().addPartialTarget(invoiceColSelectBind);
    }

    public void SaveWriteOffDetails(ActionEvent actionEvent) {
        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding holditer = (DCIteratorBinding) bindings.get("SgsStlmtVoucherVO1Iterator");
        ViewObject holdVO = holditer.getViewObject();


        oracle.jbo.Row[] selectedRows = holdVO.getFilteredRows("SelectRecord", "Yes");
        System.out.println("*****Selected rows****" + selectedRows.length);
        for (oracle.jbo.Row rw : selectedRows) {
            if (rw.getAttribute("PaymentStatus").equals("Partially paid") ||
                rw.getAttribute("PaymentStatus").equals("Unpaid")) {

                double outstandingAmount = ((Number) rw.getAttribute("OsAmountPayable")).doubleValue();
                rw.setAttribute("AmountWrittenBank", outstandingAmount);
                rw.setAttribute("OsAmountPayable", 0);
                rw.setAttribute("StlmtStatus", "Amount Written Off");
                // PaymentStatus
                rw.setAttribute("PaymentStatus", "Written Off");
            } else {

                continue;
            }
        }

        executeBinding("Commit");

        AdfFacesContext.getCurrentInstance().addPartialTarget(tablePaymentRecords1);


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
        DCIteratorBinding settlementData = null;
        settlementData = getDCIteratorBindings("SgsStlmtVoucherVO1Iterator");
        oracle.jbo.Row[] settlementDataDatarows = settlementData.getAllRowsInRange();
        if ((Boolean) valueChangeEvent.getNewValue()) {
            for (int i = 0; i < settlementDataDatarows.length; i++) {
                settlementDataDatarows[i].setAttribute("SelectRecord", "Yes");
            }
        } else {
            for (int i = 0; i < settlementDataDatarows.length; i++) {
                settlementDataDatarows[i].setAttribute("SelectRecord", "No");
            }
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindSettlementRowCheckBox);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindSettlementSelectColumn);
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

    }


    public void onGenerateSettlementEvent(ActionEvent actionEvent) {

        System.out.println("ONGEN");
        double transactionAmount = 0.00;
        double bankCharge = 0.00;
        DCIteratorBinding dcIteratorbinding = getDCIteratorBindings("CreateStlmtRVO1Iterator");
        Row row = dcIteratorbinding.getCurrentRow();
        //        SGSAppModuleImpl am = new SGSAppModuleImpl();
        String paymentId = "PS_" + (am.getDBSequence1("SEQ_SGS_CREATE_SETTLEMENT"));
        System.out.println("---paymentid---" + paymentId);
        if (row.getAttribute("TRXAMOUNT") != null) {
            transactionAmount = ((Number) row.getAttribute("TRXAMOUNT")).doubleValue();
        }

        if (row.getAttribute("BANKCHARGES") != null) {
            bankCharge = ((Number) row.getAttribute("BANKCHARGES")).doubleValue();
        }

        ViewObject paymentIterator = ADFUtils.findIterator("SgsStlmtVoucherVO1Iterator").getViewObject();
        Row[] rows = paymentIterator.getAllRowsInRange();
        boolean isFirstRow = true;
        if (null != (Boolean) getBindReceiptCheck().getValue() && (Boolean) getBindReceiptCheck().getValue() &&
            (null != (Boolean) getBindPaymentCheck().getValue() && !((Boolean) getBindPaymentCheck().getValue()))) {
            System.out.println("ONRECEIPTONLY");
            for (Row paymentRow : getFullyPaidAndPartiallyPaidRows()) {
                String settlementStatus = (String) paymentRow.getAttribute("StlmtStatus");
                String paymentStatus = (String) paymentRow.getAttribute("PaymentStatus");
                String transactionRef = (String) row.getAttribute("TRANSACTIONREFERENCENO");
                String recTxnRefNum = (String) row.getAttribute("RECTXNREFERENCENO");
                double outstandingAmount = ((Number) paymentRow.getAttribute("OsAmountPayable")).doubleValue();
                double netPayableAmount = ((Number) paymentRow.getAttribute("NetAmountPayable")).doubleValue();
                //                String paymentId = (String) row.getAttribute("PaymentId");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                //            java.util.Date paymentDate = (java.util.Date) row.getAttribute("PAYMENTDATE");
                //            if (paymentDate != null) {
                //            java.sql.Date payDate = new java.sql.Date(paymentDate.getTime());
                //                System.out.println("Payment date" + payDate);
                //            }

                java.util.Date paymentDate = (java.util.Date) row.getAttribute("PAYMENTDATE");
                java.sql.Date payDate = null;

                if (paymentDate != null) {
                    payDate = new java.sql.Date(paymentDate.getTime());
                    System.out.println("Payment date: " + payDate);
                }

                //            java.util.Date receiptDate = (java.util.Date) row.getAttribute("RECEIPTDATE");
                //            if (receiptDate != null) {
                //            java.sql.Date rctDate = new java.sql.Date(receiptDate.getTime());
                //                System.out.println("Receipt date" + rctDate);
                //            }

                java.util.Date receiptDate = (java.util.Date) row.getAttribute("RECEIPTDATE");
                java.sql.Date rctDate = null;

                if (receiptDate != null) {
                    rctDate = new java.sql.Date(receiptDate.getTime());
                    System.out.println("Receipt date: " + rctDate);
                }
                //            System.out.println("Payment date" + payDate);
                //            System.out.println("Receipt date" + rctDate);
                String ReceiptBankName = (String) AdfFacesContext.getCurrentInstance()
                                                                 .getPageFlowScope()
                                                                 .get("selectedRctBankName");
                String ReceiptBankCode = (String) AdfFacesContext.getCurrentInstance()
                                                                 .getPageFlowScope()
                                                                 .get("selectedRctBankCode");
                String paymentBankName = (String) AdfFacesContext.getCurrentInstance()
                                                                 .getPageFlowScope()
                                                                 .get("selectedPayBankName");
                String paymentBankCode = (String) AdfFacesContext.getCurrentInstance()
                                                                 .getPageFlowScope()
                                                                 .get("selectedPayBankCode");
                String paymentMethod = (String) AdfFacesContext.getCurrentInstance()
                                                               .getPageFlowScope()
                                                               .get("selectedPayMetd");
                String paymentCurrency = (String) AdfFacesContext.getCurrentInstance()
                                                                 .getPageFlowScope()
                                                                 .get("selectedPayCurr");
                String purposeCode = (String) AdfFacesContext.getCurrentInstance()
                                                             .getPageFlowScope()
                                                             .get("selectedPurposeCode");


                //            boolean paymentOnly =(Boolean)bindPaymentCheck.getValue();
                //
                //            boolean receiptOnly =(Boolean)bindReceiptCheck.getValue();

                String receiptPurposeCode = (String) AdfFacesContext.getCurrentInstance()
                                                                    .getPageFlowScope()
                                                                    .get("selectedReceiptPurposeCode");

                String receiptCurrency = (String) AdfFacesContext.getCurrentInstance()
                                                                 .getPageFlowScope()
                                                                 .get("selectedReceiptCurrency");

                System.out.println("Receipt Bank Name " + ReceiptBankName);
                System.out.println("Receipt Bank Code " + ReceiptBankCode);
                System.out.println("payment Bank Name " + paymentBankName);
                System.out.println("payment Bank Code" + paymentBankCode);

                double settlementAmount = 0;

                if ("Fully Paid".equals(paymentStatus)) {
                    //                    if (transactionAmount >= outstandingAmount) {
                    //                        settlementAmount = outstandingAmount;
                    //                        settlementStatus = "Settlement Completed";
                    //                        paymentStatus = "Fully Paid";
                    //
                    //                    } else {
                    //
                    //                        DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    //                        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
                    //                        settlementAmount = Double.parseDouble(decimalFormat.format(transactionAmount));
                    //
                    //
                    //                        settlementStatus = "Partially Settled";
                    //                        paymentStatus = "Partially Paid";
                    //                        transactionAmount = 0;
                    //                    }
                    settlementStatus = "Settlement Completed";
                    paymentStatus = "Fully Paid";
                    if (isFirstRow) {
                        paymentRow.setAttribute("BANKCHARGES", bankCharge);
                        isFirstRow = false;
                    }

                    //                                    transactionAmount -= outstandingAmount;
                    paymentRow.setAttribute("StlmtStatus", settlementStatus);
                    paymentRow.setAttribute("PaymentStatus", paymentStatus);
                    //                    paymentRow.setAttribute("StlmtAmount", settlementAmount);
                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
                    //                    paymentRow.setAttribute("OsAmountPayable",
                    //                                            Double.parseDouble(decimalFormat.format(netPayableAmount -
                    //                                                                                    settlementAmount)));


                    //                    paymentRow.setAttribute("PAYMENTCURRENCY", "NULL");
                    //                    paymentRow.setAttribute("PURPOSECODE", "NULL");
                    //                    paymentRow.setAttribute("PAYMENTDATE", "");
                    //                    paymentRow.setAttribute("PAYMENTBANKCD", "NULL");
                    //                    paymentRow.setAttribute("PAYMENTMETHOD", "NULL");
                    //                    paymentRow.setAttribute("PAYMENTBANKACCTKEY", "NULL");

                    paymentRow.setAttribute("RECEIPTPURPOSECODE", receiptPurposeCode);
                    paymentRow.setAttribute("RECEIPTDATE", rctDate);
                    paymentRow.setAttribute("RECEIPTBANKCD", ReceiptBankName);
                    paymentRow.setAttribute("RECEIPTBANKACCTKEY", ReceiptBankCode);
                    paymentRow.setAttribute("RECTXNREFERENCENO", recTxnRefNum);
                    paymentRow.setAttribute("RECEIPTCURRENCY", receiptCurrency);
                    paymentRow.setAttribute("PaymentId", paymentId);


                } else if ("On Hold".equals(paymentStatus)) {

                    continue;

                } else {
                    transactionAmount = 0.0;
                    //                    if (transactionAmount >= outstandingAmount) {
                    //                        System.out.println("inside partial settled record");
                    //                        settlementAmount = netPayableAmount;
                    //                        settlementStatus = "Settlement Completed";
                    //                        paymentStatus = "Fully Paid";
                    //
                    //
                    //                    } else {
                    //
                    //                        DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    //                        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
                    ////                        settlementAmount = Double.parseDouble(decimalFormat.format(transactionAmount));
                    //                System.out.println("partially paid stlmnt status $$$$ ");
                    //
                    //                        settlementStatus = "Partially Settled";
                    //                        paymentStatus = "Partially Paid";
                    //
                    //                    }
                    settlementStatus = "Partially Settled";
                    paymentStatus = "Partially Paid";
                    if (isFirstRow) {
                        paymentRow.setAttribute("BANKCHARGES", bankCharge);
                        isFirstRow = false;
                    }

                    //                    transactionAmount -= outstandingAmount;
                    paymentRow.setAttribute("StlmtStatus", settlementStatus);
                    paymentRow.setAttribute("PaymentStatus", paymentStatus);
                    //                    paymentRow.setAttribute("StlmtAmount", settlementAmount);
                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
                    //                    paymentRow.setAttribute("OsAmountPayable",
                    //                                            Double.parseDouble(decimalFormat.format(netPayableAmount -
                    //                                                                                    settlementAmount)));

                    //                    paymentRow.setAttribute("PAYMENTCURRENCY", "NULL");
                    //                    paymentRow.setAttribute("PURPOSECODE", "NULL");
                    //                    paymentRow.setAttribute("PAYMENTDATE", "");
                    //                    paymentRow.setAttribute("PAYMENTBANKCD", "NULL");
                    //                    paymentRow.setAttribute("PAYMENTMETHOD", "NULL");
                    //                    paymentRow.setAttribute("PAYMENTBANKACCTKEY", "NULL");

                    paymentRow.setAttribute("RECEIPTPURPOSECODE", receiptPurposeCode);
                    paymentRow.setAttribute("RECEIPTDATE", rctDate);
                    paymentRow.setAttribute("RECEIPTBANKCD", ReceiptBankName);
                    paymentRow.setAttribute("RECEIPTBANKACCTKEY", ReceiptBankCode);
                    paymentRow.setAttribute("RECTXNREFERENCENO", recTxnRefNum);
                    paymentRow.setAttribute("RECEIPTCURRENCY", receiptCurrency);
                    paymentRow.setAttribute("PaymentId", paymentId);


                }
            }
            return;
        }


        for (Row paymentRow : getUnpaidAndPartiallySettledRows()) {

            System.out.println("insidefor");
            String settlementStatus = (String) paymentRow.getAttribute("StlmtStatus");
            String paymentStatus = (String) paymentRow.getAttribute("PaymentStatus");
            String transactionRef = (String) row.getAttribute("TRANSACTIONREFERENCENO");
            String recTxnRefNum = (String) row.getAttribute("RECTXNREFERENCENO");
            double outstandingAmount = ((Number) paymentRow.getAttribute("OsAmountPayable")).doubleValue();
            double netPayableAmount = ((Number) paymentRow.getAttribute("NetAmountPayable")).doubleValue();
            double settlementAmount = 0.0;
            if (null == paymentRow.getAttribute("StlmtAmount")) {
                settlementAmount = 0.0;
            } else {
                settlementAmount = ((Number) paymentRow.getAttribute("StlmtAmount")).doubleValue();
            }

            //            String paymentId = (String) row.getAttribute("PaymentId");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            //            java.util.Date paymentDate = (java.util.Date) row.getAttribute("PAYMENTDATE");
            //            if (paymentDate != null) {
            //            java.sql.Date payDate = new java.sql.Date(paymentDate.getTime());
            //                System.out.println("Payment date" + payDate);
            //            }

            java.util.Date paymentDate = (java.util.Date) row.getAttribute("PAYMENTDATE");
            java.sql.Date payDate = null;

            if (paymentDate != null) {
                payDate = new java.sql.Date(paymentDate.getTime());
                System.out.println("Payment date: " + payDate);
            }

            //            java.util.Date receiptDate = (java.util.Date) row.getAttribute("RECEIPTDATE");
            //            if (receiptDate != null) {
            //            java.sql.Date rctDate = new java.sql.Date(receiptDate.getTime());
            //                System.out.println("Receipt date" + rctDate);
            //            }

            java.util.Date receiptDate = (java.util.Date) row.getAttribute("RECEIPTDATE");
            java.sql.Date rctDate = null;

            if (receiptDate != null) {
                rctDate = new java.sql.Date(receiptDate.getTime());
                System.out.println("Receipt date: " + rctDate);
            }
            //            System.out.println("Payment date" + payDate);
            //            System.out.println("Receipt date" + rctDate);
            String ReceiptBankName = (String) AdfFacesContext.getCurrentInstance()
                                                             .getPageFlowScope()
                                                             .get("selectedRctBankName");
            String ReceiptBankCode = (String) AdfFacesContext.getCurrentInstance()
                                                             .getPageFlowScope()
                                                             .get("selectedRctBankCode");
            String paymentBankName = (String) AdfFacesContext.getCurrentInstance()
                                                             .getPageFlowScope()
                                                             .get("selectedPayBankName");
            String paymentBankCode = (String) AdfFacesContext.getCurrentInstance()
                                                             .getPageFlowScope()
                                                             .get("selectedPayBankCode");
            String paymentMethod = (String) AdfFacesContext.getCurrentInstance()
                                                           .getPageFlowScope()
                                                           .get("selectedPayMetd");
            String paymentCurrency = (String) AdfFacesContext.getCurrentInstance()
                                                             .getPageFlowScope()
                                                             .get("selectedPayCurr");
            String purposeCode = (String) AdfFacesContext.getCurrentInstance()
                                                         .getPageFlowScope()
                                                         .get("selectedPurposeCode");


            //            boolean paymentOnly =(Boolean)bindPaymentCheck.getValue();
            //
            //            boolean receiptOnly =(Boolean)bindReceiptCheck.getValue();

            String receiptPurposeCode = (String) AdfFacesContext.getCurrentInstance()
                                                                .getPageFlowScope()
                                                                .get("selectedReceiptPurposeCode");

            String receiptCurrency = (String) AdfFacesContext.getCurrentInstance()
                                                             .getPageFlowScope()
                                                             .get("selectedReceiptCurrency");

            System.out.println("Receipt Bank Name " + ReceiptBankName);
            System.out.println("Receipt Bank Code " + ReceiptBankCode);
            System.out.println("payment Bank Name " + paymentBankName);
            System.out.println("payment Bank Code" + paymentBankCode);


            if (null != (Boolean) getBindReceiptCheck().getValue() &&
                null != (Boolean) getBindPaymentCheck().getValue() && (Boolean) getBindPaymentCheck().getValue() &&
                (Boolean) getBindReceiptCheck().getValue()) {

                System.out.println("ONBOTHONLY----------------");
                if ("Unpaid".equals(paymentStatus)) {
                    if (transactionAmount >= outstandingAmount) {
                        settlementAmount = outstandingAmount;
                        settlementStatus = "Settlement Completed";
                        paymentStatus = "Fully Paid";

                    } else {

                        DecimalFormat decimalFormat = new DecimalFormat("#.##");
                        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
                        settlementAmount += Double.parseDouble(decimalFormat.format(transactionAmount));


                        settlementStatus = "Partially Settled";
                        paymentStatus = "Partially Paid";
                        //                        transactionAmount = 0;
                    }
                    if (isFirstRow) {
                        paymentRow.setAttribute("BANKCHARGES", bankCharge);
                        isFirstRow = false;
                    }

                    transactionAmount -= outstandingAmount;
                    paymentRow.setAttribute("StlmtStatus", settlementStatus);
                    paymentRow.setAttribute("PaymentStatus", paymentStatus);
                    paymentRow.setAttribute("StlmtAmount", settlementAmount);
                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
                    paymentRow.setAttribute("OsAmountPayable",
                                            Double.parseDouble(decimalFormat.format(netPayableAmount -
                                                                                    settlementAmount)));


                    //                     paymentRow.setAttribute("PaymentId", value);
                    paymentRow.setAttribute("PaymentId", paymentId);
                    paymentRow.setAttribute("PAYMENTDATE", payDate);
                    paymentRow.setAttribute("RECEIPTDATE", rctDate);
                    paymentRow.setAttribute("RECEIPTBANKCD", ReceiptBankName);
                    paymentRow.setAttribute("RECEIPTBANKACCTKEY", ReceiptBankCode);
                    paymentRow.setAttribute("PAYMENTBANKCD", paymentBankName);
                    paymentRow.setAttribute("PAYMENTBANKACCTKEY", paymentBankCode);
                    paymentRow.setAttribute("TxnReferenceNo", transactionRef);
                    paymentRow.setAttribute("RECTXNREFERENCENO", recTxnRefNum);
                    paymentRow.setAttribute("PAYMENTMETHOD", paymentMethod);
                    paymentRow.setAttribute("PAYMENTCURRENCY", paymentCurrency);
                    paymentRow.setAttribute("PURPOSECODE", purposeCode);
                    paymentRow.setAttribute("RECEIPTCURRENCY", receiptCurrency);
                    paymentRow.setAttribute("RECEIPTPURPOSECODE", receiptPurposeCode);


                } else if ("On Hold".equals(paymentStatus)) {

                    continue;

                } else {
                    if (transactionAmount >= outstandingAmount) {
                        System.out.println("inside partial settled record");
                        settlementAmount = netPayableAmount;
                        settlementStatus = "Settlement Completed";
                        paymentStatus = "Fully Paid";


                    } else {

                        DecimalFormat decimalFormat = new DecimalFormat("#.##");
                        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
                        settlementAmount += Double.parseDouble(decimalFormat.format(transactionAmount));

                        settlementStatus = "Partially Settled";
                        paymentStatus = "Partially Paid";
                        //                        transactionAmount = 0;
                    }
                    if (isFirstRow) {
                        paymentRow.setAttribute("BANKCHARGES", bankCharge);
                        isFirstRow = false;
                    }

                    transactionAmount -= outstandingAmount;
                    paymentRow.setAttribute("StlmtStatus", settlementStatus);
                    paymentRow.setAttribute("PaymentStatus", paymentStatus);
                    paymentRow.setAttribute("StlmtAmount", settlementAmount);
                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
                    paymentRow.setAttribute("OsAmountPayable",
                                            Double.parseDouble(decimalFormat.format(netPayableAmount -
                                                                                    settlementAmount)));
                    //                     paymentRow.setAttribute("PaymentId", paymentId);
                    paymentRow.setAttribute("PaymentId", paymentId);
                    paymentRow.setAttribute("PAYMENTDATE", payDate);
                    paymentRow.setAttribute("RECEIPTDATE", rctDate);
                    paymentRow.setAttribute("RECEIPTBANKCD", ReceiptBankName);
                    paymentRow.setAttribute("RECEIPTBANKACCTKEY", ReceiptBankCode);
                    paymentRow.setAttribute("PAYMENTBANKCD", paymentBankName);
                    paymentRow.setAttribute("PAYMENTBANKACCTKEY", paymentBankCode);
                    paymentRow.setAttribute("TxnReferenceNo", transactionRef);
                    paymentRow.setAttribute("PAYMENTMETHOD", paymentMethod);
                    paymentRow.setAttribute("PAYMENTCURRENCY", paymentCurrency);
                    paymentRow.setAttribute("PURPOSECODE", purposeCode);
                    paymentRow.setAttribute("RECEIPTCURRENCY", receiptCurrency);
                    paymentRow.setAttribute("RECEIPTPURPOSECODE", receiptPurposeCode);


                }
            } else if (null != (Boolean) getBindPaymentCheck().getValue() &&
                       (Boolean) getBindPaymentCheck().getValue()) {
                System.out.println("ONPAYMENTONLY");
                if ("Unpaid".equals(paymentStatus)) {
                    if (transactionAmount >= outstandingAmount) {
                        settlementAmount = outstandingAmount;
                        settlementStatus = "Voucher Paid-Invoice Pending";
                        paymentStatus = "Fully Paid";

                    } else {

                        DecimalFormat decimalFormat = new DecimalFormat("#.##");
                        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
                        settlementAmount += Double.parseDouble(decimalFormat.format(transactionAmount));


                        settlementStatus = "Partial Voucher Paid-Invoice Pending";
                        paymentStatus = "Partially Paid";
                        //                        transactionAmount = 0;
                    }
                    if (isFirstRow) {
                        paymentRow.setAttribute("BANKCHARGES", bankCharge);
                        isFirstRow = false;
                    }

                    transactionAmount -= outstandingAmount;
                    paymentRow.setAttribute("StlmtStatus", settlementStatus);
                    paymentRow.setAttribute("PaymentStatus", paymentStatus);
                    paymentRow.setAttribute("StlmtAmount", settlementAmount);
                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
                    paymentRow.setAttribute("OsAmountPayable",
                                            Double.parseDouble(decimalFormat.format(netPayableAmount -
                                                                                    settlementAmount)));


                    //                    paymentRow.setAttribute("RECEIPTPURPOSECODE", "NULL");
                    //                    paymentRow.setAttribute("RECEIPTDATE", "");
                    //                    paymentRow.setAttribute("RECEIPTBANKCD", "NULL");
                    //                    paymentRow.setAttribute("RECEIPTBANKACCTKEY", "NULL");
                    //                    paymentRow.setAttribute("RECEIPTCURRENCY", "NULL");

                    paymentRow.setAttribute("PAYMENTCURRENCY", paymentCurrency);
                    paymentRow.setAttribute("PURPOSECODE", purposeCode);
                    paymentRow.setAttribute("PAYMENTDATE", payDate);
                    paymentRow.setAttribute("PAYMENTBANKCD", paymentBankName);
                    paymentRow.setAttribute("PAYMENTMETHOD", paymentMethod);
                    paymentRow.setAttribute("TxnReferenceNo", transactionRef);
                    paymentRow.setAttribute("PAYMENTBANKACCTKEY", paymentBankCode);
                    paymentRow.setAttribute("PaymentId", paymentId);


                } else if ("On Hold".equals(paymentStatus)) {

                    continue;

                } else {
                    if (transactionAmount >= outstandingAmount) {
                        System.out.println("inside partial settled record");
                        settlementAmount = netPayableAmount;
                        settlementStatus = "Voucher Paid-Invoice Pending";
                        paymentStatus = "Fully Paid";


                    } else {

                        DecimalFormat decimalFormat = new DecimalFormat("#.##");
                        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
                        settlementAmount += Double.parseDouble(decimalFormat.format(transactionAmount));
                        System.out.println("settlementAmount---" + settlementAmount);

                        settlementStatus = "Partial Voucher Paid-Invoice Pending";
                        paymentStatus = "Partially Paid";
                        //                        transactionAmount = 0;
                    }
                    if (isFirstRow) {
                        paymentRow.setAttribute("BANKCHARGES", bankCharge);
                        isFirstRow = false;
                    }

                    transactionAmount -= outstandingAmount;
                    paymentRow.setAttribute("StlmtStatus", settlementStatus);
                    paymentRow.setAttribute("PaymentStatus", paymentStatus);
                    paymentRow.setAttribute("StlmtAmount", settlementAmount);
                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
                    paymentRow.setAttribute("OsAmountPayable",
                                            Double.parseDouble(decimalFormat.format(netPayableAmount -
                                                                                    settlementAmount)));
                    //                     paymentRow.setAttribute("PaymentId", paymentId);

                    //                    paymentRow.setAttribute("RECEIPTPURPOSECODE", "NULL");
                    //                    paymentRow.setAttribute("RECEIPTDATE", "");
                    //                    paymentRow.setAttribute("RECEIPTBANKCD", "NULL");
                    //                    paymentRow.setAttribute("RECEIPTBANKACCTKEY", "NULL");
                    //                    paymentRow.setAttribute("RECEIPTCURRENCY", "NULL");

                    paymentRow.setAttribute("PAYMENTCURRENCY", paymentCurrency);
                    paymentRow.setAttribute("PURPOSECODE", purposeCode);
                    paymentRow.setAttribute("PAYMENTDATE", payDate);
                    paymentRow.setAttribute("PAYMENTBANKCD", paymentBankName);
                    paymentRow.setAttribute("PAYMENTMETHOD", paymentMethod);
                    paymentRow.setAttribute("TxnReferenceNo", transactionRef);
                    paymentRow.setAttribute("PAYMENTBANKACCTKEY", paymentBankCode);
                    paymentRow.setAttribute("PaymentId", paymentId);


                }
            } else {
                JSFUtil.addComponentFacesMessage(FacesMessage.SEVERITY_ERROR,
                                                 "Select either Apply Payment Only OR Apply Receipt Only", null);
                break;
            }
            if (transactionAmount <= 0) {
                break;
            }


        }

        //        System.out.println("Balance : " + transactionAmount);
        //
        //
        //        if (transactionAmount >= 0) {
        //            DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        //            decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        //            String balanceText = "Balance: " + decimalFormat.format(transactionAmount);
        //            System.out.println(balanceText);
        //            this.setBalanceOutputText(balanceText);
        //            System.out.println(balanceOutputText);
        //            AdfFacesContext.getCurrentInstance().addPartialTarget(balanceOutputText1);
        //
        //        }


        //        executeBinding(SAVE_DATA);

        AdfFacesContext.getCurrentInstance().addPartialTarget(tablePaymentRecords);


    }

    private Row[] getUnpaidAndPartiallySettledRows() {

        ViewObject vo = getPaymentSettlementVO();
        vo.setWhereClause("PAYMENT_STATUS NOT IN ('Fully Paid')");
        vo.setOrderByClause("CASE PAYMENT_STATUS\n" + "				WHEN 'Partially Paid' then 1\n" +
                            "				WHEN 'Unpaid' then 2\n" + "				else 3\n" +
                            "END");
        vo.executeQuery();
        return vo.getAllRowsInRange();
    }

    private Row[] getFullyPaidAndPartiallyPaidRows() {

        ViewObject vo = getPaymentSettlementVO();
        vo.setWhereClause("PAYMENT_STATUS NOT IN ('Unpaid')");
        vo.setOrderByClause("CASE PAYMENT_STATUS\n" + "                               WHEN 'Partially Paid' then 1\n" +
                            "                               WHEN 'Fully Paid' then 2\n" +
                            "                               else 3\n" + "END");
        vo.executeQuery();
        return vo.getAllRowsInRange();
    }

    private ViewObject getPaymentSettlementVO() {
        DCBindingContainer binding = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = binding.findIteratorBinding("SgsStlmtVoucherVO1Iterator");
        return iterator.getViewObject();
    }


    public void onCreateSettlementSearch(ActionEvent actionEvent) {

        System.out.println("bindPaymentCheck::" + getBindPaymentCheck().getValue());
        System.out.println("bindReceiptCheck::" + getBindReceiptCheck().getValue());
        System.out.println("zeroBalanceCheckBind::" + getZeroBalanceCheckBind().getValue());

        //        bindPaymentCheck
        //        bindReceiptCheck

        DCIteratorBinding dcIteratorbinding = getDCIteratorBindings("CreateStlmtRVO1Iterator");
        Row row = dcIteratorbinding.getCurrentRow();
        //SgsCreateSettlementVO1Iterator

        String ICSUPPLIERGEO = (String) AdfFacesContext.getCurrentInstance()
                                                       .getPageFlowScope()
                                                       .get("selectedValue");

        String ICCUSTOMERGEO = (String) AdfFacesContext.getCurrentInstance()
                                                       .getPageFlowScope()
                                                       .get("selectedValue1");
        String collectionBU = (String) AdfFacesContext.getCurrentInstance()
                                                      .getPageFlowScope()
                                                      .get("selectedValue2");

        String payerBU = (String) AdfFacesContext.getCurrentInstance()
                                                 .getPageFlowScope()
                                                 .get("selectedValue3");

        String ICCUSTOMERBU = (String) AdfFacesContext.getCurrentInstance()
                                                      .getPageFlowScope()
                                                      .get("selectedValue4");

        String ICSUPPLIERBU = (String) AdfFacesContext.getCurrentInstance()
                                                      .getPageFlowScope()
                                                      .get("selectedValue4");

        //        bindPsVoucherNumber.getValue();
        //        bindPsInvoiceNumber.getValue();
        //        paymentTxnNum.getValue();


        //
        //                        String PAYMENTID = (String) row.getAttribute("PAYMENTID");
        //                String ICCUSTOMERGEO = (String) row.getAttribute("ICCUSTOMERGEO");
        //                String ICCUSTOMERBU = (String) row.getAttribute("ICCUSTOMERBU");
        //                String ICSUPPLIERGEO = (String) row.getAttribute("ICSUPPLIERGEO");
        //                String ICSUPPLIERBU = (String) row.getAttribute("ICSUPPLIERBU");

        //        String COLLECTORBU = (String) row.getAttribute("COLLECTORBU");
        //        String PAYERBU = (String) row.getAttribute("PAYERBU");


        System.out.println("suppliergeo : " + ICSUPPLIERGEO);
        System.out.println("CUSTOMERGEO : " + ICCUSTOMERGEO);

        System.out.println("collectionBU : " + collectionBU);
        System.out.println("payerBU : " + payerBU);

        System.out.println("IC customer BU : " + ICCUSTOMERBU);
        System.out.println("IC Supplier BU : " + ICSUPPLIERBU);


        //        System.out.println("COLLECTORBU : " + COLLECTORBU);
        //        System.out.println("PAYERBU : " + PAYERBU);

        DCIteratorBinding iteratorBinding = getDCIteratorBindings("SgsStlmtVoucherVO1Iterator");
        RowSetIterator rowSetIterator = iteratorBinding.getRowSetIterator();
        ViewObjectImpl voucherView = (ViewObjectImpl) iteratorBinding.getViewObject();
        ViewCriteria criteria = voucherView.getViewCriteria("SgsCreateStlmtVoucherVOCriteria");
        voucherView.applyViewCriteria(criteria);
        voucherView.setNamedWhereClauseParam("bCusBu", ICCUSTOMERBU);
        voucherView.setNamedWhereClauseParam("bCusGeo", ICCUSTOMERGEO);
        voucherView.setNamedWhereClauseParam("bSupBu", ICSUPPLIERBU);
        voucherView.setNamedWhereClauseParam("bSupGeo", ICSUPPLIERGEO);
        voucherView.setNamedWhereClauseParam("bCollectorBU", collectionBU);
        voucherView.setNamedWhereClauseParam("bPayerBU", payerBU);
        voucherView.setNamedWhereClauseParam("bSltmtStatus", "Settled");


        if (null != bindPaymentRefNum.getValue()) {
            voucherView.setNamedWhereClauseParam("bPaymentRefNum", bindPaymentRefNum.getValue());
        }


        if (null != bindPsVoucherNumber.getValue()) {
            voucherView.setNamedWhereClauseParam("bPsVoucherNumber", bindPsVoucherNumber.getValue());
        }


        if (null != bindPsInvoiceNumber.getValue()) {
            voucherView.setNamedWhereClauseParam("bRefToArInvoice", bindPsInvoiceNumber.getValue());
        }

        System.out.println("bindPaymentCheck::" + getBindPaymentCheck().getValue());
        System.out.println("bindReceiptCheck::" + getBindReceiptCheck().getValue());

        //        boolean paymentCheck= (Boolean)getBindPaymentCheck().getValue();
        //        boolean receiptCheck= (Boolean)getBindReceiptCheck().getValue();
        //        System.out.println("bindPaymentCheck::"+paymentCheck);
        //        System.out.println("bindReceiptCheck::"+paymentCheck);

        System.out.println("zeroBalanceCheckBind::" + getZeroBalanceCheckBind().getValue());
        if (null != (Boolean) getZeroBalanceCheckBind().getValue() && (Boolean) getZeroBalanceCheckBind().getValue()) {
            voucherView.setWhereClause(" PAYMENT_STATUS = 'Unpaid' AND  CR_FLAG = 'Yes' AND STLMT_STATUS = 'Pending for Settlement'");

        } else if (null != (Boolean) getBindPaymentCheck().getValue() && (Boolean) getBindPaymentCheck().getValue()) {
            totalSettleOutput.setVisible(false);
            String stlmtStatus = "'Pending for Settlement','Settlement on Hold'";
            String paymentStatus = " 'Fully Paid','On Hold'";
            //            voucherView.setNamedWhereClauseParam("bNewStlmtStatus", "Settlement on Hold");
            //            voucherView.setNamedWhereClauseParam("bNewStlmtStatus2", "Pending for Settlement");
            //            voucherView.setNamedWhereClauseParam("bPayStatus", "On Hold");
            //            voucherView.setNamedWhereClauseParam("bPayStatus2", "Fully Paid");
            voucherView.setWhereClause(" PAYMENT_STATUS = 'Unpaid' OR PAYMENT_STATUS = 'Partially Paid' AND  CR_FLAG <> 'Yes'");

        } else if (null != (Boolean) getBindReceiptCheck().getValue() && (Boolean) getBindReceiptCheck().getValue()) {
            totalSettleOutput.setVisible(true);
            String stlmtStatus = "'Voucher Paid-Invoice Pending'";
            String paymentStatus = "'Fully Paid','Partially Paid'";
            // voucherView.setNamedWhereClauseParam("bNewStlmtStatus", "Voucher Paid");
            //            voucherView.setNamedWhereClauseParam("bNewStlmtStatus2", "Voucher Paid-Invoice Pending");
            //            voucherView.setNamedWhereClauseParam("bPayStatus", "Partially Paid");
            //            voucherView.setNamedWhereClauseParam("bPayStatus2", "Fully Paid");
            voucherView.setWhereClause(" (PAYMENT_STATUS = 'Fully Paid' AND STLMT_STATUS = 'Voucher Paid-Invoice Pending') " +
                                       " OR(PAYMENT_STATUS = 'Partially Paid' AND STLMT_STATUS = 'Partial Voucher Paid-Invoice Pending') AND  CR_FLAG <> 'Yes' ");
        } else if (null != (Boolean) getBindReceiptCheck().getValue() &&
                   null != (Boolean) getBindPaymentCheck().getValue() && (Boolean) getBindPaymentCheck().getValue() &&
                   (Boolean) getBindReceiptCheck().getValue()) {
            totalSettleOutput.setVisible(false);
            String stlmtStatus = "'Pending for Settlement','Settlement on Hold'";
            String paymentStatus = " 'Fully Paid','On Hold'";
            //            voucherView.setNamedWhereClauseParam("bNewStlmtStatus", "Pending for Settlement");
            //            voucherView.setNamedWhereClauseParam("bNewStlmtStatus2", "Settlement on Hold");
            //            voucherView.setNamedWhereClauseParam("bPayStatus", "On Hold");
            //            voucherView.setNamedWhereClauseParam("bPayStatus2", "Fully Paid");
            //            voucherView.setWhereClause(" (PAYMENT_STATUS = 'Unpaid') " +
            //            "(OR PAYMENT_STATUS = 'Partially Paid') ");
            JSFUtil.addComponentFacesMessage(FacesMessage.SEVERITY_ERROR,
                                             "Select either Apply Payment Only OR Apply Receipt Only", null);

        }

        System.out.println("Query ::" + voucherView.getQuery());
        voucherView.executeQuery();
        System.out.println("voucherView Row Count ::" + voucherView.getRowCount());


        if (null != (Boolean) getBindReceiptCheck().getValue() && (Boolean) getBindReceiptCheck().getValue()) {
            System.out.println("calling calculateTotalSettlementAmount()");
            calculateTotalSettlementAmount();
            if (voucherView.getRowCount() == 0) {
                String message = "Please apply payment first on the pending Vouchers";
                ADFUtils.errorPopup(message);
            }
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(totalSettleOutput);
        //        if (null != (Boolean) getBindReceiptCheck().getValue() && (Boolean) getBindReceiptCheck().getValue()) {
        //            if(null != getBindPaymentCheck()) {
        //                if((Boolean) getBindPaymentCheck().getValue()){
        //                    System.out.println("Not calling calculateTotalSettlementAmount()");
        //                }else{
        //                    System.out.println("calling calculateTotalSettlementAmount()");
        //                    calculateTotalSettlementAmount();
        //                }
        //            }
        //        }
        //        if (getBindReceiptCheck() != null && (Boolean) getBindReceiptCheck().getValue() != null && (Boolean) getBindReceiptCheck().getValue()) {
        //    if (getBindPaymentCheck() != null && (Boolean) getBindPaymentCheck().getValue() != null) {
        //        if ((Boolean) getBindPaymentCheck().getValue()) {
        //            System.out.println("Not calling calculateTotalSettlementAmount()");
        //        } else {
        //            System.out.println("calling calculateTotalSettlementAmount()");
        //            calculateTotalSettlementAmount();
        //        }
        //    }
        //}
    }

    public void onCreateSettlementSave(ActionEvent actionEvent) {
        executeBinding(SAVE_DATA);
        ViewObjectImpl data = (ViewObjectImpl) getDCIteratorBindings("SgsCreateSettlementVO1Iterator").getViewObject();
        data.executeQuery();
        ADFUtils.saveNotifier();
    }

    public void SaveHoldDetails(ActionEvent actionEvent) {
        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding holditer = (DCIteratorBinding) bindings.get("SgsStlmtVoucherVO1Iterator");
        ViewObject holdVO = holditer.getViewObject();


        oracle.jbo.Row[] selectedRows = holdVO.getFilteredRows("SelectRecord", "Yes");
        System.out.println("*****Selected rows****" + selectedRows.length);
        for (oracle.jbo.Row rw : selectedRows) {
            if (rw.getAttribute("PaymentStatus").equals("Unpaid")) {


                rw.setAttribute("PaymentStatus", "On Hold");
            }
        }

        executeBinding("Commit");


    }


    public void SaveReleaseDetails(ActionEvent actionEvent) {
        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding holditer = (DCIteratorBinding) bindings.get("SgsStlmtVoucherVO1Iterator");
        ViewObject holdVO = holditer.getViewObject();


        oracle.jbo.Row[] selectedRows = holdVO.getFilteredRows("SelectRecord", "Yes");
        System.out.println("*****Selected rows****" + selectedRows.length);
        for (oracle.jbo.Row rw : selectedRows) {
            if (rw.getAttribute("PaymentStatus").equals("On Hold")) {


                rw.setAttribute("PaymentStatus", "Unpaid");
            }
        }

        executeBinding("Commit");


    }

    public void onNettingHeaderSelectRecord(ValueChangeEvent valueChangeEvent) {
        DCIteratorBinding settlementData = null;
        settlementData = getDCIteratorBindings("SgsNettingInvoiceVoucherVO1Iterator");
        oracle.jbo.Row[] NettingDataDatarows = settlementData.getAllRowsInRange();
        if ((Boolean) valueChangeEvent.getNewValue()) {
            for (int i = 0; i < NettingDataDatarows.length; i++) {
                NettingDataDatarows[i].setAttribute("SelectedNettingRec", "Yes");
            }
        } else {
            for (int i = 0; i < NettingDataDatarows.length; i++) {

                NettingDataDatarows[i].setAttribute("SelectedNettingRec", "No");

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
        if (null != geos && !geos.isEmpty()) {
            String[] result = new String[2];
            result = geos.split("\\-", 0); // splitting the string at "-"
            String Geo1 = result[0];
            String Geo2 = result[1];
            if (null != Geo1 && !Geo1.isEmpty()) {
                ViewObjectImpl viewImpl1 = null;
                viewImpl1 = (ViewObjectImpl) getDCIteratorBindings("SgsNettingGeo1VO1Iterator").getViewObject();
                viewImpl1.setFullSqlMode(ViewObjectImpl.FULLSQL_MODE_AUGMENTATION);
                viewImpl1.setWhereClause(" GEOGRAPHY_1 IN ( '" + Geo1 + "')");
                viewImpl1.executeQuery();

                ViewObjectImpl viewImplsum = null;
                viewImplsum = (ViewObjectImpl) getDCIteratorBindings("SgsGeo1SumValuesVO1Iterator").getViewObject();
                viewImplsum.setFullSqlMode(ViewObjectImpl.FULLSQL_MODE_AUGMENTATION);
                viewImplsum.setWhereClause(" GEOGRAPHY_1 IN ( '" + Geo1 + "')");
                viewImplsum.executeQuery();
            }
            if (null != Geo2 && !Geo2.isEmpty()) {
                ViewObjectImpl viewImpl2 = null;
                viewImpl2 = (ViewObjectImpl) getDCIteratorBindings("SgsNettingGeo2VO1Iterator").getViewObject();
                viewImpl2.setFullSqlMode(ViewObjectImpl.FULLSQL_MODE_AUGMENTATION);
                viewImpl2.setWhereClause(" GEOGRAPHY_2 IN ( '" + Geo2 + "')");
                viewImpl2.executeQuery();

                ViewObjectImpl viewImplsum = null;
                viewImplsum = (ViewObjectImpl) getDCIteratorBindings("SgsGeo2SumValuesVO1Iterator").getViewObject();
                viewImplsum.setFullSqlMode(ViewObjectImpl.FULLSQL_MODE_AUGMENTATION);
                viewImplsum.setWhereClause(" GEOGRAPHY_2 IN ( '" + Geo2 + "')");
                viewImplsum.executeQuery();
            }
        }

    }

    public void onNettingGeo2SelectRec(ValueChangeEvent valueChangeEvent) {
        DCIteratorBinding geo2Data = null;
        geo2Data = getDCIteratorBindings("SgsNettingGeo2VO1Iterator");
        oracle.jbo.Row[] geo2Datarows = geo2Data.getAllRowsInRange();
        if ((Boolean) valueChangeEvent.getNewValue()) {
            for (int i = 0; i < geo2Datarows.length; i++) {
                geo2Datarows[i].setAttribute("SelectGeo2Rec", "Yes");
            }
        } else {
            for (int i = 0; i < geo2Datarows.length; i++) {
                geo2Datarows[i].setAttribute("SelectGeo2Rec", "No");
            }
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindGeo2SelectCol);
        AdfFacesContext.getCurrentInstance().addPartialTarget(bindSelectAllGeo2);
    }

    public void onNettingGeo1SelectRec(ValueChangeEvent valueChangeEvent) {
        DCIteratorBinding geo1Data = null;
        geo1Data = getDCIteratorBindings("SgsNettingGeo1VO1Iterator");
        oracle.jbo.Row[] geo1Datarows = geo1Data.getAllRowsInRange();
        if ((Boolean) valueChangeEvent.getNewValue()) {
            for (int i = 0; i < geo1Datarows.length; i++) {
                geo1Datarows[i].setAttribute("SelectGeo1Rec", "Yes");
            }
        } else {
            for (int i = 0; i < geo1Datarows.length; i++) {
                geo1Datarows[i].setAttribute("SelectGeo1Rec", "No");
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
        DCIteratorBinding statData = null;
        statData = getDCIteratorBindings("SgsStatisticalDataVO1Iterator");
        oracle.jbo.Row[] statDataDatarows = statData.getAllRowsInRange();
        for (int i = 0; i < statDataDatarows.length; i++) {
            if (null != statDataDatarows[i].getAttribute("StatSelectedRecord") &&
                statDataDatarows[i].getAttribute("StatSelectedRecord").equals("Yes")) {
                statDataDatarows[i].setAttribute("APPROVESTATUS", "Submitted For Approval");
            }
        }
        executeBinding(SAVE_DATA);
        submitstatpopupbind.hide();
    }


    public void onStatisticalSubmitAprovalAttach(ActionEvent actionEvent) {
        DCIteratorBinding statData = null;
        statData = getDCIteratorBindings("SgsStatisticalDataVO1Iterator");
        oracle.jbo.Row[] statDataDatarows = statData.getAllRowsInRange();
        for (int i = 0; i < statDataDatarows.length; i++) {
            if (null != statDataDatarows[i].getAttribute("StatSelectedRecord") &&
                statDataDatarows[i].getAttribute("StatSelectedRecord").equals("Yes")) {
                statDataDatarows[i].setAttribute("APPROVESTATUS", "Submitted For Approval");
            }
        }
        executeBinding(SAVE_DATA);
        submitstatpopupattachbind.hide();
    }


    public void onStatisticalSubmitAprovalDocValidations(ActionEvent actionEvent) {
        DCIteratorBinding statData = null;
        statData = getDCIteratorBindings("SgsStatisticalDataVO1Iterator");
        oracle.jbo.Row[] statDataDatarows = statData.getAllRowsInRange();
        int count = 0;
        for (int i = 0; i < statDataDatarows.length; i++) {
            if (null != statDataDatarows[i].getAttribute("StatSelectedRecord") &&
                statDataDatarows[i].getAttribute("StatSelectedRecord").equals("Yes")) {

                if (null == statDataDatarows[i].getAttribute("DOCATTM") ||
                    statDataDatarows[i].getAttribute("DOCATTM").equals(" ") || statDataDatarows[i].getAttribute("DOCATTM")
                                                                                                  .toString()
                                                                                                  .length() == 0) {
                    count = 1;
                    break;
                }

            }
        }

        if (count > 0) {

            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            this.submitstatpopupattachbind.show(hints);
        } else {
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            this.submitstatpopupbind.show(hints);
        }

    }

    public void onStatisticalApprove(ActionEvent actionEvent) {
        ArrayList<String> pageList = (ArrayList<String>) ADFContext.getCurrent()
                                                                   .getSessionScope()
                                                                   .get("pageList");
        if (null != pageList && !(pageList.isEmpty()) && pageList.contains("ALL_PAGE")) {
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            this.approvepoopupbind.show(hints);
        } else {
            DCIteratorBinding statData = null;
            statData = getDCIteratorBindings("SgsStatisticalDataVO1Iterator");
            oracle.jbo.Row[] statDataDatarows = statData.getAllRowsInRange();
            for (int i = 0; i < statDataDatarows.length; i++) {
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
        DCIteratorBinding statData = null;
        statData = getDCIteratorBindings("SgsStatisticalDataVO1Iterator");
        oracle.jbo.Row[] statDataDatarows = statData.getAllRowsInRange();
        if ((Boolean) valueChangeEvent.getNewValue()) {
            for (int i = 0; i < statDataDatarows.length; i++) {
                statDataDatarows[i].setAttribute("StatSelectedRecord", "Yes");
            }
        } else {
            for (int i = 0; i < statDataDatarows.length; i++) {
                statDataDatarows[i].setAttribute("StatSelectedRecord", "No");
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
            if (null != statDataDatarows[i].getAttribute("StatSelectedRecord") &&
                statDataDatarows[i].getAttribute("StatSelectedRecord").equals("Yes")) {
                if (null != rejectionReasonLOVBind.getValue()) {
                    String reason = (String) rejectionReasonLOVBind.getValue();
                    statDataDatarows[i].setAttribute("RejectedReason", reason);
                }
                if (null != rejectionCommentsBind.getValue()) {
                    String comments = (String) rejectionCommentsBind.getValue();
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
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            String messageText = "File Uploaded successfully";
            FacesMessage fm = new FacesMessage(messageText);
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            context.addMessage(null, fm);

            executeBinding(SAVE_DATA);
            inputFileBind.setValue(null);
            inputFileBind.resetValue();
            AdfFacesContext.getCurrentInstance().addPartialTarget(inputFileBind);
            approvepoopupbind.hide();
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }


    //    public void onFAFileAttachment(ActionEvent actionEvent) {
    //        BindingContainer bindings = getBindingsCont();
    //        DCIteratorBinding faiter = (DCIteratorBinding) bindings.get("SgsFixedAssetsTxnVO1Iterator");
    //        ViewObject faVO = faiter.getViewObject();
    //        oracle.jbo.Row[] selectedRows = faVO.getFilteredRows("Selected", true);
    //        for (oracle.jbo.Row rw : selectedRows) {
    //            if (null != inputFileBindFA.getValue()) {
    //                UploadedFile uploadedFile = (UploadedFile) inputFileBindFA.getValue();
    //                if (null != uploadedFile.getFilename()) {
    //                    String fileName = (String) uploadedFile.getFilename();
    //                    rw.setAttribute("ATTACHMENT", fileName);
    //                    rw.setAttribute("Status", "Approved");
    //                }
    //            }
    //        }
    //        executeBinding(SAVE_DATA);
    //        inputFileBindFA.setValue(null);
    //        inputFileBindFA.resetValue();
    //        AdfFacesContext.getCurrentInstance().addPartialTarget(inputFileBindFA);
    //        approvePopupFABind.hide();
    //
    //    }


    public void onFAFileAttachment(ValueChangeEvent valueChangeEvent) {


        if (valueChangeEvent.getNewValue() != null) {


            String filePath1 = ADFUtils.getPath();

            if (filePath1.equalsIgnoreCase("NOPATH")) {

                FacesContext context = FacesContext.getCurrentInstance();

                String messageText = "Please setup the system path to upload the file.";

                FacesMessage fm = new FacesMessage(messageText);
                fm.setSeverity(FacesMessage.SEVERITY_INFO);
                context.addMessage(null, fm);
            } else {
                try {
                    UploadedFile uploadedFile = (UploadedFile) valueChangeEvent.getNewValue();
                    if (null != uploadedFile) {

                        //                        InputStream inputStream = null;

                        //                        inputStream = uploadedFile.getInputStream();

                        //                        BufferedInputStream bfi = new BufferedInputStream(inputStream);

                        //                        String fileName = uploadedFile.getFilename();
                        String path = null;

                        String tokens = uploadedFile.getFilename();
                        String fileName = uploadedFile.getFilename();
                        String contentType = uploadedFile.getContentType();


                        //                        path = filePath1 + File.separator + fileName;
                        saveFile(filePath1, fileName, uploadedFile);

                        // DCIteratorBinding docs = getDCIteratorBindings("SgsTpaDocAttachment1VO2Iterator");

                        // Row row = docs.getCurrentRow();
                        BindingContainer bindings = getBindingsCont();
                        DCIteratorBinding faiter = (DCIteratorBinding) bindings.get("SgsFixedAssetsTxnVO1Iterator");
                        ViewObject faVO = faiter.getViewObject();
                        oracle.jbo.Row[] selectedRows = faVO.getFilteredRows("Selected", true);
                        for (oracle.jbo.Row rw : selectedRows) {

                            if (null != inputFileBindFA.getValue()) {
                                //                UploadedFile uploadedFile = (UploadedFile) inputFileBindFA.getValue();
                                if (null != uploadedFile.getFilename()) {
                                    //                    fileName = (String) uploadedFile.getFilename();
                                    rw.setAttribute("ATTACHMENT", fileName);
                                    rw.setAttribute("Status", "Approved");
                                    rw.setAttribute("ATTRIBUTE1", path);
                                    rw.setAttribute("ATTRIBUTE2", contentType);

                                }
                            }
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

    }

    public void setRejectionReasonLOVBind(RichSelectOneChoice rejectionReasonLOVBind) {
        this.rejectionReasonLOVBind = rejectionReasonLOVBind;
    }

    public RichSelectOneChoice getRejectionReasonLOVBind() {
        return rejectionReasonLOVBind;
    }

    public void onStdRateFormSave(ActionEvent actionEvent) {
        executeBinding(SAVE_DATA);
        String inputProvider = null;
        DCIteratorBinding dcIteratorbinding = getDCIteratorBindings("SgsStandardRateSetupVO1Iterator");
        Row row = dcIteratorbinding.getCurrentRow();
        LOG.info("Input provider :: " + row.getAttribute("INPUTPROVIDER"));
        ADFContext.getCurrent()
                  .getSessionScope()
                  .put("INPUTPROVIDER", row.getAttribute("INPUTPROVIDER"));
    }

    public void onStdRateRowSelection(ActionEvent actionEvent) {
        DCIteratorBinding dcIteratorbinding = getDCIteratorBindings("SgsStandardRateSetupVO1Iterator");
        Row row = dcIteratorbinding.getCurrentRow();
        LOG.info("Input provider :: " + row.getAttribute("INPUTPROVIDER"));
        ADFContext.getCurrent()
                  .getSessionScope()
                  .put("INPUTPROVIDER", row.getAttribute("INPUTPROVIDER"));
    }


    public void onInvoiceApprove(ActionEvent actionEvent) {
        DCIteratorBinding statData = getDCIteratorBindings("SgsIcInvoiceHeaderVO1Iterator");
        oracle.jbo.Row[] rows = statData.getAllRowsInRange();
        for (int i = 0; i < rows.length; i++) {
            if (null != rows[i].getAttribute("selectInvoiceRecord") &&
                rows[i].getAttribute("selectInvoiceRecord").equals("Yes")) {

                System.out.println("Period 0::" + rows[i].getAttribute("Period"));
                java.util.Date period1 = (java.util.Date) rows[i].getAttribute("Period");
                System.out.println("Period1::" + period1);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                System.out.println(" sdf: " + sdf);
                String dateAsString2 = sdf.format(period1);
                System.out.println(" dateAsString2: " + dateAsString2);
                LocalDate currentDateTest = LocalDate.parse(dateAsString2);
                int day1 = currentDateTest.getMonthValue();
                System.out.println(" day1: " + day1);
                int cutOffDay = returnCutOffDay(day1);
                System.out.println(" cutOffDay: " + cutOffDay);
                // currentDateTest.getMonthValue();
                //                Date d = new Date();
                // Get an instance of LocalTime
                // from date
                //                String dateAsString = "2020-07-18";
                //                // String period =  sdf.format(period1);
                //                LocalDate currentDate = LocalDate.parse(dateAsString);
                //                System.out.println("Specified  date: " + currentDate);
                //                // Get day from date
                //                int day = currentDate.getDayOfMonth();
                //
                //                // Get month from date
                //                Month month1 = currentDate.getMonth();
                //
                //                // Get year from date
                //                int year = currentDate.getYear();
                //
                //                // Print the day, month, and year
                //                System.out.println("Day: " + day);
                //                System.out.println("Month: " + month1);
                //                System.out.println("Year: " + year);

                LocalDate currentdate1 = LocalDate.now();
                System.out.println("Current date: " + currentdate1);
                int currentDay = currentdate1.getDayOfMonth();
                System.out.println("Current day: " + currentDay);


                rows[i].setAttribute("TransactionStatus", "Approved");
                if (currentDay > cutOffDay) {
                    rows[i].setAttribute("TransactionCategory", "Provisional Journal");
                }

            }
        }
        executeBinding(SAVE_DATA);
        invoiceApprovBind.hide();
    }

    public int returnCutOffDay(int day) {
        int dayOFMonth = 0;
        Date d = null;
        String queryString = "SELECT DATE FROM PROV_ENTRY_CUTOFF_TBL WHERE PERIOD=" + day + "";
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            SGSAppModuleImpl am = new SGSAppModuleImpl();
            conn = am.getDBConnection();
            String sqlIdentifier = queryString;
            pst = conn.prepareStatement(sqlIdentifier);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                d = (Date) rs.getDate(1);
            }
            System.out.println("date 0::" + d);

            //            java.util.Date period1 = (java.util.Date) rows[i].getAttribute("Period");
            //            System.out.println("Period1::" + period1);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println(" sdf3: " + sdf);
            String dateAsString = sdf.format(d);
            System.out.println(" dateAsString3: " + dateAsString);
            LocalDate currentDateTest = LocalDate.parse(dateAsString);
            dayOFMonth = currentDateTest.getDayOfMonth();
            System.out.println(" day3 dayOFMonth: " + dayOFMonth);
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


        return dayOFMonth;
    }

    public void onInvoiceReject(ActionEvent actionEvent) {
        DCIteratorBinding invoiceData = getDCIteratorBindings("SgsIcInvoiceHeaderVO1Iterator");
        oracle.jbo.Row[] rows = invoiceData.getAllRowsInRange();
        for (int i = 0; i < rows.length; i++) {
            if (null != rows[i].getAttribute("selectInvoiceRecord") &&
                rows[i].getAttribute("SelecselectInvoiceRecordted").equals("Yes")) {
                rows[i].setAttribute("TransactionStatus", "Rejected");
            }
        }
        executeBinding(SAVE_DATA);
        invoiceRejectBind.hide();
    }

    public void onInvoiceConfirm(ActionEvent actionEvent) {
        DCIteratorBinding invoiceData = getDCIteratorBindings("SgsIcInvoiceHeaderVO1Iterator");
        oracle.jbo.Row[] rows = invoiceData.getAllRowsInRange();
        for (int i = 0; i < rows.length; i++) {
            if (null != rows[i].getAttribute("selectInvoiceRecord") &&
                rows[i].getAttribute("selectInvoiceRecord").equals("Yes")) {
                rows[i].setAttribute("TransactionStatus", "Confirm for Invoicing");
                SGSAppModuleImpl am = new SGSAppModuleImpl();
                String voucherNumber = "PS_Voucher_" + am.getDBSequence1("SEQ_IC_INVOICE_HEADER_VOUCHER");
                String invoiceNumber = "PS_Invoice_" + am.getDBSequence1("SEQ_IC_INVOICE_HEADER_INVOICE");
                rows[i].setAttribute("ReferenceVoucherNum", voucherNumber);
                rows[i].setAttribute("ReferenceInvoiceNum", invoiceNumber);
            }
        }
        executeBinding(SAVE_DATA);
        invoiceConfirmBind.hide();
    }

    public void onInvoiceApproveNo(ActionEvent actionEvent) {
        invoiceApprovBind.hide();
    }

    public void onInvoiceRejectNo(ActionEvent actionEvent) {
        invoiceRejectBind.hide();
    }

    public void onInvoiceConfirmNo(ActionEvent actionEvent) {
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
        submitstatpopupbind.hide();
    }

    public void onStatSubmitAttachNo(ActionEvent actionEvent) {
        submitstatpopupattachbind.hide();
    }

    public void setCreditMemoPopupBind(RichPopup creditMemoPopupBind) {
        this.creditMemoPopupBind = creditMemoPopupBind;
    }

    public RichPopup getCreditMemoPopupBind() {
        return creditMemoPopupBind;
    }

    public void creditMemoSubmitApprovals(ActionEvent actionEvent) {
        String reversalReason = "NONE";
        DCIteratorBinding creditData = getDCIteratorBindings("SgsInvoiceCreditMemoVO1Iterator");
        oracle.jbo.Row[] row = creditData.getAllRowsInRange();
        for (int i = 0; i < row.length; i++) {
            if (null != creditDateBindVal.getValue()) {
                java.util.Date utilDate = (java.util.Date) creditDateBindVal.getValue();
                DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
                java.sql.Date dateCredit = new java.sql.Date(utilDate.getTime());
            }
            //            if (null != percentageReversalBind.getValue()) {
            //                String str = (String) percentageReversalBind.getValue();
            //                double reversalPerc = Double.parseDouble(str);
            //                Object invAmt = row[i].getAttribute("InvoiceAmount");
            //                double invAmt2 = new Double(invAmt.toString());
            //                DecimalFormat df = new DecimalFormat("#.##");
            //                double invoiceAmount = Double.valueOf(df.format(invAmt2));
            //                double reversalAmt = (Double) ((reversalPerc / 100) * (invoiceAmount));
            //                double revAmt = Double.valueOf(df.format(reversalAmt));
            //                row[i].setAttribute("ReversalAmount", revAmt);
            //                row[i].setAttribute("PERCENTAGEREVERSAL", reversalPerc);
            //            }
            BindingContainer bc = this.getBindingsCont();
            JUCtrlListBinding list = (JUCtrlListBinding) bc.get("ReversalReasonLOVVO1");
            String selectedRow = (String) list.getSelectedValue();

            if (null != selectedRow) {
                String type = "REVERSAL_REASON";
                reversalReason = getLookupCode(selectedRow, type);
            }

            System.out.println("Reversal Resaon::" + reversalReason);

            //            BindingContainer bc1 = this.getBindingsCont();
            //            JUCtrlListBinding list1 = (JUCtrlListBinding) bc1.get("ReversalTypeLOVVO1");
            //            String selectedRow1 = (String) list1.getSelectedValue();
            //
            //            String reversalType = "NONE";
            //            if (null != selectedRow1) {
            //                String type = "REVERSAL_TYPE";
            //                reversalType = getLookupCode(selectedRow1, type);
            //            }
            //
            //            if (null != reversalType) {
            //                row[i].setAttribute("REVERSALTYPE", reversalType);
            //            }
            System.out.println("Comments Value ::" + otherCommentBind.getValue());
            if (null != otherCommentBind.getValue() && !("".equals(otherCommentBind.getValue()))) {
                row[i].setAttribute("REVERSALREASON", otherCommentBind.getValue());
            } else {
                if (null != reversalReason) {
                    row[i].setAttribute("REVERSALREASON", reversalReason);

                }
            }

            row[i].setAttribute("STATUS", "New");
        }


        this.creditDateBindVal.setValue(null);
        this.otherCommentBind.setValue(null);
        //        this.percentageReversalBind.setValue(null);

        AdfFacesContext.getCurrentInstance().addPartialTarget(creditDateBindVal);
        AdfFacesContext.getCurrentInstance().addPartialTarget(otherCommentBind);
        //     AdfFacesContext.getCurrentInstance().addPartialTarget(percentageReversalBind);
        // executeBinding(SAVE_DATA);

    }

    public void onCreditMemoClose(ActionEvent actionEvent) {
        creditMemoPopupBind.hide();
    }

    //    public Connection getDBConnection() {
    //        Connection conn = null;
    //        try {
    ////            conn =
    ////                DriverManager.getConnection("jdbc:sqlserver://localhost;instanceName=MSSQLSERVER;databasename=DEVINTER;integratedSecurity=true;");
    //            conn = DriverManager.getConnection("jdbc:sqlserver://ASBCOLPS02:1433;databaseName=DEVINTER","EYUser","Ey@123");
    //
    //        } catch (SQLException sqle) {
    //            // TODO: Add catch code
    //            sqle.printStackTrace();
    //        }
    //        return conn;
    //    }

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

        DCIteratorBinding invoiceCreditData = getDCIteratorBindings("SgsInvoiceCreditMemoVO1Iterator");
        oracle.jbo.Row[] invoiceCreditDatarows = invoiceCreditData.getAllRowsInRange();
        if ((Boolean) valueChangeEvent.getNewValue()) {
            for (int i = 0; i < invoiceCreditDatarows.length; i++) {
                invoiceCreditDatarows[i].setAttribute("SelectCreditRecord", "Yes");
            }
        } else {
            for (int i = 0; i < invoiceCreditDatarows.length; i++) {
                invoiceCreditDatarows[i].setAttribute("SelectCreditRecord", "No");
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

        executeBinding(SAVE_DATA);
        ADFUtils.saveNotifier();
    }

    public void onCreduitMemoInvoke(ActionEvent actionEvent) {
        executeBinding("CreateInsertCredit");
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        this.creditMemoPopupBind.show(hints);

    }

    public void onCreditMemosClose(ActionEvent actionEvent) {

        invoicecreditmemobindpopup.hide();
    }

    public void setInvoicecreditmemobindpopup(RichPopup invoicecreditmemobindpopup) {
        this.invoicecreditmemobindpopup = invoicecreditmemobindpopup;
    }

    public RichPopup getInvoicecreditmemobindpopup() {
        return invoicecreditmemobindpopup;
    }


    public void downloadFileListener(FacesContext facesContext, OutputStream outputStream) throws IOException {
        String filePath1 = ADFUtils.getPath();
        LOG.info("File Path :: " + filePath1);
        if (filePath1.equalsIgnoreCase("NOPATH")) {
        } else {
            File filed = new File(filePath1 + "TPA_Master.xlsx");
            FileInputStream fis;
            byte[] b;
            try {
                fis = new FileInputStream(filed);
                int n;
                while ((n = fis.available()) > 0) {
                    b = new byte[n];
                    int result = fis.read(b);
                    outputStream.write(b, 0, b.length);
                    if (result == -1)
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            outputStream.flush();

        }
    }

    public void onInvoiceSelectAll(ValueChangeEvent valueChangeEvent) {

        DCIteratorBinding invoiceDBData = getDCIteratorBindings("SgsIcInvoiceHeaderVO1Iterator");
        oracle.jbo.Row[] invoiceDBDatarows = invoiceDBData.getAllRowsInRange();
        if ((Boolean) valueChangeEvent.getNewValue()) {
            for (int i = 0; i < invoiceDBDatarows.length; i++) {
                invoiceDBDatarows[i].setAttribute("selectInvoiceRecord", "Yes");
            }
        } else {
            for (int i = 0; i < invoiceDBDatarows.length; i++) {
                invoiceDBDatarows[i].setAttribute("selectInvoiceRecord", "No");
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
        //creditData.getViewObject().executeQuery();
        oracle.jbo.Row[] invoiceDatarows = invoiceData.getAllRowsInRange();
        CommonUtils util = new CommonUtils();
        int nonInvoice = 0;
        int selectedRecords = 0;
        Object user = (Object) util.getSessionScopeValue("_username").toString();

        //        for (int j = 0; j < invoiceDatarows.length; j++) {
        //                if (null != invoiceDatarows[i].getAttribute("selectInvoiceRecord") &&
        //                    invoiceDatarows[i].getAttribute("selectInvoiceRecord").equals("Yes")) {
        //                    }
        //            }

        System.out.println("invoiceDatarows.length ****" + invoiceDatarows.length);

        if (invoiceDatarows.length == 0) {
            FacesContext context = FacesContext.getCurrentInstance();
            String messageText = "Please select a transaction for creation of Credit Transactions";
            FacesMessage fm = new FacesMessage(messageText);
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, fm);
        } else {
            for (int i = 0; i < invoiceDatarows.length; i++) {

                System.out.println("nonInvoice Cat::" + nonInvoice);
                if (null != invoiceDatarows[i].getAttribute("selectInvoiceRecord") &&
                    invoiceDatarows[i].getAttribute("selectInvoiceRecord").equals("Yes")) {
                    String transactionStatus = (String) invoiceDatarows[i].getAttribute("TransactionStatus");
                    System.out.println("transaction Statust::" + transactionStatus);
                    System.out.println("nonInvoice Cat00::" + nonInvoice);
                    if (null != transactionStatus && !(transactionStatus.equalsIgnoreCase("Invoiced In PeopleSoft"))) {
                        nonInvoice = 1;
                        break;
                    }
                    executeBinding("CreateInsertCredit");
                    Row row = creditData.getCurrentRow();
                    row.setAttribute("InvoiceSeqNo", invoiceDatarows[i].getAttribute("InvoiceSeqNo"));
                    row.setAttribute("Period", invoiceDatarows[i].getAttribute("Period"));
                    row.setAttribute("TransactionCategory", invoiceDatarows[i].getAttribute("TransactionCategory"));
                    row.setAttribute("PsftVoucherRef", invoiceDatarows[i].getAttribute("ReferenceVoucherNum"));
                    row.setAttribute("PsftInvoiceRef", invoiceDatarows[i].getAttribute("ReferenceInvoiceNum"));
                    row.setAttribute("NatureOfExpense", invoiceDatarows[i].getAttribute("NATUREOFEXPENSE"));
                    row.setAttribute("FromBu", invoiceDatarows[i].getAttribute("SourceBu"));
                    row.setAttribute("ToBu", invoiceDatarows[i].getAttribute("TargetBu"));
                    row.setAttribute("InvoiceAmount", invoiceDatarows[i].getAttribute("ALLOCATEDHEADERAMOUNT"));
                    //row.setAttribute("ReversalAmount",null);
                    row.setAttribute("InputProvider", invoiceDatarows[i].getAttribute("InputProvider"));
                    row.setAttribute("CreatedDate", invoiceDatarows[i].getAttribute("CreatedDate"));
                    row.setAttribute("CreatedBy", invoiceDatarows[i].getAttribute("CreatedBy"));
                    row.setAttribute("UpdatedDate", invoiceDatarows[i].getAttribute("UpdatedDate"));
                    row.setAttribute("UpdatedBy", invoiceDatarows[i].getAttribute("UpdatedBy"));
                    row.setAttribute("REVERSALREASON", invoiceDatarows[i].getAttribute("REVERSALREASON"));
                    selectedRecords = selectedRecords + 1;
                }

            }
            System.out.println("nonInvoice Cat 11::" + nonInvoice);
            if (nonInvoice == 1) {
                FacesContext context = FacesContext.getCurrentInstance();
                String messageText =
                    "Please select a transaction Invoiced in PeopleSoft for creation of Credit Transactions";
                FacesMessage fm = new FacesMessage(messageText);
                fm.setSeverity(FacesMessage.SEVERITY_ERROR);
                context.addMessage(null, fm);
            } else if (selectedRecords == 0) {
                FacesContext context = FacesContext.getCurrentInstance();
                String messageText = "Please select a transaction for creation of Credit Transactions";
                FacesMessage fm = new FacesMessage(messageText);
                fm.setSeverity(FacesMessage.SEVERITY_ERROR);
                context.addMessage(null, fm);
            } else {

                //executeBinding(SAVE_DATA);
                RichPopup.PopupHints hints = new RichPopup.PopupHints();
                this.invoicecreditmemobindpopup.show(hints);
            }

        }


    }


    public String getLookupCode(String reason, String lookupType) {
        String revReason = null;
        String queryString =
            "SELECT LOOKUP_CODE from SGS_LOOKUP_TABLE WHERE MEANING='" + reason + "' AND LOOKUP_TYPE='" + lookupType +
            "' AND ENABLED='Y'";
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            SGSAppModuleImpl am = new SGSAppModuleImpl();
            conn = am.getDBConnection();
            String sqlIdentifier = queryString;
            pst = conn.prepareStatement(sqlIdentifier);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                revReason = (String) rs.getString(1);
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
        System.out.println("onCreditPopupClose called");
        executeBinding("Rollback");
        invoicecreditmemobindpopup.hide();
    }

    public void onSettlementTypeVL(ValueChangeEvent valueChangeEvent) {

    }


    public void setInputTransactionAmount(RichInputText inputTransactionAmount) {
        this.inputTransactionAmount = inputTransactionAmount;
    }

    public RichInputText getInputTransactionAmount() {
        return inputTransactionAmount;
    }

    public void setInputBankCharge(RichInputText inputBankCharge) {
        this.inputBankCharge = inputBankCharge;
    }

    public RichInputText getInputBankCharge() {
        return inputBankCharge;
    }

    public void setTablePaymentRecords(RichTable tablePaymentRecords) {
        this.tablePaymentRecords = tablePaymentRecords;
    }

    public RichTable getTablePaymentRecords() {
        return tablePaymentRecords;
    }

    public void onCompleteReversalChechBox(ValueChangeEvent valueChangeEvent) {
        if ((Boolean) valueChangeEvent.getNewValue()) {
            percentageReversalBind.setVisible(true);
            percentageGroupBind.setVisible(true);
        } else {
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
        String reverseTypeVal = (String) valueChangeEvent.getNewValue();
        String type = "REVERSAL_TYPE";
        if (null != reverseTypeVal && !(reverseTypeVal.equalsIgnoreCase(""))) {
            String reversalType = getLookupCode(reverseTypeVal, type);
            if (null != valueChangeEvent.getNewValue() && reversalType.equalsIgnoreCase("PERCENTAGE_REVERSAL")) {
                percentageReversalBind.setVisible(true);
                percentageGroupBind.setVisible(true);
            } else {
                percentageReversalBind.setVisible(false);
                percentageGroupBind.setVisible(false);
            }
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(percentageReversalBind);
        AdfFacesContext.getCurrentInstance().addPartialTarget(percentageGroupBind);
    }

    public void onConfirmingInvoiceEvent(ActionEvent actionEvent) {
        DCIteratorBinding dcIteratorbinding = getDCIteratorBindings("SgsFixedAssetsTxnVO1Iterator");

        ViewObject faVO = dcIteratorbinding.getViewObject();
        oracle.jbo.Row[] selectedRows = faVO.getFilteredRows("Selected", true);
        for (oracle.jbo.Row rw : selectedRows) {
            rw.setAttribute("Status", "Confirmed for Invoicing");
        }

    }

    public void selectAllCheckboxValueChangeFA(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        boolean isSelected = ((Boolean) valueChangeEvent.getNewValue()).booleanValue();
        LOG.info("*****is Selected***" + isSelected);
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
                LOG.info("****selected***" + row.getAttribute(SelectedAttribute));
            } else {
                row.setAttribute(SelectedAttribute, "false");
                LOG.info("****Unselected***" + row.getAttribute(SelectedAttribute));
            }
        }
        rs.closeRowSetIterator();
        //Refresh the table
        AdfFacesContext.getCurrentInstance().addPartialTarget(valueChangeEvent.getComponent()
                                                                              .getParent()
                                                                              .getParent());

    }


    public void importFromTxn(ActionEvent actionEvent) {
        LOG.info("Inside Import from Transaction**********************");
        Connection conn = null;
        PreparedStatement pst = null;
        PreparedStatement ps = null;
        try {
            conn = am.getDBConnection();
            String SPsql = "EXEC USP_SCN_FA_IMPORT"; // for stored proc
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


    public void selectAllCheckboxValueChange(ValueChangeEvent valueChangeEvent) {
        boolean isSelected = ((Boolean) valueChangeEvent.getNewValue()).booleanValue();
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
            } else {
                row.setAttribute(SelectedAttribute, "false");
            }
        }
        rs.closeRowSetIterator();
        AdfFacesContext.getCurrentInstance().addPartialTarget(valueChangeEvent.getComponent()
                                                                              .getParent()
                                                                              .getParent());

    }


    public void onAddFAButton(ActionEvent actionEvent) {

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

    public void saveCreateSettlement(ActionEvent actionEvent) {
        executeBinding(SAVE_DATA);
        ADFUtils.saveNotifier();
    }

    public void resetSettlementAmt(ActionEvent actionEvent) {

        //        executeBinding("clearVoCache");
        //
        //        BindingContext bindingContext = BindingContext.getCurrent();
        //        DCBindingContainer bindingContainer = (DCBindingContainer) bindingContext.getCurrentBindingsEntry();
        //        DCIteratorBinding iteratorBinding = bindingContainer.findIteratorBinding("SgsStlmtVoucherVO1Iterator");

        //        iteratorBinding.executeQuery();
        //        ViewObjectImpl data = (ViewObjectImpl) getDCIteratorBindings("SgsStlmtVoucherVO1Iterator").getViewObject();
        //        data.clearCache();
        //        data.executeQuery();
        executeBinding("Rollback");
        this.setBalanceOutputText("Balance : ");
        ADFUtils.resetNotifier();
        AdfFacesContext.getCurrentInstance().addPartialTarget(tablePaymentRecords);


    }


    public void setBalanceOutputText(String balanceOutputText) {
        this.balanceOutputText = balanceOutputText;
    }

    public String getBalanceOutputText() {
        return balanceOutputText;
    }

    public void setBalanceOutputText1(RichOutputText balanceOutputText1) {
        this.balanceOutputText1 = balanceOutputText1;
    }

    public RichOutputText getBalanceOutputText1() {
        return balanceOutputText1;
    }

    public void setTablePaymentRecords1(RichTable tablePaymentRecords1) {
        this.tablePaymentRecords1 = tablePaymentRecords1;
    }

    public RichTable getTablePaymentRecords1() {
        return tablePaymentRecords1;
    }

    public void onCreditMemoSubmit(ActionEvent actionEvent) {
        // Add event code here...
        executeBinding(SAVE_DATA);
        ADFUtils.saveNotifier();

        LOG.info("Inside DRTCROSS CHARGE**********************");
        Connection conn = null;
        PreparedStatement pst = null;

        try {

            conn = am.getDBConnection();
            String SPsql = "EXEC USP_SCN_CREDIT_TXN "; // for stored proc
            pst = conn.prepareStatement(SPsql);

            pst.execute();
        } catch (SQLException sqle) {
            // TODO: Add catch code
            sqle.printStackTrace();
        } finally {
            try {
                conn.close();
                pst.close();
            } catch (SQLException e) {
            }
        }
    }


    public void setbindPaymentCheckBind(RichSelectBooleanCheckbox bindPaymentCheck) {
        this.bindPaymentCheck = bindPaymentCheck;
    }

    public RichSelectBooleanCheckbox getbindPaymentCheckBind() {
        return bindPaymentCheck;
    }

    public void setbindReceiptCheckBind(RichSelectBooleanCheckbox bindReceiptCheck) {
        this.bindReceiptCheck = bindReceiptCheck;
    }

    public RichSelectBooleanCheckbox getbindReceiptCheckBind() {
        return bindReceiptCheck;
    }


    public void setBindPaymentCheck(RichSelectBooleanCheckbox bindPaymentCheck) {
        this.bindPaymentCheck = bindPaymentCheck;
    }

    public RichSelectBooleanCheckbox getBindPaymentCheck() {
        return bindPaymentCheck;
    }

    public void setBindReceiptCheck(RichSelectBooleanCheckbox bindReceiptCheck) {
        this.bindReceiptCheck = bindReceiptCheck;
    }

    public RichSelectBooleanCheckbox getBindReceiptCheck() {
        return bindReceiptCheck;
    }

    public void setBindPsInvoiceNumber(RichInputText bindPsInvoiceNumber) {
        this.bindPsInvoiceNumber = bindPsInvoiceNumber;
    }

    public RichInputText getBindPsInvoiceNumber() {
        return bindPsInvoiceNumber;
    }

    public void setBindPsVoucherNumber(RichInputText bindPsVoucherNumber) {
        this.bindPsVoucherNumber = bindPsVoucherNumber;
    }

    public RichInputText getBindPsVoucherNumber() {
        return bindPsVoucherNumber;
    }

    public void disablePaymentAction(ValueChangeEvent valueChangeEvent) {

        if (valueChangeEvent.getNewValue().equals(true)) {
            purposeCodeLov.setDisabled(false);
            paymentDate.setDisabled(false);
            payBankName.setDisabled(false);
            payBankCode.setDisabled(false);
            paymentMethod.setDisabled(false);
            paymentCurrency.setDisabled(false);
            inputTransactionAmount.setDisabled(false);
            paymentTxnNum.setDisabled(false);

        } else {
            purposeCodeLov.setDisabled(true);
            paymentDate.setDisabled(true);
            payBankName.setDisabled(true);
            payBankCode.setDisabled(true);
            paymentMethod.setDisabled(true);
            paymentCurrency.setDisabled(true);
            inputTransactionAmount.setDisabled(true);
            paymentTxnNum.setDisabled(true);


        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(purposeCodeLov);
        AdfFacesContext.getCurrentInstance().addPartialTarget(paymentDate);
        AdfFacesContext.getCurrentInstance().addPartialTarget(payBankName);
        AdfFacesContext.getCurrentInstance().addPartialTarget(payBankCode);
        AdfFacesContext.getCurrentInstance().addPartialTarget(paymentMethod);
        AdfFacesContext.getCurrentInstance().addPartialTarget(paymentCurrency);
        AdfFacesContext.getCurrentInstance().addPartialTarget(inputTransactionAmount);
        AdfFacesContext.getCurrentInstance().addPartialTarget(paymentTxnNum);

        if (valueChangeEvent.getNewValue().equals(true) &&
            (null != (Boolean) getBindReceiptCheck().getValue() && (Boolean) getBindReceiptCheck().getValue())) {
            txnAmtOnReceipt.setVisible(true);
            totalSettleOutput.setVisible(false);
        } else {
            txnAmtOnReceipt.setVisible(false);
            totalSettleOutput.setVisible(false);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(txnAmtOnReceipt);
        AdfFacesContext.getCurrentInstance().addPartialTarget(totalSettleOutput);
    }

    public void setPurposeCodeLov(RichSelectOneChoice purposeCodeLov) {
        this.purposeCodeLov = purposeCodeLov;
    }

    public RichSelectOneChoice getPurposeCodeLov() {
        return purposeCodeLov;
    }

    public void setPaymentDate(RichInputDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public RichInputDate getPaymentDate() {
        return paymentDate;
    }

    public void setPayBankName(RichSelectOneChoice payBankName) {
        this.payBankName = payBankName;
    }

    public RichSelectOneChoice getPayBankName() {
        return payBankName;
    }

    public void setPayBankCode(RichSelectOneChoice payBankCode) {
        this.payBankCode = payBankCode;
    }

    public RichSelectOneChoice getPayBankCode() {
        return payBankCode;
    }

    public void setPaymentMethod(RichSelectOneChoice paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public RichSelectOneChoice getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentCurrency(RichSelectOneChoice paymentCurrency) {
        this.paymentCurrency = paymentCurrency;
    }

    public RichSelectOneChoice getPaymentCurrency() {
        return paymentCurrency;
    }

    public void disableReceiptAction(ValueChangeEvent valueChangeEvent) {

        if (valueChangeEvent.getNewValue().equals(true)) {
            recPurposeCode.setDisabled(false);
            totalSettleOutput.setVisible(true);
            receiptDate.setDisabled(false);
            recBankName.setDisabled(false);
            recBankCode.setDisabled(false);
            recCurrency.setDisabled(false);
            inputBankCharge.setDisabled(false);
            recTxnRefNum.setDisabled(false);

        } else {
            recPurposeCode.setDisabled(true);
            totalSettleOutput.setVisible(false);
            receiptDate.setDisabled(true);
            recBankName.setDisabled(true);
            recBankCode.setDisabled(true);
            recCurrency.setDisabled(true);
            inputBankCharge.setDisabled(true);
            recTxnRefNum.setDisabled(true);

        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(recPurposeCode);
        AdfFacesContext.getCurrentInstance().addPartialTarget(totalSettleOutput);
        AdfFacesContext.getCurrentInstance().addPartialTarget(receiptDate);
        AdfFacesContext.getCurrentInstance().addPartialTarget(recBankName);
        AdfFacesContext.getCurrentInstance().addPartialTarget(recBankCode);
        AdfFacesContext.getCurrentInstance().addPartialTarget(recCurrency);
        AdfFacesContext.getCurrentInstance().addPartialTarget(inputBankCharge);
        AdfFacesContext.getCurrentInstance().addPartialTarget(recTxnRefNum);

        if (valueChangeEvent.getNewValue().equals(true) &&
            (null != (Boolean) getBindPaymentCheck().getValue() && (Boolean) getBindPaymentCheck().getValue())) {
            txnAmtOnReceipt.setVisible(true);
            totalSettleOutput.setVisible(false);
        } else {
            txnAmtOnReceipt.setVisible(false);
            totalSettleOutput.setVisible(false);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(txnAmtOnReceipt);
        AdfFacesContext.getCurrentInstance().addPartialTarget(totalSettleOutput);
    }


    public void setRecPurposeCode(RichSelectOneChoice recPurposeCode) {
        this.recPurposeCode = recPurposeCode;
    }

    public RichSelectOneChoice getRecPurposeCode() {
        return recPurposeCode;
    }

    public void setReceiptDate(RichInputDate receiptDate) {
        this.receiptDate = receiptDate;
    }

    public RichInputDate getReceiptDate() {
        return receiptDate;
    }

    public void setRecBankName(RichSelectOneChoice recBankName) {
        this.recBankName = recBankName;
    }

    public RichSelectOneChoice getRecBankName() {
        return recBankName;
    }

    public void setRecBankCode(RichSelectOneChoice recBankCode) {
        this.recBankCode = recBankCode;
    }

    public RichSelectOneChoice getRecBankCode() {
        return recBankCode;
    }

    public void setRecCurrency(RichSelectOneChoice recCurrency) {
        this.recCurrency = recCurrency;
    }

    public RichSelectOneChoice getRecCurrency() {
        return recCurrency;
    }


    public void onInvoiceConfirmProcessing(ActionEvent actionEvent) {
        DCIteratorBinding invoiceData = getDCIteratorBindings("SgsIcInvoiceHeaderVO1Iterator");
        oracle.jbo.Row[] rows = invoiceData.getAllRowsInRange();
        for (int i = 0; i < rows.length; i++) {
            if (null != rows[i].getAttribute("selectInvoiceRecord") &&
                rows[i].getAttribute("selectInvoiceRecord").equals("Yes")) {
                rows[i].setAttribute("TransactionStatus", "Confirmed for processing");
            }
        }
        executeBinding(SAVE_DATA);
        invoiceConfirmProcessPopup.hide();
    }

    public void setInvoiceConfirmProcessPopup(RichPopup invoiceConfirmProcessPopup) {
        this.invoiceConfirmProcessPopup = invoiceConfirmProcessPopup;
    }

    public RichPopup getInvoiceConfirmProcessPopup() {
        return invoiceConfirmProcessPopup;
    }

    public void onInvoiceConfirmProcessNo(ActionEvent actionEvent) {
        invoiceConfirmProcessPopup.hide();
    }

    public void setPaymentTxnNum(RichInputText paymentTxnNum) {
        this.paymentTxnNum = paymentTxnNum;
    }

    public RichInputText getPaymentTxnNum() {
        return paymentTxnNum;
    }

    public void setRecTxnRefNum(RichInputText recTxnRefNum) {
        this.recTxnRefNum = recTxnRefNum;
    }

    public RichInputText getRecTxnRefNum() {
        return recTxnRefNum;
    }


    public void amountOnReceiptDisplay(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        System.out.println("bindPaymentCheck::" + getBindPaymentCheck().getValue());
        System.out.println("bindReceiptCheck::" + getBindReceiptCheck().getValue());
        if (null != (Boolean) getBindPaymentCheck().getValue() && (Boolean) getBindPaymentCheck().getValue() &&
            null != (Boolean) getBindReceiptCheck().getValue() && (Boolean) getBindReceiptCheck().getValue()) {
            System.out.println("bindReceiptCheck*******");
            txnAmtOnReceipt.setVisible(true);
        } else {
            txnAmtOnReceipt.setVisible(false);
        }
    }

    public void setTxnAmtOnReceipt(RichOutputText txnAmtOnReceipt) {
        this.txnAmtOnReceipt = txnAmtOnReceipt;
    }

    public RichOutputText getTxnAmtOnReceipt() {
        return txnAmtOnReceipt;
    }

    public void calculateTotalSettlementAmount() {
        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iter = bindings.findIteratorBinding("SgsStlmtVoucherVO1Iterator");
        Double totalSettlementAmount = 0.0;
        RowSetIterator rowSetIterator = iter.getRowSetIterator();
        System.out.println("LENGTH---" + rowSetIterator.getFetchedRowCount());
        //        while (rowSetIterator.hasNext())
        for (Row row : rowSetIterator.getAllRowsInRange()) {
            //            row = rowSetIterator.next();
            Object stlmtAmountObj = row.getAttribute("StlmtAmount");
            if (stlmtAmountObj == null) {

                continue;
            }
            totalSettlementAmount += ((BigDecimal) row.getAttribute("StlmtAmount")).doubleValue();
            System.out.println("totalSettlementAmount--" + totalSettlementAmount);
        }
        System.out.println("Amount---" + totalSettlementAmount);
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        totalSettlementAmount = Double.parseDouble(decimalFormat.format(totalSettlementAmount));
        setTotalSettlementAmount(totalSettlementAmount);
        AdfFacesContext.getCurrentInstance().addPartialTarget(totalSettleOutput);

    }

    public void setTotalSettleOutput(RichOutputText totalSettleOutput) {
        this.totalSettleOutput = totalSettleOutput;
    }

    public RichOutputText getTotalSettleOutput() {
        return totalSettleOutput;
    }

    public void onInvoiceApproveClick(ActionEvent actionEvent) {


        DCIteratorBinding statData = getDCIteratorBindings("SgsIcInvoiceHeaderVO1Iterator");
        oracle.jbo.Row[] rows = statData.getAllRowsInRange();
        int j = 0;
        for (int i = 0; i < rows.length; i++) {
            if (null != rows[i].getAttribute("selectInvoiceRecord") &&
                rows[i].getAttribute("selectInvoiceRecord").equals("Yes")) {

                System.out.println("Period 0::" + rows[i].getAttribute("Period"));
                java.util.Date period1 = (java.util.Date) rows[i].getAttribute("Period");
                System.out.println("Period1::" + period1);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                System.out.println(" sdf: " + sdf);
                String dateAsString2 = sdf.format(period1);
                System.out.println(" dateAsString2: " + dateAsString2);
                LocalDate currentDateTest = LocalDate.parse(dateAsString2);
                int day1 = currentDateTest.getMonthValue();
                System.out.println(" day1: " + day1);
                int cutOffDay = returnCutOffDay(day1);
                System.out.println(" cutOffDay: " + cutOffDay);
                LocalDate currentdate1 = LocalDate.now();
                System.out.println("Current date: " + currentdate1);
                int currentDay = currentdate1.getDayOfMonth();
                System.out.println("Current day: " + currentDay);


                rows[i].setAttribute("TransactionStatus", "Approved");
                if (currentDay > cutOffDay) {
                    j = 1;
                    break;
                }

            }
        }

        if (j == 1) {
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            this.invoiceApprovBind.show(hints);

        } else {
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            this.invoiceApproveYesNoPopup.show(hints);


        }
        //        executeBinding(SAVE_DATA);
        //        invoiceApproveYesNoPopup.hide();
    }

    public void setInvoiceApproveYesNoPopup(RichPopup invoiceApproveYesNoPopup) {
        this.invoiceApproveYesNoPopup = invoiceApproveYesNoPopup;
    }

    public RichPopup getInvoiceApproveYesNoPopup() {
        return invoiceApproveYesNoPopup;
    }

    public void onInvoiceApproveYes(ActionEvent actionEvent) {
        DCIteratorBinding statData = getDCIteratorBindings("SgsIcInvoiceHeaderVO1Iterator");
        oracle.jbo.Row[] rows = statData.getAllRowsInRange();
        for (int i = 0; i < rows.length; i++) {
            if (null != rows[i].getAttribute("selectInvoiceRecord") &&
                rows[i].getAttribute("selectInvoiceRecord").equals("Yes")) {

                System.out.println("Period 0::" + rows[i].getAttribute("Period"));
                java.util.Date period1 = (java.util.Date) rows[i].getAttribute("Period");
                System.out.println("Period1::" + period1);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                System.out.println(" sdf: " + sdf);
                String dateAsString2 = sdf.format(period1);
                System.out.println(" dateAsString2: " + dateAsString2);
                LocalDate currentDateTest = LocalDate.parse(dateAsString2);
                int day1 = currentDateTest.getMonthValue();
                System.out.println(" day1: " + day1);
                int cutOffDay = returnCutOffDay(day1);
                System.out.println(" cutOffDay: " + cutOffDay);
                // currentDateTest.getMonthValue();
                //                Date d = new Date();
                // Get an instance of LocalTime
                // from date
                //                String dateAsString = "2020-07-18";
                //                // String period =  sdf.format(period1);
                //                LocalDate currentDate = LocalDate.parse(dateAsString);
                //                System.out.println("Specified  date: " + currentDate);
                //                // Get day from date
                //                int day = currentDate.getDayOfMonth();
                //
                //                // Get month from date
                //                Month month1 = currentDate.getMonth();
                //
                //                // Get year from date
                //                int year = currentDate.getYear();
                //
                //                // Print the day, month, and year
                //                System.out.println("Day: " + day);
                //                System.out.println("Month: " + month1);
                //                System.out.println("Year: " + year);

                LocalDate currentdate1 = LocalDate.now();
                System.out.println("Current date: " + currentdate1);
                int currentDay = currentdate1.getDayOfMonth();
                System.out.println("Current day: " + currentDay);


                rows[i].setAttribute("TransactionStatus", "Approved");
                if (currentDay > cutOffDay) {
                    rows[i].setAttribute("TransactionCategory", "Provisional Journal");
                }

            }
        }
        executeBinding(SAVE_DATA);
        invoiceApproveYesNoPopup.hide();
    }

    public void onInvoiceApproveNoClick(ActionEvent actionEvent) {
        invoiceApproveYesNoPopup.hide();

    }


    public void setBindPaymentRefNum(RichInputText bindPaymentRefNum) {
        this.bindPaymentRefNum = bindPaymentRefNum;
    }

    public RichInputText getBindPaymentRefNum() {
        return bindPaymentRefNum;
    }


    public void CreditTxnPopupCancel(PopupCanceledEvent popupCanceledEvent) {
        // Add event code here...
        try {
            System.out.println("CreditTxnPopupCancel");
            executeBinding("Rollback");
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }

    public void setFaRejectBind(RichPopup faRejectBind) {
        this.faRejectBind = faRejectBind;
    }

    public RichPopup getFaRejectBind() {
        return faRejectBind;
    }

    public void onFaReject(ActionEvent actionEvent) {
        // Add event code here...
        try {
            DCIteratorBinding data = getDCIteratorBindings("SgsFixedAssetsTxnVO1Iterator");
            oracle.jbo.Row[] rows = data.getAllRowsInRange();
            //        for (int i = 0; i < rows.length; i++) {
            //            if (null != rows[i].getAttribute("Selected") &&
            //                rows[i].getAttribute("Selected").equals("true")) {
            //                rows[i].setAttribute("Status", "Rejected");
            //            }
            //        }
            for (Row rw : rows) {
                System.out.println("selected value : " + rw.getAttribute("Selected"));
                if (rw.getAttribute("Selected").equals(true)) {
                    rw.setAttribute("Status", "Rejected");

                }

            }

        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
        executeBinding(SAVE_DATA);
        faRejectBind.hide();
    }

    public void onFaRejectNo(ActionEvent actionEvent) {
        // Add event code here...
        faRejectBind.hide();
    }


    public void OnFAUpload(ActionEvent actionEvent) {
        // Add event code here...
        FacesContext context = FacesContext.getCurrentInstance();
        String messageText = "File Uploaded successfully";
        FacesMessage fm = new FacesMessage(messageText);
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        context.addMessage(null, fm);

        executeBinding(SAVE_DATA);
        inputFileBindFA.setValue(null);
        inputFileBindFA.resetValue();
        AdfFacesContext.getCurrentInstance().addPartialTarget(inputFileBindFA);
        approvePopupFABind.hide();
    }

    public void handleGeoChange(ValueChangeEvent valueChangeEvent) {
        System.out.println("--------------------------inside handleGeoChange------------ ");
        String selectedGeo = valueChangeEvent.getNewValue().toString();
        System.out.println("selected geo----" + selectedGeo);
        try {
            //            UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
            //            RichColumn TaxRateIdenHide = (RichColumn) viewRoot.findComponent("taxRateAppWhtBind:c15");
            if (selectedGeo.equalsIgnoreCase("IND")) {

                taxRateIdenColumnBind.setRendered(true);
            } else {
                taxRateIdenColumnBind.setRendered(false);
            }
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }

    public void setTaxRateAppWhtBind(RichTable taxRateAppWhtBind) {
        this.taxRateAppWhtBind = taxRateAppWhtBind;
    }

    public RichTable getTaxRateAppWhtBind() {
        return taxRateAppWhtBind;
    }

    public void setBindTaxRateIdenWht(RichColumn bindTaxRateIdenWht) {
        this.bindTaxRateIdenWht = bindTaxRateIdenWht;
    }

    public RichColumn getBindTaxRateIdenWht() {
        return bindTaxRateIdenWht;
    }

    public void setTaxRateIdenColumnBind(RichColumn taxRateIdenColumnBind) {
        this.taxRateIdenColumnBind = taxRateIdenColumnBind;
    }

    public RichColumn getTaxRateIdenColumnBind() {
        return taxRateIdenColumnBind;
    }

    public void handleTRCChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        System.out.println("--------------------------inside handleTRCChange------------ ");
        String selectedDocType = valueChangeEvent.getNewValue().toString();
        System.out.println("selected DocType----" + selectedDocType);
        try {
            trcReadOnly1 = !(selectedDocType != null && selectedDocType.equalsIgnoreCase("0"));
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }


    public void setDueDateBind(RichColumn dueDateBind) {
        this.dueDateBind = dueDateBind;
    }

    public RichColumn getDueDateBind() {
        return dueDateBind;
    }

    public void setTrcReadOnly1(boolean trcReadOnly1) {
        this.trcReadOnly1 = trcReadOnly1;
    }

    public boolean getTrcReadOnly1() {
        return trcReadOnly1;
    }


    public void onStatUpload(ValueChangeEvent valueChangeEvent) {
        System.out.println("---------------inside onStatUpload-------------------");
        if (valueChangeEvent.getNewValue() != null) {
            String filePath1 = ADFUtils.getPath();
            if (filePath1.equalsIgnoreCase("NOPATH")) {
                FacesContext context = FacesContext.getCurrentInstance();
                String messageText = "Please setup the system path to upload the file.";
                FacesMessage fm = new FacesMessage(messageText);
                fm.setSeverity(FacesMessage.SEVERITY_INFO);
                context.addMessage(null, fm);
            } else {
                try {
                    UploadedFile uploadedFile = (UploadedFile) valueChangeEvent.getNewValue();
                    if (null != uploadedFile) {


                        String path = null;
                        String tokens = uploadedFile.getFilename();
                        String fileName = uploadedFile.getFilename();
                        String contentType = uploadedFile.getContentType();

                        saveFile(filePath1, fileName, uploadedFile);

                        BindingContainer bindings = getBindingsCont();
                        DCIteratorBinding faiter = (DCIteratorBinding) bindings.get("SgsStatisticalDataVO1Iterator");
                        ViewObject faVO = faiter.getViewObject();
                        oracle.jbo.Row[] selectedRows = faVO.getFilteredRows("StatSelectedRecord", "Yes");
                        System.out.println("selectedrow--------" + selectedRows.length);
                        for (oracle.jbo.Row rw : selectedRows) {

                            if (null != inputFileBind.getValue()) {
                                if (null != uploadedFile.getFilename()) {
                                    rw.setAttribute("EMAILATTACHMENT", fileName);
                                    rw.setAttribute("APPROVESTATUS", "Approved");
                                    rw.setAttribute("Attribute1", path);
                                    rw.setAttribute("Attribute2", contentType);

                                }
                            }
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void onStatDocsDownload(FacesContext facesContext,
                                   OutputStream outputStream) {
        // Add event code here...
        DCBindingContainer bindingContainer =
            (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding imageIter = (DCIteratorBinding) bindingContainer.get("SgsStatisticalDataVO1Iterator");
        ViewObject vo = imageIter.getViewObject();
        oracle.jbo.Row currentRow = (oracle.jbo.Row) vo.getCurrentRow();
        String filePath = (String) currentRow.getAttribute("Attribute1");
        String fileName = (String) currentRow.getAttribute("EMAILATTACHMENT");

        String filePath1 = ADFUtils.getPath();
        LOG.info("File Path :: " + filePath1);

        try {
            if (filePath1.equalsIgnoreCase("NOPATH")) {
                System.out.println("No Path selected");
            }

            else if (null != filePath1 && null != fileName) {
                File f = new File(filePath1 + File.separator + fileName);
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

    public void onDocAppTds(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        System.out.println("--------------------------inside onDocAppTds------------ ");
        String selectedDocAppl = valueChangeEvent.getNewValue().toString();
        System.out.println("selected doc applicability----" + selectedDocAppl);
        try {
            DCBindingContainer bindingContainer =
                (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding iter = (DCIteratorBinding) bindingContainer.get("SgsTdsWhtRateApplicabilityVO1Iterator");
            Row row = iter.getCurrentRow();
            if (selectedDocAppl.equalsIgnoreCase("1")) {
                taxRate = !(selectedDocAppl != null && selectedDocAppl.equalsIgnoreCase("2"));

                taxRateNoDoc = !(selectedDocAppl != null && selectedDocAppl.equalsIgnoreCase("1"));

                row.setAttribute("WithholdingTaxRate", 0);
            } else {
                taxRate = !(selectedDocAppl != null && selectedDocAppl.equalsIgnoreCase("2"));

                taxRateNoDoc = !(selectedDocAppl != null && selectedDocAppl.equalsIgnoreCase("1"));
                row.setAttribute("WitholdingTaxRateNoTin", 0);
            }

        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }

    public void setTaxRate(boolean taxRate) {
        this.taxRate = taxRate;
    }

    public boolean getTaxRate() {
        return taxRate;
    }

    public void setTaxRateNoDoc(boolean taxRateNoDoc) {
        this.taxRateNoDoc = taxRateNoDoc;
    }

    public boolean getTaxRateNoDoc() {
        return taxRateNoDoc;
    }

    public void overRidingNettingLimit(ActionEvent actionEvent) {
        DCIteratorBinding nettingData = null;
        nettingData = getDCIteratorBindings("SgsNetHeaderTblVO1Iterator");
        oracle.jbo.Row[] nettingDataDatarows = nettingData.getAllRowsInRange();
        int rowCount = 0;
        System.out.println("NettingDataDatarows Length::" + nettingDataDatarows.length);
        System.out.println("Before rowCount::" + rowCount);
        for (int i = 0; i < nettingDataDatarows.length; i++) {
            System.out.println("Selected Record ::" + nettingDataDatarows[i].getAttribute("selectedRecord"));
            if (null != nettingDataDatarows[i].getAttribute("selectedRecord") &&
                nettingDataDatarows[i].getAttribute("selectedRecord").equals("Yes")) {
                rowCount++;
            }
        }
        System.out.println("After rowCount::" + rowCount);

        if (rowCount > 1) {
            FacesContext context = FacesContext.getCurrentInstance();
            String messageText = "Please select only one record to Override the Netting Limit.";
            FacesMessage fm = new FacesMessage(messageText);
            fm.setSeverity(FacesMessage.SEVERITY_WARN);
            context.addMessage(null, fm);

        } else if (rowCount == 1) {
            for (int i = 0; i < nettingDataDatarows.length; i++) {
                System.out.println("Selected Record ::" + nettingDataDatarows[i].getAttribute("selectedRecord"));
                if (null != nettingDataDatarows[i].getAttribute("selectedRecord") &&
                    nettingDataDatarows[i].getAttribute("selectedRecord").equals("Yes")) {
                    //            DCIteratorBinding dcIteratorbinding = getDCIteratorBindings("SgsNetHeaderTblVO1Iterator");
                    //            Row row = dcIteratorbinding.getCurrentRow();
                    String nettingId = (String) nettingDataDatarows[i].getAttribute("NettingId");
                    System.out.println("Netting ID ::" + nettingId);
                    if (null != nettingId && !nettingId.isEmpty()) {
                        String[] result = new String[2];
                        result = nettingId.split("\\-", 0); // splitting the string at "-"
                        String Geo1 = result[0];
                        String Geo2 = result[1];
                        System.out.println("Geo1 ::" + Geo1);
                        System.out.println("Geo2 ::" + Geo2);
                        CommonUtils util = new CommonUtils();
                        Object user = (Object) util.getSessionScopeValue("_username").toString();
                        ADFContext.getCurrent()
                                  .getPageFlowScope()
                                  .put("Geo1", Geo1);
                        ADFContext.getCurrent()
                                  .getPageFlowScope()
                                  .put("Geo2", Geo2);
                        ADFContext.getCurrent()
                                  .getPageFlowScope()
                                  .put("CalculatedIcAllowableLimit",
                                       nettingDataDatarows[i].getAttribute("NetAllowIcTran"));
                        ADFContext.getCurrent()
                                  .getPageFlowScope()
                                  .put("UserIcAllowableLimit", null);
                        ADFContext.getCurrent()
                                  .getPageFlowScope()
                                  .put("CalculatedCCAllowableLimit",
                                       nettingDataDatarows[i].getAttribute("NetAllowArColl"));
                        ADFContext.getCurrent()
                                  .getPageFlowScope()
                                  .put("UserCCAllowableLimit", null);
                        ADFContext.getCurrent()
                                  .getPageFlowScope()
                                  .put("createdBy", user);
                        ADFContext.getCurrent()
                                  .getPageFlowScope()
                                  .put("creationDate", new Date());
                        ADFContext.getCurrent()
                                  .getPageFlowScope()
                                  .put("Remarks", null);

                    }

                    RichPopup.PopupHints hints = new RichPopup.PopupHints();
                    this.nettingovertidepopup.show(hints);
                }
            }


        }

    }

    public void overRidingClosePopup(ActionEvent actionEvent) {

        this.nettingovertidepopup.hide();
        ADFContext.getCurrent()
                  .getPageFlowScope()
                  .put("Geo1", null);
        ADFContext.getCurrent()
                  .getPageFlowScope()
                  .put("Geo2", null);
        ADFContext.getCurrent()
                  .getPageFlowScope()
                  .put("CalculatedIcAllowableLimit", null);
        // ADFContext.getCurrent().getPageFlowScope().put("UserIcAllowableLimit", null);
        ADFContext.getCurrent()
                  .getPageFlowScope()
                  .put("CalculatedCCAllowableLimit", null);
        // ADFContext.getCurrent().getPageFlowScope().put("UserCCAllowableLimit", null);
        ADFContext.getCurrent()
                  .getPageFlowScope()
                  .put("createdBy", null);
        ADFContext.getCurrent()
                  .getPageFlowScope()
                  .put("creationDate", null);
        ADFContext.getCurrent()
                  .getPageFlowScope()
                  .put("Remarks", null);
        setCcAllowableLimit(null);
        setIcAllowableLimitBind(null);
        setNettingRemarksBind(null);

    }

    public void overRidingSavePopup(ActionEvent actionEvent) {
        int count = 0;
        DCIteratorBinding nettingData = null;
        nettingData = getDCIteratorBindings("SgsNetHeaderTblVO1Iterator");
        oracle.jbo.Row[] nettingDataDatarows = nettingData.getAllRowsInRange();
        for (int i = 0; i < nettingDataDatarows.length; i++) {
            System.out.println("Selected Record ::" + nettingDataDatarows[i].getAttribute("selectedRecord"));
            if (null != nettingDataDatarows[i].getAttribute("selectedRecord") &&
                nettingDataDatarows[i].getAttribute("selectedRecord").equals("Yes")) {
                count++;
            }
        }

        if (count == 1) {
            for (int i = 0; i < nettingDataDatarows.length; i++) {
                System.out.println("Selected Record ::" + nettingDataDatarows[i].getAttribute("selectedRecord"));
                if (null != nettingDataDatarows[i].getAttribute("selectedRecord") &&
                    nettingDataDatarows[i].getAttribute("selectedRecord").equals("Yes")) {

                    //        DCIteratorBinding dcIteratorbinding = getDCIteratorBindings("SgsNetHeaderTblVO1Iterator");
                    //        Row row = dcIteratorbinding.getCurrentRow();
                    System.out.println("GEO1 ::" + ADFContext.getCurrent()
                                                             .getPageFlowScope()
                                                             .get("Geo1"));
                    System.out.println("GEO2 ::" + ADFContext.getCurrent()
                                                             .getPageFlowScope()
                                                             .get("Geo2"));
                    System.out.println("UserIcAllowableLimit ::" + ADFContext.getCurrent()
                                                                             .getPageFlowScope()
                                                                             .get("UserIcAllowableLimit"));
                    System.out.println("UserCCAllowableLimit ::" + ADFContext.getCurrent()
                                                                             .getPageFlowScope()
                                                                             .get("UserCCAllowableLimit"));
                    if (null != icAllowableLimitBind.getValue()) {
                        nettingDataDatarows[i].setAttribute("NETLIMITFIXARCOLL", icAllowableLimitBind.getValue());
                    }

                    if (null != ccAllowableLimit.getValue()) {
                        nettingDataDatarows[i].setAttribute("NETLIMITFIXICTRANS", ccAllowableLimit.getValue());
                    }
                    if (null != nettingRemarksBind.getValue()) {
                        System.out.println("Remarks ::" + nettingRemarksBind.getValue());
                        nettingDataDatarows[i].setAttribute("REMARKS", nettingRemarksBind.getValue());
                    }
                   
                        nettingDataDatarows[i].setAttribute("Attribute1", "Y");
                   
                    double NetIcAllLimCalRead = ((BigDecimal) netIcAllLimCalBind.getValue()).doubleValue();

                    Double EnterNetIcLimOf = null;
                    if (icAllowableLimitBind.getValue() instanceof String) {
                        String icAllowableLimitValue = (String) icAllowableLimitBind.getValue();
                        if (!icAllowableLimitValue.isEmpty()) {
                            EnterNetIcLimOf = Double.parseDouble(icAllowableLimitValue);
                        }
                    }

                    double NettingCcAllLimCal = ((BigDecimal) netCcAllLimCal.getValue()).doubleValue();

                    Double EnterccAllowableLimit = null;
                    if (ccAllowableLimit.getValue() instanceof String) {
                        String ccAllowableLimitValue = (String) ccAllowableLimit.getValue();
                        if (!ccAllowableLimitValue.isEmpty()) {
                            EnterccAllowableLimit = Double.parseDouble(ccAllowableLimitValue);

                        }
                    }
                    System.out.println("EnterNetIcLimOf====================" + EnterNetIcLimOf);
                    System.out.println("EnterccAllowableLimit====================" + EnterccAllowableLimit);


                    if (EnterNetIcLimOf != null && (EnterNetIcLimOf > NetIcAllLimCalRead)) {
                        FacesContext context = FacesContext.getCurrentInstance();
                        String messageText =
                            "Netting Limit fixed by users cannot be greater than Netting Allowable Limit";
                        FacesMessage fm = new FacesMessage(messageText);
                        fm.setSeverity(FacesMessage.SEVERITY_WARN);
                        context.addMessage(null, fm);

                    } else if (EnterccAllowableLimit != null && (EnterccAllowableLimit > NettingCcAllLimCal)) {
                        FacesContext context = FacesContext.getCurrentInstance();
                        String messageText =
                            "Netting Limit fixed by users cannot be greater than Netting Allowable Limit";
                        FacesMessage fm = new FacesMessage(messageText);
                        fm.setSeverity(FacesMessage.SEVERITY_WARN);
                        context.addMessage(null, fm);

                    } else {
                        System.out.println("comminted-----*");

                        executeBinding(SAVE_DATA);
                        approveAction();
                        ADFContext.getCurrent()
                                  .getPageFlowScope()
                                  .put("Geo1", null);
                        ADFContext.getCurrent()
                                  .getPageFlowScope()
                                  .put("Geo2", null);
                        ADFContext.getCurrent()
                                  .getPageFlowScope()
                                  .put("CalculatedIcAllowableLimit", null);
                        // ADFContext.getCurrent().getPageFlowScope().put("UserIcAllowableLimit", null);
                        ADFContext.getCurrent()
                                  .getPageFlowScope()
                                  .put("CalculatedCCAllowableLimit", null);
                        // ADFContext.getCurrent().getPageFlowScope().put("UserCCAllowableLimit", null);
                        ADFContext.getCurrent()
                                  .getPageFlowScope()
                                  .put("createdBy", null);
                        ADFContext.getCurrent()
                                  .getPageFlowScope()
                                  .put("creationDate", null);
                        ADFContext.getCurrent()
                                  .getPageFlowScope()
                                  .put("Remarks", null);
                        this.nettingovertidepopup.hide();
                        setCcAllowableLimit(null);
                        setIcAllowableLimitBind(null);
                        setNettingRemarksBind(null);
                    }
                }
            }
        }


    }

    public void setNettingovertidepopup(RichPopup nettingovertidepopup) {
        this.nettingovertidepopup = nettingovertidepopup;
    }

    public RichPopup getNettingovertidepopup() {
        return nettingovertidepopup;
    }

    public void setIcAllowableLimitBind(RichInputText icAllowableLimitBind) {
        this.icAllowableLimitBind = icAllowableLimitBind;
    }

    public RichInputText getIcAllowableLimitBind() {
        return icAllowableLimitBind;
    }

    public void setCcAllowableLimit(RichInputText ccAllowableLimit) {
        this.ccAllowableLimit = ccAllowableLimit;
    }

    public RichInputText getCcAllowableLimit() {
        return ccAllowableLimit;
    }

    public void setNettingRemarksBind(RichInputText nettingRemarksBind) {
        this.nettingRemarksBind = nettingRemarksBind;
    }

    public RichInputText getNettingRemarksBind() {
        return nettingRemarksBind;

    }

    public void setOncolumnSelectRecord(RichColumn oncolumnSelectRecord) {
        this.oncolumnSelectRecord = oncolumnSelectRecord;
    }

    public RichColumn getOncolumnSelectRecord() {
        return oncolumnSelectRecord;
    }

    public void setNettingselectcheckbox(RichSelectBooleanCheckbox nettingselectcheckbox) {
        this.nettingselectcheckbox = nettingselectcheckbox;
    }

    public RichSelectBooleanCheckbox getNettingselectcheckbox() {
        return nettingselectcheckbox;
    }

    public void setOnNettingSelectAll(RichSelectBooleanCheckbox onNettingSelectAll) {
        this.onNettingSelectAll = onNettingSelectAll;
    }

    public RichSelectBooleanCheckbox getOnNettingSelectAll() {
        return onNettingSelectAll;
    }

    public void onNettingSelectAll(ValueChangeEvent valueChangeEvent) {
        DCIteratorBinding nettingData = null;
        nettingData = getDCIteratorBindings("SgsNetHeaderTblVO1Iterator");
        oracle.jbo.Row[] nettingDataDatarows = nettingData.getAllRowsInRange();
        if ((Boolean) valueChangeEvent.getNewValue()) {
            for (int i = 0; i < nettingDataDatarows.length; i++) {
                nettingDataDatarows[i].setAttribute("selectedRecord", "Yes");
            }
        } else {
            for (int i = 0; i < nettingDataDatarows.length; i++) {
                nettingDataDatarows[i].setAttribute("selectedRecord", "No");
            }
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(nettingselectcheckbox);
        AdfFacesContext.getCurrentInstance().addPartialTarget(oncolumnSelectRecord);
    }

    public void setNetIcAllLimCalBind(RichInputText netIcAllLimCalBind) {
        this.netIcAllLimCalBind = netIcAllLimCalBind;
    }

    public RichInputText getNetIcAllLimCalBind() {
        return netIcAllLimCalBind;
    }


    public void onCreditTransactionInSettlement(ActionEvent actionEvent) {
        //        executeBinding("Rollback");
        DCIteratorBinding settlemtntData = getDCIteratorBindings("SgsStlmtVoucherVO1Iterator");
        DCIteratorBinding creditData = getDCIteratorBindings("SgsInvoiceCreditMemoVO1Iterator");
        //creditData.getViewObject().executeQuery();
        oracle.jbo.Row[] settlemtntDatarows = settlemtntData.getAllRowsInRange();
        CommonUtils util = new CommonUtils();
        int nonInvoice = 0;
        int selectedRecords = 0;
        Object user = (Object) util.getSessionScopeValue("_username").toString();
        int transBreak = 0;
        int paymentBreak = 0;

        System.out.println("settlemtntDatarows Length ::" + settlemtntDatarows.length);
        if (settlemtntDatarows.length == 0) {
            FacesContext context = FacesContext.getCurrentInstance();
            String messageText = "Please select a transaction for creation of Credit Transactions";
            FacesMessage fm = new FacesMessage(messageText);
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, fm);
        } else {
            for (int i = 0; i < settlemtntDatarows.length; i++) {
                System.out.println("SelectRecord ::" + settlemtntDatarows[i].getAttribute("SelectRecord"));
                if (null != settlemtntDatarows[i].getAttribute("SelectRecord") &&
                    settlemtntDatarows[i].getAttribute("SelectRecord").equals("Yes")) {
                    String transCategory = (String) settlemtntDatarows[i].getAttribute("TRANSACTIONCATEGORY");
                    System.out.println("transCategory ::" + transCategory);
                    if (null != transCategory && transCategory.equalsIgnoreCase("Credit Transaction")) {
                        transBreak = 1;
                        break;
                    }
                    String paymentStatus = (String) settlemtntDatarows[i].getAttribute("PaymentStatus");
                    System.out.println("paymentStatus ::" + paymentStatus);

                    if (null != paymentStatus && (paymentStatus.equalsIgnoreCase("NA"))) {
                        paymentBreak = 2;
                        break;
                    }
                    if (null != paymentStatus && !(paymentStatus.equalsIgnoreCase("Unpaid"))) {
                        paymentBreak = 1;
                        break;
                    }


                    System.out.println("SelectRecord ::" + settlemtntDatarows[i].getAttribute("SelectRecord"));
                    executeBinding("CreateInsertCreditInStltmt");
                    Row row = creditData.getCurrentRow();
                    row.setAttribute("InvoiceSeqNo", settlemtntDatarows[i].getAttribute("WBINVSEQ"));
                    row.setAttribute("Period", settlemtntDatarows[i].getAttribute("Period"));
                    row.setAttribute("TransactionCategory", settlemtntDatarows[i].getAttribute("TRANSACTIONCATEGORY"));
                    row.setAttribute("PsftVoucherRef", settlemtntDatarows[i].getAttribute("PsVoucherNo"));
                    row.setAttribute("PsftInvoiceRef", settlemtntDatarows[i].getAttribute("RefToArInvoice"));
                    row.setAttribute("NatureOfExpense", settlemtntDatarows[i].getAttribute("NATUREOFEXPENSE"));
                    row.setAttribute("FromBu", settlemtntDatarows[i].getAttribute("IcSupplierBu"));
                    row.setAttribute("ToBu", settlemtntDatarows[i].getAttribute("IcCustomerBu"));
                    row.setAttribute("InvoiceAmount", settlemtntDatarows[i].getAttribute("VoucherAmount"));
                    //row.setAttribute("ReversalAmount",null);
                    row.setAttribute("InputProvider", settlemtntDatarows[i].getAttribute("INPUTPROVIDER"));
                    row.setAttribute("CreatedDate", settlemtntDatarows[i].getAttribute("CreatedDate"));
                    row.setAttribute("CreatedBy", user);
                    row.setAttribute("UpdatedDate", settlemtntDatarows[i].getAttribute("UpdatedDate"));
                    row.setAttribute("UpdatedBy", user);


                    //                    System.out.println("InvoiceSeqNo::::"+ settlemtntDatarows[i].getAttribute("INVOICESEQNO"));
                    //                    System.out.println("Period::"+ settlemtntDatarows[i].getAttribute("Period"));
                    //                    System.out.println("TransactionCategory::"+ settlemtntDatarows[i].getAttribute("TRANSACTIONCATEGORY"));
                    //                    System.out.println("PsftVoucherRef::"+ settlemtntDatarows[i].getAttribute("PsVoucherNo"));
                    //                    System.out.println("PsftInvoiceRef::"+ settlemtntDatarows[i].getAttribute("RefToArInvoice"));
                    //                    System.out.println("NatureOfExpense::"+ settlemtntDatarows[i].getAttribute("NATUREOFEXPENSE"));
                    //                    System.out.println("FromBu::"+ settlemtntDatarows[i].getAttribute("IcSupplierBu"));
                    //                    System.out.println("ToBu::"+ settlemtntDatarows[i].getAttribute("IcCustomerBu"));
                    //                    System.out.println("InvoiceAmount::"+ settlemtntDatarows[i].getAttribute("VoucherAmount"));
                    //                    System.out.println("InputProvider::"+ settlemtntDatarows[i].getAttribute("INPUTPROVIDER"));
                    //                    System.out.println("CreatedDate::"+ settlemtntDatarows[i].getAttribute("CreatedDate"));
                    //                    System.out.println("CreatedBy::"+ settlemtntDatarows[i].getAttribute("CreatedBy"));
                    //                    System.out.println("UpdatedDate::"+ settlemtntDatarows[i].getAttribute("UpdatedDate"));
                    //                    System.out.println("UpdatedBy::"+ settlemtntDatarows[i].getAttribute("UpdatedBy"));
                    //row.setAttribute("REVERSALREASON", settlemtntDatarows[i].getAttribute("REVERSALREASON"));
                    selectedRecords = selectedRecords + 1;


                }

            }

            if (transBreak > 0) {
                FacesContext context = FacesContext.getCurrentInstance();
                String messageText = "Please select an Invoice for creation of Credit Transaction";
                FacesMessage fm = new FacesMessage(messageText);
                fm.setSeverity(FacesMessage.SEVERITY_ERROR);
                context.addMessage(null, fm);
            } else if (paymentBreak == 1) {
                FacesContext context = FacesContext.getCurrentInstance();
                String messageText = "Please select an Unpaid Transaction for creation of Credit Transaction";
                FacesMessage fm = new FacesMessage(messageText);
                fm.setSeverity(FacesMessage.SEVERITY_ERROR);
                context.addMessage(null, fm);
            } else if (paymentBreak == 2) {
                FacesContext context = FacesContext.getCurrentInstance();
                String messageText =
                    "The selected Invoice is already reversed. Kindly select an Upaid Invoice for creation of Credit Transactions";
                FacesMessage fm = new FacesMessage(messageText);
                fm.setSeverity(FacesMessage.SEVERITY_ERROR);
                context.addMessage(null, fm);
            }
            //
            else {
                System.out.println("nonInvoice Cat 11::" + nonInvoice);
                RichPopup.PopupHints hints = new RichPopup.PopupHints();
                this.invoicecreditmemobindpopup.show(hints);
            }

        }


    }


    public void syncAction(ActionEvent actionEvent) {
        LOG.info("Inside syncAction**********************");
        Connection conn = null;
        PreparedStatement ps = null;
        // java.util.Calendar cal = new GregorianCalendar();
        try {
            conn = am.getDBConnection();
            String SPsql = "EXEC USPGET_NETTING_HEADER";
            ps = conn.prepareStatement(SPsql);
            ps.setEscapeProcessing(true);
            ps.execute();

            String SPsql1 = "EXEC USPGET_NETTING_HEADER_PPSOFT";
            ps = conn.prepareStatement(SPsql1);
            ps.setEscapeProcessing(true);
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

    public void netPerformAction(ActionEvent actionEvent) {
        // Add event code here...
        LOG.info("Inside syncAction**********************");
        Connection conn = null;
        PreparedStatement ps = null;
        DCIteratorBinding dcIteratorbinding = getDCIteratorBindings("SgsNetHeaderTblVO1Iterator");
        Row row = dcIteratorbinding.getCurrentRow();
        String nettingid = (String) row.getAttribute("NettingId");
        // java.util.Calendar cal = new GregorianCalendar();
        try {
            conn = am.getDBConnection();
            String SPsql = "EXEC USP_UPDATE_NETTING ?";
            ps = conn.prepareStatement(SPsql);
            ps.setEscapeProcessing(true);
            ps.setString(1, nettingid);
            ps.execute();
            String SPsql1 = "EXEC USP_UPDATE_NETTING_PPLSOFT ?";
            ps = conn.prepareStatement(SPsql1);
            ps.setEscapeProcessing(true);
            ps.setString(1, nettingid);
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

    public void onNetIcRecvSelectAll(ValueChangeEvent valueChangeEvent) {
        String newValue = valueChangeEvent.getNewValue().toString();
        System.out.println("---newvalue---" + newValue);
        ViewObject netIcRecvVO = ADFUtils.findIterator("SgsNetIcReceivableVO1Iterator").getViewObject();
        RowSetIterator dtlsRS = netIcRecvVO.createRowSetIterator(null);
        while (dtlsRS.hasNext()) {

            Row r = dtlsRS.next();

            if (newValue.equals("true")) {

                r.setAttribute("selectedRecords", "true");

            } else {

                r.setAttribute("selectedRecords", "false");

            }

        }

        AdfFacesContext.getCurrentInstance().addPartialTarget(netIcRecvselectcheckbox);
        AdfFacesContext.getCurrentInstance().addPartialTarget(onIcRecvColumnSelectRecord);


    }


    public void setOnIcRecvColumnSelectRecord(RichColumn onIcRecvColumnSelectRecord) {
        this.onIcRecvColumnSelectRecord = onIcRecvColumnSelectRecord;
    }

    public RichColumn getOnIcRecvColumnSelectRecord() {
        return onIcRecvColumnSelectRecord;
    }

    public void setNetIcRecvselectcheckbox(RichSelectBooleanCheckbox netIcRecvselectcheckbox) {
        this.netIcRecvselectcheckbox = netIcRecvselectcheckbox;
    }

    public RichSelectBooleanCheckbox getNetIcRecvselectcheckbox() {
        return netIcRecvselectcheckbox;
    }

    public void setOnIcPayColumnSelectRecord(RichColumn onIcPayColumnSelectRecord) {
        this.onIcPayColumnSelectRecord = onIcPayColumnSelectRecord;
    }

    public RichColumn getOnIcPayColumnSelectRecord() {
        return onIcPayColumnSelectRecord;
    }

    public void setNetIcPayselectcheckbox(RichSelectBooleanCheckbox netIcPayselectcheckbox) {
        this.netIcPayselectcheckbox = netIcPayselectcheckbox;
    }

    public RichSelectBooleanCheckbox getNetIcPayselectcheckbox() {
        return netIcPayselectcheckbox;
    }

    public void onNetIcPaySelectAll(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        String newValue = valueChangeEvent.getNewValue().toString();
        System.out.println("---newvalue---" + newValue);
        ViewObject netIcPayVO = ADFUtils.findIterator("SgsNetIcPayableVO2Iterator").getViewObject();
        RowSetIterator dtlsRS = netIcPayVO.createRowSetIterator(null);
        while (dtlsRS.hasNext()) {

            Row r = dtlsRS.next();

            if (newValue.equals("true")) {

                r.setAttribute("selectedRecord", "true");

            } else {

                r.setAttribute("selectedRecord", "false");

            }

        }

        AdfFacesContext.getCurrentInstance().addPartialTarget(netIcPayselectcheckbox);
        AdfFacesContext.getCurrentInstance().addPartialTarget(onIcPayColumnSelectRecord);
    }

    public void setOnNetArColRecColumnSelectRecord(RichColumn onNetArColRecColumnSelectRecord) {
        this.onNetArColRecColumnSelectRecord = onNetArColRecColumnSelectRecord;
    }

    public RichColumn getOnNetArColRecColumnSelectRecord() {
        return onNetArColRecColumnSelectRecord;
    }

    public void setNetNetArColRecselectcheckbox(RichSelectBooleanCheckbox netNetArColRecselectcheckbox) {
        this.netNetArColRecselectcheckbox = netNetArColRecselectcheckbox;
    }

    public RichSelectBooleanCheckbox getNetNetArColRecselectcheckbox() {
        return netNetArColRecselectcheckbox;
    }

    public void onNetArColRecvSelectAll(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        String newValue = valueChangeEvent.getNewValue().toString();
        System.out.println("---newvalue---" + newValue);
        ViewObject netArColRecvVO = ADFUtils.findIterator("NetArColRecVO1Iterator").getViewObject();
        RowSetIterator dtlsRS = netArColRecvVO.createRowSetIterator(null);
        while (dtlsRS.hasNext()) {

            Row r = dtlsRS.next();

            if (newValue.equals("true")) {

                r.setAttribute("selectedRecord", "true");

            } else {

                r.setAttribute("selectedRecord", "false");

            }

        }

        AdfFacesContext.getCurrentInstance().addPartialTarget(netNetArColRecselectcheckbox);
        AdfFacesContext.getCurrentInstance().addPartialTarget(onNetArColRecColumnSelectRecord);

    }

    public void setOnNetArColPayColumnSelectRecord(RichColumn onNetArColPayColumnSelectRecord) {
        this.onNetArColPayColumnSelectRecord = onNetArColPayColumnSelectRecord;
    }

    public RichColumn getOnNetArColPayColumnSelectRecord() {
        return onNetArColPayColumnSelectRecord;
    }

    public void setNetNetArColPayselectcheckbox(RichSelectBooleanCheckbox netNetArColPayselectcheckbox) {
        this.netNetArColPayselectcheckbox = netNetArColPayselectcheckbox;
    }

    public RichSelectBooleanCheckbox getNetNetArColPayselectcheckbox() {
        return netNetArColPayselectcheckbox;
    }

    public void onNetArColPaySelectAll(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        String newValue = valueChangeEvent.getNewValue().toString();
        System.out.println("---newvalue---" + newValue);
        ViewObject netArColPayVO = ADFUtils.findIterator("NetArColPayVO1Iterator").getViewObject();
        RowSetIterator dtlsRS = netArColPayVO.createRowSetIterator(null);
        while (dtlsRS.hasNext()) {

            Row r = dtlsRS.next();

            if (newValue.equals("true")) {

                r.setAttribute("selectedRecord", "true");

            } else {

                r.setAttribute("selectedRecord", "false");

            }

        }

        AdfFacesContext.getCurrentInstance().addPartialTarget(netNetArColPayselectcheckbox);
        AdfFacesContext.getCurrentInstance().addPartialTarget(onNetArColPayColumnSelectRecord);

    }


    public void setNetCcAllLimCal(RichInputText netCcAllLimCal) {
        this.netCcAllLimCal = netCcAllLimCal;
    }

    public RichInputText getNetCcAllLimCal() {
        return netCcAllLimCal;
    }

    public void onNetIcRecvHold(ActionEvent actionEvent) {
        // Add event code here...
        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding holditer = (DCIteratorBinding) bindings.get("SgsNetIcReceivableVO1Iterator");
        ViewObject holdVO = holditer.getViewObject();


        oracle.jbo.Row[] selectedRows = holdVO.getFilteredRows("selectedRecords", "true");
        System.out.println("*****Selected rows****" + selectedRows.length);
        for (oracle.jbo.Row rw : selectedRows) {
            if (!(rw.getAttribute("Status").equals("On Hold"))) {


                rw.setAttribute("Status", "On Hold");
            }
        }

        executeBinding("Commit");
        holdAction();
    }

    public void onNetIcRecvRelease(ActionEvent actionEvent) {
        // Add event code here...
        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding holditer = (DCIteratorBinding) bindings.get("SgsNetIcReceivableVO1Iterator");
        ViewObject holdVO = holditer.getViewObject();


        oracle.jbo.Row[] selectedRows = holdVO.getFilteredRows("selectedRecords", "true");
        System.out.println("*****Selected rows****" + selectedRows.length);
        for (oracle.jbo.Row rw : selectedRows) {
            if (rw.getAttribute("Status").equals("On Hold")) {


                rw.setAttribute("Status", "Pending for Treasury Approval");
            }
        }

        executeBinding("Commit");

    }

    public void onNetIcPayHold(ActionEvent actionEvent) {
        // Add event code here...
        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding holditer = (DCIteratorBinding) bindings.get("SgsNetIcPayableVO2Iterator");
        ViewObject holdVO = holditer.getViewObject();


        oracle.jbo.Row[] selectedRows = holdVO.getFilteredRows("selectedRecord", "true");
        System.out.println("*****Selected rows****" + selectedRows.length);
        for (oracle.jbo.Row rw : selectedRows) {
            if (!(rw.getAttribute("Status").equals("On Hold"))) {


                rw.setAttribute("Status", "On Hold");
            }
        }

        executeBinding("Commit");
        holdAction();
    }

    public void onNetIcPayRelease(ActionEvent actionEvent) {
        // Add event code here...
        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding holditer = (DCIteratorBinding) bindings.get("SgsNetIcPayableVO2Iterator");
        ViewObject holdVO = holditer.getViewObject();


        oracle.jbo.Row[] selectedRows = holdVO.getFilteredRows("selectedRecord", "true");
        System.out.println("*****Selected rows****" + selectedRows.length);
        for (oracle.jbo.Row rw : selectedRows) {
            if (rw.getAttribute("Status").equals("On Hold")) {


                rw.setAttribute("Status", "Pending for Treasury Approval");
            }
        }

        executeBinding("Commit");
    }

    public void onNetArColRecvHold(ActionEvent actionEvent) {
        // Add event code here...
        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding holditer = (DCIteratorBinding) bindings.get("NetArColRecVO1Iterator");
        ViewObject holdVO = holditer.getViewObject();


        oracle.jbo.Row[] selectedRows = holdVO.getFilteredRows("selectedRecord", "true");
        System.out.println("*****Selected rows****" + selectedRows.length);
        for (oracle.jbo.Row rw : selectedRows) {
            if (!(rw.getAttribute("Status").equals("On Hold"))) {


                rw.setAttribute("Status", "On Hold");
            }
        }

        executeBinding("Commit");
        holdAction();
    }

    public void onNetArColRecvRelease(ActionEvent actionEvent) {
        // Add event code here...
        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding holditer = (DCIteratorBinding) bindings.get("NetArColRecVO1Iterator");
        ViewObject holdVO = holditer.getViewObject();


        oracle.jbo.Row[] selectedRows = holdVO.getFilteredRows("selectedRecord", "true");
        System.out.println("*****Selected rows****" + selectedRows.length);
        for (oracle.jbo.Row rw : selectedRows) {
            if (rw.getAttribute("Status").equals("On Hold")) {


                rw.setAttribute("Status", "Pending for Treasury Approval");
            }
        }

        executeBinding("Commit");
    }

    public void onNetArColPayHold(ActionEvent actionEvent) {
        // Add event code here...
        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding holditer = (DCIteratorBinding) bindings.get("NetArColPayVO1Iterator");
        ViewObject holdVO = holditer.getViewObject();


        oracle.jbo.Row[] selectedRows = holdVO.getFilteredRows("selectedRecord", "true");
        System.out.println("*****Selected rows****" + selectedRows.length);
        for (oracle.jbo.Row rw : selectedRows) {
            if (!(rw.getAttribute("Status").equals("On Hold"))) {


                rw.setAttribute("Status", "On Hold");
            }
        }

        executeBinding("Commit");
        holdAction();
    }

    public void onNetArColPayRelease(ActionEvent actionEvent) {
        // Add event code here...
        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding holditer = (DCIteratorBinding) bindings.get("NetArColPayVO1Iterator");
        ViewObject holdVO = holditer.getViewObject();


        oracle.jbo.Row[] selectedRows = holdVO.getFilteredRows("selectedRecord", "true");
        System.out.println("*****Selected rows****" + selectedRows.length);
        for (oracle.jbo.Row rw : selectedRows) {
            if (rw.getAttribute("Status").equals("On Hold")) {


                rw.setAttribute("Status", "Pending for Treasury Approval");
            }
        }

        executeBinding("Commit");
    }


    public void setOtherCommentBind(RichInputText otherCommentBind) {
        this.otherCommentBind = otherCommentBind;
    }

    public RichInputText getOtherCommentBind() {
        return otherCommentBind;
    }

    public void onNetIcRecvSettled(ActionEvent actionEvent) {
        // Add event code here...


        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding holditer = (DCIteratorBinding) bindings.get("SgsNetIcReceivableVO1Iterator");
        ViewObject holdVO = holditer.getViewObject();


        oracle.jbo.Row[] selectedRows = holdVO.getFilteredRows("selectedRecords", "true");
        System.out.println("*****Selected rows****" + selectedRows.length);
        for (oracle.jbo.Row rw : selectedRows) {
            if (!(rw.getAttribute("Status").equals("On Hold"))) {


                rw.setAttribute("Status", "Settled");
            }
        }

        executeBinding("Commit");
        displayInfoMessgage("Transaction Available for Settlement");
        settledAction();


    }


    public String getCurrentStatus() {
        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding holditer = (DCIteratorBinding) bindings.get("SgsNetHeaderTblVO1Iterator");
        ViewObject VO = holditer.getViewObject();
        oracle.jbo.Row[] selectedRows = VO.getFilteredRows("selectedRecord", "Yes");
        if (selectedRows.length > 0) {
            oracle.jbo.Row rw = selectedRows[0];
            if (null != rw.getAttribute("Status")) {
                return (String) rw.getAttribute("Status");
            }
        }
        return null;
    }

    public String getCurrentApprover() {
        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding holditer = (DCIteratorBinding) bindings.get("SgsNetHeaderTblVO1Iterator");
        ViewObject VO = holditer.getViewObject();
        oracle.jbo.Row[] selectedRows = VO.getFilteredRows("selectedRecord", "Yes");
        if (selectedRows.length > 0) {
            oracle.jbo.Row rw = selectedRows[0];
            if (null != rw.getAttribute("APPROVERFC1")) {
                return (String) rw.getAttribute("APPROVERFC1");
            }
        }
        return null;
    }

    public void setApprover(String userName, String Approver) {
        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding holditer = (DCIteratorBinding) bindings.get("SgsNetHeaderTblVO1Iterator");
        ViewObject VO = holditer.getViewObject();
        oracle.jbo.Row[] selectedRows = VO.getFilteredRows("selectedRecord", "Yes");
        for (oracle.jbo.Row rw : selectedRows) {
            rw.setAttribute(Approver, userName);

        }

        executeBinding("Commit");
    }


    public void onNetHeaderApprove(ActionEvent actionEvent) {

        CommonUtils util = new CommonUtils();
        String username = util.getSessionScopeValue("_username").toString();

        String userRole = getUserRole();
        System.out.println("===============userRole====================" + userRole);

        if (userRole.equalsIgnoreCase("Treasury")) {

            changeStatus("Pending for FC Approval");
            setApprover(username, "APPROVERT");
            displayInfoMessgage("     Approved    ");


        } else if (userRole.equalsIgnoreCase("FINANCE_CTRLR")) {

            String currentStatus = getCurrentStatus();
            String currentApprover = getCurrentApprover();
            System.out.println("=======CURRENTSTATUS=====" + currentStatus);
            System.out.println("=======CURRENTAPPROVER=====" + currentApprover);
            System.out.println("=====-----Currentusername---=====" + username);

            if (currentStatus.equalsIgnoreCase("Pending for FC Approval")) {

                changeStatus("One Geo Approved, Second Geo Pending");
                setApprover(username, "APPROVERFC1");
                displayInfoMessgage("     Approved    ");

            } else if (currentStatus.equalsIgnoreCase("One Geo Approved, Second Geo Pending")) {

                if (currentApprover.equalsIgnoreCase(username)) {

                    System.out.println("=====-----CurrentApprover---=====" + currentApprover);
                    System.out.println("=====-----Currentusername---=====" + username);
                    displayWarningMessgage("You have already approved once");

                } else {

                    changeStatus("Pending for GAO Approval");
                    setApprover(username, "APPROVERFC2");
                    displayInfoMessgage("     Approved    ");
                }
            } else {

                displayWarningMessgage();
            }
        } else if (userRole.equalsIgnoreCase("GAO")) {

            changeStatus("Approved");
            setApprover(username, "APPROVER");
            displayInfoMessgage("     Approved    ");

        } else if (userRole.equalsIgnoreCase("GFSS")) {

            changeStatus("Perform Netting, Netting Completed");

        } else {

            displayWarningMessgage();

        }


    }

    public void displayWarningMessgage() {

        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message =
            new FacesMessage(FacesMessage.SEVERITY_WARN, "Unauthorized Status Change",
                             "You are not authorized to change the status at this point.");
        context.addMessage(null, message);

    }

    public void displayWarningMessgage(String customMessage) {

        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message =
            new FacesMessage(FacesMessage.SEVERITY_WARN, "Unauthorized Status Change", customMessage);
        context.addMessage(null, message);

    }

    public void displayInfoMessgage(String customMessage) {

        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Status Change", customMessage);
        context.addMessage(null, message);

    }

    public void changeStatus(String status) {


        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding holditer = (DCIteratorBinding) bindings.get("SgsNetHeaderTblVO1Iterator");
        ViewObject VO = holditer.getViewObject();


        oracle.jbo.Row[] selectedRows = VO.getFilteredRows("selectedRecord", "Yes");
        System.out.println("*****Selected rows****" + selectedRows.length);


        for (oracle.jbo.Row rw : selectedRows) {


            if (null != rw.getAttribute("Status")) {


                rw.setAttribute("Status", status);
                rw.setAttribute("Attribute1", "Y");
            }
        }

        executeBinding("Commit");
        approveAction();
    }


    public void onNetIcPaySettled(ActionEvent actionEvent) {
        // Add event code here...
        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding holditer = (DCIteratorBinding) bindings.get("SgsNetIcPayableVO2Iterator");
        ViewObject holdVO = holditer.getViewObject();


        oracle.jbo.Row[] selectedRows = holdVO.getFilteredRows("selectedRecord", "true");
        System.out.println("*****Selected rows****" + selectedRows.length);
        for (oracle.jbo.Row rw : selectedRows) {
            if (!(rw.getAttribute("Status").equals("On Hold"))) {


                rw.setAttribute("Status", "Settled");
            }
        }

        executeBinding("Commit");
        displayInfoMessgage("Transaction Available for Settlement");
        settledAction();
    }

    public void onNetArColRecvSettled(ActionEvent actionEvent) {

        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding holditer = (DCIteratorBinding) bindings.get("NetArColRecVO1Iterator");
        ViewObject holdVO = holditer.getViewObject();


        oracle.jbo.Row[] selectedRows = holdVO.getFilteredRows("selectedRecord", "true");
        System.out.println("*****Selected rows****" + selectedRows.length);
        for (oracle.jbo.Row rw : selectedRows) {
            if (!(rw.getAttribute("Status").equals("On Hold"))) {


                rw.setAttribute("Status", "Settled");
            }
        }

        executeBinding("Commit");
        displayInfoMessgage("Transaction Available for Settlement");
        settledAction();
    }

    public void onNetArColPaySettled(ActionEvent actionEvent) {
        // Add event code here...
        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding holditer = (DCIteratorBinding) bindings.get("NetArColPayVO1Iterator");
        ViewObject holdVO = holditer.getViewObject();


        oracle.jbo.Row[] selectedRows = holdVO.getFilteredRows("selectedRecord", "true");
        System.out.println("*****Selected rows****" + selectedRows.length);
        for (oracle.jbo.Row rw : selectedRows) {
            if (!(rw.getAttribute("Status").equals("On Hold"))) {


                rw.setAttribute("Status", "Settled");
            }
        }

        executeBinding("Commit");
        displayInfoMessgage("Transaction Available for Settlement");
        settledAction();
    }


    public String getUserRole() {
        System.out.println("----Get User  detail---------");
        String userRole = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        CommonUtils util = new CommonUtils();
        String user = util.getSessionScopeValue("_username").toString();


        try {
            connection = am.getDBConnection();


            // Prepare and execute the SQL query
            String sql = "SELECT ROLE_ID FROM [USER_ROLE_MAPPING] WHERE USER_ID= ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, user);
            resultSet = statement.executeQuery();


            // Retrieve the user's role from the result set
            if (resultSet.next()) {
                userRole = resultSet.getString("ROLE_ID");
            }
        } catch (SQLException e) {
            // Handle exceptions
        } finally {
            // Close the database resources
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    // Handle exception
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    // Handle exception
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    // Handle exception
                }
            }
        }


        return userRole;
    }


    public void setNetHeaderTableData(RichTable netHeaderTableData) {
        this.netHeaderTableData = netHeaderTableData;
    }

    public RichTable getNetHeaderTableData() {
        return netHeaderTableData;
    }

    public void queryByStatus(String Status) {
        String userRole = getUserRole();
        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding holditer = (DCIteratorBinding) bindings.get("SgsNetHeaderTblVO1Iterator");
        ViewObject vo = holditer.getViewObject();
        String statusValue = "STATUS" + "=" + "'" + Status + "'";
        System.out.println("------statusValue======" + statusValue);
        vo.setWhereClause(statusValue);

        vo.executeQuery();


    }

    public void queryByStatuswith2Param(String Status, String Status1) {
        String userRole = getUserRole();
        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding holditer = (DCIteratorBinding) bindings.get("SgsNetHeaderTblVO1Iterator");
        ViewObject vo = holditer.getViewObject();
        String statusValue = "STATUS" + " " + " IN " + "(" + "'" + Status + "'" + "," + "'" + Status1 + "'" + ")";
        System.out.println("------statusValue======" + statusValue);
        vo.setWhereClause(statusValue);

        vo.executeQuery();


    }

    public void nettingHeaderTableFilter(ActionEvent actionEvent) {
        // Add event code here...
        String userRole = getUserRole();

        if (userRole.equalsIgnoreCase("Treasury")) {

            queryByStatus("Pending for Treasury Approval");


        } else if (userRole.equalsIgnoreCase("FINANCE_CTRLR")) {

            queryByStatuswith2Param("Pending for FC Approval", "One Geo Approved, Second Geo Pending");


        }

        else if (userRole.equalsIgnoreCase("GAO")) {

            queryByStatus("Pending for GAO Approval");

        } else if (userRole.equalsIgnoreCase("GFSS")) {

            queryByStatus("Approved");

        }


    }

    public void netHeaderReject(ActionEvent actionEvent) {
        // Add event code here...
        String userRole = getUserRole();
        System.out.println("===============userRole====================" + userRole);

        if (userRole.equalsIgnoreCase("FINANCE_CTRLR")) {

            changeStatus("Pending for Treasury Approval");

        } else if (userRole.equalsIgnoreCase("GAO")) {

            changeStatus("Pending for Treasury Approval");
        }

    }

    public void setSubmitstatpopupattachbind(RichPopup submitstatpopupattachbind) {
        this.submitstatpopupattachbind = submitstatpopupattachbind;
    }

    public RichPopup getSubmitstatpopupattachbind() {
        return submitstatpopupattachbind;
    }

    public void setZeroBalanceCheckBind(RichSelectBooleanCheckbox zeroBalanceCheckBind) {
        this.zeroBalanceCheckBind = zeroBalanceCheckBind;
    }

    public RichSelectBooleanCheckbox getZeroBalanceCheckBind() {
        return zeroBalanceCheckBind;
    }

    public void settledAction() {
        LOG.info("Inside settledAction**********************");
        Connection conn = null;
        PreparedStatement ps = null;
        // java.util.Calendar cal = new GregorianCalendar();
        try {
            conn = am.getDBConnection();
            String SPsql = "EXEC USP_UPDATE_NETTING_SETTLEMENT";
            ps = conn.prepareStatement(SPsql);
            ps.setEscapeProcessing(true);
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

    public void approveAction() {
        LOG.info("Inside approveAction**********************");
        System.out.println("Inside approveAction**********************");
        Connection conn = null;
        PreparedStatement ps = null;
        // java.util.Calendar cal = new GregorianCalendar();
        try {
            conn = am.getDBConnection();
            String SPsql = "EXEC USPGET_NETTING_HEADER";
            ps = conn.prepareStatement(SPsql);
            ps.setEscapeProcessing(true);
            ps.execute();

            String SPsql1 = "EXEC USP_UPDATE_NETTING_HEADER";
            ps = conn.prepareStatement(SPsql1);
            ps.setEscapeProcessing(true);
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

    public void holdAction() {
        LOG.info("Inside holdAction**********************");
        System.out.println("Inside holdAction**************");
        Connection conn = null;
        PreparedStatement ps = null;
        // java.util.Calendar cal = new GregorianCalendar();
        try {
            conn = am.getDBConnection();
            String SPsql = "EXEC USP_UPDATE_NETTING_HOLD";
            ps = conn.prepareStatement(SPsql);
            ps.setEscapeProcessing(true);
            ps.execute();

            String SPsql1 = "EXEC USP_UPDATE_NETTING_HOLD";
            ps = conn.prepareStatement(SPsql1);
            ps.setEscapeProcessing(true);
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

