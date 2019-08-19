package service;

import bean.Page;
import dao.ApiComponentEntityDao;
import dao.ApiComponentTypeEntityDao;
import entity.ApiComponentEntity;

import java.util.List;

import entity.ApiComponentTypeEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApiComponentService {
    private ApiComponentEntityDao apiComponentEntityDao;
    private ApiComponentTypeEntityDao apiComponentTypeEntityDao;

    public ApiComponentService() {
    }

    public void setApiComponentEntityDao(ApiComponentEntityDao apiComponentEntityDao) {
        this.apiComponentEntityDao = apiComponentEntityDao;
    }

    public void setApiComponentTypeEntityDao(ApiComponentTypeEntityDao apiComponentTypeEntityDao) {
        this.apiComponentTypeEntityDao = apiComponentTypeEntityDao;
    }

    @Transactional(readOnly = true)
    public List<ApiComponentEntity> getApiComponentAll() {
        return apiComponentEntityDao.getAll(ApiComponentEntity.class);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public Page<ApiComponentEntity> getAllByPage(int pageNumber, int pageSize) {
        return apiComponentEntityDao.getAllByPage(ApiComponentEntity.class, pageNumber, pageSize);
    }

    @Transactional
    public Boolean deleteApiComponentById(int id) {
        return apiComponentEntityDao.delete(ApiComponentEntity.class, id);
    }

    @Transactional
    public boolean saveApiComponent(ApiComponentEntity apiComponentEntity) {
        apiComponentEntityDao.save(apiComponentEntity);
        return true;
    }

    @Transactional
    public boolean updateApiComponent(ApiComponentEntity apiComponentEntity) {
        return apiComponentEntityDao.update(apiComponentEntity);
    }

    @Transactional(readOnly = true)
    public List<ApiComponentTypeEntity> getApiComponentTypeAll() {
        return apiComponentTypeEntityDao.getAll(ApiComponentTypeEntity.class);
    }

    @Transactional(readOnly = true)
    public ApiComponentEntity getApiComponentById(Integer id) {
        return apiComponentEntityDao.getById(ApiComponentEntity.class,id);
    }

    @Transactional
    public boolean batchDeleteApiComponent(List<Object> id) {
        return apiComponentEntityDao.batchToDelete(ApiComponentEntity.class,id);
    }
}
