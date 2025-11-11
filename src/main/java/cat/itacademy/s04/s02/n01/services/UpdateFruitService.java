package cat.itacademy.s04.s02.n01.services;

import cat.itacademy.s04.s02.n01.Command;
import cat.itacademy.s04.s02.n01.exception.FruitNotFoundException;
import cat.itacademy.s04.s02.n01.model.Fruit;
import cat.itacademy.s04.s02.n01.model.FruitDTO;
import cat.itacademy.s04.s02.n01.model.PutFruitCommand;
import cat.itacademy.s04.s02.n01.repository.FruitRepositoryInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UpdateFruitService implements Command<PutFruitCommand, FruitDTO> {

    private final FruitRepositoryInterface fruitRepository;

    public UpdateFruitService(FruitRepositoryInterface fruitRepository){
        this.fruitRepository = fruitRepository;
    }

    @Override
    public ResponseEntity<FruitDTO> execute(PutFruitCommand putFruitCommand) {
        Optional<Fruit> foundFruitOptional = fruitRepository.findById(putFruitCommand.getId());
        if (foundFruitOptional.isPresent()){
            Fruit fruit = putFruitCommand.getFruit();
            fruit.setId(putFruitCommand.getId());
            fruitRepository.save(fruit);
            return ResponseEntity.ok(new FruitDTO(fruit));
        }
        throw new FruitNotFoundException();
    }
}