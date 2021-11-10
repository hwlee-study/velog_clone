package com.gteam.glog.main.controller;

import com.gteam.glog.common.utils.ResponseDTOUtils;
import com.gteam.glog.main.service.MainService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class MainController {

    private final MainService mainService;
    private final ResponseDTOUtils responseDTOUtils;

    @Autowired
    public MainController(MainService mainService, ResponseDTOUtils responseDTOUtils) {
        this.mainService = mainService;
        this.responseDTOUtils = responseDTOUtils;
    }

    @GetMapping(value = "/board")
    @ApiOperation(value = "메인 화면 API", notes = "메인 화면 게시물 조회 API")
    public ResponseEntity<?> setMainPage(@RequestHeader("pageNum") int pageNum, @RequestParam("sortRule") int sortRule, HttpServletResponse response) {


        return responseDTOUtils.doGenerateResponseDTO(null);
    }

}