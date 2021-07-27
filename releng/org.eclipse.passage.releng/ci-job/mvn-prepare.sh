set +e


MVN_CMD=/opt/tools/apache-maven/latest/bin/mvn
MVN_SETTINGS=/home/jenkins/.m2/settings.xml
JAVA_HOME=/opt/tools/java/oracle/jdk-8/latest

REPO_PATH=releng/org.eclipse.passage.releng/publish/target/repository/final
REPO_ID=ossrh
REPO_URL=https://oss.sonatype.org/service/local/staging/deploy/maven2

$MVN_CMD -version


#####################################
## FUNCTION create_javadoc
#####################################
# $1: source jar path
# $2: javadoc jar path
# $3: javadoc window title
# $4: javadoc doc title
# $5: javadoc header
# $6: javadoc bottom
create_javadoc () {
  pushd $(pwd)
  
  mkdir -p $WORKSPACE/.create_javadoc/src
  cd $WORKSPACE/.create_javadoc/src

  # extract to src dir
  jar -xf $1

  # go one level up to invoke javadoc
  cd ..
  
  echo "Creating Javadoc..."
  echo "  Window Title: $3"
  echo "  Doc Title: $4"
  echo "  Header: $5"
  echo "  Botton: $6"
  
  # produce javadoc from 'src' dir to 'target' dir
  $JAVA_HOME/bin/javadoc -sourcepath src \
    -d target \
    -use \
    -splitIndex \
    -windowtitle "$3" \
    -doctitle "$4" \
    -header "$5" \
    -bottom "$6" \
    -quiet \
    -J-Xmx180m \
    -Xdoclint:none \
    -subpackages org 2>&1 | tail -n 2
  
  # package javadocs
  cd target
  $JAVA_HOME/bin/jar -cf $2 *
  
  popd
  # clean temp dir
  rm -rf $WORKSPACE/.create_javadoc
}

#####################################
## MAIN SCRIPT
#####################################

# Enhance generated POMs by mandatory information (name, SCM, license, developers)
$JAVA_HOME/bin/java \
  -jar releng/org.eclipse.emf.releng/publish/pommod/target/pommod.jar \
  -dir $REPO_PATH/org

find $REPO_PATH/org -name "*.pom" | while read fname; do
	sed "/^<\/project>/d" "$fname" > "$fname".new
    cat >> "$fname".new <<EOL
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-gpg-plugin</artifactId>
        <version>1.6</version>
        <executions>
          <execution>
            <id>sign-artifacts</id>
            <phase>verify</phase>
            <goals>
              <goal>sign</goal>
            </goals>
            <configuration>
              <gpgArguments>
                <arg>--pinentry-mode</arg>
                <arg>loopback</arg>
              </gpgArguments>
            </configuration>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
</project>
EOL
    mv "$fname".new "$fname"
done

# loop over the aggregated repository and call the GPG plugin with 'sign-and-deploy-file' goal
#   http://maven.apache.org/plugins/maven-gpg-plugin/sign-and-deploy-file-mojo.html
# append /org to REPO_PATH to avoid match of artifacts.jar/content.jar
# use string replacement to replace the file extension for the .pom file for each jar
find $REPO_PATH/org -name "*.jar" | while read fname; do
  # only take action for the non-source bundles
  if [[ ! "$fname" =~ ".*-sources.jar" ]]; then
    POM_FILE="${fname/%.jar/.pom}"
    SOURCES_JAR="${fname/%.jar/-sources.jar}"
    JAVADOC_JAR="${fname/%.jar/-javadoc.jar}"
    PUBLISH_FILE="${fname/%.jar/.publish}"
    
    if [ -f $SOURCES_JAR -a ! -f $PUBLISH_FILE ]; then
      echo "Artifact already published"
    fi
    
    if [ -f $SOURCES_JAR -a -f $PUBLISH_FILE ]; then
      JAVADOC_TITLE=`sed '1q;d' $PUBLISH_FILE`
      JAVADOC_FOOTER=`sed '2q;d' $PUBLISH_FILE`
      create_javadoc \
        $WORKSPACE/$SOURCES_JAR \
        $WORKSPACE/$JAVADOC_JAR \
        "$JAVADOC_TITLE" \
        "$JAVADOC_TITLE" \
        "$JAVADOC_TITLE" \
        "$JAVADOC_FOOTER"

      if  [[ $PUBLISH_REPOSITORY == "true" ]]
      then
        cat $POM_FILE
        $MVN_CMD -B -DinteractiveMode=false org.apache.maven.plugins:maven-gpg-plugin:1.6:sign-and-deploy-file \
          -Dfile=$fname \
          -DpomFile=$POM_FILE \
          -DrepositoryId=$REPO_ID \
          -Durl=$REPO_URL

        $MVN_CMD -B -DinteractiveMode=false org.apache.maven.plugins:maven-gpg-plugin:1.6:sign-and-deploy-file \
          -Dfile=$SOURCES_JAR \
          -DpomFile=$POM_FILE \
          -DrepositoryId=$REPO_ID \
          -Durl=$REPO_URL \
          -Dclassifier=sources
      
        $MVN_CMD -B -DinteractiveMode=false org.apache.maven.plugins:maven-gpg-plugin:1.6:sign-and-deploy-file \
          -Dfile=$JAVADOC_JAR \
          -DpomFile=$POM_FILE \
          -DrepositoryId=$REPO_ID \
          -Durl=$REPO_URL \
          -Dclassifier=javadoc
      fi
    fi
  fi
done
