package com.company;

/*

2014 Serializable Solution
Serialize the Solution class.
Consider which fields do not need to be serialized, mark the unnecessary fields with the transient modifier.
The object should always contain up-to-date summary data.
The main method does not participate in testing.
Write the verification code yourself in the main method:
1) create a file, open a stream for reading (input stream) and for writing (output stream);
2) create an instance of the class Solution - savedObject;
3) write savedObject to the stream (make sure they are indeed there);
4) create another instance of the Solution class with a different parameter;
5) load the object - loadedObject from the stream;
6) check that savedObject.string is loadedObject.string;
7) handle exceptions.

Requirements:
1. The pattern field must be marked with the transient modifier.
2. The currentDate field must be marked with the transient modifier.
3. The temperature field must be marked with the transient modifier.
4. The string field MUST NOT be checked by the transient modifier.


 */

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Solution implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println(new Solution(4));

        ObjectOutputStream objOS = new ObjectOutputStream(new FileOutputStream("d:/ser.txt"));
        Solution savedObject = new Solution(4);
        objOS.writeObject(savedObject);
        objOS.flush();
        objOS.close();

        Solution loadedObject = new Solution(10);
        System.out.println(loadedObject);
        ObjectInputStream objectIS = new ObjectInputStream(new FileInputStream("d:/ser.txt"));
        loadedObject = (Solution) objectIS.readObject();
        System.out.println(loadedObject);

        if(savedObject.string.equals(loadedObject.string))
            System.out.println("String are equal");
    }

    private final transient String pattern = "dd MMMM yyyy, EEEE";
    private transient Date currentDate;
    private transient int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}


