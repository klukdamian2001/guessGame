/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package max.vanach.lesson_1;

import java.util.Scanner;
import java.io.*;
import java.util.Random;
import java.nio.file.*;

/**
 *
 * @author Damian Kluk
 */ 
public class Lesson_1 {
    
    // procedure and function is a Methods
    // this is a local methods
    
    // procedure
    static void setSay()
    {
        System.out.println("saing: I'm procedure !!!");
    }

    // function
    static String getSay()
    {
        return "saing: I'm function !!!";
    }
   
    // software engine
    public static void main(String[] args) throws FileNotFoundException {
        //System.out.println("Hello World!");
        
        // Scanner is a reader object, he use System.in package
        Scanner scan = new Scanner(System.in);
        
        // System.out is writer object, he can write any use print
        // or print any and close line use println
        System.out.print("What is your name: ");
        
        // nextLine() is Scanner method reading any what you write 
        String name = scan.nextLine();
        
        System.out.print("What is your last name: ");
        String lastname = scan.nextLine();
        
        // this is like you can create new object
        person person = new person(name, lastname);
        // and use method from this object
        person.sayHello();
        
        // software instruction, they akcept only true or false value
        int one = 1;
        
        if (one == 1)
        {
            System.out.println("This can be true :D");
        }
        else if (one > 1)
        {
            System.out.println("This can't be true w0w");
        }
        else
        {
            System.out.println("This is impossible :O");
        }
        
        // for, while and do while is a twister instruction
        String message = "Booom !!!";
        
        for (int i = 0; i < 10; i++)
        {
            System.out.println(i);
        }
        System.out.println(message);
        
        int j = 10;
        while (j > 0)
        {
            System.out.println(j);
            j--;
        }
        System.out.println(message);
        
        int k = 10;
        do
        {
            System.out.println(k);
            k--;
        }
        while (k > 0);
        System.out.println(message);
        
        // switch is switch instruction
        int key = 0;
        
        switch (key)
        {
            case 0:
            {
                System.out.println("You haven't time now !!!");
            }
            break;
            
            case 1:
            {
                System.out.println("You have time, run now !!!");
            }
            break;
                
            default:
            {
                System.out.println(message);
            }
        }
        
        // that's like you can use local methods
        setSay();
        String say = getSay();
        System.out.println(say);
        
        // that's like you can create new file        
        Path pathname = Paths.get("./testing.txt");
        
        File file = new File(pathname.toString());
        
        if (!file.exists())
        {
            try 
            {
                // Create the empty file with default permissions, etc.
                Files.createFile(pathname);
            }
            catch (FileAlreadyExistsException x) 
            {
                System.err.format("file named %s" + " already exists%n", pathname);
            } 
            catch (IOException x) 
            {
                // Some other sort of failure, such as permissions.
                System.err.format("createFile error: %s%n", x);
            }
        }
        
        // that's like you can write text to file
        // if more line to write use loop
        PrintWriter filewriter = new PrintWriter(file);
        String writeline = "testing write line and read line";
        filewriter.println(writeline);
        filewriter.close();
        
        // that's like you can read text from file
        // if more line to reas use loop
        Scanner filereader = new Scanner(file);
        String readline = filereader.nextLine();
        System.out.println(readline);
        
    }
}

