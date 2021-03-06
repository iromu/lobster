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
 *
 */
@Entity
public class Activity implements Serializable {

    enum ActivityType {SLEEP, RUN, PLAY, TV, HOMEWORK}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private ActivityType foodType;

    @Getter
    @Setter
    private Long calories;

    @Getter
    @Setter
    private Long fatLevel;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private Long hours;

    @Getter
    @Setter
    private Long happiness;

}
