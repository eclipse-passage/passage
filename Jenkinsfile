pipeline {
	options {
		timeout(time: 40, unit: 'MINUTES')
		buildDiscarder(logRotator(numToKeepStr:'10'))
		disableConcurrentBuilds(abortPrevious: true)
	}
	agent {
		label "ubuntu-latest"
	}
	tools {
		maven 'apache-maven-latest'
		jdk 'openjdk-jdk21-latest'
	}
	parameters {
		choice(
			name: 'BUILD_TYPE',
			choices: ['nightly', 'milestone', 'release'],
			description: '''
			Choose the type of build.
			Note that a release build will not promote the build, but rather will promote the most recent milestone build.
			'''
		)
		booleanParam(
			name: 'PROMOTE',
			defaultValue: true,
			description: 'Whether to promote the build to the download server.'
		)
	}
	stages {
		stage('Display Parameters') {
				steps {
						script {
								env.BUILD_TYPE = params.BUILD_TYPE
								if (env.BRANCH_NAME == 'master') {
									if (params.PROMOTE) {
										env.MAVEN_PROFILES = "-Psign -Ppromote"
									} else {
										env.MAVEN_PROFILES = "-Psign"
									}
								} else {
									env.MAVEN_PROFILES = "-P baseline-check,coverage"
								}
								def description = """
BUILD_TYPE=${env.BUILD_TYPE}
MAVEN_PROFILES=${env.MAVEN_PROFILES}
""".trim()
								echo description
								currentBuild.description = description.replace("\n", "<br/>")
						}
				}
		}
		stage('Initialize PGP') {
			steps {
				withCredentials([file(credentialsId: 'secret-subkeys.asc', variable: 'KEYRING')]) {
					sh 'gpg --batch --import "${KEYRING}"'
					sh 'for fpr in $(gpg --list-keys --with-colons  | awk -F: \'/fpr:/ {print $10}\' | sort -u); do echo -e "5\ny\n" |  gpg --batch --command-fd 0 --expert --edit-key ${fpr} trust; done'
				}
			}
		}
		stage('Build Passage') {
			steps {
				sshagent (['projects-storage.eclipse.org-bot-ssh']) {
					wrap([$class: 'Xvnc', useXauthority: true]) {
						sh '''
							mvn \
							clean \
							verify \
							-B \
							$MAVEN_PROFILES \
							-Dmaven.repo.local=$WORKSPACE/.m2/repository \
							-Dmaven.test.failure.ignore=true \
							-Dmaven.test.error.ignore=true \
							-Ddash.fail=false \
							-Dorg.eclipse.justj.p2.manager.build.url=$JOB_URL \
							-Dbuild.type=$BUILD_TYPE \
							-Dgit.commit=$GIT_COMMIT
						'''
					}
				}
			}
			post {
				always {
					archiveArtifacts artifacts: '**/target/repository/**/*,**/target/*.zip,**/target/work/data/.metadata/.log'
					junit '**/target/surefire-reports/TEST-*.xml'
				}
			}
		}
	}
}
