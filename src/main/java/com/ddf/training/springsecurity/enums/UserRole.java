package com.ddf.training.springsecurity.enums;

import com.google.common.collect.Sets;
import lombok.Getter;

import java.util.Set;

import static com.ddf.training.springsecurity.enums.UserPermission.*;

@Getter
public enum UserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE));

    private final Set<UserPermission> userPermissions;

    UserRole(Set<UserPermission> userPermissions) {
        this.userPermissions = userPermissions;
    }
}
