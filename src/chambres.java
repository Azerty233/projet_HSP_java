package Model;

public class chambres {

private int id;
private int numerochambre;
private String choix;

public chambres(int id, int numeroChambre, String choix) {
this.id = id;
this.numerochambre = numeroChambre;
this.choix= choix;
}

public int getId() {
return id;
}

public void setId(int id) {
this.id = id;
}

public int getNumerochambre() {
return numerochambre;
}

public void setNumeroChambre(int numeroChambre) {
this.numerochambre = numeroChambre;
}

public String getChoix() {
return choix;
}

public void setChoix(String choix) {
this.choix = choix;
}
public String toString() {
return  this.id+ "N° " + numerochambre + "  Contient " + choix + "";
}


}
