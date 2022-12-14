class Krus
{
    MyHash<Integer, Integer> parent = new MyHash<>();

    Krus(int count)
    {
        for (int i = 0; i < count; i++)
        {
            parent.put(i, i);
        }
    }

    private int find(int value)
    {
        if (parent.get(value) == value)
        {
            return value;
        }

        return find(parent.get(value));
    }

    private void union(int a, int b)
    {
        int rootA = find(a);
        int rootB = find(b);

        parent.put(rootA, rootB);
    }

    public static MyArrayList<Node> algorithm(MyArrayList<Node> node, int count)
    {
        MyArrayList<Node> list = new MyArrayList<>();
        Krus set = new Krus(count);

        TimSort sort = new TimSort();

        node = sort.algorithm(node);

        int index = 0;
        while (list.size() != count - 1)
        {
            Node nextNode = node.get(index);
            index++;

            int x = set.find(nextNode.from);
            int y = set.find(nextNode.to);

            if (x != y)
            {
                list.add(nextNode);
                set.union(x, y);
            }
        }

        return list;
    }
}
