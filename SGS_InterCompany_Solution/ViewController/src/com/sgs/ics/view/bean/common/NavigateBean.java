
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
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.fragment.RichRegion;
import oracle.adf.view.rich.component.rich.layout.RichShowDetailItem;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;

import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewCriteria;
import oracle.jbo.server.ViewObjectImpl;

import org.apache.myfaces.trinidad.event.DisclosureEvent;

public class NavigateBean implements Serializable {
    private static final ADFLogger LOG = ADFLogger.createADFLogger(NavigateBean.class);
    private String taskFlowId = "/taskflows/commom/sgs-costIdentificationRule-flow.xml#sgs-costIdentificationRule-flow";

    public NavigateBean() {

        super();
        taskFlowId = "/taskflows/commom/welcome-empty-flow.xml#welcome-empty-flow";
    }

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
        LOG.info("Page List ::" + pageList.toString());
        if (null != pageList && !(pageList.isEmpty()) && (pageList.contains("ALL_PAGE"))) {
            //No Filter For Admin

        } else {
            String buList = (String) ADFContext.getCurrent()
                                               .getSessionScope()
                                               .get("buList");
            String natureOfExpenseList = (String) ADFContext.getCurrent()
                                                            .getSessionScope()
                                                            .get("natureOfExpenseList");
            LOG.info("buList on Page " + buList);
            LOG.info("natureOfExpenseList on Page " + natureOfExpenseList);
            // String[] resBuList = buList.split("[,]", 0);
            String[] resnatureOfExpenseList = natureOfExpenseList.split("[,]", 0);
            StringBuffer buString = new StringBuffer();
            for (int i = 0; i < resnatureOfExpenseList.length; i++) {
                LOG.info(resnatureOfExpenseList[i]);
                buString.append("'" + resnatureOfExpenseList[i] + "'");
                LOG.info("Value i ::" + i);
                LOG.info("length  ::" + resnatureOfExpenseList.length);
                if (i + 1 == resnatureOfExpenseList.length) {
                    //Don't append comma at the end of the String

                } else {
                    buString.append(",");
                }
            }
            String inputProviderList = (String) ADFContext.getCurrent()
                                               .getSessionScope()
                                               .get("inputProvider");
            LOG.info("inputProvider on Page " + inputProviderList);
            String[] resIPList = inputProviderList.split("[,]", 0);
            StringBuffer iPString = new StringBuffer();
            for (int i = 0; i < resIPList.length; i++) {
                LOG.info(resIPList[i]);
                iPString.append("'" + resIPList[i] + "'");
                LOG.info("Value i ::" + i);
                LOG.info("length  ::" + resIPList.length);
                if (i + 1 == resIPList.length) {
                    //Don't append comma at the end of the String

                } else {
                    iPString.append(",");
                }
            }
            
            LOG.info(buString.toString());
            ViewObjectImpl viewImpl = null;
            viewImpl = (ViewObjectImpl) getDCIteratorBindings("SgsCostIdentificationRuleVOIterator").getViewObject();
            viewImpl.setFullSqlMode(ViewObjectImpl.FULLSQL_MODE_AUGMENTATION);
            viewImpl.setWhereClause(" NATURE_OF_EXPENSE IN ( " + buString.toString() + ")"+"AND INPUT_PROVIDER IN( " + iPString.toString() + ")");
            LOG.info("viewImpl getQuery :: " + viewImpl.getQuery());
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
        LOG.info("Page List ::" + pageList.toString());
        if (null != pageList && !(pageList.isEmpty()) && (pageList.contains("ALL_PAGE"))) {
            //No Filter For Admin


        } else {
            String buList = (String) ADFContext.getCurrent()
                                               .getSessionScope()
                                               .get("buList");
            String natureOfExpenseList = (String) ADFContext.getCurrent()
                                                            .getSessionScope()
                                                            .get("natureOfExpenseList");
            LOG.info("buList on Page " + buList);
            LOG.info("natureOfExpenseList on Page " + natureOfExpenseList);
            String[] resBuList = buList.split("[,]", 0);
            StringBuffer buString = new StringBuffer();
            for (int i = 0; i < resBuList.length; i++) {
                LOG.info(resBuList[i]);
                buString.append("'" + resBuList[i] + "'");
                LOG.info("Value i ::" + i);
                LOG.info("length  ::" + resBuList.length);
                if (i + 1 == resBuList.length) {
                    //Don't append comma at the end of the String

                } else {
                    buString.append(",");
                }
            }
            LOG.info(buString.toString());
            ViewObjectImpl viewImpl = null;
            viewImpl = (ViewObjectImpl) getDCIteratorBindings("SgsBusinessUnitMasterVO1Iterator").getViewObject();
            viewImpl.setFullSqlMode(ViewObjectImpl.FULLSQL_MODE_AUGMENTATION);
            viewImpl.setWhereClause(" BUSSINESS_UNIT_NAME IN ( " + buString.toString() + ")");
            LOG.info("viewImpl getQuery :: " + viewImpl.getQuery());
            viewImpl.executeQuery();
        }
        return null;
    }


