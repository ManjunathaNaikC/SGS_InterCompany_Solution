package com.sgs.ics.model.bc.am;


import com.sgs.ics.model.bc.am.common.SGSAppModule;
import com.sgs.ics.model.bc.view.SgsTpaMasterVOImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        try {
            String connectionUrl =
                //           "jdbc:sqlserver://localhost;instanceName=SQLEXPRESS;databasename=SGS_New;integratedSecurity=true;";
                "jdbc:sqlserver://localhost;instanceName=MSSQLSERVER;databasename=SGSICO;integratedSecurity=true;";
            conn = DriverManager.getConnection(connectionUrl);
        } catch (SQLException sqle) {
            // TODO: Add catch code
            sqle.printStackTrace();
        } finally {

        }
               
         return conn;   
        }


    /**
     * @param seqName
     * @return sequence next value
     */
    public int getDBSequence1(String seqName) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = getDBConnection();
            String sqlIdentifier = "select next value for " + seqName;
            pst = conn.prepareStatement(sqlIdentifier);

            ResultSet rs = pst.executeQuery();
            if (rs.next())
                rs.getInt(1);
            
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
    
    public String loginValidationMethod(String user,String pass){
         
         String userId =null;
         
         ViewObjectImpl vo = this.getSgsUserAuthVO1();
         ViewCriteria vc =vo.getViewCriteria("SgsUserAuthVOCriteria");
         vo.setNamedWhereClauseParam("bUserId", user);
         vo.setNamedWhereClauseParam("bPassword", pass);
         vo.applyViewCriteria(vc);
         vo.executeQuery();
             if(vo.getEstimatedRowCount()>0){   
                 if(vo.hasNext()){
                      
                     Row row = vo.next();   
                     System.out.println("UserId "+ row.getAttribute("UserId"));
                 
                     userId = (String)row.getAttribute("UserId");
                     }    
             }
             
             return userId;

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
}

