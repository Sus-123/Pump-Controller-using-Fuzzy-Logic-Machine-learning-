# Pump Controller using Fuzzy Logic 
**This is implementation of Fuzzy logic .The  example of  Pump Controller has been implemented  to understand fuzzy logic effectively.
It is one of the most important applications of fuzzy logic.
The pump controller maintains the level of the water tank between 40% and 70% full.**

Fuzzy Logic deals with vague and imprecise information. It is based on degrees of truth rather than usual true/false or 1/0 like Boolean logic. 

**Fuzzy logic is very useful for commercial and practical purposes because:**</br>
It can control machines and consumer products.</br>
It may not give accurate reasoning, but acceptable reasoning.</br>
Fuzzy logic helps to deal with the uncertainty in engineering.</br>


**To compile and run the code** : 
Setup Java environment in linux by running following commands in terminal.</br>
a. $ sudo apt install default - jre </br>
b. $ sudo apt install default - jdk </br>
	
Now ,  Download three .jar files provided in the repository. (Given files can also be downloaded from the official site of fuzzylite ) .
These jar files are necessary for importing fuzzylite libraries.</br> 
              (i)    core.jar </br>
              (ii)   jfuzzylite -src.jar </br>
              (iii)  jfuzzylite.jar </br>
       
Place the .jar files and .java file in the same folder.</br>

In terminal cd to the folder containing jar files and code file.</br>

Run the following command in terminal to compile and run  :</br>
        javac -classpath .:jfuzzylite.jar:core.jar FPC.java </br>
        java -classpath .:jfuzzylite.jar:core.jar FPC </br>
        
**OUTPUT**
![image10](https://user-images.githubusercontent.com/46710508/123592888-79860c00-d80b-11eb-8095-4e975cdcd7f8.jpg)





