package com.sgs.ics.view.bean.common;

import com.sgs.ics.ui.utils.ADFUtils;

import java.io.IOException;

import java.io.InputStream;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.apache.myfaces.trinidad.model.UploadedFile;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TransBCostAllocationBean {
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
     * Generic Method to execute operation
     * */
    public OperationBinding executeOperation(String operation) {
        OperationBinding createParam = getBindingsCont().getOperationBinding(operation);
        return createParam;

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

                            row.setAttribute("NatureOfExpense", MytempCell.getStringCellValue());

                        } else if (Index == 1) {

                            row.setAttribute("FromBusinessUnit", MytempCell.getStringCellValue());

                        } else if (Index == 2) {

                            row.setAttribute("FromJobCode", MytempCell.getStringCellValue());

                        } else if (Index == 3) {

                            row.setAttribute("CrGlAccount", MytempCell.getStringCellValue());

                        } else if (Index == 4) {

                            row.setAttribute("FromOperatingUnit", MytempCell.getStringCellValue());

                        } else if (Index == 5) {

                            row.setAttribute("FromDepartmentId", MytempCell.getStringCellValue());

                        } else if (Index == 6) {


                            row.setAttribute("ToBusinessUnit", MytempCell.getStringCellValue());

                        } else if (Index == 7) {


                            row.setAttribute("ToOperatingUnit", MytempCell.getStringCellValue());

                        } else if (Index == 8) {

                            row.setAttribute("ToJobCode", MytempCell.getStringCellValue());

                        } else if (Index == 9) {


                            row.setAttribute("ToDeptId", MytempCell.getStringCellValue());

                        } else if (Index == 10) {

                            row.setAttribute("DrGlAccount", MytempCell.getStringCellValue());

                        } else if (Index == 11) {

                            row.setAttribute("BaseCurrency", MytempCell.getStringCellValue());

                        } else if (Index == 12) {


                            row.setAttribute("ToTransactionCurrency", MytempCell.getStringCellValue());

                        } else if (Index == 13) {

                            row.setAttribute("TargetAmount", MytempCell.getNumericCellValue());

                        } else if (Index == 14) {

                            row.setAttribute("BookCode", MytempCell.getStringCellValue());

                        } else if (Index == 15) {

                            row.setAttribute("TransactionPeriod", MytempCell.getStringCellValue());

                        } else if (Index == 16) {

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
                            

                            row.setAttribute("FromTransactionCurrency", MytempCell.getStringCellValue());

                        } else if (Index == 19) {
                            
                            row.setAttribute("TransactionAmount", MytempCell.getNumericCellValue());
                            
                        } else if (Index == 20) {
                            
                            row.setAttribute("FunctionalCurrency", MytempCell.getStringCellValue());
                            
                        } else if (Index == 21) {
                            
                            row.setAttribute("AccountTreatment", MytempCell.getStringCellValue());
                            
                        } else if (Index == 22) {
                            
                            row.setAttribute("PeoplesoftTransactionId", MytempCell.getStringCellValue());
                            
                        } else if (Index == 23) {
                            
                            row.setAttribute("VendorId", MytempCell.getStringCellValue());
                            
                        } else if (Index == 24) {
                            
                            row.setAttribute("PoNumber", MytempCell.getStringCellValue());
                            
                        } else if (Index == 25) {
                            
                            row.setAttribute("VoucherId", MytempCell.getStringCellValue());
                            
                        } else if (Index == 26) {
                            
                            row.setAttribute("VoucherNo", MytempCell.getStringCellValue());
                            
                        } else if (Index == 27) {
                            
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
    if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok)
    {
        
        executeOperation("ExecuteTransBCostAlloc").execute();
        executeOperation("ExecuteOtrTarget").execute();
        ADFUtils.saveNotifier();
    }
  }
}
