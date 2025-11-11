package cat.itacademy.s04.s02.n01.model;

import lombok.Getter;

@Getter
public class PutFruitCommand {
    private int Id;
    private Fruit fruit;

    public PutFruitCommand(int id, Fruit fruit) {
        this.Id = id;
        this.fruit = fruit;
    }
}