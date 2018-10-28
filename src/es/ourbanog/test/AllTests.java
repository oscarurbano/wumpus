package es.ourbanog.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	Giros.class,
	TirosFlechas.class,
	Movimientos.class
})
public class AllTests {

}
