
package org.trello4j.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.trello4j.gson.CopyOfPermissionTypeDeserializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Board extends TrelloObject {
    @JsonDeserialize(using = CopyOfPermissionTypeDeserializer.class)
    public enum PERMISSION_TYPE {
        PUBLIC, ORGANIZATION, MEMBERS
    }

    private Boolean closed;
    private String dateLastActivity;
    private String dateLastView;
    private String desc;
    private Object descData;
    private String id;
    private String idOrganization;
    //private List<Object> idTags = null;
    private Object invitations;
    private Boolean invited;
    //private LabelNames labelNames = null;
    private List<Membership> memberships;
    private String name;
    private Object pinned;
   // private List<String> powerUps = null;
    //private Prefs prefs = null;
    private String shortLink;
    private String shortUrl;
    private Boolean starred;
    private Boolean subscribed;
    private String url;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getDescData() {
        return descData;
    }

    public void setDescData(Object descData) {
        this.descData = descData;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public String getIdOrganization() {
        return idOrganization;
    }

    public void setIdOrganization(String idOrganization) {
        this.idOrganization = idOrganization;
    }

    public Object getPinned() {
        return pinned;
    }

    public void setPinned(Object pinned) {
        this.pinned = pinned;
    }

    public Object getInvitations() {
        return invitations;
    }

    public void setInvitations(Object invitations) {
        this.invitations = invitations;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

/*    public List<String> getPowerUps() {
        return powerUps;
    }

    public void setPowerUps(List<String> powerUps) {
        this.powerUps = powerUps;
    }*/

    public String getDateLastActivity() {
        return dateLastActivity;
    }

    public void setDateLastActivity(String dateLastActivity) {
        this.dateLastActivity = dateLastActivity;
    }
/*

    public List<Object> getIdTags() {
        return idTags;
    }

    public void setIdTags(List<Object> idTags) {
        this.idTags = idTags;
    }
*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getInvited() {
        return invited;
    }

    public void setInvited(Boolean invited) {
        this.invited = invited;
    }

    public Boolean getStarred() {
        return starred;
    }

    public void setStarred(Boolean starred) {
        this.starred = starred;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

/*
    public Prefs getPrefs() {
        return prefs;
    }

    public void setPrefs(Prefs prefs) {
        this.prefs = prefs;
    }

    public List<Membership> getMemberships() {
        return memberships;
    }

    public void setMemberships(List<Membership> memberships) {
        this.memberships = memberships;
    }
*/

    public Boolean getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        this.subscribed = subscribed;
    }

/*
    public LabelNames getLabelNames() {
        return labelNames;
    }

    public void setLabelNames(LabelNames labelNames) {
        this.labelNames = labelNames;
    }
*/

    public String getDateLastView() {
        return dateLastView;
    }

    public void setDateLastView(String dateLastView) {
        this.dateLastView = dateLastView;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
