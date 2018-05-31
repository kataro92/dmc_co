package com.kat.dmc.common.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "employee", schema = "public", catalog = "dmco_sys")
public class EmployeeEntity {
    private String address;
    private String code;
    private String createdBy;
    private Timestamp createdDate;
    private String dateOfBirth;
    private String defCode;
    private String documents0Hash;
    private String documents0Name;
    private String documents0Relative;
    private String documents0UploadedDate;
    private String documents1Hash;
    private String documents1Name;
    private String documents1Relative;
    private String documents1UploadedDate;
    private String documents2Hash;
    private String documents2Name;
    private String documents2Relative;
    private String documents2UploadedDate;
    private String editedBy;
    private String email;
    private String firstName;
    private String gender;
    private String identityCardCardNumber;
    private String identityCardIssuedBy;
    private String identityCardIssuedDate;
    private String jobPositionCode;
    private Timestamp lastModified;
    private String leaveDate;
    private String name;
    private String phone;
    private Boolean isPublished;
    private String publishedBy;
    private String startDate;
    private Integer status;
    private String userCode;
    private String valueToSearch;
    private int id;

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
    @Column(name = "created_by")
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "date_of_birth")
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
    @Column(name = "documents_0_hash")
    public String getDocuments0Hash() {
        return documents0Hash;
    }

    public void setDocuments0Hash(String documents0Hash) {
        this.documents0Hash = documents0Hash;
    }

    @Basic
    @Column(name = "documents_0_name")
    public String getDocuments0Name() {
        return documents0Name;
    }

    public void setDocuments0Name(String documents0Name) {
        this.documents0Name = documents0Name;
    }

    @Basic
    @Column(name = "documents_0_relative")
    public String getDocuments0Relative() {
        return documents0Relative;
    }

    public void setDocuments0Relative(String documents0Relative) {
        this.documents0Relative = documents0Relative;
    }

    @Basic
    @Column(name = "documents_0_uploaded_date")
    public String getDocuments0UploadedDate() {
        return documents0UploadedDate;
    }

    public void setDocuments0UploadedDate(String documents0UploadedDate) {
        this.documents0UploadedDate = documents0UploadedDate;
    }

    @Basic
    @Column(name = "documents_1_hash")
    public String getDocuments1Hash() {
        return documents1Hash;
    }

    public void setDocuments1Hash(String documents1Hash) {
        this.documents1Hash = documents1Hash;
    }

    @Basic
    @Column(name = "documents_1_name")
    public String getDocuments1Name() {
        return documents1Name;
    }

    public void setDocuments1Name(String documents1Name) {
        this.documents1Name = documents1Name;
    }

    @Basic
    @Column(name = "documents_1_relative")
    public String getDocuments1Relative() {
        return documents1Relative;
    }

    public void setDocuments1Relative(String documents1Relative) {
        this.documents1Relative = documents1Relative;
    }

    @Basic
    @Column(name = "documents_1_uploaded_date")
    public String getDocuments1UploadedDate() {
        return documents1UploadedDate;
    }

    public void setDocuments1UploadedDate(String documents1UploadedDate) {
        this.documents1UploadedDate = documents1UploadedDate;
    }

    @Basic
    @Column(name = "documents_2_hash")
    public String getDocuments2Hash() {
        return documents2Hash;
    }

    public void setDocuments2Hash(String documents2Hash) {
        this.documents2Hash = documents2Hash;
    }

    @Basic
    @Column(name = "documents_2_name")
    public String getDocuments2Name() {
        return documents2Name;
    }

    public void setDocuments2Name(String documents2Name) {
        this.documents2Name = documents2Name;
    }

    @Basic
    @Column(name = "documents_2_relative")
    public String getDocuments2Relative() {
        return documents2Relative;
    }

    public void setDocuments2Relative(String documents2Relative) {
        this.documents2Relative = documents2Relative;
    }

    @Basic
    @Column(name = "documents_2_uploaded_date")
    public String getDocuments2UploadedDate() {
        return documents2UploadedDate;
    }

    public void setDocuments2UploadedDate(String documents2UploadedDate) {
        this.documents2UploadedDate = documents2UploadedDate;
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
    @Column(name = "identity_card_issued_date")
    public String getIdentityCardIssuedDate() {
        return identityCardIssuedDate;
    }

    public void setIdentityCardIssuedDate(String identityCardIssuedDate) {
        this.identityCardIssuedDate = identityCardIssuedDate;
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
    @Column(name = "last_modified")
    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
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
    @Column(name = "is_published")
    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }

    @Basic
    @Column(name = "published_by")
    public String getPublishedBy() {
        return publishedBy;
    }

    public void setPublishedBy(String publishedBy) {
        this.publishedBy = publishedBy;
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

    @Basic
    @Column(name = "value_to_search")
    public String getValueToSearch() {
        return valueToSearch;
    }

    public void setValueToSearch(String valueToSearch) {
        this.valueToSearch = valueToSearch;
    }

    @Id
    @Column(name = "_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeEntity that = (EmployeeEntity) o;

        if (id != that.id) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(that.dateOfBirth) : that.dateOfBirth != null) return false;
        if (defCode != null ? !defCode.equals(that.defCode) : that.defCode != null) return false;
        if (documents0Hash != null ? !documents0Hash.equals(that.documents0Hash) : that.documents0Hash != null)
            return false;
        if (documents0Name != null ? !documents0Name.equals(that.documents0Name) : that.documents0Name != null)
            return false;
        if (documents0Relative != null ? !documents0Relative.equals(that.documents0Relative) : that.documents0Relative != null)
            return false;
        if (documents0UploadedDate != null ? !documents0UploadedDate.equals(that.documents0UploadedDate) : that.documents0UploadedDate != null)
            return false;
        if (documents1Hash != null ? !documents1Hash.equals(that.documents1Hash) : that.documents1Hash != null)
            return false;
        if (documents1Name != null ? !documents1Name.equals(that.documents1Name) : that.documents1Name != null)
            return false;
        if (documents1Relative != null ? !documents1Relative.equals(that.documents1Relative) : that.documents1Relative != null)
            return false;
        if (documents1UploadedDate != null ? !documents1UploadedDate.equals(that.documents1UploadedDate) : that.documents1UploadedDate != null)
            return false;
        if (documents2Hash != null ? !documents2Hash.equals(that.documents2Hash) : that.documents2Hash != null)
            return false;
        if (documents2Name != null ? !documents2Name.equals(that.documents2Name) : that.documents2Name != null)
            return false;
        if (documents2Relative != null ? !documents2Relative.equals(that.documents2Relative) : that.documents2Relative != null)
            return false;
        if (documents2UploadedDate != null ? !documents2UploadedDate.equals(that.documents2UploadedDate) : that.documents2UploadedDate != null)
            return false;
        if (editedBy != null ? !editedBy.equals(that.editedBy) : that.editedBy != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (identityCardCardNumber != null ? !identityCardCardNumber.equals(that.identityCardCardNumber) : that.identityCardCardNumber != null)
            return false;
        if (identityCardIssuedBy != null ? !identityCardIssuedBy.equals(that.identityCardIssuedBy) : that.identityCardIssuedBy != null)
            return false;
        if (identityCardIssuedDate != null ? !identityCardIssuedDate.equals(that.identityCardIssuedDate) : that.identityCardIssuedDate != null)
            return false;
        if (jobPositionCode != null ? !jobPositionCode.equals(that.jobPositionCode) : that.jobPositionCode != null)
            return false;
        if (lastModified != null ? !lastModified.equals(that.lastModified) : that.lastModified != null) return false;
        if (leaveDate != null ? !leaveDate.equals(that.leaveDate) : that.leaveDate != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (isPublished != null ? !isPublished.equals(that.isPublished) : that.isPublished != null) return false;
        if (publishedBy != null ? !publishedBy.equals(that.publishedBy) : that.publishedBy != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (userCode != null ? !userCode.equals(that.userCode) : that.userCode != null) return false;
        if (valueToSearch != null ? !valueToSearch.equals(that.valueToSearch) : that.valueToSearch != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = address != null ? address.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (defCode != null ? defCode.hashCode() : 0);
        result = 31 * result + (documents0Hash != null ? documents0Hash.hashCode() : 0);
        result = 31 * result + (documents0Name != null ? documents0Name.hashCode() : 0);
        result = 31 * result + (documents0Relative != null ? documents0Relative.hashCode() : 0);
        result = 31 * result + (documents0UploadedDate != null ? documents0UploadedDate.hashCode() : 0);
        result = 31 * result + (documents1Hash != null ? documents1Hash.hashCode() : 0);
        result = 31 * result + (documents1Name != null ? documents1Name.hashCode() : 0);
        result = 31 * result + (documents1Relative != null ? documents1Relative.hashCode() : 0);
        result = 31 * result + (documents1UploadedDate != null ? documents1UploadedDate.hashCode() : 0);
        result = 31 * result + (documents2Hash != null ? documents2Hash.hashCode() : 0);
        result = 31 * result + (documents2Name != null ? documents2Name.hashCode() : 0);
        result = 31 * result + (documents2Relative != null ? documents2Relative.hashCode() : 0);
        result = 31 * result + (documents2UploadedDate != null ? documents2UploadedDate.hashCode() : 0);
        result = 31 * result + (editedBy != null ? editedBy.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (identityCardCardNumber != null ? identityCardCardNumber.hashCode() : 0);
        result = 31 * result + (identityCardIssuedBy != null ? identityCardIssuedBy.hashCode() : 0);
        result = 31 * result + (identityCardIssuedDate != null ? identityCardIssuedDate.hashCode() : 0);
        result = 31 * result + (jobPositionCode != null ? jobPositionCode.hashCode() : 0);
        result = 31 * result + (lastModified != null ? lastModified.hashCode() : 0);
        result = 31 * result + (leaveDate != null ? leaveDate.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (isPublished != null ? isPublished.hashCode() : 0);
        result = 31 * result + (publishedBy != null ? publishedBy.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (valueToSearch != null ? valueToSearch.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
