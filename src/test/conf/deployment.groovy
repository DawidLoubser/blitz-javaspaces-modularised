import org.rioproject.config.Constants
import java.util.logging.Level
import java.util.concurrent.TimeUnit
import org.rioproject.associations.strategy.Utilization

deployment(name:'Basic Blitz Deployment')
{
    /* Configuration for the discovery group that the services should join */
    groups System.getProperty(Constants.GROUPS_PROPERTY_NAME, System.getProperty('user.name'))

    // Blitz
    artifact id: 'blitz-dl',   'org.dancres:blitz-javaspaces:jar:dl:2.1.5'
    artifact id: 'blitz-impl', 'org.dancres:blitz-javaspaces:2.1.5'


    logging
    {
//      logger 'com.travellinck', Level.INFO
    }


    /** Infrastructure service: Space */
    service(name: 'Space')
    {
        interfaces
        {
            classes 'net.jini.space.JavaSpace05'
            artifact ref: 'blitz-dl'
        }

        implementation(class: 'org.dancres.blitz.remote.BlitzServiceImpl')
        {
            artifact ref: 'blitz-impl'
        }

        maintain 1

    }
}