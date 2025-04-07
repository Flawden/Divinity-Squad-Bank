package ru.flawden.divinitybankspring.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.flawden.divinitybankspring.entity.Person;
import ru.flawden.divinitybankspring.security.PersonDetails;

@Component
public class AuthUtil {

    public Person getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof PersonDetails personDetails) {
            return personDetails.getPerson();
        }
        throw new IllegalStateException("User not authenticated");
    }
}
