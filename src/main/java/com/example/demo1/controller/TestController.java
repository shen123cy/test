package com.example.demo1.controller;

import com.example.demo1.dto.ParamDTO;
import com.example.demo1.service.def.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/test")
public class TestController {


    /*@Value("${test.diff.properties}")
    private String diffProperties;*/


    @Autowired
    private TestService testService;

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/t")
    public Object test() {
        testService.test();
//        ListOperations listOperations = redisTemplate.opsForList();
//        listOperations.leftPush()
        return "success";
    }

    /**
     * @param param
     * @return
     * @Validated
     */
    @GetMapping("/validated")
    public Object getValidatedTest(@Validated ParamDTO param) {
        return param;
    }

    /**
     * @param param
     * @return
     * @Validated
     */
    @PostMapping("/validated")
    public Object postValidatedTest(@Validated @RequestBody ParamDTO param) {
        return param;
    }

    /**
     * 文件上传
     *
     * @param multipartFile
     */
    @PostMapping("/uploadFile")
    public void uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        testService.uploadFile(multipartFile);
    }

    /**
     * 读取excel
     *
     * @param multipartFile
     */
    @PostMapping("/excel")
    public void excel(@RequestParam("file") MultipartFile multipartFile) {
        testService.excel(multipartFile);
    }


    /**
     * zip 上传
     *
     * @param multipartFile
     */
    @PostMapping("/uploadZip")
    public void uploadZip(@RequestParam("file") MultipartFile multipartFile) {
        testService.uploadZip(multipartFile);
    }


    @PostMapping("/gz")
    public void uploadGz(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        testService.uploadGz(multipartFile);
    }

}
