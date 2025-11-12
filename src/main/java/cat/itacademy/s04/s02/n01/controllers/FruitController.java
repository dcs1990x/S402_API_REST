package cat.itacademy.s04.s02.n01.controllers;

import cat.itacademy.s04.s02.n01.model.Fruit;
import cat.itacademy.s04.s02.n01.model.FruitDTO;
import cat.itacademy.s04.s02.n01.model.PutFruitCommand;
import cat.itacademy.s04.s02.n01.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class FruitController {

    private final CreateFruitService createFruitService;
    private final ReadFruitService readFruitService;
    private final ReadFruitByIdService readFruitByIdService;
    private final UpdateFruitService updateFruitService;
    private final DeleteFruitService deleteFruitService;

    public FruitController(CreateFruitService createFruitService,
                           ReadFruitService readFruitService,
                           ReadFruitByIdService readFruitByIdService,
                           UpdateFruitService updateFruitService,
                           DeleteFruitService deleteFruitService) {
        this.createFruitService = createFruitService;
        this.readFruitService = readFruitService;
        this.readFruitByIdService = readFruitByIdService;
        this.updateFruitService = updateFruitService;
        this.deleteFruitService = deleteFruitService;
    }

    @PostMapping("/fruit")
    public ResponseEntity<FruitDTO> postFruit(@RequestBody Fruit fruit){
        return createFruitService.execute(fruit);
    }

    @GetMapping("/fruits")
    public ResponseEntity<List<FruitDTO>> getFruits(){
        return readFruitService.execute(null);
    }

    @GetMapping("/fruit/{id}")
    public ResponseEntity<FruitDTO> getFruitById(@PathVariable int id){
        return readFruitByIdService.execute(id);
    }

    @PutMapping("/fruit/{id}")
    public ResponseEntity<FruitDTO> putFruit(@PathVariable int id, @RequestBody Fruit fruit){

        return updateFruitService.execute(new PutFruitCommand(id, fruit));
    }

    @DeleteMapping("/fruit/{id}")
    public ResponseEntity<Void> deleteFruit(@PathVariable int id){
        return deleteFruitService.execute(id);
    }
}