package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "API_COMPONENTS")
public class ApiComponentEntity {
    private Integer id;
    private Integer typeId;
    private String functionMacrotaxonomy;
    private String name;
    private ApiComponentTypeEntity apiComponentTypeByTypeId;

    public ApiComponentEntity() {
    }

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TYPE_ID", nullable = false)
    public Integer getTypeId() {
        return this.typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "FUNCTION_MACROTAXONOMY", nullable = false, length = 50)
    public String getFunctionMacrotaxonomy() {
        return this.functionMacrotaxonomy;
    }

    public void setFunctionMacrotaxonomy(String functionMacrotaxonomy) {
        this.functionMacrotaxonomy = functionMacrotaxonomy;
    }

    @Basic
    @Column(name = "NAME", length = 50)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            ApiComponentEntity that = (ApiComponentEntity) o;
            return this.id.equals(that.id) && this.typeId.equals(that.typeId) && Objects.equals(this.functionMacrotaxonomy, that.functionMacrotaxonomy) && Objects.equals(this.name, that.name);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(this.id, this.typeId, this.functionMacrotaxonomy, this.name);
    }

    //@JsonIgnore
    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPE_ID", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    public ApiComponentTypeEntity getApiComponentTypeByTypeId() {
        return this.apiComponentTypeByTypeId;
    }

    public void setApiComponentTypeByTypeId(ApiComponentTypeEntity apiComponentTypeByTypeId) {
        this.apiComponentTypeByTypeId = apiComponentTypeByTypeId;
    }
}
