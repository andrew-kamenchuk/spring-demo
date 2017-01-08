package org.j.products;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.j.products.config.AppConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Andrew on 1/9/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
abstract public class AbstractTest {
    protected final Logger logger = LogManager.getLogger(getClass());

    protected Logger getLogger() {
        return logger;
    }
}
