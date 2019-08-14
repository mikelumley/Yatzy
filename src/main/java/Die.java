public class Die implements Comparable{
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
        if(this.value < otherDie.value) {
            return -1;
        }
        else if(this.value == otherDie.value) {
            return 0;
        }
        else {
            return 1;
        }
    }
}
