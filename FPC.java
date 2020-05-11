 
/* FUZZY PUMP CONTROLLER
       CONTRIBUTORS: SUSHANT (IIT2018171)
                     PRAKHAR (IIT2018172)
                     NANDINI (IIT2018173)
Save the code in file named FPC.java
*/     import java.util.*;
import processing.core.*;
import processing.core.PApplet;
import com.fuzzylite.*;
import com.fuzzylite.defuzzifier.*;
import com.fuzzylite.norm.s.*;
import com.fuzzylite.norm.t.*;
import com.fuzzylite.rule.*;
import com.fuzzylite.term.*;
import com.fuzzylite.variable.*;


public class FPC extends PApplet {

    int MAX = 70;     
    int MIN = 40; 

    boolean pumOv = false;
    boolean raOv = false;
    boolean dmOv = false;
    boolean pumOn = false;
    boolean raOn = false;
    boolean demOn= false;

    float t1 = (float) 3;
    float t2 = (float) 3;

    float lev;           
    float dem;          
    float rain;            
    float pumAc;    
    Engine eng;

    InputVariable iV1;   
    InputVariable iV2;   
    OutputVariable outV;

    RuleBlock rulSet;
    int blue = color(85,145,232);
    int white = color(255,255,255);
    int red = color(255,0,0);
    int green = color(0,204,102);
    int black = color(0,0,0);
    int grey = color(100,100,100);

    public void settings(){
        size(800, 360);
    }


    public void setup(){
        Random ran = new Random();
        lev = ran.nextInt(80)+10;         
        dem = 0;
        pumAc = 0;
        rain = 0;

        eng=new Engine();
        eng.setName("Fuzzy Pump Controller");

        iV1 = new InputVariable();
        iV1.setEnabled(true);
        iV1.setName("lev");
        iV1.setRange(0,100);
        iV1.addTerm(new Trapezoid("vlow", 0, 10, 20, 30));
        iV1.addTerm(new Trapezoid("low", 20, 30, 40, 50));
        iV1.addTerm(new Trapezoid("good", 40, 50, 60, 70));
        iV1.addTerm(new Trapezoid("high", 60, 70, 80, 90));
        iV1.addTerm(new Trapezoid("vhigh", 80, 90, 100, 100));
        eng.addInputVariable(iV1);

        iV2 = new InputVariable();
        iV2.setEnabled(true);
        iV2.setName("dem");
        iV2.setRange(-1.0, 1.50);	
        iV2.addTerm(new Triangle("vlow", -1.0, -0.75, -0.50));
        iV2.addTerm(new Trapezoid("low", -0.75, -0.50, -0.25, 0));
        iV2.addTerm(new Trapezoid("good", -0.25, 0, 0.25, 0.50));
        iV2.addTerm(new Trapezoid("high", 0.25, 0.50, 0.75, 1.0));
        iV2.addTerm(new Trapezoid("vhigh", 0.75, 1.0, 1.25, 1.50));
        eng.addInputVariable(iV2);

        outV = new OutputVariable();
        outV.setEnabled(true);
        outV.setName("command");
        outV.setRange(-1.00, 1.00);
        outV.fuzzyOutput().setAccumulation(new Maximum());
        outV.setDefuzzifier(new Centroid(100));
        outV.setLockValidOutput(false);
        outV.setLockOutputRange(false);
        outV.addTerm(new Trapezoid("vlow", -1.0, -0.75, -0.50, -0.25));
        outV.addTerm(new Triangle("low", -0.50, -0.25, 0));
        outV.addTerm(new Triangle("good", -0.25, 0, 0.25));
        outV.addTerm(new Triangle("high", 0, 0.25, 0.5));
        outV.addTerm(new Trapezoid("vhigh", 0.25, 0.50, 0.75, 1.00));
        eng.addOutputVariable(outV);

        rulSet = new RuleBlock();
        rulSet.setEnabled(true);
        rulSet.setName("Rule Block");
        rulSet.setConjunction(new Minimum());
        rulSet.setDisjunction(new Maximum());
        rulSet.setActivation(new Minimum());
        rulSet.addRule(Rule.parse("if (lev is vlow or lev is low) then command is vhigh", eng));
        rulSet.addRule(Rule.parse("if (lev is good) then command is good", eng));
        rulSet.addRule(Rule.parse("if (lev is high or lev is vhigh) then command is vlow", eng));
        rulSet.addRule(Rule.parse("if (lev is good and dem is vlow) then command is vlow", eng));
        rulSet.addRule(Rule.parse("if (lev is good and dem is low) then command is low", eng));
        rulSet.addRule(Rule.parse("if (lev is good and dem is good) then command is good", eng));
        rulSet.addRule(Rule.parse("if (lev is good and dem is high) then command is high", eng));
        rulSet.addRule(Rule.parse("if (lev is good and dem is vhigh) then command is vhigh", eng)); 
        rulSet.addRule(Rule.parse("if (lev is vlow and dem is vlow) then command is good", eng));
        rulSet.addRule(Rule.parse("if (lev is low and dem is low) then command is good", eng));
        rulSet.addRule(Rule.parse("if (lev is high and dem is high) then command is good", eng));
        rulSet.addRule(Rule.parse("if (lev is vlow and dem is vhigh) then command is vhigh", eng));
        rulSet.addRule(Rule.parse("if (lev is low and dem is vhigh) then command is high", eng));
        rulSet.addRule(Rule.parse("if (lev is high and dem is vhigh) then command is low", eng));
        rulSet.addRule(Rule.parse("if (lev is vhigh and dem is vhigh) then command is vlow", eng));
        eng.addRuleBlock(rulSet);

        background(255);
    }

