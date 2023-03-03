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
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.ViewObject;

import org.apache.myfaces.trinidad.model.UploadedFile;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UploadInInvoice {
    private RichSelectOneChoice natureOfExpenseLovBind;
    private RichPopup directChargePopupBind;


    public UploadInInvoice() {
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
             else {
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
        DCIteratorBinding iter = (DCIteratorBinding) bindings.get("SgsDrtCrossChargeVO1Iterator");


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
                executeOperation("CreateInsertDirectCharge").execute();

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
                            System.out.println("Allocation Basis------>" + MytempCell.getCellType());
                            System.out.println("Allocation Basis------>" + MytempCell.getStringCellValue());


                            row.setAttribute("AllocationBasis", MytempCell.getStringCellValue());

                        } else if (Index == 1) {
                            System.out.println("From BU------>" + MytempCell.getCellType());
                            System.out.println("From BU------>" + MytempCell.getStringCellValue());

                            row.setAttribute("FromBu", MytempCell.getStringCellValue());

                        } else if (Index == 2) {
                            System.out.println("From OU Type------>" + MytempCell.getCellType());
                            System.out.println("From OU Value------>" + MytempCell.getStringCellValue());

                            row.setAttribute("FromOu", MytempCell.getStringCellValue());

                        }  else if (Index == 3) {
                            System.out.println("From JC------>" + MytempCell.getCellType());
                            System.out.println("From JC------>" + MytempCell.getStringCellValue());

                            row.setAttribute("FromJobCode", MytempCell.getStringCellValue());

                        } else if (Index == 4) {
                            System.out.println("From Dept------>" + MytempCell.getCellType());
                            System.out.println("From Dept------>" + MytempCell.getStringCellValue());

                            row.setAttribute("FromDeptId", MytempCell.getStringCellValue());

                        } else if (Index == 5) {

                            System.out.println("Source Currency Type*****------>" + MytempCell.getCellType());

                            if (MytempCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                String str = NumberToTextConverter.toText(MytempCell.getNumericCellValue());
                                System.out.println("Source Currency Value*****------>" + str);
                                row.setAttribute("SourceCurrency", str);
                            } else {

                                row.setAttribute("SourceCurrency", MytempCell.getNumericCellValue());

                            }


                        } else if (Index == 6) {
                            System.out.println("To BU------>" + MytempCell.getCellType());
                            System.out.println("To BU------>" + MytempCell.getStringCellValue());


                            row.setAttribute("ToBu", MytempCell.getStringCellValue());

                        } else if (Index == 7) {
                            System.out.println("To OU------>" + MytempCell.getCellType());
                            System.out.println("To OU------>" + MytempCell.getStringCellValue());


                            row.setAttribute("ToOu", MytempCell.getStringCellValue());

                        } else if (Index == 8) {
                            System.out.println("To Job Code Type------>" + MytempCell.getCellType());
                            System.out.println("To Job Code Value------>" + MytempCell.getStringCellValue());


                            row.setAttribute("ToJobCode", MytempCell.getStringCellValue());

                        } else if (Index == 9) {
                            System.out.println("To Dept Type------>" + MytempCell.getCellType());
                            System.out.println("To Dept Value------>" + MytempCell.getStringCellValue());


                            row.setAttribute("ToDeptId", MytempCell.getStringCellValue());

                        }  else if (Index == 10) {
                            System.out.println("Offset GL Account------>" + MytempCell.getCellType());
                            System.out.println("Offset GL Account------>" + MytempCell.getStringCellValue());

                            row.setAttribute("OffsetGlAccountCr", MytempCell.getStringCellValue());

                        } else if (Index == 11) {
                            System.out.println("Target GL Account------>" + MytempCell.getCellType());
                            System.out.println("Target GL Account------>" + MytempCell.getStringCellValue());

                            row.setAttribute("TARGETGLACCOUNTDR", MytempCell.getStringCellValue());

                        } else if (Index == 12) {

                            System.out.println("Allocated Amount Type*****------>" + MytempCell.getCellType());

                            if (MytempCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                String str = NumberToTextConverter.toText(MytempCell.getNumericCellValue());
                                System.out.println("Allocated Amount Value*****------>" + str);
                                row.setAttribute("AllocatedAmount", str);
                            } else {

                                row.setAttribute("AllocatedAmount", MytempCell.getNumericCellValue());

                            }


                        }  else if (Index == 13) {
                            System.out.println("Accounting Treatment------>" + MytempCell.getCellType());
                            System.out.println("Accounting Treatment------>" + MytempCell.getStringCellValue());

                            row.setAttribute("AccountingTreatment", MytempCell.getStringCellValue());

                        }  else {
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
    
    /**    
     *Save button on the popup for upload Direct charge button
    */                                                 
    public void directChargeSave(ActionEvent actionEvent) {
        // Add event code here...
        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding directIter = (DCIteratorBinding) bindings.get("SgsDrtCrossChargeVO1Iterator");
        ViewObject directCVO = directIter.getViewObject();
        
        oracle.jbo.Row[] selectedRows =directCVO.getFilteredRows("NatureOfExpense", null);
        System.out.println("*****Selected rows****"+selectedRows.length);
        String status = "New";
        
        for(oracle.jbo.Row rw:selectedRows){
        if (null != natureOfExpenseLovBind.getValue()) {
            String naturevalue = natureOfExpenseLovBind.getValue().toString();
            System.out.println("Nature of Expense : " + naturevalue);
            rw.setAttribute("NatureOfExpense", naturevalue);
            rw.setAttribute("ALLOCATIONSTATUS", status);
            
            }
        }
        executeOperation("Commit").execute();
        natureOfExpenseLovBind.setValue(null);
        directChargePopupBind.hide();
        ADFUtils.saveNotifier();
    }

    public void setNatureOfExpenseLovBind(RichSelectOneChoice natureOfExpenseLovBind) {
        this.natureOfExpenseLovBind = natureOfExpenseLovBind;
    }

    public RichSelectOneChoice getNatureOfExpenseLovBind() {
        return natureOfExpenseLovBind;
    }

    public void setDirectChargePopupBind(RichPopup directChargePopupBind) {
        this.directChargePopupBind = directChargePopupBind;
    }

    public RichPopup getDirectChargePopupBind() {
        return directChargePopupBind;
    }
}
