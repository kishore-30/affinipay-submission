package assignment;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.affinipay.assignment.representation.UserRequestRepresentation;
import com.affinipay.assignment.resource.TimeDeltaResource;

class TestCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(TimeDeltaResource.class);
	TimeDeltaResource test = new TimeDeltaResource();
	UserRequestRepresentation test1= new UserRequestRepresentation();
	static String transactionId = UUID.randomUUID().toString();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		LOGGER.info("***************Starting Test Cases***************");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		LOGGER.info("***************Completed Test Cases***************");
	}

	@Test
	@Order(1)
	@DisplayName("Test 1")
	void test1() {
		try {
			assertEquals("12:33 PM", test.addDelta(transactionId, "9:13 AM", 200));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	@Order(2)
	@DisplayName("Test 2")
	void test2() {
		try {
			assertEquals("9:07 AM", test.addDelta(transactionId, "9:03 AM", 4));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	@Order(3)
	@DisplayName("Test 3")
	void test3() {
		try {
			assertEquals("12:00 AM", test.addDelta(transactionId, "11:59 PM", 1));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	@Order(4)
	@DisplayName("Test 4")
	void test4() {
		try {
			assertEquals("12:03 PM", test.addDelta(transactionId, "11:59 AM", 4));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	@Order(5)
	@DisplayName("Test 5")
	void test5() {
		try {
			assertEquals("5:25 AM", test.addDelta(transactionId, "3:45 AM", 100));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	@Order(6)
	@DisplayName("Test 6")
	void test6() {
		try {
			assertEquals("9:39 AM", test.addDelta(transactionId, "4:59 PM", 1000));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	@Order(7)
	@DisplayName("Test 7")
	void test7() {
		try {
			assertEquals("2:00 AM", test.addDelta(transactionId, "1:00 AM", 60));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	@Order(8)
	@DisplayName("Test 8")
	void test8() {
		try {
			assertEquals("4:20 PM", test.addDelta(transactionId, "6:00 PM", -100));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	@Order(9)
	@DisplayName("Test 9")
	void test9() {
		try {
			assertThrows(Exception.class, ()->test.addDelta(transactionId, "2:00 AM", -1440));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	@Order(10)
	@DisplayName("Test 10")
	void test10() {
		try {
			assertEquals("3:00 PM", test.addDelta(transactionId, "3:00 PM", 1440));
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	@Order(11)
	@DisplayName("Test 11")
	void test11() {
		try {
			assertThrows(Exception.class, ()->test1.setTime("9:133 AM"));
		} catch (Exception e) {
			fail();
		}
	}
	@Test
	@Order(12)
	@DisplayName("Test 12")
	void test12() {
		try {
			assertThrows(Exception.class, ()->test1.setTime("29:13 ZM"));
		} catch (Exception e) {
			fail();
		}
	}
	@Test
	@Order(13)
	@DisplayName("Test 13")
	void test13() {
		try {
			assertThrows(Exception.class, ()->test1.setTime("9:133 M"));
		} catch (Exception e) {
			fail();
		}
	}
	@Test
	@Order(14)
	@DisplayName("Test 14")
	void test14() {
		try {
			assertThrows(Exception.class, ()->test1.setTime("009:133 ZM"));
		} catch (Exception e) {
			fail();
		}
	}
	@Test
	@Order(15)
	@DisplayName("Test 15")
	void test15() {
		try {
			assertThrows(Exception.class, ()->test1.setTime("9:133 ZM"));
		} catch (Exception e) {
			fail();
		}
	}

}
