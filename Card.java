import java.io.*;

public class Card implements Serializable {
    // Fields
    private Color color;
    private Value value;

    // Constructors
    public Card(Color color, Value value) {
        this.color = color;
        this.value = value;
    }

    // Getters and setters
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    // Methods
}
