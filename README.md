web-service
===========

### Some deign notes
* Cluster details and summaries are provided as separate resources. Past experiences with PRIDE Archive web
serviced demonstrated that having a single resource (e.g. Project) providing both details and summaries depending
on what individual end point was requested was confusing from the point of view of client libraries and web apps.
* Spectra will be an independent WS endpoint instead of being part of the Cluster detail. Doing so we can better manage
resources in the client since it can potentially return long collections of results.
