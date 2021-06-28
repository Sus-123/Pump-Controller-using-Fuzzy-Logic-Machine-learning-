# Pump Controller using Fuzzy Logic 
**This is implementation of Fuzzy logic .The  example of  Pump Controller has been implemented  to understand fuzzy logic effectively.
It is one of the most important applications of fuzzy logic.
The pump controller maintains the level of the water tank between 40% and 70% full.**

Fuzzy Logic deals with vague and imprecise information. It is based on degrees of truth rather than usual true/false or 1/0 like Boolean logic. In classical set theory, an element either does or does not belong to a set, being characterized by a membership in the set that may have one of two values: 1 or 0. The output of a fuzzy controller is derived from fuzzifications of both inputs and outputs using the associated membership functions. Fuzzy set enables us to work in uncertain and ambiguous situations and solve ill-posed problems or problems with incomplete information thus providing extensive use in industrial applications. In fuzzy sets, each element is mapped to [0,1] by membership function.
A: X￫ [0, 1], where [0,1] means real numbers between 0 and 1 (including 0 and 1).

**Fuzzy logic is very useful for commercial and practical purposes because:**</br>
It can control machines and consumer products.</br>
It may not give accurate reasoning, but acceptable reasoning.</br>
Fuzzy logic helps to deal with the uncertainty in engineering.</br>
The architechture of Fuzzy Algo is: 

![image8](https://user-images.githubusercontent.com/46710508/123592290-a980df80-d80a-11eb-88fc-da004a6a303e.png)

**To compile and run the code : **
Setup Java environment in linux by running following commands in terminal.
a. $ sudo apt install default - jre
b. $ sudo apt install default - jdk
	
Now ,  Download three .jar files provided in the repository. (Given files can also be downloaded from the official site of fuzzylite ) .
These jar files are necessary for importing fuzzylite libraries. 
              (i)    core.jar
              (ii)   jfuzzylite -src.jar
              (iii)  jfuzzylite.jar
       
Place the .jar files and .java file in the same folder.

In terminal cd to the folder containing jar files and code file.

Run the following command in terminal to compile and run  :
        javac -classpath .:jfuzzylite.jar:core.jar FPC.java
        java -classpath .:jfuzzylite.jar:core.jar FPC
        
**OUTPUT**




