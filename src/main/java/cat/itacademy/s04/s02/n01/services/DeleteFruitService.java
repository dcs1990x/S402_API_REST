package cat.itacademy.s04.s02.n01.services;

import cat.itacademy.s04.s02.n01.Command;
import cat.itacademy.s04.s02.n01.exception.FruitNotFoundException;
import cat.itacademy.s04.s02.n01.model.Fruit;
import cat.itacademy.s04.s02.n01.repository.FruitRepositoryInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class DeleteFruitService implements Command<Long, Void>{

    private final FruitRepositoryInterface fruitRepository;

    public DeleteFruitService(FruitRepositoryInterface fruitRepository){
        this.fruitRepository = fruitRepository;
    }

    @Override
    public ResponseEntity<Void> execute(Long fruitId) {
        Optional<Fruit> foundFruitOptional = fruitRepository.findById(fruitId);
        if(foundFruitOptional.isPresent()){
            fruitRepository.deleteById(fruitId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        throw new FruitNotFoundException();
    }
}