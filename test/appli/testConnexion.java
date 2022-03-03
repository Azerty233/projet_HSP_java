package appli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.dbconnexion.Database;

import Manager.Manager_connexion;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class testConnexion {
private static Utilisateurs user;
static Connection coBdd;
static Statement stm;
private static String hash = BCrypt.hashpw("JUnit", BCrypt.gensalt(10));

@BeforeAll
public void before() throws Exception {
coBdd = Database.getInstance();
stm = coBdd.createStatement();
stm.executeUpdate(
"INSERT INTO Utilisateur VALUES (DEFAULT, 'JUnit', 'JUnit', DATE(NOW()), '1234567890', 'JUnit', 'JUnit', '"
+ hash + "', 'junit@test.com', 1, 1, 0);");
}

@Test
@Order(1)
public void seConnecter() throws Exception {
user = new Utilisateurs("JUnit", "JUnit");
Manager_connexion connexion = new Manager_connexion();
co.connexion(user);
assertNotNull(user.getLogin(), "Login : " + user.getLogin());
assertNotNull(user.getMotDePasse(), "Mot de passe : " + user.getMotDePasse());
assertEquals(user.getLogin(), "JUnit", "Login : " + user.getLogin());
assertEquals(user.getMotDePasse(), "JUnit", "Mot de passe : " + user.getMotDePasse());
}

@Test
@Order(2)
public void seConnecterSansLogin() throws Exception {
user = new Utilisateur(null, "JUnit");
Manager_connexion connexion = new Manager_connexion();
co.connexion(user);
assertNull(user.getLogin(), "Login vide");
assertNotNull(user.getMotDePasse(), "Mot de passe : " + user.getMotDePasse());
user = new Utilisateur("", "JUnit");
co.connexion(user);
assertNotEquals(user.getLogin(), "JUnit", "Login : ");
assertEquals(user.getMotDePasse(), "JUnit", "Mot de passe vide");

}

@Test
@Order(3)
public void seConnecterSansMdp() throws Exception {
user = new Utilisateur("JUnit", null);
connexionMAN co = new connexionMAN();
co.connexion(user);
assertNotNull(user.getLogin(), "Login : ");
assertNull(user.getMotDePasse(), "Mot de passe vide");
user = new Utilisateur("JUnit", "");
co.connexion(user);
assertEquals(user.getLogin(), "JUnit", "Login : ");
assertNotEquals(user.getMotDePasse(), "JUnit", "Mot de passe vide");
}

@Test
@Order(4)
public void seConnecterAvecRien() throws Exception {
user = new Utilisateur(null, null);
connexionMAN co = new connexionMAN();
co.connexion(user);
assertNull(user.getLogin(), "Login vide");
assertNull(user.getMotDePasse(), "Mot de passe vide");
user = new Utilisateur("", "");
co.connexion(user);
assertNotEquals(user.getLogin(), "JUnit", "Login : ");
assertNotEquals(user.getMotDePasse(), "JUnit", "Mot de passe vide");
}

@Test
@Order(5)
public void seConnecterAvecMauvaisLogin() throws Exception {
user = new Utilisateur("JavaScript", "JUnit");
connexionMAN co = new connexionMAN();
co.connexion(user);
assertNotEquals(user.getLogin(), "JUnit", "Login : " + user.getLogin());
assertEquals(user.getMotDePasse(), "JUnit", "Mot de passe : " + user.getMotDePasse());
}

@Test
@Order(6)
public void seConnecterAvecMauvaisMdp() throws Exception {
user = new Utilisateur("JUnit", "JavaScript");
connexionMAN co = new connexionMAN();
co.connexion(user);
assertEquals(user.getLogin(), "JUnit", "Login : " + user.getLogin());
assertNotEquals(user.getMotDePasse(), "JUnit", "Mot de passe : " + user.getMotDePasse());
}

@Test
@Order(7)
public void seConnecterAvecMauvaisChamps() throws Exception {
user = new Utilisateur("JavaScript", "JavaScript");
connexionMAN co = new connexionMAN();
co.connexion(user);
assertNotEquals(user.getLogin(), "JUnit", "Login : " + user.getLogin());
assertNotEquals(user.getMotDePasse(), "JUnit", "Mot de passe : " + user.getMotDePasse());
}

@AfterAll
public void after() throws Exception {
stm.executeUpdate("DELETE FROM Utilisateur WHERE Id = LAST_INSERT_ID();");
}
}