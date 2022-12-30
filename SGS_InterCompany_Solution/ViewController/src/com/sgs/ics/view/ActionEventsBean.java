package com.sgs.ics.view;

import com.sgs.ics.model.bc.am.SGSAppModuleImpl;
import com.sgs.ics.ui.utils.ADFUtils;

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

import oracle.adf.view.rich.component.rich.input.RichSelectManyChoice;
import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.server.ViewObjectImpl;
import oracle.jbo.uicli.binding.JUCtrlListBinding;

import oracle.jbo.server.ViewDefImpl;
import oracle.jbo.server.ViewObjectImpl;

public class ActionEventsBean {
    protected String SAVE_DATA="Commit";
    
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
            "select LOOKUP_CODE from SGS_LOOKUP_TABLE where LOOKUP_TYPE='NATURE_OF_EXPENSE' and LOOKUP_ID IN (" +
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
}
