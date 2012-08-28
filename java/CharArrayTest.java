package com.willfaught;

import static com.willfaught.Assert.*;

public class CharArrayTest
{
    @Test
    public void basic()
    {
        begin("empty");
        CharArray c = new CharArray();
        assertEqual("empty", "", c.toString());
        end();
        
        begin("one letter");
        c.append("H");
        assertEqual("letter", "H", c.toString());
        end();
        
        begin("many letters");
        c.append("ello");
        assertEqual("word", "Hello", c.toString());
        end();
        
        begin("many words");
        c.append(", ");
        c.append("world");
        c.append("!");
        assertEqual("phrase", "Hello, world!", c.toString());
        end();
    }
}