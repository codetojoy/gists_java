
package net.codetojoy;

public class MyClass {
    public String methodToBeTested() {
      Logger logger = LoggerFact.getLogger(this.getClass(), "test");
      logger.info("This is a test");
      return "SUCCESS";
    }
}
