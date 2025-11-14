package cat.itacademy.s04.s02.n01.controllers;

import cat.itacademy.s04.s02.n01.model.Fruit;
import cat.itacademy.s04.s02.n01.model.FruitDTO;
import cat.itacademy.s04.s02.n01.model.PutFruitCommand;
import cat.itacademy.s04.s02.n01.services.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name="Search API", description="Fruit search API")
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
    @Operation(summary = "Create a fruit")
    @ApiResponse(responseCode = "201", description = "The fruit was created successfully")
    public ResponseEntity<FruitDTO> postFruit(@RequestBody Fruit fruit){
        return createFruitService.execute(fruit);
    }

    @GetMapping("/fruits")
    @Operation(summary = "Get all fruits")
    @ApiResponse(responseCode = "200", description = "All fruits are found")
    @ApiResponse(responseCode = "404", description = "No fruit was found")
    public ResponseEntity<List<FruitDTO>> getFruits(){
        return readFruitService.execute(null);
    }

    @GetMapping("/fruit/{id}")
    @Operation(summary = "Find fruit by ID")
    @ApiResponse(responseCode = "200", description = "The fruit was found")
    @ApiResponse(responseCode = "404", description = "The fruit was not found")
    public ResponseEntity<FruitDTO> getFruitById(@PathVariable int id){
        return readFruitByIdService.execute(id);
    }

    @PutMapping("/fruit/{id}")
    @Operation(summary = "Update fruit by ID")
    @ApiResponse(responseCode = "200", description = "The fruit was updated successfully")
    @ApiResponse(responseCode = "201", description = "The fruit was created successfully")
    public ResponseEntity<FruitDTO> putFruit(@PathVariable int id, @RequestBody Fruit fruit){
        return updateFruitService.execute(new PutFruitCommand(id, fruit));
    }

    @DeleteMapping("/fruit/{id}")
    @Operation(summary = "Delete fruit by ID")
    @ApiResponse(responseCode = "200", description = "The fruit was deleted successfully")
    public ResponseEntity<Void> deleteFruit(@PathVariable int id){
        return deleteFruitService.execute(id);
    }
}