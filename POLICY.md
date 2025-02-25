### Releases and versions

At the beginning of a release cycle versions are  incremented synchronously for
 - bundles
 - features
 - products, if any
 - other modules of Passage project source base
 
according to the severity of changes, planned for the release. As the result each module version is equal to the release version. 

Besides that, all EMF models also sustain changes each release, as nsURI of an EMF model in Passage project 
comprises version literal as well. This means public constant `*Package::eNS_URI` evolves every release, 
which change we do not treat as critical.

#### Version incrementing strategy
Any version change in Passage project follows [Semantic Versioning 2.0.0](https://semver.org/) rules literally:
only incompatible API changes cause major version increment. As currently these rules are opposed by recent demands of 
Eclipse Platform stated in the document [Evolving Java-based APIs 2](https://github.com/eclipse-platform/eclipse.platform/blob/master/docs/Evolving-Java-based-APIs-2.md), this policy directly stipulate that 
Passage Project does not fully follow Eclipse Platform's rules until Eclipse Platform project fully follows these rules itself. 
