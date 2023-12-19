package HUC;

import java.awt.*;
import java.awt.event.*;

/**
 * @author Lucas Orsvärn
 * @version 2.1  2008/10/17
 * 
 * This program is intended for people using the Hammer editor from Valve. it is
 * able to quickly and easily make your conversions to and from the imperial,
 * system, the metric system and the units found in Hammer. It can also convert
 * standard skybox units wich are the units that are used when making a 3D
 * skybox in Hammer.
 */
public class Main extends Frame implements ItemListener, KeyListener{
   
//    Creating all the components I will need
    CheckboxGroup checkboxGroup1;
    CheckboxGroup checkboxGroup2;
    
//    Group NORTH
    Panel northPanel;
    TextField textField;
    Label currentTypeField;
    TextField answerField;
    
//    Group WEST
    Panel westPanel;
    Panel yardsPanel;
    Label imperial;
    Checkbox yardsRB1;
    Checkbox yardsRB2;
    Checkbox feetRB1;
    Checkbox feetRB2;
    Checkbox inchesRB1;
    Checkbox inchesRB2;
    
//    Group CENTER
    Label metric;
    Checkbox metersRB1;
    Checkbox metersRB2;
    Checkbox decimetersRB1;
    Checkbox decimetersRB2;
    Checkbox centimetersRB1;
    Checkbox centimetersRB2;
    
//    Group EAST
    Label hammer;
    Checkbox unitsRB1;
    Checkbox unitsRB2;
    Checkbox skyboxRB1;
    Checkbox skyboxRB2;
    
//    Variables
    double answer = 0;
    String answerType = "yd", currentType = "yd";
    Color bgColor = new Color(212, 208, 200);
    
