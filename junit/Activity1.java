import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Activity1
{
    static List<String> list1;

    @BeforeAll
    static public void setUp()
    {
        list1 = new ArrayList<String>();
        list1.add("dhruv");
        list1.add("aryan");
    }

    @Test
    public void insertTest()
    {
        //assertion to check the size of the list
        assertEquals(2, list1.size(), "size of the list now is 2");

        //add new element
        list1.add("arjun");

        //assertion to check the size of the list
        assertEquals(3, list1.size(), "Size of the list now is 3");

        //assertion to check each value in list
        assertEquals("dhruv", list1.get(0), "this is the first element in list");
        assertEquals("aryan", list1.get(1), "this is the second element in list");
        assertEquals("arjun", list1.get(2), "this is the third element in list");
    }

    @Test
    public void replaceTest()
    {
        //assertion to check current list size
        assertEquals(2,list1.size(),"size of the list is 2");

        //replace with new element
        list1.set(1,"arjun");
        assertEquals(2,list1.size(),"size of the list is 2");

        //assertion to check each value in list
        assertEquals("dhruv", list1.get(0), "this is the first element in list");
        assertEquals("arjun", list1.get(1), "this is the second element in list");

    }








}
