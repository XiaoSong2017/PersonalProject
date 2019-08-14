package controller;

import bean.Page;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sun.istack.NotNull;
import entity.ApiComponentEntity;
import entity.ApiComponentTypeEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ApiComponentService;

import java.util.Collection;
import java.util.List;

@Controller
public class ApiComponentController {

    private final ApiComponentService apiComponentService;

    public ApiComponentController(ApiComponentService apiComponentService) {
        this.apiComponentService = apiComponentService;
    }

    @RequestMapping(value = "/apiComponentTypeAll", method = RequestMethod.POST)
    @ResponseBody
    public List<ApiComponentTypeEntity> getApiComponentTypeAll() {
        return apiComponentService.getApiComponentTypeAll();
    }

    @RequestMapping(value = "/apiComponentAll", method = RequestMethod.POST)
    @ResponseBody
    public String getApiComponentAll() {
        JSONObject jsonObject=new JSONObject();
//        List list=apiComponentService.getApiComponentAll();
//        System.out.println("list:"+list);
        jsonObject.put("data", apiComponentService.getApiComponentAll());
//        System.out.println("jsonObject:"+jsonObject.toJSONString());
        return JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect);
    }

    @RequestMapping(value = "/apiComponentAllByPage", method = RequestMethod.POST)
    @ResponseBody
    public Page<ApiComponentEntity> getAllByPage(int pageNumber, int pageSize) {
        return apiComponentService.getAllByPage(pageNumber, pageSize);
    }

    @RequestMapping(value = "/apiComponentById", method = RequestMethod.POST)
    @ResponseBody
    public @NotNull ApiComponentEntity getApiComponentById(@NotNull Integer id){
        return apiComponentService.getApiComponentById(id);
    }

    @RequestMapping(value = "/deleteApiComponentById", method = RequestMethod.POST)
    @ResponseBody
    public String deleteApiComponentById(int id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", apiComponentService.deleteApiComponentById(id));
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/saveApiComponent", method = RequestMethod.POST)
    @ResponseBody
    public String saveApiComponent(@NotNull ApiComponentEntity apiComponentEntity) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", apiComponentService.saveApiComponent(apiComponentEntity));
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/updateApiComponent", method = RequestMethod.POST)
    @ResponseBody
    public String updateApiComponent(@NotNull ApiComponentEntity apiComponentEntity) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", apiComponentService.updateApiComponent(apiComponentEntity));
        return jsonObject.toJSONString();
    }
}
