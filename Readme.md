# Description:
Optimizes football teams for the kicker managerspiel (www.kicker.de) based on the
points from the last year (better point prediction model todo).
Modeled as a Knapsack problem solved by SAT and EAs provided by Opt4J (http://opt4j.sourceforge.net/).


# Build and Run:

## Eclipse:
⋅⋅* right click on Opt4J.lauch "run as"

## Ant:
⋅⋅* ant build
⋅⋅* ant Opt4J

## Gradle: 
⋅⋅* gradle tasks

# Features:

## Inclusion/Exclusion Lists:
⋅⋅* Players specified in these lists are always included in the team (inclusion list)
  or are excluded from the team selection (exclusion list)

# Known Issues/bugs:
⋅⋅* Budget doesn't adjust itself to the chosen league and mode (interactive/classic)

# Todos:
⋅⋅* Couple with point prediction.