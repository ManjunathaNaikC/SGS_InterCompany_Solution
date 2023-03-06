package com.sgs.ics.view.bean.common;

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

import java.net.MalformedURLException;

import java.text.SimpleDateFormat;

import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.adf.view.rich.event.PopupCanceledEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.i18n.text.OraSimpleDateFormat;

import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;

import org.apache.myfaces.trinidad.model.UploadedFile;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TransBCostAllocationBean {
    private RichPopup holdPopup;
    private RichPopup releasePopup;
    private RichInputText holdReasonBind;
    private RichInputDate dueDateBind;
    private RichInputText holdRemarkBind;
    private RichInputText releaseActionBind;
    private RichInputText releaseRemarksBind;
    private RichPopup holdPopup1;
    private RichPopup releasePopup1;
    private RichInputText holdReasonBind1;
    private RichInputDate dueDateBind1;
    private RichInputText holdRemarkBind1;
    private RichInputText releaseActionBind1;
    private RichInputText releaseRemarksBind1;
    protected String SAVE_DATA="Commit";

    public void setHoldPopup(RichPopup holdPopup) {
        this.holdPopup = holdPopup;
    }

    public RichPopup getHoldPopup() {
        return holdPopup;
    }


    public TransBCostAllocationBean() {
    }

    //    public BindingContainer getBindings() {
    //        return BindingContext.getCurrent().getCurrentBindingsEntry();
    //    }

    public String deleteTransBCostAllocation() {
        BindingContainer bindings = getBindingsCont();
        OperationBinding operationBinding = bindings.getOperationBinding("Delete");
        Object result = operationBinding.execute();
        OperationBinding deleteOb = bindings.getOperationBinding("Commit");
        deleteOb.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }

    /**Method to upload XLS/XLSX file and read data from table
     * @param valueChangeEvent
     */
    public void uploadFileVCE(ValueChangeEvent valueChangeEvent) {
        System.out.println("Inside upload file");
        UploadedFile file = (UploadedFile) valueChangeEvent.getNewValue();

        try {
            //Check if file is XLSX
            if (file.getContentType()
                .equalsIgnoreCase("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") ||
                file.getContentType().equalsIgnoreCase("application/xlsx")) {

                readNProcessExcelx(file.getInputStream()); //for xlsx

            }
            //Check if file is XLS
            else if (file.getContentType().equalsIgnoreCase("application/vnd.ms-excel")) {

                if (file.getFilename()
                        .toUpperCase()
                        .endsWith(".XLS")) {
                    readNProcessExcel(file.getInputStream()); //for xls
                }

            } else {
                FacesMessage msg = new FacesMessage("File format not supported.-- Upload XLS or XLSX file");
                msg.setSeverity(FacesMessage.SEVERITY_WARN);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            //            AdfFacesContext.getCurrentInstance().addPartialTarget(empTable);

        } catch (IOException e) {
            System.out.println("Exception : " + e);
        }
    }

    /**Method to get Binding Container of current view port
     * @return
     */
    public BindingContainer getBindingsCont() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }
   
    /**
     * Generic Method to DCIteratorBinding operation
     * */
    private DCIteratorBinding getDCIteratorBindings(String iterName) {
        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        return bindings.findIteratorBinding(iterName);

    } 
    
    
    /**
     * Generic Method to execute operation
     * */
    public OperationBinding executeOperation(String operation) {
        OperationBinding createParam = getBindingsCont().getOperationBinding(operation);
        return createParam;

    }

    public void saveHoldDetails(ActionEvent actionEvent) {
        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding holditer = (DCIteratorBinding) bindings.get("SgsTransBCostAllocationVO1Iterator");
        ViewObject holdVO = holditer.getViewObject();
        RowSetIterator rsIter = holditer.getRowSetIterator();
        oracle.jbo.Row r = (oracle.jbo.Row) rsIter.getCurrentRow();

        String _holdReason = getHoldReasonBind().getValue().toString();
        String _dueDate = getDueDateBind().getValue().toString();
        String _holdRemark = getHoldRemarkBind().getValue().toString();
        System.out.println("hold reason : " + _holdReason);
        System.out.println("Hold due date : " + _dueDate);
        System.out.println("hold remarks : " + _holdRemark);

        if(_holdReason != null){
            r.setAttribute("HoldReason", _holdReason);
           
        }
        if(_holdRemark !=null){
            r.setAttribute("HoldRemarks", _holdRemark);
           
        }
        if(_dueDate != null){
            r.setAttribute("Duedate", _dueDate);     
        }
    
        r.setAttribute("TransactionStatus", "Transaction on Hold");
        executeOperation("Commit").execute();
        
        getHoldPopup().hide();
    }


    public void multiSaveHoldDetails(ActionEvent actionEvent) {
        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding holditer = (DCIteratorBinding) bindings.get("SgsTransBCostAllocationVO1Iterator");
        ViewObject holdVO = holditer.getViewObject();


        String _holdReason1 = getHoldReasonBind1().getValue().toString();
        String _dueDate1 = getDueDateBind1().getValue().toString();
        String _holdRemark1 = getHoldRemarkBind1().getValue().toString();
        System.out.println("hold reason : " + _holdReason1);
        System.out.println("Hold due date : " + _dueDate1);
        System.out.println("hold remarks : " + _holdRemark1);

        CommonUtils util = new CommonUtils();
        Object user = (Object) util.getSessionScopeValue("_username").toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());

        oracle.jbo.Row[] selectedRows = holdVO.getFilteredRows("Selected", true);
        System.out.println("*****Selected rows****" + selectedRows.length);
        for (oracle.jbo.Row rw : selectedRows) {
         if(rw.getAttribute("TransactionStatus").equals("New")||rw.getAttribute("TransactionStatus").equals("Transaction Released from Hold")){
           
            rw.setAttribute("Holdby", user);
            rw.setAttribute("Holdon", date);
            rw.setAttribute("HoldReason", _holdReason1);
            rw.setAttribute("Duedate", _dueDate1);
            rw.setAttribute("HoldRemarks", _holdRemark1);
            rw.setAttribute("TransactionStatus", "Transaction on Hold");
            }
        }

        executeOperation("Commit").execute();
        

        getHoldPopup1().hide();
    }

    public void saveReleaseDetails(ActionEvent actionEvent) {
        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding releaseIter = (DCIteratorBinding) bindings.get("SgsTransBCostAllocationVO1Iterator");
        ViewObject releaseVO = releaseIter.getViewObject();
        RowSetIterator rsIter = releaseIter.getRowSetIterator();
        oracle.jbo.Row r = (oracle.jbo.Row) rsIter.getCurrentRow();
        String _releaseAction = getReleaseActionBind().getValue().toString();
        String _releaseRemark = getReleaseRemarksBind().getValue().toString();
        r.setAttribute("ReleaseAction", _releaseAction);
        r.setAttribute("ReleaseRemarks", _releaseRemark);
        r.setAttribute("TransactionStatus", "Transaction Released from Hold");
        executeOperation("Commit").execute();
        
        getReleasePopup().hide();
    }


    public void multiSaveReleaseDetails(ActionEvent actionEvent) {
        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding releaseIter = (DCIteratorBinding) bindings.get("SgsTransBCostAllocationVO1Iterator");
        ViewObject releaseVO = releaseIter.getViewObject();


        String _releaseAction1 = getReleaseActionBind1().getValue().toString();
        String _releaseRemark1 = getReleaseRemarksBind1().getValue().toString();

        CommonUtils util = new CommonUtils();
        Object user = (Object) util.getSessionScopeValue("_username").toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());

        oracle.jbo.Row[] selectedRows = releaseVO.getFilteredRows("Selected", true);
        System.out.println("*****Selected rows****" + selectedRows.length);
        for (oracle.jbo.Row rw : selectedRows) {
            if (rw.getAttribute("TransactionStatus").equals("Transaction on Hold")) {

            rw.setAttribute("Releasedby", user);
            rw.setAttribute("Releasedon", date);
            rw.setAttribute("ReleaseAction", _releaseAction1);
            rw.setAttribute("ReleaseRemarks", _releaseRemark1);
            rw.setAttribute("TransactionStatus", "Transaction Released from Hold");
            }
        }
        executeOperation("Commit").execute();
        
        getReleasePopup1().hide();
    }


    public void closeHoldDetails(ActionEvent actionEvent) {
        getHoldPopup().hide();
    }

    public void closeReleaseDetails(ActionEvent actionEvent) {
        getReleasePopup().hide();
    }

    public void multicloseHoldDetails(ActionEvent actionEvent) {
        getHoldPopup1().hide();
    }

    public void multicloseReleaseDetails(ActionEvent actionEvent) {
        getReleasePopup1().hide();
    }

    /**Method to read xls file and upload to table.
     * @param xls
     * @throws IOException
     */
    public void readNProcessExcel(InputStream xls) throws IOException {

        //        CollectionModel cModel = (CollectionModel) empTable.getValue();
        //        JUCtrlHierBinding tableBinding = (JUCtrlHierBinding) cModel.getWrappedData();
        //        DCIteratorBinding iter = tableBinding.getDCIteratorBinding();
        System.out.println("Inside Read xls Method");

        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding iter = (DCIteratorBinding) bindings.get("SgsTransReceivablesTempVO1Iterator");

        //Use HSSFWorkbook for XLS file
        HSSFWorkbook WorkBook = null;

        int sheetIndex = 0;

        try {
            WorkBook = new HSSFWorkbook(xls);
        } catch (IOException e) {
            System.out.println("Exception : " + e);
        }

        HSSFSheet sheet = WorkBook.getSheetAt(sheetIndex);

        Integer skipRw = 1;
        Integer skipcnt = 1;
        Integer sno = 1;

        //Iterate over excel rows
        for (Row tempRow : sheet) {
            System.out.println(skipcnt + "--" + skipRw);
            if (skipcnt > skipRw) { //skip first n row for labels.
                //Create new row in table
                executeOperation("CreateInsertTempTbl").execute();
                //Get current row from iterator
                oracle.jbo.Row row = iter.getNavigatableRowIterator().getCurrentRow();

                int Index = 0;
                //Iterate over row's columns
                for (int column = 0; column < tempRow.getLastCellNum(); column++) {
                    Cell MytempCell = tempRow.getCell(column);
                    if (MytempCell != null) {
                        Index = MytempCell.getColumnIndex();

                        if (Index == 0) {
                            row.setAttribute("StatisticalDataId", MytempCell.getNumericCellValue());

                        } else if (Index == 1) {
                            row.setAttribute("StatisticalDataCategory", MytempCell.getStringCellValue());

                        } else if (Index == 2) {
                            row.setAttribute("ToBusinessUnit", MytempCell.getStringCellValue());

                        } else if (Index == 3) {
                            row.setAttribute("ToJobCode", MytempCell.getStringCellValue());

                        } else if (Index == 4) {
                            row.setAttribute("ToOperatingUnit", MytempCell.getStringCellValue());

                        } else if (Index == 5) {
                            row.setAttribute("ToDepartmentId", MytempCell.getStringCellValue());

                        } else if (Index == 6) {
                            row.setAttribute("StatisticalData", MytempCell.getStringCellValue());

                        } else if (Index == 7) {
                            row.setAttribute("UnitOfMeasure", MytempCell.getStringCellValue());

                        } else if (Index == 8) {
                            row.setAttribute("CostGroup", MytempCell.getStringCellValue());

                        } else if (Index == 9) {
                            row.setAttribute("Currency", MytempCell.getStringCellValue());

                        } else if (Index == 10) {
                            row.setAttribute("EmployeeId", MytempCell.getStringCellValue());

                        } else if (Index == 11) {
                            row.setAttribute("TargetAmount", MytempCell.getNumericCellValue());

                        } else if (Index == 12) {
                            row.setAttribute("RejectedReason", MytempCell.getStringCellValue());

                        } else if (Index == 13) {
                            row.setAttribute("RejectionComments", MytempCell.getStringCellValue());

                        } else if (Index == 14) {
                            java.util.Date date = MytempCell.getDateCellValue();
                            if (null != date) {
                                System.out.println("dATE-ValidateFrom" + date);
                                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                                System.out.println("d");
                                String date1 = dateFormat.format(date);
                                System.out.println("date1" + date1);
                                try {
                                    date = dateFormat.parse(date1);
                                } catch (ParseException e) {
                                    System.out.println("Exception : " + e);
                                }
                                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                                oracle.jbo.domain.Date jboDate = new oracle.jbo.domain.Date(sqlDate);
                                row.setAttribute("ValidityFrom", jboDate);
                            } else {
                                row.setAttribute("ValidityFrom", null);
                            }
                        } else if (Index == 15) {
                            java.util.Date date = MytempCell.getDateCellValue();
                            if (null != date) {
                                System.out.println("dATE-ValidateFrom" + date);
                                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                                System.out.println("d");
                                String date1 = dateFormat.format(date);
                                System.out.println("date1" + date1);
                                try {
                                    date = dateFormat.parse(date1);
                                } catch (ParseException e) {
                                    System.out.println("Exception : " + e);
                                }
                                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                                oracle.jbo.domain.Date jboDate = new oracle.jbo.domain.Date(sqlDate);
                                row.setAttribute("ValidityTill", jboDate);
                            } else {
                                row.setAttribute("ValidityTill", null);
                            }

                        } else if (Index == 16) {
                            row.setAttribute("CreatedBy", MytempCell.getStringCellValue());

                        } else if (Index == 17) {
                            java.util.Date date = MytempCell.getDateCellValue();
                            if (null != date) {
                                System.out.println("dATE-ValidateFrom" + date);
                                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                                System.out.println("d");
                                String date1 = dateFormat.format(date);
                                System.out.println("date1" + date1);
                                try {
                                    date = dateFormat.parse(date1);
                                } catch (ParseException e) {
                                    System.out.println("Exception : " + e);
                                }
                                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                                oracle.jbo.domain.Date jboDate = new oracle.jbo.domain.Date(sqlDate);
                                row.setAttribute("UpdatedDate", jboDate);
                            } else {
                                row.setAttribute("UpdatedDate", null);
                            }


                        } else if (Index == 18) {
                            row.setAttribute("UpdatedBy", MytempCell.getStringCellValue());

                        }

                    } else {
                        Index++;
                    }

                }
                sno++;
            }
            skipcnt++;
        }
        //Execute table viewObject
        //        executeOperation("Execute").execute();
        System.out.println("xls commit");
        executeOperation("Commit").execute();
    }


    /**
     * Method to read xlsx file and upload to table.
     *
     * @throws IOException
     */
    public void readNProcessExcelx(InputStream xlsx) throws IOException {

        //        CollectionModel cModel = (CollectionModel) empTable.getValue();
        //
        //        JUCtrlHierBinding tableBinding = (JUCtrlHierBinding) cModel.getWrappedData();
        //        //Acess the ADF iterator binding that is used with ADF table binding
        //        DCIteratorBinding iter = tableBinding.getDCIteratorBinding();
        System.out.println("Inside Read xlsx Method");

        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding iter = (DCIteratorBinding) bindings.get("SgsTransReceivablesTempVO1Iterator");


        //Use XSSFWorkbook for XLS file
        XSSFWorkbook WorkBook = null;
        int sheetIndex = 0;

        try {
            WorkBook = new XSSFWorkbook(xlsx);
        } catch (IOException e) {
            System.out.println("Exception : " + e);
        }
        XSSFSheet sheet = WorkBook.getSheetAt(sheetIndex);

        Integer skipRw = 1;
        Integer skipcnt = 1;

        //Iterate over excel rows
        for (Row tempRow : sheet) {
            System.out.println("Row------>" + tempRow.getPhysicalNumberOfCells());
            //            System.out.println("Repeting rows------>"+sheet.getRepeatingRows());
            System.out.println("FirstRowNum------>" + sheet.getFirstRowNum());
            System.out.println("getLastRowNum------>" + sheet.getLastRowNum());
            System.out.println("RowNum------>" + tempRow.getRowNum());
            System.out.println("RowNumFirst------>" + tempRow.getFirstCellNum());
            System.out.println("RowNumLast------>" + tempRow.getLastCellNum());

            System.out.println("skipcnt------>" + skipcnt);
            System.out.println("skipRw------>" + skipRw);

            if (skipcnt > skipRw) { //skip first n row for labels.
                System.out.println("CreateInsert------>");
                //Create new row in table
                executeOperation("CreateInsertTempTbl").execute();

                System.out.println("Get current row from iterator------>");
                //Get current row from iterator
                oracle.jbo.Row row = iter.getNavigatableRowIterator().getCurrentRow();
                int Index = 0;
                //Iterate over row's columns
                for (int column = 0; column < tempRow.getLastCellNum(); column++) {
                    System.out.println("Column------>" + column);

                    Cell MytempCell = tempRow.getCell(column);
                    if (MytempCell != null) {
                        System.out.println("MytempCell------>" + MytempCell);
                        Index = MytempCell.getColumnIndex();
                        System.out.println("Index------>" + Index);

                        if (Index == 0) {
                            System.out.println("NatureOfExpense------>" + MytempCell.getCellType());
                            System.out.println("NatureOfExpense------>" + MytempCell.getStringCellValue());


                            row.setAttribute("NatureOfExpense", MytempCell.getStringCellValue());

                        } else if (Index == 1) {
                            System.out.println("FromBusinessUnit------>" + MytempCell.getCellType());
                            System.out.println("FromBusinessUnit------>" + MytempCell.getStringCellValue());

                            row.setAttribute("FromBusinessUnit", MytempCell.getStringCellValue());

                        } else if (Index == 2) {

                            System.out.println("FromJobCode Type*****------>" + MytempCell.getCellType());

                            if (MytempCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                String str = NumberToTextConverter.toText(MytempCell.getNumericCellValue());
                                System.out.println("FromJobCode Value*****------>" + str);
                                row.setAttribute("FromJobCode", str);
                            } else {

                                row.setAttribute("FromJobCode", MytempCell.getStringCellValue());

                            }


                        } else if (Index == 3) {
                            System.out.println("CrGlAccount------>" + MytempCell.getCellType());
                            System.out.println("CrGlAccount------>" + MytempCell.getStringCellValue());

                            row.setAttribute("CrGlAccount", MytempCell.getStringCellValue());

                        } else if (Index == 4) {
                            System.out.println("FromOperatingUnit------>" + MytempCell.getCellType());
                            System.out.println("FromOperatingUnit------>" + MytempCell.getStringCellValue());

                            row.setAttribute("FromOperatingUnit", MytempCell.getStringCellValue());

                        } else if (Index == 5) {

                            System.out.println("FromDepartmentId Type*****------>" + MytempCell.getCellType());

                            if (MytempCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                String str = NumberToTextConverter.toText(MytempCell.getNumericCellValue());
                                System.out.println("FromDepartmentId Value*****------>" + str);
                                row.setAttribute("FromDepartmentId", str);
                            } else {

                                row.setAttribute("FromDepartmentId", MytempCell.getStringCellValue());

                            }


                        } else if (Index == 6) {
                            System.out.println("ToBusinessUnit------>" + MytempCell.getCellType());
                            System.out.println("ToBusinessUnit------>" + MytempCell.getStringCellValue());


                            row.setAttribute("ToBusinessUnit", MytempCell.getStringCellValue());

                        } else if (Index == 7) {
                            System.out.println("ToOperatingUnit------>" + MytempCell.getCellType());
                            System.out.println("ToOperatingUnit------>" + MytempCell.getStringCellValue());


                            row.setAttribute("ToOperatingUnit", MytempCell.getStringCellValue());

                        } else if (Index == 8) {

                            System.out.println("ToJobCode Type*****------>" + MytempCell.getCellType());

                            if (MytempCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                String str = NumberToTextConverter.toText(MytempCell.getNumericCellValue());
                                System.out.println("ToJobCode Value*****------>" + str);
                                row.setAttribute("ToJobCode", str);
                            } else {

                                row.setAttribute("ToJobCode", MytempCell.getStringCellValue());

                            }


                        } else if (Index == 9) {
                            System.out.println("ToDeptId Type*****------>" + MytempCell.getCellType());

                            if (MytempCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                String str = NumberToTextConverter.toText(MytempCell.getNumericCellValue());
                                System.out.println("ToDeptId Value*****------>" + str);
                                row.setAttribute("ToDeptId", str);
                            } else {

                                row.setAttribute("ToDeptId", MytempCell.getStringCellValue());

                            }


                        } else if (Index == 10) {
                            System.out.println("DrGlAccount------>" + MytempCell.getCellType());
                            System.out.println("DrGlAccount------>" + MytempCell.getStringCellValue());

                            row.setAttribute("DrGlAccount", MytempCell.getStringCellValue());

                        } else if (Index == 11) {
                            System.out.println("BaseCurrency------>" + MytempCell.getCellType());
                            System.out.println("BaseCurrency------>" + MytempCell.getStringCellValue());

                            row.setAttribute("BaseCurrency", MytempCell.getStringCellValue());

                        } else if (Index == 12) {
                            System.out.println("ToTransactionCurrency------>" + MytempCell.getCellType());
                            System.out.println("ToTransactionCurrency------>" + MytempCell.getStringCellValue());


                            row.setAttribute("ToTransactionCurrency", MytempCell.getStringCellValue());

                        } else if (Index == 13) {
                            System.out.println("TargetAmount------>" + MytempCell.getCellType());
                            System.out.println("TargetAmount------>" + MytempCell.getNumericCellValue());

                            row.setAttribute("TargetAmount", MytempCell.getNumericCellValue());

                        } else if (Index == 14) {
                            System.out.println("BookCode------>" + MytempCell.getCellType());
                            System.out.println("BookCode------>" + MytempCell.getStringCellValue());

                            row.setAttribute("BookCode", MytempCell.getStringCellValue());

                        } else if (Index == 15) {
                            System.out.println("TransactionPeriod------>" + MytempCell.getCellType());
                            System.out.println("TransactionPeriod------>" + MytempCell.getDateCellValue());

                            // row.setAttribute("TransactionPeriod", MytempCell.getStringCellValue());
                            java.util.Date date = MytempCell.getDateCellValue();
                            if (null != date) {
                                System.out.println("DATE-TransactionPeriod    :" + date);
                                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

                                String date1 = dateFormat.format(date);
                                System.out.println("date1" + date1);
                                try {
                                    date = dateFormat.parse(date1);
                                } catch (ParseException e) {
                                    System.out.println("Exception : " + e);
                                }
                                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                                oracle.jbo.domain.Date jboDate = new oracle.jbo.domain.Date(sqlDate);
                                row.setAttribute("TransactionPeriod", jboDate);
                            } else {
                                row.setAttribute("TransactionPeriod", null);
                            }


                        } else if (Index == 16) {
                            System.out.println("GlDate------>" + MytempCell.getCellType());
                            System.out.println("GlDate------>" + MytempCell.getDateCellValue());

                            java.util.Date date = MytempCell.getDateCellValue();
                            if (null != date) {
                                System.out.println("DATE-GlDate    :" + date);
                                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                                System.out.println("d");
                                String date1 = dateFormat.format(date);
                                System.out.println("date1" + date1);
                                try {
                                    date = dateFormat.parse(date1);
                                } catch (ParseException e) {
                                    System.out.println("Exception : " + e);
                                }
                                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                                oracle.jbo.domain.Date jboDate = new oracle.jbo.domain.Date(sqlDate);
                                row.setAttribute("GlDate", jboDate);
                            } else {
                                row.setAttribute("GlDate", null);
                            }

                        } else if (Index == 17) {
                            System.out.println("TransactionDate------>" + MytempCell.getCellType());


                            System.out.println("TransactionDate------>" + MytempCell.getDateCellValue());

                            java.util.Date date = MytempCell.getDateCellValue();
                            if (null != date) {
                                System.out.println("DATE-TransactionDate    :" + date);
                                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                                System.out.println("d");
                                String date1 = dateFormat.format(date);
                                System.out.println("date1" + date1);
                                try {
                                    date = dateFormat.parse(date1);
                                } catch (ParseException e) {
                                    System.out.println("Exception : " + e);
                                }
                                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                                oracle.jbo.domain.Date jboDate = new oracle.jbo.domain.Date(sqlDate);
                                row.setAttribute("TransactionDate", jboDate);
                            } else {
                                row.setAttribute("TransactionDate", null);
                            }

                        } else if (Index == 18) {
                            System.out.println("FromTransactionCurrency------>" + MytempCell.getCellType());
                            System.out.println("FromTransactionCurrency------>" + MytempCell.getStringCellValue());


                            row.setAttribute("FromTransactionCurrency", MytempCell.getStringCellValue());

                        } else if (Index == 19) {
                            System.out.println("TransactionAmount------>" + MytempCell.getCellType());
                            System.out.println("TransactionAmount------>" + MytempCell.getNumericCellValue());

                            row.setAttribute("TransactionAmount", MytempCell.getNumericCellValue());

                        } else if (Index == 20) {
                            System.out.println("FunctionalCurrency------>" + MytempCell.getCellType());
                            System.out.println("FunctionalCurrency------>" + MytempCell.getStringCellValue());

                            row.setAttribute("FunctionalCurrency", MytempCell.getStringCellValue());

                        } else if (Index == 21) {
                            System.out.println("AccountTreatment------>" + MytempCell.getCellType());
                            System.out.println("AccountTreatment------>" + MytempCell.getStringCellValue());

                            row.setAttribute("AccountTreatment", MytempCell.getStringCellValue());

                        } else if (Index == 22) {
                            System.out.println("PeoplesoftTransactionId------>" + MytempCell.getCellType());
                            System.out.println("PeoplesoftTransactionId------>" + MytempCell.getStringCellValue());

                            row.setAttribute("PeoplesoftTransactionId", MytempCell.getStringCellValue());

                        } else if (Index == 23) {
                            System.out.println("VendorId Type*****------>" + MytempCell.getCellType());

                            if (MytempCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                String str = NumberToTextConverter.toText(MytempCell.getNumericCellValue());
                                System.out.println("VendorId Value*****------>" + str);
                                row.setAttribute("VendorId", str);
                            } else {

                                row.setAttribute("VendorId", MytempCell.getStringCellValue());

                            }
                        } else if (Index == 24) {
                            System.out.println("PoNumber------>" + MytempCell.getCellType());
                            System.out.println("PoNumber------>" + MytempCell.getStringCellValue());

                            row.setAttribute("PoNumber", MytempCell.getStringCellValue());

                        } else if (Index == 25) {
                            System.out.println("VoucherId Type*****------>" + MytempCell.getCellType());

                            if (MytempCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                String str = NumberToTextConverter.toText(MytempCell.getNumericCellValue());
                                System.out.println("VoucherId Value*****------>" + str);
                                row.setAttribute("VoucherId", str);
                            } else {

                                row.setAttribute("VoucherId", MytempCell.getStringCellValue());

                            }


                        } else if (Index == 26) {
                            System.out.println("VoucherNo Type*****------>" + MytempCell.getCellType());

                            if (MytempCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                String str = NumberToTextConverter.toText(MytempCell.getNumericCellValue());
                                System.out.println("VoucherNo Value*****------>" + str);
                                row.setAttribute("VoucherNo", str);
                            } else {

                                row.setAttribute("VoucherNo", MytempCell.getStringCellValue());

                            }

                        } else if (Index == 27) {
                            System.out.println("SourceModule------>" + MytempCell.getCellType());
                            System.out.println("SourceModule------>" + MytempCell.getStringCellValue());

                            row.setAttribute("SourceModule", MytempCell.getStringCellValue());

                        } else {
                            System.out.println("-----> cell Is empty Please fill the cell with data" +
                                               tempRow.getRowNum() + "+" + tempRow.getCell(column));
                            System.out.println("-----> cell Is empty Please fill the cell with data at Index" + Index);
                            Index++;
                        }

                    }
                }


            }
            System.out.println("skipcntBefore----->" + skipcnt);

            skipcnt++;

            System.out.println("skipcntAfter----->" + skipcnt);


        }
        System.out.println("xlsx Commit");
        //Execute table viewObject
        executeOperation("Commit").execute();

    }

    public void okButtonDialogListner(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {

            executeOperation("ExecuteTransBCostAlloc").execute();
            executeOperation("ExecuteOtrTarget").execute();
            ADFUtils.saveNotifier();
        }
    }


    public void holdPopupBeginListener(PopupFetchEvent popupFetchEvent) {
        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding holditer = (DCIteratorBinding) bindings.get("SgsTransBCostAllocationVO1Iterator");
        ViewObject holdVO = holditer.getViewObject();
        RowSetIterator rsIter = holditer.getRowSetIterator();
        oracle.jbo.Row r = (oracle.jbo.Row) rsIter.getCurrentRow();


        CommonUtils util = new CommonUtils();
        Object user = (Object) util.getSessionScopeValue("_username").toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());

        r.setAttribute("Holdby", user);
        r.setAttribute("Holdon", date);
        
        
        
    }
  
  
    public void multiHoldPopupBeginListener(PopupFetchEvent popupFetchEvent) {
        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding holditer = (DCIteratorBinding) bindings.get("SgsTransBCostAllocationVO1Iterator");
        ViewObject holdVO = holditer.getViewObject();
        RowSetIterator rsIter = holditer.getRowSetIterator();
        oracle.jbo.Row r = (oracle.jbo.Row) rsIter.getCurrentRow();


        CommonUtils util = new CommonUtils();
        Object user = (Object) util.getSessionScopeValue("_username").toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());

        r.setAttribute("Holdby", user);
        r.setAttribute("Holdon", date);
        
        
        
    }



    public void releasePopupBeginListener(PopupFetchEvent popupFetchEvent) {
        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding holditer = (DCIteratorBinding) bindings.get("SgsTransBCostAllocationVO1Iterator");
        ViewObject holdVO = holditer.getViewObject();
        RowSetIterator rsIter = holditer.getRowSetIterator();
        oracle.jbo.Row r = (oracle.jbo.Row) rsIter.getCurrentRow();


        CommonUtils util = new CommonUtils();
        Object user = (Object) util.getSessionScopeValue("_username").toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());

        r.setAttribute("Releasedby", user);
        r.setAttribute("Releasedon", date);
        




    }
    

    public void multiReleasePopupBeginListener(PopupFetchEvent popupFetchEvent) {
        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding holditer = (DCIteratorBinding) bindings.get("SgsTransBCostAllocationVO1Iterator");
        ViewObject holdVO = holditer.getViewObject();
        RowSetIterator rsIter = holditer.getRowSetIterator();
        oracle.jbo.Row r = (oracle.jbo.Row) rsIter.getCurrentRow();


        CommonUtils util = new CommonUtils();
        Object user = (Object) util.getSessionScopeValue("_username").toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());

        r.setAttribute("Releasedby", user);
        r.setAttribute("Releasedon", date);
        
      

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
        DCIteratorBinding dciter = bindingContainer.findIteratorBinding("SgsTransBCostAllocationVO1Iterator");
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

    public void HoldReleaseCancelButton(PopupCanceledEvent popupCanceledEvent) {
        executeOperation("Rollback").execute();
    }

    public void setReleasePopup(RichPopup releasePopup) {
        this.releasePopup = releasePopup;
    }

    public RichPopup getReleasePopup() {
        return releasePopup;
    }

    public void setHoldReasonBind(RichInputText holdReasonBind) {
        this.holdReasonBind = holdReasonBind;
    }

    public RichInputText getHoldReasonBind() {
        return holdReasonBind;
    }

    public void setDueDateBind(RichInputDate dueDateBind) {
        this.dueDateBind = dueDateBind;
    }

    public RichInputDate getDueDateBind() {
        return dueDateBind;
    }

    public void setHoldRemarkBind(RichInputText holdRemarkBind) {
        this.holdRemarkBind = holdRemarkBind;
    }

    public RichInputText getHoldRemarkBind() {
        return holdRemarkBind;
    }

    public void setReleaseActionBind(RichInputText releaseActionBind) {
        this.releaseActionBind = releaseActionBind;
    }

    public RichInputText getReleaseActionBind() {
        return releaseActionBind;
    }

    public void setReleaseRemarksBind(RichInputText releaseRemarksBind) {
        this.releaseRemarksBind = releaseRemarksBind;
    }

    public RichInputText getReleaseRemarksBind() {
        return releaseRemarksBind;
    }

    public void setHoldPopup1(RichPopup holdPopup1) {
        this.holdPopup1 = holdPopup1;
    }

    public RichPopup getHoldPopup1() {
        return holdPopup1;
    }

    public void setReleasePopup1(RichPopup releasePopup1) {
        this.releasePopup1 = releasePopup1;
    }

    public RichPopup getReleasePopup1() {
        return releasePopup1;
    }

    public void setHoldReasonBind1(RichInputText holdReasonBind1) {
        this.holdReasonBind1 = holdReasonBind1;
    }

    public RichInputText getHoldReasonBind1() {
        return holdReasonBind1;
    }

    public void setDueDateBind1(RichInputDate dueDateBind1) {
        this.dueDateBind1 = dueDateBind1;
    }

    public RichInputDate getDueDateBind1() {
        return dueDateBind1;
    }

    public void setHoldRemarkBind1(RichInputText holdRemarkBind1) {
        this.holdRemarkBind1 = holdRemarkBind1;
    }

    public RichInputText getHoldRemarkBind1() {
        return holdRemarkBind1;
    }

    public void setReleaseActionBind1(RichInputText releaseActionBind1) {
        this.releaseActionBind1 = releaseActionBind1;
    }

    public RichInputText getReleaseActionBind1() {
        return releaseActionBind1;
    }

    public void setReleaseRemarksBind1(RichInputText releaseRemarksBind1) {
        this.releaseRemarksBind1 = releaseRemarksBind1;
    }

    public RichInputText getReleaseRemarksBind1() {
        return releaseRemarksBind1;
    }

    public void confirmProcessingBtn(ActionEvent actionEvent) {
        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding confIter = (DCIteratorBinding) bindings.get("SgsTransBCostAllocationVO1Iterator");
        ViewObject confVO = confIter.getViewObject();

       
        oracle.jbo.Row[] selectedRows = confVO.getFilteredRows("Selected", true);
        System.out.println("*****Selected rows****" + selectedRows.length);
        for (oracle.jbo.Row rw : selectedRows) {
                if(rw.getAttribute("TransactionStatus").equals("New")||rw.getAttribute("TransactionStatus").equals("Transaction Released from Hold")){
                    rw.setAttribute("TransactionStatus", "Confirmed for Processing");
         }
   }
        executeOperation("Commit").execute();
  }
    
    
    public void onDocumentsDelete(ActionEvent actionEvent) {
        executeOperation("DeleteDocs").execute();
        executeOperation(SAVE_DATA).execute();      
        ADFUtils.deleteNotifier();
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

    
    public void onPstTxnDocsUpload(ValueChangeEvent valueChangeEvent) {
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

                        DCIteratorBinding docs = getDCIteratorBindings("SgsPstTxnDocAttachVO1Iterator");
                        oracle.jbo.Row row = docs.getCurrentRow();
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
    
    
    public void onPstTxnDocsDownload(FacesContext facesContext, OutputStream outputStream) {
            DCBindingContainer bindingContainer =
                (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding imageIter = (DCIteratorBinding) bindingContainer.get("SgsPstTxnDocAttachVO1Iterator");
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
}