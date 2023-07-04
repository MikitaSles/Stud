class Trial {
    protected String account;
    protected int mark1;
    protected int mark2;

    public Trial(String account, int mark1, int mark2) {
        this.account = account;
        this.mark1 = mark1;
        this.mark2 = mark2;
    }

    public boolean isPassed() {
        int sum = mark1 + mark2;
        return sum >= getPassingThreshold();
    }

    protected int getPassingThreshold() {
        return 0;
    }

    @Override
    public String toString() {
        return "Account: " + account + ", Mark1: " + mark1 + ", Mark2: " + mark2;
    }
}
