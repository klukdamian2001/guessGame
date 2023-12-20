/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package max.vanach.lesson_1;

/**
 *
 * @author max
 */
// this is object creator
public class person {
    
    // this is object values
    private String name, lastname;
    
    // this is object constructor
    public person(String name, String lastname)
    {
        this.name = name;
        this.lastname = lastname;
    }
    
    // this is object method
    public void sayHello()
    {
        System.out.println("Hello World!\nI'm " + this.name + " " + this.lastname + ".");
    }
}

