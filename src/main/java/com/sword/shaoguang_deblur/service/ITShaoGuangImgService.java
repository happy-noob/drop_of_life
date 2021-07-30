package com.sword.shaoguang_deblur.service;/*
 *    Create By Yelson Li on 2021/7/27.
 *
 */

import com.sword.shaoguang_deblur.entity.vo.resp.DeblurImg;

public interface ITShaoGuangImgService {
    // base64字符转图片
    DeblurImg base64ToImg(String base64str, String suffix);
}
