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
//import org.springframework.security.crypto.bcrypt.BCrypt;

import com.dbconnexion.Database;
import Model.user;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class testConnexion {
private static user user;
static Connection coBdd;
static Statement stm;
//private static String hash = BCrypt.hashpw("JUnit", BCrypt.gensalt(10));

@BeforeAll
public void before() throws Exception {
coBdd = Database.getInstance();
stm = coBdd.createStatement();
stm.executeUpdate(
"INSERT INTO utilisateurs VALUES (DEFAULT, 'LIGNANI', 'QUENTIN', 'admin@admin.fr', '1234', 'ADMIN');");
}

@Test
@Order(1)
public void seConnecter() throws Exception {
	user = new user(null, "1234");
	System.out.println(user.getMdp());
	ConnexionJUnit co = new ConnexionJUnit();
	co.connexion(user);
	assertNull(user.getEmail(), "Email vide");
	assertNotNull(user.getMdp(), "Mot de passe : " + user.getMdp());
	user = new user("", "1234");
	co.connexion(user);
	assertNotEquals(user.getEmail(), "admin@admin.fr", "Email : ");
	assertEquals(user.getMdp(), "1234");
}

@Test
@Order(2)
public void seConnecterSansLogin() throws Exception {
user = new user(null, "1234");
ConnexionJUnit co = new ConnexionJUnit();
co.connexion(user);
assertNull(user.getEmail(), "Email vide");
assertNotNull(user.getMdp(), "Mot de passe : " + user.getMdp());
user = new user("", "1234");
co.connexion(user);
assertNotEquals(user.getEmail(), "admin@admin.fr", "Email : ");
assertEquals(user.getMdp(), "1234", "Mot de passe vide");

}

@Test
@Order(3)
public void seConnecterSansMdp() throws Exception {
	user = new user(null, "1234");
	ConnexionJUnit co = new ConnexionJUnit();
	co.connexion(user);
	assertNull(user.getEmail(), "Email vide");
	assertNotNull(user.getMdp(), "Mot de passe : " + user.getMdp());
	user = new user("", "1234");
	co.connexion(user);
	assertNotEquals(user.getEmail(), "admin@admin.fr", "Email : ");
	assertEquals(user.getMdp(), "1234", "Mot de passe vide");
}

@Test
@Order(4)
public void seConnecterAvecRien() throws Exception {
	user = new user(null, null);
	ConnexionJUnit co = new ConnexionJUnit();
	co.connexion(user);
	assertNull(user.getEmail(), "Email vide");
	assertNull(user.getMdp(), "Mot de passe vide");
	user = new user("", "");
	co.connexion(user);
	assertNotEquals(user.getEmail(), "admin@admin.fr", "Email :");
	assertNotEquals(user.getMdp(), "1234", "Mot de passe vide");
}

@Test
@Order(5)
public void seConnecterAvecMauvaisLogin() throws Exception {
user = new user("JavaScript", "1234");
ConnexionJUnit co = new ConnexionJUnit();
co.connexion(user);
assertNotEquals(user.getEmail(), "admin@admin.fr", "Email : " + user.getEmail());
assertEquals(user.getMdp(), "1234", "Mot de passe : " + user.getMdp());
}

@Test
@Order(6)
public void seConnecterAvecMauvaisMdp() throws Exception {
user = new Model.user("admin@admin.fr", "JavaScript");
ConnexionJUnit co = new ConnexionJUnit();
co.connexion(user);
assertEquals(user.getEmail(), "admin@admin.fr", "Email : " + user.getEmail());
assertNotEquals(user.getMdp(), "1234", "Mot de passe : " + user.getMdp());
}

@Test
@Order(7)
public void seConnecterAvecMauvaisChamps() throws Exception {
user = new Model.user("JavaScript", "JavaScript");
ConnexionJUnit co = new ConnexionJUnit();
co.connexion(user);
assertNotEquals(user.getEmail(), "admin@admin.fr", "Email : " + user.getEmail());
assertNotEquals(user.getMdp(), "1234", "Mot de passe : " + user.getMdp());
}

@AfterAll
public void after() throws Exception {
stm.executeUpdate("DELETE FROM utilisateurs WHERE Id = LAST_INSERT_ID();");
}
}