/*
 * Copyright 2021 University of Applied Sciences Würzburg-Schweinfurt, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.fhws.fiw.fds.suttondemo.server.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.Link;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.annotations.SuttonLink;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.time.LocalDate;

@JsonRootName("university")
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement(name = "university")
public class University extends AbstractModel {

    private String uniName;

    private String country;

    private String departmant;

    private String departmantUrl;

    private String contactPerson;

    private int sendStudents;

    private int acceptStudents;

    private LocalDate firstDaySpring;

    private LocalDate firstDayAutumn;

    @SuttonLink(
            value = "universities/${id}",
            rel = "self"
    )
    private transient Link selfLink;

    @SuttonLink(
            value = "universities/${id}/modules",
            rel = "getModulesOfUniversity"
    )
    private transient Link module;

    public University() {
        // make JPA happy
    }

    public University(String uniName, String country, String departmant, String departmantUrl, String contactPerson,
                      int sendStudents, int acceptStudents, LocalDate firstDaySpring, LocalDate firstDayAutumn) {
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

    public Link getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(Link selfLink) {
        this.selfLink = selfLink;
    }

    public Link getModule() {
        return module;
    }

    public void setModule(Link module) {
        this.module = module;
    }

    @Override
    public String toString() {
        return "University{" +
                "uniName='" + uniName + '\'' +
                ", country='" + country + '\'' +
                ", departmant='" + departmant + '\'' +
                ", departmantUrl='" + departmantUrl + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", sendStudents=" + sendStudents +
                ", acceptStudents=" + acceptStudents +
                ", firstDaySpring=" + firstDaySpring +
                ", firstDayAutumn=" + firstDayAutumn +
                ", module=" + module +
                ", id=" + id +
                '}';
    }
}
