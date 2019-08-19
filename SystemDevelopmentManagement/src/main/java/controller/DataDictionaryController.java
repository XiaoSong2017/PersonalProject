package controller;

import bean.Page;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sun.istack.NotNull;
import entity.DataDictionaryEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.DataDictionaryService;

import java.util.List;

@Controller
public class DataDictionaryController {

    private final DataDictionaryService dataDictionaryService;

    public DataDictionaryController(DataDictionaryService dataDictionaryService) {
        this.dataDictionaryService = dataDictionaryService;
    }

    @RequestMapping("/dataDictionaryAll")
    @ResponseBody
    public String getDataDictionaryAll() {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("data",dataDictionaryService.getAll());
        return JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);
    }

    @RequestMapping(value = "/dataDictionaryAllByPage", method = RequestMethod.POST)
    @ResponseBody
    public Page<DataDictionaryEntity> getAllByPage(int pageNumber, int pageSize) {
        return dataDictionaryService.getAllByPage(pageNumber, pageSize);
    }

    @RequestMapping(value = "/dataDictionaryById", method = RequestMethod.POST)
    @ResponseBody
    public @NotNull
    DataDictionaryEntity getDataDictionaryById(@NotNull Integer id){
        return dataDictionaryService.getDataDictionaryById(id);
    }

    @RequestMapping(value = "/deleteDataDictionaryById", method = RequestMethod.POST)
    @ResponseBody
    public String deleteDataDictionaryById(int id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", dataDictionaryService.deleteDataDictionaryById(id));
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/saveDataDictionary", method = RequestMethod.POST)
    @ResponseBody
    public String saveDataDictionary(@NotNull DataDictionaryEntity dataDictionary) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", dataDictionaryService.saveDataDictionary(dataDictionary));
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/updateDataDictionary", method = RequestMethod.POST)
    @ResponseBody
    public String updateDataDictionary(@NotNull DataDictionaryEntity dataDictionary) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", dataDictionaryService.updateDataDictionary(dataDictionary));
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/batchDeleteDataDictionary", method = RequestMethod.POST)
    @ResponseBody
    public String batchDeleteDataDictionary(@RequestParam(value = "ids[]") Integer[] ids){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", dataDictionaryService.batchDeleteDataDictionary(List.of(ids)));
        return jsonObject.toJSONString();
    }
}
