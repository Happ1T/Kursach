import java.util.Iterator;

public class MyArrayList<T> implements Iterable<T>
{
    private class dynamic_array_iterator implements Iterator<T>
    {
        private int index = 0;

        public boolean hasNext()
        {
            return index < size();
        }

        public T next()
        {
            return (T) get(index++);
        }
    }

    private int default_size = 10;
    private int size = 0;
    private Object[] array = new Object[default_size];

    private void resize(int size)
    {
        Object[] new_array = new Object[size];
        System.arraycopy(array, 0, new_array, 0, array.length);
        array = new_array;
    }

    public void add(T obj)
    {
        if (size >= array.length - 1)
        {
            resize(array.length * 2);
        }
        array[size] = obj;
        size++;
    }

    public void remove(int index)
    {
        if (index < size && !is_empty())
        {
            if (size - 1 <= array.length / 4 && array.length > default_size)
            {
                resize(array.length / 2);
            }
            System.arraycopy(array, 0, array, 0, index);
            System.arraycopy(array, index + 1, array, index, array.length - index - 1);
            size--;
        }
    }

    public void remove(T obj)
    {
        for (int i = 0; i < size; i++)
        {
            if (array[i] == obj)
            {
                remove(i);
                break;
            }
        }
    }

    public void remove_all(T obj)
    {
        for (int i = 0; i < size; i++)
        {
            if (array[i] == obj)
            {
                remove(i);
            }
        }
    }

    public void clear()
    {
        Object[] new_array = new Object[default_size];
        array = new_array;
        size = 0;
    }

    public void set(T obj, int index)
    {
        if (index < size && !is_empty())
        {
            array[index] = obj;
        }
    }

    public boolean is_empty()
    {
        return size == 0;
    }

    public boolean contains(T obj)
    {
        for (int i = 0; i < size; i++)
        {
            if (array[i] == obj)
            {
                return true;
            }
        }

        return false;
    }

    public int index_of(T obj)
    {
        for (int i = 0; i < size; i++)
        {
            if (array[i] == obj)
            {
                return i;
            }
        }

        return -1;
    }

    public int size()
    {
        return this.size;
    }

    public T get(int index)
    {
        if (index < size && !is_empty())
        {
            return (T) array[index];
        }

        return null;
    }

    public Iterator<T> iterator()
    {
        return new dynamic_array_iterator();
    }
}