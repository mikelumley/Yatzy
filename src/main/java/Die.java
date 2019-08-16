import java.util.Objects;

public class Die implements Comparable {
    public static final int MAX_VALUE = 6;
    public static final int MIN_VALUE = 1;

    private int value;

    public Die(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public int compareTo(Object obj) {
        Die otherDie = (Die) obj;
        if (this.value < otherDie.value) {
            return -1;
        }
        else if (this.value == otherDie.value) {
            return 0;
        }
        else {
            return 1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        Die die = (Die) obj;
        return this.value == die.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
