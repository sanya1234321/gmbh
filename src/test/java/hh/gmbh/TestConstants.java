package hh.gmbh;

import hh.gmbh.db.entities.CountValueProjection;
import hh.gmbh.db.entities.GmbhStringEntity;

public class TestConstants {

    public static final String TEST_STRING = "test";

    public static final Long TEST_LONG = 3l;

    public static final Integer TEST_INTEGER = 1;

    public static final CountValueProjection TEST_COUNT_VALUE = CountValueProjection
                                                                .builder()
                                                                .value(TEST_STRING)
                                                                .count(TEST_LONG)
                                                                .build();

    public static final GmbhStringEntity TEST_GMBH_STRING = GmbhStringEntity
                                                                .builder()
                                                                .value(TEST_STRING)
                                                                .length(TEST_STRING.length())
                                                                .build();

}
