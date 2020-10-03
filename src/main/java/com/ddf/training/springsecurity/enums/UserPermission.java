package com.ddf.training.springsecurity.enums;

import lombok.Getter;

@Getter
public enum UserPermission {

    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");

    private String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }
}
