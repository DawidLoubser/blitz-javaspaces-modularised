import org.rioproject.config.Constants
import java.util.logging.Level

/** Most basic example of deploying a default Blitz instance.
 * See Blitz documentation for configuration options.
 */
deployment(name:'Basic Blitz Deployment') {
    /* Configuration for the discovery group that the services should join */
    groups System.getProperty(Constants.GROUPS_PROPERTY_NAME, System.getProperty('user.name'))

    // Blitz
    artifact id: 'blitz-dl',   'org.dancres.blitz:blitz-proxy:2.2.1'
    artifact id: 'blitz-impl', 'org.dancres.blitz:blitz-service:2.2.1'

    logging {
      logger 'com.travellinck', Level.INFO
    }


    /** Infrastructure service: Space */
    service(name: 'Space') {
        interfaces {
            classes 'net.jini.space.JavaSpace05'
            artifact ref: 'blitz-dl'
        }

        implementation(class: 'org.dancres.blitz.remote.BlitzServiceImpl') {
            artifact ref: 'blitz-impl'
        }


        // Sample configuration for a basic, untuned, default instance. See Blitz
        // sample configs for details
        //configuration "file:src/test/conf/blitz.config"
        configuration "file:src/test/conf/BlitzConfig-Persistent.groovy"

        maintain 1
    }
}