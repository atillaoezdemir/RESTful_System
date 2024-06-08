package de.fhws.fiw.fds.suttondemo.client.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.fhws.fiw.fds.sutton.client.converters.ClientLinkJsonConverter;
import de.fhws.fiw.fds.sutton.client.model.AbstractClientModel;
import de.fhws.fiw.fds.sutton.client.utils.Link;

import java.time.LocalDate;

public class UniversityClientModel extends AbstractClientModel {
    private String uniName;

    private String country;

    private String departmant;

    private String departmantUrl;

    private String contactPerson;

    private int sendStudents;

    private int acceptStudents;

    private LocalDate firstDaySpring;

    private LocalDate firstDayAutumn;


    @JsonDeserialize(using = ClientLinkJsonConverter.class)
    private transient Link selfLinkOnSecond;

    @JsonDeserialize(using = ClientLinkJsonConverter.class)
    private transient Link selfLink;

    public UniversityClientModel() {
    }

    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDepartmant() {
        return departmant;
    }

    public void setDepartmant(String departmant) {
        this.departmant = departmant;
    }

    public String getDepartmantUrl() {
        return departmantUrl;
    }

    public void setDepartmantUrl(String departmantUrl) {
        this.departmantUrl = departmantUrl;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public int getSendStudents() {
        return sendStudents;
    }

    public void setSendStudents(int sendStudents) {
        this.sendStudents = sendStudents;
    }

    public int getAcceptStudents() {
        return acceptStudents;
    }

    public void setAcceptStudents(int acceptStudents) {
        this.acceptStudents = acceptStudents;
    }

    public LocalDate getFirstDaySpring() {
        return firstDaySpring;
    }

    public void setFirstDaySpring(LocalDate firstDaySpring) {
        this.firstDaySpring = firstDaySpring;
    }

    public LocalDate getFirstDayAutumn() {
        return firstDayAutumn;
    }

    public void setFirstDayAutumn(LocalDate firstDayAutumn) {
        this.firstDayAutumn = firstDayAutumn;
    }

    public Link getSelfLinkOnSecond() {
        return selfLinkOnSecond;
    }

    public void setSelfLinkOnSecond(Link selfLinkOnSecond) {
        this.selfLinkOnSecond = selfLinkOnSecond;
    }

    public Link getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(Link selfLink) {
        this.selfLink = selfLink;
    }
}