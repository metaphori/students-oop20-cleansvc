package model.step.enumerations;

public enum StepType {
    /** 
     * Mandatory CLEANING step.
     */
    CLEANING("CLEANING", "Fase iniziale: pulizia"),
    /**
     * Mandatory CLEANSING step.
     */
    CLEANSING("CLEANSING", "Fase opzionale: detersione"),
    /**
     * Mandatory DISINFECTION step. 
     */
    DISINFECTION("DISINFECTION", "Fase chiave del processo: disinfezione"),
    /**
     * Optional RINSING step.
     */
    RINSING("RINSING", "Fase opzionale: risciacquo"),
    /**
     * Optional DISINFESTATION step.
     */
    DISINFESTATION("DISINFESTATION", "Fase opzionale: disinfestazione"),
    /**
     * Mandatory CONCLUSION step.
     */
    CONCLUSION("CONCLUSION", "Fase finale: sistemazione");

    private String name;
    private String description;

    public String getName() {
        return this.name;
    }

    public String getDesciption() {
        return this.description;
    }

    StepType(final String name, final String description) {
        this.description = description;
        this.name = name;
    }
}