package lobster.persistence.model;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer totalCalories;

    private Integer fatLevel;

    private Integer happiness;

    private Date lastEat;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="status_vitamines")
    private Set<VitamineAmount> vitamineAmountList;

    public Status()
    {
        totalCalories = new Integer(50);
        fatLevel = new Integer(50);
        happiness  =new Integer(50);
        lastEat = new Date();
        vitamineAmountList = new HashSet<VitamineAmount>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotalCalories() {
        return totalCalories == null? 0 : totalCalories;
    }

    public void setTotalCalories(Integer totalCalories) {
        this.totalCalories = totalCalories;
    }

    public Integer getFatLevel() {
        return fatLevel!=null?fatLevel:0;
    }

    public void setFatLevel(Integer fatLevel) {
        this.fatLevel = fatLevel;
    }

    public Integer getHappiness() {
        return happiness!=null?happiness:0;
    }

    public void setHappiness(Integer happiness) {
        this.happiness = happiness;
    }

    public Date getLastEat() {
        return lastEat;
    }

    public void setLastEat(Date lastEat) {
        this.lastEat = lastEat;
    }


    public Set<VitamineAmount> getVitamineAmountList() {
        return vitamineAmountList;
    }

    public void setVitamineAmountList(Set<VitamineAmount> vitamineAmountList) {
        this.vitamineAmountList = vitamineAmountList;
    }
}
