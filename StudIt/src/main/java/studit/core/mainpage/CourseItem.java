package studit.core.mainpage;

public class CourseItem {

  private String fagkode;
  private String fagnavn;
  private String kommentar;
  private String score;

  //public CourseItem(String fagkode, String fagnavn, String kommentar, String score){
  //    this.fagkode = fagkode;
  //    this.fagnavn = fagnavn;
  //    this.kommentar = kommentar;
  //    this.score = score;
  //}

  public void setFagnavn(String fagnavn){
    this.fagnavn = fagnavn;
  }
  public void setFagkode(String fagkode){
    this.fagkode = fagkode;
  }
  public void setKommentar(String kommentar){
    this.kommentar = kommentar;
  }
  public void setScore(String score){
    this.score = score;
  }

  public String getFagnavn(){
      return this.fagnavn;
  }

  public String getFagkode(){
      return this.fagkode;
  }

  public String getKommentar(){
      return this.kommentar;
  }

  public String getScore() {
      return this.score;
  }

}