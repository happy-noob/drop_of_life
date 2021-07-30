package com.sword.shaoguang_deblur.controller;/*
 *    Create By Yelson Li on 2021/7/27.
 *
 */

import com.sword.shaoguang_deblur.base.CosmoController;
import com.sword.shaoguang_deblur.base.ResultVO;
import com.sword.shaoguang_deblur.entity.vo.req.BlurImgBase64;
import com.sword.shaoguang_deblur.entity.vo.resp.DeblurImg;
import com.sword.shaoguang_deblur.service.ITShaoGuangImgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@CosmoController
@RequestMapping("/shaoguang_deblur/img")
public class TShaoGuangImg {
    @Autowired
    ITShaoGuangImgService shaoGuangImgService;

    @PostMapping("/deblur")
    public ResultVO<DeblurImg> getDeblurImg(@RequestBody BlurImgBase64 blurImgBase64) {
        return ResultVO.success(shaoGuangImgService.base64ToImg(blurImgBase64.getBase64str(),
                blurImgBase64.getSuffix()));
    }
}
