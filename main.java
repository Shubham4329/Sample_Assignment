import java.util.Scanner;

class Match {
    private String location;
    private String team1;
    private String team2;
    private String timing;
    private Match next;

    public Match(String location, String team1, String team2, String timing) {
        this.location = location;
        this.team1 = team1;
        this.team2 = team2;
        this.timing = timing;
        this.next = null;
    }

    public Match getNext() {
        return next;
    }

    public void setNext(Match next) {
        this.next = next;
    }

    public String getLocation() {
        return location;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public String getTiming() {
        return timing;
    }

    public String toString() {
        return team1 + " vs " + team2 + " at " + location + " (" + timing + ")";
    }
}

class FlightTable {
    private Match head;

    public FlightTable() {
        head = null;
    }

    public void addMatch(String location, String team1, String team2, String timing) {
        Match newMatch = new Match(location, team1, team2, timing);
        if (head == null) {
            head = newMatch;
        } else {
            Match current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newMatch);
        }
    }

    public Match getMatches() {
        return head;
    }

    public Match getMatchesByTeam(String team) {
        Match current = head;
        Match teamMatches = null;

        while (current != null) {
            if (current.getTeam1().equals(team) || current.getTeam2().equals(team)) {
                if (teamMatches == null) {
                    teamMatches = new Match(current.getLocation(), current.getTeam1(), current.getTeam2(), current.getTiming());
                } else {
                    Match newMatch = new Match(current.getLocation(), current.getTeam1(), current.getTeam2(), current.getTiming());
                    newMatch.setNext(teamMatches);
                    teamMatches = newMatch;
                }
            }
            current = current.getNext();
        }

        return teamMatches;
    }

    public Match getMatchesByLocation(String location) {
        Match current = head;
        Match locationMatches = null;

        while (current != null) {
            if (current.getLocation().equals(location)) {
                if (locationMatches == null) {
                    locationMatches = new Match(current.getLocation(), current.getTeam1(), current.getTeam2(), current.getTiming());
                } else {
                    Match newMatch = new Match(current.getLocation(), current.getTeam1(), current.getTeam2(), current.getTiming());
                    newMatch.setNext(locationMatches);
                    locationMatches = newMatch;
                }
            }
            current = current.getNext();
        }

        return locationMatches;
    }

    public Match getMatchesByTiming(String timing) {
        Match current = head;
        Match timingMatches = null;

        while (current != null) {
            if (current.getTiming().equals(timing)) {
                if (timingMatches == null) {
                    timingMatches = new Match(current.getLocation(), current.getTeam1(), current.getTeam2(), current.getTiming());
                } else {
                    Match newMatch = new Match(current.getLocation(), current.getTeam1(), current.getTeam2(), current.getTiming());
                    newMatch.setNext(timingMatches);
                    timingMatches = newMatch;
                }
            }
            current = current.getNext();
        }

        return timingMatches;
    }
}

public class main {
    public static void main(String[] args) {
        FlightTable flightTable = new FlightTable();

        flightTable.addMatch("Mumbai", "India", "Sri Lanka", "DAY");
        flightTable.addMatch("Delhi", "England", "Australia", "DAY-NIGHT");
        flightTable.addMatch("Chennai", "India", "South Africa", "DAY");
        flightTable.addMatch("Indore", "England", "Sri Lanka", "DAY-NIGHT");
        flightTable.addMatch("Mohali", "Australia", "South Africa", "DAY-NIGHT");
        flightTable.addMatch("Delhi", "India", "Australia", "DAY");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a search parameter:");
        System.out.println("1. List of all the matches of a Team");
        System.out.println("2. List of Matches on a Location");
        System.out.println("3. List of Matches based on timing");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Match matches;

        switch (choice) {
            case 1:
                System.out.print("Enter the team name: ");
                String team = scanner.nextLine();
                matches = flightTable.getMatchesByTeam(team);
                break;
            case 2:
                System.out.print("Enter the location: ");
                String location = scanner.nextLine();
                matches = flightTable.getMatchesByLocation(location);
                break;
            case 3:
                System.out.print("Enter the timing: ");
                String timing = scanner.nextLine();
                matches = flightTable.getMatchesByTiming(timing);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        if (matches == null) {
            System.out.println("No matches found.");
        } else {
            System.out.println("Search Results:");
            while (matches != null) {
                System.out.println(matches);
                matches = matches.getNext();
            }
        }

        scanner.close();
    }
}
