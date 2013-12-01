package lobster.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: spawn
 * Date: 08/11/13
 * Time: 23:39
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Food implements Serializable {

    enum FoodType {VEGETABLE, MEAT, FISH, LEGUME, CANDY, FRUIT, CEREAL}


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    private Integer calories;

    private Integer fatLevel;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "food_vitamines",
            joinColumns = {@JoinColumn(name = "food_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "vitamineamount_id", referencedColumnName = "id")})
    private Set<VitamineAmount> vitamines;

    @Column(nullable = false)
    private String name;

    private String description;

    private Integer happiness;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Set<VitamineAmount> getVitamines() {
        return vitamines;
    }

    public void setVitamines(Set<VitamineAmount> vitamines) {
        this.vitamines = vitamines;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHappiness() {
        return happiness;
    }

    public Integer getFatLevel() {
        return fatLevel;
    }

    public void setFatLevel(Integer fatLevel) {
        this.fatLevel = fatLevel;
    }

    public void setHappiness(Integer happiness) {
        this.happiness = happiness;
    }


}
