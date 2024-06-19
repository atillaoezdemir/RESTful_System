package de.fhws.fiw.fds.suttondemo.client.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.fhws.fiw.fds.sutton.client.converters.ClientLinkJsonConverter;
import de.fhws.fiw.fds.sutton.client.model.AbstractClientModel;
import de.fhws.fiw.fds.sutton.client.utils.Link;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.time.LocalDate;

@XmlRootElement
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

    public UniversityClientModel(String uniName, String country, String departmant, String departmantUrl, String contactPerson, int sendStudents, int acceptStudents, LocalDate firstDaySpring, LocalDate firstDayAutumn) {
        this.uniName = uniName;
        this.country = country;
        this.departmant = departmant;
        this.departmantUrl = departmantUrl;
        this.contactPerson = contactPerson;
        this.sendStudents = sendStudents;
        this.acceptStudents = acceptStudents;
        this.firstDaySpring = firstDaySpring;
        this.firstDayAutumn = firstDayAutumn;
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

    @Override
    public String toString() {
        return "UniversityClientModel{" +
                "uniName='" + uniName + '\'' +
                ", country='" + country + '\'' +
                ", departmant='" + departmant + '\'' +
                ", departmantUrl='" + departmantUrl + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", sendStudents=" + sendStudents +
                ", acceptStudents=" + acceptStudents +
                ", firstDaySpring=" + firstDaySpring +
                ", firstDayAutumn=" + firstDayAutumn +
                ", id=" + id +
                '}';
    }
}