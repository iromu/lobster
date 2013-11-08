package lobster.server.rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: spawn
 * Date: 08/11/13
 * Time: 23:47
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Vitamine implements Serializable {

    enum VitamineType {VITAMINE_A, VITAMINE_B, VITAMINE_C, CALCIUM }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private VitamineType vitamineType;

    private String name;

    private String description;

    private Integer amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public VitamineType getVitamineType() {
        return vitamineType;
    }

    public void setVitamineType(VitamineType vitamineType) {
        this.vitamineType = vitamineType;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
