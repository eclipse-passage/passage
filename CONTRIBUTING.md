# Contributing to Eclipse Passage

Thanks for your interest in this project.

### Prerequisites

Java 11 and Maven 3.6.3, or newer.

If your Internet connection uses a proxy, make sure that you have the proxy configured in your [Maven settings.xml](http://maven.apache.org/settings.html).

### Using the Eclipse Installer (Oomph)

Step by step instructions:

1. Download the [Eclipse Installer](https://wiki.eclipse.org/Eclipse_Installer). 
2. Start the installer using the `eclipse-inst` executable.
3. On the first page (product selection), click the preference button in the top-right corner and select the _Advanced Mode_ .
4. If you are behind a proxy, at this point you might want to double check your network settings by clicking in the _Network Proxy Settings_ at the bottom.
5. Select _Eclipse IDE for Eclipse Committers_ . Click _Next_ .
6. Under _Eclipse.org_ , double-click on _Passage_ (single click is not enough!). Make sure that _Passage_ is shown in the table on the bottom. Click _Next_.
7. You can edit the _Installation Folder_ , but you do not have to select the _Target Platform_ here, this will be set later automatically. By choosing _Show all variables_ at the bottom of the page, you are able to change other values as well but you do not have to. (Unless you have write access to the GitHub repository, make sure you select "HTTPS read-only" in the dropdown "Passage Github repository protocol"). Click _Next_.
8. Press _Finished_ on the _Confirmation_ page will start the installation process. 
9. The installer will download the selected Eclipse version, starts Eclipse and will perform all the additional steps (cloning the git repos, etc...). When the downloaded Eclipse started, the progress bar in the status bar shows the progress of the overall setup.
10. Once the _Executing startup task_ job is finished you should have all the Passage projects imported into several working sets called according to Passage component name .

### Manually setup

Preferred and easier way is to follow the instructions above, but you could also setup your environment manually:

1. Get an [Eclipse IDE](https://www.eclipse.org/downloads/eclipse-packages/) with a recent version of the [Maven integration for Eclipse (m2eclipse)](https://www.eclipse.org/m2e/) and Eclipse PDE installed. m2eclipse is included in various Eclipse packages, e.g. the _Eclipse IDE for Eclipse Committers_ package. To add m2eclipse to your existing Eclipse installation, install it from the Eclipse Marketplace.
2. Clone this repository (via CLI or EGit)
3. In Eclipse, use ''File > Import > Existing Maven Projects'', select the root directory of the sources, and import all projects. If prompted by m2eclipse, install the proposed project configurators and restart Eclipse.
4. Configure the target platform: Open the file `releng/org.eclipse.passage.target/org.eclipse.passage.target.target` and click on _Set as Target Platform_ in the upper right corner of the target definition editor.
5. Configure the baseline with the file `releng/org.eclipse.passage.target/org.eclipse.passage.baseline.target` and click on _Set as Target Platform_ in the upper right corner of the target definition editor.


The result should be an Eclipse workspace without build errors. m2eclipse may take some time to download required libraries from Maven central.


## 🏗️ Build & Test

From the root directory of your local Passage git-repository clone run the following Maven commands...
* to check if compilation and all tests succeed:
    * `mvn clean verify`

## Commits

### Message Guidelines

Start with `Bug: <record>` stating the Bugzilla record number the change is related to; to reference a GiHub issue start with `Bug: #<issue>`

Also in the first line, provide a clear and concise description of the change

Add one blank line, followed by more details about the change. This could include a motivation for the change and/or reasons why things were done in the particular way they are done in the change.

### Granularity

Make small commits, yet self-contained commits. This makes them easy to review.

Do not mix concerns in commits: have a commit do a single thing. This makes them reviewable 'in isolation'. This is particularly important if you need to do refactorings to the existing code: Refactorings tend to lead to large diffs which are difficult to review. Therefore make sure to have separate commits for refactorings and for functional changes.

## Submit patch

As GitHub pull request.

## Contact

Contact the project developers via the project's "dev" list: https://dev.eclipse.org/mailman/listinfo/passage-dev

## 👔 Process and Legal

## Eclipse Development Process

This Eclipse Foundation open project is governed by the Eclipse Foundation
Development Process and operates under the terms of the Eclipse IP Policy.

## Eclipse Contributor Agreement

Before your contribution can be accepted by the project team contributors must
electronically sign the Eclipse Contributor Agreement (ECA): http://www.eclipse.org/legal/ECA.php

For more information, please see the Eclipse Committer Handbook:
https://www.eclipse.org/projects/handbook/#resources-commit


