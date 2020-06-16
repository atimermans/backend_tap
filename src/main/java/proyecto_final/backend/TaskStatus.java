package proyecto_final.backend;

public enum TaskStatus {
    Completed, Pending;
	
    public static TaskStatus fromString(String x) {
        switch(x) {
        case "Completed":
            return Completed;
        case "Pending":
            return Pending;
        }
        return null;
    }
}

