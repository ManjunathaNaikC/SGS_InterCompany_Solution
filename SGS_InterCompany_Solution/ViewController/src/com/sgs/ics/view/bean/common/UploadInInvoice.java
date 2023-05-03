package com.sgs.ics.view.bean.common;

import com.sgs.ics.model.bc.am.SGSAppModuleImpl;
import com.sgs.ics.ui.utils.ADFUtils;

import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.ViewObject;

import org.apache.myfaces.trinidad.model.UploadedFile;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UploadInInvoice {
    private RichSelectOneChoice natureOfExpenseLovBind;
    private RichPopup directChargePopupBind;
    private RichSelectOneChoice adjNatureofExpBind;
    private RichSelectOneChoice adjReverseEntryBind;
    private RichSelectOneChoice adjTxnCategoryBind;
    private RichPopup adjEntryPopupBind;
    private static final ADFLogger LOG = ADFLogger.createADFLogger(UploadInInvoice.class);
    private SGSAppModuleImpl am = new SGSAppModuleImpl();
    private RichSelectBooleanCheckbox digitalPLBind;
    private RichSelectBooleanCheckbox taxApplicaBind;
    private RichSelectBooleanCheckbox reverseEntryBind;

    public UploadInInvoice() {
    }
    
    
    /**Method to upload XLS/XLSX file and read data from table
     * @param valueChangeEvent
     */
    public void uploadFileVCE(ValueChangeEvent valueChangeEvent) {
        LOG.info("Inside upload file");
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

        } catch (IOException e) {
            LOG.info("Exception : " + e);
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
        LOG.info("Inside Read xlsx Method");

        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding iter = (DCIteratorBinding) bindings.get("SgsDrtCrossChargeVO1Iterator");


        //Use XSSFWorkbook for XLS file
        XSSFWorkbook WorkBook = null;
        int sheetIndex = 0;

        try {
            WorkBook = new XSSFWorkbook(xlsx);
        } catch (IOException e) {
            LOG.info("Exception : " + e);
        }
        XSSFSheet sheet = WorkBook.getSheetAt(sheetIndex);

        Integer skipRw = 1;
        Integer skipcnt = 1;

        //Iterate over excel rows
        for (Row tempRow : sheet) {
            LOG.info("Row------>" + tempRow.getPhysicalNumberOfCells());
            //            LOG.info("Repeting rows------>"+sheet.getRepeatingRows());
            LOG.info("FirstRowNum------>" + sheet.getFirstRowNum());
            LOG.info("getLastRowNum------>" + sheet.getLastRowNum());
            LOG.info("RowNum------>" + tempRow.getRowNum());
            LOG.info("RowNumFirst------>" + tempRow.getFirstCellNum());
            LOG.info("RowNumLast------>" + tempRow.getLastCellNum());

            LOG.info("skipcnt------>" + skipcnt);
            LOG.info("skipRw------>" + skipRw);

            if (skipcnt > skipRw) { //skip first n row for labels.
                LOG.info("CreateInsert------>");
                //Create new row in table
                executeOperation("CreateInsertDirectCharge").execute();

                LOG.info("Get current row from iterator------>");
                //Get current row from iterator
                oracle.jbo.Row row = iter.getNavigatableRowIterator().getCurrentRow();
                int Index = 0;
                //Iterate over row's columns
                for (int column = 0; column < tempRow.getLastCellNum(); column++) {
                    LOG.info("Column------>" + column);

                    Cell MytempCell = tempRow.getCell(column);
                    if (MytempCell != null) {
                        LOG.info("MytempCell------>" + MytempCell);
                        Index = MytempCell.getColumnIndex();
                        LOG.info("Index------>" + Index);

                        if (Index == 0) {
                            LOG.info("Allocation Basis------>" + MytempCell.getCellType());
                            LOG.info("Allocation Basis------>" + MytempCell.getStringCellValue());


                            row.setAttribute("AllocationBasis", MytempCell.getStringCellValue());

                        } else if (Index == 1) {
                            LOG.info("From BU------>" + MytempCell.getCellType());
                            LOG.info("From BU------>" + MytempCell.getStringCellValue());

                            row.setAttribute("FromBu", MytempCell.getStringCellValue());

                        } else if (Index == 2) {
                            LOG.info("From OU Type------>" + MytempCell.getCellType());
                            LOG.info("From OU Value------>" + MytempCell.getStringCellValue());

                            row.setAttribute("FromOu", MytempCell.getStringCellValue());

                        }  else if (Index == 3) {
                            LOG.info("From JC------>" + MytempCell.getCellType());
                            LOG.info("From JC------>" + MytempCell.getStringCellValue());

                            row.setAttribute("FromJobCode", MytempCell.getStringCellValue());

                        } else if (Index == 4) {
                            LOG.info("From Dept------>" + MytempCell.getCellType());
                            LOG.info("From Dept------>" + MytempCell.getStringCellValue());

                            row.setAttribute("FromDeptId", MytempCell.getStringCellValue());

                        } else if (Index == 5) {
                            LOG.info("SourceCurrency------>" + MytempCell.getCellType());
                            LOG.info("SourceCurrency------>" + MytempCell.getStringCellValue());

                            row.setAttribute("SourceCurrency", MytempCell.getStringCellValue());
                    
                        } else if (Index == 6) {
                            LOG.info("To BU------>" + MytempCell.getCellType());
                            LOG.info("To BU------>" + MytempCell.getStringCellValue());


                            row.setAttribute("ToBu", MytempCell.getStringCellValue());

                        } else if (Index == 7) {
                            LOG.info("To OU------>" + MytempCell.getCellType());
                            LOG.info("To OU------>" + MytempCell.getStringCellValue());


                            row.setAttribute("ToOu", MytempCell.getStringCellValue());

                        } else if (Index == 8) {
                            LOG.info("To Job Code Type------>" + MytempCell.getCellType());
                            LOG.info("To Job Code Value------>" + MytempCell.getStringCellValue());


                            row.setAttribute("ToJobCode", MytempCell.getStringCellValue());

                        } else if (Index == 9) {
                            LOG.info("To Dept Type------>" + MytempCell.getCellType());
                            LOG.info("To Dept Value------>" + MytempCell.getStringCellValue());


                            row.setAttribute("ToDeptId", MytempCell.getStringCellValue());

                        }  else if (Index == 10) {
                            LOG.info("Offset GL Account------>" + MytempCell.getCellType());
                            LOG.info("Offset GL Account------>" + MytempCell.getStringCellValue());

                            row.setAttribute("OffsetGlAccountCr", MytempCell.getStringCellValue());

                        } else if (Index == 11) {
                            LOG.info("Target GL Account------>" + MytempCell.getCellType());
                            LOG.info("Target GL Account------>" + MytempCell.getStringCellValue());

                            row.setAttribute("TargetGlAccountDr", MytempCell.getStringCellValue());

                        } else if (Index == 12) {

                            LOG.info("Allocated Amount Type*****------>" + MytempCell.getCellType());

                            if (MytempCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                String str = NumberToTextConverter.toText(MytempCell.getNumericCellValue());
                                LOG.info("Allocated Amount Value*****------>" + str);
                                row.setAttribute("AllocatedAmount", str);
                            } else {

                                row.setAttribute("AllocatedAmount", MytempCell.getNumericCellValue());

                            }


                        }  else if (Index == 13) {
                            LOG.info("Accounting Treatment------>" + MytempCell.getCellType());
                            LOG.info("Accounting Treatment------>" + MytempCell.getStringCellValue());

                            row.setAttribute("AccountingTreatment", MytempCell.getStringCellValue());

                        }  else {
                            LOG.info("-----> cell Is empty Please fill the cell with data" +
                                               tempRow.getRowNum() + "+" + tempRow.getCell(column));
                            LOG.info("-----> cell Is empty Please fill the cell with data at Index" + Index);
                            Index++;
                        }

                    }
                }


            }
            LOG.info("skipcntBefore----->" + skipcnt);

            skipcnt++;

            LOG.info("skipcntAfter----->" + skipcnt);


        }
        LOG.info("xlsx Commit");
        //Execute table viewObject
        executeOperation("Commit").execute();

    }
    
    /**    
     *Save button on the popup for upload Direct charge button
    */                                                 
    public void directChargeSave(ActionEvent actionEvent) {
        // Add event code here...
        String naturevalue = natureOfExpenseLovBind.getValue().toString();
        String lookup = null;
        
        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding natureIter = (DCIteratorBinding) bindings.get("NatureOfExpenseLookupVO2Iterator");
        ViewObject natureVO = natureIter.getViewObject();
       oracle.jbo.Row natRow= natureVO.getCurrentRow();
       if(natRow.getAttribute("MEANING") == naturevalue){
             lookup = natRow.getAttribute("LOOKUPCODE").toString();
             LOG.info("lookup code  : "+ lookup);
           }
        DCIteratorBinding directIter = (DCIteratorBinding) bindings.get("SgsDrtCrossChargeVO1Iterator");
        ViewObject directCVO = directIter.getViewObject();
        
        oracle.jbo.Row[] selectedRows =directCVO.getFilteredRows("NatureOfExpense", null);
        LOG.info("*****Selected rows****"+selectedRows.length);
        String status = "New";
        
        for(oracle.jbo.Row rw:selectedRows){
        if (null != natureOfExpenseLovBind.getValue()) {
//            String naturevalue = natureOfExpenseLovBind.getValue().toString();
            LOG.info("Nature of Expense : " + lookup);
            rw.setAttribute("NatureOfExpense", lookup);
            rw.setAttribute("AllocationStatus", status);
            
            }
        }
        executeOperation("Commit").execute();
        natureOfExpenseLovBind.setValue(null);
        directChargePopupBind.hide();
        ADFUtils.saveNotifier();
        directChargeRun();
        
    }
    
    
    /**
     *Save button on the popup for Upload Adjustment Entries button
     */
    public void adjEntrySave(ActionEvent actionEvent) {
        // Add event code here...
        String nature_value = adjNatureofExpBind.getValue().toString();
        String reversEntry = reverseEntryBind.getValue().toString();
        String txnCategory = adjTxnCategoryBind.getValue().toString();
        String digitalPL = digitalPLBind.getValue().toString();
        System.out.println("Digital P&L : " + digitalPL);
        String AddExpQualifier = (String) AdfFacesContext.getCurrentInstance()
                                                         .getPageFlowScope()
                                                         .get("selectedAddExpQualifier");
        String taxApplicable = taxApplicaBind.getValue().toString();
        String lookup = null;

        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding natureIter = (DCIteratorBinding) bindings.get("NatureOfExpenseLookupVO2Iterator");
        ViewObject natureVO = natureIter.getViewObject();
        oracle.jbo.Row natRow = natureVO.getCurrentRow();
        if (natRow.getAttribute("MEANING") == nature_value) {
            lookup = natRow.getAttribute("LOOKUPCODE").toString();
            LOG.info("lookup code  : " + lookup);
        }


        //        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding directIter = (DCIteratorBinding) bindings.get("SgsDrtCrossChargeVO1Iterator");
        ViewObject directCVO = directIter.getViewObject();

        oracle.jbo.Row[] selectedRows = directCVO.getFilteredRows("NatureOfExpense", null);
        LOG.info("*****Selected rows****" + selectedRows.length);
        String status = "New";
        String alloc_basis = "DIRECT_CROSS_CHARGE";
//        String txn_type = "ADJUSTMENT_ENTRY";

        for (oracle.jbo.Row rw : selectedRows) {
            if (null != adjNatureofExpBind.getValue()) {
                LOG.info("Nature of Expense : " + lookup);
                rw.setAttribute("NatureOfExpense", lookup);
                //            rw.setAttribute("REVERSABLEENTRY", reversEntry);
                rw.setAttribute("TRANSACTIONCATEGORY", txnCategory);
                rw.setAttribute("AllocationBasis", alloc_basis);
                rw.setAttribute("AllocationStatus", status);
                rw.setAttribute("AdditionalExpQualifier", AddExpQualifier);
                if ("true".equalsIgnoreCase(reversEntry)) {
                    rw.setAttribute("REVERSABLEENTRY", "Y");
                } else {
                    rw.setAttribute("REVERSABLEENTRY", "N");
                }

                if ("true".equalsIgnoreCase(taxApplicable)) {
                    rw.setAttribute("TaxApplicability", "Y");
                } else {
                    rw.setAttribute("TaxApplicability", "N");
                }


            }
            if (digitalPL == "true") {
                rw.setAttribute("TXNTYPE", "Digital P&L");
            } else {
                rw.setAttribute("TXNTYPE", "ADJUSTMENT_ENTRY");
            }
        }
        executeOperation("Commit").execute();
        adjNatureofExpBind.setValue(null);
        adjEntryPopupBind.hide();
        ADFUtils.saveNotifier();
       
        


    }
    
    
    
    public void directChargeRun() {
        // Add event code here...
        LOG.info("inside DRTCROSS CHARGE**********************");
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            
            conn = am.getDBConnection();
            String SPsql = "EXEC USP_SCN_DRTCROSS_CHARGE "; // for stored proc
            //Connection con = SmartPoolFactory.getConnection();   // java.sql.Connection
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
    
//    public Connection getDBConnection() {
//            Connection conn = null;
//        try {
////               String connectionUrl = "jdbc:sqlserver://localhost;instanceName=SQLEXPRESS;databasename=SGS_NEW;integratedSecurity=true;";
////                conn = DriverManager.getConnection(connectionUrl);
//            conn = DriverManager.getConnection("jdbc:sqlserver://ASBCOLPS02:1433;databaseName=DEVINTER","EYUser","Ey@123");
//
//        } catch (SQLException sqle) {
//            // TODO: Add catch code
//            sqle.printStackTrace();
//        } finally {
//
//        }
//               
//         return conn;   
//        }

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

    public void setAdjNatureofExpBind(RichSelectOneChoice adjNatureofExpBind) {
        this.adjNatureofExpBind = adjNatureofExpBind;
    }

    public RichSelectOneChoice getAdjNatureofExpBind() {
        return adjNatureofExpBind;
    }

    public void setAdjReverseEntryBind(RichSelectOneChoice adjReverseEntryBind) {
        this.adjReverseEntryBind = adjReverseEntryBind;
    }

    public RichSelectOneChoice getAdjReverseEntryBind() {
        return adjReverseEntryBind;
    }

    public void setAdjTxnCategoryBind(RichSelectOneChoice adjTxnCategoryBind) {
        this.adjTxnCategoryBind = adjTxnCategoryBind;
    }

    public RichSelectOneChoice getAdjTxnCategoryBind() {
        return adjTxnCategoryBind;
    }

    public void setAdjEntryPopupBind(RichPopup adjEntryPopupBind) {
        this.adjEntryPopupBind = adjEntryPopupBind;
    }

    public RichPopup getAdjEntryPopupBind() {
        return adjEntryPopupBind;
    }

    public void setDigitalPLBind(RichSelectBooleanCheckbox digitalPLBind) {
        this.digitalPLBind = digitalPLBind;
    }

    public RichSelectBooleanCheckbox getDigitalPLBind() {
        return digitalPLBind;
    }

    public void setTaxApplicaBind(RichSelectBooleanCheckbox taxApplicaBind) {
        this.taxApplicaBind = taxApplicaBind;
    }

    public RichSelectBooleanCheckbox getTaxApplicaBind() {
        return taxApplicaBind;
    }

    public void setReverseEntryBind(RichSelectBooleanCheckbox reverseEntryBind) {
        this.reverseEntryBind = reverseEntryBind;
    }

    public RichSelectBooleanCheckbox getReverseEntryBind() {
        return reverseEntryBind;
    }
}
