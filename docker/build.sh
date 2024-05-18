set -e

mvn package -DskipTests -f ../pom.xml
echo "packaging done"

java -Djarmode=layertools -jar ../target/*.jar extract --destination ../target/extracted
echo "extracting done"

docker build -t tasklab-task-service:1.0.0 ../ -f Dockerfile
echo "image built"

docker tag tasklab-task-service:1.0.0 ineffable24/tasklab-task-service:1.0.0
echo "image tagged"

docker push ineffable24/tasklab-task-service:1.0.0
echo "image pushed to an external repository"