package service;

import bean.Page;
import dao.KnowledgeRepositoryEntityDao;
import dao.KnowledgeTypeEntityDao;
import entity.KnowledgeRepositoryEntity;
import java.util.List;

import entity.KnowledgeTypeEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KnowledgeRepositoryService {

    private KnowledgeTypeEntityDao knowledgeTypeEntityDao;

    private KnowledgeRepositoryEntityDao knowledgeRepositoryEntityDao;

    public KnowledgeRepositoryService() {
    }

    public void setKnowledgeTypeEntityDao(KnowledgeTypeEntityDao knowledgeTypeEntityDao) {
        this.knowledgeTypeEntityDao = knowledgeTypeEntityDao;
    }

    public void setKnowledgeRepositoryEntityDao(KnowledgeRepositoryEntityDao knowledgeRepositoryEntityDao) {
        this.knowledgeRepositoryEntityDao = knowledgeRepositoryEntityDao;
    }

    @Transactional(readOnly = true)
    public List<KnowledgeRepositoryEntity> getKnowledgeRepositoryAll() {
        return knowledgeRepositoryEntityDao.getAll(KnowledgeRepositoryEntity.class);
    }

    @Transactional(readOnly = true)
    public Page<KnowledgeRepositoryEntity> getAllByPage(int pageNumber, int pageSize) {
        return knowledgeRepositoryEntityDao.getAllByPage(KnowledgeRepositoryEntity.class,pageNumber,pageSize);
    }

    @Transactional
    public boolean deleteKnowledgeRepositoryById(int id) {
        return knowledgeRepositoryEntityDao.delete(KnowledgeRepositoryEntity.class,id);
    }

    @Transactional(readOnly = true)
    public KnowledgeRepositoryEntity getKnowledgeRepositoryById(Integer id) {
        return knowledgeRepositoryEntityDao.getById(KnowledgeRepositoryEntity.class,id);
    }

    @Transactional
    public boolean saveKnowledgeRepository(KnowledgeRepositoryEntity knowledgeRepositoryEntity) {
        knowledgeRepositoryEntityDao.save(knowledgeRepositoryEntity);
        return true;
    }


    @Transactional
    public boolean updateKnowledgeRepository(KnowledgeRepositoryEntity knowledgeRepository) {
        return knowledgeRepositoryEntityDao.update(knowledgeRepository);
    }

    @Transactional(readOnly = true)
    public List<KnowledgeTypeEntity> getKnowledgeRepositoryTypeAll() {
        return knowledgeTypeEntityDao.getAll(KnowledgeTypeEntity.class);
    }

    @Transactional
    public boolean batchDeleteKnowledgeRepository(List<Object> ids) {
        return knowledgeRepositoryEntityDao.batchToDelete(KnowledgeRepositoryEntity.class,ids);
    }
}
