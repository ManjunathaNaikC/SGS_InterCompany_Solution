
package com.sgs.ics.view.bean.common;

import com.sgs.ics.model.bc.am.SGSAppModuleImpl;
import com.sgs.ics.ui.utils.ADFUtils;

import java.io.Serializable;

import java.util.ArrayList;

import javax.faces.event.ActionEvent;

import oracle.adf.controller.TaskFlowId;
import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailItem;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;

import oracle.jbo.server.ViewObjectImpl;

import org.apache.myfaces.trinidad.event.DisclosureEvent;

public class NavigateBean implements Serializable {
    //    private RichRegion mainTFBinding;
    //    private RichShowDetailItem setUpDetailItemBinding;

    private String taskFlowId = "/taskflows/commom/sgs-costIdentificationRule-flow.xml#sgs-costIdentificationRule-flow";

    public NavigateBean() {

        super();
        taskFlowId = "/taskflows/commom/welcome-empty-flow.xml#welcome-empty-flow";
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
        ArrayList<String> pageList = (ArrayList<String>) ADFContext.getCurrent()
                                                                   .getSessionScope()
                                                                   .get("pageList");
        System.out.println("Page List ::" + pageList.toString());
        if (null != pageList && !(pageList.isEmpty()) && (pageList.contains("ALL_PAGE"))) {
            //No Filter For Admin

        } else {
            String buList = (String) ADFContext.getCurrent()
                                               .getSessionScope()
                                               .get("buList");
            String natureOfExpenseList = (String) ADFContext.getCurrent()
                                                            .getSessionScope()
                                                            .get("natureOfExpenseList");
            System.out.println("buList on Page " + buList);
            System.out.println("natureOfExpenseList on Page " + natureOfExpenseList);
            // String[] resBuList = buList.split("[,]", 0);
            String[] resnatureOfExpenseList = natureOfExpenseList.split("[,]", 0);
            StringBuffer buString = new StringBuffer();
            for (int i = 0; i < resnatureOfExpenseList.length; i++) {
                System.out.println(resnatureOfExpenseList[i]);
                buString.append("'" + resnatureOfExpenseList[i] + "'");
                System.out.println("Value i ::" + i);
                System.out.println("length  ::" + resnatureOfExpenseList.length);
                if (i + 1 == resnatureOfExpenseList.length) {
                    //Don't append comma at the end of the String

                } else {
                    buString.append(",");
                }
            }
            String inputProviderList = (String) ADFContext.getCurrent()
                                               .getSessionScope()
                                               .get("inputProvider");
            System.out.println("inputProvider on Page " + inputProviderList);
            String[] resIPList = inputProviderList.split("[,]", 0);
            StringBuffer iPString = new StringBuffer();
            for (int i = 0; i < resIPList.length; i++) {
                System.out.println(resIPList[i]);
                iPString.append("'" + resIPList[i] + "'");
                System.out.println("Value i ::" + i);
                System.out.println("length  ::" + resIPList.length);
                if (i + 1 == resIPList.length) {
                    //Don't append comma at the end of the String

                } else {
                    iPString.append(",");
                }
            }
            
            System.out.println(buString.toString());
            ViewObjectImpl viewImpl = null;
            viewImpl = (ViewObjectImpl) getDCIteratorBindings("SgsCostIdentificationRuleVOIterator").getViewObject();
            viewImpl.setFullSqlMode(ViewObjectImpl.FULLSQL_MODE_AUGMENTATION);
            viewImpl.setWhereClause(" NATURE_OF_EXPENSE IN ( " + buString.toString() + ")"+"AND INPUT_PROVIDER IN( " + iPString.toString() + ")");
            System.out.println("viewImpl getQuery :: " + viewImpl.getQuery());
            viewImpl.executeQuery();
        }

        return null;
    }

    public String tPASetupBTF() {
        setDynamicTaskFlowId("/taskflows/commom/TPASetup-flow.xml#TPASetupBTF");
        return null;
    }


    public String welcomeemptyflow() {
        setDynamicTaskFlowId("/taskflows/commom/welcome-empty-flow.xml#welcome-empty-flow");
        return null;
    }

