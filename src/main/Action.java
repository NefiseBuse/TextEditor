/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

public class Action {
    private String type;
    private String text;
    private int position;
    private String originalText; // replace işlemi için

    public Action(String type, String text, int position) {
        this.type = type;
        this.text = text;
        this.position = position;
    }

    public Action(String type, String text, int position, String originalText) {
        this.type = type;
        this.text = text;
        this.position = position;
        this.originalText = originalText;
    }

    public String getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public int getPosition() {
        return position;
    }

    public String getOriginalText() {
        return originalText;
    }
}
