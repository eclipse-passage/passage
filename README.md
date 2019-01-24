## Passage Licensing Integration Components (LIC) 

The Passage LIC is a part of the [Eclipse Passage](https://projects.eclipse.org/projects/technology.passage) solution.
This component verifies that the software has sufficient license grants in accordance with the specified licensing requirements.
The Equinox-based implementation includes various validators for the time-limited, node-locked and other types of licenses.

### How to use

Just include the Passage LIC bundles in your product and declare licensing requirement for your features.

### How it works

The Passage LIC works inside your product installed on the user side. Its basic steps are as follows:
1. Get the system configuration specified in the Licensing Operator client: the feature identifiers and the respective usage restrictions.
2. Get the license conditions. The LIC may be tuned to look through different locations like `user.home` and `osgi.instance.area`.
3. Compare the license specified configuration with the current system state (the current date, node id, etc) to confirm the license is active and valid.
4. Use the system configuration and the evaluated license state to make a decision.
5. If there are uncovered licensing requirements, call the required constraint functions to impose restrictions: limit certain functionality or disable everything.

### License

Copyright (c) 2018-2019 ArSysOp and others

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
[http://www.eclipse.org/legal/epl-2.0](http://www.eclipse.org/legal/epl-2.0).

SPDX-License-Identifier: EPL-2.0
