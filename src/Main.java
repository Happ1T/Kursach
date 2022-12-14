import java.io.IOException;
import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            MyGrath test = new MyGrath("impute.txt");

            MyArrayList<Node> node = Krus.algorithm(test.node, test.hashArray.size());

            List<String> output = new ArrayList<>();

            int fullWeight = 0;
            for (Node N : node)
            {
                output.add(test.hashArray.get(Math.min(N.from, N.to)) + " " + test.hashArray.get(Math.max(N.from, N.to)));
                fullWeight += N.weight;
            }

            output = output.stream().sorted().toList();

            for (String S : output)
            {
                System.out.println(S);
            }

            System.out.println(fullWeight);

        } catch (IOException N)
        {
            System.out.println("Не удалось открыть файл");
        } catch (IndexOutOfBoundsException N)
        {
            System.out.println("Неверный ввод");
        }
    }
}