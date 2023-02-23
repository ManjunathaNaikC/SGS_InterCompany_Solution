package com.sgs.ics.model.bc.entity;

import com.sgs.ics.model.bc.am.SGSAppModuleImpl;
import com.sgs.ics.model.bc.commonutils.CommonUtils;

import java.math.BigDecimal;

import java.sql.Date;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.RowIterator;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Jan 10 14:53:05 IST 2023
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class SgsTransBCostAllocationImpl extends EntityImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        TbcaId,
        CirId,
        BuId,
        ApprovalReqType,
        ExtractId,
        RectificationId,
        LegalEntity,
        JobCode,
        GlAccount,
        OprationalUnit,
        DepartmentId,
        AffiliateCode,
        VerticalCode,
        HorizontalCode,
        BookCode,
        SourceSystem,
        SourceModule,
        SourceTable,
        TransactionPeriod,
        InputProvider,
        NatureOfExpense,
        JournalId,
        GlDate,
        TransactionDate,
        TransactionName,
        TransactionType,
        TransactionCategory,
        TransactionCurrency,
        TransactionAmount,
        FunctionalCurrency,
        ForeignExchangeRate,
        ServiceCategory,
        AccountTreatment,
        ExtractedBy,
        ExtractedDate,
        TransactionStatus,
        CreatedDate,
        CreatedBy,
        UpdatedDate,
        UpdatedBy,
        AllocationBasis,
        PeoplesoftTransactionId,
        VendorId,
        PoNumber,
        VoucherId,
        VoucherNo,
        IgstAmount,
        CgstAmount,
        SgstAmount,
        Duedate,
        HoldReason,
        HoldRemarks,
        Holdby,
        Holdon,
        ReleaseAction,
        ReleaseRemarks,
        Releasedby,
        Releasedon,
        SgsOtrTargetEO;
        private static AttributesEnum[] vals = null;
        ;
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
    private static final ADFLogger LOG = ADFLogger.createADFLogger(SgsTransBCostAllocationImpl.class);


    public static final int TBCAID = AttributesEnum.TbcaId.index();
    public static final int CIRID = AttributesEnum.CirId.index();
    public static final int BUID = AttributesEnum.BuId.index();
    public static final int APPROVALREQTYPE = AttributesEnum.ApprovalReqType.index();
    public static final int EXTRACTID = AttributesEnum.ExtractId.index();
    public static final int RECTIFICATIONID = AttributesEnum.RectificationId.index();
    public static final int LEGALENTITY = AttributesEnum.LegalEntity.index();
    public static final int JOBCODE = AttributesEnum.JobCode.index();
    public static final int GLACCOUNT = AttributesEnum.GlAccount.index();
    public static final int OPRATIONALUNIT = AttributesEnum.OprationalUnit.index();
    public static final int DEPARTMENTID = AttributesEnum.DepartmentId.index();
    public static final int AFFILIATECODE = AttributesEnum.AffiliateCode.index();
    public static final int VERTICALCODE = AttributesEnum.VerticalCode.index();
    public static final int HORIZONTALCODE = AttributesEnum.HorizontalCode.index();
    public static final int BOOKCODE = AttributesEnum.BookCode.index();
    public static final int SOURCESYSTEM = AttributesEnum.SourceSystem.index();
    public static final int SOURCEMODULE = AttributesEnum.SourceModule.index();
    public static final int SOURCETABLE = AttributesEnum.SourceTable.index();
    public static final int TRANSACTIONPERIOD = AttributesEnum.TransactionPeriod.index();
    public static final int INPUTPROVIDER = AttributesEnum.InputProvider.index();
    public static final int NATUREOFEXPENSE = AttributesEnum.NatureOfExpense.index();
    public static final int JOURNALID = AttributesEnum.JournalId.index();
    public static final int GLDATE = AttributesEnum.GlDate.index();
    public static final int TRANSACTIONDATE = AttributesEnum.TransactionDate.index();
    public static final int TRANSACTIONNAME = AttributesEnum.TransactionName.index();
    public static final int TRANSACTIONTYPE = AttributesEnum.TransactionType.index();
    public static final int TRANSACTIONCATEGORY = AttributesEnum.TransactionCategory.index();
    public static final int TRANSACTIONCURRENCY = AttributesEnum.TransactionCurrency.index();
    public static final int TRANSACTIONAMOUNT = AttributesEnum.TransactionAmount.index();
    public static final int FUNCTIONALCURRENCY = AttributesEnum.FunctionalCurrency.index();
    public static final int FOREIGNEXCHANGERATE = AttributesEnum.ForeignExchangeRate.index();
    public static final int SERVICECATEGORY = AttributesEnum.ServiceCategory.index();
    public static final int ACCOUNTTREATMENT = AttributesEnum.AccountTreatment.index();
    public static final int EXTRACTEDBY = AttributesEnum.ExtractedBy.index();
    public static final int EXTRACTEDDATE = AttributesEnum.ExtractedDate.index();
    public static final int TRANSACTIONSTATUS = AttributesEnum.TransactionStatus.index();
    public static final int CREATEDDATE = AttributesEnum.CreatedDate.index();
    public static final int CREATEDBY = AttributesEnum.CreatedBy.index();
    public static final int UPDATEDDATE = AttributesEnum.UpdatedDate.index();
    public static final int UPDATEDBY = AttributesEnum.UpdatedBy.index();
    public static final int ALLOCATIONBASIS = AttributesEnum.AllocationBasis.index();
    public static final int PEOPLESOFTTRANSACTIONID = AttributesEnum.PeoplesoftTransactionId.index();
    public static final int VENDORID = AttributesEnum.VendorId.index();
    public static final int PONUMBER = AttributesEnum.PoNumber.index();
    public static final int VOUCHERID = AttributesEnum.VoucherId.index();
    public static final int VOUCHERNO = AttributesEnum.VoucherNo.index();
    public static final int IGSTAMOUNT = AttributesEnum.IgstAmount.index();
    public static final int CGSTAMOUNT = AttributesEnum.CgstAmount.index();
    public static final int SGSTAMOUNT = AttributesEnum.SgstAmount.index();
    public static final int DUEDATE = AttributesEnum.Duedate.index();
    public static final int HOLDREASON = AttributesEnum.HoldReason.index();
    public static final int HOLDREMARKS = AttributesEnum.HoldRemarks.index();
    public static final int HOLDBY = AttributesEnum.Holdby.index();
    public static final int HOLDON = AttributesEnum.Holdon.index();
    public static final int RELEASEACTION = AttributesEnum.ReleaseAction.index();
    public static final int RELEASEREMARKS = AttributesEnum.ReleaseRemarks.index();
    public static final int RELEASEDBY = AttributesEnum.Releasedby.index();
    public static final int RELEASEDON = AttributesEnum.Releasedon.index();
    public static final int SGSOTRTARGETEO = AttributesEnum.SgsOtrTargetEO.index();

    /**
     * This is the default constructor (do not remove).
     */
    public SgsTransBCostAllocationImpl() {
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("com.sgs.ics.model.bc.entity.SgsTransBCostAllocation");
    }


    /**
     * Gets the attribute value for TbcaId, using the alias name TbcaId.
     * @return the value of TbcaId
     */
    public String getTbcaId() {
        return (String) getAttributeInternal(TBCAID);
    }

    /**
     * Sets <code>value</code> as the attribute value for TbcaId.
     * @param value value to set the TbcaId
     */
    public void setTbcaId(String value) {
        setAttributeInternal(TBCAID, value);
    }

    /**
     * Gets the attribute value for CirId, using the alias name CirId.
     * @return the value of CirId
     */
    public String getCirId() {
        return (String) getAttributeInternal(CIRID);
    }

    /**
     * Sets <code>value</code> as the attribute value for CirId.
     * @param value value to set the CirId
     */
    public void setCirId(String value) {
        setAttributeInternal(CIRID, value);
    }

    /**
     * Gets the attribute value for BuId, using the alias name BuId.
     * @return the value of BuId
     */
    public String getBuId() {
        return (String) getAttributeInternal(BUID);
    }

    /**
     * Sets <code>value</code> as the attribute value for BuId.
     * @param value value to set the BuId
     */
    public void setBuId(String value) {
        setAttributeInternal(BUID, value);
     // SgsBusinessUnitMasterEOImpl bu =(SgsBusinessUnitMasterEOImpl)getBussinessUnitId();
      //setLegalEntity(bu.getLegalEntity());
        
    }

    /**
     * Gets the attribute value for ApprovalReqType, using the alias name ApprovalReqType.
     * @return the value of ApprovalReqType
     */
    public String getApprovalReqType() {
        return (String) getAttributeInternal(APPROVALREQTYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for ApprovalReqType.
     * @param value value to set the ApprovalReqType
     */
    public void setApprovalReqType(String value) {
        setAttributeInternal(APPROVALREQTYPE, value);
    }

    /**
     * Gets the attribute value for ExtractId, using the alias name ExtractId.
     * @return the value of ExtractId
     */
    public String getExtractId() {
        return (String) getAttributeInternal(EXTRACTID);
    }

    /**
     * Sets <code>value</code> as the attribute value for ExtractId.
     * @param value value to set the ExtractId
     */
    public void setExtractId(String value) {
        setAttributeInternal(EXTRACTID, value);
    }

    /**
     * Gets the attribute value for RectificationId, using the alias name RectificationId.
     * @return the value of RectificationId
     */
    public String getRectificationId() {
        return (String) getAttributeInternal(RECTIFICATIONID);
    }

    /**
     * Sets <code>value</code> as the attribute value for RectificationId.
     * @param value value to set the RectificationId
     */
    public void setRectificationId(String value) {
        setAttributeInternal(RECTIFICATIONID, value);
    }

    /**
     * Gets the attribute value for LegalEntity, using the alias name LegalEntity.
     * @return the value of LegalEntity
     */
    public String getLegalEntity() {
        return (String) getAttributeInternal(LEGALENTITY);
    }

    /**
     * Sets <code>value</code> as the attribute value for LegalEntity.
     * @param value value to set the LegalEntity
     */
    public void setLegalEntity(String value) {
        setAttributeInternal(LEGALENTITY, value);
        
    }

    /**
     * Gets the attribute value for JobCode, using the alias name JobCode.
     * @return the value of JobCode
     */
    public String getJobCode() {
        return (String) getAttributeInternal(JOBCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for JobCode.
     * @param value value to set the JobCode
     */
    public void setJobCode(String value) {
        setAttributeInternal(JOBCODE, value);
    }

    /**
     * Gets the attribute value for GlAccount, using the alias name GlAccount.
     * @return the value of GlAccount
     */
    public String getGlAccount() {
        return (String) getAttributeInternal(GLACCOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for GlAccount.
     * @param value value to set the GlAccount
     */
    public void setGlAccount(String value) {
        setAttributeInternal(GLACCOUNT, value);
    }

    /**
     * Gets the attribute value for OprationalUnit, using the alias name OprationalUnit.
     * @return the value of OprationalUnit
     */
    public String getOprationalUnit() {
        return (String) getAttributeInternal(OPRATIONALUNIT);
    }

    /**
     * Sets <code>value</code> as the attribute value for OprationalUnit.
     * @param value value to set the OprationalUnit
     */
    public void setOprationalUnit(String value) {
        setAttributeInternal(OPRATIONALUNIT, value);
    }

    /**
     * Gets the attribute value for DepartmentId, using the alias name DepartmentId.
     * @return the value of DepartmentId
     */
    public String getDepartmentId() {
        return (String) getAttributeInternal(DEPARTMENTID);
    }

    /**
     * Sets <code>value</code> as the attribute value for DepartmentId.
     * @param value value to set the DepartmentId
     */
    public void setDepartmentId(String value) {
        setAttributeInternal(DEPARTMENTID, value);
    }

    /**
     * Gets the attribute value for AffiliateCode, using the alias name AffiliateCode.
     * @return the value of AffiliateCode
     */
    public String getAffiliateCode() {
        return (String) getAttributeInternal(AFFILIATECODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for AffiliateCode.
     * @param value value to set the AffiliateCode
     */
    public void setAffiliateCode(String value) {
        setAttributeInternal(AFFILIATECODE, value);
    }

    /**
     * Gets the attribute value for VerticalCode, using the alias name VerticalCode.
     * @return the value of VerticalCode
     */
    public String getVerticalCode() {
        return (String) getAttributeInternal(VERTICALCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for VerticalCode.
     * @param value value to set the VerticalCode
     */
    public void setVerticalCode(String value) {
        setAttributeInternal(VERTICALCODE, value);
    }

    /**
     * Gets the attribute value for HorizontalCode, using the alias name HorizontalCode.
     * @return the value of HorizontalCode
     */
    public String getHorizontalCode() {
        return (String) getAttributeInternal(HORIZONTALCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for HorizontalCode.
     * @param value value to set the HorizontalCode
     */
    public void setHorizontalCode(String value) {
        setAttributeInternal(HORIZONTALCODE, value);
    }

    /**
     * Gets the attribute value for BookCode, using the alias name BookCode.
     * @return the value of BookCode
     */
    public String getBookCode() {
        return (String) getAttributeInternal(BOOKCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for BookCode.
     * @param value value to set the BookCode
     */
    public void setBookCode(String value) {
        setAttributeInternal(BOOKCODE, value);
    }

    /**
     * Gets the attribute value for SourceSystem, using the alias name SourceSystem.
     * @return the value of SourceSystem
     */
    public String getSourceSystem() {
        return (String) getAttributeInternal(SOURCESYSTEM);
    }

    /**
     * Sets <code>value</code> as the attribute value for SourceSystem.
     * @param value value to set the SourceSystem
     */
    public void setSourceSystem(String value) {
        setAttributeInternal(SOURCESYSTEM, value);
    }

    /**
     * Gets the attribute value for SourceModule, using the alias name SourceModule.
     * @return the value of SourceModule
     */
    public String getSourceModule() {
        return (String) getAttributeInternal(SOURCEMODULE);
    }

    /**
     * Sets <code>value</code> as the attribute value for SourceModule.
     * @param value value to set the SourceModule
     */
    public void setSourceModule(String value) {
        setAttributeInternal(SOURCEMODULE, value);
    }

    /**
     * Gets the attribute value for SourceTable, using the alias name SourceTable.
     * @return the value of SourceTable
     */
    public String getSourceTable() {
        return (String) getAttributeInternal(SOURCETABLE);
    }

    /**
     * Sets <code>value</code> as the attribute value for SourceTable.
     * @param value value to set the SourceTable
     */
    public void setSourceTable(String value) {
        setAttributeInternal(SOURCETABLE, value);
    }

    /**
     * Gets the attribute value for TransactionPeriod, using the alias name TransactionPeriod.
     * @return the value of TransactionPeriod
     */
    public String getTransactionPeriod() {
        return (String) getAttributeInternal(TRANSACTIONPERIOD);
    }

    /**
     * Sets <code>value</code> as the attribute value for TransactionPeriod.
     * @param value value to set the TransactionPeriod
     */
    public void setTransactionPeriod(String value) {
        setAttributeInternal(TRANSACTIONPERIOD, value);
    }

    /**
     * Gets the attribute value for InputProvider, using the alias name InputProvider.
     * @return the value of InputProvider
     */
    public String getInputProvider() {
        return (String) getAttributeInternal(INPUTPROVIDER);
    }

    /**
     * Sets <code>value</code> as the attribute value for InputProvider.
     * @param value value to set the InputProvider
     */
    public void setInputProvider(String value) {
        setAttributeInternal(INPUTPROVIDER, value);
    }

    /**
     * Gets the attribute value for NatureOfExpense, using the alias name NatureOfExpense.
     * @return the value of NatureOfExpense
     */
    public String getNatureOfExpense() {
        return (String) getAttributeInternal(NATUREOFEXPENSE);
    }

    /**
     * Sets <code>value</code> as the attribute value for NatureOfExpense.
     * @param value value to set the NatureOfExpense
     */
    public void setNatureOfExpense(String value) {
        setAttributeInternal(NATUREOFEXPENSE, value);
    }

    /**
     * Gets the attribute value for JournalId, using the alias name JournalId.
     * @return the value of JournalId
     */
    public String getJournalId() {
        return (String) getAttributeInternal(JOURNALID);
    }

    /**
     * Sets <code>value</code> as the attribute value for JournalId.
     * @param value value to set the JournalId
     */
    public void setJournalId(String value) {
        setAttributeInternal(JOURNALID, value);
    }

    /**
     * Gets the attribute value for GlDate, using the alias name GlDate.
     * @return the value of GlDate
     */
    public Date getGlDate() {
        return (Date) getAttributeInternal(GLDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for GlDate.
     * @param value value to set the GlDate
     */
    public void setGlDate(Date value) {
        setAttributeInternal(GLDATE, value);
    }

    /**
     * Gets the attribute value for TransactionDate, using the alias name TransactionDate.
     * @return the value of TransactionDate
     */
    public Date getTransactionDate() {
        return (Date) getAttributeInternal(TRANSACTIONDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for TransactionDate.
     * @param value value to set the TransactionDate
     */
    public void setTransactionDate(Date value) {
        setAttributeInternal(TRANSACTIONDATE, value);
    }

    /**
     * Gets the attribute value for TransactionName, using the alias name TransactionName.
     * @return the value of TransactionName
     */
    public String getTransactionName() {
        return (String) getAttributeInternal(TRANSACTIONNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for TransactionName.
     * @param value value to set the TransactionName
     */
    public void setTransactionName(String value) {
        setAttributeInternal(TRANSACTIONNAME, value);
    }

    /**
     * Gets the attribute value for TransactionType, using the alias name TransactionType.
     * @return the value of TransactionType
     */
    public String getTransactionType() {
        return (String) getAttributeInternal(TRANSACTIONTYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for TransactionType.
     * @param value value to set the TransactionType
     */
    public void setTransactionType(String value) {
        setAttributeInternal(TRANSACTIONTYPE, value);
    }

    /**
     * Gets the attribute value for TransactionCategory, using the alias name TransactionCategory.
     * @return the value of TransactionCategory
     */
    public String getTransactionCategory() {
        return (String) getAttributeInternal(TRANSACTIONCATEGORY);
    }

    /**
     * Sets <code>value</code> as the attribute value for TransactionCategory.
     * @param value value to set the TransactionCategory
     */
    public void setTransactionCategory(String value) {
        setAttributeInternal(TRANSACTIONCATEGORY, value);
    }

    /**
     * Gets the attribute value for TransactionCurrency, using the alias name TransactionCurrency.
     * @return the value of TransactionCurrency
     */
    public String getTransactionCurrency() {
        return (String) getAttributeInternal(TRANSACTIONCURRENCY);
    }

    /**
     * Sets <code>value</code> as the attribute value for TransactionCurrency.
     * @param value value to set the TransactionCurrency
     */
    public void setTransactionCurrency(String value) {
        setAttributeInternal(TRANSACTIONCURRENCY, value);
    }

    /**
     * Gets the attribute value for TransactionAmount, using the alias name TransactionAmount.
     * @return the value of TransactionAmount
     */
    public BigDecimal getTransactionAmount() {
        return (BigDecimal) getAttributeInternal(TRANSACTIONAMOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for TransactionAmount.
     * @param value value to set the TransactionAmount
     */
    public void setTransactionAmount(BigDecimal value) {
        setAttributeInternal(TRANSACTIONAMOUNT, value);
    }

    /**
     * Gets the attribute value for FunctionalCurrency, using the alias name FunctionalCurrency.
     * @return the value of FunctionalCurrency
     */
    public String getFunctionalCurrency() {
        return (String) getAttributeInternal(FUNCTIONALCURRENCY);
    }

    /**
     * Sets <code>value</code> as the attribute value for FunctionalCurrency.
     * @param value value to set the FunctionalCurrency
     */
    public void setFunctionalCurrency(String value) {
        setAttributeInternal(FUNCTIONALCURRENCY, value);
    }

    /**
     * Gets the attribute value for ForeignExchangeRate, using the alias name ForeignExchangeRate.
     * @return the value of ForeignExchangeRate
     */
    public String getForeignExchangeRate() {
        return (String) getAttributeInternal(FOREIGNEXCHANGERATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for ForeignExchangeRate.
     * @param value value to set the ForeignExchangeRate
     */
    public void setForeignExchangeRate(String value) {
        setAttributeInternal(FOREIGNEXCHANGERATE, value);
    }

    /**
     * Gets the attribute value for ServiceCategory, using the alias name ServiceCategory.
     * @return the value of ServiceCategory
     */
    public String getServiceCategory() {
        return (String) getAttributeInternal(SERVICECATEGORY);
    }

    /**
     * Sets <code>value</code> as the attribute value for ServiceCategory.
     * @param value value to set the ServiceCategory
     */
    public void setServiceCategory(String value) {
        setAttributeInternal(SERVICECATEGORY, value);
    }

    /**
     * Gets the attribute value for AccountTreatment, using the alias name AccountTreatment.
     * @return the value of AccountTreatment
     */
    public String getAccountTreatment() {
        return (String) getAttributeInternal(ACCOUNTTREATMENT);
    }

    /**
     * Sets <code>value</code> as the attribute value for AccountTreatment.
     * @param value value to set the AccountTreatment
     */
    public void setAccountTreatment(String value) {
        setAttributeInternal(ACCOUNTTREATMENT, value);
    }

    /**
     * Gets the attribute value for ExtractedBy, using the alias name ExtractedBy.
     * @return the value of ExtractedBy
     */
    public String getExtractedBy() {
        return (String) getAttributeInternal(EXTRACTEDBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for ExtractedBy.
     * @param value value to set the ExtractedBy
     */
    public void setExtractedBy(String value) {
        setAttributeInternal(EXTRACTEDBY, value);
    }

    /**
     * Gets the attribute value for ExtractedDate, using the alias name ExtractedDate.
     * @return the value of ExtractedDate
     */
    public Date getExtractedDate() {
        return (Date) getAttributeInternal(EXTRACTEDDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for ExtractedDate.
     * @param value value to set the ExtractedDate
     */
    public void setExtractedDate(Date value) {
        setAttributeInternal(EXTRACTEDDATE, value);
    }

    /**
     * Gets the attribute value for TransactionStatus, using the alias name TransactionStatus.
     * @return the value of TransactionStatus
     */
    public String getTransactionStatus() {
        return (String) getAttributeInternal(TRANSACTIONSTATUS);
    }

    /**
     * Sets <code>value</code> as the attribute value for TransactionStatus.
     * @param value value to set the TransactionStatus
     */
    public void setTransactionStatus(String value) {
        setAttributeInternal(TRANSACTIONSTATUS, value);
    }

    /**
     * Gets the attribute value for CreatedDate, using the alias name CreatedDate.
     * @return the value of CreatedDate
     */
    public Date getCreatedDate() {
        return (Date) getAttributeInternal(CREATEDDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for CreatedDate.
     * @param value value to set the CreatedDate
     */
    public void setCreatedDate(Date value) {
        setAttributeInternal(CREATEDDATE, value);
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
     * Gets the attribute value for UpdatedDate, using the alias name UpdatedDate.
     * @return the value of UpdatedDate
     */
    public Date getUpdatedDate() {
        return (Date) getAttributeInternal(UPDATEDDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for UpdatedDate.
     * @param value value to set the UpdatedDate
     */
    public void setUpdatedDate(Date value) {
        setAttributeInternal(UPDATEDDATE, value);
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
     * Gets the attribute value for AllocationBasis, using the alias name AllocationBasis.
     * @return the value of AllocationBasis
     */
    public String getAllocationBasis() {
        return (String) getAttributeInternal(ALLOCATIONBASIS);
    }

    /**
     * Sets <code>value</code> as the attribute value for AllocationBasis.
     * @param value value to set the AllocationBasis
     */
    public void setAllocationBasis(String value) {
        setAttributeInternal(ALLOCATIONBASIS, value);
    }

    /**
     * Gets the attribute value for PeoplesoftTransactionId, using the alias name PeoplesoftTransactionId.
     * @return the value of PeoplesoftTransactionId
     */
    public String getPeoplesoftTransactionId() {
        return (String) getAttributeInternal(PEOPLESOFTTRANSACTIONID);
    }

    /**
     * Sets <code>value</code> as the attribute value for PeoplesoftTransactionId.
     * @param value value to set the PeoplesoftTransactionId
     */
    public void setPeoplesoftTransactionId(String value) {
        setAttributeInternal(PEOPLESOFTTRANSACTIONID, value);
    }

    /**
     * Gets the attribute value for VendorId, using the alias name VendorId.
     * @return the value of VendorId
     */
    public String getVendorId() {
        return (String) getAttributeInternal(VENDORID);
    }

    /**
     * Sets <code>value</code> as the attribute value for VendorId.
     * @param value value to set the VendorId
     */
    public void setVendorId(String value) {
        setAttributeInternal(VENDORID, value);
    }

    /**
     * Gets the attribute value for PoNumber, using the alias name PoNumber.
     * @return the value of PoNumber
     */
    public String getPoNumber() {
        return (String) getAttributeInternal(PONUMBER);
    }

    /**
     * Sets <code>value</code> as the attribute value for PoNumber.
     * @param value value to set the PoNumber
     */
    public void setPoNumber(String value) {
        setAttributeInternal(PONUMBER, value);
    }

    /**
     * Gets the attribute value for VoucherId, using the alias name VoucherId.
     * @return the value of VoucherId
     */
    public String getVoucherId() {
        return (String) getAttributeInternal(VOUCHERID);
    }

    /**
     * Sets <code>value</code> as the attribute value for VoucherId.
     * @param value value to set the VoucherId
     */
    public void setVoucherId(String value) {
        setAttributeInternal(VOUCHERID, value);
    }

    /**
     * Gets the attribute value for VoucherNo, using the alias name VoucherNo.
     * @return the value of VoucherNo
     */
    public String getVoucherNo() {
        return (String) getAttributeInternal(VOUCHERNO);
    }

    /**
     * Sets <code>value</code> as the attribute value for VoucherNo.
     * @param value value to set the VoucherNo
     */
    public void setVoucherNo(String value) {
        setAttributeInternal(VOUCHERNO, value);
    }


    /**
     * Gets the attribute value for IgstAmount, using the alias name IgstAmount.
     * @return the value of IgstAmount
     */
    public String getIgstAmount() {
        return (String) getAttributeInternal(IGSTAMOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for IgstAmount.
     * @param value value to set the IgstAmount
     */
    public void setIgstAmount(String value) {
        setAttributeInternal(IGSTAMOUNT, value);
    }

    /**
     * Gets the attribute value for CgstAmount, using the alias name CgstAmount.
     * @return the value of CgstAmount
     */
    public String getCgstAmount() {
        return (String) getAttributeInternal(CGSTAMOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for CgstAmount.
     * @param value value to set the CgstAmount
     */
    public void setCgstAmount(String value) {
        setAttributeInternal(CGSTAMOUNT, value);
    }

    /**
     * Gets the attribute value for SgstAmount, using the alias name SgstAmount.
     * @return the value of SgstAmount
     */
    public String getSgstAmount() {
        return (String) getAttributeInternal(SGSTAMOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for SgstAmount.
     * @param value value to set the SgstAmount
     */
    public void setSgstAmount(String value) {
        setAttributeInternal(SGSTAMOUNT, value);
    }


    /**
     * Gets the attribute value for Duedate, using the alias name Duedate.
     * @return the value of Duedate
     */
    public Date getDuedate() {
        return (Date) getAttributeInternal(DUEDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for Duedate.
     * @param value value to set the Duedate
     */
    public void setDuedate(Date value) {
        setAttributeInternal(DUEDATE, value);
    }

    /**
     * Gets the attribute value for HoldReason, using the alias name HoldReason.
     * @return the value of HoldReason
     */
    public String getHoldReason() {
        return (String) getAttributeInternal(HOLDREASON);
    }

    /**
     * Sets <code>value</code> as the attribute value for HoldReason.
     * @param value value to set the HoldReason
     */
    public void setHoldReason(String value) {
        setAttributeInternal(HOLDREASON, value);
    }

    /**
     * Gets the attribute value for HoldRemarks, using the alias name HoldRemarks.
     * @return the value of HoldRemarks
     */
    public String getHoldRemarks() {
        return (String) getAttributeInternal(HOLDREMARKS);
    }

    /**
     * Sets <code>value</code> as the attribute value for HoldRemarks.
     * @param value value to set the HoldRemarks
     */
    public void setHoldRemarks(String value) {
        setAttributeInternal(HOLDREMARKS, value);
    }

    /**
     * Gets the attribute value for Holdby, using the alias name Holdby.
     * @return the value of Holdby
     */
    public String getHoldby() {
        return (String) getAttributeInternal(HOLDBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for Holdby.
     * @param value value to set the Holdby
     */
    public void setHoldby(String value) {
        setAttributeInternal(HOLDBY, value);
    }

    /**
     * Gets the attribute value for Holdon, using the alias name Holdon.
     * @return the value of Holdon
     */
    public Date getHoldon() {
        return (Date) getAttributeInternal(HOLDON);
    }

    /**
     * Sets <code>value</code> as the attribute value for Holdon.
     * @param value value to set the Holdon
     */
    public void setHoldon(Date value) {
        setAttributeInternal(HOLDON, value);
    }

    /**
     * Gets the attribute value for ReleaseAction, using the alias name ReleaseAction.
     * @return the value of ReleaseAction
     */
    public String getReleaseAction() {
        return (String) getAttributeInternal(RELEASEACTION);
    }

    /**
     * Sets <code>value</code> as the attribute value for ReleaseAction.
     * @param value value to set the ReleaseAction
     */
    public void setReleaseAction(String value) {
        setAttributeInternal(RELEASEACTION, value);
    }

    /**
     * Gets the attribute value for ReleaseRemarks, using the alias name ReleaseRemarks.
     * @return the value of ReleaseRemarks
     */
    public String getReleaseRemarks() {
        return (String) getAttributeInternal(RELEASEREMARKS);
    }

    /**
     * Sets <code>value</code> as the attribute value for ReleaseRemarks.
     * @param value value to set the ReleaseRemarks
     */
    public void setReleaseRemarks(String value) {
        setAttributeInternal(RELEASEREMARKS, value);
    }

    /**
     * Gets the attribute value for Releasedby, using the alias name Releasedby.
     * @return the value of Releasedby
     */
    public String getReleasedby() {
        return (String) getAttributeInternal(RELEASEDBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for Releasedby.
     * @param value value to set the Releasedby
     */
    public void setReleasedby(String value) {
        setAttributeInternal(RELEASEDBY, value);
    }

    /**
     * Gets the attribute value for Releasedon, using the alias name Releasedon.
     * @return the value of Releasedon
     */
    public Date getReleasedon() {
        return (Date) getAttributeInternal(RELEASEDON);
    }

    /**
     * Sets <code>value</code> as the attribute value for Releasedon.
     * @param value value to set the Releasedon
     */
    public void setReleasedon(Date value) {
        setAttributeInternal(RELEASEDON, value);
    }

    /**
     * @return the associated entity oracle.jbo.RowIterator.
     */
    public RowIterator getSgsOtrTargetEO() {
        return (RowIterator) getAttributeInternal(SGSOTRTARGETEO);
    }


    /**
     * @param tbcaId key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(String tbcaId) {
        return new Key(new Object[] { tbcaId });
    }

    /**
     * Add attribute defaulting logic in this method.
     * @param attributeList list of attribute names/values to initialize the row
     */
    protected void create(AttributeList attributeList) {
        super.create(attributeList);
        
        
    }
}

