import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

// I appreciate that this is a disaster of a way to implement this, but if it works... :_)
public class NameFinder {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Enter the email ID of the name you would like to search : ");
    String emailId = reader.readLine(); // Takes the email ID as an input

    URL url =  new URL("https://www.ecs.soton.ac.uk/people/" + emailId); // Creates the URL for the profile page
    Scanner sc = new Scanner(url.openStream()); // And opens a connection to the webpage
    StringBuffer sb = new StringBuffer();

    String lookFor = "property=\"name\">"; // This is the HTML we would expect immediately before the name

    while (sc.hasNext()) { // Places the contents of the webpage into sb
      sb.append(sc.next());
    }
    String result = sb.toString();

    String nameHold = "";
    int counter = 0;
    for (int i = 0; i<result.length();i++) { // Runs through the content of the webpage looking for the preceding HTML
      if (result.charAt(i) == lookFor.charAt(counter)) {
        counter++;
        if (counter == lookFor.length()) {
          i++;
          while (result.charAt(i)!='<') { // Once the preceding HTML is found, Saves each character after to "nameHold" until the next character is a less than sign
            nameHold += result.charAt(i);
            i++;
          }
          System.out.println(nameHold);
          counter = 0;
          break;
        }
      } else {
        counter = 0;
      }
    }
  }
}
