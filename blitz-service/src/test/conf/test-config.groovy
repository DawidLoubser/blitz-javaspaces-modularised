ITBlitzRioDeployment
{
    groups = System.getProperty('user.name') + ".test.ITBlitzRioDeployment"
    numCybernodes = 1
    numMonitors = 1
    opstring = 'src/test/conf/deployment.groovy'
    autoDeploy = true
}

