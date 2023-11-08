package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.List;

public class TextMap implements WorldMap<String, Integer>{
    private final List<String> textList;

    public TextMap(){
        this.textList = new ArrayList<>();
    }

    @Override
    public boolean canMoveTo(Integer position) {
        return position >= 0 && position <= textList.size();
    }

    @Override
    public boolean place(String text) {
        textList.add(text);
        return true;
    }

    @Override
    public void move(String text, MoveDirection direction) {
        int oldPosition = textList.indexOf(text);
        if (direction == MoveDirection.FORWARD) {
            if (oldPosition == textList.size() - 1) {
                return;
            }
            textList.remove(text);
            textList.add(oldPosition + 1, text);
        }
        else if (direction == MoveDirection.BACKWARD) {
            if (oldPosition == 0) {
                return;
            }
            textList.remove(text);
            textList.add(oldPosition - 1, text);
        }
    }

    @Override
    public boolean isOccupied(Integer position) {
        return position >= 0 && position < textList.size();
    }

    @Override
    public String objectAt(Integer position) {
        if (position < 0 || position >= textList.size()) {
            return null;
        }
        return textList.get(position);
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < textList.size(); i++) {
            stringBuilder.append("\"").append(textList.get(i)).append("\"");
            if (i < textList.size() - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
