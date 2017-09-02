package ir.enshaee.karpardaz.model;

/**
 * Created by Ahmad Reza on 01/09/2017.
 */

public class Hazine {
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
      
}