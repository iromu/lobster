package lobster.persistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: spawn
 * Date: 09/11/13
 * Time: 00:11
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Activity implements Serializable {

    enum ActivityType {SLEEP, RUN, PLAY, TV, HOMEWORK}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private ActivityType foodType;

    @Getter
    @Setter
    private Integer calories;

    @Getter
    @Setter
    private Integer fatLevel;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private Integer hours;

    @Getter
    @Setter
    private Integer happiness;

}
