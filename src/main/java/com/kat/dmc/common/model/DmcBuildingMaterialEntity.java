package com.kat.dmc.common.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "dmc_building_material", schema = "public")
public class DmcBuildingMaterialEntity implements Serializable {
    private int id;
    private Integer productId;
    private Integer materialId;
    private Integer materialType;
    private Integer status;
    private Integer quantity;
    private Integer price;
    private Long total;
    private Integer importId;
    private Timestamp usedDate;
    private String materialCode;
    private String materialName;
    private Integer buildingProductId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "building_product_id")
    public Integer getBuildingProductId() {
        return buildingProductId;
    }

    public void setBuildingProductId(int buildingProductId) {
        this.buildingProductId = buildingProductId;
    }

    @Basic
    @Column(name = "product_id")
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "material_id")
    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    @Basic
    @Column(name = "material_type")
    public Integer getMaterialType() {
        return materialType;
    }

    public void setMaterialType(Integer materialType) {
        this.materialType = materialType;
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
    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "total")
    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    @Basic
    @Column(name = "import_id")
    public Integer getImportId() {
        return importId;
    }

    public void setImportId(Integer importId) {
        this.importId = importId;
    }

    @Basic
    @Column(name = "used_date")
    public Timestamp getUsedDate() {
        return usedDate;
    }

    public void setUsedDate(Timestamp usedDate) {
        this.usedDate = usedDate;
    }

    @Basic
    @Column(name = "material_code")
    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    @Basic
    @Column(name = "material_name")
    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DmcBuildingMaterialEntity that = (DmcBuildingMaterialEntity) o;

        if (id != that.id) return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        if (materialId != null ? !materialId.equals(that.materialId) : that.materialId != null) return false;
        if (materialType != null ? !materialType.equals(that.materialType) : that.materialType != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (total != null ? !total.equals(that.total) : that.total != null) return false;
        if (importId != null ? !importId.equals(that.importId) : that.importId != null) return false;
        if (usedDate != null ? !usedDate.equals(that.usedDate) : that.usedDate != null) return false;
        if (materialCode != null ? !materialCode.equals(that.materialCode) : that.materialCode != null) return false;
        if (materialName != null ? !materialName.equals(that.materialName) : that.materialName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (materialId != null ? materialId.hashCode() : 0);
        result = 31 * result + (materialType != null ? materialType.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (importId != null ? importId.hashCode() : 0);
        result = 31 * result + (usedDate != null ? usedDate.hashCode() : 0);
        result = 31 * result + (materialCode != null ? materialCode.hashCode() : 0);
        result = 31 * result + (materialName != null ? materialName.hashCode() : 0);
        return result;
    }
}
