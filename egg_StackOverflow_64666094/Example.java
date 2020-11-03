
import java.util.*;
import java.util.stream.*;

class Account{    
   private List<AccountRole> accountRoles;
   public List<AccountRole> getAccountRoles() { return accountRoles; }
   public void setAccountRoles(List<AccountRole> accountRoles) { this.accountRoles = accountRoles; }
}

class AccountRole{
   private List<Operation> allowedOperations;
   private String authority; 

   public List<Operation> getAllowedOperations() { return allowedOperations; }
   public void setAllowedOperations(List<Operation> ops) { this.allowedOperations = ops; }

   public String getAuthority() { return authority; }
   public void setAuthority(String authority) { this.authority = authority; } 
}

class Operation{
    private String authority; 
  public String getAuthority() { return authority; }
   public void setAuthority(String authority) { this.authority = authority; } 
}

public class Example {
    void go() {
        var account = new Account();
        var accountRoles = new ArrayList<AccountRole>();
        account.setAccountRoles(accountRoles);

        for (int i = 1; i <= 5; i++) {
            var authority = "item" + i;

            var operation = new Operation();
            operation.setAuthority(authority);

            var operations = new ArrayList<Operation>();
            operations.add(operation);

            var accountRole = new AccountRole();
            accountRole.setAuthority(authority);
            accountRole.setAllowedOperations(operations);

            accountRoles.add(accountRole); 
        }

        var results = account.getAccountRoles()
                             .stream()
                             .flatMap(role -> 
                                Stream.concat(role.getAllowedOperations()
                                                  .stream()
                                                  .map(Operation::getAuthority),
                                              Stream.of(role.getAuthority())))
                             .collect(Collectors.toList());

        System.out.println("TRACER # results: " + results.size());
    }

    public static void main(String[] args) {
        var example = new Example();
        example.go();
        System.out.println("Ready.");
    }
}
