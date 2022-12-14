import java.io.*;
import java.util.regex.*;

public class MyGrath
{
    MyArrayList<Node> node = new MyArrayList<>();
    MyHash<Integer, String> hashArray = new MyHash<>();

    MyGrath(String path) throws IOException
    {
        readFile(path);
    }

    public void readFile(String path) throws IOException
    {
        BufferedReader buffer = new BufferedReader(new FileReader(path));
        String string = buffer.readLine() + '\n';

        Pattern pattern = Pattern.compile("[A-Z0-9-]{1,5}[\t\n]");
        Matcher matcher = pattern.matcher(string);

        int currentColomn = 0;
        while (matcher.find())
        {
            hashArray.put(currentColomn, string.substring(matcher.start(), matcher.end() - 1));
            currentColomn++;

            if (currentColomn > 49)
            {
                throw new IndexOutOfBoundsException();
            }
        }

        for (int currentString = 0; currentString < hashArray.size(); currentString++)
        {
            currentColomn = 0;
            string = buffer.readLine() + "\n";

            matcher = pattern.matcher(string);

            while (matcher.find())
            {
                int weight = Integer.parseInt(string.substring(matcher.start(), matcher.end() - 1));
                if (currentString != currentColomn && weight != 0)
                {
                    node.add(new Node(currentString, currentColomn, Math.abs(weight)));
                }
                currentColomn++;
            }

            if (currentColomn < hashArray.size() || currentColomn > hashArray.size())
            {
                throw new IndexOutOfBoundsException();
            }
        }
    }
}
