# AcmeAir

## Deployment instructions:
1. Edit the deployment yaml of the main microservice to a hostname of your choice with the path `/acmeair` (ex: `http://acmeair.apps.lone.cp.fyre.ibm.com/acmeair`)
2. Edit the deployment yaml of each microservice to have the same hostname as the main service but with the correct path (ex: ` http://acmeair.apps.lone.cp.fyre.ibm.com/auth`
3. Before applying the deployment, create a ConfigMap with the routes with the following command, replacing the variables with the proper routes: `oc create configmap aa-routes --from-literal=main-route=$route --from-literal=auth-route=$auth --from-literal=booking-route=$booking --from-literal=customer-route=$customer --from-literal=flight-route=$flight`
4. Apply the deployment yaml of each microservice.
5. Optionally apply the deployment yamls for mongodb

## Usage:

- Ensure that all the pods are ready, and go to the main route.  Once at the main route there should be a link near the bottom with the text `Configure the Acme Air Environment`.  Click on this and if any microservice is working incorrectly a dialog box will appear.  
- The MongoDBs can be loaded after by clicking on `Load the databases` link near the top.
- Jmeter scripts can be found under the `jmeter-files` directory.

