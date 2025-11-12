package cat.itacademy.s04.s02.n01.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@Table(name="fruits")
public class Fruit {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NonNull
    @NotBlank
    @Pattern(regexp = "^[^0-9]*$", message = "The name cannot contain any numbers.")
    private String name;

    @Column(name = "weight")
    @NonNull
    @PositiveOrZero(message="Weight cannot be neither 0 nor negative.")
    private Float weight;

    public Fruit(String name, Float weight){
        this.name = name;
        this.weight = weight;
    }
}