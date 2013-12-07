package lobster.persistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: spawn
 * Date: 08/11/13
 * Time: 23:39
 *
 */
@Entity
public class Food implements Serializable {

    enum FoodType {VEGETABLE, MEAT, FISH, LEGUME, CANDY, FRUIT, CEREAL}


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private FoodType foodType;

    @Getter
    @Setter
    private Integer calories;

    @Getter
    @Setter
    private Integer fatLevel;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "food_vitamines",
            joinColumns = {@JoinColumn(name = "food_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "vitamineamount_id", referencedColumnName = "id")})
    @Getter
    @Setter
    private Set<VitamineAmount> vitamines;

    @Column(nullable = false)
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private Integer happiness;

}
