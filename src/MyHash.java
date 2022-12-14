public class MyHash<K, V>
{
    private class node<K, V>
    {
        K key;
        V value;

        node(K key, V value)
        {
            this.key = key;
            this.value = value;
        }
    }

    MyArrayList<node> array = new MyArrayList<>();

    public boolean is_empty()
    {
        return array.size() == 0;
    }

    public boolean contains_key(K key)
    {
        for (int i = 0; i < array.size(); i++)
        {
            if (array.get(i).key == key)
            {
                return true;
            }
        }

        return false;
    }

    public boolean contains_value(V value)
    {
        for (int i = 0; i < array.size(); i++)
        {
            if (array.get(i).value == value)
            {
                return true;
            }
        }

        return false;
    }

    public boolean put(K key, V value)
    {
        for (int i = 0; i < array.size(); i++)
        {
            if (key == array.get(i).key)
            {
                array.set(new node<>(key, value), i);
                return true;
            }
        }

        array.add(new node<>(key, value));
        return true;
    }

    public void remove(K key)
    {
        for (int i = 0; i < array.size(); i++)
        {
            if (array.get(i).key == key)
            {
                array.remove(i);
                break;
            }
        }
    }

    public void remove(K key, V value)
    {
        for (int i = 0; i < array.size(); i++)
        {
            if (array.get(i).key == key && array.get(i).value == value)
            {
                array.remove(i);
                break;
            }
        }
    }

    public void clear()
    {
        array.clear();
    }

    public int size()
    {
        return array.size();
    }

    public V get(K key)
    {
        for (int i = 0; i < array.size(); i++)
        {
            if (array.get(i).key == key)
            {
                return (V) array.get(i).value;
            }
        }

        return null;
    }
}