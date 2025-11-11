package cat.itacademy.s04.s02.n01.services;

import cat.itacademy.s04.s02.n01.Command;
import cat.itacademy.s04.s02.n01.model.Fruit;
import cat.itacademy.s04.s02.n01.model.FruitDTO;
import cat.itacademy.s04.s02.n01.repository.FruitRepositoryInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateFruitService implements Command<Fruit, FruitDTO> {

    private final FruitRepositoryInterface fruitRepository;

    public CreateFruitService(FruitRepositoryInterface fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    @Override
    public ResponseEntity<FruitDTO> execute(Fruit fruit) {
        Fruit savedFruit = fruitRepository.save(fruit);
        return ResponseEntity.status(HttpStatus.CREATED).body(new FruitDTO(savedFruit));
    }
}