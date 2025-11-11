package cat.itacademy.s04.s02.n01.services;

import cat.itacademy.s04.s02.n01.Query;
import cat.itacademy.s04.s02.n01.exception.FruitNotFoundException;
import cat.itacademy.s04.s02.n01.model.Fruit;
import cat.itacademy.s04.s02.n01.model.FruitDTO;
import cat.itacademy.s04.s02.n01.repository.FruitRepositoryInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ReadFruitByIdService implements Query<Integer, FruitDTO> {

    private final FruitRepositoryInterface fruitRepository;

    public ReadFruitByIdService(FruitRepositoryInterface fruitRepository){
        this.fruitRepository = fruitRepository;
    }

    @Override
    public ResponseEntity<FruitDTO> execute(Integer fruitId) {
        Optional<Fruit> foundFruitOptional = fruitRepository.findById(fruitId);
        if(foundFruitOptional.isPresent()){
            return ResponseEntity.ok(new FruitDTO(foundFruitOptional.get()));
        }
        throw new FruitNotFoundException();
    }
}