    public Main(){
        super("Hammer Units Converter 2.2");	// Window title
        
//        Putting stuff in the components and adding listeners
        
        checkboxGroup1 = new CheckboxGroup();
        checkboxGroup2 = new CheckboxGroup();

    //    Group NORTH
        textField = new TextField();
        textField.addKeyListener(this);
        currentTypeField = new Label(currentType + " = ");
        answerField = new TextField(answer + " " + answerType);
        answerField.addKeyListener(this);
        answerField.setBackground(bgColor);

    //    Group WEST
        imperial = new Label("Imperial", 1);
        yardsRB1 = new Checkbox("", checkboxGroup1, true);
        yardsRB1.addItemListener(this);
        yardsRB2 = new Checkbox("Yards", checkboxGroup2, true);
        yardsRB2.addItemListener(this);
        feetRB1 = new Checkbox("", checkboxGroup1, false);
        feetRB1.addItemListener(this);
        feetRB2 = new Checkbox("Feet", checkboxGroup2, false);
        feetRB2.addItemListener(this);
        inchesRB1 = new Checkbox("", checkboxGroup1, false);
        inchesRB1.addItemListener(this);
        inchesRB2 = new Checkbox("Inches", checkboxGroup2, false);
        inchesRB2.addItemListener(this);
        
    //    Group CENTER
        metric = new Label("Metric", 1);
        metersRB1 = new Checkbox("", checkboxGroup1, false);
        metersRB1.addItemListener(this);
        metersRB2 = new Checkbox("Meters", checkboxGroup2, false);
        metersRB2.addItemListener(this);
        decimetersRB1 = new Checkbox("", checkboxGroup1, false);
        decimetersRB1.addItemListener(this);
        decimetersRB2 = new Checkbox("Decimeters", checkboxGroup2, false);
        decimetersRB2.addItemListener(this);
        centimetersRB1 = new Checkbox("", checkboxGroup1, false);
        centimetersRB1.addItemListener(this);
        centimetersRB2 = new Checkbox("Centimeters", checkboxGroup2, false);
        centimetersRB2.addItemListener(this);
        
    //    Group EAST
        hammer = new Label("Hammer", 1);
        unitsRB1 = new Checkbox("", checkboxGroup1, false);
        unitsRB1.addItemListener(this);
        unitsRB2 = new Checkbox("Units", checkboxGroup2, false);
        unitsRB2.addItemListener(this);
        skyboxRB1 = new Checkbox("", checkboxGroup1, false);
        skyboxRB1.addItemListener(this);
        skyboxRB2 = new Checkbox("Skybox", checkboxGroup2, false);
        skyboxRB2.addItemListener(this);

        
//        Putting stuff in panels that I will later put in my layout
//        ---NORTH---
        Panel northPanel = new Panel(new GridLayout(1, 3));
        northPanel.add(textField);
        northPanel.add(currentTypeField);
        northPanel.add(answerField);
        
//        Makes small panels for all the radio buttons in westPanel
        
//        ---IMPERIAL--- WEST
//        Yards
        Panel yardsPanel = new Panel(new FlowLayout(3));
        yardsPanel.add(yardsRB1);
        yardsPanel.add(yardsRB2);
        
//        Feet
        Panel feetPanel = new Panel(new FlowLayout(3));
        feetPanel.add(feetRB1);
        feetPanel.add(feetRB2);
        
//        Inches
        Panel inchesPanel = new Panel(new FlowLayout(3));
        inchesPanel.add(inchesRB1);
        inchesPanel.add(inchesRB2);
        
//        ---METRIC--- CENTER
//        Meters
        Panel metersPanel = new Panel(new FlowLayout(3));
        metersPanel.add(metersRB1);
        metersPanel.add(metersRB2);
        
//        Decimeters
        Panel decimetersPanel = new Panel(new FlowLayout(3));
        decimetersPanel.add(decimetersRB1);
        decimetersPanel.add(decimetersRB2);
        
//        Centimeters
        Panel centimetersPanel = new Panel(new FlowLayout(3));
        centimetersPanel.add(centimetersRB1);
        centimetersPanel.add(centimetersRB2);
        
//        ---HAMMER--- EAST
//        Units
        Panel unitsPanel = new Panel(new FlowLayout(3));
        unitsPanel.add(unitsRB1);
        unitsPanel.add(unitsRB2);
        
        Panel skyboxPanel = new Panel(new FlowLayout(3));
        skyboxPanel.add(skyboxRB1);
        skyboxPanel.add(skyboxRB2);
        
//        Adding the radioButton panels to the westPanel
        Panel westPanel = new Panel(new GridLayout(4, 1));
        westPanel.add(imperial);
        westPanel.add(yardsPanel);
        westPanel.add(feetPanel);
        westPanel.add(inchesPanel);
        
//        Adding the radioButton panels to the centerPanel
        Panel centerPanel = new Panel(new GridLayout(4, 1));
        centerPanel.add(metric);
        centerPanel.add(metersPanel);
        centerPanel.add(decimetersPanel);
        centerPanel.add(centimetersPanel);
        
//        Adding the radioButton panels to the eastPanel
        Panel eastPanel = new Panel(new GridLayout(4, 1));
        eastPanel.add(hammer);
        eastPanel.add(unitsPanel);
        eastPanel.add(skyboxPanel);
        
        setLayout(new BorderLayout());
        add(northPanel, BorderLayout.NORTH);
        add(westPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(eastPanel, BorderLayout.EAST);
        
        northPanel.setBackground(bgColor);
        westPanel.setBackground(bgColor);
        centerPanel.setBackground(bgColor);
        eastPanel.setBackground(bgColor);
        
// Listener for closing the app
        addWindowListener (new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        pack();
    }
    
    /**
     * All of the actions that the program makes. Like changing the lables and passing on info to the Converter class.
     */
    public void Convert(){
        String from = "", to = "";
//        Checking first row of radio buttons
        if(yardsRB1.getState() == true){
            from = "yard";
            currentType = "yd";
        }
        else if(feetRB1.getState() == true){
            from = "foot";
            currentType = "ft";
        }
        else if(inchesRB1.getState() == true){
            from = "inch";
            currentType = "in";
        }
        else if(metersRB1.getState() == true){
            from = "meter";
            currentType = "m";
        }
        else if(decimetersRB1.getState() == true){
            from = "decimeter";
            currentType = "dm";
        }
        else if(centimetersRB1.getState() == true){
            from = "centimeter";
            currentType = "cm";
        }
        else if(unitsRB1.getState() == true){
            from = "unit";
            currentType = "u";
        }
        else if(skyboxRB1.getState() == true){
            from = "skybox";
            currentType = "sbu";
        }
//        Checking second row of radio buttons
        if(yardsRB2.getState() == true){
            to = "yard";
            answerType = "yd";
        }
        else if(feetRB2.getState() == true){
            to = "foot";
            answerType = "ft";
        }
        else if(inchesRB2.getState() == true){
            to = "inch";
            answerType = "in";
        }
        else if(metersRB2.getState() == true){
            to = "meter";
            answerType = "m";
        }
        else if(decimetersRB2.getState() == true){
            to = "decimeter";
            answerType = "dm";
        }
        else if(centimetersRB2.getState() == true){
            to = "centimeter";
            answerType = "cm";
        }
        else if(unitsRB2.getState() == true){
            answerType = "u";
            to = "unit";
        }
        else if(skyboxRB2.getState() == true){
            answerType = "sbu";
            to = "skybox";
        }
        double number = 0;
        try{
        number = Double.parseDouble(textField.getText()); // Converting string into double
        }
        catch(NumberFormatException e){  
        }
        currentTypeField.setText(currentType + " = "); // Updates currentTypeField
        Converter conv = new Converter(number, from); // Doing the conversion
        answer = conv.getCurrentIn(to); // sets answer to the conversion
        answer = (int) (100 * answer + 0.5) / 100.0; // Rounding off
        answerField.setText(answer + " " + answerType); // Updates answerField with new answer and type
    }
    
//    Listeners for the KeyEvent managers, all of them are needed but obly one is used
    public void keyReleased(KeyEvent e){
        Convert();
    }
    public void keyPressed(KeyEvent e){
    }
    public void keyTyped(KeyEvent e){
    }
//    Listener checking if any of the radio buttons have changed
    public void itemStateChanged(ItemEvent e){
        Convert();
    }
}