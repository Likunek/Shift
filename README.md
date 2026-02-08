Java version: 11.0.18, vendor: Amazon.com Inc.

Apache Maven 3.9.5

Maven: commons-codec:commons-codec:1.16.0

Maven: org.projectlombok:lombok:1.18.42

Для запуска есть два способа:

1. java -jar target\Shift-1.0.jar -s -a input1.txt input2.txt

при необходимости: mvn clean package  (очищаем и создаем jar файл)
   
2. mvn compile exec:java -Dexec.mainClass="Main" -Dexec.args="-o output -s input1.txt input2.txt"  (собирать напрямую через Maven)
