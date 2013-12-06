package lobster.persistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: spawn
 * Date: 09/11/13
 * Time: 02:05
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class VitamineAmount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @Getter
    @Setter
    private Vitamine vitamine;

    @Getter
    @Setter
    private Integer amount;

}