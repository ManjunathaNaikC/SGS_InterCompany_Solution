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
import java.math.RoundingMode;

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
import oracle.adf.view.rich.component.rich.data.RichTable;
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

import javax.faces.component.UIComponent;

import oracle.adf.share.ADFContext;

import oracle.adf.view.rich.component.rich.input.RichInputListOfValues;

import oracle.adf.share.logging.ADFLogger;

import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewObject;

import oracle.jbo.RowSetIterator;


import org.apache.myfaces.trinidad.model.UploadedFile;

public class ActionEventsBean {

    private static final ADFLogger LOG = ADFLogger.createADFLogger(ActionEventsBean.class);
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
                                "'  and YEAR(TRANSACTION_PERIOD) ='" + gcal.get(Calendar.YEAR) + "'");
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
                        InputStream inputStream = null;
                        inputStream = uploadedFile.getInputStream();
                        BufferedInputStream bfi = new BufferedInputStream(inputStream);
                        String fileName = uploadedFile.getFilename();
                        String path = null;


                        String tokens = uploadedFile.getFilename();
                        String fileNames = uploadedFile.getFilename();
                        String contentType = uploadedFile.getContentType();

                        path = filePath1 + fileNames;
                        saveFile(path, fileName, bfi);

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


    public void saveFile(String filePath, String fileName, BufferedInputStream in) throws MalformedURLException,
                                                                                          IOException {
        FileOutputStream fout = null;
        try {
            File files = new File(filePath);
            if (!files.exists()) {
                if (files.mkdirs()) {
                    LOG.info("Multiple directories are created!");
                } else {
                    LOG.info("Failed to create multiple directories!");
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
            if (rw.getAttribute("StlmtStatus").equals("Partially paid") ||
                rw.getAttribute("StlmtStatus").equals("Unpaid")) {

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


        DCIteratorBinding dcIteratorbinding = getDCIteratorBindings("CreateStlmtRVO1Iterator");
        Row row = dcIteratorbinding.getCurrentRow();
        double transactionAmount = ((Number) row.getAttribute("TRXAMOUNT")).doubleValue();
        double bankCharge = ((Number) row.getAttribute("BANKCHARGES")).doubleValue();
        ViewObject paymentIterator = ADFUtils.findIterator("SgsStlmtVoucherVO1Iterator").getViewObject();
        Row[] rows = paymentIterator.getAllRowsInRange();
        boolean isFirstRow = true;

        for (Row paymentRow : getUnpaidAndPartiallySettledRows()) {


            String settlementStatus = (String) paymentRow.getAttribute("StlmtStatus");
            String paymentStatus = (String) paymentRow.getAttribute("PaymentStatus");
            double outstandingAmount = ((Number) paymentRow.getAttribute("OsAmountPayable")).doubleValue();
            double netPayableAmount = ((Number) paymentRow.getAttribute("NetAmountPayable")).doubleValue();
            String paymentId= (String) row.getAttribute("PAYMENTID");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date paymentDate = (java.util.Date) row.getAttribute("PAYMENTDATE");
            java.sql.Date payDate = new java.sql.Date(paymentDate.getTime());
            java.util.Date receiptDate = (java.util.Date) row.getAttribute("RECEIPTDATE");
            java.sql.Date rctDate = new java.sql.Date(receiptDate.getTime());
            System.out.println("Payment date" + payDate);
            System.out.println("Receipt date" + rctDate);
            String ReceiptBankName = (String) AdfFacesContext.getCurrentInstance()
                                    .getPageFlowScope().get("selectedRctBankName");
            String ReceiptBankCode = (String) AdfFacesContext.getCurrentInstance()
                                    .getPageFlowScope().get("selectedRctBankCode");
            String paymentBankName = (String) AdfFacesContext.getCurrentInstance()
                                    .getPageFlowScope().get("selectedPayBankName");
            String paymentBankCode = (String) AdfFacesContext.getCurrentInstance()
                                    .getPageFlowScope().get("selectedPayBankCode");
            System.out.println("Receipt Bank Name " + ReceiptBankName);
            System.out.println("Receipt Bank Code " + ReceiptBankCode);
            System.out.println("payment Bank Name " + paymentBankName);
            System.out.println("payment Bank Code" + paymentBankCode);
            
            double settlementAmount = 0;

            if ("Unpaid".equals(settlementStatus)) {
                if (transactionAmount >= outstandingAmount) {
                    settlementAmount = outstandingAmount;
                    settlementStatus = "Settled";
                    paymentStatus = "Fully Paid";

                } else {

                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
                    settlementAmount = Double.parseDouble(decimalFormat.format(transactionAmount));


                    settlementStatus = "Partially settled";
                    paymentStatus = "Partially Paid";
                    transactionAmount = 0;
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
                                        Double.parseDouble(decimalFormat.format(netPayableAmount - settlementAmount)));
                

            } else if ("Transaction on Hold".equals(settlementStatus)) {

                continue;

            } else {
                if (transactionAmount >= outstandingAmount) {
                    settlementAmount = netPayableAmount;
                    settlementStatus = "Settled";
                    paymentStatus = "Fully Paid";


                } else {

                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
                    settlementAmount = Double.parseDouble(decimalFormat.format(transactionAmount));

                    settlementStatus = "Partially settled";
                    paymentStatus = "Partially Paid";
                    transactionAmount = 0;
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
                                        Double.parseDouble(decimalFormat.format(netPayableAmount - settlementAmount)));
                


            }
            if (transactionAmount <= 0) {
                break;
            }
            if("Settled".equals(settlementStatus)){
                
                    paymentRow.setAttribute("PaymentId", paymentId);
                    paymentRow.setAttribute("PAYMENTDATE", payDate);
                    paymentRow.setAttribute("RECEIPTDATE", rctDate);
                    paymentRow.setAttribute("RECEIPTBANKCD", ReceiptBankName);
                    paymentRow.setAttribute("RECEIPTBANKACCTKEY", ReceiptBankCode);
                    paymentRow.setAttribute("PAYMENTBANKCD", paymentBankName);
                    paymentRow.setAttribute("PAYMENTBANKACCTKEY", paymentBankCode);

                }
        }

        System.out.println("Balance : "+transactionAmount);
        
        
        if (transactionAmount>=0){
                DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
                decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
                String balanceText = "Balance: " + decimalFormat.format(transactionAmount);
                System.out.println(balanceText);
                this.setBalanceOutputText(balanceText);
                System.out.println(balanceOutputText);
                AdfFacesContext.getCurrentInstance().addPartialTarget(balanceOutputText1);
            
            }
            
        

        //        executeBinding(SAVE_DATA);

        AdfFacesContext.getCurrentInstance().addPartialTarget(tablePaymentRecords);


    }

    private Row[] getUnpaidAndPartiallySettledRows() {
    
            ViewObject vo = getPaymentSettlementVO();
            vo.setWhereClause("STLMT_STATUS IN ('Unpaid', 'Partially Settled','Transaction on Hold')");
            vo.setOrderByClause("STLMT_STATUS , PS_VOUCHER_NO ");
            vo.executeQuery();
            return vo.getAllRowsInRange();
        }

  
    private ViewObject getPaymentSettlementVO() {
        DCBindingContainer binding = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iterator = binding.findIteratorBinding("SgsStlmtVoucherVO1Iterator");
        return iterator.getViewObject();
    }



    public void onCreateSettlementSearch(ActionEvent actionEvent) {
        DCIteratorBinding dcIteratorbinding = getDCIteratorBindings("CreateStlmtRVO1Iterator");
        Row row = dcIteratorbinding.getCurrentRow();

        
        String ICSUPPLIERGEO = (String) AdfFacesContext.getCurrentInstance()
                                .getPageFlowScope().get("selectedValue");
        
        String ICCUSTOMERGEO = (String) AdfFacesContext.getCurrentInstance()
                                .getPageFlowScope().get("selectedValue1");
        String collectionBU = (String) AdfFacesContext.getCurrentInstance()
                                .getPageFlowScope().get("selectedValue2");
        
        String payerBU = (String) AdfFacesContext.getCurrentInstance()
                                .getPageFlowScope().get("selectedValue3");
        
        System.out.println("suppliergeo : "+ICSUPPLIERGEO);
        System.out.println("CUSTOMERGEO : "+ICSUPPLIERGEO);
        
//        String PAYMENTID = (String) row.getAttribute("PAYMENTID");
//        String ICCUSTOMERGEO = (String) row.getAttribute("ICCUSTOMERGEO");
//        String ICCUSTOMERBU = (String) row.getAttribute("ICCUSTOMERBU");
//        String ICSUPPLIERGEO = (String) row.getAttribute("ICSUPPLIERGEO");
//        String ICSUPPLIERBU = (String) row.getAttribute("ICSUPPLIERBU");


        DCIteratorBinding iteratorBinding = getDCIteratorBindings("SgsStlmtVoucherVO1Iterator");
        RowSetIterator rowSetIterator = iteratorBinding.getRowSetIterator();
        ViewObjectImpl voucherView = (ViewObjectImpl) iteratorBinding.getViewObject();
        ViewCriteria criteria = voucherView.getViewCriteria("SgsCreateStlmtVoucherVOCriteria");
        voucherView.applyViewCriteria(criteria);
//        voucherView.setNamedWhereClauseParam("bCusBu", ICCUSTOMERBU);
        voucherView.setNamedWhereClauseParam("bCusGeo", ICCUSTOMERGEO);
//        voucherView.setNamedWhereClauseParam("bSupBu", ICSUPPLIERBU);
        voucherView.setNamedWhereClauseParam("bSupGeo", ICSUPPLIERGEO);
        voucherView.setNamedWhereClauseParam("bCollectorBU", collectionBU);
        voucherView.setNamedWhereClauseParam("bPayerBU", payerBU);
        voucherView.executeQuery();
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
            if (rw.getAttribute("StlmtStatus").equals("Unpaid")) {


                rw.setAttribute("StlmtStatus", "Transaction on Hold");
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
            if (rw.getAttribute("StlmtStatus").equals("Transaction on Hold")) {


                rw.setAttribute("StlmtStatus", "Unpaid");
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
        DCIteratorBinding statData = null;
        statData = getDCIteratorBindings("SgsStatisticalDataVO1Iterator");
        oracle.jbo.Row[] statDataDatarows = statData.getAllRowsInRange();
        for (int i = 0; i < statDataDatarows.length; i++) {
            if (null != statDataDatarows[i].getAttribute("StatSelectedRecord") &&
                statDataDatarows[i].getAttribute("StatSelectedRecord").equals("Yes")) {
                if (null != inputFileBind.getValue()) {
                    UploadedFile uploadedFile = (UploadedFile) inputFileBind.getValue();
                    if (null != uploadedFile.getFilename()) {
                        String fileName = (String) uploadedFile.getFilename();
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
        for (oracle.jbo.Row rw : selectedRows) {
            if (null != inputFileBindFA.getValue()) {
                UploadedFile uploadedFile = (UploadedFile) inputFileBindFA.getValue();
                if (null != uploadedFile.getFilename()) {
                    String fileName = (String) uploadedFile.getFilename();
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
                rows[i].setAttribute("TransactionStatus", "Approved");
            }
        }
        executeBinding(SAVE_DATA);
        invoiceApprovBind.hide();
    }

    public void onInvoiceReject(ActionEvent actionEvent) {
        DCIteratorBinding invoiceData = getDCIteratorBindings("SgsIcInvoiceHeaderVO1Iterator");
        oracle.jbo.Row[] rows = invoiceData.getAllRowsInRange();
        for (int i = 0; i < rows.length; i++) {
            if (null != rows[i].getAttribute("selectInvoiceRecord") &&
                rows[i].getAttribute("selectInvoiceRecord").equals("Yes")) {
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
            if (null != percentageReversalBind.getValue()) {
                String str = (String) percentageReversalBind.getValue();
                double reversalPerc = Double.parseDouble(str);
                Object invAmt = row[i].getAttribute("InvoiceAmount");
                double invAmt2 = new Double(invAmt.toString());
                DecimalFormat df = new DecimalFormat("#.##");
                double invoiceAmount = Double.valueOf(df.format(invAmt2));
                double reversalAmt = (Double) ((reversalPerc / 100) * (invoiceAmount));
                double revAmt = Double.valueOf(df.format(reversalAmt));
                row[i].setAttribute("ReversalAmount", revAmt);
                row[i].setAttribute("PERCENTAGEREVERSAL", reversalPerc);
            }
            BindingContainer bc = this.getBindingsCont();
            JUCtrlListBinding list = (JUCtrlListBinding) bc.get("ReversalReasonLOVVO1");
            String selectedRow = (String) list.getSelectedValue();

            if (null != selectedRow) {
                String type = "REVERSAL_REASON";
                reversalReason = getLookupCode(selectedRow, type);
            }

            if (null != reversalReason) {
                row[i].setAttribute("REVERSALREASON", reversalReason);

            }

            BindingContainer bc1 = this.getBindingsCont();
            JUCtrlListBinding list1 = (JUCtrlListBinding) bc1.get("ReversalTypeLOVVO1");
            String selectedRow1 = (String) list1.getSelectedValue();

            String reversalType = "NONE";
            if (null != selectedRow1) {
                String type = "REVERSAL_TYPE";
                reversalType = getLookupCode(selectedRow1, type);
            }

            if (null != reversalType) {
                row[i].setAttribute("REVERSALTYPE", reversalType);
            }

            row[i].setAttribute("STATUS", "New");
        }


        this.creditDateBindVal.setValue(null);
        this.percentageReversalBind.setValue(null);

        AdfFacesContext.getCurrentInstance().addPartialTarget(creditDateBindVal);
        AdfFacesContext.getCurrentInstance().addPartialTarget(percentageReversalBind);
        executeBinding(SAVE_DATA);
        LOG.info("Inside DRTCROSS CHARGE**********************");
        Connection conn = null;
        PreparedStatement pst = null;

        try {

            conn = getDBConnection();
            String SPsql = "EXEC USP_SCN_CREDIT_TXN "; // for stored proc
            PreparedStatement ps = conn.prepareStatement(SPsql);

            ps.execute();
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

    public void onCreditMemoClose(ActionEvent actionEvent) {
        creditMemoPopupBind.hide();
    }

    public Connection getDBConnection() {
        Connection conn = null;
        try {
//            conn =
//                DriverManager.getConnection("jdbc:sqlserver://localhost;instanceName=MSSQLSERVER;databasename=DEVINTER;integratedSecurity=true;");
            conn = DriverManager.getConnection("jdbc:sqlserver://ASBCOLPS02:1433;databaseName=DEVINTER","EYUser","Ey@123");

        } catch (SQLException sqle) {
            // TODO: Add catch code
            sqle.printStackTrace();
        }
        return conn;
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
        oracle.jbo.Row[] invoiceDatarows = invoiceData.getAllRowsInRange();
        CommonUtils util = new CommonUtils();
        Object user = (Object) util.getSessionScopeValue("_username").toString();
        for (int i = 0; i < invoiceDatarows.length; i++) {
            if (null != invoiceDatarows[i].getAttribute("selectInvoiceRecord") &&
                invoiceDatarows[i].getAttribute("selectInvoiceRecord").equals("Yes")) {
                executeBinding("CreateInsertCredit");
                Row row = creditData.getCurrentRow();
                row.setAttribute("InvoiceSeqNo", invoiceDatarows[i].getAttribute("InvoiceSeqNo"));
                row.setAttribute("Period", invoiceDatarows[i].getAttribute("Period"));
                row.setAttribute("TransactionCategory", invoiceDatarows[i].getAttribute("TransactionCategory"));
                row.setAttribute("PsftVoucherRef", invoiceDatarows[i].getAttribute("ReferenceVoucherNum"));
                row.setAttribute("PsftInvoiceRef", invoiceDatarows[i].getAttribute("ReferenceInvoiceNum"));
                //row.setAttribute("NatureOfExpense",null);
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
            }
        }
        executeBinding(SAVE_DATA);
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        this.invoicecreditmemobindpopup.show(hints);
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
        Row row = dcIteratorbinding.getCurrentRow();
        row.setAttribute("Status", "Confirmed for Invoicing");
    }


    public void importFromTxn(ActionEvent actionEvent) {
        LOG.info("Inside Import from Transaction**********************");
        Connection conn = null;
        PreparedStatement pst = null;
        PreparedStatement ps = null;
        try {
            conn = getDBConnection();
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

    public String  getBalanceOutputText() {
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
}

