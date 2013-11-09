package lobster.server.rest.model;

import javax.persistence.*;
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

    @Enumerated(EnumType.STRING)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vitamine vitamine = (Vitamine) o;

        if (id != null ? !id.equals(vitamine.id) : vitamine.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
