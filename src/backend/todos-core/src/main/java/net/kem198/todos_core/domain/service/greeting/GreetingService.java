package net.kem198.todos_core.domain.service.greeting;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    private static final String template = "Hello, %s!\n";

    public String execute(String name) {
        return String.format(template, name);
    }
}
