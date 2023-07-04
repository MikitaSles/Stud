import java.sql.Date;

class Result {
    String login;
    String test;
    Date date;
    int mark;

    Result(String login, String test, Date date, int mark) {
        this.login = login;
        this.test = test;
        this.date = date;
        this.mark = mark;
    }
}
