# k8s-demo
https://www.bluefyre.io/getting-started-springboot-kubernetes/

### set our docker environment variables
```
eval $(minikube docker-env)
```

```
mengka-host:springboot-demo3 hyy044101331$ echo $DOCKER_HOST
tcp://192.168.99.100:2376
```

### dockerfile-maven-plugin
```
            <plugin>
                <groupId>com.spotify</groupId>
                <artifactId>dockerfile-maven-plugin</artifactId>
                <version>1.3.6</version>
                <executions>
                    <execution>
                        <id>default</id>
                        <goals>
                            <goal>build</goal>
                            <goal>push</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <repository>bluefyre/hellojavakubernetes</repository>
                    <tag>${project.version}</tag>
                    <verbose>true</verbose>
                </configuration>
            </plugin>
```

### DOCKFILE
```
FROM openjdk:8-jdk-alpine

ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/hellojavakubernetes/springboot-demo3-0.0.1.jar"]

# Add Maven dependencies
ADD target/springboot-demo3-0.0.1.jar /usr/share/hellojavakubernetes/springboot-demo3-0.0.1.jar
```

### kick off the docker build process
```
mvn compile -e
mvn package -Dmaven.test.skip=true
mvn dockerfile:build
```

### create the kubernetes service
```
kubectl create -f hellojavakubernetes-service.yaml
```

### kick off the deployment
```
kubectl create -f hellojavakubernetes-deployment.yaml
```

### 创建并发布服务: hello-tomcat
```
kubectl run hello-tomcat --image=tomcat:8.0 --port=8080
kubectl expose deployment hello-tomcat --type=NodePort
kubectl get pods
```

### To view if the deployment was successful, run the following
```
mengka-host:springboot-demo3 hyy044101331$ kubectl get deployment
NAME                  DESIRED   CURRENT   UP-TO-DATE   AVAILABLE   AGE
hello-minikube        1         1         1            0           94d
hello-tomcat          1         1         1            1           94d
hellojavakubernetes   1         1         1            1           6s
```

### get the endpoint address
Lets send requests to our kubernetes cluster. To do that let’s get the endpoint that we need to send our requests to


```
minikube service hellojavakubernetes --url
```

### 访问服务
http://192.168.99.100:31257/v1/rate


### To clean up the cluster
```
kubectl delete service,deployment hellojavakubernetes
minikube stop
minikube delete # to get rid of the local hypervisor vm
```