package com.sgs.ics.model.bc.entity;

import com.sgs.ics.model.bc.am.SGSAppModuleImpl;

import com.sgs.ics.model.bc.commonutils.CommonUtils;

import java.math.BigDecimal;

import java.sql.Date;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.TransactionEvent;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Dec 23 13:53:14 IST 2022
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class SgsStdRateLineTblEOImpl extends EntityImpl {
    private static final ADFLogger LOG = ADFLogger.createADFLogger(SgsStdRateLineTblEOImpl.class);
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        StdRateLineSeq,
        StandardRateSeq,
        CostIdentifier,
        SrGeography,
        StandardCost,
        NonPersonnelCost,
        UnitOfMeasure,
        Currency,
        BuUnit,
        EffectiveEndDate,
        EffectiveStartDate,
        CreatedBy,
        CreatedDate,
        UpdatedDate,
        UpdatedBy,
        Attribute1,
        Attribute2,
        Attribute3,
        Attribute4,
        Attribute5,
        Attribute6,
        Attribute7,
        Attribute8,
        Attribute9,
        Attribute10,
        Attribute11,
        Attribute12,
        Attribute13,
        Attribute14,
        Attribute15,
        JOBCODE,
        DEPTCOSTCENTER,
        EMPLOYEEGRADELEVEL,
        EMPLOYEEID,
        PROGRAMNAME,
        OPERATINGUNIT,
        NatureofExpense,
        STDRATEGEOGRAPHY,
        ADDTEXPENSECAT,
        SgsStandardRateSetupEO;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        protected int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        protected static final int firstIndex() {
            return firstIndex;
        }

        protected static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        protected static final AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }


    public static final int STDRATELINESEQ = AttributesEnum.StdRateLineSeq.index();
    public static final int STANDARDRATESEQ = AttributesEnum.StandardRateSeq.index();
    public static final int COSTIDENTIFIER = AttributesEnum.CostIdentifier.index();
    public static final int SRGEOGRAPHY = AttributesEnum.SrGeography.index();
    public static final int STANDARDCOST = AttributesEnum.StandardCost.index();
    public static final int NONPERSONNELCOST = AttributesEnum.NonPersonnelCost.index();
    public static final int UNITOFMEASURE = AttributesEnum.UnitOfMeasure.index();
    public static final int CURRENCY = AttributesEnum.Currency.index();
    public static final int BUUNIT = AttributesEnum.BuUnit.index();
    public static final int EFFECTIVEENDDATE = AttributesEnum.EffectiveEndDate.index();
    public static final int EFFECTIVESTARTDATE = AttributesEnum.EffectiveStartDate.index();
    public static final int CREATEDBY = AttributesEnum.CreatedBy.index();
    public static final int CREATEDDATE = AttributesEnum.CreatedDate.index();
    public static final int UPDATEDDATE = AttributesEnum.UpdatedDate.index();
    public static final int UPDATEDBY = AttributesEnum.UpdatedBy.index();
    public static final int ATTRIBUTE1 = AttributesEnum.Attribute1.index();
    public static final int ATTRIBUTE2 = AttributesEnum.Attribute2.index();
    public static final int ATTRIBUTE3 = AttributesEnum.Attribute3.index();
    public static final int ATTRIBUTE4 = AttributesEnum.Attribute4.index();
    public static final int ATTRIBUTE5 = AttributesEnum.Attribute5.index();
    public static final int ATTRIBUTE6 = AttributesEnum.Attribute6.index();
    public static final int ATTRIBUTE7 = AttributesEnum.Attribute7.index();
    public static final int ATTRIBUTE8 = AttributesEnum.Attribute8.index();
    public static final int ATTRIBUTE9 = AttributesEnum.Attribute9.index();
    public static final int ATTRIBUTE10 = AttributesEnum.Attribute10.index();
    public static final int ATTRIBUTE11 = AttributesEnum.Attribute11.index();
    public static final int ATTRIBUTE12 = AttributesEnum.Attribute12.index();
    public static final int ATTRIBUTE13 = AttributesEnum.Attribute13.index();
    public static final int ATTRIBUTE14 = AttributesEnum.Attribute14.index();
    public static final int ATTRIBUTE15 = AttributesEnum.Attribute15.index();
    public static final int JOBCODE = AttributesEnum.JOBCODE.index();
    public static final int DEPTCOSTCENTER = AttributesEnum.DEPTCOSTCENTER.index();
    public static final int EMPLOYEEGRADELEVEL = AttributesEnum.EMPLOYEEGRADELEVEL.index();
    public static final int EMPLOYEEID = AttributesEnum.EMPLOYEEID.index();
    public static final int PROGRAMNAME = AttributesEnum.PROGRAMNAME.index();
    public static final int OPERATINGUNIT = AttributesEnum.OPERATINGUNIT.index();
    public static final int NATUREOFEXPENSE = AttributesEnum.NatureofExpense.index();
    public static final int STDRATEGEOGRAPHY = AttributesEnum.STDRATEGEOGRAPHY.index();
    public static final int ADDTEXPENSECAT = AttributesEnum.ADDTEXPENSECAT.index();
    public static final int SGSSTANDARDRATESETUPEO = AttributesEnum.SgsStandardRateSetupEO.index();

    /**
     * This is the default constructor (do not remove).
     */
    public SgsStdRateLineTblEOImpl() {
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("com.sgs.ics.model.bc.entity.SgsStdRateLineTblEO");
    }


    /**
     * Gets the attribute value for StdRateLineSeq, using the alias name StdRateLineSeq.
     * @return the value of StdRateLineSeq
     */
    public Integer getStdRateLineSeq() {
        return (Integer) getAttributeInternal(STDRATELINESEQ);
    }

    /**
     * Sets <code>value</code> as the attribute value for StdRateLineSeq.
     * @param value value to set the StdRateLineSeq
     */
    public void setStdRateLineSeq(Integer value) {
        setAttributeInternal(STDRATELINESEQ, value);
    }

    /**
     * Gets the attribute value for StandardRateSeq, using the alias name StandardRateSeq.
     * @return the value of StandardRateSeq
     */
    public Integer getStandardRateSeq() {
        return (Integer) getAttributeInternal(STANDARDRATESEQ);
    }

    /**
     * Sets <code>value</code> as the attribute value for StandardRateSeq.
     * @param value value to set the StandardRateSeq
     */
    public void setStandardRateSeq(Integer value) {
        setAttributeInternal(STANDARDRATESEQ, value);
    }

    /**
     * Gets the attribute value for CostIdentifier, using the alias name CostIdentifier.
     * @return the value of CostIdentifier
     */
    public String getCostIdentifier() {
        return (String) getAttributeInternal(COSTIDENTIFIER);
    }

    /**
     * Sets <code>value</code> as the attribute value for CostIdentifier.
     * @param value value to set the CostIdentifier
     */
    public void setCostIdentifier(String value) {
        setAttributeInternal(COSTIDENTIFIER, value);
    }

    /**
     * Gets the attribute value for SrGeography, using the alias name SrGeography.
     * @return the value of SrGeography
     */
    public String getSrGeography() {
        return (String) getAttributeInternal(SRGEOGRAPHY);
    }

    /**
     * Sets <code>value</code> as the attribute value for SrGeography.
     * @param value value to set the SrGeography
     */
    public void setSrGeography(String value) {
        setAttributeInternal(SRGEOGRAPHY, value);
    }

    /**
     * Gets the attribute value for StandardCost, using the alias name StandardCost.
     * @return the value of StandardCost
     */
    public BigDecimal getStandardCost() {
        return (BigDecimal) getAttributeInternal(STANDARDCOST);
    }

    /**
     * Sets <code>value</code> as the attribute value for StandardCost.
     * @param value value to set the StandardCost
     */
    public void setStandardCost(BigDecimal value) {
        setAttributeInternal(STANDARDCOST, value);
    }

    /**
     * Gets the attribute value for NonPersonnelCost, using the alias name NonPersonnelCost.
     * @return the value of NonPersonnelCost
     */
    public BigDecimal getNonPersonnelCost() {
        return (BigDecimal) getAttributeInternal(NONPERSONNELCOST);
    }

    /**
     * Sets <code>value</code> as the attribute value for NonPersonnelCost.
     * @param value value to set the NonPersonnelCost
     */
    public void setNonPersonnelCost(BigDecimal value) {
        setAttributeInternal(NONPERSONNELCOST, value);
    }

    /**
     * Gets the attribute value for UnitOfMeasure, using the alias name UnitOfMeasure.
     * @return the value of UnitOfMeasure
     */
    public String getUnitOfMeasure() {
        return (String) getAttributeInternal(UNITOFMEASURE);
    }

    /**
     * Sets <code>value</code> as the attribute value for UnitOfMeasure.
     * @param value value to set the UnitOfMeasure
     */
    public void setUnitOfMeasure(String value) {
        setAttributeInternal(UNITOFMEASURE, value);
    }

    /**
     * Gets the attribute value for Currency, using the alias name Currency.
     * @return the value of Currency
     */
    public String getCurrency() {
        return (String) getAttributeInternal(CURRENCY);
    }

    /**
     * Sets <code>value</code> as the attribute value for Currency.
     * @param value value to set the Currency
     */
    public void setCurrency(String value) {
        setAttributeInternal(CURRENCY, value);
    }

    /**
     * Gets the attribute value for BuUnit, using the alias name BuUnit.
     * @return the value of BuUnit
     */
    public String getBuUnit() {
        return (String) getAttributeInternal(BUUNIT);
    }

    /**
     * Sets <code>value</code> as the attribute value for BuUnit.
     * @param value value to set the BuUnit
     */
    public void setBuUnit(String value) {
        setAttributeInternal(BUUNIT, value);
    }

    /**
     * Gets the attribute value for EffectiveEndDate, using the alias name EffectiveEndDate.
     * @return the value of EffectiveEndDate
     */
    public Date getEffectiveEndDate() {
        return (Date) getAttributeInternal(EFFECTIVEENDDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for EffectiveEndDate.
     * @param value value to set the EffectiveEndDate
     */
    public void setEffectiveEndDate(Date value) {
        setAttributeInternal(EFFECTIVEENDDATE, value);
    }

    /**
     * Gets the attribute value for EffectiveStartDate, using the alias name EffectiveStartDate.
     * @return the value of EffectiveStartDate
     */
    public Date getEffectiveStartDate() {
        return (Date) getAttributeInternal(EFFECTIVESTARTDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for EffectiveStartDate.
     * @param value value to set the EffectiveStartDate
     */
    public void setEffectiveStartDate(Date value) {
        setAttributeInternal(EFFECTIVESTARTDATE, value);
    }

    /**
     * Gets the attribute value for CreatedBy, using the alias name CreatedBy.
     * @return the value of CreatedBy
     */
    public String getCreatedBy() {
        return (String) getAttributeInternal(CREATEDBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for CreatedBy.
     * @param value value to set the CreatedBy
     */
    public void setCreatedBy(String value) {
        setAttributeInternal(CREATEDBY, value);
    }

    /**
     * Gets the attribute value for CreatedDate, using the alias name CreatedDate.
     * @return the value of CreatedDate
     */
    public Date getCreatedDate() {
        return (Date) getAttributeInternal(CREATEDDATE);
    }


    /**
     * Gets the attribute value for UpdatedDate, using the alias name UpdatedDate.
     * @return the value of UpdatedDate
     */
    public Date getUpdatedDate() {
        return (Date) getAttributeInternal(UPDATEDDATE);
    }


    /**
     * Gets the attribute value for UpdatedBy, using the alias name UpdatedBy.
     * @return the value of UpdatedBy
     */
    public String getUpdatedBy() {
        return (String) getAttributeInternal(UPDATEDBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for UpdatedBy.
     * @param value value to set the UpdatedBy
     */
    public void setUpdatedBy(String value) {
        setAttributeInternal(UPDATEDBY, value);
    }

    /**
     * Gets the attribute value for Attribute1, using the alias name Attribute1.
     * @return the value of Attribute1
     */
    public String getAttribute1() {
        return (String) getAttributeInternal(ATTRIBUTE1);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute1.
     * @param value value to set the Attribute1
     */
    public void setAttribute1(String value) {
        setAttributeInternal(ATTRIBUTE1, value);
    }

    /**
     * Gets the attribute value for Attribute2, using the alias name Attribute2.
     * @return the value of Attribute2
     */
    public String getAttribute2() {
        return (String) getAttributeInternal(ATTRIBUTE2);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute2.
     * @param value value to set the Attribute2
     */
    public void setAttribute2(String value) {
        setAttributeInternal(ATTRIBUTE2, value);
    }

    /**
     * Gets the attribute value for Attribute3, using the alias name Attribute3.
     * @return the value of Attribute3
     */
    public String getAttribute3() {
        return (String) getAttributeInternal(ATTRIBUTE3);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute3.
     * @param value value to set the Attribute3
     */
    public void setAttribute3(String value) {
        setAttributeInternal(ATTRIBUTE3, value);
    }

    /**
     * Gets the attribute value for Attribute4, using the alias name Attribute4.
     * @return the value of Attribute4
     */
    public String getAttribute4() {
        return (String) getAttributeInternal(ATTRIBUTE4);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute4.
     * @param value value to set the Attribute4
     */
    public void setAttribute4(String value) {
        setAttributeInternal(ATTRIBUTE4, value);
    }

    /**
     * Gets the attribute value for Attribute5, using the alias name Attribute5.
     * @return the value of Attribute5
     */
    public String getAttribute5() {
        return (String) getAttributeInternal(ATTRIBUTE5);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute5.
     * @param value value to set the Attribute5
     */
    public void setAttribute5(String value) {
        setAttributeInternal(ATTRIBUTE5, value);
    }

    /**
     * Gets the attribute value for Attribute6, using the alias name Attribute6.
     * @return the value of Attribute6
     */
    public Date getAttribute6() {
        return (Date) getAttributeInternal(ATTRIBUTE6);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute6.
     * @param value value to set the Attribute6
     */
    public void setAttribute6(Date value) {
        setAttributeInternal(ATTRIBUTE6, value);
    }

    /**
     * Gets the attribute value for Attribute7, using the alias name Attribute7.
     * @return the value of Attribute7
     */
    public Date getAttribute7() {
        return (Date) getAttributeInternal(ATTRIBUTE7);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute7.
     * @param value value to set the Attribute7
     */
    public void setAttribute7(Date value) {
        setAttributeInternal(ATTRIBUTE7, value);
    }

    /**
     * Gets the attribute value for Attribute8, using the alias name Attribute8.
     * @return the value of Attribute8
     */
    public Date getAttribute8() {
        return (Date) getAttributeInternal(ATTRIBUTE8);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute8.
     * @param value value to set the Attribute8
     */
    public void setAttribute8(Date value) {
        setAttributeInternal(ATTRIBUTE8, value);
    }

    /**
     * Gets the attribute value for Attribute9, using the alias name Attribute9.
     * @return the value of Attribute9
     */
    public Date getAttribute9() {
        return (Date) getAttributeInternal(ATTRIBUTE9);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute9.
     * @param value value to set the Attribute9
     */
    public void setAttribute9(Date value) {
        setAttributeInternal(ATTRIBUTE9, value);
    }

    /**
     * Gets the attribute value for Attribute10, using the alias name Attribute10.
     * @return the value of Attribute10
     */
    public Date getAttribute10() {
        return (Date) getAttributeInternal(ATTRIBUTE10);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute10.
     * @param value value to set the Attribute10
     */
    public void setAttribute10(Date value) {
        setAttributeInternal(ATTRIBUTE10, value);
    }

    /**
     * Gets the attribute value for Attribute11, using the alias name Attribute11.
     * @return the value of Attribute11
     */
    public BigDecimal getAttribute11() {
        return (BigDecimal) getAttributeInternal(ATTRIBUTE11);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute11.
     * @param value value to set the Attribute11
     */
    public void setAttribute11(BigDecimal value) {
        setAttributeInternal(ATTRIBUTE11, value);
    }

    /**
     * Gets the attribute value for Attribute12, using the alias name Attribute12.
     * @return the value of Attribute12
     */
    public BigDecimal getAttribute12() {
        return (BigDecimal) getAttributeInternal(ATTRIBUTE12);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute12.
     * @param value value to set the Attribute12
     */
    public void setAttribute12(BigDecimal value) {
        setAttributeInternal(ATTRIBUTE12, value);
    }

    /**
     * Gets the attribute value for Attribute13, using the alias name Attribute13.
     * @return the value of Attribute13
     */
    public BigDecimal getAttribute13() {
        return (BigDecimal) getAttributeInternal(ATTRIBUTE13);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute13.
     * @param value value to set the Attribute13
     */
    public void setAttribute13(BigDecimal value) {
        setAttributeInternal(ATTRIBUTE13, value);
    }

    /**
     * Gets the attribute value for Attribute14, using the alias name Attribute14.
     * @return the value of Attribute14
     */
    public BigDecimal getAttribute14() {
        return (BigDecimal) getAttributeInternal(ATTRIBUTE14);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute14.
     * @param value value to set the Attribute14
     */
    public void setAttribute14(BigDecimal value) {
        setAttributeInternal(ATTRIBUTE14, value);
    }

    /**
     * Gets the attribute value for Attribute15, using the alias name Attribute15.
     * @return the value of Attribute15
     */
    public BigDecimal getAttribute15() {
        return (BigDecimal) getAttributeInternal(ATTRIBUTE15);
    }

    /**
     * Sets <code>value</code> as the attribute value for Attribute15.
     * @param value value to set the Attribute15
     */
    public void setAttribute15(BigDecimal value) {
        setAttributeInternal(ATTRIBUTE15, value);
    }

    /**
     * Gets the attribute value for JOBCODE, using the alias name JOBCODE.
     * @return the value of JOBCODE
     */
    public String getJOBCODE() {
        return (String) getAttributeInternal(JOBCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for JOBCODE.
     * @param value value to set the JOBCODE
     */
    public void setJOBCODE(String value) {
        setAttributeInternal(JOBCODE, value);
    }

    /**
     * Gets the attribute value for DEPTCOSTCENTER, using the alias name DEPTCOSTCENTER.
     * @return the value of DEPTCOSTCENTER
     */
    public String getDEPTCOSTCENTER() {
        return (String) getAttributeInternal(DEPTCOSTCENTER);
    }

    /**
     * Sets <code>value</code> as the attribute value for DEPTCOSTCENTER.
     * @param value value to set the DEPTCOSTCENTER
     */
    public void setDEPTCOSTCENTER(String value) {
        setAttributeInternal(DEPTCOSTCENTER, value);
    }

    /**
     * Gets the attribute value for EMPLOYEEGRADELEVEL, using the alias name EMPLOYEEGRADELEVEL.
     * @return the value of EMPLOYEEGRADELEVEL
     */
    public String getEMPLOYEEGRADELEVEL() {
        return (String) getAttributeInternal(EMPLOYEEGRADELEVEL);
    }

    /**
     * Sets <code>value</code> as the attribute value for EMPLOYEEGRADELEVEL.
     * @param value value to set the EMPLOYEEGRADELEVEL
     */
    public void setEMPLOYEEGRADELEVEL(String value) {
        setAttributeInternal(EMPLOYEEGRADELEVEL, value);
    }

    /**
     * Gets the attribute value for EMPLOYEEID, using the alias name EMPLOYEEID.
     * @return the value of EMPLOYEEID
     */
    public String getEMPLOYEEID() {
        return (String) getAttributeInternal(EMPLOYEEID);
    }

    /**
     * Sets <code>value</code> as the attribute value for EMPLOYEEID.
     * @param value value to set the EMPLOYEEID
     */
    public void setEMPLOYEEID(String value) {
        setAttributeInternal(EMPLOYEEID, value);
    }

    /**
     * Gets the attribute value for PROGRAMNAME, using the alias name PROGRAMNAME.
     * @return the value of PROGRAMNAME
     */
    public String getPROGRAMNAME() {
        return (String) getAttributeInternal(PROGRAMNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for PROGRAMNAME.
     * @param value value to set the PROGRAMNAME
     */
    public void setPROGRAMNAME(String value) {
        setAttributeInternal(PROGRAMNAME, value);
    }

    /**
     * Gets the attribute value for OPERATINGUNIT, using the alias name OPERATINGUNIT.
     * @return the value of OPERATINGUNIT
     */
    public String getOPERATINGUNIT() {
        return (String) getAttributeInternal(OPERATINGUNIT);
    }

    /**
     * Sets <code>value</code> as the attribute value for OPERATINGUNIT.
     * @param value value to set the OPERATINGUNIT
     */
    public void setOPERATINGUNIT(String value) {
        setAttributeInternal(OPERATINGUNIT, value);
    }

    /**
     * Gets the attribute value for NatureofExpense, using the alias name NatureofExpense.
     * @return the value of NatureofExpense
     */
    public String getNatureofExpense() {
        return (String) getAttributeInternal(NATUREOFEXPENSE);
    }

    /**
     * Sets <code>value</code> as the attribute value for NatureofExpense.
     * @param value value to set the NatureofExpense
     */
    public void setNatureofExpense(String value) {
        setAttributeInternal(NATUREOFEXPENSE, value);
    }

    /**
     * Gets the attribute value for STDRATEGEOGRAPHY, using the alias name STDRATEGEOGRAPHY.
     * @return the value of STDRATEGEOGRAPHY
     */
    public String getSTDRATEGEOGRAPHY() {
        return (String) getAttributeInternal(STDRATEGEOGRAPHY);
    }

    /**
     * Sets <code>value</code> as the attribute value for STDRATEGEOGRAPHY.
     * @param value value to set the STDRATEGEOGRAPHY
     */
    public void setSTDRATEGEOGRAPHY(String value) {
        setAttributeInternal(STDRATEGEOGRAPHY, value);
    }

    /**
     * Gets the attribute value for ADDTEXPENSECAT, using the alias name ADDTEXPENSECAT.
     * @return the value of ADDTEXPENSECAT
     */
    public String getADDTEXPENSECAT() {
        return (String) getAttributeInternal(ADDTEXPENSECAT);
    }

    /**
     * Sets <code>value</code> as the attribute value for ADDTEXPENSECAT.
     * @param value value to set the ADDTEXPENSECAT
     */
    public void setADDTEXPENSECAT(String value) {
        setAttributeInternal(ADDTEXPENSECAT, value);
    }

    /**
     * @return the associated entity SgsStandardRateSetupEOImpl.
     */
    public SgsStandardRateSetupEOImpl getSgsStandardRateSetupEO() {
        return (SgsStandardRateSetupEOImpl) getAttributeInternal(SGSSTANDARDRATESETUPEO);
    }

    /**
     * Sets <code>value</code> as the associated entity SgsStandardRateSetupEOImpl.
     */
    public void setSgsStandardRateSetupEO(SgsStandardRateSetupEOImpl value) {
        setAttributeInternal(SGSSTANDARDRATESETUPEO, value);
    }


    /**
     * @param stdRateLineSeq key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Integer stdRateLineSeq) {
        return new Key(new Object[] { stdRateLineSeq });
    }

    /**
     * Add attribute defaulting logic in this method.
     * @param attributeList list of attribute names/values to initialize the row
     */
  
    
    protected void create(AttributeList attributeList) {
        super.create(attributeList);        
        try {
            SGSAppModuleImpl am = new SGSAppModuleImpl();
            setStdRateLineSeq(am.getDBSequence1("SEQ_SGS_STDRATE_LINE_TBL"));
            CommonUtils util= new CommonUtils();
            Object user= (Object)util.getSessionScopeValue("_username").toString();
            setCreatedBy(user.toString());
            
        } catch (Exception e) {
            LOG.severe(e);
        }
    }

    /**
     * Custom DML update/insert/delete logic here.
     * @param operation the operation type
     * @param e the transaction event
     */
    protected void doDML(int operation, TransactionEvent e) {
        if (operation == DML_UPDATE) {
            CommonUtils util = new CommonUtils();
            Object user = (Object) util.getSessionScopeValue("_username").toString();
            setUpdatedBy(user.toString());
        }

        super.doDML(operation, e);
    }

}

