# Lazy Loading Vs Eager Loading

## Requirements

You are provided with 3 models - `Employee`, `IdentityProof` and `Team`.

Fields are already present inside these models. 
You need to
 - Make sure tables are created for each of these.
 - Define Cardinalities among these entities.
 - Also make sure that employee data is loaded lazily when asked for team details and proofs are loaded eagerly when asked for Employee Details.

You also need to add implementation in Service layer methods
 
- `getEmployeeDetails` - This should return details of Employee having particular id.
- `getTeamDetails` - This should return details about Team having particular id.

You also need to use JpaRepository in all repos present in this assignment.

## Hints

- Nothing is needed from your side in pom.xml or application.properties
- No new file need to be created, No fields need to be added/modified/removed.
- If you will try to run testcases without giving solution, all Testcases will fail.
