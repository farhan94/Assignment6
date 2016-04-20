package assignment6;

import static org.junit.Assert.fail;

import org.junit.Test;

public class TestTicketOffice {

  public static int score = 0;
/*  @Test
  public void basicServerTest() {
    try {
      TicketServer.start(16789, "Box Office A");
    } catch (Exception e) {
      fail();
    }
    TicketClient client = new TicketClient(16789);
    client.requestTicket();
  }

  @Test
  public void testServerCachedHardInstance() {
    try {
      TicketServer.start(16790, "Box Office A");
    } catch (Exception e) {
      fail();
    }
    TicketClient client1 = new TicketClient("c1", 16790);
    TicketClient client2 = new TicketClient("c2", 16790);
    client1.requestTicket();
    client2.requestTicket();
    
  }

  @Test
  public void twoNonConcurrentServerTest() {
    try {
      TicketServer.start(16791, "Box Office A");
    } catch (Exception e) {
      fail();
    }
    TicketClient c1 = new TicketClient("nonconc1", 16791);
    TicketClient c2 = new TicketClient("nonconc2", 16791);
    TicketClient c3 = new TicketClient("nonconc3", 16791);
    c1.requestTicket();
    c2.requestTicket();
    c3.requestTicket();
  }

  @Test
  public void twoConcurrentServerTest() {
    try {
      TicketServer.start(16792, "Box Office A");
    } catch (Exception e) {
      fail();
    }
    final TicketClient c1 = new TicketClient("conc1", 16792);
    final TicketClient c2 = new TicketClient("conc2", 16792);
    final TicketClient c3 = new TicketClient("conc3", 16792);
    Thread t1 = new Thread() {
      public void run() {
        c1.requestTicket();
      }
    };
    Thread t2 = new Thread() {
      public void run() {
        c2.requestTicket();
      }
    };
    Thread t3 = new Thread() {
      public void run() {
        c3.requestTicket();
      }
    };
    t1.start();
    t2.start();
    t3.start();
    try {
      t1.join();
      t2.join();
      t3.join();
    } catch (Exception e) {
      e.printStackTrace();
    }
    

  }
  
  @Test
  public void soldOutNonConcurrentTest() {
      try {
            TicketServer.start(16793, "Box Office A");
        } catch (Exception e) {
            fail();
        }
      for (int i = 0; i < 800; i++){
            final TicketClient c1 = new TicketClient("client " + i, 16793);
            c1.requestTicket();
      }
      
  }
  
  @Test
  public void soldOutConcurrentTest() {
      try {
            TicketServer.start(16794, "Box Office A");
        } catch (Exception e) {
            fail();
        }
      Thread[] threads = new Thread[900];
      for (int i = 0; i < 800; i++){
          TicketClient c1 = new TicketClient("client " + i, 16794);
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
  
  @Test 
  public void twoServersNonConcurrentTest() {
       try {
           TicketServer.start(16795, "Box Office A");
           TicketServer.start(16796, "Box Office B");
       } catch (Exception e) {
           fail();
       }
       TicketClient client1 = new TicketClient("client 1", 16795);
       TicketClient client2 = new TicketClient("client 2", 16796);
       client1.requestTicket();
       client2.requestTicket();
  }
  
   @Test
   public void soldOutTwoServersNonConcurrentTest() {
         try {
               TicketServer.start(16797, "Box Office A");
               TicketServer.start(16798, "Box Office B");
         } catch (Exception e) {
             fail();
         }
         for (int i = 0; i < 800; i++) {
             final TicketClient c1;
             if (i%2 == 0) {
                 c1 = new TicketClient("client " + i, 16797);    
             }
             else {
                 c1 = new TicketClient("client " + i, 16798);   
             }
          
             c1.requestTicket();
         }
      
   }
  
      @Test
      public void TwoServersConcurrentTest() {
         try {
               TicketServer.start(16799, "Box Office A");
               TicketServer.start(16800, "Box Office B");
           } catch (Exception e) {
               fail();
           }
         TicketClient c1 = new TicketClient("client 1", 16799);
         TicketClient c2 = new TicketClient("client 2", 16799);
         TicketClient c3 = new TicketClient("client 3", 16800);
         TicketClient c4 = new TicketClient("client 4", 16800);
         Thread t1 = new Thread() {
             public void run() {
                 c1.requestTicket();
             }
         };
         Thread t2 = new Thread() {
             public void run() {
                 c2.requestTicket();
             }
         };
         Thread t3 = new Thread() {
             public void run() {
                 c3.requestTicket();
             }
         };
         Thread t4 = new Thread() {
             public void run() {
                 c4.requestTicket();
             }
         };
         t1.start();
         t2.start();
         t3.start();
         t4.start();
         try {
             t1.join();
             t2.join();
             t3.join();
             t4.join();
         } catch (Exception e) {
             e.printStackTrace();
         }
         
     }
  
  
   @Test
   public void soldOutConcurrentTwoServersTest() {
          try {
                TicketServer.start(16801, "Box Office A");
                TicketServer.start(16802, "Box Office B");
            } catch (Exception e) {
                fail();
            }
          Thread[] threads = new Thread[800];
          for (int i = 0; i < 800; i++){
              final TicketClient c1;
              if (i % 2 == 0){
                  c1 = new TicketClient("client " + i, 16801);
              }
              else {
                  c1 = new TicketClient("client " + i, 16802);
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
   
       @Test 
       public void threeServersNonConcurrentTest() {
          try {
              TicketServer.start(16803, "Box Office A");
              TicketServer.start(16804, "Box Office B");
              TicketServer.start(16805, "Box Office C");
          } catch (Exception e) {
              fail();
          }
          TicketClient client1 = new TicketClient("client 1", 16803);
          TicketClient client2 = new TicketClient("client 2", 16804);
          TicketClient client3 = new TicketClient("client 3", 16805);
          client1.requestTicket();
          client2.requestTicket();
          client3.requestTicket();
       }
   
      @Test
      public void soldOutThreeServersNonConcurrentTest() {
         try {
               TicketServer.start(16806, "Box Office A");
               TicketServer.start(16807, "Box Office B");
               TicketServer.start(16808, "Box Office C");
           } catch (Exception e) {
               fail();
           }
         for (int i = 0; i < 800; i++) {
             final TicketClient c1;
             if (i%3 == 0) {
                 c1 = new TicketClient("client " + i, 16806);    
             }
             else if (i%3 == 1){
                 c1 = new TicketClient("client " + i, 16807);   
             }
             else {
                 c1 = new TicketClient("client " + i, 16808);
             }
             
             c1.requestTicket();
         }
         
     }
  
        @Test
        public void ThreeServersConcurrentTest() {
           try {
                 TicketServer.start(16809, "Box Office A");
                 TicketServer.start(16810, "Box Office B");
                 TicketServer.start(16811, "Box Office C");
             } catch (Exception e) {
                 fail();
             }
           TicketClient c1 = new TicketClient("client 1", 16809);
           TicketClient c2 = new TicketClient("client 2", 16809);
           TicketClient c3 = new TicketClient("client 3", 16810);
           TicketClient c4 = new TicketClient("client 4", 16810);
           TicketClient c5 = new TicketClient("client 5", 16811);
           TicketClient c6 = new TicketClient("client 6", 16811);
           Thread t1 = new Thread() {
               public void run() {
                   c1.requestTicket();
               }
           };
           Thread t2 = new Thread() {
               public void run() {
                   c2.requestTicket();
               }
           };
           Thread t3 = new Thread() {
               public void run() {
                   c3.requestTicket();
               }
           };
           Thread t4 = new Thread() {
               public void run() {
                   c4.requestTicket();
               }
           };
           Thread t5 = new Thread() {
               public void run() {
                   c5.requestTicket();
               }
           };
           Thread t6 = new Thread() {
               public void run() {
                   c6.requestTicket();
               }
           };
           t1.start();
           t2.start();
           t3.start();
           t4.start();
           t5.start();
           t6.start();
           try {
               t1.join();
               t2.join();
               t3.join();
               t4.join();
               t5.join();
               t6.join();
           } catch (Exception e) {
               e.printStackTrace();
           }
           
        } 


        @Test
        public void soldOutConcurrentThreeServersTest() {
            try {
                  TicketServer.start(16812, "Box Office A");
                  TicketServer.start(16813, "Box Office B");
                  TicketServer.start(16814, "Box Office C");
              } catch (Exception e) {
                  fail();
              }
            Thread[] threads = new Thread[800];
            for (int i = 0; i < 800; i++){
                final TicketClient c1;
                if (i % 3 == 0){
                    c1 = new TicketClient("client " + i, 16812);
                }
                else if (i % 3 == 1) {
                    c1 = new TicketClient("client " + i, 16813);
                }
                else {
                    c1 = new TicketClient("client " + i, 16814);
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
        } */
  

}
