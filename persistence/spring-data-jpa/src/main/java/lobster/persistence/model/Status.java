package lobster.persistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: spawn
 * Date: 09/11/13
 * Time: 00:22
 */
@Entity
public class Status implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private Long totalCalories;

    @Getter
    @Setter
    private Long fatLevel;

    @Getter
    @Setter
    private Long happiness;

    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date lastEat;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "status_vitamines")
    @Getter
    @Setter
    private Set<VitamineAmount> vitamineAmountList;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Lobster lobster;

    public Status() {
        totalCalories = 50L;
        fatLevel = 50L;
        happiness = 50L;
        lastEat = new Date();
        vitamineAmountList = new HashSet<>();
    }

}
