package readingTracker.com.br.model;

public class requestStatus {

    public requestStatus(){

    }

    public requestStatus(boolean status){
        requestStatus = status;
    }

    private boolean requestStatus;

    public  boolean getRequestStatus(){
        return requestStatus;
    }

    public void setRequestStatus(boolean requestStatus) {
        this.requestStatus = requestStatus;
    }

}
