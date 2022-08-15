## Flipdeal
Sample java project demonstrating json marshalling/unmarshalling and factory design pattern

### Software Dependencies
- Java == 17.0.4
- maven == 3.8.6

### Target OS
The code was created using windows OS, should run just fine with mac and linux as long as the above dependencies are installed,
Please note that, in case of linux/mac, meanwhile following execution steps, please find distinction between forward `/` and backward `\` slash in paths 

### Steps to run
- make sure that above mentioned software dependencies are installed on the system (a through testing has not been done with available versions so it might run for different versions as well)
- clone the source repository
```bash
git clone git@github.com:sudhanshu-chauhan/flipdeal.git
```
- go inside the project directory and run `mvn package`
```bash
cd flipdeal
mvn package
```
- once the mvn package has finished, there will be two jars created in target folder, one with and one without dependencies, run the one with dependencies
```bash
java -jar java -jar .\target\flipdeal-1.0-jar-with-dependencies.jar promotionsetb
```
- to clear setup, run mvn clean.
```bash
mvn clean
```