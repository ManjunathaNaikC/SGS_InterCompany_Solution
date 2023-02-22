package com.sgs.ics.view.bean.common;

import com.sgs.ics.ui.utils.ADFUtils;

import java.io.IOException;

import java.io.InputStream;

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.UploadedFile;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class UploadExcelBean {
    public UploadExcelBean() {
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
        DCIteratorBinding iter = (DCIteratorBinding) bindings.get("sgsStatisticalDataTempVO1Iterator");

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
                executeOperation("CreateInsert").execute();
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
        DCIteratorBinding iter = (DCIteratorBinding) bindings.get("sgsStatisticalDataTempVO1Iterator");


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
            System.out.println("Row------>"+tempRow.getPhysicalNumberOfCells());
//            System.out.println("Repeting rows------>"+sheet.getRepeatingRows());
            System.out.println("FirstRowNum------>"+sheet.getFirstRowNum());
            System.out.println("getLastRowNum------>"+sheet.getLastRowNum());
            System.out.println("RowNum------>"+tempRow.getRowNum());
            System.out.println("RowNumFirst------>"+tempRow.getFirstCellNum());
            System.out.println("RowNumLast------>"+tempRow.getLastCellNum());
            
            System.out.println("skipcnt------>"+skipcnt);
            System.out.println("skipRw------>"+skipRw);
            
            if (skipcnt > skipRw) { //skip first n row for labels.
                System.out.println("CreateInsert------>");
                //Create new row in table
                executeOperation("CreateInsert").execute();
                
                System.out.println("Get current row from iterator------>");
                //Get current row from iterator
                oracle.jbo.Row row = iter.getNavigatableRowIterator().getCurrentRow();
                int Index = 0;
                //Iterate over row's columns
                for (int column = 0; column < tempRow.getLastCellNum(); column++) {
                    System.out.println("Column------>"+column);

                    Cell MytempCell = tempRow.getCell(column);
                    if (MytempCell != null) {
                        System.out.println("MytempCell------>"+MytempCell);
                        Index = MytempCell.getColumnIndex();
                        System.out.println("Index------>"+Index);

                        if (Index == 0) {
                            System.out.println("StatisticalDataId------>"+MytempCell.getCellType());
                            System.out.println("StatisticalDataId------>"+MytempCell.getNumericCellValue());
                            
                            row.setAttribute("StatisticalDataId", MytempCell.getNumericCellValue());
                             
                        } else if (Index == 1) {
                            System.out.println("NATUREOFEXPENSE------>"+MytempCell.getStringCellValue());
                            System.out.println("NATUREOFEXPENSEAVVVV------>"+row.getAttribute("NATUREOFEXPENSE"));
                            
                            
                            row.setAttribute("NATUREOFEXPENSE", MytempCell.getStringCellValue());

                        } else if (Index == 2) {
                            System.out.println("StatisticalDataCategory------>"+MytempCell.getStringCellValue());
                            System.out.println("StatisticalDataCategoryAVVVV------>"+row.getAttribute("StatisticalDataCategory"));
                            
                            
                            row.setAttribute("StatisticalDataCategory", MytempCell.getStringCellValue());

                        } else if (Index == 3) {
                            
                            System.out.println("FROMBU------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("FROMBU", MytempCell.getStringCellValue());

                        } else if (Index == 4) {
                            
                            System.out.println("FROMJOBCODE------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("FROMJOBCODE", MytempCell.getStringCellValue());

                        } else if (Index == 5) {
                            
                            System.out.println("FROMOU------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("FROMOU", MytempCell.getStringCellValue());

                        } else if (Index == 6) {
                            
                            System.out.println("FROMDEPTID------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("FROMDEPTID", MytempCell.getStringCellValue());

                        } else if (Index == 7) {
                            
                            System.out.println("ToBusinessUnit------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("ToBusinessUnit", MytempCell.getStringCellValue());

                        } else if (Index == 8) {
                            System.out.println("ToJobCode------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("ToJobCode", MytempCell.getStringCellValue());

                        } else if (Index == 9) {
                            System.out.println("ToOperatingUnit------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("ToOperatingUnit", MytempCell.getStringCellValue());

                        } else if (Index == 10) {
                            System.out.println("ToDepartmentId------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("ToDepartmentId", MytempCell.getStringCellValue());

                        } else if (Index == 11) {
                            System.out.println("StatisticalData------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("StatisticalData", MytempCell.getStringCellValue());

                        } else if (Index == 12) {
                            
                            System.out.println("STATGEOGRAPHY------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("STATGEOGRAPHY", MytempCell.getStringCellValue());

                        } else if (Index == 13) {
                            
                            System.out.println("INPUTPROVIDER------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("INPUTPROVIDER", MytempCell.getStringCellValue());

                        } else if (Index == 14) {
                            
                            System.out.println("ADDTEXPENSECAT------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("ADDTEXPENSECAT", MytempCell.getStringCellValue());

                        } else if (Index == 15) {
                            
                            System.out.println("GLACCOUNT------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("GLACCOUNT", MytempCell.getStringCellValue());

                        } else if (Index == 16) {
                            System.out.println("UnitOfMeasure------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("UnitOfMeasure", MytempCell.getStringCellValue());

                        } else if (Index == 17) {
                            System.out.println("CostGroup------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("CostGroup", MytempCell.getStringCellValue());

                        } else if (Index == 18) {
                            System.out.println("Currency------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("Currency", MytempCell.getStringCellValue());

                        } else if (Index == 19) {
                            System.out.println("EmployeeId------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("EmployeeId", MytempCell.getStringCellValue());

                        } else if (Index == 20) {
                            
                            System.out.println("EMPGRADE------>"+MytempCell.getNumericCellValue());
                            
                            row.setAttribute("EMPGRADE", MytempCell.getNumericCellValue());

                        } else if (Index == 21) {
                            System.out.println("TargetAmount------>"+MytempCell.getNumericCellValue());
                            
                            row.setAttribute("TargetAmount", MytempCell.getNumericCellValue());

                        } else if (Index == 22) {
                            System.out.println("RejectedReason------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("RejectedReason", MytempCell.getStringCellValue());

                        } else if (Index == 23) {
                            System.out.println("RejectionComments------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("RejectionComments", MytempCell.getStringCellValue());

                        } else if (Index == 24) {
                            System.out.println("ValidityFrom------>"+MytempCell.getDateCellValue());
                            
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
                        } else if (Index == 25) {
                            System.out.println("ValidityTill------>"+MytempCell.getDateCellValue());
                            
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

                         
                        } else {
                            System.out.println("-----> cell Is empty Please fill the cell with data"+tempRow.getRowNum()+"+"+tempRow.getCell(column));
                            System.out.println("-----> cell Is empty Please fill the cell with data at Index"+Index);
                            Index++;
                        }

                    }
                }
                
                
                
                

            }
            System.out.println("skipcntBefore----->"+skipcnt);
            
            skipcnt++;
            
            System.out.println("skipcntAfter----->"+skipcnt);
            
            
            
        }
        System.out.println("xlsx Commit");
        //Execute table viewObject
        executeOperation("Commit").execute();
        
    }
    
    
    
    public void okButtonDialogListner(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {

            executeOperation("ExecuteStatisticalData").execute();
            ADFUtils.saveNotifier();
        }

     }
}