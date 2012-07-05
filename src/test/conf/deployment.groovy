import org.rioproject.config.Constants
import java.util.logging.Level

/** Most basic example of deploying a default Blitz instance.
 * See Blitz documentation for configuration options.
 */
deployment(name:'Basic Blitz Deployment')
{
    /* Configuration for the discovery group that the services should join */
    groups System.getProperty(Constants.GROUPS_PROPERTY_NAME, System.getProperty('user.name'))

    // Blitz
    artifact id: 'blitz-dl',   'org.dancres:blitz-javaspaces-mr:jar:dl:2.1.5'
    artifact id: 'blitz-impl', 'org.dancres:blitz-javaspaces-mr:2.1.5'


    logging
    {
      logger 'com.travellinck', Level.INFO
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


        // Sample configuration for a basic, untuned, default instance. See Blitz
        // sample configs for details
        configuration '''
        import net.jini.security.BasicProxyPreparer;
        import net.jini.jeri.tcp.TcpServerEndpoint;
        import net.jini.jeri.ProxyTrustILFactory;
        import net.jini.jeri.BasicJeriExporter;
        import org.dancres.blitz.stats.Switch;
        import org.dancres.blitz.stats.OpSwitch;
        import org.dancres.blitz.stats.InstanceSwitch;
        import org.dancres.blitz.config.Transient;

        org.dancres.blitz
        {

            syncNotifyOnWrite = true;
            ignoreLogConfig = new Boolean(true);
            name = "Space";
            // TODO: Auto-set to somehwere in RIO install dir
            persistDir = "${RIO_HOME}/system/external/blitz/data";
            logDir = "${RIO_HOME}/system/external/blitz/log";
            maxWriteThreads = 1;
            desiredPendingWrites = 256;
            dbCache = 27262976;
            maxDbTxns = 256;
            dumpDbStats = new Boolean(false);
            logCkpts = new Boolean(false);
            entryReposCacheSize = 4096;
            cacheEntriesPerPartition = 32;
            loadBackoff = new int[] {20, 50};
            entryLeaseBound = 0;
            notifyLeaseBound = 0;
            leaseReapInterval = 0;
            maxOidAllocators = 512;
            eventgenSaveInterval = 500;
            eventgenRestartJump = 1000;
            maxTaskThreads = 8;
            maxEventProcessors = 4;

            // TODO: Configure TimeBarrierPersistent/Persistent
            storageModel = new Transient();

            statsDump = 60000;
            compliantDestroy = new Boolean(false);
            stats = new Switch[] {new OpSwitch(OpSwitch.ALL_TYPES,
            OpSwitch.TAKE_OPS, true),
            new OpSwitch(OpSwitch.ALL_TYPES, OpSwitch.READ_OPS, true),
            new OpSwitch(OpSwitch.ALL_TYPES, OpSwitch.WRITE_OPS, true),
            new InstanceSwitch(InstanceSwitch.ALL_TYPES, true)};
            serverExporter = new BasicJeriExporter( TcpServerEndpoint.getInstance(0),
                                  new ProxyTrustILFactory(null, null), false, true);
            notifyPreparer = new BasicProxyPreparer();
            recoveredNotifyPreparer = new BasicProxyPreparer();
            txnPreparer = new BasicProxyPreparer();
            recoveredTxnPreparer = new BasicProxyPreparer();
            activationIdPreparer = new BasicProxyPreparer();
            activationSysPreparer = new BasicProxyPreparer();
        }
        '''

        maintain 1
    }
}