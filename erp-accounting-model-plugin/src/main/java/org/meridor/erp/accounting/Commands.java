package org.meridor.erp.accounting;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Commands {
    private int commandId;
    private int companyId;
    private int employeeId;
    private Timestamp createDate;
    private String commandType;
    private int templateId;

    @Id
    @Column(name = "command_id")
    public int getCommandId() {
        return commandId;
    }

    public void setCommandId(int commandId) {
        this.commandId = commandId;
    }

    @Basic
    @Column(name = "company_id")
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "employee_id")
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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
    @Column(name = "command_type")
    public String getCommandType() {
        return commandType;
    }

    public void setCommandType(String commandType) {
        this.commandType = commandType;
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

        Commands commands = (Commands) o;

        if (commandId != commands.commandId) return false;
        if (companyId != commands.companyId) return false;
        if (employeeId != commands.employeeId) return false;
        if (templateId != commands.templateId) return false;
        if (commandType != null ? !commandType.equals(commands.commandType) : commands.commandType != null)
            return false;
        if (createDate != null ? !createDate.equals(commands.createDate) : commands.createDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commandId;
        result = 31 * result + companyId;
        result = 31 * result + employeeId;
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (commandType != null ? commandType.hashCode() : 0);
        result = 31 * result + templateId;
        return result;
    }
}
