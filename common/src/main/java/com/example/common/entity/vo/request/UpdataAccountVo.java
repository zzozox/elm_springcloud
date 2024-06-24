package com.example.common.entity.vo.request;

import lombok.Data;

@Data
public class UpdataAccountVo {
    private Integer userId;
    private String username;
    private Integer userSex;
    private String userImg;
    private String password;
}
