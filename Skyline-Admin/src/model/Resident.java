package model;

public class Resident extends User {
    private String lot;

    /**
     * @return the lot the resident owns
     */
    public String getLot() {
        return lot;
    }

    /**
     * @param lot the lot to assign to resident
     */
    public void setLot(String lot) {
        this.lot = lot;
    }
}
