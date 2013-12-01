package lobster.persistence.jpa.repository;

import com.google.common.collect.Lists;
import lobster.persistence.model.Food;
import lobster.persistence.model.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class StatusRepositoryTest {
    @Inject
    StatusRepository statusRepository;

    @Test
    public void findAll() {
        Iterable<Status> iterable = statusRepository.findAll();
        ArrayList<Status> arrayList = Lists.newArrayList(iterable);
        assertThat(arrayList.size(),is(4));
    }
}
