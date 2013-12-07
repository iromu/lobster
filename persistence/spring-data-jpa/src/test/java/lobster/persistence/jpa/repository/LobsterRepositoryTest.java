package lobster.persistence.jpa.repository;

import com.google.common.collect.Lists;
import lobster.persistence.model.Food;
import lobster.persistence.model.Lobster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: wantez
 * Date: 01/12/13
 * Time: 18:37
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class LobsterRepositoryTest {
    @Inject
    LobsterRepository lobsterRepository;

    @Test
    public void findAll() {
        Iterable<Lobster> iterable = lobsterRepository.findAll();
        ArrayList<Lobster> arrayList = Lists.newArrayList(iterable);
        assertThat(arrayList.size(),is(6));
    }
}
