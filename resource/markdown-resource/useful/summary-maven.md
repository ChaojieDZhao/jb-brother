## maven打包跳过测试

```
mvn package -Dmaven.test.skip=true 

mvn versions:set -DnewVersion=1.0.88-SNAPSHOT

mvn -N versions:update-child-modules

mvn -Djetty.port=8082 jetty:run

mvn -e package docker:build

mvn -s D:\soooooooft\maven\apache-maven-3.3.9\conf\settings_alldios.xml  dependency:tree -DskipTests=true install -f pom.xml -e -U
```


##  标签标记
 
<build>
    <plugins>
        <!-- Docker镜像构建 mvn docker:build https://github.com/spotify/docker-maven-plugin  -->
        <plugin>
            <groupId>com.spotify</groupId>
            <artifactId>docker-maven-plugin</artifactId>
            <version>${docker.plugin.version}</version>
            <executions>
                <execution>
                    <id>build-image</id>    <!--定义一个执行命令的id-->
                    <phase>package</phase>  <!--绑定mvn的哪个命令-->
                    <goals>
                        <goal>build</goal>  <!--要执行的命令 -->
                    </goals>
                </execution>
                <execution>
                    <id>image-tag</id>
                    <phase>package</phase>
                    <goals>
                        <goal>tag</goal>     <!--tag命令，相当于docker的tag命令-->
                    </goals>
                    <configuration>
                        <image>my/${docker.image.name}</image>  <!--镜像名-->
                        <newName>192.168.134.128:5000/my/${docker.image.name}</newName>  <!--打的标签名-->
                    </configuration>
                </execution>
                <execution>
                    <id>package-push</id>
                    <phase>package</phase>
                    <goals>
                        <goal>push</goal>   <!--相当于docker的push命令-->
                    </goals>
                    <configuration>
                        <imageName>192.168.134.128:5000/my/${docker.image.name}</imageName> <!--要push的镜像名-->
                    </configuration>
                </execution>
            </executions>
            <configuration>
                <forceTags>true</forceTags>   <!--覆盖相同标签镜像-->
                <imageName>my/${docker.image.name}</imageName> <!--指定镜像名称 仓库/镜像名:标签-->
                <baseImage>java:8-jre-alpine</baseImage>   <!--指定基础镜像，相当于dockerFile中的FROM<image> -->
                <dockerHost>http://192.168.134.128:5678</dockerHost> <!-- 指定仓库地址 -->
                <entryPoint>["java","-jar","/${project.build.finalName}.jar"]
                </entryPoint><!-- 容器启动执行的命令，相当于dockerFile的ENTRYPOINT -->
                <resources>
                    <resource>                                            <!-- 指定资源文件 -->
                        <targetPath>/</targetPath>                        <!-- 指定要复制的目录路径，这里是当前目录 -->
                        <directory>${project.build.directory}</directory> <!-- 指定要复制的根目录，这里是target目录 -->
                        <include>${project.build.finalName}.jar</include> <!-- 指定需要拷贝的文件，这里指最后生成的jar包 -->
                    </resource>
                </resources>
            </configuration>
        </plugin>
    </plugins>
</build>

<project>
  ...
  <dependencies>
    <!-- declare the dependency to be set as optional -->
    <dependency>
      <groupId>sample.ProjectB</groupId>
      <artifactId>Project-B</artifactId>
      <version>1.0</version>
      <scope>compile</scope>
      <optional>true</optional> <!-- value will be true or false only -->
    </dependency>
  </dependencies>
</project>

> 假设以上配置是项目A的配置，即：Project-A –> Project-B。在编译项目A时，是可以正常通过的。
如果有一个新的项目X依赖A，即：Project-X -> Project-A。
此时项目X就不会依赖项目B了。如果项目X用到了涉及项目B的功能，那么就需要在pom.xml中重新配置对项目B的依赖。
假设A->B, B->X（可选）, B->Y（可选）。这里由于X，Y是可选依赖，依赖不会传递，X，Y将不会对A有任何影响