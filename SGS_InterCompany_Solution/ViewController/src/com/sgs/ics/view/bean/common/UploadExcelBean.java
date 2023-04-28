package com.sgs.ics.view.bean.common;

import com.sgs.ics.ui.utils.ADFUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.InputStream;

import java.io.OutputStream;

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

import java.net.MalformedURLException;

import oracle.adf.model.binding.DCBindingContainer;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.ViewObject;

import org.apache.poi.ss.util.NumberToTextConverter;


public class UploadExcelBean {
    private static final ADFLogger LOG = ADFLogger.createADFLogger(UploadExcelBean.class);
    public UploadExcelBean() {
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

            } else {
                FacesMessage msg = new FacesMessage("File format not supported.-- Upload XLS or XLSX file");
                msg.setSeverity(FacesMessage.SEVERITY_WARN);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            //            AdfFacesContext.getCurrentInstance().addPartialTarget(empTable);

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
    
  
    /**
     * Method to read xlsx file and upload to table.
     *
     * @throws IOException
     */
    public void readNProcessExcelx(InputStream xlsx) throws IOException {

        LOG.info("Inside Read xlsx Method");

        BindingContainer bindings = getBindingsCont();
        DCIteratorBinding iter = (DCIteratorBinding) bindings.get("sgsStatisticalDataTempVO1Iterator");


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
            LOG.info("Row------>"+tempRow.getPhysicalNumberOfCells());
            LOG.info("FirstRowNum------>"+sheet.getFirstRowNum());
            LOG.info("getLastRowNum------>"+sheet.getLastRowNum());
            LOG.info("RowNum------>"+tempRow.getRowNum());
            LOG.info("RowNumFirst------>"+tempRow.getFirstCellNum());
            LOG.info("RowNumLast------>"+tempRow.getLastCellNum());
            
            LOG.info("skipcnt------>"+skipcnt);
            LOG.info("skipRw------>"+skipRw);
            
            if (skipcnt > skipRw) { //skip first n row for labels.
                LOG.info("CreateInsert------>");
                //Create new row in table
                executeOperation("CreateInsert").execute();
                
                LOG.info("Get current row from iterator------>");
                //Get current row from iterator
                oracle.jbo.Row row = iter.getNavigatableRowIterator().getCurrentRow();
                int Index = 0;
                //Iterate over row's columns
                for (int column = 0; column < tempRow.getLastCellNum(); column++) {
                    LOG.info("Column------>"+column);

                    Cell MytempCell = tempRow.getCell(column);
                    if (MytempCell != null) {
                        LOG.info("MytempCell------>"+MytempCell);
                        Index = MytempCell.getColumnIndex();
                        LOG.info("Index------>"+Index);

                         if (Index == 0) {
                            LOG.info("NATUREOFEXPENSE------>"+MytempCell.getStringCellValue());
                            LOG.info("NATUREOFEXPENSEAVVVV------>"+row.getAttribute("NATUREOFEXPENSE"));
                            
                            
                            row.setAttribute("NATUREOFEXPENSE", MytempCell.getStringCellValue());

                        } else if (Index == 1) {
                            LOG.info("StatisticalDataCategory------>"+MytempCell.getStringCellValue());
                            LOG.info("StatisticalDataCategoryAVVVV------>"+row.getAttribute("StatisticalDataCategory"));
                            
                            
                            row.setAttribute("StatisticalDataCategory", MytempCell.getStringCellValue());

                        } else if (Index == 2) {
                            LOG.info("TransactionPeriod------>" + MytempCell.getCellType());
                            LOG.info("TransactionPeriod------>" + MytempCell.getDateCellValue());

                            // row.setAttribute("TransactionPeriod", MytempCell.getStringCellValue());
                            java.util.Date date = MytempCell.getDateCellValue();
                            if (null != date) {
                                LOG.info("DATE-TransactionPeriod    :" + date);
                                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

                                String date1 = dateFormat.format(date);
                                LOG.info("date1" + date1);
                                try {
                                    date = dateFormat.parse(date1);
                                } catch (ParseException e) {
                                    LOG.info("Exception : " + e);
                                }
                                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                                oracle.jbo.domain.Date jboDate = new oracle.jbo.domain.Date(sqlDate);
                                row.setAttribute("TRANSACTIONPERIOD", jboDate);
                            } else {
                                row.setAttribute("TRANSACTIONPERIOD", null);
                            }


                        } else if (Index == 3) {
                            
                            LOG.info("FROMBU------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("FROMBU", MytempCell.getStringCellValue());

                        } else if (Index == 4) {

                            LOG.info("FROMJOBCODE------>" + MytempCell.getStringCellValue());

                            if (MytempCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                String str = NumberToTextConverter.toText(MytempCell.getNumericCellValue());
                                LOG.info("FROMJOBCODE Value*****------>" + str);
                                row.setAttribute("FROMJOBCODE", str);
                            } else {

                                row.setAttribute("FROMJOBCODE", MytempCell.getStringCellValue());

                            }

                        } else if (Index == 5) {
                            
                            LOG.info("FROMOU------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("FROMOU", MytempCell.getStringCellValue());

                        } else if (Index == 6) {

                            LOG.info("FROMDEPTID------>" + MytempCell.getStringCellValue());

                            if (MytempCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                String str = NumberToTextConverter.toText(MytempCell.getNumericCellValue());
                                LOG.info("FROMDEPTID Value*****------>" + str);
                                row.setAttribute("FROMDEPTID", str);
                            } else {

                                row.setAttribute("FROMDEPTID", MytempCell.getStringCellValue());

                            }

                        } else if (Index == 7) {

                            LOG.info("ToBusinessUnit------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("ToBusinessUnit", MytempCell.getStringCellValue());

                        } else if (Index == 8) {
                            LOG.info("ToJobCode------>" + MytempCell.getStringCellValue());

                            if (MytempCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                String str = NumberToTextConverter.toText(MytempCell.getNumericCellValue());
                                LOG.info("ToJobCode Value*****------>" + str);
                                row.setAttribute("ToJobCode", str);
                            } else {

                                row.setAttribute("ToJobCode", MytempCell.getStringCellValue());

                            }

                        } else if (Index == 9) {
                            LOG.info("ToOperatingUnit------>" + MytempCell.getStringCellValue());

                            row.setAttribute("ToOperatingUnit", MytempCell.getStringCellValue());

                        } else if (Index == 10) {
                            LOG.info("ToDepartmentId------>"+MytempCell.getStringCellValue());
                            
                            if (MytempCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                String str = NumberToTextConverter.toText(MytempCell.getNumericCellValue());
                                LOG.info("ToDepartmentId Value*****------>" + str);
                                row.setAttribute("ToDepartmentId", str);
                            } else {

                                row.setAttribute("ToDepartmentId", MytempCell.getStringCellValue());

                            }

                        } else if (Index == 11) {
                            LOG.info("STATISTICALDATA------>"+MytempCell.getNumericCellValue());
                            
                            row.setAttribute("STATISTICALDATA", MytempCell.getNumericCellValue());

                        } else if (Index == 12) {
                            
                            LOG.info("STATGEOGRAPHY------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("STATGEOGRAPHY", MytempCell.getStringCellValue());

                        } else if (Index == 13) {
                            
                            LOG.info("INPUTPROVIDER------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("INPUTPROVIDER", MytempCell.getStringCellValue());

                        } else if (Index == 14) {
                            
                            LOG.info("ADDTEXPENSECAT------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("ADDTEXPENSECAT", MytempCell.getStringCellValue());

                        } else if (Index == 15) {
                            
                            LOG.info("GLACCOUNT------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("GLACCOUNT", MytempCell.getStringCellValue());

                        } else if (Index == 16) {
                            LOG.info("UnitOfMeasure------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("UnitOfMeasure", MytempCell.getStringCellValue());

                        } else if (Index == 17) {
                            LOG.info("CostGroup------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("CostGroup", MytempCell.getStringCellValue());

                        } else if (Index == 18) {
                            LOG.info("Currency------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("Currency", MytempCell.getStringCellValue());

                        } else if (Index == 19) {
                            LOG.info("EmployeeId------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("EmployeeId", MytempCell.getStringCellValue());

                        } else if (Index == 20) {
                            
                            LOG.info("EMPGRADE------>"+MytempCell.getNumericCellValue());
                            
                            row.setAttribute("EMPGRADE", MytempCell.getNumericCellValue());

                        } else if (Index == 21) {
                            LOG.info("TargetAmount------>"+MytempCell.getNumericCellValue());
                            
                            row.setAttribute("TargetAmount", MytempCell.getNumericCellValue());

                        } else if (Index == 22) {
                            LOG.info("RejectedReason------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("RejectedReason", MytempCell.getStringCellValue());

                        } else if (Index == 23) {
                            LOG.info("RejectionComments------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("RejectionComments", MytempCell.getStringCellValue());

                        } else if (Index == 24) {
                            LOG.info("ValidityFrom------>"+MytempCell.getDateCellValue());
                            
                            java.util.Date date = MytempCell.getDateCellValue();
                            if (null != date) {
                                LOG.info("dATE-ValidateFrom" + date);
                                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                                LOG.info("d");
                                String date1 = dateFormat.format(date);
                                LOG.info("date1" + date1);
                                try {
                                    date = dateFormat.parse(date1);
                                } catch (ParseException e) {
                                    LOG.info("Exception : " + e);
                                }
                                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                                oracle.jbo.domain.Date jboDate = new oracle.jbo.domain.Date(sqlDate);
                                row.setAttribute("ValidityFrom", jboDate);
                            } else {
                                row.setAttribute("ValidityFrom", null);
                            }
                        } else if (Index == 25) {
                            LOG.info("ValidityTill------>"+MytempCell.getDateCellValue());
                            
                            java.util.Date date = MytempCell.getDateCellValue();
                            if (null != date) {
                                LOG.info("dATE-ValidateFrom" + date);
                                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                                LOG.info("d");
                                String date1 = dateFormat.format(date);
                                LOG.info("date1" + date1);
                                try {
                                    date = dateFormat.parse(date1);
                                } catch (ParseException e) {
                                    LOG.info("Exception : " + e);
                                }
                                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                                oracle.jbo.domain.Date jboDate = new oracle.jbo.domain.Date(sqlDate);
                                row.setAttribute("ValidityTill", jboDate);
                            } else {
                                row.setAttribute("ValidityTill", null);
                            }

                         
                        } else if (Index == 26) {
                            LOG.info("SLOC------>"+MytempCell.getStringCellValue());
                            
                            row.setAttribute("SLOC", MytempCell.getStringCellValue());

                        } else {
                            LOG.info("-----> cell Is empty Please fill the cell with data"+tempRow.getRowNum()+"+"+tempRow.getCell(column));
                            LOG.info("-----> cell Is empty Please fill the cell with data at Index"+Index);
                            Index++;
                        }

                    }
                }
                
                
                
                

            }
            LOG.info("skipcntBefore----->"+skipcnt);
            
            skipcnt++;
            
            LOG.info("skipcntAfter----->"+skipcnt);
            
            
            
        }
        LOG.info("xlsx Commit");
        //Execute table viewObject
        executeOperation("Commit").execute();
        
    }
    
    
    
    public void okButtonDialogListner(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome() == DialogEvent.Outcome.ok) {

            executeOperation("ExecuteStatisticalData").execute();
            ADFUtils.saveNotifier();
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

    
        
        
        
        
        public void onStatDocsUpload(ValueChangeEvent valueChangeEvent) {
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
                        LOG.info("filePath1" + filePath1);
                        
                        String tokens = uploadedFile.getFilename();
                        String fileNames = uploadedFile.getFilename();
                        String contentType = uploadedFile.getContentType();
                        path = filePath1 + fileNames;
                        saveFile(path, fileName, bfi);

                        DCIteratorBinding docs = getDCIteratorBindings("SgsStatisticalDataVO1Iterator");
                        oracle.jbo.Row row = docs.getCurrentRow();
                        row.setAttribute("DOCATTM", fileName);
                        row.setAttribute("Attribute1", path);
                        row.setAttribute("Attribute2", contentType);

                        LOG.info("File path and file Name in downlaod" + path + fileName);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        }
    
    
    public void onStatDocsDownload(FacesContext facesContext, OutputStream outputStream) {
            DCBindingContainer bindingContainer =
                (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding imageIter = (DCIteratorBinding) bindingContainer.get("SgsStatisticalDataVO1Iterator");
            ViewObject vo = imageIter.getViewObject();
            oracle.jbo.Row currentRow = (oracle.jbo.Row) vo.getCurrentRow();

            String filePath = (String) currentRow.getAttribute("Attribute1");
            String fileName = (String) currentRow.getAttribute("DOCATTM");
            LOG.info("filePath :: " + filePath);
            LOG.info("fileName :: " + fileName);


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


    public void creditUploadActionEvent(ActionEvent actionEvent) {
        // Add event code here...
        executeOperation("ExecuteStatisticalData").execute();
        ADFUtils.saveNotifier();
    }
}