    public String sgsbusinessUnitMasterflow() {
        setDynamicTaskFlowId("/taskflows/commom/sgs-businessUnitMaster-flow.xml#sgs-businessUnitMaster-flow");
        ArrayList<String> pageList = (ArrayList<String>) ADFContext.getCurrent()
                                                                   .getSessionScope()
                                                                   .get("pageList");
        System.out.println("Page List ::" + pageList.toString());
        if (null != pageList && !(pageList.isEmpty()) && (pageList.contains("ALL_PAGE"))) {
            //No Filter For Admin


        } else {
            String buList = (String) ADFContext.getCurrent()
                                               .getSessionScope()
                                               .get("buList");
            String natureOfExpenseList = (String) ADFContext.getCurrent()
                                                            .getSessionScope()
                                                            .get("natureOfExpenseList");
            System.out.println("buList on Page " + buList);
            System.out.println("natureOfExpenseList on Page " + natureOfExpenseList);
            String[] resBuList = buList.split("[,]", 0);
            StringBuffer buString = new StringBuffer();
            for (int i = 0; i < resBuList.length; i++) {
                System.out.println(resBuList[i]);
                buString.append("'" + resBuList[i] + "'");
                System.out.println("Value i ::" + i);
                System.out.println("length  ::" + resBuList.length);
                if (i + 1 == resBuList.length) {
                    //Don't append comma at the end of the String

                } else {
                    buString.append(",");
                }
            }
            System.out.println(buString.toString());
            ViewObjectImpl viewImpl = null;
            viewImpl = (ViewObjectImpl) getDCIteratorBindings("SgsBusinessUnitMasterVO1Iterator").getViewObject();
            viewImpl.setFullSqlMode(ViewObjectImpl.FULLSQL_MODE_AUGMENTATION);
            viewImpl.setWhereClause(" BUSSINESS_UNIT_NAME IN ( " + buString.toString() + ")");
            System.out.println("viewImpl getQuery :: " + viewImpl.getQuery());
            viewImpl.executeQuery();
        }
        return null;
    }


    public String sgsgstInquiryflow() {
        System.out.println("******************************inside gst navigation");
        setDynamicTaskFlowId("/taskflows/commom/sgs-gstInquiry-flow.xml#sgs-gstInquiry-flow");
        return null;
    }

    public String sgsvatInquiryflow() {
        setDynamicTaskFlowId("/taskflows/commom/sgs-vatInquiry-flow.xml#sgs-vatInquiry-flow");
        return null;
    }


    public String sgsstatisticalDateflow() {
        setDynamicTaskFlowId("/taskflows/TransactionalData/sgs-statisticalDate-flow.xml#sgs-statisticalDate-flow");
        ArrayList<String> pageList = (ArrayList<String>) ADFContext.getCurrent()
                                                                   .getSessionScope()
                                                                   .get("pageList");
        System.out.println("Page List ::" + pageList.toString());
        if (null != pageList && !(pageList.isEmpty()) && (pageList.contains("ALL_PAGE"))) {
            //No Filter For Admin


        } else {
            String inputProviderList = (String) ADFContext.getCurrent()
                                               .getSessionScope()
                                               .get("inputProvider");
            System.out.println("inputProvider on Page " + inputProviderList);
            String[] resIPList = inputProviderList.split("[,]", 0);
            StringBuffer iPString = new StringBuffer();
            for (int i = 0; i < resIPList.length; i++) {
                System.out.println(resIPList[i]);
                iPString.append("'" + resIPList[i] + "'");
                System.out.println("Value i ::" + i);
                System.out.println("length  ::" + resIPList.length);
                if (i + 1 == resIPList.length) {
                    //Don't append comma at the end of the String

                } else {
                    iPString.append(",");
                }
            }
            System.out.println(iPString.toString());
            ViewObjectImpl viewImpl = null;
            viewImpl = (ViewObjectImpl) getDCIteratorBindings("SgsStatisticalDataVO1Iterator").getViewObject();
            System.out.println("viewImpl   ********"+viewImpl);
            viewImpl.setFullSqlMode(ViewObjectImpl.FULLSQL_MODE_AUGMENTATION);
            viewImpl.setWhereClause(" INPUT_PROVIDER IN ( " + iPString.toString() + ")");
            System.out.println("viewImpl getQuery :: " + viewImpl.getQuery());
            viewImpl.executeQuery();
        }
        
        return null;
    }

