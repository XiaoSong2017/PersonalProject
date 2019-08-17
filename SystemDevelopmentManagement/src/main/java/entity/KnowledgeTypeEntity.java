package entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name = "KNOWLEDGE_TYPES")
public class KnowledgeTypeEntity {
    private Integer id;
    private String name;
    private Set<KnowledgeRepositoryEntity> knowledgeRepositoriesById;

    public KnowledgeTypeEntity() {
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
    @Column(name = "NAME", nullable = false, length = 50)
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
            KnowledgeTypeEntity that = (KnowledgeTypeEntity) o;
            return this.id.equals(that.id) && Objects.equals(this.name, that.name);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

    @JsonIgnore
    @JSONField(serialize = false)
    @OneToMany(mappedBy = "knowledgeTypeByTypeId", fetch = FetchType.LAZY)
    public Set<KnowledgeRepositoryEntity> getKnowledgeRepositoriesById() {
        return this.knowledgeRepositoriesById;
    }

    public void setKnowledgeRepositoriesById(Set<KnowledgeRepositoryEntity> knowledgeRepositoriesById) {
        this.knowledgeRepositoriesById = knowledgeRepositoriesById;
    }
}
