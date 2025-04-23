/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;


public class TextEditor {
    private  StringBuilder text;
    private  Stack<Action> undoStack;
    private  Stack<Action> redoStack;

    public TextEditor() {
        this.text = new StringBuilder();
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }

    public void insert(String input, int position) {
        text.insert(position, input);
        undoStack.push(new Action("insert", input, position));
        redoStack.clear();
    }

    public void delete(int position, int length) {
        String deletedText = text.substring(position, position + length);
        text.delete(position, position + length);
        undoStack.push(new Action("delete", deletedText, position));
        redoStack.clear();
    }

    public void replace(String newText, int position, int length) {
        String originalText = text.substring(position, position + length);
        text.replace(position, position + length, newText);
        undoStack.push(new Action("replace", newText, position, originalText));
        redoStack.clear();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Action lastAction = undoStack.pop();
            redoStack.push(lastAction);
            switch (lastAction.getType()) {
                case "insert":
                    text.delete(lastAction.getPosition(), lastAction.getPosition() + lastAction.getText().length());
                    break;
                case "delete":
                    text.insert(lastAction.getPosition(), lastAction.getText());
                    break;
                case "replace":
                    text.replace(lastAction.getPosition(),
                                 lastAction.getPosition() + lastAction.getText().length(),
                                 lastAction.getOriginalText());
                    break;
            }
        } else {
            System.out.println("Geri alınacak işlem yok.");
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Action lastUndoAction = redoStack.pop();
            undoStack.push(lastUndoAction);
            switch (lastUndoAction.getType()) {
                case "insert":
                    text.insert(lastUndoAction.getPosition(), lastUndoAction.getText());
                    break;
                case "delete":
                    text.delete(lastUndoAction.getPosition(),
                                lastUndoAction.getPosition() + lastUndoAction.getText().length());
                    break;
                case "replace":
                    text.replace(lastUndoAction.getPosition(),
                                 lastUndoAction.getPosition() + lastUndoAction.getOriginalText().length(),
                                 lastUndoAction.getText());
                    break;
            }
        } else {
            System.out.println("İleri alınacak işlem yok.");
        }
    }

    public void readActionsFromFile(String fileName) {        
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            String command = parts[0];

            switch (command) {
                case "insert":
                    StringBuilder insertTextBuilder = new StringBuilder();
                    for (int i = 1; i < parts.length - 1; i++) {
                        insertTextBuilder.append(parts[i]);
                        if (i < parts.length - 2) {
                            insertTextBuilder.append(" ");
                        }
                    }
                    String insertText = insertTextBuilder.toString();
                    int insertPosition = Integer.parseInt(parts[parts.length - 1]);
                    insert(insertText, insertPosition);
                    break;

                case "delete":
                    int deletePosition = Integer.parseInt(parts[1]);
                    int deleteLength = Integer.parseInt(parts[2]);
                    delete(deletePosition, deleteLength);
                    break;

                case "replace":
                    String replaceText = parts[1];
                    int replacePosition = Integer.parseInt(parts[2]);
                    int replaceLength = Integer.parseInt(parts[3]);
                    replace(replaceText, replacePosition, replaceLength);
                    break;

                case "undo":
                    undo();
                    break;

                case "redo":
                    redo();
                    break;

                default:
                    System.out.println("Bilinmeyen komut: " + command);
                    break;
            }
        }
    } catch (IOException e) {
        System.out.println("Dosya okuma hatası: " + e.getMessage());
    }
}
    public String getText() {
        return text.toString();
    }    
}
