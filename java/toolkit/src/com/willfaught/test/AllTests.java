package com.willfaught.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import com.willfaught.test.data.*;

@RunWith(Suite.class)
@SuiteClasses({ LinkedListTest.class, StringBuilderTest.class })
public class AllTests
{
}