package cat.itacademy.s04.s02.n01.fruit;

import cat.itacademy.s04.s02.n01.model.Fruit;
import cat.itacademy.s04.s02.n01.model.FruitDTO;
import cat.itacademy.s04.s02.n01.repository.FruitRepositoryInterface;
import cat.itacademy.s04.s02.n01.services.ReadFruitByIdService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class FruitApiH2ApplicationTests {

    @Mock private FruitRepositoryInterface fruitRepository;
    @InjectMocks ReadFruitByIdService readFruitServiceByIdService;

	@Test
	void contextLoads() {}

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenExistingFruit_WhenGetFruitService_ThenReturnFruitDTO(){
        Fruit fruit = new Fruit();
        fruit.setId(1);
        fruit.setName("banana");
        fruit.setWeight(0.15f);

        when(fruitRepository.findAllById(1)).thenReturn(Optional.of(fruit));

        ResponseEntity<FruitDTO> response = readFruitServiceByIdService.execute(1);

        assertEquals(ResponseEntity.ok(new FruitDTO(fruit)), response);
    }
}
