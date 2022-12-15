package com.sgs.ics.view;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class ActionEventsBean {
    public ActionEventsBean() {
    }

    public void onStatisticalDataSave(ActionEvent actionEvent) {
        String commitBind = "Commit";
        executeBinding(commitBind);
        saveNotifier();
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

    public void saveNotifier() {
        FacesContext context = FacesContext.getCurrentInstance();
        String messageText = "Record saved Successfully.";
        FacesMessage fm = new FacesMessage(messageText);
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        context.addMessage(null, fm);
    }
    
    public void deleteNotifier() {
        FacesContext context = FacesContext.getCurrentInstance();
        String messageText = "Record Deleted Successfully.";
        FacesMessage fm = new FacesMessage(messageText);
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        context.addMessage(null, fm);
    }
    


    public void onDeleteStatisticalData(ActionEvent actionEvent) {
        executeBinding("DeleteStatisticalData");
        executeBinding("Commit");      
        deleteNotifier();
    }
}
