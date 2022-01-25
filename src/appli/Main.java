package appli;

public class Main
{

	public static void main(String[] args)
	{
		try
		{
			Connexion window = new Connexion();
			window.open();	
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