    public String sgsgstInquiryflow() {
        LOG.info("******************************inside gst navigation");
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
        LOG.info("Page List ::" + pageList.toString());
        if (null != pageList && !(pageList.isEmpty()) && (pageList.contains("ALL_PAGE"))) {
            //No Filter For Admin


        } else {
            String inputProviderList = (String) ADFContext.getCurrent()
                                               .getSessionScope()
                                               .get("inputProvider");
            LOG.info("inputProvider on Page " + inputProviderList);
            String[] resIPList = inputProviderList.split("[,]", 0);
            StringBuffer iPString = new StringBuffer();
            for (int i = 0; i < resIPList.length; i++) {
                LOG.info(resIPList[i]);
                iPString.append("'" + resIPList[i] + "'");
                LOG.info("Value i ::" + i);
                LOG.info("length  ::" + resIPList.length);
                if (i + 1 == resIPList.length) {
                    //Don't append comma at the end of the String

                } else {
                    iPString.append(",");
                }
            }
            LOG.info(iPString.toString());
            ViewObjectImpl viewImpl = null;
            viewImpl = (ViewObjectImpl) getDCIteratorBindings("SgsStatisticalDataVO1Iterator").getViewObject();
            LOG.info("viewImpl   ********"+viewImpl);
            viewImpl.setFullSqlMode(ViewObjectImpl.FULLSQL_MODE_AUGMENTATION);
            viewImpl.setWhereClause(" INPUT_PROVIDER IN ( " + iPString.toString() + ")");
            LOG.info("viewImpl getQuery :: " + viewImpl.getQuery());
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
        LOG.info("Page List ::" + pageList.toString());
        if (null != pageList && !(pageList.isEmpty()) && (pageList.contains("ALL_PAGE"))) {
            //No Filter For Admin


        } else {
                       String inputProviderList = (String) ADFContext.getCurrent()
                                                           .getSessionScope()
                                                           .get("inputProvider");
                        LOG.info("inputProvider on Page " + inputProviderList);
                        String[] resIPList = inputProviderList.split("[,]", 0);
                        StringBuffer iPString = new StringBuffer();
                        for (int i = 0; i < resIPList.length; i++) {
                            LOG.info(resIPList[i]);
                            iPString.append("'" + resIPList[i] + "'");
                            LOG.info("Value i ::" + i);
                            LOG.info("length  ::" + resIPList.length);
                            if (i + 1 == resIPList.length) {
                                //Don't append comma at the end of the String

                            } else {
                                iPString.append(",");
                            }
                        }
            
            String buList = (String) ADFContext.getCurrent()
                                               .getSessionScope()
                                               .get("buList");
            LOG.info("buList on Page " + buList);
            String[] resBuList = buList.split("[,]", 0);
            StringBuffer buString = new StringBuffer();
            for (int i = 0; i < resBuList.length; i++) {
                LOG.info(resBuList[i]);
                buString.append("'" + resBuList[i] + "'");
                LOG.info("Value i ::" + i);
                LOG.info("length  ::" + resBuList.length);
                if (i + 1 == resBuList.length) {
                    //Don't append comma at the end of the String

                } else {
                    buString.append(",");
                }
            }
            LOG.info(buString.toString());
            ViewObjectImpl viewImpl = null;
            viewImpl = (ViewObjectImpl) getDCIteratorBindings("SgsIcInvoiceHeaderVO1Iterator").getViewObject();
            LOG.info("viewImpl   ********"+viewImpl);
            viewImpl.setFullSqlMode(ViewObjectImpl.FULLSQL_MODE_AUGMENTATION);
            LOG.info("TEsting ****************** Invoice fileter");
            viewImpl.setWhereClause("SOURCE_BU IN ( " + buString.toString() + ")"+"AND INPUT_PROVIDER IN( " + iPString.toString() + ")");
            LOG.info("viewImpl getQuery :: " + viewImpl.getQuery());
            viewImpl.executeQuery();
        }
        return null;
    }

    public String sgstransBCostAllocationMainflow() {
        setDynamicTaskFlowId("/taskflows/TransactionalData/sgs-transBCostAllocationMain-flow.xml#sgs-transBCostAllocationMain-flow");
        ArrayList<String> pageList = (ArrayList<String>) ADFContext.getCurrent()
                                                                   .getSessionScope()
                                                                   .get("pageList");
        LOG.info("Page List ::" + pageList.toString());
        if (null != pageList && !(pageList.isEmpty()) && (pageList.contains("ALL_PAGE"))) {
            //No Filter For Admin


        } else {
            String inputProviderList = (String) ADFContext.getCurrent()
                                                .getSessionScope()
                                                .get("inputProvider");
             LOG.info("inputProvider on Page " + inputProviderList);
             String[] resIPList = inputProviderList.split("[,]", 0);
             StringBuffer iPString = new StringBuffer();
             for (int i = 0; i < resIPList.length; i++) {
                 LOG.info(resIPList[i]);
                 iPString.append("'" + resIPList[i] + "'");
                 LOG.info("Value i ::" + i);
                 LOG.info("length  ::" + resIPList.length);
                 if (i + 1 == resIPList.length) {
                     //Don't append comma at the end of the String

                 } else {
                     iPString.append(",");
                 }
             }
            
            String buList = (String) ADFContext.getCurrent()
                                               .getSessionScope()
                                               .get("buList");
            LOG.info("buList on Page " + buList);
            String[] resBuList = buList.split("[,]", 0);
            StringBuffer buString = new StringBuffer();
            for (int i = 0; i < resBuList.length; i++) {
                LOG.info(resBuList[i]);
                buString.append("'" + resBuList[i] + "'");
                LOG.info("Value i ::" + i);
                LOG.info("length  ::" + resBuList.length);
                if (i + 1 == resBuList.length) {
                    //Don't append comma at the end of the String

                } else {
                    buString.append(",");
                }
            }
            LOG.info(buString.toString());
            ViewObjectImpl viewImpl = null;
            viewImpl = (ViewObjectImpl) getDCIteratorBindings("SgsTransBCostAllocationVO1Iterator").getViewObject();
            LOG.info("viewImpl   ********"+viewImpl);
            viewImpl.setFullSqlMode(ViewObjectImpl.FULLSQL_MODE_AUGMENTATION);
            viewImpl.setWhereClause(" BU_ID IN ( " + buString.toString() + ")"+"AND INPUT_PROVIDER IN( " + iPString.toString() + ")");
            LOG.info("viewImpl getQuery :: " + viewImpl.getQuery());
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
        
        //SgsStlmtVoucherVO1Iterator
//        ViewObjectImpl viewImpl = null;
//        viewImpl = (ViewObjectImpl) getDCIteratorBindings("SgsStlmtVoucherVO1Iterator").getViewObject();
//        viewImpl.setFullSqlMode(ViewObjectImpl.FULLSQL_MODE_AUGMENTATION);
//        viewImpl.setWhereClause(" VoucherId = null");
//        LOG.info("viewImpl getQuery :: " + viewImpl.getQuery());
//        viewImpl.executeQuery();
//        ADFUtils.executeBinding("CreateSettlement");
//        SGSAppModuleImpl am = new SGSAppModuleImpl();
//        String value = "PS_" + (am.getDBSequence1("SEQ_SGS_CREATE_SETTLEMENT"));
//        DCIteratorBinding dcIteratorbinding = getDCIteratorBindings("CreateStlmtRVO1Iterator");
//        Row row = dcIteratorbinding.getCurrentRow();
//        row.setAttribute("PAYMENTID", value);
        
        
        DCIteratorBinding iteratorBinding = getDCIteratorBindings("SgsStlmtVoucherVO1Iterator");
        //RowSetIterator rowSetIterator = iteratorBinding.getRowSetIterator();
        ViewObjectImpl voucherView = (ViewObjectImpl) iteratorBinding.getViewObject();
        ViewCriteria criteria = voucherView.getViewCriteria("SgsCreateStlmtVoucherVOCriteria");
        voucherView.applyViewCriteria(criteria);
        voucherView.setNamedWhereClauseParam("bCusGeo", "No_GEO");
        voucherView.setNamedWhereClauseParam("bSupGeo", "No_GEO");
        voucherView.setNamedWhereClauseParam("bCollectorBU", "No_BU");
        voucherView.setNamedWhereClauseParam("bPayerBU", "No_BU");
        voucherView.setNamedWhereClauseParam("bSltmtStatus", "Settled");
        voucherView.executeQuery();
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

    public String sgs_netting_new_flow() {
        setDynamicTaskFlowId("/taskflows/TransactionalData/sgs_netting_new_flow.xml#sgs_netting_new_flow");
        return null;
    }
}
