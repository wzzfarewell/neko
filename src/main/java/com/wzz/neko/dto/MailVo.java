package com.wzz.neko.dto;

import lombok.Data;

/**
 * MailVo
 *
 * @author wzzfarewell
 * @date 2020/2/24
 **/
@Data
public class MailVo {
    private String subject;
    private String toEmail;
    private String content;
}
