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
// ---    Mon Jan 30 08:34:32 IST 2023
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class SgsFixedAssetsTxnEOImpl extends EntityImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        FstId,
        TransferDate,
        TransactionCategory,
        PeoplesoftAssetNo,
        AssetDescription,
        PeoplesoftTransactionId,
        FromLegalEntity,
        FromBusinessUnit,
        FromOperatingUnit,
        FromJobCode,
        FromDeptId,
        FromGlAccount,
        ToBusinessUnit,
        ToOperatingUnit,
        ToJobCode,
        ToDeptId,
        ToGlAccount,
        TransactionCurrency,
        OriginalCost,
        DepAmount,
        NetBookValue,
        GstVatRate,
        GstVatAmount,
        TransferValue,
        InvoicingCurrency,
        InvoiceValue,
        Status,
        AccountingEntry,
        Approval,
        CreatedDate,
        CreatedBy,
        UpdatedDate,
        UpdatedBy,
        ACCDATE,
        GSTAMOUNT,
        GSTRATE,
        IGSTAMOUNT,
        SGTAMOUNT,
        VATAMOUNT,
        VATRATE,
        Selected,
        ATTACHMENT,
        ATTRIBUTE1,
        ATTRIBUTE2;
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
    private static final ADFLogger LOG = ADFLogger.createADFLogger(SgsFixedAssetsTxnEOImpl.class);


    public static final int FSTID = AttributesEnum.FstId.index();
    public static final int TRANSFERDATE = AttributesEnum.TransferDate.index();
    public static final int TRANSACTIONCATEGORY = AttributesEnum.TransactionCategory.index();
    public static final int PEOPLESOFTASSETNO = AttributesEnum.PeoplesoftAssetNo.index();
    public static final int ASSETDESCRIPTION = AttributesEnum.AssetDescription.index();
    public static final int PEOPLESOFTTRANSACTIONID = AttributesEnum.PeoplesoftTransactionId.index();
    public static final int FROMLEGALENTITY = AttributesEnum.FromLegalEntity.index();
    public static final int FROMBUSINESSUNIT = AttributesEnum.FromBusinessUnit.index();
    public static final int FROMOPERATINGUNIT = AttributesEnum.FromOperatingUnit.index();
    public static final int FROMJOBCODE = AttributesEnum.FromJobCode.index();
    public static final int FROMDEPTID = AttributesEnum.FromDeptId.index();
    public static final int FROMGLACCOUNT = AttributesEnum.FromGlAccount.index();
    public static final int TOBUSINESSUNIT = AttributesEnum.ToBusinessUnit.index();
    public static final int TOOPERATINGUNIT = AttributesEnum.ToOperatingUnit.index();
    public static final int TOJOBCODE = AttributesEnum.ToJobCode.index();
    public static final int TODEPTID = AttributesEnum.ToDeptId.index();
    public static final int TOGLACCOUNT = AttributesEnum.ToGlAccount.index();
    public static final int TRANSACTIONCURRENCY = AttributesEnum.TransactionCurrency.index();
    public static final int ORIGINALCOST = AttributesEnum.OriginalCost.index();
    public static final int DEPAMOUNT = AttributesEnum.DepAmount.index();
    public static final int NETBOOKVALUE = AttributesEnum.NetBookValue.index();
    public static final int GSTVATRATE = AttributesEnum.GstVatRate.index();
    public static final int GSTVATAMOUNT = AttributesEnum.GstVatAmount.index();
    public static final int TRANSFERVALUE = AttributesEnum.TransferValue.index();
    public static final int INVOICINGCURRENCY = AttributesEnum.InvoicingCurrency.index();
    public static final int INVOICEVALUE = AttributesEnum.InvoiceValue.index();
    public static final int STATUS = AttributesEnum.Status.index();
    public static final int ACCOUNTINGENTRY = AttributesEnum.AccountingEntry.index();
    public static final int APPROVAL = AttributesEnum.Approval.index();
    public static final int CREATEDDATE = AttributesEnum.CreatedDate.index();
    public static final int CREATEDBY = AttributesEnum.CreatedBy.index();
    public static final int UPDATEDDATE = AttributesEnum.UpdatedDate.index();
    public static final int UPDATEDBY = AttributesEnum.UpdatedBy.index();
    public static final int ACCDATE = AttributesEnum.ACCDATE.index();
    public static final int GSTAMOUNT = AttributesEnum.GSTAMOUNT.index();
    public static final int GSTRATE = AttributesEnum.GSTRATE.index();
    public static final int IGSTAMOUNT = AttributesEnum.IGSTAMOUNT.index();
    public static final int SGTAMOUNT = AttributesEnum.SGTAMOUNT.index();
    public static final int VATAMOUNT = AttributesEnum.VATAMOUNT.index();
    public static final int VATRATE = AttributesEnum.VATRATE.index();
    public static final int SELECTED = AttributesEnum.Selected.index();
    public static final int ATTACHMENT = AttributesEnum.ATTACHMENT.index();
    public static final int ATTRIBUTE1 = AttributesEnum.ATTRIBUTE1.index();
    public static final int ATTRIBUTE2 = AttributesEnum.ATTRIBUTE2.index();

    /**
     * This is the default constructor (do not remove).
     */
    public SgsFixedAssetsTxnEOImpl() {
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("com.sgs.ics.model.bc.entity.SgsFixedAssetsTxnEO");
    }


    /**
     * Gets the attribute value for FstId, using the alias name FstId.
     * @return the value of FstId
     */
    public String getFstId() {
        return (String) getAttributeInternal(FSTID);
    }

    /**
     * Sets <code>value</code> as the attribute value for FstId.
     * @param value value to set the FstId
     */
    public void setFstId(String value) {
        setAttributeInternal(FSTID, value);
    }

    /**
     * Gets the attribute value for TransferDate, using the alias name TransferDate.
     * @return the value of TransferDate
     */
    public Date getTransferDate() {
        return (Date) getAttributeInternal(TRANSFERDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for TransferDate.
     * @param value value to set the TransferDate
     */
    public void setTransferDate(Date value) {
        setAttributeInternal(TRANSFERDATE, value);
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
     * Gets the attribute value for PeoplesoftAssetNo, using the alias name PeoplesoftAssetNo.
     * @return the value of PeoplesoftAssetNo
     */
    public String getPeoplesoftAssetNo() {
        return (String) getAttributeInternal(PEOPLESOFTASSETNO);
    }

    /**
     * Sets <code>value</code> as the attribute value for PeoplesoftAssetNo.
     * @param value value to set the PeoplesoftAssetNo
     */
    public void setPeoplesoftAssetNo(String value) {
        setAttributeInternal(PEOPLESOFTASSETNO, value);
    }

    /**
     * Gets the attribute value for AssetDescription, using the alias name AssetDescription.
     * @return the value of AssetDescription
     */
    public String getAssetDescription() {
        return (String) getAttributeInternal(ASSETDESCRIPTION);
    }

    /**
     * Sets <code>value</code> as the attribute value for AssetDescription.
     * @param value value to set the AssetDescription
     */
    public void setAssetDescription(String value) {
        setAttributeInternal(ASSETDESCRIPTION, value);
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
     * Gets the attribute value for FromLegalEntity, using the alias name FromLegalEntity.
     * @return the value of FromLegalEntity
     */
    public String getFromLegalEntity() {
        return (String) getAttributeInternal(FROMLEGALENTITY);
    }

    /**
     * Sets <code>value</code> as the attribute value for FromLegalEntity.
     * @param value value to set the FromLegalEntity
     */
    public void setFromLegalEntity(String value) {
        setAttributeInternal(FROMLEGALENTITY, value);
    }

    /**
     * Gets the attribute value for FromBusinessUnit, using the alias name FromBusinessUnit.
     * @return the value of FromBusinessUnit
     */
    public String getFromBusinessUnit() {
        return (String) getAttributeInternal(FROMBUSINESSUNIT);
    }

    /**
     * Sets <code>value</code> as the attribute value for FromBusinessUnit.
     * @param value value to set the FromBusinessUnit
     */
    public void setFromBusinessUnit(String value) {
        setAttributeInternal(FROMBUSINESSUNIT, value);
    }

    /**
     * Gets the attribute value for FromOperatingUnit, using the alias name FromOperatingUnit.
     * @return the value of FromOperatingUnit
     */
    public String getFromOperatingUnit() {
        return (String) getAttributeInternal(FROMOPERATINGUNIT);
    }

    /**
     * Sets <code>value</code> as the attribute value for FromOperatingUnit.
     * @param value value to set the FromOperatingUnit
     */
    public void setFromOperatingUnit(String value) {
        setAttributeInternal(FROMOPERATINGUNIT, value);
    }

    /**
     * Gets the attribute value for FromJobCode, using the alias name FromJobCode.
     * @return the value of FromJobCode
     */
    public String getFromJobCode() {
        return (String) getAttributeInternal(FROMJOBCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for FromJobCode.
     * @param value value to set the FromJobCode
     */
    public void setFromJobCode(String value) {
        setAttributeInternal(FROMJOBCODE, value);
    }

    /**
     * Gets the attribute value for FromDeptId, using the alias name FromDeptId.
     * @return the value of FromDeptId
     */
    public String getFromDeptId() {
        return (String) getAttributeInternal(FROMDEPTID);
    }

    /**
     * Sets <code>value</code> as the attribute value for FromDeptId.
     * @param value value to set the FromDeptId
     */
    public void setFromDeptId(String value) {
        setAttributeInternal(FROMDEPTID, value);
    }

    /**
     * Gets the attribute value for FromGlAccount, using the alias name FromGlAccount.
     * @return the value of FromGlAccount
     */
    public String getFromGlAccount() {
        return (String) getAttributeInternal(FROMGLACCOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for FromGlAccount.
     * @param value value to set the FromGlAccount
     */
    public void setFromGlAccount(String value) {
        setAttributeInternal(FROMGLACCOUNT, value);
    }

    /**
     * Gets the attribute value for ToBusinessUnit, using the alias name ToBusinessUnit.
     * @return the value of ToBusinessUnit
     */
    public String getToBusinessUnit() {
        return (String) getAttributeInternal(TOBUSINESSUNIT);
    }

    /**
     * Sets <code>value</code> as the attribute value for ToBusinessUnit.
     * @param value value to set the ToBusinessUnit
     */
    public void setToBusinessUnit(String value) {
        setAttributeInternal(TOBUSINESSUNIT, value);
    }

    /**
     * Gets the attribute value for ToOperatingUnit, using the alias name ToOperatingUnit.
     * @return the value of ToOperatingUnit
     */
    public String getToOperatingUnit() {
        return (String) getAttributeInternal(TOOPERATINGUNIT);
    }

    /**
     * Sets <code>value</code> as the attribute value for ToOperatingUnit.
     * @param value value to set the ToOperatingUnit
     */
    public void setToOperatingUnit(String value) {
        setAttributeInternal(TOOPERATINGUNIT, value);
    }

    /**
     * Gets the attribute value for ToJobCode, using the alias name ToJobCode.
     * @return the value of ToJobCode
     */
    public String getToJobCode() {
        return (String) getAttributeInternal(TOJOBCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for ToJobCode.
     * @param value value to set the ToJobCode
     */
    public void setToJobCode(String value) {
        setAttributeInternal(TOJOBCODE, value);
    }

    /**
     * Gets the attribute value for ToDeptId, using the alias name ToDeptId.
     * @return the value of ToDeptId
     */
    public String getToDeptId() {
        return (String) getAttributeInternal(TODEPTID);
    }

    /**
     * Sets <code>value</code> as the attribute value for ToDeptId.
     * @param value value to set the ToDeptId
     */
    public void setToDeptId(String value) {
        setAttributeInternal(TODEPTID, value);
    }

    /**
     * Gets the attribute value for ToGlAccount, using the alias name ToGlAccount.
     * @return the value of ToGlAccount
     */
    public String getToGlAccount() {
        return (String) getAttributeInternal(TOGLACCOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for ToGlAccount.
     * @param value value to set the ToGlAccount
     */
    public void setToGlAccount(String value) {
        setAttributeInternal(TOGLACCOUNT, value);
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
     * Gets the attribute value for OriginalCost, using the alias name OriginalCost.
     * @return the value of OriginalCost
     */
    public BigDecimal getOriginalCost() {
        return (BigDecimal) getAttributeInternal(ORIGINALCOST);
    }

    /**
     * Sets <code>value</code> as the attribute value for OriginalCost.
     * @param value value to set the OriginalCost
     */
    public void setOriginalCost(BigDecimal value) {
        setAttributeInternal(ORIGINALCOST, value);
    }

    /**
     * Gets the attribute value for DepAmount, using the alias name DepAmount.
     * @return the value of DepAmount
     */
    public BigDecimal getDepAmount() {
        return (BigDecimal) getAttributeInternal(DEPAMOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for DepAmount.
     * @param value value to set the DepAmount
     */
    public void setDepAmount(BigDecimal value) {
        setAttributeInternal(DEPAMOUNT, value);
    }

    /**
     * Gets the attribute value for NetBookValue, using the alias name NetBookValue.
     * @return the value of NetBookValue
     */
    public BigDecimal getNetBookValue() {
        return (BigDecimal) getAttributeInternal(NETBOOKVALUE);
    }

    /**
     * Sets <code>value</code> as the attribute value for NetBookValue.
     * @param value value to set the NetBookValue
     */
    public void setNetBookValue(BigDecimal value) {
        setAttributeInternal(NETBOOKVALUE, value);
    }

    /**
     * Gets the attribute value for GstVatRate, using the alias name GstVatRate.
     * @return the value of GstVatRate
     */
    public BigDecimal getGstVatRate() {
        return (BigDecimal) getAttributeInternal(GSTVATRATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for GstVatRate.
     * @param value value to set the GstVatRate
     */
    public void setGstVatRate(BigDecimal value) {
        setAttributeInternal(GSTVATRATE, value);
    }

    /**
     * Gets the attribute value for GstVatAmount, using the alias name GstVatAmount.
     * @return the value of GstVatAmount
     */
    public BigDecimal getGstVatAmount() {
        return (BigDecimal) getAttributeInternal(GSTVATAMOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for GstVatAmount.
     * @param value value to set the GstVatAmount
     */
    public void setGstVatAmount(BigDecimal value) {
        setAttributeInternal(GSTVATAMOUNT, value);
    }

    /**
     * Gets the attribute value for TransferValue, using the alias name TransferValue.
     * @return the value of TransferValue
     */
    public BigDecimal getTransferValue() {
        return (BigDecimal) getAttributeInternal(TRANSFERVALUE);
    }

    /**
     * Sets <code>value</code> as the attribute value for TransferValue.
     * @param value value to set the TransferValue
     */
    public void setTransferValue(BigDecimal value) {
        setAttributeInternal(TRANSFERVALUE, value);
    }

    /**
     * Gets the attribute value for InvoicingCurrency, using the alias name InvoicingCurrency.
     * @return the value of InvoicingCurrency
     */
    public String getInvoicingCurrency() {
        return (String) getAttributeInternal(INVOICINGCURRENCY);
    }

    /**
     * Sets <code>value</code> as the attribute value for InvoicingCurrency.
     * @param value value to set the InvoicingCurrency
     */
    public void setInvoicingCurrency(String value) {
        setAttributeInternal(INVOICINGCURRENCY, value);
    }

    /**
     * Gets the attribute value for InvoiceValue, using the alias name InvoiceValue.
     * @return the value of InvoiceValue
     */
    public BigDecimal getInvoiceValue() {
        return (BigDecimal) getAttributeInternal(INVOICEVALUE);
    }

    /**
     * Sets <code>value</code> as the attribute value for InvoiceValue.
     * @param value value to set the InvoiceValue
     */
    public void setInvoiceValue(BigDecimal value) {
        setAttributeInternal(INVOICEVALUE, value);
    }

    /**
     * Gets the attribute value for Status, using the alias name Status.
     * @return the value of Status
     */
    public String getStatus() {
        return (String) getAttributeInternal(STATUS);
    }

    /**
     * Sets <code>value</code> as the attribute value for Status.
     * @param value value to set the Status
     */
    public void setStatus(String value) {
        setAttributeInternal(STATUS, value);
    }

    /**
     * Gets the attribute value for AccountingEntry, using the alias name AccountingEntry.
     * @return the value of AccountingEntry
     */
    public String getAccountingEntry() {
        return (String) getAttributeInternal(ACCOUNTINGENTRY);
    }

    /**
     * Sets <code>value</code> as the attribute value for AccountingEntry.
     * @param value value to set the AccountingEntry
     */
    public void setAccountingEntry(String value) {
        setAttributeInternal(ACCOUNTINGENTRY, value);
    }

    /**
     * Gets the attribute value for Approval, using the alias name Approval.
     * @return the value of Approval
     */
    public String getApproval() {
        return (String) getAttributeInternal(APPROVAL);
    }

    /**
     * Sets <code>value</code> as the attribute value for Approval.
     * @param value value to set the Approval
     */
    public void setApproval(String value) {
        setAttributeInternal(APPROVAL, value);
    }

    /**
     * Gets the attribute value for CreatedDate, using the alias name CreatedDate.
     * @return the value of CreatedDate
     */
    public Date getCreatedDate() {
        return (Date) getAttributeInternal(CREATEDDATE);
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
     * Gets the attribute value for ACCDATE, using the alias name ACCDATE.
     * @return the value of ACCDATE
     */
    public Date getACCDATE() {
        return (Date) getAttributeInternal(ACCDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for ACCDATE.
     * @param value value to set the ACCDATE
     */
    public void setACCDATE(Date value) {
        setAttributeInternal(ACCDATE, value);
    }

    /**
     * Gets the attribute value for GSTAMOUNT, using the alias name GSTAMOUNT.
     * @return the value of GSTAMOUNT
     */
    public BigDecimal getGSTAMOUNT() {
        return (BigDecimal) getAttributeInternal(GSTAMOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for GSTAMOUNT.
     * @param value value to set the GSTAMOUNT
     */
    public void setGSTAMOUNT(BigDecimal value) {
        setAttributeInternal(GSTAMOUNT, value);
    }

    /**
     * Gets the attribute value for GSTRATE, using the alias name GSTRATE.
     * @return the value of GSTRATE
     */
    public BigDecimal getGSTRATE() {
        return (BigDecimal) getAttributeInternal(GSTRATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for GSTRATE.
     * @param value value to set the GSTRATE
     */
    public void setGSTRATE(BigDecimal value) {
        setAttributeInternal(GSTRATE, value);
    }

    /**
     * Gets the attribute value for IGSTAMOUNT, using the alias name IGSTAMOUNT.
     * @return the value of IGSTAMOUNT
     */
    public BigDecimal getIGSTAMOUNT() {
        return (BigDecimal) getAttributeInternal(IGSTAMOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for IGSTAMOUNT.
     * @param value value to set the IGSTAMOUNT
     */
    public void setIGSTAMOUNT(BigDecimal value) {
        setAttributeInternal(IGSTAMOUNT, value);
    }

    /**
     * Gets the attribute value for SGTAMOUNT, using the alias name SGTAMOUNT.
     * @return the value of SGTAMOUNT
     */
    public BigDecimal getSGTAMOUNT() {
        return (BigDecimal) getAttributeInternal(SGTAMOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for SGTAMOUNT.
     * @param value value to set the SGTAMOUNT
     */
    public void setSGTAMOUNT(BigDecimal value) {
        setAttributeInternal(SGTAMOUNT, value);
    }

    /**
     * Gets the attribute value for VATAMOUNT, using the alias name VATAMOUNT.
     * @return the value of VATAMOUNT
     */
    public BigDecimal getVATAMOUNT() {
        return (BigDecimal) getAttributeInternal(VATAMOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for VATAMOUNT.
     * @param value value to set the VATAMOUNT
     */
    public void setVATAMOUNT(BigDecimal value) {
        setAttributeInternal(VATAMOUNT, value);
    }

    /**
     * Gets the attribute value for VATRATE, using the alias name VATRATE.
     * @return the value of VATRATE
     */
    public BigDecimal getVATRATE() {
        return (BigDecimal) getAttributeInternal(VATRATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for VATRATE.
     * @param value value to set the VATRATE
     */
    public void setVATRATE(BigDecimal value) {
        setAttributeInternal(VATRATE, value);
    }


    /**
     * Gets the attribute value for Selected, using the alias name Selected.
     * @return the value of Selected
     */
    public Boolean getSelected() {
        return (Boolean) getAttributeInternal(SELECTED);
    }

    /**
     * Sets <code>value</code> as the attribute value for Selected.
     * @param value value to set the Selected
     */
    public void setSelected(Boolean value) {
        setAttributeInternal(SELECTED, value);
    }

    /**
     * Gets the attribute value for ATTACHMENT, using the alias name ATTACHMENT.
     * @return the value of ATTACHMENT
     */
    public String getATTACHMENT() {
        return (String) getAttributeInternal(ATTACHMENT);
    }

    /**
     * Sets <code>value</code> as the attribute value for ATTACHMENT.
     * @param value value to set the ATTACHMENT
     */
    public void setATTACHMENT(String value) {
        setAttributeInternal(ATTACHMENT, value);
    }


    /**
     * Gets the attribute value for ATTRIBUTE1, using the alias name ATTRIBUTE1.
     * @return the value of ATTRIBUTE1
     */
    public String getATTRIBUTE1() {
        return (String) getAttributeInternal(ATTRIBUTE1);
    }

    /**
     * Sets <code>value</code> as the attribute value for ATTRIBUTE1.
     * @param value value to set the ATTRIBUTE1
     */
    public void setATTRIBUTE1(String value) {
        setAttributeInternal(ATTRIBUTE1, value);
    }

    /**
     * Gets the attribute value for ATTRIBUTE2, using the alias name ATTRIBUTE2.
     * @return the value of ATTRIBUTE2
     */
    public String getATTRIBUTE2() {
        return (String) getAttributeInternal(ATTRIBUTE2);
    }

    /**
     * Sets <code>value</code> as the attribute value for ATTRIBUTE2.
     * @param value value to set the ATTRIBUTE2
     */
    public void setATTRIBUTE2(String value) {
        setAttributeInternal(ATTRIBUTE2, value);
    }

    /**
     * @param fstId key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(String fstId) {
        return new Key(new Object[] { fstId });
    }

    /**
     * Add attribute defaulting logic in this method.
     * @param attributeList list of attribute names/values to initialize the row
     */
    protected void create(AttributeList attributeList) {
        super.create(attributeList);
        try {
            SGSAppModuleImpl am = new SGSAppModuleImpl();
            //setCostIdentificationId("SGSID"+Math.random());
//            setFstId(am.getDBSequence1("SEQ_SEQ_SGS_FIXED_ASSETS_TXN"));
            CommonUtils util = new CommonUtils();
            Object user = (Object) util.getSessionScopeValue("_username").toString();
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

