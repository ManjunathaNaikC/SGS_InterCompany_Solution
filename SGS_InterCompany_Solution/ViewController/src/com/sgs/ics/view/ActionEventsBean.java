package com.sgs.ics.view;

import com.sgs.ics.ui.utils.ADFUtils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;

public class ActionEventsBean {
    protected String SAVE_DATA="Commit";
    
    public ActionEventsBean() {
    }
    
    

    public void onStatisticalDataSave(ActionEvent actionEvent) {
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
}
