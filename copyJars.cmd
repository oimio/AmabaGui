cd ../amaba-dao && mvn install && cd ../amaba-bo && mvn install && cd ../AmabaGui

cp ../amaba-dao/target/amaba-dao-1.0-SNAPSHOT.jar ./src/main/webapp/WEB-INF/lib

cp ../amaba-bo/target/amaba-bo-1.0-SNAPSHOT.jar ./src/main/webapp/WEB-INF/lib

cp ./resources/application-context.xml ./src/main/webapp/WEB-INF/classes
