package com.microservice.data;

public class Contact {
    private String PersonalNo;
    private String AlternateNo;

    public Contact(String personalNo, String alternateNo) {
        PersonalNo = personalNo;
        AlternateNo = alternateNo;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "PersonalNo='" + PersonalNo + '\'' +
                ", AlternateNo='" + AlternateNo + '\'' +
                '}';
    }

    public String getPersonalNo() {
        return PersonalNo;
    }

    public void setPersonalNo(String personalNo) {
        PersonalNo = personalNo;
    }

    public String getAlternateNo() {
        return AlternateNo;
    }

    public void setAlternateNo(String alternateNo) {
        AlternateNo = alternateNo;
    }
}
