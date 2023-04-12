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

import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;
import java.net.MalformedURLException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.ViewObject;

import org.apache.myfaces.trinidad.model.UploadedFile;


public class StandardRateSetupBean {
    
    protected String SAVE_DATA="Commit";
    private static final ADFLogger LOG = ADFLogger.createADFLogger(StandardRateSetupBean.class);
    
    public StandardRateSetupBean() {
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

    
    
    public String deleteStandardrateRule() {
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

    
    public void onStdRateDocsUpload(ValueChangeEvent valueChangeEvent) {
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

                       
                        LOG.info("filePath1" + filePath1);
                        
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

                        DCIteratorBinding docs = getDCIteratorBindings("SgsStdRateDocVO1Iterator");
                        oracle.jbo.Row row = docs.getCurrentRow();
                        // row.setAttribute("FileContent",sbyte);
                        //row.setAttribute("EffectiveFrom",new java.sql.Date(new java.util.Date().getTime()));
                        row.setAttribute("Attachment", fileName);
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
    
    
    public void onStdRateDocsDownload(FacesContext facesContext, OutputStream outputStream) {
            DCBindingContainer bindingContainer =
                (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding imageIter = (DCIteratorBinding) bindingContainer.get("SgsStdRateDocVO1Iterator");
            ViewObject vo = imageIter.getViewObject();
            oracle.jbo.Row currentRow = (oracle.jbo.Row) vo.getCurrentRow();

            String filePath = (String) currentRow.getAttribute("Attribute1");
            String fileName = (String) currentRow.getAttribute("Attachment");
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


    public void onDocumentsDelete(ActionEvent actionEvent) {
        executeOperation("DeleteDocs").execute();
        executeOperation(SAVE_DATA).execute();      
        ADFUtils.deleteNotifier();
    }
}
