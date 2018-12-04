package com.ph.idisciple.idiscipleapp.data.remote.model;

public class Speaker {
    private String speakerName;
    private String speakerPlanaryName;
    private String speakerPlanaryDateTime;

    public Speaker(String name, String topic, String date){
        speakerName = name;
        speakerPlanaryName = topic;
        speakerPlanaryDateTime = date;
    }

    public String getSpeakerName(){
        return speakerName;
    }
    public void setSpeakerName(String speakerName){
        this.speakerName = speakerName;
    }

    public String getSpeakerPlanaryName(){
        return speakerPlanaryName;
    }
    public void setSpeakerPlanaryName(String speakerPlanaryName){
        this.speakerPlanaryName = speakerPlanaryName;
    }

    public String getSpeakerPlanaryDateTime(){
        return speakerPlanaryDateTime;
    }
    public void setSpeakerPlanaryDateTime(String dateTime){
        this.speakerPlanaryDateTime = dateTime;
    }
}
