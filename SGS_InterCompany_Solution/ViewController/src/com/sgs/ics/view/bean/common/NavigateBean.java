package com.sgs.ics.view.bean.common;

import javax.faces.event.ActionEvent;

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailItem;
import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.event.DisclosureEvent;

public class NavigateBean {
    private RichRegion mainTFBinding;
    private RichShowDetailItem setUpDetailItemBinding;

    public NavigateBean() {
        super();
    }

    public void setMainTFBinding(RichRegion mainTFBinding) {
        this.mainTFBinding = mainTFBinding;
    }

    public RichRegion getMainTFBinding() {
        return mainTFBinding;
    }

    public void loadCostAllocaionSetup(ActionEvent actionEvent) {
        System.out.println("CIR flow");
        ADFContext.getCurrent().getPageFlowScope().put("navigate", "CIR");  
        getMainTFBinding().setRendered(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(getMainTFBinding());
    }
   
    public void loadISDSetUp(ActionEvent actionEvent) {
        System.out.println("ISD flow");
        ADFContext.getCurrent().getPageFlowScope().put("navigate", "ISD");  
        getMainTFBinding().setRendered(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(getMainTFBinding());
    }
    
    private void loadRegion(){
        getMainTFBinding().setRendered(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(getMainTFBinding());
    }

    public void setUpDetailItem(DisclosureEvent disclosureEvent) {
        System.out.println("DisclosureEvent flow");
        if( getSetUpDetailItemBinding().isDisabled())
        getSetUpDetailItemBinding().setDisabled(false);
    }

    public void setSetUpDetailItemBinding(RichShowDetailItem setUpDetailItemBinding) {
        this.setUpDetailItemBinding = setUpDetailItemBinding;
    }

    public RichShowDetailItem getSetUpDetailItemBinding() {
        return setUpDetailItemBinding;
    }
}
