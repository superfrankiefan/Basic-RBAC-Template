package com.sff.rbacdemo.common.model;

import lombok.Data;

import java.io.File;

/**
 * @author Frankie Fan
 * @date 2022-04-29 14:48
 */

@Data
public class MailBean {
    private String subject;
    private String content;
    private String toAccount;
    private File attachmentFile;
    private String attachmentFileName;
}
