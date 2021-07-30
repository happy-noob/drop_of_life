package com.sword.shaoguang_deblur.entity.vo.req;/*
 *    Create By Yelson Li on 2021/7/27.
 *
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BlurImgBase64 {
    // 图片文件base64编码字符串
    private String base64str;

    // 图片文件名后缀
    private String suffix;
}
