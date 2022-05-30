package com.raferlyan.words.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author raferlyan
 * @date 2022/5/30 09:11
 **/
@Getter
@Setter
public class WordsWriteDto {

    private String words;

    private String translation;

    private String remark;
}
