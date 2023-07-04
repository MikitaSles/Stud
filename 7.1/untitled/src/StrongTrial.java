class StrongTrial extends Trial {
    public StrongTrial(String account, int mark1, int mark2) {
        super(account, mark1, mark2);
    }

    @Override
    protected int getPassingThreshold() {
        return 150;
    }
}
