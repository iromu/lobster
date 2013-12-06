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
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Status implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private Integer totalCalories;

    @Getter
    @Setter
    private Integer fatLevel;

    @Getter
    @Setter
    private Integer happiness;

    @Temporal(TemporalType.TIMESTAMP)
    @Getter
    @Setter
    private Date lastEat;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "status_vitamines")
    @Getter
    @Setter
    private Set<VitamineAmount> vitamineAmountList;

    public Status() {
        totalCalories = new Integer(50);
        fatLevel = new Integer(50);
        happiness = new Integer(50);
        lastEat = new Date();
        vitamineAmountList = new HashSet<>();
    }

}
