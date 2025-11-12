package cat.itacademy.s04.s02.n01.model;

import lombok.Data;

@Data
public class FruitDTO {

    private int id;
    private String name;
    private float weight;

    public FruitDTO(Fruit fruit) {
        this.id = fruit.getId();
        this.name = fruit.getName();
        this.weight = fruit.getWeight();
    }
}