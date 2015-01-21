package org.meridor.erp.accounting;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Contracts {
    private int contractId;
    private int issuerId;
    private int signerId;
    private String contractType;
    private Timestamp createDate;
    private int templateId;

    @Id
    @Column(name = "contract_id")
    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    @Basic
    @Column(name = "issuer_id")
    public int getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(int issuerId) {
        this.issuerId = issuerId;
    }

    @Basic
    @Column(name = "signer_id")
    public int getSignerId() {
        return signerId;
    }

    public void setSignerId(int signerId) {
        this.signerId = signerId;
    }

    @Basic
    @Column(name = "contract_type")
    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    @Basic
    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "template_id")
    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contracts contracts = (Contracts) o;

        if (contractId != contracts.contractId) return false;
        if (issuerId != contracts.issuerId) return false;
        if (signerId != contracts.signerId) return false;
        if (templateId != contracts.templateId) return false;
        if (contractType != null ? !contractType.equals(contracts.contractType) : contracts.contractType != null)
            return false;
        if (createDate != null ? !createDate.equals(contracts.createDate) : contracts.createDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = contractId;
        result = 31 * result + issuerId;
        result = 31 * result + signerId;
        result = 31 * result + (contractType != null ? contractType.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + templateId;
        return result;
    }
}
