package com.sword.shaoguang_deblur.entity.vo.resp;/*
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
public class DeblurImg {
    private String outputPath;
}
