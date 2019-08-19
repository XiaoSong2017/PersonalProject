package service;

import bean.Page;
import dao.DataDictionaryEntityDao;
import entity.DataDictionaryEntity;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DataDictionaryService {
    private DataDictionaryEntityDao dataDictionaryEntityDao;

    public DataDictionaryService() {
    }

    public void setDataDictionaryEntityDao(DataDictionaryEntityDao dataDictionaryEntityDao) {
        this.dataDictionaryEntityDao = dataDictionaryEntityDao;
    }

    @Transactional(readOnly = true)
    public List<DataDictionaryEntity> getAll() {
        return this.dataDictionaryEntityDao.getAll(DataDictionaryEntity.class);
    }

    @Transactional(readOnly = true)
    public Page<DataDictionaryEntity> getAllByPage(int pageNumber, int pageSize) {
        return dataDictionaryEntityDao.getAllByPage(DataDictionaryEntity.class,pageNumber,pageSize);
    }

    @Transactional
    public boolean deleteDataDictionaryById(int id) {
        return dataDictionaryEntityDao.delete(DataDictionaryEntity.class,id);
    }

    @Transactional(readOnly = true)
    public DataDictionaryEntity getDataDictionaryById(Integer id) {
        return dataDictionaryEntityDao.getById(DataDictionaryEntity.class,id);
    }


    @Transactional
    public boolean saveDataDictionary(DataDictionaryEntity dataDictionary) {
        dataDictionaryEntityDao.save(dataDictionary);
        return true;
    }

    @Transactional
    public boolean updateDataDictionary(DataDictionaryEntity dataDictionary) {
        return dataDictionaryEntityDao.update(dataDictionary);
    }

    @Transactional
    public boolean batchDeleteDataDictionary(List<Object> ids) {
        return dataDictionaryEntityDao.batchToDelete(DataDictionaryEntity.class,ids);
    }
}
