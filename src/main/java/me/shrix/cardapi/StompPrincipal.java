package me.shrix.cardapi;

import javax.security.auth.Subject;
import java.security.Principal;

public class StompPrincipal implements Principal {

    private String name;

    StompPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }
}
