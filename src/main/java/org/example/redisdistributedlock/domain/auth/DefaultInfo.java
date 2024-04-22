package org.example.redisdistributedlock.domain.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DefaultInfo {
    private Long id;
    private String name;
    public DefaultInfo(String name) {
        this.name = name;
    }

    public DefaultInfo(Long id) {
        this.id = id;
    }

    public static DefaultInfo of(Long id) {
        return new DefaultInfo(id);
    }
}
