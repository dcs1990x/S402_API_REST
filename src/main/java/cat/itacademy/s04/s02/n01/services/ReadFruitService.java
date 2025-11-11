package cat.itacademy.s04.s02.n01.services;

import cat.itacademy.s04.s02.n01.Query;
import cat.itacademy.s04.s02.n01.model.Fruit;
import cat.itacademy.s04.s02.n01.model.FruitDTO;
import cat.itacademy.s04.s02.n01.repository.FruitRepositoryInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReadFruitService implements Query<Fruit, List<FruitDTO>> {

    private final FruitRepositoryInterface fruitRepository;

    public ReadFruitService(FruitRepositoryInterface fruitRepository){
        this.fruitRepository = fruitRepository;
    }

    @Override
    public ResponseEntity<List<FruitDTO>> execute(Fruit fruit) {
        List<Fruit> foundFruits = fruitRepository.findAll();
        List<FruitDTO> fruitDTOs = foundFruits.stream().map(FruitDTO::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(fruitDTOs);
    }
}