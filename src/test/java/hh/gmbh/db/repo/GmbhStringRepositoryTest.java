package hh.gmbh.db.repo;

import hh.gmbh.db.entities.CountValueProjection;
import hh.gmbh.db.entities.GmbhStringEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static hh.gmbh.TestConstants.TEST_GMBH_STRING;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GmbhStringRepositoryTest {

    @Autowired
    GmbhStringRepository gmbhStringRepository;

    @Test
    public void save() {
        GmbhStringEntity saved = gmbhStringRepository.save(TEST_GMBH_STRING);
        GmbhStringEntity found = gmbhStringRepository.findOne(saved.getId());

        assertThat(found.getValue()).isEqualTo(saved.getValue());
        assertThat(found.getLength()).isEqualTo(saved.getLength());
    }

    @Test
    public void getAverageLength() {
        gmbhStringRepository.save(TEST_GMBH_STRING);
        gmbhStringRepository.save(TEST_GMBH_STRING);

        assertThat(gmbhStringRepository.getAverageLength()).isEqualTo(TEST_GMBH_STRING.getLength());
    }

    @Test
    public void countStrings() {
        String testString2Value = "test2";

        gmbhStringRepository.save(TEST_GMBH_STRING);
        gmbhStringRepository.save(GmbhStringEntity
                                    .builder()
                                    .value(TEST_GMBH_STRING.getValue())
                                    .length(TEST_GMBH_STRING.getLength())
                                    .build());

        gmbhStringRepository.save(GmbhStringEntity
                                    .builder()
                                    .value(testString2Value)
                                    .length(testString2Value.length())
                                    .build());
        List<CountValueProjection> countValueProjections = gmbhStringRepository.countStrings();

        assertThat(countValueProjections.size()).isEqualTo(2);

        countValueProjections.forEach(cvp -> {
            if (TEST_GMBH_STRING.getValue().equals(cvp.getValue())) {
                assertThat(cvp.getCount()).isEqualTo(2);
            } else if (testString2Value.equals(cvp.getValue())) {
                assertThat(cvp.getCount()).isEqualTo(1);
            }
        });
    }

}
