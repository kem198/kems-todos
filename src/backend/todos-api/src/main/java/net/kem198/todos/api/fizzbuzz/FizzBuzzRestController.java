package net.kem198.todos.api.fizzbuzz;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.kem198.todos.core.domain.service.fizzbuzz.FizzBuzzService;

@RestController
@RequestMapping("/v1/fizzbuzz")
public class FizzBuzzRestController {

    private final FizzBuzzService fizzBuzzService;
    private final FizzBuzzResourceMapper fizzBuzzResourceMapper;

    public FizzBuzzRestController(FizzBuzzService fizzBuzzService, FizzBuzzResourceMapper fizzBuzzResourceMapper) {
        this.fizzBuzzService = fizzBuzzService;
        this.fizzBuzzResourceMapper = fizzBuzzResourceMapper;
    }

    @GetMapping("/convert")
    public ResponseEntity<FizzBuzzResource> convert(@RequestParam(value = "num") int number) {
        String result = fizzBuzzService.processFizzBuzz(number);
        FizzBuzzResource fizzBuzzResource = fizzBuzzResourceMapper.toResource(result);
        return ResponseEntity.ok(fizzBuzzResource);
    }
}
