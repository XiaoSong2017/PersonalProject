package entity;

import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "DATA_DICTIONARIES")
public class DataDictionaryEntity {
    private Integer id;
    private String name;
    private String abbreviation;
    private String fullName;
    private String dataType;

    public DataDictionaryEntity() {
    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "abbreviation", length = 50)
    public String getAbbreviation() {
        return this.abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Basic
    @Column(name = "full_name", length = 400)
    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Basic
    @Column(name = "data_type", length = 30)
    public String getDataType() {
        return this.dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            DataDictionaryEntity that = (DataDictionaryEntity)o;
            return this.id.equals(that.id) && Objects.equals(this.name, that.name) && Objects.equals(this.abbreviation, that.abbreviation) && Objects.equals(this.fullName, that.fullName) && Objects.equals(this.dataType, that.dataType);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(this.id, this.name, this.abbreviation, this.fullName, this.dataType);
    }
}
