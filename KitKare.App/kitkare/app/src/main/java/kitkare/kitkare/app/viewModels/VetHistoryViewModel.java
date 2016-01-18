package kitkare.kitkare.app.viewModels;

import java.util.Date;

public class VetHistoryViewModel {
    private String lastCall;
    private String vetName;

    public VetHistoryViewModel(){
    }

    public VetHistoryViewModel(String lastCall, String vetName){
        this();
        this.lastCall = lastCall;
    }

    //TODO: make Date
    public String getLastCall() {
        return lastCall;
    }

    public void setLastCall(String lastCall) {
        this.lastCall = lastCall;
    }

    public String getVetName() {
        return vetName;
    }

    public void setVetName(String vetName) {
        this.vetName = vetName;
    }
}
