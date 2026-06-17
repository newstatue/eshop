package com.evorsio.eshop.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Evorsio
 * @since 2026/6/16
 * Token返回结果
 */
@Data
public class TokenVo {
    private String accessToken;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expireAt;
}