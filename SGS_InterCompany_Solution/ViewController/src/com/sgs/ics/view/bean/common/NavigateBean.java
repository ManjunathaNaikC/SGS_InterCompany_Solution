package com.sgs.ics.view.bean.common;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import oracle.adf.controller.TaskFlowId;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailItem;
import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.event.DisclosureEvent;

public class NavigateBean implements Serializable {
//    private RichRegion mainTFBinding;
//    private RichShowDetailItem setUpDetailItemBinding;

    private String taskFlowId = "/taskflows/commom/sgs-costIdentificationRule-flow.xml#sgs-costIdentificationRule-flow";

    public NavigateBean() {
        super();
    }

//    public void setMainTFBinding(RichRegion mainTFBinding) {
//        this.mainTFBinding = mainTFBinding;
//    }
//
//    public RichRegion getMainTFBinding() {
//        return mainTFBinding;
//    }
//
//    public void loadCostAllocaionSetup(ActionEvent actionEvent) {
//        System.out.println("CIR flow");
//        ADFContext.getCurrent().getPageFlowScope().put("navigate", "CIR");  
//        getMainTFBinding().setRendered(true);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(getMainTFBinding());
//    }
//   
//    public void loadISDSetUp(ActionEvent actionEvent) {
//        System.out.println("ISD flow");
//        ADFContext.getCurrent().getPageFlowScope().put("navigate", "ISD");  
//        getMainTFBinding().setRendered(true);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(getMainTFBinding());
//    }
//    
//    private void loadRegion(){
//        getMainTFBinding().setRendered(true);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(getMainTFBinding());
//    }
//
//    public void setUpDetailItem(DisclosureEvent disclosureEvent) {
//        System.out.println("DisclosureEvent flow");
//        if( getSetUpDetailItemBinding().isDisabled())
//        getSetUpDetailItemBinding().setDisabled(false);
//    }
//
//    public void setSetUpDetailItemBinding(RichShowDetailItem setUpDetailItemBinding) {
//        this.setUpDetailItemBinding = setUpDetailItemBinding;
//    }
//
//    public RichShowDetailItem getSetUpDetailItemBinding() {
//        return setUpDetailItemBinding;
//    }
//
//    public void loadTPASetupPage(ActionEvent actionEvent) {
//        System.out.println("TPA flow");
//        ADFContext.getCurrent().getPageFlowScope().put("navigate", "TPA");  
//        getMainTFBinding().setRendered(true);
//        AdfFacesContext.getCurrentInstance().addPartialTarget(getMainTFBinding());
//    }
    public TaskFlowId getDynamicTaskFlowId() {
        return TaskFlowId.parse(taskFlowId);
    }

    public void setDynamicTaskFlowId(String taskFlowId) {
        this.taskFlowId = taskFlowId;
    }

    public String sgscostIdentificationRuleflow() {
        setDynamicTaskFlowId("/taskflows/commom/sgs-costIdentificationRule-flow.xml#sgs-costIdentificationRule-flow");
        return null;
    }

    public String tPASetupBTF() {
        setDynamicTaskFlowId("/taskflows/commom/TPASetup-flow.xml#TPASetupBTF");
        return null;
    }
}
