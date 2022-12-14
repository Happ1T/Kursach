public class TimSort<T>
{
    private class node
    {
        int startIndex;
        int length;

        node(int startIndex, int length)
        {
            this.startIndex = startIndex;
            this.length = length;
        }
    }

    private MyStack<node> runs;
    private MyArrayList<Node> array;

    private int getMinrunLenght()
    {
        int bitsS = 0, arrayLenght = array.size();
        while (arrayLenght >= 64)
        {
            bitsS |= arrayLenght & 1;
            arrayLenght >>= 1;
        }

        return arrayLenght + bitsS;
    }

    private void binarSort(int startIndexOfArray, int arrayLenght)
    {
        int left = 0, right = 0, middle = 0;

        for (int i = startIndexOfArray; i - startIndexOfArray < arrayLenght - 1; i++)
        {
            if (array.get(i).weight > array.get(i + 1).weight)
            {
                Node insertValue = array.get(i + 1);
                left = startIndexOfArray;
                right = i;

                do
                {
                    middle = (left + right) / 2;

                    if (array.get(middle).weight < insertValue.weight)
                    {
                        left = middle + 1;
                    } else
                    {
                        right = middle - 1;
                    }
                } while (left <= right);

                for (int j = i; j >= left; j--)
                {
                    array.set(array.get(j), j + 1);
                }
                array.set(insertValue, left);
            }
        }
    }

    private void binarMerge(node firstRun, node secondRun)
    {
        int runsLenght = firstRun.length + secondRun.length, firstP = firstRun.startIndex, secondP = secondRun.startIndex;
        int firstRunEnd = firstRun.length + firstRun.startIndex, secondRunP = secondRun.length + secondRun.startIndex;

        MyArrayList<Node> tmp = new MyArrayList<>();

        for (int i = 0; i < runsLenght; i++)
        {

            if (firstP >= firstRunEnd)
            {
                tmp.add(array.get(secondP));
                secondP++;
            } else if (secondP >= secondRunP)
            {
                tmp.add(array.get(firstP));
                firstP++;
            } else if (array.get(firstP).weight < array.get(secondP).weight)
            {
                tmp.add(array.get(firstP));
                firstP++;

            } else
            {
                tmp.add(array.get(secondP));
                secondP++;
            }

        }

        int startIndexOfArray = Math.min(firstRun.startIndex, secondRun.startIndex);
        for (int i = 0; i < runsLenght; i++)
        {
            array.set(tmp.get(i), i + startIndexOfArray);
        }
    }

    private void mergeRuns()
    {
        node z = runs.peek(2);
        node y = runs.peek(1);
        node x = runs.peek(0);
        boolean isX = z.length > y.length + x.length || runs.size < 3, isY = y.length > x.length || runs.size < 2;

        while (!isX || !isY)
        {
            runs.pop();
            if (z.length != 0)
            {
                runs.pop();
                if (z.length < x.length)
                {
                    binarMerge(y, z);

                    runs.edit(new node(Math.min(y.startIndex, z.startIndex), y.length + z.length), 0);
                    runs.push(new node(x.startIndex, x.length));
                } else
                {
                    binarMerge(y, x);
                    runs.push(new node(Math.min(y.startIndex, x.startIndex), y.length + x.length));
                }
            } else
            {
                binarMerge(y, x);

                runs.edit(new node(Math.min(y.startIndex, x.startIndex), y.length + x.length), 0);
            }

            z = runs.peek(2);
            y = runs.peek(1);
            x = runs.peek(0);
            isX = z.length > y.length + x.length || runs.size < 3;
            isY = y.length > x.length || runs.size < 2;
        }
        ;
    }

    private void finalMerge()
    {
        while (!runs.isEmpty())
        {
            node firstRun = runs.peek(1), secondRun = runs.peek(0);
            binarMerge(firstRun, secondRun);
            runs.pop();
            runs.edit(new node(Math.min(firstRun.startIndex, secondRun.startIndex), firstRun.length + secondRun.length), 0);
        }
    }

    private void divideToRuns()
    {
        int i = 0, minRunLenght = getMinrunLenght();
        while (i < array.size())
        {
            int runStartIndex = i, runLenght = minRunLenght;

            if (i + minRunLenght < array.size())
            {
                binarSort(i, minRunLenght);
                i += minRunLenght;

                while (array.get(i - 1).weight <= array.get(i).weight)
                {
                    runLenght++;
                    i++;
                }
            } else
            {
                binarSort(i, array.size() - i);
                runLenght = array.size() - i;
                i = array.size();
            }

            runs.push(new node(runStartIndex, runLenght));
        }
    }

    public MyArrayList<Node> algorithm(MyArrayList<Node> array)
    {
        this.runs = new MyStack<>(new node(0, 0));
        this.array = array;
        divideToRuns();
        mergeRuns();
        finalMerge();
        return array;
    }
}
