package org.pursuit.psychic_app_hw_jimenez_luis.model;

public class Choice {
    private boolean isCorrect;
    private String theme;

    public Choice(boolean isCorrect, String theme) {
        this.isCorrect = isCorrect;
        this.theme = theme;
    }
    public boolean isCorrect() {
        return isCorrect;
    }

    public String getTheme() {
        return theme;
    }
}
