package com.sgs.ics.model.bc.am;


import com.sgs.ics.model.bc.am.common.SGSAppModule;
import com.sgs.ics.model.bc.view.SgsStdRateLineTblVOImpl;
import com.sgs.ics.model.bc.view.SgsTpaMasterVOImpl;

import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Properties;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.ViewLinkImpl;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Nov 22 09:18:46 IST 2022
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class SGSAppModuleImpl extends ApplicationModuleImpl implements SGSAppModule {
    /**
     * This is the default constructor (do not remove).
     */
    public SGSAppModuleImpl() {
    }
    private static final ADFLogger LOG = ADFLogger.createADFLogger(SGSAppModuleImpl.class);

    /**
     * @param seqName
     * @return sequence String next value
     */
    public String getDBSequence(String seqName) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {

            conn = getDBConnection();
            String sqlIdentifier = "select next value for " + seqName;
            pst = conn.prepareStatement(sqlIdentifier);

            ResultSet rs = pst.executeQuery();
            if (rs.next())
                rs.getInt(1);

            return rs.getString(1);
        } catch (SQLException sqle) {
            LOG.severe(sqle);
        } finally {
            try {
                conn.close();
                pst.close();
            } catch (SQLException e) {
                LOG.severe(e);
            }
        }
        return "0";
    }


    public Connection getDBConnection() {
        Connection conn = null;
        Properties prop = new Properties();
        InputStream input = null;
        try {

//            input = getClass().getClassLoader().getResourceAsStream("com/sgs/ics/model/bc/am/db_config.properties");
//            prop.load(input);
//
//            String connectionUrl = prop.getProperty("db_url_local");
//
//            conn = DriverManager.getConnection(connectionUrl);

                    //  conn = DriverManager.getConnection("jdbc:sqlserver://localhost;instanceName=SQLEXPRESS;databasename=DEVINTER;integratedSecurity=true;");
                     conn = DriverManager.getConnection("jdbc:sqlserver://ASBCOLPS02:1433;databaseName=DEVINTER","EYUser","Ey@123");


        } catch (SQLException sqle) {
            // TODO: Add catch code
            sqle.printStackTrace();
       } 
//        catch (IOException ex) {
//            ex.printStackTrace();
//        } 
        finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return conn;
    }


    /**
     * @param seqName
     * @return sequence next value
     */
    public int getDBSequence1(String seqName) {
        System.out.println("inside sequence");
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = getDBConnection();
            String sqlIdentifier = "select next value for " + seqName;
            pst = conn.prepareStatement(sqlIdentifier);

            ResultSet rs = pst.executeQuery();
            if (rs.next())
                rs.getInt(1);
            System.out.println("inside sequence" + rs.getInt(1));
            return rs.getInt(1);
        } catch (SQLException sqle) {
            LOG.severe(sqle);
        } finally {
            try {
                conn.close();
                pst.close();
            } catch (SQLException e) {
                LOG.severe(e);
            }
        }
        return 0;
    }

    /**
     * Container's getter for SgsCostIdentificationRuleVO1.
     * @return SgsCostIdentificationRuleVO1
     */
    public ViewObjectImpl getSgsCostIdentificationRuleVO() {
        return (ViewObjectImpl) findViewObject("SgsCostIdentificationRuleVO");
    }

    /**
     * Container's getter for AllocationRuleVO1.
     * @return AllocationRuleVO1
     */
    public ViewObjectImpl getAllocationRuleVO() {
        return (ViewObjectImpl) findViewObject("AllocationRuleVO");
    }

    /**
     * Container's getter for CostIdentificationRuleToAllocationRulelink1.
     * @return CostIdentificationRuleToAllocationRulelink1
     */
    public ViewLinkImpl getCostIdentificationRuleToAllocationRulelink() {
        return (ViewLinkImpl) findViewLink("CostIdentificationRuleToAllocationRulelink");
    }

    /**
     * Container's getter for CrossChargeCombinationVO1.
     * @return CrossChargeCombinationVO1
     */
    public ViewObjectImpl getCrossChargeCombinationVO() {
        return (ViewObjectImpl) findViewObject("CrossChargeCombinationVO");
    }

    /**
     * Container's getter for CostIdentificationRuleToCrossChargeCombinationlink1.
     * @return CostIdentificationRuleToCrossChargeCombinationlink1
     */
    public ViewLinkImpl getCostIdentificationRuleToCrossChargeCombinationlink() {
        return (ViewLinkImpl) findViewLink("CostIdentificationRuleToCrossChargeCombinationlink");
    }

    /**
     * Container's getter for SgsCostIdentificationCombinationVO1.
     * @return SgsCostIdentificationCombinationVO1
     */
    public ViewObjectImpl getSgsCostIdentificationCombinationVO() {
        return (ViewObjectImpl) findViewObject("SgsCostIdentificationCombinationVO");
    }

    /**
     * Container's getter for CostIdentificationRuleToCostIdentificationCombinationlink1.
     * @return CostIdentificationRuleToCostIdentificationCombinationlink1
     */
    public ViewLinkImpl getCostIdentificationRuleToCostIdentificationCombinationlink() {
        return (ViewLinkImpl) findViewLink("CostIdentificationRuleToCostIdentificationCombinationlink");
    }


    /**
     * Container's getter for SgsTpaMasterVO1.
     * @return SgsTpaMasterVO1
     */
    public SgsTpaMasterVOImpl getSgsTpaMasterVO() {
        return (SgsTpaMasterVOImpl) findViewObject("SgsTpaMasterVO");
    }

    /**
     * Container's getter for SgsTpaDocTypeVO1.
     * @return SgsTpaDocTypeVO1
     */
    public ViewObjectImpl getSgsTpaDocTypeVO() {
        return (ViewObjectImpl) findViewObject("SgsTpaDocTypeVO");
    }

    /**
     * Container's getter for FKSGSTPADOCATTACHMENTLink1.
     * @return FKSGSTPADOCATTACHMENTLink1
     */
    public ViewLinkImpl getFKSGSTPADOCATTACHMENTLink() {
        return (ViewLinkImpl) findViewLink("FKSGSTPADOCATTACHMENTLink");
    }


    /**
     * Container's getter for SgsMarkupRateTblVO2.
     * @return SgsMarkupRateTblVO2
     */
    public ViewObjectImpl getSgsMarkupRateTblVO() {
        return (ViewObjectImpl) findViewObject("SgsMarkupRateTblVO");
    }

    /**
     * Container's getter for FKSGSMARKUPRATETBLViewLink2.
     * @return FKSGSMARKUPRATETBLViewLink2
     */
    public ViewLinkImpl getFKSGSMARKUPRATETBLViewLink() {
        return (ViewLinkImpl) findViewLink("FKSGSMARKUPRATETBLViewLink");
    }

    /**
     * Container's getter for SgsLookupVO1.
     * @return SgsLookupVO1
     */
    public ViewObjectImpl getSgsLookupVO1() {
        return (ViewObjectImpl) findViewObject("SgsLookupVO1");
    }

    /**

     * Container's getter for SgsBusinessUnitMasterVO1.
     * @return SgsBusinessUnitMasterVO1
     */
    public ViewObjectImpl getSgsBusinessUnitMasterVO1() {
        return (ViewObjectImpl) findViewObject("SgsBusinessUnitMasterVO1");
    }

    //     * Container's getter for NatureOfExpenseLookupVO1.
    //     * @return NatureOfExpenseLookupVO1
    //     */
    public ViewObjectImpl getNatureOfExpenseLookupVO1() {
        return (ViewObjectImpl) findViewObject("NatureOfExpenseLookupVO1");

    }

    /**
     * Container's getter for SgsStatisticalDataVO1.
     * @return SgsStatisticalDataVO1
     */
    public ViewObjectImpl getSgsStatisticalDataVO1() {
        return (ViewObjectImpl) findViewObject("SgsStatisticalDataVO1");
    }

    /**
     * Container's getter for SgsStandardRateSetupVO1.
     * @return SgsStandardRateSetupVO1
     */
    public ViewObjectImpl getSgsStandardRateSetupVO1() {
        return (ViewObjectImpl) findViewObject("SgsStandardRateSetupVO1");
    }

    /**
     * Container's getter for SgsUserAuthVO1.
     * @return SgsUserAuthVO1
     */
    public ViewObjectImpl getSgsUserAuthVO1() {
        return (ViewObjectImpl) findViewObject("SgsUserAuthVO1");
    }

    public String loginValidationMethod(String user, String pass) {

        String userId = null;

        ViewObjectImpl vo = this.getSgsUserAuthVO1();
        ViewCriteria vc = vo.getViewCriteria("SgsUserAuthVOCriteria");
        vo.setNamedWhereClauseParam("bUserId", user);
        vo.setNamedWhereClauseParam("bPassword", pass);
        vo.applyViewCriteria(vc);
        vo.executeQuery();

        Row userVORow = vo.first();

        //Returns null if the username doesn't exists in the database
        return (userVORow != null) ? userVORow.getAttribute("UserId").toString() : null;
    }

    /**
     * Container's getter for NatureOfExpenseLookupVO2.
     * @return NatureOfExpenseLookupVO2
     */
    public ViewObjectImpl getNatureOfExpenseLookupVO2() {
        return (ViewObjectImpl) findViewObject("NatureOfExpenseLookupVO2");
    }

    /**
     * Container's getter for CostAllocationLOV1.
     * @return CostAllocationLOV1
     */
    public ViewObjectImpl getCostAllocationLOV1() {
        return (ViewObjectImpl) findViewObject("CostAllocationLOV1");
    }

    /**
     * Container's getter for CostGroupLOV1.
     * @return CostGroupLOV1
     */
    public ViewObjectImpl getCostGroupLOV1() {
        return (ViewObjectImpl) findViewObject("CostGroupLOV1");
    }

    /**
     * Container's getter for SgsStandardRateSetupVO2.
     * @return SgsStandardRateSetupVO2
     */
    public ViewObjectImpl getSgsStandardRateSetupVO2() {
        return (ViewObjectImpl) findViewObject("SgsStandardRateSetupVO2");
    }

    /**
     * Container's getter for SgsStdRateLineTblVO1.
     * @return SgsStdRateLineTblVO1
     */
    public SgsStdRateLineTblVOImpl getSgsStdRateLineTblVO1() {
        return (SgsStdRateLineTblVOImpl) findViewObject("SgsStdRateLineTblVO1");
    }

    /**
     * Container's getter for SgsStdRateLineTblVO2.
     * @return SgsStdRateLineTblVO2
     */
    public SgsStdRateLineTblVOImpl getSgsStdRateLineTblVO2() {
        return (SgsStdRateLineTblVOImpl) findViewObject("SgsStdRateLineTblVO2");
    }

    /**
     * Container's getter for FKSGSSTDRATELINETBLLink.
     * @return FKSGSSTDRATELINETBLLink
     */
    public ViewLinkImpl getFKSGSSTDRATELINETBLLink() {
        return (ViewLinkImpl) findViewLink("FKSGSSTDRATELINETBLLink");
    }


    /**
     * Container's getter for SgsStdRateLineTblVO4.
     * @return SgsStdRateLineTblVO4
     */
    public SgsStdRateLineTblVOImpl getSgsStdRateLineTblVO4() {
        return (SgsStdRateLineTblVOImpl) findViewObject("SgsStdRateLineTblVO4");
    }

    /**
     * Container's getter for StdRateViewLink2.
     * @return StdRateViewLink2
     */
    public ViewLinkImpl getStdRateViewLink2() {
        return (ViewLinkImpl) findViewLink("StdRateViewLink2");
    }


    /**
     * Container's getter for SgsTdsWhtTblVO1.
     * @return SgsTdsWhtTblVO1
     */
    public ViewObjectImpl getSgsTdsWhtTblVO1() {
        return (ViewObjectImpl) findViewObject("SgsTdsWhtTblVO1");
    }

    /**
     * Container's getter for SgsTdsWhtIdentificationVO1.
     * @return SgsTdsWhtIdentificationVO1
     */
    public ViewObjectImpl getSgsTdsWhtIdentificationVO1() {
        return (ViewObjectImpl) findViewObject("SgsTdsWhtIdentificationVO1");
    }

    /**
     * Container's getter for sgsTdsWhtIdentificationVL1.
     * @return sgsTdsWhtIdentificationVL1
     */
    public ViewLinkImpl getsgsTdsWhtIdentificationVL1() {
        return (ViewLinkImpl) findViewLink("sgsTdsWhtIdentificationVL1");
    }

    /**
     * Container's getter for SgsTdsWhtRateApplicabilityVO1.
     * @return SgsTdsWhtRateApplicabilityVO1
     */
    public ViewObjectImpl getSgsTdsWhtRateApplicabilityVO1() {
        return (ViewObjectImpl) findViewObject("SgsTdsWhtRateApplicabilityVO1");
    }

    /**
     * Container's getter for sgsTdsWhtRateApplicabilitVL1.
     * @return sgsTdsWhtRateApplicabilitVL1
     */
    public ViewLinkImpl getsgsTdsWhtRateApplicabilitVL1() {
        return (ViewLinkImpl) findViewLink("sgsTdsWhtRateApplicabilitVL1");
    }

    /**
     * Container's getter for SgsVatTblVO1.
     * @return SgsVatTblVO1
     */
    public ViewObjectImpl getSgsVatTblVO1() {
        return (ViewObjectImpl) findViewObject("SgsVatTblVO1");
    }

    /**
     * Container's getter for SgsVatTaxApplicabilityVO1.
     * @return SgsVatTaxApplicabilityVO1
     */
    public ViewObjectImpl getSgsVatTaxApplicabilityVO1() {
        return (ViewObjectImpl) findViewObject("SgsVatTaxApplicabilityVO1");
    }

    /**
     * Container's getter for sgsVatTaxApplicabilityVL1.
     * @return sgsVatTaxApplicabilityVL1
     */
    public ViewLinkImpl getsgsVatTaxApplicabilityVL1() {
        return (ViewLinkImpl) findViewLink("sgsVatTaxApplicabilityVL1");
    }
    /* Container's getter for SgsGstTblVO1.
     * @return SgsGstTblVO1
     */
    public ViewObjectImpl getSgsGstTblVO1() {
        return (ViewObjectImpl) findViewObject("SgsGstTblVO1");
    }


    /**
     * Container's getter for SgsGstTaxRateApplicabilityVO2.
     * @return SgsGstTaxRateApplicabilityVO2
     */
    public ViewObjectImpl getSgsGstTaxRateApplicabilityVO2() {
        return (ViewObjectImpl) findViewObject("SgsGstTaxRateApplicabilityVO2");
    }

    /**
     * Container's getter for sgsGstTaxRateApplicabilityVL1.
     * @return sgsGstTaxRateApplicabilityVL1
     */
    public ViewLinkImpl getsgsGstTaxRateApplicabilityVL1() {
        return (ViewLinkImpl) findViewLink("sgsGstTaxRateApplicabilityVL1");
    }

    /**
     * Container's getter for sgsMarkUpRateMasterVO1.
     * @return sgsMarkUpRateMasterVO1
     */
    public ViewObjectImpl getsgsMarkUpRateMasterVO1() {
        return (ViewObjectImpl) findViewObject("sgsMarkUpRateMasterVO1");
    }

    /**
     * Container's getter for sgsStatisticalDataTempVO1.
     * @return sgsStatisticalDataTempVO1
     */
    public ViewObjectImpl getsgsStatisticalDataTempVO1() {
        return (ViewObjectImpl) findViewObject("sgsStatisticalDataTempVO1");
    }

    /**
     * Container's getter for SgsIcInvoiceHeaderVO1.
     * @return SgsIcInvoiceHeaderVO1
     */
    public ViewObjectImpl getSgsIcInvoiceHeaderVO1() {
        return (ViewObjectImpl) findViewObject("SgsIcInvoiceHeaderVO1");
    }

    /**
     * Container's getter for SgsIcInvoiceLineVO1.
     * @return SgsIcInvoiceLineVO1
     */
    public ViewObjectImpl getSgsIcInvoiceLineVO1() {
        return (ViewObjectImpl) findViewObject("SgsIcInvoiceLineVO1");
    }

    /**
     * Container's getter for SgsInvoiceHeaderToLines1.
     * @return SgsInvoiceHeaderToLines1
     */
    public ViewLinkImpl getSgsInvoiceHeaderToLines1() {
        return (ViewLinkImpl) findViewLink("SgsInvoiceHeaderToLines1");

    }

    /**
     * Container's getter for SgsStatisticalPreviousMonthVO1.
     * @return SgsStatisticalPreviousMonthVO1
     */
    public ViewObjectImpl getSgsStatisticalPreviousMonthVO1() {
        return (ViewObjectImpl) findViewObject("SgsStatisticalPreviousMonthVO1");
    }


    /**
     * Container's getter for SgsCostIdentificationRuleVO1.
     * @return SgsCostIdentificationRuleVO1
     */
    public ViewObjectImpl getSgsCostIdentificationRuleVO1() {
        return (ViewObjectImpl) findViewObject("SgsCostIdentificationRuleVO1");
    }

    /**
     * Container's getter for SgsCostOffsetCombinationVO1.
     * @return SgsCostOffsetCombinationVO1
     */
    public ViewObjectImpl getSgsCostOffsetCombinationVO1() {
        return (ViewObjectImpl) findViewObject("SgsCostOffsetCombinationVO1");
    }

    /**
     * Container's getter for CostAllocationToOffsetCombVL1.
     * @return CostAllocationToOffsetCombVL1
     */
    public ViewLinkImpl getCostAllocationToOffsetCombVL1() {
        return (ViewLinkImpl) findViewLink("CostAllocationToOffsetCombVL1");
    }


    /**
     * Container's getter for SgsCostOffsetCombinationVO3.
     * @return SgsCostOffsetCombinationVO3
     */
    public ViewObjectImpl getSgsCostOffsetCombinationVO3() {
        return (ViewObjectImpl) findViewObject("SgsCostOffsetCombinationVO3");
    }

    /**
     * Container's getter for CostAllocationToOffsetCombVL3.
     * @return CostAllocationToOffsetCombVL3
     */
    public ViewLinkImpl getCostAllocationToOffsetCombVL3() {
        return (ViewLinkImpl) findViewLink("CostAllocationToOffsetCombVL3");
    }

    /**
     * Container's getter for SgsCostTargetCombinationVO1.
     * @return SgsCostTargetCombinationVO1
     */
    public ViewObjectImpl getSgsCostTargetCombinationVO1() {
        return (ViewObjectImpl) findViewObject("SgsCostTargetCombinationVO1");
    }

    /**
     * Container's getter for CostAllocationToTargetComboVL1.
     * @return CostAllocationToTargetComboVL1
     */
    public ViewLinkImpl getCostAllocationToTargetComboVL1() {
        return (ViewLinkImpl) findViewLink("CostAllocationToTargetComboVL1");
    }

    /**
     * Container's getter for SgsTransBCostAllocationVO1.
     * @return SgsTransBCostAllocationVO1
     */
    public ViewObjectImpl getSgsTransBCostAllocationVO1() {
        return (ViewObjectImpl) findViewObject("SgsTransBCostAllocationVO1");
    }

    /**
     * Container's getter for SgsTransReceivablesTempVO1.
     * @return SgsTransReceivablesTempVO1
     */
    public ViewObjectImpl getSgsTransReceivablesTempVO1() {
        return (ViewObjectImpl) findViewObject("SgsTransReceivablesTempVO1");
    }

    /**
     * Container's getter for SgsOtrTargetVO1.
     * @return SgsOtrTargetVO1
     */
    public ViewObjectImpl getSgsOtrTargetVO1() {
        return (ViewObjectImpl) findViewObject("SgsOtrTargetVO1");
    }

    /**
     * Container's getter for FKOTRTARGETViewLink1.
     * @return FKOTRTARGETViewLink1
     */
    public ViewLinkImpl getFKOTRTARGETViewLink1() {
        return (ViewLinkImpl) findViewLink("FKOTRTARGETViewLink1");
    }

    /**
     * Container's getter for SgsFixedAssetsTxnVO1.
     * @return SgsFixedAssetsTxnVO1
     */
    public ViewObjectImpl getSgsFixedAssetsTxnVO1() {
        return (ViewObjectImpl) findViewObject("SgsFixedAssetsTxnVO1");
    }

    /**
     * Container's getter for SgsTpaMaster1VO1.
     * @return SgsTpaMaster1VO1
     */
    public ViewObjectImpl getSgsTpaMaster1VO1() {
        return (ViewObjectImpl) findViewObject("SgsTpaMaster1VO1");
    }

    /**
     * Container's getter for SgsStlmtVoucherVO1.
     * @return SgsStlmtVoucherVO1
     */
    public ViewObjectImpl getSgsStlmtVoucherVO1() {
        return (ViewObjectImpl) findViewObject("SgsStlmtVoucherVO1");
    }

    /**
     * Container's getter for SgsStlmtInvVO1.
     * @return SgsStlmtInvVO1
     */
    public ViewObjectImpl getSgsStlmtInvVO1() {
        return (ViewObjectImpl) findViewObject("SgsStlmtInvVO1");
    }


    /**
     * Container's getter for SgsTpaDocAttachment1VO2.
     * @return SgsTpaDocAttachment1VO2
     */
    public ViewObjectImpl getSgsTpaDocAttachment1VO2() {
        return (ViewObjectImpl) findViewObject("SgsTpaDocAttachment1VO2");
    }

    /**
     * Container's getter for sgsTpa1VL1.
     * @return sgsTpa1VL1
     */
    public ViewLinkImpl getsgsTpa1VL1() {
        return (ViewLinkImpl) findViewLink("sgsTpa1VL1");
    }

    /**
     * Container's getter for CreateStlmtRVO1.
     * @return CreateStlmtRVO1
     */
    public ViewObjectImpl getCreateStlmtRVO1() {
        return (ViewObjectImpl) findViewObject("CreateStlmtRVO1");
    }

    /**
     * Container's getter for SgsCreateSettlementVO1.
     * @return SgsCreateSettlementVO1
     */
    public ViewObjectImpl getSgsCreateSettlementVO1() {
        return (ViewObjectImpl) findViewObject("SgsCreateSettlementVO1");
    }

    /**
     * Container's getter for SgsNettingInvoiceVoucherVO1.
     * @return SgsNettingInvoiceVoucherVO1
     */
    public ViewObjectImpl getSgsNettingInvoiceVoucherVO1() {
        return (ViewObjectImpl) findViewObject("SgsNettingInvoiceVoucherVO1");
    }

    /**
     * Container's getter for SgsNettingInvoiceVoucherVO2.
     * @return SgsNettingInvoiceVoucherVO2
     */
    public ViewObjectImpl getSgsNettingInvoiceVoucherVO2() {
        return (ViewObjectImpl) findViewObject("SgsNettingInvoiceVoucherVO2");
    }

    /**
     * Container's getter for SgsNettingGeo1VO1.
     * @return SgsNettingGeo1VO1
     */
    public ViewObjectImpl getSgsNettingGeo1VO1() {
        return (ViewObjectImpl) findViewObject("SgsNettingGeo1VO1");
    }

    /**
     * Container's getter for SgsNettingGeo2VO1.
     * @return SgsNettingGeo2VO1
     */
    public ViewObjectImpl getSgsNettingGeo2VO1() {
        return (ViewObjectImpl) findViewObject("SgsNettingGeo2VO1");
    }

    /**
     * Container's getter for SgsGeo1SumValuesVO1.
     * @return SgsGeo1SumValuesVO1
     */
    public ViewObjectImpl getSgsGeo1SumValuesVO1() {
        return (ViewObjectImpl) findViewObject("SgsGeo1SumValuesVO1");
    }

    /**
     * Container's getter for SgsGeo2SumValuesVO1.
     * @return SgsGeo2SumValuesVO1
     */
    public ViewObjectImpl getSgsGeo2SumValuesVO1() {
        return (ViewObjectImpl) findViewObject("SgsGeo2SumValuesVO1");
    }

    /**
     * Container's getter for RejectionReasonLOVVO1.
     * @return RejectionReasonLOVVO1
     */
    public ViewObjectImpl getRejectionReasonLOVVO1() {
        return (ViewObjectImpl) findViewObject("RejectionReasonLOVVO1");
    }

    /**
     * Container's getter for SgsPstTxnDocAttachVO1.
     * @return SgsPstTxnDocAttachVO1
     */
    public ViewObjectImpl getSgsPstTxnDocAttachVO1() {
        return (ViewObjectImpl) findViewObject("SgsPstTxnDocAttachVO1");
    }

    /**
     * Container's getter for FKSGSPSTTXNDOCATTACHMENTVL1.
     * @return FKSGSPSTTXNDOCATTACHMENTVL1
     */
    public ViewLinkImpl getFKSGSPSTTXNDOCATTACHMENTVL1() {
        return (ViewLinkImpl) findViewLink("FKSGSPSTTXNDOCATTACHMENTVL1");
    }

    /**
     * Container's getter for SgsStdRateDocVO1.
     * @return SgsStdRateDocVO1
     */
    public ViewObjectImpl getSgsStdRateDocVO1() {
        return (ViewObjectImpl) findViewObject("SgsStdRateDocVO1");
    }

    /**
     * Container's getter for SgsStdRateDocVL1.
     * @return SgsStdRateDocVL1
     */
    public ViewLinkImpl getSgsStdRateDocVL1() {
        return (ViewLinkImpl) findViewLink("SgsStdRateDocVL1");
    }

    /**
     * Container's getter for SgsDrtCrossChargeVO1.
     * @return SgsDrtCrossChargeVO1
     */
    public ViewObjectImpl getSgsDrtCrossChargeVO1() {
        return (ViewObjectImpl) findViewObject("SgsDrtCrossChargeVO1");
    }

    /**
     * Container's getter for SgsInvoiceCreditMemoVO1.
     * @return SgsInvoiceCreditMemoVO1
     */
    public ViewObjectImpl getSgsInvoiceCreditMemoVO1() {
        return (ViewObjectImpl) findViewObject("SgsInvoiceCreditMemoVO1");
    }

    /**
     * Container's getter for CreditMemoLOVVO1.
     * @return CreditMemoLOVVO1
     */
    public ViewObjectImpl getCreditMemoLOVVO1() {
        return (ViewObjectImpl) findViewObject("CreditMemoLOVVO1");
    }

    /**
     * Container's getter for TxnCategoryLOVVO1.
     * @return TxnCategoryLOVVO1
     */
    public ViewObjectImpl getTxnCategoryLOVVO1() {
        return (ViewObjectImpl) findViewObject("TxnCategoryLOVVO1");
    }

    /**
     * Container's getter for YesOrNoLookupVO1.
     * @return YesOrNoLookupVO1
     */
    public ViewObjectImpl getYesOrNoLookupVO1() {
        return (ViewObjectImpl) findViewObject("YesOrNoLookupVO1");
    }

    /**
     * Container's getter for ReversalReasonLOVVO1.
     * @return ReversalReasonLOVVO1
     */
    public ViewObjectImpl getReversalReasonLOVVO1() {
        return (ViewObjectImpl) findViewObject("ReversalReasonLOVVO1");
    }

    /**
     * Container's getter for SgsContraCashTblVO1.
     * @return SgsContraCashTblVO1
     */
    public ViewObjectImpl getSgsContraCashTblVO1() {
        return (ViewObjectImpl) findViewObject("SgsContraCashTblVO1");
    }

    /**
     * Container's getter for SettlementTypeLOVVO1.
     * @return SettlementTypeLOVVO1
     */
    public ViewObjectImpl getSettlementTypeLOVVO1() {
        return (ViewObjectImpl) findViewObject("SettlementTypeLOVVO1");
    }

    /**
     * Container's getter for ReversalTypeLOVVO1.
     * @return ReversalTypeLOVVO1
     */
    public ViewObjectImpl getReversalTypeLOVVO1() {
        return (ViewObjectImpl) findViewObject("ReversalTypeLOVVO1");
    }


    /**
     * Container's getter for SgsIcInvoiceLineVO2.
     * @return SgsIcInvoiceLineVO2
     */
    public ViewObjectImpl getSgsIcInvoiceLineVO2() {
        return (ViewObjectImpl) findViewObject("SgsIcInvoiceLineVO2");
    }

    /**
     * Container's getter for SgsStlmtVoucherVO2.
     * @return SgsStlmtVoucherVO2
     */
    public ViewObjectImpl getSgsStlmtVoucherVO2() {
        return (ViewObjectImpl) findViewObject("SgsStlmtVoucherVO2");
    }
}

