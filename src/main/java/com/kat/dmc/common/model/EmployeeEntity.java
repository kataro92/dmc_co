package com.kat.dmc.common.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "employee", schema = "public")
public class EmployeeEntity {
    private String address;
    private String code;
    private String defCode;
    private String editedBy;
    private String email;
    private String firstName;
    private String gender;
    private String identityCardCardNumber;
    private String identityCardIssuedBy;
    private String jobPositionCode;
    private String leaveDate;
    private String name;
    private String phone;
    private String startDate;
    private Integer status;
    private String userCode;
    private int id;
    private int deptId;
    private Timestamp dateOfBirth;
    private Timestamp identityCardIssuedDate;

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "def_code")
    public String getDefCode() {
        return defCode;
    }

    public void setDefCode(String defCode) {
        this.defCode = defCode;
    }

    @Basic
    @Column(name = "edited_by")
    public String getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(String editedBy) {
        this.editedBy = editedBy;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "identity_card_card_number")
    public String getIdentityCardCardNumber() {
        return identityCardCardNumber;
    }

    public void setIdentityCardCardNumber(String identityCardCardNumber) {
        this.identityCardCardNumber = identityCardCardNumber;
    }

    @Basic
    @Column(name = "identity_card_issued_by")
    public String getIdentityCardIssuedBy() {
        return identityCardIssuedBy;
    }

    public void setIdentityCardIssuedBy(String identityCardIssuedBy) {
        this.identityCardIssuedBy = identityCardIssuedBy;
    }

    @Basic
    @Column(name = "job_position_code")
    public String getJobPositionCode() {
        return jobPositionCode;
    }

    public void setJobPositionCode(String jobPositionCode) {
        this.jobPositionCode = jobPositionCode;
    }

    @Basic
    @Column(name = "leave_date")
    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "start_date")
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "user_code")
    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Id
    @Column(name = "_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "dept_id")
    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    @Basic
    @Column(name = "date_of_birth")
    public Timestamp getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Timestamp dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Basic
    @Column(name = "identity_card_issued_date")
    public Timestamp getIdentityCardIssuedDate() {
        return identityCardIssuedDate;
    }

    public void setIdentityCardIssuedDate(Timestamp identityCardIssuedDate) {
        this.identityCardIssuedDate = identityCardIssuedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeEntity that = (EmployeeEntity) o;

        if (id != that.id) return false;
        if (deptId != that.deptId) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (defCode != null ? !defCode.equals(that.defCode) : that.defCode != null) return false;
        if (editedBy != null ? !editedBy.equals(that.editedBy) : that.editedBy != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (identityCardCardNumber != null ? !identityCardCardNumber.equals(that.identityCardCardNumber) : that.identityCardCardNumber != null)
            return false;
        if (identityCardIssuedBy != null ? !identityCardIssuedBy.equals(that.identityCardIssuedBy) : that.identityCardIssuedBy != null)
            return false;
        if (jobPositionCode != null ? !jobPositionCode.equals(that.jobPositionCode) : that.jobPositionCode != null)
            return false;
        if (leaveDate != null ? !leaveDate.equals(that.leaveDate) : that.leaveDate != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (userCode != null ? !userCode.equals(that.userCode) : that.userCode != null) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(that.dateOfBirth) : that.dateOfBirth != null) return false;
        if (identityCardIssuedDate != null ? !identityCardIssuedDate.equals(that.identityCardIssuedDate) : that.identityCardIssuedDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = address != null ? address.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (defCode != null ? defCode.hashCode() : 0);
        result = 31 * result + (editedBy != null ? editedBy.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (identityCardCardNumber != null ? identityCardCardNumber.hashCode() : 0);
        result = 31 * result + (identityCardIssuedBy != null ? identityCardIssuedBy.hashCode() : 0);
        result = 31 * result + (jobPositionCode != null ? jobPositionCode.hashCode() : 0);
        result = 31 * result + (leaveDate != null ? leaveDate.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + deptId;
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (identityCardIssuedDate != null ? identityCardIssuedDate.hashCode() : 0);
        return result;
    }
}
