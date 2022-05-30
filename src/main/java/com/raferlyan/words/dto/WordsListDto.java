package com.raferlyan.words.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author raferlyan
 * @date 2022/5/30 08:49
 **/
@Getter
@Setter
public class WordsListDto {

    private Integer id;

    private String words;

    private String translation;

    private String remark;
}
