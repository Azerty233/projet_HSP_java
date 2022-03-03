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
private static Model.user user;
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
	user = new Model.user(null, "JUnit");
	Manager_connexion co = new Manager_connexion();
	co.Connexion(null, null, null, user);
	assertNull(user.getEmail(), "Email vide");
	assertNotNull(user.getMdp(), "Mot de passe : " + user.getMdp());
	user = new Model.user("", "JUnit");
	co.Connexion(null, null, null, user);
	assertNotEquals(user.getEmail(), "JUnit", "Email : ");
	assertEquals(user.getMdp(), "JUnit", "Mot de passe vide");
}

@Test
@Order(2)
public void seConnecterSansLogin() throws Exception {
user = new Model.user("JUnit", "JUnit");
Manager_connexion co = new Manager_connexion();
co.Connexion(null, null, null, user);
assertNull(user.getEmail(), "Email vide");
assertNotNull(user.getMdp(), "Mot de passe : " + user.getMdp());
user = new Model.user("", "JUnit");
co.Connexion(null, null, null, user);
assertNotEquals(user.getEmail(), "JUnit", "Email : ");
assertEquals(user.getMdp(), "JUnit", "Mot de passe vide");

}

@Test
@Order(3)
public void seConnecterSansMdp() throws Exception {
	user = new Model.user("JUnit", null);
	Manager_connexion co = new Manager_connexion();
	co.Connexion(null, null, null, user);
	assertNull(user.getEmail(), "Email vide");
	assertNotNull(user.getMdp(), "Mot de passe : " + user.getMdp());
	user = new Model.user("", "JUnit");
	co.Connexion(null, null, null, user);
	assertNotEquals(user.getEmail(), "JUnit", "Email : ");
	assertEquals(user.getMdp(), "JUnit", "Mot de passe vide");
}

@Test
@Order(4)
public void seConnecterAvecRien() throws Exception {
	user = new Model.user(null, null);
	Manager_connexion co = new Manager_connexion();
	co.Connexion(null, null, null, user);
	assertNull(user.getEmail(), "Email vide");
	assertNotNull(user.getMdp(), "Mot de passe : " + user.getMdp());
	user = new Model.user("", "JUnit");
	co.Connexion(null, null, null, user);
	assertNotEquals(user.getEmail(), "JUnit", "Email : ");
	assertEquals(user.getMdp(), "JUnit", "Mot de passe vide");
}

@Test
@Order(5)
public void seConnecterAvecMauvaisLogin() throws Exception {
user = new Model.user("JavaScript", "JUnit");
Manager_connexion co = new Manager_connexion();
co.Connexion(null, null, null, user);
assertNotEquals(user.getEmail(), "JUnit", "Email : " + user.getEmail());
assertEquals(user.getMdp(), "JUnit", "Mot de passe : " + user.getMdp());
}

@Test
@Order(6)
public void seConnecterAvecMauvaisMdp() throws Exception {
user = new Model.user("JUnit", "JavaScript");
Manager_connexion co = new Manager_connexion();
co.Connexion(null, null, null, user);
assertEquals(user.getEmail(), "JUnit", "Email : " + user.getEmail());
assertNotEquals(user.getMdp(), "JUnit", "Mot de passe : " + user.getMdp());
}

@Test
@Order(7)
public void seConnecterAvecMauvaisChamps() throws Exception {
user = new Model.user("JavaScript", "JavaScript");
Manager_connexion co = new Manager_connexion();
co.Connexion(null, null, null, user);
assertNotEquals(user.getEmail(), "JUnit", "Email : " + user.getEmail());
assertNotEquals(user.getMdp(), "JUnit", "Mot de passe : " + user.getMdp());
}

@AfterAll
public void after() throws Exception {
stm.executeUpdate("DELETE FROM Utilisateur WHERE Id = LAST_INSERT_ID();");
}
}