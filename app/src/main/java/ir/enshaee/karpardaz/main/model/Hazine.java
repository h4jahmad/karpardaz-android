package ir.enshaee.karpardaz.main.model;


public class Hazine {
    private int id;
    private String date;
    private String subject;
    private String cost;


    public Hazine() {
    }

    public Hazine(String date, String subject, String cost) {
        this.date = date;
        this.subject = subject;
        this.cost = cost;
    }

    public Hazine(int id, String date, String subject, String cost) {
        this.id = id;
        this.date = date;
        this.subject = subject;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Hazine{" +
                "date='" + date + '\'' +
                ", subject='" + subject + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hazine hazine = (Hazine) o;

        if (getId() != hazine.getId()) return false;
        if (!getDate().equals(hazine.getDate())) return false;
        if (!getSubject().equals(hazine.getSubject())) return false;
        return getCost().equals(hazine.getCost());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getDate().hashCode();
        result = 31 * result + getSubject().hashCode();
        result = 31 * result + getCost().hashCode();
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String toReadableString() {
        return subject + "\n"
                + cost;
    }

}