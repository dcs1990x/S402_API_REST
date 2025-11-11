package cat.itacademy.s04.s02.n01.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name="fruits")
public class Fruit {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[^0-9]*$", message = "The name cannot contain any numbers.")
    private String name;

    @Column(name = "weight")
    @PositiveOrZero(message="Weight cannot be neither 0 nor negative.")
    private float weight;
}