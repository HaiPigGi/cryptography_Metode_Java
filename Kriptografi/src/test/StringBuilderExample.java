/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

public class StringBuilderExample {
    public static void main(String[] args) {
        // Create a StringBuilder instance
        StringBuilder stringBuilder = new StringBuilder();

        // Append strings to the StringBuilder
        stringBuilder.append("Hello, ");
        stringBuilder.append("world!");
        stringBuilder.append(" This is a StringBuilder example.");

        // Convert StringBuilder to a String
        String result = stringBuilder.toString();

        // Print the result
        System.out.println(result);

        // You can also insert or replace characters at specific positions
        stringBuilder.insert(13, " Java"); // Insert " Java" at index 13
        System.out.println(stringBuilder.toString());

        stringBuilder.replace(0, 5, "Hi"); // Replace "Hello" with "Hi"
        System.out.println(stringBuilder.toString());

        // Delete characters from the StringBuilder
        stringBuilder.delete(0, 3); // Delete the first 3 characters
        System.out.println(stringBuilder.toString());

        // Get the length of the StringBuilder
        int length = stringBuilder.length();
        System.out.println("Length: " + length);

        // Reverse the StringBuilder
        stringBuilder.reverse();
        System.out.println(stringBuilder.toString());
    }
}
