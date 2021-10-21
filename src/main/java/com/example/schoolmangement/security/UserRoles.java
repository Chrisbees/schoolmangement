//package com.example.schoolmangement.security;
//
//import com.google.common.collect.*;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import static com.example.schoolmangement.security.UserRolePermission.*;
//
//
//public enum UserRoles {
//    ADMIN(Sets.newHashSet(STAFF_READ, STAFF_WRITE, STAFF_DELETE,
//            STUDENT_READ, STUDENT_WRITE, STUDENT_DELETE, STUDENT_REGISTER, PARENT_REGISTER)),
//    PARENTS(Sets.newHashSet(STUDENT_REGISTER, PARENT_REGISTER));
//
//    private final Set<UserRolePermission> permissions;
//
//
//    UserRoles(Set<UserRolePermission> permissions) {
//        this.permissions = permissions;
//    }
//    public Set<UserRolePermission> getPermissions() {
//        return permissions;
//    }
//
//    public Set<SimpleGrantedAuthority> grantedAuthorities(){
//        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
//                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
//                .collect(Collectors.toSet());
//        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
//        return permissions;
//    }
//}
