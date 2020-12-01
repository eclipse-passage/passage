<img src="https://github.com/eclipse/passage/blob/master/bundles/org.eclipse.passage.ldc/images/topiclabel/menu_item.png" width="48px" height="48px" />

## Eclipse Passage

[![Build Status](https://github.com/eclipse-passage/passage/workflows/CI/badge.svg)](https://github.com/eclipse-passage/passage/actions)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/9b7ac68ec46a4d58b6e33c5d96a34d42)](https://www.codacy.com/manual/eclipse_2/passage?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=eclipse/passage&amp;utm_campaign=Badge_Grade)
[![codecov](https://codecov.io/gh/eclipse-passage/passage/branch/master/graph/badge.svg)](https://codecov.io/gh/eclipse-passage/passage)

[![Eclipse License](https://img.shields.io/badge/license-EPL--2.0-HotPink.svg)](https://github.com/eclipse/passage/blob/master/LICENSE)

###### Latest release 
[![Stable release P2](https://img.shields.io/badge/P2%20Components-1.0.1-DeepSkyBlue.svg)](https://download.eclipse.org/passage/updates/release/1.0.1/)
[![Stable release Products](https://img.shields.io/badge/Runnable%20Products-1.0.1-DeepSkyBlue.svg)](https://download.eclipse.org/passage/downloads/release/1.0.1/)

###### Current milestone
[![Current P2](https://img.shields.io/badge/P2%20Components-1.1.0--RC1-gold.svg)](https://download.eclipse.org/passage/updates/milestone/1.1.0-RC1/)
[![Current Products](https://img.shields.io/badge/Runnable%20Products-1.1.0--RC1-gold.svg)](https://download.eclipse.org/passage/downloads/milestone/1.1.0-RC1/)

[Eclipse Passage](https://projects.eclipse.org/projects/technology.passage) helps to verify that the software has sufficient license grants in accordance with the specified licensing requirements.
The Equinox-based implementation includes various validators for the time-limited, node-locked and other types of licenses.

### How to use

Just include the "org.eclipse.passage.lic.execute.feature" in your product and declare licensing requirement for your features.

### How it works

The Eclipse Passage works inside your product installed on the user side. Its basic steps are as follows:
1. Get the system configuration specified in the Licensing Operator client: the feature identifiers and the respective usage restrictions.
2. Get the license conditions. The LIC may be tuned to look through different locations like `user.home` and `osgi.instance.area`.
3. Compare the license specified configuration with the current system state (the current date, node id, etc) to confirm the license is active and valid.
4. Use the system configuration and the evaluated license state to make a decision.
5. If there are uncovered licensing requirements, call the required constraint functions to impose restrictions: limit certain functionality or disable everything.

### License

Copyright (c) 2018, 2020 ArSysOp and others

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
[https://www.eclipse.org/legal/epl-2.0/](https://www.eclipse.org/legal/epl-2.0/).

SPDX-License-Identifier: EPL-2.0
