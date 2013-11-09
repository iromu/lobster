package lobster.server.rest.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

    private Integer idealCalories;

    private Integer happiness;

    private Date lastEat;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<StatusVitamine> statusVitamineList;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(Integer totalCalories) {
        this.totalCalories = totalCalories;
    }

    public Integer getIdealCalories() {
        return idealCalories;
    }

    public void setIdealCalories(Integer idealCalories) {
        this.idealCalories = idealCalories;
    }

    public Integer getHappiness() {
        return happiness;
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


    public List<StatusVitamine> getStatusVitamineList() {
        return statusVitamineList;
    }

    public void setStatusVitamineList(List<StatusVitamine> statusVitamineList) {
        this.statusVitamineList = statusVitamineList;
    }
}
