/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;


public class Main {

    
   
     public static void main(String[] args) {
        TextEditor editor = new TextEditor();              
        String filePath = "/Users/nefisebuseuzun/Desktop/actions.txt"; //dosya yolu
        editor.readActionsFromFile(filePath); //dosyadan okumak için
        
        // Son halini yazdırmak için
        System.out.println("Son metin: " + editor.getText());
      
    }
}


