package assignment6;

import static org.junit.Assert.fail;

import org.junit.Test;

public class TestTicketOffice {

	public static int score = 0;

//	 @Test
//	public void basicServerTest() {
//		try {
//			TicketServer.start(16789, "Box Office A");
//		} catch (Exception e) {
//			fail();
//		}
//		TicketClient client = new TicketClient(16789);
//		client.requestTicket();
//	}
//
//	@Test
//	public void testServerCachedHardInstance() {
//		try {
//			TicketServer.start(16790, "Box Office A");
//		} catch (Exception e) {
//			fail();
//		}
//		TicketClient client1 = new TicketClient("c1", 16790);
//		TicketClient client2 = new TicketClient("c2", 16790);
//		client1.requestTicket();
//		client2.requestTicket();
//		
//	}

//	@Test
//	public void twoNonConcurrentServerTest() {
//		try {
//			TicketServer.start(16791, "Box Office A");
//		} catch (Exception e) {
//			fail();
//		}
//		TicketClient c1 = new TicketClient("nonconc1", 16791);
//		TicketClient c2 = new TicketClient("nonconc2", 16791);
//		TicketClient c3 = new TicketClient("nonconc3", 16791);
//		c1.requestTicket();
//		c2.requestTicket();
//		c3.requestTicket();
//	}
//
//	@Test
//	public void twoConcurrentServerTest() {
//		try {
//			TicketServer.start(16792, "Box Office A");
//		} catch (Exception e) {
//			fail();
//		}
//		final TicketClient c1 = new TicketClient("conc1", 16792);
//		final TicketClient c2 = new TicketClient("conc2", 16792);
//		final TicketClient c3 = new TicketClient("conc3", 16792);
//		Thread t1 = new Thread() {
//			public void run() {
//				c1.requestTicket();
//			}
//		};
//		Thread t2 = new Thread() {
//			public void run() {
//				c2.requestTicket();
//			}
//		};
//		Thread t3 = new Thread() {
//			public void run() {
//				c3.requestTicket();
//			}
//		};
//		t1.start();
//		t2.start();
//		t3.start();
//		try {
//			t1.join();
//			t2.join();
//			t3.join();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		

//	}
	
//	@Test
//	public void soldOutNonConcurrentTest() {
//	    try {
//            TicketServer.start(16793, "Box Office A");
//        } catch (Exception e) {
//            fail();
//        }
//	    for (int i = 0; i < 800; i++){
//            final TicketClient c1 = new TicketClient("client " + i, 16793);
//            c1.requestTicket();
//            
//	    }
//	    
//	}
	
//	@Test
//	public void soldOutConcurrentTest() {
//	    try {
//            TicketServer.start(16794, "Box Office A");
//        } catch (Exception e) {
//            fail();
//        }
//	    Thread[] threads = new Thread[900];
//	    for (int i = 0; i < 900; i++){
//	        TicketClient c1 = new TicketClient("client " + i, 16794);
//	        threads[i] = new Thread() {
//	          public void run() {
//	              c1.requestTicket();
//	          }
//	      };
//	    }
//	    for (int i = 0; i < 900; i++){
//	        threads[i].start();
//	    }
//	    for (int i = 0; i < 900; i++){
//	        try {
//	          threads[i].join();
//	      } catch (Exception e) {
//	          e.printStackTrace();
//	      }
//	    }
//	}
	
//	@Test 
//	public void twoServersNonConcurrentTest() {
//	     try {
//	         TicketServer.start(16794, "Box Office A");
//	         TicketServer.start(16795, "Box Office B");
//	     } catch (Exception e) {
//	         fail();
//	     }
//	     TicketClient client1 = new TicketClient("client 1", 16794);
//	     TicketClient client2 = new TicketClient("client 2", 16795);
//	     client1.requestTicket();
//	     client2.requestTicket();
//	}
	
//	 @Test
//  public void soldOutTwoServersNonConcurrentTest() {
//      try {
//            TicketServer.start(16796, "Box Office A");
//            TicketServer.start(16797, "Box Office B");
//        } catch (Exception e) {
//            fail();
//        }
//      for (int i = 0; i < 800; i++) {
//          final TicketClient c1;
//          if (i%2 == 0) {
//              c1 = new TicketClient("client " + i, 16796);    
//          }
//          else {
//              c1 = new TicketClient("client " + i, 16797);   
//          }
//          
//          c1.requestTicket();
//      }
//      
//  }
	
	 @Test
  public void soldOutConcurrentTwoServersTest() {
      try {
            TicketServer.start(16798, "Box Office A");
            TicketServer.start(16799, "Box Office B");
        } catch (Exception e) {
            fail();
        }
      Thread[] threads = new Thread[800];
      for (int i = 0; i < 800; i++){
          final TicketClient c1;
          if (i % 2 == 0){
              c1 = new TicketClient("client " + i, 16798);
          }
          else {
              c1 = new TicketClient("client " + i, 16799);
          }
          threads[i] = new Thread() {
            public void run() {
                c1.requestTicket();
            }
        };
      }
      for (int i = 0; i < 800; i++){
          threads[i].start();
      }
      for (int i = 0; i < 800; i++){
          try {
            threads[i].join();
        } catch (Exception e) {
            e.printStackTrace();
        }
      }
  }
	

}
