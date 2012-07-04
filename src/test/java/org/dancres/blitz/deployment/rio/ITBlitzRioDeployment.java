package org.dancres.blitz.deployment.rio;

import net.jini.core.lease.Lease;
import net.jini.lookup.entry.Address;
import net.jini.space.JavaSpace05;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rioproject.test.RioTestRunner;
import org.rioproject.test.SetTestManager;
import org.rioproject.test.TestManager;

import java.util.logging.Logger;

import static org.junit.Assert.assertNotNull;

/**
 * A simple test to check that we can deploy Blitz as a Rio-provisioned service.
 * @author Dawid Loubser (dawid@travellinck.com)
 */
@RunWith(RioTestRunner.class)
public class ITBlitzRioDeployment
{
    /** Simple behaviour that should work with any space (e.g. Outrigger) */
    @Test
    public void testWriteTake_transient() throws Exception
    {
        Address a = new Address();
        a.country = "ZA";
        a.locality = "Johannesburg";
        logger.info("Publishing: " + a);
        space.write(a, null, Lease.FOREVER);

        Address b = new Address();
        b.country = "ZA";
        b.locality = "Cape Town";
        logger.info("Publishing: " + b);
        space.write(b, null, Lease.FOREVER);

        Address template = new Address();
        template.country = "ZA";

        // Should find two entries with this template
        Address sA = (Address)space.take(template, null, 0);
        Address sB = (Address)space.take(template, null, 0);

        assertNotNull(sA);
        assertNotNull(sB);
    }

    @Before
    public void getHandleToSpace() throws Exception
    {
        space = (JavaSpace05) testManager.waitForService(JavaSpace05.class);
    }


    @SetTestManager
    static TestManager testManager;
    private static final Logger logger = Logger.getLogger( ITBlitzRioDeployment.class.getName());
    JavaSpace05 space;
}
