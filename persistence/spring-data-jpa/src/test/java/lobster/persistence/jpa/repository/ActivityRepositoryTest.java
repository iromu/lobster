package lobster.persistence.jpa.repository;

import com.google.common.collect.Lists;
import lobster.persistence.model.Activity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: wantez
 * Date: 01/12/13
 * Time: 18:36
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class ActivityRepositoryTest {
    @Inject
    ActivityRepository activityRepository;

    @Test
    public void findAll() {
        Iterable<Activity> activities = activityRepository.findAll();
        List<Activity> arrayList = Lists.newArrayList(activities);
        assertThat(arrayList.size(), is(5));

    }
}