    public String sgsstandardSetupRuleflow() {
        setDynamicTaskFlowId("/taskflows/TransactionalData/sgs-standardSetupRule-flow.xml#sgs-standardSetupRule-flow");
        return null;
    }

    public String sgstdswhtInquiryflow() {
        setDynamicTaskFlowId("/taskflows/commom/sgs-tdswhtInquiry-flow.xml#sgs-tdswhtInquiry-flow");
        return null;
    }

    public String sgsMarkUpRateMasterflow() {
        setDynamicTaskFlowId("/taskflows/commom/sgs-MarkUpRateMaster-flow.xml#sgs-MarkUpRateMaster-flow");
        return null;
    }

    public String sgsinvoiceDashboardflow() {
        setDynamicTaskFlowId("/taskflows/TransactionalData/sgs-invoiceDashboard-flow.xml#sgs-invoiceDashboard-flow");
        ArrayList<String> pageList = (ArrayList<String>) ADFContext.getCurrent()
                                                                   .getSessionScope()
                                                                   .get("pageList");
        System.out.println("Page List ::" + pageList.toString());
        if (null != pageList && !(pageList.isEmpty()) && (pageList.contains("ALL_PAGE"))) {
            //No Filter For Admin


        } else {
                       String inputProviderList = (String) ADFContext.getCurrent()
                                                           .getSessionScope()
                                                           .get("inputProvider");
                        System.out.println("inputProvider on Page " + inputProviderList);
                        String[] resIPList = inputProviderList.split("[,]", 0);
                        StringBuffer iPString = new StringBuffer();
                        for (int i = 0; i < resIPList.length; i++) {
                            System.out.println(resIPList[i]);
                            iPString.append("'" + resIPList[i] + "'");
                            System.out.println("Value i ::" + i);
                            System.out.println("length  ::" + resIPList.length);
                            if (i + 1 == resIPList.length) {
                                //Don't append comma at the end of the String

                            } else {
                                iPString.append(",");
                            }
                        }
            
            String buList = (String) ADFContext.getCurrent()
                                               .getSessionScope()
                                               .get("buList");
            System.out.println("buList on Page " + buList);
            String[] resBuList = buList.split("[,]", 0);
            StringBuffer buString = new StringBuffer();
            for (int i = 0; i < resBuList.length; i++) {
                System.out.println(resBuList[i]);
                buString.append("'" + resBuList[i] + "'");
                System.out.println("Value i ::" + i);
                System.out.println("length  ::" + resBuList.length);
                if (i + 1 == resBuList.length) {
                    //Don't append comma at the end of the String

                } else {
                    buString.append(",");
                }
            }
            System.out.println(buString.toString());
            ViewObjectImpl viewImpl = null;
            viewImpl = (ViewObjectImpl) getDCIteratorBindings("SgsIcInvoiceHeaderVO1Iterator").getViewObject();
            System.out.println("viewImpl   ********"+viewImpl);
            viewImpl.setFullSqlMode(ViewObjectImpl.FULLSQL_MODE_AUGMENTATION);
            System.out.println("TEsting ****************** Invoice fileter");
            viewImpl.setWhereClause("SOURCE_BU IN ( " + buString.toString() + ")"+"AND INPUT_PROVIDER IN( " + iPString.toString() + ")");
            System.out.println("viewImpl getQuery :: " + viewImpl.getQuery());
            viewImpl.executeQuery();
        }
        return null;
    }

