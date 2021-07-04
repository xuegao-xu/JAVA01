package moocSuper;
import java.util.Locale;
import java.util.ResourceBundle;

public class mooc03{
    public static void main(String[] args)  {
        Locale myLocale = Locale.getDefault();
        System.out.println(myLocale);
        ResourceBundle  nameBundle = ResourceBundle.getBundle("msg",myLocale);
        ResourceBundle  universityBundle= ResourceBundle.getBundle("msg",myLocale);
        System.out.println(universityBundle.getString("university"));
        System.out.println(nameBundle.getString("name"));
        myLocale= new Locale("en","US");
        nameBundle = ResourceBundle.getBundle("msg",myLocale);
        System.out.println(nameBundle.getString("name"));
    }
}