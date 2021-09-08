import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parse implements LoggerDetector {
    String IPv4 = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
    Pattern pattern = Pattern.compile(IPv4);
    Matcher match;
    int fiveMin = 300;

    Deque<String> timeCapture = new LinkedList<>();
    int FailCount = 0;

    @Override
    public String parseLine(String line) {
        String[] lineList = line.split(",");

        if (!pattern.matcher(lineList[0]).matches()) {
            return "Detected IP number " + lineList[0] + " is an Invalid IP4 number";
        }

        if (lineList[2].equals("SUCCESS")) {
            System.out.println("User Successfully Logged in!");
            return "User Successfully Logged in!";
        } else if (lineList[2].equals("FAILURE")) {
            timeCapture.add(lineList[1]);
        }

        removeItem();

        if (timeCapture.size()<=5 && lineList[2].equals("FAILURE")){
            System.out.println("[" + (timeCapture.size()) + "/5]");
        }
        if(timeCapture.size()==5 && ((Integer.valueOf(timeCapture.getLast()) - Integer.valueOf(timeCapture.getFirst()))) <= 300){
            System.out.println(lineList[0]);
            timeCapture.clear();
            return lineList[0];
        }
/*
        while ( ((Integer.valueOf(timeCapture.getFirst()) - Integer.valueOf(timeCapture.getLast()))) >= 300) {
            FailCount--;
            timeCapture.removeFirst();

        }*/
        return "Login Failed!";
    }


    public void removeItem (){

        if (timeCapture.size()>0
                && ((Integer.valueOf(timeCapture.getLast()) - Integer.valueOf(timeCapture.getFirst()))) >= 300) {
            timeCapture.removeFirst();
            removeItem();
        }
    }
}