    public String sgstransBCostAllocationMainflow() {
        setDynamicTaskFlowId("/taskflows/TransactionalData/sgs-transBCostAllocationMain-flow.xml#sgs-transBCostAllocationMain-flow");
        ArrayList<String> pageList = (ArrayList<String>) ADFContext.getCurrent()
                                                                   .getSessionScope()
                                                                   .get("pageList");
        System.out.println("Page List ::" + pageList.toString());
        if (null != pageList && !(pageList.isEmpty()) && (pageList.contains("ALL_PAGE"))) {
            //No Filter For Admin


        } else {
            String inputProviderList = (String) ADFContext.getCurrent()
                                                .getSessionScope()
                                                .get("inputProvider");
             System.out.println("inputProvider on Page " + inputProviderList);
             String[] resIPList = inputProviderList.split("[,]", 0);
             StringBuffer iPString = new StringBuffer();
             for (int i = 0; i < resIPList.length; i++) {
                 System.out.println(resIPList[i]);
                 iPString.append("'" + resIPList[i] + "'");
                 System.out.println("Value i ::" + i);
                 System.out.println("length  ::" + resIPList.length);
                 if (i + 1 == resIPList.length) {
                     //Don't append comma at the end of the String

                 } else {
                     iPString.append(",");
                 }
             }
            
            String buList = (String) ADFContext.getCurrent()
                                               .getSessionScope()
                                               .get("buList");
            System.out.println("buList on Page " + buList);
            String[] resBuList = buList.split("[,]", 0);
            StringBuffer buString = new StringBuffer();
            for (int i = 0; i < resBuList.length; i++) {
                System.out.println(resBuList[i]);
                buString.append("'" + resBuList[i] + "'");
                System.out.println("Value i ::" + i);
                System.out.println("length  ::" + resBuList.length);
                if (i + 1 == resBuList.length) {
                    //Don't append comma at the end of the String

                } else {
                    buString.append(",");
                }
            }
            System.out.println(buString.toString());
            ViewObjectImpl viewImpl = null;
            viewImpl = (ViewObjectImpl) getDCIteratorBindings("SgsTransBCostAllocationVO1Iterator").getViewObject();
            System.out.println("viewImpl   ********"+viewImpl);
            viewImpl.setFullSqlMode(ViewObjectImpl.FULLSQL_MODE_AUGMENTATION);
            viewImpl.setWhereClause(" BU_ID IN ( " + buString.toString() + ")"+"AND INPUT_PROVIDER IN( " + iPString.toString() + ")");
            System.out.println("viewImpl getQuery :: " + viewImpl.getQuery());
            viewImpl.executeQuery();
        }
        return null;
    }


    public String sgsfixedAssetsTxnflow() {
        setDynamicTaskFlowId("/taskflows/TransactionalData/sgs-fixedAssetsTxn-flow.xml#sgs-fixedAssetsTxn-flow");
        return null;
    }

    public String sgstpaInquiryflow() {
        setDynamicTaskFlowId("/taskflows/commom/sgs-tpaInquiry.xml#sgs-tpaInquiry");
        return null;
    }

    public String settlementdashboardflow() {
        setDynamicTaskFlowId("/taskflows/TransactionalData/settlementdashboard-flow.xml#settlementdashboard-flow");
        return null;
    }

    public String createsettlement_flow() {
        setDynamicTaskFlowId("/taskflows/TransactionalData/createsettlement_flow.xml#createsettlement_flow");
        ADFUtils.executeBinding("CreateSettlement");
        SGSAppModuleImpl am = new SGSAppModuleImpl();
        String value = "PS_" + (am.getDBSequence1("SEQ_SGS_CREATE_SETTLEMENT"));
        DCIteratorBinding dcIteratorbinding = getDCIteratorBindings("CreateStlmtRVO1Iterator");
        Row row = dcIteratorbinding.getCurrentRow();
        row.setAttribute("PAYMENTID", value);
        return null;
    }

    private DCIteratorBinding getDCIteratorBindings(String iterName) {
        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        return bindings.findIteratorBinding(iterName);

    }


    public String sgsAllocationRunflow() {
        setDynamicTaskFlowId("/taskflows/Process/sgs-Allocation-Run-flow.xml#sgs-Allocation-Run-flow");
        return null;
    }

    public String sgsnettingflow() {
        setDynamicTaskFlowId("/taskflows/TransactionalData/sgs-netting-flow.xml#sgs-netting-flow");
        return null;
    }

    public String sgs_creditmemo_flow() {
        setDynamicTaskFlowId("/taskflows/TransactionalData/sgs_creditmemo_flow.xml#sgs_creditmemo_flow");
        return null;
    }

    public String sgsreportsflow() {
        setDynamicTaskFlowId("/taskflows/Reports/sgs-reports-flow.xml#sgs-reports-flow");
        return null;
    }
    public String sgstransactionreportsflow() {
        setDynamicTaskFlowId("/taskflows/Reports/sgs-tansactional-reports-flow-definition.xml#sgs-tansactional-reports-flow-definition");
        return null;
    }
}
