class MyStack<T>
{
    private class node
    {
        T values;
        node next;

        node(T values)
        {
            this.values = values;
        }
    }

    node nullNode;
    node head;
    int size;

    public MyStack(T nullNode)
    {
        this.nullNode = new node(nullNode);
        this.head = null;
        this.size = 0;
    }

    boolean isEmpty()
    {
        return head == null;
    }

    public void push(T values)
    {
        node tmp = new node(values);
        tmp.next = head;
        head = tmp;
        size++;
    }

    void pop()
    {
        if (!isEmpty())
        {
            node tmp = head;
            head = head.next;
            size--;
        }
    }

    public T peek(int index)
    {
        if (!isEmpty() && index < size)
        {
            node tmp = head;
            for (int i = 0; i < index; i++)
            {
                tmp = tmp.next;
            }

            return tmp.values;
        } else
        {
            return nullNode.values;
        }
    }

    public void edit(T values, int index)
    {
        if (!isEmpty() && index < size)
        {
            node tmp = head;
            tmp.values = values;
        }
    }
}