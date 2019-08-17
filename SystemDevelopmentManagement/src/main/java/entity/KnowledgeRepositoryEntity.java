package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Date;
import java.util.Objects;
import javax.persistence.*;

@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name = "KNOWLEDGE_REPOSITORY")
public class KnowledgeRepositoryEntity {
    private Integer id;
    private Integer typeId;
    private String number;
    private String description;
    private Date date;
    private KnowledgeTypeEntity knowledgeTypeByTypeId;

    public KnowledgeRepositoryEntity() {
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
    @Column(name = "NUMBERS", length = 20)
    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Basic
    @Column(name = "DESCRIPTION", length = 200)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "DATES")
    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            KnowledgeRepositoryEntity that = (KnowledgeRepositoryEntity) o;
            return this.id.equals(that.id) && this.typeId.equals(that.typeId) && Objects.equals(this.number, that.number) && Objects.equals(this.description, that.description) && Objects.equals(this.date, that.date);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(this.id, this.typeId, this.number, this.description, this.date);
    }

    @JsonIgnore
    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPE_ID", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    public KnowledgeTypeEntity getKnowledgeTypeByTypeId() {
        return this.knowledgeTypeByTypeId;
    }

    public void setKnowledgeTypeByTypeId(KnowledgeTypeEntity knowledgeTypeByTypeId) {
        this.knowledgeTypeByTypeId = knowledgeTypeByTypeId;
    }
}
