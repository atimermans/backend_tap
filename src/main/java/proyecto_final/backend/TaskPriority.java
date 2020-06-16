package proyecto_final.backend;

public enum TaskPriority {
    Low, Medium, High, Default;
	
    public static TaskPriority fromString(String x) {
        switch(x) {
        case "Low":
            return Low;
        case "Medium":
            return Medium;
        case "High":
        	return High;
        case "Default":
        	return Default;
        }
        return null;
    }
}