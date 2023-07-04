public class Euro {
    private int cents;

    public Euro() {
        // Конструктор без аргументов
    }

    public Euro(int cents) {
        this.cents = cents;
    }

    public int getCents() {
        return cents;
    }

    public String toString() {
        int euros = cents / 100;
        int remainingCents = cents % 100;
        return euros + "." + String.format("%02d", remainingCents);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Euro other = (Euro) obj;
        return this.cents == other.cents;
    }

    public int compareTo(Euro euro) {
        return Integer.compare(this.cents, euro.cents);
    }
}