    void drExit(){
        background(red);
        fill(white);
        textSize(60);
        text("Water Level full or zero",200,100);
    }

    void drCon(){
        
        stroke(0);                  
        line(100,290,500,290);      
        line(100,300,500,300);
        line(600,290,750,290);      
        line(600,300,750,300);
        noStroke();
        fill(blue);                
        rect(100,291,400,9);
        rect(601,291,150,9);        
    }

    void drMot(){
        fill(grey);                
        rect(255,275,40,40);
    }

    void drTan(){
        stroke(0);                  
        line(500,300,500,200);
        line(600,300,600,200);
        line(500,300,600,300);
    }

    void drWatLev(float lev){
        noStroke();                  
        fill(blue);                  
        rect(501,300-lev,99,lev);    
    }

    void drInformation(float l, float d, float p){
        fill(black);
        text("Water Level " +l,50,50);
        text("Demand lev " +d,50,65);
        text("Pump Action " +p,50,80);
        if (l > MAX || l < MIN)
            fill(red);
        else
            fill(green);
    
        ellipse(60,95,20,20);         
        fill(white);
        if (p < 0)
            text("<-", 261,298);
        else
            text("->", 261,298);
    }

    float fuzpumpControl(float l, float d){
        iV1.setInputValue(l);
        iV2.setInputValue(d);
        eng.process();
        return (float)(outV.defuzzify());
    }   
    void drCheckBoxes(){
        stroke(black);
        if (pumOn)
            fill(grey);
        else
            fill(white);
        rect(650, 30, 15, 15);
        fill(black);
        text("Pump", 670, 42);
        stroke(black);
        if (raOn)
            fill(grey);
        else
            fill(white);
        rect(650, 50, 15, 15);
        fill(black);
        text("Rain", 670, 62);
        stroke(black);
        if (demOn)
            fill(grey);
        else
            fill(white);
        rect(650, 70, 15, 15);
        fill(black);
        text("Demand", 670, 83);

    }

    public void drawSystem(){
        background(255);            
        update(mouseX, mouseY);     
        drCheckBoxes();
        drCon();
        drMot();
        drTan();
        if (raOn)
            rain = noise(t1) * 0.05f;
        else
            rain = 0;
        if (demOn)
        {
            dem = noise(t2);
            dem = map(dem,0f,1f,-1f,1.5f);    
        }
        else
            dem = 0; 
        lev+=rain;           
        lev-=dem;         
        drWatLev(lev);          
        if (pumOn == true)
        {
            pumAc=fuzpumpControl(lev, dem);
            lev+=pumAc;       
        }
        drInformation(lev, dem, pumAc);    
        t1 += 0.01;
        t2 += .6;
    }

    public void draw()
    {
        if (lev<0 || lev>100)
            drExit();
        else
            drawSystem();
    }

    void update(int x, int y) 
    {
        if (overCheckbox(650, 30, 15, 15)) 
        {
            pumOv = true;
            raOv = false;
            dmOv = false;
        }
        else if (overCheckbox(650, 50, 15, 15)) 
        {
            raOv = true;
            pumOv = false;
            dmOv = false;
        }
        else if (overCheckbox(650, 70, 15, 15)) 
        {
            dmOv = true;
            pumOv = false;
            raOv = false;
        }
        else 
        {
            pumOv = raOv = dmOv = false;
        }
    }

    
    public void mousePressed() 
    {
        if (raOv)   raOn=!raOn;
        if (dmOv)   demOn = !demOn;
        if (pumOv)   pumOn = !pumOn;

    }
    boolean overCheckbox(int x, int y, int width, int height)  
    {
        if (mouseX >= x && mouseX <= x+width && mouseY >= y && mouseY <= y+height)
            return true;
        else
            return false;
    }
    public static void main(String[] args)
    {
        String[] a = {"FPC"};
        PApplet.main(a);
    }

}