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
import oracle.jbo.server.TransactionEvent;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Jan 04 12:17:14 IST 2023
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class SgsIcInvoiceHeaderEOImpl extends EntityImpl {
    private static final ADFLogger LOG = ADFLogger.createADFLogger(SgsIcInvoiceHeaderEOImpl.class);
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        InvoiceSeqNo,
        Period,
        TpaId,
        TransactionCategory,
        TransactionType,
        IuVendor,
        SourceBu,
        SourceCurrency,
        SourceAmount,
        IuCustomer,
        TargetBu,
        AccountingTreatment,
        MarkupRate,
        TargetCurrency,
        InvoiceHeaderAmount,
        MarkupAmount,
        BaseAmountBeforeTax,
        GstVatRate,
        GstVatAmount,
        TotalAmountAfterTax,
        WhtRate,
        WhtAmount,
        TransactionStatus,
        PaymentStatus,
        PaidAmount,
        ReferenceVoucherNum,
        ReferenceInvoiceNum,
        ReviewAccEntries,
        InputProvider,
        CreatedDate,
        CreatedBy,
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
        ALLOCATEDHEADERAMOUNT,
        GSTAMNT,
        GSTRATE,
        VATAMNT,
        VATRATE,
        REVERSALREASON,
        NATUREOFEXPENSE,
        SgsIcInvoiceLineEO;
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


    public static final int INVOICESEQNO = AttributesEnum.InvoiceSeqNo.index();
    public static final int PERIOD = AttributesEnum.Period.index();
    public static final int TPAID = AttributesEnum.TpaId.index();
    public static final int TRANSACTIONCATEGORY = AttributesEnum.TransactionCategory.index();
    public static final int TRANSACTIONTYPE = AttributesEnum.TransactionType.index();
    public static final int IUVENDOR = AttributesEnum.IuVendor.index();
    public static final int SOURCEBU = AttributesEnum.SourceBu.index();
    public static final int SOURCECURRENCY = AttributesEnum.SourceCurrency.index();
    public static final int SOURCEAMOUNT = AttributesEnum.SourceAmount.index();
    public static final int IUCUSTOMER = AttributesEnum.IuCustomer.index();
    public static final int TARGETBU = AttributesEnum.TargetBu.index();
    public static final int ACCOUNTINGTREATMENT = AttributesEnum.AccountingTreatment.index();
    public static final int MARKUPRATE = AttributesEnum.MarkupRate.index();
    public static final int TARGETCURRENCY = AttributesEnum.TargetCurrency.index();
    public static final int INVOICEHEADERAMOUNT = AttributesEnum.InvoiceHeaderAmount.index();
    public static final int MARKUPAMOUNT = AttributesEnum.MarkupAmount.index();
    public static final int BASEAMOUNTBEFORETAX = AttributesEnum.BaseAmountBeforeTax.index();
    public static final int GSTVATRATE = AttributesEnum.GstVatRate.index();
    public static final int GSTVATAMOUNT = AttributesEnum.GstVatAmount.index();
    public static final int TOTALAMOUNTAFTERTAX = AttributesEnum.TotalAmountAfterTax.index();
    public static final int WHTRATE = AttributesEnum.WhtRate.index();
    public static final int WHTAMOUNT = AttributesEnum.WhtAmount.index();
    public static final int TRANSACTIONSTATUS = AttributesEnum.TransactionStatus.index();
    public static final int PAYMENTSTATUS = AttributesEnum.PaymentStatus.index();
    public static final int PAIDAMOUNT = AttributesEnum.PaidAmount.index();
    public static final int REFERENCEVOUCHERNUM = AttributesEnum.ReferenceVoucherNum.index();
    public static final int REFERENCEINVOICENUM = AttributesEnum.ReferenceInvoiceNum.index();
    public static final int REVIEWACCENTRIES = AttributesEnum.ReviewAccEntries.index();
    public static final int INPUTPROVIDER = AttributesEnum.InputProvider.index();
    public static final int CREATEDDATE = AttributesEnum.CreatedDate.index();
    public static final int CREATEDBY = AttributesEnum.CreatedBy.index();
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
    public static final int ALLOCATEDHEADERAMOUNT = AttributesEnum.ALLOCATEDHEADERAMOUNT.index();
    public static final int GSTAMNT = AttributesEnum.GSTAMNT.index();
    public static final int GSTRATE = AttributesEnum.GSTRATE.index();
    public static final int VATAMNT = AttributesEnum.VATAMNT.index();
    public static final int VATRATE = AttributesEnum.VATRATE.index();
    public static final int REVERSALREASON = AttributesEnum.REVERSALREASON.index();
    public static final int NATUREOFEXPENSE = AttributesEnum.NATUREOFEXPENSE.index();
    public static final int SGSICINVOICELINEEO = AttributesEnum.SgsIcInvoiceLineEO.index();

    /**
     * This is the default constructor (do not remove).
     */
    public SgsIcInvoiceHeaderEOImpl() {
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("com.sgs.ics.model.bc.entity.SgsIcInvoiceHeaderEO");
    }


    /**
     * Gets the attribute value for InvoiceSeqNo, using the alias name InvoiceSeqNo.
     * @return the value of InvoiceSeqNo
     */
    public String getInvoiceSeqNo() {
        return (String) getAttributeInternal(INVOICESEQNO);
    }

    /**
     * Sets <code>value</code> as the attribute value for InvoiceSeqNo.
     * @param value value to set the InvoiceSeqNo
     */
    public void setInvoiceSeqNo(String value) {
        setAttributeInternal(INVOICESEQNO, value);
    }

    /**
     * Gets the attribute value for Period, using the alias name Period.
     * @return the value of Period
     */
    public Date getPeriod() {
        return (Date) getAttributeInternal(PERIOD);
    }

    /**
     * Sets <code>value</code> as the attribute value for Period.
     * @param value value to set the Period
     */
    public void setPeriod(Date value) {
        setAttributeInternal(PERIOD, value);
    }

    /**
     * Gets the attribute value for TpaId, using the alias name TpaId.
     * @return the value of TpaId
     */
    public String getTpaId() {
        return (String) getAttributeInternal(TPAID);
    }

    /**
     * Sets <code>value</code> as the attribute value for TpaId.
     * @param value value to set the TpaId
     */
    public void setTpaId(String value) {
        setAttributeInternal(TPAID, value);
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
     * Gets the attribute value for IuVendor, using the alias name IuVendor.
     * @return the value of IuVendor
     */
    public String getIuVendor() {
        return (String) getAttributeInternal(IUVENDOR);
    }

    /**
     * Sets <code>value</code> as the attribute value for IuVendor.
     * @param value value to set the IuVendor
     */
    public void setIuVendor(String value) {
        setAttributeInternal(IUVENDOR, value);
    }

    /**
     * Gets the attribute value for SourceBu, using the alias name SourceBu.
     * @return the value of SourceBu
     */
    public String getSourceBu() {
        return (String) getAttributeInternal(SOURCEBU);
    }

    /**
     * Sets <code>value</code> as the attribute value for SourceBu.
     * @param value value to set the SourceBu
     */
    public void setSourceBu(String value) {
        setAttributeInternal(SOURCEBU, value);
    }

    /**
     * Gets the attribute value for SourceCurrency, using the alias name SourceCurrency.
     * @return the value of SourceCurrency
     */
    public String getSourceCurrency() {
        return (String) getAttributeInternal(SOURCECURRENCY);
    }

    /**
     * Sets <code>value</code> as the attribute value for SourceCurrency.
     * @param value value to set the SourceCurrency
     */
    public void setSourceCurrency(String value) {
        setAttributeInternal(SOURCECURRENCY, value);
    }

    /**
     * Gets the attribute value for SourceAmount, using the alias name SourceAmount.
     * @return the value of SourceAmount
     */
    public BigDecimal getSourceAmount() {
        return (BigDecimal) getAttributeInternal(SOURCEAMOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for SourceAmount.
     * @param value value to set the SourceAmount
     */
    public void setSourceAmount(BigDecimal value) {
        setAttributeInternal(SOURCEAMOUNT, value);
    }

    /**
     * Gets the attribute value for IuCustomer, using the alias name IuCustomer.
     * @return the value of IuCustomer
     */
    public String getIuCustomer() {
        return (String) getAttributeInternal(IUCUSTOMER);
    }

    /**
     * Sets <code>value</code> as the attribute value for IuCustomer.
     * @param value value to set the IuCustomer
     */
    public void setIuCustomer(String value) {
        setAttributeInternal(IUCUSTOMER, value);
    }

    /**
     * Gets the attribute value for TargetBu, using the alias name TargetBu.
     * @return the value of TargetBu
     */
    public String getTargetBu() {
        return (String) getAttributeInternal(TARGETBU);
    }

    /**
     * Sets <code>value</code> as the attribute value for TargetBu.
     * @param value value to set the TargetBu
     */
    public void setTargetBu(String value) {
        setAttributeInternal(TARGETBU, value);
    }

    /**
     * Gets the attribute value for AccountingTreatment, using the alias name AccountingTreatment.
     * @return the value of AccountingTreatment
     */
    public String getAccountingTreatment() {
        return (String) getAttributeInternal(ACCOUNTINGTREATMENT);
    }

    /**
     * Sets <code>value</code> as the attribute value for AccountingTreatment.
     * @param value value to set the AccountingTreatment
     */
    public void setAccountingTreatment(String value) {
        setAttributeInternal(ACCOUNTINGTREATMENT, value);
    }

    /**
     * Gets the attribute value for MarkupRate, using the alias name MarkupRate.
     * @return the value of MarkupRate
     */
    public BigDecimal getMarkupRate() {
        return (BigDecimal) getAttributeInternal(MARKUPRATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for MarkupRate.
     * @param value value to set the MarkupRate
     */
    public void setMarkupRate(BigDecimal value) {
        setAttributeInternal(MARKUPRATE, value);
    }

    /**
     * Gets the attribute value for TargetCurrency, using the alias name TargetCurrency.
     * @return the value of TargetCurrency
     */
    public String getTargetCurrency() {
        return (String) getAttributeInternal(TARGETCURRENCY);
    }

    /**
     * Sets <code>value</code> as the attribute value for TargetCurrency.
     * @param value value to set the TargetCurrency
     */
    public void setTargetCurrency(String value) {
        setAttributeInternal(TARGETCURRENCY, value);
    }

    /**
     * Gets the attribute value for InvoiceHeaderAmount, using the alias name InvoiceHeaderAmount.
     * @return the value of InvoiceHeaderAmount
     */
    public BigDecimal getInvoiceHeaderAmount() {
        return (BigDecimal) getAttributeInternal(INVOICEHEADERAMOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for InvoiceHeaderAmount.
     * @param value value to set the InvoiceHeaderAmount
     */
    public void setInvoiceHeaderAmount(BigDecimal value) {
        setAttributeInternal(INVOICEHEADERAMOUNT, value);
    }

    /**
     * Gets the attribute value for MarkupAmount, using the alias name MarkupAmount.
     * @return the value of MarkupAmount
     */
    public BigDecimal getMarkupAmount() {
        return (BigDecimal) getAttributeInternal(MARKUPAMOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for MarkupAmount.
     * @param value value to set the MarkupAmount
     */
    public void setMarkupAmount(BigDecimal value) {
        setAttributeInternal(MARKUPAMOUNT, value);
    }

    /**
     * Gets the attribute value for BaseAmountBeforeTax, using the alias name BaseAmountBeforeTax.
     * @return the value of BaseAmountBeforeTax
     */
    public BigDecimal getBaseAmountBeforeTax() {
        return (BigDecimal) getAttributeInternal(BASEAMOUNTBEFORETAX);
    }

    /**
     * Sets <code>value</code> as the attribute value for BaseAmountBeforeTax.
     * @param value value to set the BaseAmountBeforeTax
     */
    public void setBaseAmountBeforeTax(BigDecimal value) {
        setAttributeInternal(BASEAMOUNTBEFORETAX, value);
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
     * Gets the attribute value for TotalAmountAfterTax, using the alias name TotalAmountAfterTax.
     * @return the value of TotalAmountAfterTax
     */
    public BigDecimal getTotalAmountAfterTax() {
        return (BigDecimal) getAttributeInternal(TOTALAMOUNTAFTERTAX);
    }

    /**
     * Sets <code>value</code> as the attribute value for TotalAmountAfterTax.
     * @param value value to set the TotalAmountAfterTax
     */
    public void setTotalAmountAfterTax(BigDecimal value) {
        setAttributeInternal(TOTALAMOUNTAFTERTAX, value);
    }

    /**
     * Gets the attribute value for WhtRate, using the alias name WhtRate.
     * @return the value of WhtRate
     */
    public BigDecimal getWhtRate() {
        return (BigDecimal) getAttributeInternal(WHTRATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for WhtRate.
     * @param value value to set the WhtRate
     */
    public void setWhtRate(BigDecimal value) {
        setAttributeInternal(WHTRATE, value);
    }

    /**
     * Gets the attribute value for WhtAmount, using the alias name WhtAmount.
     * @return the value of WhtAmount
     */
    public BigDecimal getWhtAmount() {
        return (BigDecimal) getAttributeInternal(WHTAMOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for WhtAmount.
     * @param value value to set the WhtAmount
     */
    public void setWhtAmount(BigDecimal value) {
        setAttributeInternal(WHTAMOUNT, value);
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
     * Gets the attribute value for PaymentStatus, using the alias name PaymentStatus.
     * @return the value of PaymentStatus
     */
    public String getPaymentStatus() {
        return (String) getAttributeInternal(PAYMENTSTATUS);
    }

    /**
     * Sets <code>value</code> as the attribute value for PaymentStatus.
     * @param value value to set the PaymentStatus
     */
    public void setPaymentStatus(String value) {
        setAttributeInternal(PAYMENTSTATUS, value);
    }

    /**
     * Gets the attribute value for PaidAmount, using the alias name PaidAmount.
     * @return the value of PaidAmount
     */
    public BigDecimal getPaidAmount() {
        return (BigDecimal) getAttributeInternal(PAIDAMOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for PaidAmount.
     * @param value value to set the PaidAmount
     */
    public void setPaidAmount(BigDecimal value) {
        setAttributeInternal(PAIDAMOUNT, value);
    }

    /**
     * Gets the attribute value for ReferenceVoucherNum, using the alias name ReferenceVoucherNum.
     * @return the value of ReferenceVoucherNum
     */
    public String getReferenceVoucherNum() {
        return (String) getAttributeInternal(REFERENCEVOUCHERNUM);
    }

    /**
     * Sets <code>value</code> as the attribute value for ReferenceVoucherNum.
     * @param value value to set the ReferenceVoucherNum
     */
    public void setReferenceVoucherNum(String value) {
        setAttributeInternal(REFERENCEVOUCHERNUM, value);
    }

    /**
     * Gets the attribute value for ReferenceInvoiceNum, using the alias name ReferenceInvoiceNum.
     * @return the value of ReferenceInvoiceNum
     */
    public String getReferenceInvoiceNum() {
        return (String) getAttributeInternal(REFERENCEINVOICENUM);
    }

    /**
     * Sets <code>value</code> as the attribute value for ReferenceInvoiceNum.
     * @param value value to set the ReferenceInvoiceNum
     */
    public void setReferenceInvoiceNum(String value) {
        setAttributeInternal(REFERENCEINVOICENUM, value);
    }

    /**
     * Gets the attribute value for ReviewAccEntries, using the alias name ReviewAccEntries.
     * @return the value of ReviewAccEntries
     */
    public String getReviewAccEntries() {
        return (String) getAttributeInternal(REVIEWACCENTRIES);
    }

    /**
     * Sets <code>value</code> as the attribute value for ReviewAccEntries.
     * @param value value to set the ReviewAccEntries
     */
    public void setReviewAccEntries(String value) {
        setAttributeInternal(REVIEWACCENTRIES, value);
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
     * Gets the attribute value for ALLOCATEDHEADERAMOUNT, using the alias name ALLOCATEDHEADERAMOUNT.
     * @return the value of ALLOCATEDHEADERAMOUNT
     */
    public BigDecimal getALLOCATEDHEADERAMOUNT() {
        return (BigDecimal) getAttributeInternal(ALLOCATEDHEADERAMOUNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for ALLOCATEDHEADERAMOUNT.
     * @param value value to set the ALLOCATEDHEADERAMOUNT
     */
    public void setALLOCATEDHEADERAMOUNT(BigDecimal value) {
        setAttributeInternal(ALLOCATEDHEADERAMOUNT, value);
    }

    /**
     * Gets the attribute value for GSTAMNT, using the alias name GSTAMNT.
     * @return the value of GSTAMNT
     */
    public BigDecimal getGSTAMNT() {
        return (BigDecimal) getAttributeInternal(GSTAMNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for GSTAMNT.
     * @param value value to set the GSTAMNT
     */
    public void setGSTAMNT(BigDecimal value) {
        setAttributeInternal(GSTAMNT, value);
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
     * Gets the attribute value for VATAMNT, using the alias name VATAMNT.
     * @return the value of VATAMNT
     */
    public BigDecimal getVATAMNT() {
        return (BigDecimal) getAttributeInternal(VATAMNT);
    }

    /**
     * Sets <code>value</code> as the attribute value for VATAMNT.
     * @param value value to set the VATAMNT
     */
    public void setVATAMNT(BigDecimal value) {
        setAttributeInternal(VATAMNT, value);
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
     * Gets the attribute value for REVERSALREASON, using the alias name REVERSALREASON.
     * @return the value of REVERSALREASON
     */
    public String getREVERSALREASON() {
        return (String) getAttributeInternal(REVERSALREASON);
    }

    /**
     * Sets <code>value</code> as the attribute value for REVERSALREASON.
     * @param value value to set the REVERSALREASON
     */
    public void setREVERSALREASON(String value) {
        setAttributeInternal(REVERSALREASON, value);
    }

    /**
     * Gets the attribute value for NATUREOFEXPENSE, using the alias name NATUREOFEXPENSE.
     * @return the value of NATUREOFEXPENSE
     */
    public String getNATUREOFEXPENSE() {
        return (String) getAttributeInternal(NATUREOFEXPENSE);
    }

    /**
     * Sets <code>value</code> as the attribute value for NATUREOFEXPENSE.
     * @param value value to set the NATUREOFEXPENSE
     */
    public void setNATUREOFEXPENSE(String value) {
        setAttributeInternal(NATUREOFEXPENSE, value);
    }

    /**
     * @return the associated entity oracle.jbo.RowIterator.
     */
    public RowIterator getSgsIcInvoiceLineEO() {
        return (RowIterator) getAttributeInternal(SGSICINVOICELINEEO);
    }


    /**
     * @param invoiceSeqNo key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(String invoiceSeqNo) {
        return new Key(new Object[] { invoiceSeqNo });
    }

    /**
     * Add attribute defaulting logic in this method.
     * @param attributeList list of attribute names/values to initialize the row
     */
    protected void create(AttributeList attributeList) {
        super.create(attributeList);
        try {
            SGSAppModuleImpl am = new SGSAppModuleImpl();
            setInvoiceSeqNo("ID"+am.getDBSequence("SEQ_IC_INVOICE_HEADER"));
            CommonUtils util = new CommonUtils();
            Object user = (Object) util.getSessionScopeValue("_username").toString();
            setCreatedBy(user.toString());
        } catch (Exception e) {
            LOG.severe(e);
        }
    }

    /**
     * Add locking logic here.
     */
    public void lock() {
        super.lock();
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

